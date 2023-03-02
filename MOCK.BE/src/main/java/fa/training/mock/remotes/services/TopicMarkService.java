package fa.training.mock.remotes.services;

import java.util.Collection;

import fa.training.mock.models.dto.res.trainingResult.TopicMarkDto;

public interface TopicMarkService {

	Boolean saveAll(Collection<TopicMarkDto> dtos, Integer traineeId);

}
