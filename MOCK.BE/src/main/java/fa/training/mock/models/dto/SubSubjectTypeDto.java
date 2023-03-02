package fa.training.mock.models.dto;

import java.io.Serializable;
import javax.validation.constraints.Size;

import fa.training.mock.config.enums.SubSubjectTypeEnum;
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
public class SubSubjectTypeDto extends BaseDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer subSubjectTypeId;
	
	private SubSubjectTypeEnum subSubjectTypeName;
	
	@Size(max = 200)
	private String remarks;

}
