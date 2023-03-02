package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.ProfileDto;
import fa.training.mock.models.dto.TraineeDto;
import fa.training.mock.models.dto.TraineeDtoList;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.dto.res.trainingResult.CommitmentDto;
import fa.training.mock.models.dto.res.trainingResult.RewardPenaltyDto;
import fa.training.mock.models.dto.res.trainingResult.TopicMarkDto;
import fa.training.mock.models.mapper.TraineeMapper;
import fa.training.mock.remotes.entities.Allocation;
import fa.training.mock.remotes.entities.AttendantStatus;
import fa.training.mock.remotes.entities.ClassBatch;
import fa.training.mock.remotes.entities.RewardPenalty;
import fa.training.mock.remotes.entities.TopicMark;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.remotes.entities.TraineeCandidateProfile;
import fa.training.mock.remotes.repositories.AllocationRepository;
import fa.training.mock.remotes.repositories.AttendanceStatusRepository;
import fa.training.mock.remotes.repositories.ClassBatchRepository;
import fa.training.mock.remotes.repositories.FacultyRepository;
import fa.training.mock.remotes.repositories.MilestoneRepository;
import fa.training.mock.remotes.repositories.RewardPenaltyRepository;
import fa.training.mock.remotes.repositories.StatusRepository;
import fa.training.mock.remotes.repositories.TopicMarkRepository;
import fa.training.mock.remotes.repositories.TraineeCandidateProfileRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.repositories.UniversityRepository;
import fa.training.mock.remotes.services.TraineeService;

@Service
public class TraineeServiceImpl implements TraineeService {
	@Autowired
	TraineeRepository repository;

	@Autowired	TraineeCandidateProfileRepository profileRepository;

	@Autowired
	UniversityRepository universityRepository;

	@Autowired
	FacultyRepository facultyRepository;

	@Autowired
	AllocationRepository allocationRepository;

	@Autowired
	ClassBatchRepository classBatchRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	TopicMarkRepository topicMarkRepository;

	@Autowired
	RewardPenaltyRepository rewardPenaltyRepository;

	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	AttendanceStatusRepository attendanceStatusRepository;
	
	@Autowired
	TraineeMapper mapper;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub

		try {
			TraineeDto traineeDto = (TraineeDto) obj;
			ProfileDto profileDto = traineeDto.getTraineeCandidateProfile();

			Trainee trainee = mapper.mapToEntity(traineeDto, Trainee.class);

			TraineeCandidateProfile profile = trainee.getTraineeCandidateProfile();
			profile.setUniversity(universityRepository.findById(profileDto.getUniversityPK()).get());
			profile.setFaculty(facultyRepository.findById(profileDto.getFacultyPK()).get());

			profileRepository.save(profile);

			trainee.setTraineeCandidateProfile(profile);
			repository.save(trainee);
			TraineeDto dto = mapper.mapToDto(trainee, TraineeDto.class);
			dto.getTraineeCandidateProfile().setUniversityPK(profileDto.getUniversityPK());
			dto.getTraineeCandidateProfile().setFacultyPK(profileDto.getFacultyPK());

			return ResponseDto.<BaseDto>builder().message("Create trainee completed !!!").obj(dto)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_CREATED).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Create trainee failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub

