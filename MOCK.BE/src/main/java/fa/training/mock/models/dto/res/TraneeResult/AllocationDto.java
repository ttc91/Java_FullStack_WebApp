package fa.training.mock.models.dto.res.TraneeResult;

import java.io.Serializable;
import java.util.Date;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.Data;

@Data
public class AllocationDto extends BaseDto implements Serializable {
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
