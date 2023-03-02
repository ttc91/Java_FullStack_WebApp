package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.UniversityDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.University;

@Component
public class UniversityMapper extends AbstractModelMapper<University, UniversityDto>{

}
