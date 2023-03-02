package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResultsDto extends BaseDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private List<EntryTestDto> entryTests;
	private List<InterviewDto> interviews;

}
