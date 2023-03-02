package fa.training.mock.models.dto.res.trainingResult;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AllocationDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String allocatedFSU;
	private Double salary;
	private Date startDate;
	private String allocationStatus;
	private String remarks;
}