		try {
			TraineeDto traineeDto = (TraineeDto) obj;
			ProfileDto profileDto = traineeDto.getTraineeCandidateProfile();

			Trainee trainee = mapper.mapToEntity(traineeDto, Trainee.class);

			TraineeCandidateProfile profile = trainee.getTraineeCandidateProfile();
			profile.setUniversity(universityRepository.findById(profileDto.getUniversityPK()).get());
			profile.setFaculty(facultyRepository.findById(profileDto.getFacultyPK()).get());

			profileRepository.save(profile);

			trainee.setTraineeCandidateProfile(profile);
			repository.save(trainee);
			TraineeDto dto = mapper.mapToDto(trainee, TraineeDto.class);
			dto.getTraineeCandidateProfile().setUniversityPK(profileDto.getUniversityPK());
			dto.getTraineeCandidateProfile().setFacultyPK(profileDto.getFacultyPK());

			return ResponseDto.<BaseDto>builder().message("Update trainee completed !!!").obj(dto)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update trainee failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub

		try {

			Trainee trainee = repository.findById(mapper.mapToEntity((TraineeDto) obj, Trainee.class).getTraineeId())
					.get();
			repository.delete(trainee);

			return ResponseDto.<BaseDto>builder().message("Delete trainee completed !!!")
					.obj(mapper.mapToDto(trainee, TraineeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Delete trainee failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Trainee trainee = repository.findById(((TraineeDto) obj).getTraineeId())
					.get();
			TraineeDto traineeDto = mapper.mapToDto(trainee, TraineeDto.class);
			if (trainee.getClassBatch() != null) {
				traineeDto.setClazzId(trainee.getClassBatch().getClassBatchId());				
			}
			traineeDto.getTraineeCandidateProfile()
					.setUniversityPK(trainee.getTraineeCandidateProfile().getUniversity().getUniversityId());
			traineeDto.getTraineeCandidateProfile()
					.setFacultyPK(trainee.getTraineeCandidateProfile().getFaculty().getMajorId());
			return ResponseDto.<BaseDto>builder().message("Get trainee completed !!!").obj(traineeDto)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get trainee failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {

			List<?> dtos = mapper.mapToDtoList(repository.findAll(), TraineeDto.class);

			return ResponseDto.<BaseDto>builder().message("Get all trainees completed !!!").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all trainees failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAllByClassBatchId(Integer classBatchId) {
		// TODO Auto-generated method stub
		try {
			List<Trainee> trainees = (List<Trainee>) classBatchRepository.findById(classBatchId).get().getTrainees();
			List<TraineeDto> dtos = new ArrayList<>();

			for (Trainee trainee : trainees) {
				System.out.println("========== " + trainee.getStatus());
				TraineeDto traineeDto = mapper.mapToDto(trainee, TraineeDto.class);
				traineeDto.getTraineeCandidateProfile()
						.setUniversityPK(trainee.getTraineeCandidateProfile().getUniversity().getUniversityId());
				traineeDto.getTraineeCandidateProfile()
						.setFacultyPK(trainee.getTraineeCandidateProfile().getFaculty().getMajorId());

				dtos.add(traineeDto);
			}

			return ResponseDto.<BaseDto>builder().message("Get all trainees by class batch completed !!!").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all trainees by class batch failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public List<Trainee> findAll() {
		return repository.findAll();
	}

	@Override
	public ResponseDto<BaseDto> findByWaitingforClass(Integer classBatchId) {
		try {
			List<Trainee> trainees = repository.findByWaitingforClass(classBatchId);
			List<TraineeDto> dtos = new ArrayList<>();

			for (Trainee trainee : trainees) {
				TraineeDto traineeDto = mapper.mapToDto(trainee, TraineeDto.class);
				traineeDto.getTraineeCandidateProfile()
						.setUniversityPK(trainee.getTraineeCandidateProfile().getUniversity().getUniversityId());
				traineeDto.getTraineeCandidateProfile()
						.setFacultyPK(trainee.getTraineeCandidateProfile().getFaculty().getMajorId());
				dtos.add(traineeDto);
			}

			return ResponseDto.<BaseDto>builder().message("Get all trainees by class batch completed !!!").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all trainees by class batch failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public Trainee update(CommitmentDto dto, Integer traineeId) {
		Trainee trainee = repository.findById(traineeId).get();
		trainee.setCommitedValue(dto.getCommitedValue());
		trainee.setCommittedWorkingDuration(dto.getCommitedWorkingDuration());
		trainee.setCommittedWorkingStartDate(dto.getCommitedWorkingStartDate());
		return repository.save(trainee);
	}

	@Override
	public ResponseDto<BaseDto> updateLocation(List<TraineeDto> obj) {
		// TODO Auto-generated method stub

		try {

			List<Trainee> trainees = mapper.mapToEntityList((List<TraineeDto>) obj, Trainee.class);
			for (Trainee trainee : trainees) {
				Trainee trainee2 = repository.findById(trainee.getTraineeId()).get();
				for (Allocation allocation : trainee.getAllocations()) {
					allocation.setTrainee(trainee2);
				}
				allocationRepository.saveAll(trainee.getAllocations());
			}

			return ResponseDto.<BaseDto>builder().message("Update location in trainee completed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update trainee failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> updateTraineeStatus(List<TraineeDto> obj) {
		// TODO Auto-generated method stub

		try {

			List<Trainee> trainees = mapper.mapToEntityList((List<TraineeDto>) obj, Trainee.class);
			for (Trainee trainee : trainees) {
				Trainee trainee2 = repository.findById(trainee.getTraineeId()).get();
				trainee2.setStatus(trainee.getStatus());
				repository.save(trainee2);
			}
//			repository.saveAll(trainees);

			return ResponseDto.<BaseDto>builder().message("Update status in trainee completed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update status in trainee failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> updateTopicMark(List<TraineeDto> obj) {
		// TODO Auto-generated method stub

		try {
			for (TraineeDto traineeDto : obj) {
				for (TopicMarkDto topicMarkDto : traineeDto.getTopicMarks()) {
					TopicMark topicMark = topicMarkRepository
							.findByTraineeIdAndTopicId(traineeDto.getTraineeId(), topicMarkDto.getTopicId()).get(0);
					topicMark.setScore(topicMarkDto.getScore());
					topicMarkRepository.save(topicMark);
				}

			}

			return ResponseDto.<BaseDto>builder().message("Update topic mark in trainee completed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update topic mark in trainee failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> updateRewardPenalty(List<TraineeDto> obj) {
		// TODO Auto-generated method stub

		try {
			for (TraineeDto traineeDto : obj) {
				for (RewardPenaltyDto rewardPenaltyDto : traineeDto.getRewardPenalties()) {
					RewardPenalty rewardPenalty = rewardPenaltyRepository
							.findByTraineeIdAndMilestoneId(traineeDto.getTraineeId(), rewardPenaltyDto.getMilestoneId())
							.get(0);
					rewardPenalty.setBonusPoint(rewardPenaltyDto.getBonusPoint());
					rewardPenalty.setDate(rewardPenaltyDto.getDate());
					rewardPenalty.setPenaltyPoint(rewardPenaltyDto.getPenaltyPoint());
					rewardPenalty.setReason(rewardPenaltyDto.getReason());
					rewardPenaltyRepository.save(rewardPenalty);
				}

			}

			return ResponseDto.<BaseDto>builder().message("Update topic mark in trainee completed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update topic mark in trainee failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> addTraineeToClass(List<Integer> traineeIds, Integer classId) {
		// TODO Auto-generated method stub

		try {
			ClassBatch classBatch = classBatchRepository.findById(classId).orElse(null);
			for (Integer x : traineeIds) {
				Trainee trainee = repository.findById(x).orElse(null);
				trainee.setClassBatch(classBatch);
				repository.save(trainee);
			}

			return ResponseDto.<BaseDto>builder().message("Add trainee to class completed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Add trainee to class failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	@Override
	public ResponseDto<BaseDto> removeTraineeFromClass(List<Integer> traineeIds) {
		// TODO Auto-generated method stub

		try {
			for (Integer x : traineeIds) {
				Trainee trainee = repository.findById(x).orElse(null);
				trainee.setClassBatch(null);
				repository.save(trainee);
			}

			return ResponseDto.<BaseDto>builder().message("Remove trainee from class completed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Remove trainee from class failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	public Boolean updateAttendenceStatus(TraineeDtoList traineeDtoList) {
		List<Trainee> trainees = new ArrayList<Trainee>();
		for (TraineeDto traineeDto : traineeDtoList.getTraineeDtos()) {
			Trainee trainee = repository.findById(traineeDto.getTraineeId()).orElse(null);
			
			List<AttendantStatus> attendanceStatuses = traineeDto.getAttendantStatuses().stream()
					.filter(line -> {
						if ("P".equals(line.getStatus()) && line.getId() == null) {
							return false;
						}
						return true;
					})
					.map((line) -> {
						AttendantStatus attendantStatus = modelMapper.map(line, AttendantStatus.class);
						attendantStatus.setTrainee(trainee);
						attendantStatus.setMilestone(milestoneRepository.findById(traineeDtoList.getMilestoneId()).orElse(null));
						return attendantStatus;
					})
	                .collect(Collectors.toList());
			
			System.out.println(attendanceStatuses);
			
			attendanceStatusRepository.saveAll(attendanceStatuses);
		}
		
		return true;
	}
}
