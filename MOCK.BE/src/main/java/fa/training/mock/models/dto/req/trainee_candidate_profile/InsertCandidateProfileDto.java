package fa.training.mock.models.dto.req.trainee_candidate_profile;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import fa.training.mock.remotes.entities.Faculty;
import fa.training.mock.remotes.entities.University;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertCandidateProfileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String account;

	private String fullName;

	private Date dateOfBirth;

	private String gender;

	private University university;

	private Faculty faculty;

	private String phone;

	private String email;

	private String cv;

	private String skill;

	private Date graduationYear;

	private String foreignLanguage;

	private Integer level;

	private String type;

}
