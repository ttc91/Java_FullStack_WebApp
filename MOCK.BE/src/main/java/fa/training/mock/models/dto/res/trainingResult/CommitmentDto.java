package fa.training.mock.models.dto.res.trainingResult;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CommitmentDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double commitedValue;
	private Integer commitedWorkingDuration;
	private Date commitedWorkingStartDate;
	private Date commitedWorkingEndDate;
	private String remarks;
}
