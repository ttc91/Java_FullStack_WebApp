package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Date;

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
public class AdminProfileDto extends BaseDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String fullName;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;
	private Boolean gender;
	private String phone;
	private String email;
	private String remarks;
}
