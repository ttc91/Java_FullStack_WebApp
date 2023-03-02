package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.StatusDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Status;

@Component
public class StatusMapper extends AbstractModelMapper<Status, StatusDto>{

}
