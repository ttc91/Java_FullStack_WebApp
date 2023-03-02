package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.req.trainee_candidate_profile.CandidateProfileDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.TraineeCandidateProfile;

@Component
public class CandidateProfileMapper extends AbstractModelMapper<TraineeCandidateProfile, CandidateProfileDto> {

}
