package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.CandidateDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Candidate;

@Component
public class CandidateMapper extends AbstractModelMapper<Candidate, CandidateDto>{

}
