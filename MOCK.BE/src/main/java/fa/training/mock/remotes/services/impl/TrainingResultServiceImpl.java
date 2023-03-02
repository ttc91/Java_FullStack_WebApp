package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.dto.res.trainingResult.AllocationDto;
import fa.training.mock.models.dto.res.trainingResult.AllowanceDto;
import fa.training.mock.models.dto.res.trainingResult.AttendanceStatusDto;
import fa.training.mock.models.dto.res.trainingResult.CommitmentDto;
import fa.training.mock.models.dto.res.trainingResult.GPADto;
import fa.training.mock.models.dto.res.trainingResult.MilestoneDto;
import fa.training.mock.models.dto.res.trainingResult.MilestoneMarkDto;
import fa.training.mock.models.dto.res.trainingResult.RewardPenaltyDto;
import fa.training.mock.models.dto.res.trainingResult.TopicMarkDto;
import fa.training.mock.models.dto.res.trainingResult.TrainingResultDto;
import fa.training.mock.models.mapper.AllocationMapper;
import fa.training.mock.models.mapper.MilestoneMapper;
import fa.training.mock.remotes.entities.Allocation;
import fa.training.mock.remotes.entities.AttendantStatus;
import fa.training.mock.remotes.entities.Milestone;
import fa.training.mock.remotes.entities.RewardPenalty;
import fa.training.mock.remotes.entities.Topic;
import fa.training.mock.remotes.entities.TopicMark;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.remotes.repositories.MilestoneRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.services.AllocationService;
import fa.training.mock.remotes.services.AllowanceService;
import fa.training.mock.remotes.services.MilestoneService;
import fa.training.mock.remotes.services.RewardPenaltyService;
import fa.training.mock.remotes.services.TopicMarkService;
import fa.training.mock.remotes.services.TraineeService;
import fa.training.mock.remotes.services.TrainingResultService;

@Service
public class TrainingResultServiceImpl implements TrainingResultService {

	@Autowired
	MilestoneService milestoneService;
	
	@Autowired
	TopicMarkService topicMarkService;
	
	@Autowired
	RewardPenaltyService rewardPenaltyService;
	
	@Autowired
	AllowanceService allowanceService;
	
	@Autowired
	AllocationService allocationService;
	
	@Autowired
	TraineeService traineeService;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AllocationMapper allocationMapper;
	
	@Autowired
	MilestoneMapper milestoneMapper;

