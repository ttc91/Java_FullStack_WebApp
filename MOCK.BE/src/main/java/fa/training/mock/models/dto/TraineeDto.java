package fa.training.mock.models.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.res.trainingResult.AllocationDto;
import fa.training.mock.models.dto.res.trainingResult.RewardPenaltyDto;
import fa.training.mock.models.dto.res.trainingResult.TopicMarkDto;
import lombok.Data;

@Data
public class TraineeDto extends BaseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer traineeId;
	private Boolean salaryPaid;
	private String tpBankAccount;
	private AllowanceGroupDto allowanceGroup;
	private Integer committedWorkingDuration;
	private Date committedWorkingStartDate;
	private String remarks;
	private ProfileDto traineeCandidateProfile;
	private StatusDto status;
	private Collection<AllocationDto> allocations;
	private Collection<RewardPenaltyDto> rewardPenalties;
	private Collection<TopicMarkDto> topicMarks;
	private Collection<AttendantStatusDto> attendantStatuses;
	private Integer clazzId;
}
