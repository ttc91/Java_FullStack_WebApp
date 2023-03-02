package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Date;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.req.trainee_candidate_profile.CandidateProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CandidateDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Candidate Profile
	private CandidateProfileDto traineeCandidateProfile;
	
	// Candidate
	private Integer id;

	private Date applicationDate;
	
	private ChannelDto channel;
	
	private LocationDto location;

	private String note;

	private String status;

}
