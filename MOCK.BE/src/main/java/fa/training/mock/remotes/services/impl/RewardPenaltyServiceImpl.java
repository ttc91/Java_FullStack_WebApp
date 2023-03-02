package fa.training.mock.remotes.services.impl;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.res.trainingResult.RewardPenaltyDto;
import fa.training.mock.models.dto.res.trainingResult.TopicMarkDto;
import fa.training.mock.remotes.entities.Allowance;
import fa.training.mock.remotes.entities.RewardPenalty;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.remotes.repositories.MilestoneRepository;
import fa.training.mock.remotes.repositories.RewardPenaltyRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.services.RewardPenaltyService;
import fa.training.mock.ultils.Constants;

@Service
public class RewardPenaltyServiceImpl implements RewardPenaltyService{

	@Autowired
	RewardPenaltyRepository rewardPenaltyRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Boolean saveAll(Collection<RewardPenaltyDto> dtos, Integer traineeId){
		Trainee trainee = traineeRepository.findById(traineeId).get();
		for (RewardPenaltyDto d : dtos) {
			if (d.getWillDelete().equals("false")) {
				RewardPenalty rp = mapper.map(d, RewardPenalty.class);
				rp.setMilestone(milestoneRepository.findById(d.getMilestoneId()).orElse(null));			
				rp.setTrainee(trainee);
				
				rewardPenaltyRepository.save(rp);
			} else {
				rewardPenaltyRepository.deleteById(d.getId());
			}
		}
		return true;
	};

}
