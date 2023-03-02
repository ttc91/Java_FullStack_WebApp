package fa.training.mock.models.dto.req.trainer;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertTrainerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Full name must not empty !!!")
	private String fullName;
	
	@NotEmpty(message = "User name must not empty !!!")
	private String username;
	
	@NotEmpty(message = "Password must not empty !!!")
	@Min(value = 8)
	private String password;
	
	private String type;
	
	@NotEmpty(message = "Birthday must not empty !!!")
	private String dateOfBirth;
	
	@NotNull(message = "Gender must not null !!!")
	private Boolean gender;
	
	@NotEmpty(message = "Unit must not empty !!!")
	private String unit;
	
	@NotEmpty(message = "Major must not empty !!!")
	private String major;
	
	@NotEmpty(message = "User name must not empty !!!")
	@Pattern(regexp="(84|0[3|5|7|8|9])+([0-9]{8})\\b", message="Invalid phone number !")
	private String phone;
	
	@NotEmpty(message = "Email must not empty !!!")
	@Email(message = "Email is not correct format !!!")
	private String email;
	
	@NotEmpty(message = "Experience must not empty !!!")
	private String experience;
	
	private String remarks;

}
