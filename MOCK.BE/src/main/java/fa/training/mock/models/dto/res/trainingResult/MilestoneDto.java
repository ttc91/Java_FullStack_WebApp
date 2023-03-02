package fa.training.mock.models.dto.res.trainingResult;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.Data;

@Data
public class MilestoneDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer milestoneId;
	private String milestoneName;
	private Double totalMaxScore; 	
	private Double totalWeightedNumber;
	private Double totalPassingScore; 	
	private Date startDate;
	private Date endDate;
	private Boolean salaryPaid;
	private Collection<TopicDto> topics;
	private String willDelete;
}
