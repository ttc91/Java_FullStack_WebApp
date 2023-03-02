package fa.training.mock.remotes.services.impl;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.res.trainingResult.AllocationDto;
import fa.training.mock.remotes.entities.Allocation;
import fa.training.mock.remotes.repositories.AllocationRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.services.AllocationService;

@Service
public class AllocationServiceImpl implements AllocationService{

	@Autowired
	AllocationRepository allocationRepository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Boolean saveAll(Collection<AllocationDto> dtos, Integer traineeId) {
		if (dtos != null) {
			for (AllocationDto d : dtos) {
				Allocation a =  mapper.map(d, Allocation.class);
				a.setTrainee(traineeRepository.findById(traineeId).get());
				
				allocationRepository.save(a);
			}		
			return true;
		}
		return false;
	}

}
