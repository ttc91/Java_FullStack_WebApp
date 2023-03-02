package fa.training.mock.models.dto.req.candidate;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCandidateDto implements Serializable {

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

	private Integer university;

	private Integer faculty;

	private String phone;

	private String email;     

	private String cv;

	private String skill;

	private Date graduationYear;

	private String foreignLanguage;

	private Integer level;
	
	private String type;
	
	// Candidate
	private Integer id;

	private Date applicationDate;
	
	private Integer channel;
	
	private Integer location;

	private String note;
}
