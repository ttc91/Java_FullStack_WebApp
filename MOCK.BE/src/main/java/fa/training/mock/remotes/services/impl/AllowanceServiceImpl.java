package fa.training.mock.remotes.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.res.trainingResult.AllowanceDto;
import fa.training.mock.remotes.entities.Allowance;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.remotes.repositories.AllowanceRepository;
import fa.training.mock.remotes.repositories.AllowanceStandardRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.services.AllowanceService;

@Service
public class AllowanceServiceImpl implements AllowanceService{

	@Autowired
	AllowanceRepository allowanceRepository;
	
	@Autowired
	AllowanceStandardRepository allowanceStandardRepository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	ModelMapper mapper;
	

	@Override
	public Boolean saveAll(Collection<AllowanceDto> dtos, Integer traineeId) {
		Trainee trainee =  traineeRepository.findById(traineeId).get();
		for (AllowanceDto d : dtos) {
			Allowance a = mapper.map(d, Allowance.class);
			a.setAllowanceStandard(allowanceStandardRepository.findById(d.getAllowanceStandardId()).orElse(null));
			a.setTrainee(trainee);
			
			allowanceRepository.save(a);
		}
		return true;
	}
}
