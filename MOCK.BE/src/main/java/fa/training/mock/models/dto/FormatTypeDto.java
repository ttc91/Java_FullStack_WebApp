package fa.training.mock.models.dto;

import java.io.Serializable;

import fa.training.mock.config.enums.FormatTypeEnum;
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
public class FormatTypeDto extends BaseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private FormatTypeEnum formatTypeName;
	
	private String remarks;

}
