package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.InterviewDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Interview;
@Component
public class InterviewMapper extends AbstractModelMapper<Interview, InterviewDto> {

}
