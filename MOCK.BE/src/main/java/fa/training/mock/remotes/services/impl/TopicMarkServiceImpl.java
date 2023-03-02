package fa.training.mock.remotes.services.impl;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import fa.training.mock.models.dto.res.trainingResult.MilestoneDto;
import fa.training.mock.models.dto.res.trainingResult.TopicMarkDto;
import fa.training.mock.remotes.entities.Topic;
import fa.training.mock.remotes.entities.TopicMark;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.remotes.repositories.TopicMarkRepository;
import fa.training.mock.remotes.repositories.TopicRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.services.TopicMarkService;

@Service
public class TopicMarkServiceImpl implements TopicMarkService{
	@Autowired
	TopicMarkRepository topicMarkRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Boolean saveAll(Collection<TopicMarkDto> dtos, Integer traineeId) {
		Trainee trainee = traineeRepository.findById(traineeId).get();
		for (TopicMarkDto d : dtos) {
			TopicMark tm = mapper.map(d, TopicMark.class);
			
			Topic temp = topicRepository.findById(d.getTopicId()).orElse(null);
			if (temp == null) continue;
			tm.setTopic(temp);
			tm.setTrainee(trainee);
			
			topicMarkRepository.save(tm);
		}
		
		return true;
	};
	
}
