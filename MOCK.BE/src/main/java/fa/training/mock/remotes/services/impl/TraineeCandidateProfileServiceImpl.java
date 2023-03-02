package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.dto.req.trainee_candidate_profile.CandidateProfileDto;
import fa.training.mock.models.mapper.CandidateProfileMapper;
import fa.training.mock.remotes.entities.TraineeCandidateProfile;
import fa.training.mock.remotes.repositories.TraineeCandidateProfileRepository;
import fa.training.mock.remotes.services.TraineeCandidateProfileService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class TraineeCandidateProfileServiceImpl extends BaseService<BaseDto> implements TraineeCandidateProfileService {

	@Autowired
	TraineeCandidateProfileRepository repository;

	@Autowired
	CandidateProfileMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			TraineeCandidateProfile candidateProfile = mapper.mapToEntity((CandidateProfileDto) obj,
					TraineeCandidateProfile.class);
//			System.out.println(repository.findByEmail(candidateProfile.getEmail()));
			if (repository.findByEmail(candidateProfile.getEmail()) == null
					|| repository.findByPhone(candidateProfile.getPhone()) == null) {
				TraineeCandidateProfile candidateProfilesaved = repository.save(candidateProfile);
				return ResponseDto.<BaseDto>builder().message("Create candidate success!")
						.obj(mapper.mapToDto(candidateProfilesaved, CandidateProfileDto.class))
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_CREATED).build();
			} else {
				return ResponseDto.<BaseDto>builder().message("Create candidate failed!")
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			}
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
			TraineeCandidateProfile candidateProfile = mapper.mapToEntity((CandidateProfileDto) obj,
					TraineeCandidateProfile.class);
			System.out.println(candidateProfile.getAccount());
			System.out.println(repository.findById(candidateProfile.getProfileId()).get().getAccount());
			if (repository.findById(candidateProfile.getProfileId()).get().getAccount() != null) {
				repository.save(mapper.mapToEntity((CandidateProfileDto) obj, TraineeCandidateProfile.class));
				return ResponseDto.<BaseDto>builder().message("Update candidate profile success!")
						.obj(mapper.mapToDto(candidateProfile, CandidateProfileDto.class))
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			} else {
				return ResponseDto.<BaseDto>builder().message("Update candidate profile failed!")
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update candidate profile failed!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			TraineeCandidateProfile candidateProfile = repository
					.findById(((TraineeCandidateProfile) obj).getProfileId()).get();
			if (candidateProfile != null) {
				repository.delete(candidateProfile);

				return ResponseDto.<BaseDto>builder().message("Delete candidate profile success!")
						.obj(mapper.mapToDto(candidateProfile, CandidateProfileDto.class))
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			} else {
				return ResponseDto.<BaseDto>builder().message("Delete candidate profile failed!")
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			}

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete candidate profile failed!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();

		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			TraineeCandidateProfile candidateProfile = repository
					.findById(((TraineeCandidateProfile) obj).getProfileId()).get();
			return ResponseDto.<BaseDto>builder().message("Get candidate profile success!")
					.obj(mapper.mapToDto(candidateProfile, CandidateProfileDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get candidate profile failed!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), CandidateProfileDto.class);

			return ResponseDto.<BaseDto>builder().message("Get all candidate profile success!").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all candidate failed!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
