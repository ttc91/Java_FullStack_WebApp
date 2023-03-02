package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.TraineeDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.dto.req.candidate.TransferCandidateDto;
import fa.training.mock.models.mapper.TraineeMapper;
import fa.training.mock.remotes.entities.Candidate;
import fa.training.mock.remotes.entities.ClassBatch;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.models.dto.CandidateDto;
import fa.training.mock.models.dto.ClassBatchDto;
import fa.training.mock.models.dto.req.trainee_candidate_profile.CandidateProfileDto;
import fa.training.mock.models.mapper.CandidateMapper;
import fa.training.mock.remotes.repositories.CandidateRepository;
import fa.training.mock.remotes.repositories.StatusRepository;
import fa.training.mock.remotes.repositories.TraineeCandidateProfileRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.services.CandidateService;
import fa.training.mock.remotes.services.TraineeCandidateProfileService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class CandidateServiceImpl extends BaseService<BaseDto> implements CandidateService {

	@Autowired
	CandidateRepository repository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	TraineeCandidateProfileRepository profileRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	TraineeMapper traineeMapper;

	@Autowired
	TraineeCandidateProfileService candidateProfileService;

	@Autowired
	CandidateMapper mapper;
	
	public int PAGE_SIZE = 10;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			CandidateProfileDto candidateProfileDto = (CandidateProfileDto) candidateProfileService
					.create(((CandidateDto) obj).getTraineeCandidateProfile()).getObj();
			((CandidateDto) obj).setTraineeCandidateProfile(candidateProfileDto);
			Candidate candidate = mapper.mapToEntity((CandidateDto) obj, Candidate.class);
			candidate.setStatus("New");
			if (candidateProfileDto != null) {
				repository.save(candidate);
				return ResponseDto.<BaseDto>builder().message("Create candidate success!")
						.obj(mapper.mapToDto(candidate, CandidateDto.class)).createdTime(LocalDateTime.now())
						.statusCode(ResponseCode.RESPONSE_CREATED).build();
			}
			return ResponseDto.<BaseDto>builder().message("Create candidate failed!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create candidate failed!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Candidate candidate = repository.findById(((CandidateDto) obj).getId()).get();
			CandidateProfileDto candidateProfile = ((CandidateDto) obj).getTraineeCandidateProfile();
			if (candidate != null) {
				ResponseDto<BaseDto> responseProfile = candidateProfileService.update(candidateProfile);
				System.out.println(responseProfile);
				if (responseProfile.getObj() != null) {
					repository.save(mapper.mapToEntity((CandidateDto) obj, Candidate.class));
				} else {
					return ResponseDto.<BaseDto>builder().message("Update candidate failed!")
							.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR)
							.build();
				}
				return ResponseDto.<BaseDto>builder().message("Update candidate success!")
						.obj(mapper.mapToDto(candidate, CandidateDto.class)).createdTime(LocalDateTime.now())
						.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			} else {
				return ResponseDto.<BaseDto>builder().message("Update candidate failed!")
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update candidate failed!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub

		try {
			Candidate candidate = repository.findById(((CandidateDto) obj).getId()).get();
			if (candidate != null) {
				repository.delete(candidate);

				return ResponseDto.<BaseDto>builder().message("Delete candidate success!")
						.obj(mapper.mapToDto(candidate, CandidateDto.class)).createdTime(LocalDateTime.now())
						.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			} else {
				return ResponseDto.<BaseDto>builder().message("Delete candidate failed!")
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			}

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete candidate failed!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();

		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub

		try {
			Candidate candidate = repository.findById(((CandidateDto) obj).getId()).get();

			return ResponseDto.<BaseDto>builder().message("Get candidate success!")
					.obj(mapper.mapToDto(candidate, CandidateDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get candidate failed!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> tranferCadidate(TransferCandidateDto transferCandidateDto) {
		
		try {
			Trainee trainee = new Trainee();
			trainee.setTraineeId(transferCandidateDto.getCandidateId());
			trainee.setTraineeCandidateProfile(profileRepository.findById(transferCandidateDto.getProfileId()).get());
			trainee.setRemarks(transferCandidateDto.getRemarks());
			trainee.setStatus(statusRepository.findById(1).orElse(null));
			TraineeDto traineeDto = traineeMapper.mapToDto(traineeRepository.save(trainee), TraineeDto.class);
			return ResponseDto.<BaseDto>builder().message("Transfer candidate completed !!!")
					.obj(traineeDto)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Tranfer candidate failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub

		try {
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), CandidateDto.class);

			return ResponseDto.<BaseDto>builder().message("Get all candidate success! ").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all candidate failed! ").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	@Override
	public ResponseDto<BaseDto> getAllPaging(Integer currentPage) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(currentPage - 1, PAGE_SIZE);
			Page<Candidate> page = repository.findAll(pageable);
			List<?> dtos = mapper.mapToDtoList(page.getContent(), CandidateDto.class);
			return ResponseDto.<BaseDto>builder().message("Get paging candidate completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get paging candidate failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
