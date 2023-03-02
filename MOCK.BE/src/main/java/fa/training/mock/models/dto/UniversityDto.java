package fa.training.mock.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer universityId;

	@NotEmpty(message = "University name must not empty !!!")
	@Size(min = 5, max = 50, message = "University name must be greater than 5 and lesser than 50")
	private String universityName;

	@Size(max = 200)
	private String remarks;

}
