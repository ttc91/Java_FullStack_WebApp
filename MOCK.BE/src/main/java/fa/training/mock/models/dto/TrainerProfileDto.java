package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainerProfileDto extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Size(max = 50)
	private String fullName;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	private Boolean gender;
	
	@Size(max = 50)
	private String unit;
	
	@Size(max = 50)
	private String major;
	
	private String phone;
	
	@Size(max = 100)
	private String email;
	
	private String experience;
	
	@Size(max = 200)
	private String remarks;
	
}
