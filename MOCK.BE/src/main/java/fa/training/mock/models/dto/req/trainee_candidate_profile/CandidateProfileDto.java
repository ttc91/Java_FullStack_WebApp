package fa.training.mock.models.dto.req.trainee_candidate_profile;

import java.io.Serializable;
import java.util.Date;

import fa.training.mock.models.dto.FacultyDto;
import fa.training.mock.models.dto.UniversityDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.remotes.entities.Faculty;
import fa.training.mock.remotes.entities.University;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateProfileDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Candidate Profile
	private Integer profileId;
	
	private String account;

	private String fullName;

	private Date dateOfBirth;

	private String gender;

	private UniversityDto university;

	private FacultyDto faculty;

	private String phone;

	private String email;

	private String cv;

	private String skill;

	private Date graduationYear;

	private String foreignLanguage;

	private Integer level;

	private String type;

}
