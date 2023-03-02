package fa.training.mock.remotes.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.res.TraneeResult.AttendanceStatusDto;
import fa.training.mock.models.dto.res.TraneeResult.GPADto;
import fa.training.mock.models.dto.res.TraneeResult.MilestoneDto;
import fa.training.mock.models.dto.res.TraneeResult.RewardPenaltyDto;
import fa.training.mock.models.dto.res.TraneeResult.TopicDto;
import fa.training.mock.models.dto.res.TraneeResult.TraneeResultDto;
import fa.training.mock.remotes.entities.AttendantStatus;
import fa.training.mock.remotes.entities.GPA;
import fa.training.mock.remotes.entities.Milestone;
import fa.training.mock.remotes.entities.RewardPenalty;
import fa.training.mock.remotes.entities.Topic;
import fa.training.mock.remotes.entities.TopicMark;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.repositories.TraneeResultRepository;
import fa.training.mock.remotes.services.TraneeResultService;

@Service
public class TraneeResultServiceImpl implements TraneeResultService {

	@Autowired
	TraneeResultRepository repository;

	@Autowired
	TraineeRepository traineeRepository;
	@Autowired
	ModelMapper mapper;

	@Override
	public List<Milestone> getMilestone(Integer classBatchId, Integer traneeId) {
		TraneeResultDto traneeResultDto = new TraneeResultDto();
		Trainee trainee = traineeRepository.findById(traneeId).get();
		traneeResultDto = mapper.map(trainee, TraneeResultDto.class);

		List<MilestoneDto> milestoneDtos = new ArrayList<>();
		MilestoneDto milestoneDto = new MilestoneDto();

		List<Milestone> milestones = repository.getMilestone(classBatchId);

		for (Milestone milestone : milestones) {
			milestoneDto = mapper.map(milestone, MilestoneDto.class);
			AttendanceStatusDto attendanceStatusDto = new AttendanceStatusDto();
			attendanceStatusDto.setAbsentTimes(0);
			attendanceStatusDto.setLateAndEarlyLeave(0);
			for (AttendantStatus attendantStatus : milestone.getAttendantStatuses()) {
				if (attendantStatus.getTrainee().getTraineeId() == traneeId) {
					switch (attendantStatus.getStatus()) {
					case "A":
						attendanceStatusDto.setAbsentTimes(attendanceStatusDto.getAbsentTimes() + 1);
						break;
					case "E":
						attendanceStatusDto.setLateAndEarlyLeave(attendanceStatusDto.getLateAndEarlyLeave() + 1);
						break;
					case "L":
						attendanceStatusDto.setLateAndEarlyLeave(attendanceStatusDto.getLateAndEarlyLeave() + 1);
						break;
					default:
						break;
					}
				}
			}

			milestoneDto.setAttendanceStatusDto(attendanceStatusDto);

			List<TopicDto> topicDtos = new ArrayList<>();
			TopicDto topicDto = new TopicDto();
			for (Topic topic : milestone.getTopics()) {
				topicDto = mapper.map(topic, TopicDto.class);

				for (TopicMark topicMark : topic.getGpas()) {
					if (topicMark.getTrainee().getTraineeId() == traneeId) {
						topicDto.setScore(topicMark.getScore());
						break;
					}
				}
				
				topicDtos.add(topicDto);
			}
			milestoneDto.setTopicDtos(topicDtos);
			

			List<RewardPenaltyDto> rewardPenaltyDtos = new ArrayList<>();
			for (RewardPenalty rewardPenalty : milestone.getRewardPenalties()) {
				if (rewardPenalty.getTrainee().getTraineeId() == traneeId) {
					rewardPenaltyDtos.add(mapper.map(rewardPenalty, RewardPenaltyDto.class));
				}
			}
			milestoneDto.setRewardPenaltyDtos(rewardPenaltyDtos);
			
			
			GPADto gpaDto = new GPADto();
			for (GPA gpa : milestone.getGpas()) {
				
				if (gpa.getTrainee().getTraineeId() == traneeId) {
					gpaDto = mapper.map(gpa, GPADto.class);
				}
					
			}
			milestoneDto.setGpaDto(gpaDto);
				
		}
		traneeResultDto.setMilestoneDtos(milestoneDtos);
		

		return milestones;
	}

}
