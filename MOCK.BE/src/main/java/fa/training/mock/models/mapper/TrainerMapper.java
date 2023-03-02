package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.TrainerDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Trainer;

@Component
public class TrainerMapper extends AbstractModelMapper<Trainer, TrainerDto>{

}