	@Override
	public ResponseDto<BaseDto> update(TrainingResultDto dto){
		try {
			milestoneService.saveAll(dto.getMilestoneDtos(), dto.getClassId());
			topicMarkService.saveAll(dto.getTopicMarkDtos(), dto.getTraineeId());
			System.out.println( "=====" + dto.getRewardPenaltyDtos());
			if(dto.getRewardPenaltyDtos().size() !=0)	rewardPenaltyService.saveAll(dto.getRewardPenaltyDtos(), dto.getTraineeId());
			
			traineeService.update(dto.getCommitmentDto(), dto.getTraineeId());
//			allowanceService.saveAll(dto.getAllowanceDtos(), dto.getTraineeId());
			allocationService.saveAll(dto.getAllocationDtos(), dto.getTraineeId());
			
			ResponseDto<BaseDto> response = getOne(dto.getClassId(), dto.getTraineeId());
			response.setMessage("Update training result completed!!!");
			return response;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update training result failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	};

	@Override
	public ResponseDto<BaseDto> getOne(int classId, int traineeId){
		TrainingResultDto dto = new TrainingResultDto();
		dto.setTraineeId(traineeId);
		dto.setClassId(classId);
		
		List<Milestone> milestones = milestoneRepository.findByClassId(classId);
		
		Collection<MilestoneDto> milestoneDtos = milestoneMapper.mapToDtoList(milestones, MilestoneDto.class);
		Collection<AttendanceStatusDto> statusDtos = new ArrayList<AttendanceStatusDto>();
		Collection<MilestoneMarkDto> milestoneMarkDtos = new ArrayList<MilestoneMarkDto>();
		Collection<RewardPenaltyDto> rewardPenaltyDtos = new ArrayList<RewardPenaltyDto>();
		Collection<GPADto> gpaDtos = new ArrayList<GPADto>();
		Collection<AllowanceDto> allowanceDtos = new ArrayList<AllowanceDto>();
		Collection<AllocationDto> aloDtos = new ArrayList<AllocationDto>();
		
		dto.setMilestoneDtos(milestoneDtos);
		
		for (Milestone m : milestones) {
			AttendanceStatusDto attendanceStatusDto = new AttendanceStatusDto();
			attendanceStatusDto.setMilestoneId(m.getMilestoneId());
			attendanceStatusDto.setMilestoneName(m.getMilestoneName());
			attendanceStatusDto.setAbsentTimes(0);
			attendanceStatusDto.setLateAndEarlyLeave(0);
			if (m.getAttendantStatuses()!=null) {
				for (AttendantStatus attendantStatus : m.getAttendantStatuses()) {
					if (attendantStatus.getTrainee().getTraineeId() == traineeId) {
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
			}

			statusDtos.add(attendanceStatusDto);
			dto.setAttendanceStatusDtos(statusDtos);

			//milestone mark
			MilestoneMarkDto milestoneMarkDto = new MilestoneMarkDto();
			milestoneMarkDto.setMilestoneName(m.getMilestoneName());
			milestoneMarkDto.setMilestoneMark(0);	//tam thoi
			
			Collection<TopicMarkDto> topicMarkDtos = new ArrayList<TopicMarkDto>();
			
			GPADto gpaDto = new GPADto();
			gpaDto.setMilestoneName(m.getMilestoneName());
			if (m.getTopics() != null) {
				for (Topic topic : m.getTopics()) {
					TopicMarkDto topicMarkDto = new TopicMarkDto();
					topicMarkDto.setTopicId(topic.getTopicId());
					topicMarkDto.setTopicName(topic.getTopicName());
					if (topic.getGpas() != null) {
						for (TopicMark topicMark : topic.getGpas()) {
							if (topicMark.getTrainee().getTraineeId() == traineeId) {
								topicMarkDto.setScore(topicMark.getScore());
								gpaDto.setAcademicMark(Optional.ofNullable(gpaDto.getAcademicMark()).orElse(0D)  + Optional.ofNullable(topicMark.getScore()).orElse(0D)*10);
								break;
							}
						}
					}
					
					topicMarkDtos.add(topicMarkDto);
				}
			}

			if (m.getTopics().size()>0) {
				gpaDto.setAcademicMark(Optional.ofNullable(gpaDto.getAcademicMark()).orElse(0D)/m.getTopics().size());				
			}
			
			milestoneMarkDto.setTopicMarkDtos(topicMarkDtos);
			milestoneMarkDtos.add(milestoneMarkDto);
			
			dto.setMilestoneMarkDtos(milestoneMarkDtos);
			
			//reward penalty
			if (m.getRewardPenalties()!=null) {
				for (RewardPenalty rewardPenalty : m.getRewardPenalties()) {
					if (rewardPenalty.getTrainee().getTraineeId() == traineeId) {
						rewardPenaltyDtos.add(modelMapper.map(rewardPenalty, RewardPenaltyDto.class));
					}
				}				
			}
			dto.setRewardPenaltyDtos(rewardPenaltyDtos);
			
			
//			for (GPA gpa : m.getGpas()) {
//				if (gpa.getTrainee().getTraineeId() == traineeId) {
//					gpaDto = modelMapper.map(gpa, GPADto.class);
//					break;
//				}
//			}
			
			gpaDto.setMilestoneName(m.getMilestoneName());
			gpaDtos.add(gpaDto);
			dto.setGpaDtos(gpaDtos);
				
			//allowance
			AllowanceDto allowanceDto = new AllowanceDto();
			
			dto.setAllowanceDtos(allowanceDtos);
		}
		
		//commitment
		Trainee trainee = traineeRepository.findById(traineeId).get();
		CommitmentDto commit = modelMapper.map(trainee, CommitmentDto.class);
		commit.setCommitedValue(trainee.getCommitedValue());
		commit.setCommitedWorkingDuration(trainee.getCommittedWorkingDuration());
        commit.setCommitedWorkingStartDate(trainee.getCommittedWorkingStartDate());
		dto.setCommitmentDto(commit);
		
		
		
		//allocation
		if (trainee.getAllocations()!=null) {
			for (Allocation a : trainee.getAllocations()) {
				aloDtos.add(allocationMapper.mapToDto(a, AllocationDto.class));
			}			
		}
		dto.setAllocationDtos(aloDtos);
		

		return ResponseDto.<BaseDto>builder().message("Get training result completed !!!")
				.obj(dto)
				.createdTime(LocalDateTime.now())
				.statusCode(ResponseCode.RESPONSE_OK_CODE).build(); 
	};
}
