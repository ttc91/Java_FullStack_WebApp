package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.List;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TraineeDtoList extends BaseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Integer milestoneId;
	List<TraineeDto> traineeDtos;

	public TraineeDtoList(List<TraineeDto> traineeDtos) {
		super();
		this.traineeDtos = traineeDtos;
	}
		
}

