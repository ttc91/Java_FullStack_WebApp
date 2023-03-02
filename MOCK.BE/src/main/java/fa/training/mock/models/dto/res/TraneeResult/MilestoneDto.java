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
public class MilestoneDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer milestoneId;
	private String milestoneName;
	private Double totalMaxScore; 
	private Double totalWeightedNumber;
	private Double totalPassingScore; 
	private Date startDate;
	private Date endDate;
	private Boolean salaryPaid;
	
	private Collection<TopicDto> topicDtos;
	
	private AttendanceStatusDto attendanceStatusDto;
		
	private Collection<RewardPenaltyDto> rewardPenaltyDtos;
	
	private GPADto gpaDto;
}
