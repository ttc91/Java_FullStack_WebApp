package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.res.trainingResult.TopicDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Topic;

@Component
public class TopicMapper extends AbstractModelMapper<Topic, TopicDto>{

}
