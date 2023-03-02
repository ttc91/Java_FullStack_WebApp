package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.FacultyDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Faculty;

@Component
public class FacultyMapper extends AbstractModelMapper<Faculty, FacultyDto>{

}
