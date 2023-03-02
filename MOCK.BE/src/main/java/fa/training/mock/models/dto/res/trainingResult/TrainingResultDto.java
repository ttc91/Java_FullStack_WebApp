package fa.training.mock.models.dto.res.trainingResult;

import java.util.Collection;

import fa.training.mock.models.dto.base.BaseDto;
import lombok.Data;

@Data
public class TrainingResultDto extends BaseDto {
	private Integer traineeId;
	private Integer classId;
	private Collection<MilestoneDto> milestoneDtos;
	private Collection<AttendanceStatusDto> attendanceStatusDtos;
	
	private Collection<MilestoneMarkDto> milestoneMarkDtos;
	private Collection<TopicMarkDto> topicMarkDtos;	//dung khi update
	
	private Collection<RewardPenaltyDto> rewardPenaltyDtos;
	private Collection<GPADto> gpaDtos;
	private CommitmentDto commitmentDto;
	private Collection<AllowanceDto> allowanceDtos;
	private Collection<AllocationDto> allocationDtos;
}
