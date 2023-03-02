package fa.training.mock.models.dto.res.trainingResult;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RewardPenaltyDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer milestoneId;
	private String milestoneName;
	private Date date;
	private Double bonusPoint;
	private Double penaltyPoint;
	private String reason;
	private String willDelete;
}
