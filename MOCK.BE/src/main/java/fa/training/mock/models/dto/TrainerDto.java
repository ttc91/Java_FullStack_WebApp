package fa.training.mock.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class TrainerDto extends BaseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer trainerId;
	
	@NotEmpty(message = "Type must not be empty !!!")
	@Size(max = 100)
	private String type;
	
	private String username;
	
	private String password;
	
	@NotNull(message = "Profile must not be null !!!")
	private TrainerProfileDto trainerProfile;

}
