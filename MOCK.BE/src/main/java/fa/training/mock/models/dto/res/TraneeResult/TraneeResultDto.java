package fa.training.mock.models.dto.res.TraneeResult;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class TraneeResultDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer traineeId;
	private Long committedValue;
	private Integer committedWorkingDuration;
	private Date committedWorkingStartDate;
	private Date committedWorkingEndDate;
	private String remarks;
	private Collection<AllocationDto> allocations;
	private Collection<MilestoneDto> milestoneDtos;
}
