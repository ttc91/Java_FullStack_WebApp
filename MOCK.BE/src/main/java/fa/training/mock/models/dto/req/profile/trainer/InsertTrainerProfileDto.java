package fa.training.mock.models.dto.req.profile.trainer;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class InsertTrainerProfileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer trainerId;
	private String fullName;
	private String dateOfBirth;
	private Boolean gender;
	private String unit;
	private String major;
	private String phone;
	private String email;
	private String experience;
	private String remarks;

}
