package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.TrainerProfileDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.TrainerProfile;

@Component
public class TrainerProfileMapper extends AbstractModelMapper<TrainerProfile, TrainerProfileDto>{

}
