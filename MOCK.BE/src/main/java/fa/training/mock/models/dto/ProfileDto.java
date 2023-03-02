package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Date;

import fa.training.mock.models.dto.FacultyDto;
import fa.training.mock.models.dto.UniversityDto;
import fa.training.mock.models.dto.base.BaseDto;
import lombok.Data;

@Data
public class ProfileDto extends BaseDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Integer profileId;
	private String account;
	private String fullName;
	private String gender;
	private Date dateOfBirth;
	private String phone;
	private String email;
	private Integer universityPK;
	private Integer facultyPK;


}
