package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Date;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntryTestDto extends BaseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer entryTestId;
	
    private Integer time;
	
	private Date date;
	
	private String languageValuator;
	
	private String languagePoint;
	
	private String technicalValuator;
	
	private String technicalPoint;
	
	private String result;
	
	private String remarks;
	
	private String willDelete;
		

}
