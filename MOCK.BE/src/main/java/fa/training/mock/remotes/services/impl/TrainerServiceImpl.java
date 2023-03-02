package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.config.enums.Role;
import fa.training.mock.models.dto.TrainerDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.TrainerMapper;
import fa.training.mock.remotes.entities.Trainer;
import fa.training.mock.remotes.entities.User;
import fa.training.mock.remotes.repositories.TrainerProfileRepository;
import fa.training.mock.remotes.repositories.TrainerRepository;
import fa.training.mock.remotes.repositories.UserRepository;
import fa.training.mock.remotes.services.TrainerService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class TrainerServiceImpl extends BaseService<BaseDto> implements TrainerService{
	
	@Autowired
	TrainerRepository trainerRepository;
	
	@Autowired
	TrainerProfileRepository profileRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TrainerMapper mapper;

	public static int PAGE_SIZE = 4;
	
	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			if(((TrainerDto)obj).getUsername() == null) {
				return ResponseDto.<BaseDto>builder().message("Please input username !!!")
						.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
			
			Trainer trainer = mapper.mapToEntity((TrainerDto)obj, Trainer.class);
			trainer.setTrainerId(null);
			
			Optional<User> userOpt = userRepository.findById(trainer.getUsername());
			if(userOpt.isPresent()) {
				return ResponseDto.<BaseDto>builder().message("Trainer is exist in system !!!")
						.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
			
			((TrainerDto) obj).setPassword(BCrypt.hashpw(((TrainerDto) obj).getPassword(), BCrypt.gensalt(12)));
			userRepository.save(new User(((TrainerDto) obj).getUsername(), ((TrainerDto) obj).getPassword(), Role.TRAINER));

			trainerRepository.save(trainer);
				
			((TrainerDto)obj).setTrainerId(trainer.getTrainerId());
			
			return ResponseDto.<BaseDto>builder().message("Create trainer completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(obj).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create trainer failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Trainer trainer = trainerRepository.findById(((TrainerDto)obj).getTrainerId()).get();
			
			if(trainer == null) {
				return ResponseDto.<BaseDto>builder().message("Trainer is not exist in system !!!")
						.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
			
			String username = trainer.getUsername();
			Integer profileId = trainer.getTrainerProfile().getTrainerProfileId();
			
			trainer = mapper.mapToEntity((TrainerDto)obj, Trainer.class);
			trainer.setUsername(username);
			trainer.getTrainerProfile().setTrainerProfileId(profileId);
			
			trainerRepository.save(trainer);
				
			return ResponseDto.<BaseDto>builder().message("Update trainer completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(trainer, TrainerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update trainer failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Trainer trainer = trainerRepository.findById(((TrainerDto)obj).getTrainerId()).get();
			
			if(trainer == null) {
				return ResponseDto.<BaseDto>builder().message("Trainer is not exist in system !!!")
						.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
			
			trainerRepository.delete(trainer);
				
			return ResponseDto.<BaseDto>builder().message("Delete trainer completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(trainer, TrainerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Delete trainer failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Trainer trainer = trainerRepository.findById(((TrainerDto)obj).getTrainerId()).get();
			
			if(trainer == null) {
				return ResponseDto.<BaseDto>builder().message("Trainer is not exist in system !!!")
						.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
			
			return ResponseDto.<BaseDto>builder().message("Get trainer completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(trainer, TrainerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get trainer failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			return ResponseDto.<BaseDto>builder().message("Get trainers completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).objList(mapper.mapToDtoList(trainerRepository.findAll(), TrainerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get trainers failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}
	
	@Override
	public ResponseDto<BaseDto> getAllPaging(Integer currentPage) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(currentPage - 1, PAGE_SIZE);
			Page<Trainer> page = trainerRepository.findAll(pageable);
			List<?> dtos = mapper.mapToDtoList(page.getContent(), TrainerDto.class);
			return ResponseDto.<BaseDto>builder().message("Get paging trainer completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get paging trainer failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
