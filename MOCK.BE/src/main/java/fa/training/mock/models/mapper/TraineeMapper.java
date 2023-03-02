package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.TraineeDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Trainee;

@Component
public class TraineeMapper extends AbstractModelMapper<Trainee, TraineeDto>{

}
