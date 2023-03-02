package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.config.enums.Role;
import fa.training.mock.models.dto.ClassAdminDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.ClassAdminMapper;
import fa.training.mock.remotes.entities.ClassAdmin;
import fa.training.mock.remotes.entities.User;
import fa.training.mock.remotes.repositories.ClassAdminRepository;
import fa.training.mock.remotes.repositories.UserRepository;
import fa.training.mock.remotes.services.ClassAdminService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class ClassAdminServiceImpl extends BaseService<BaseDto> implements ClassAdminService{


	@Autowired
	ClassAdminRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClassAdminMapper classAdminMapper;
	
	@Autowired
	ClassAdminMapper mapper;
	
	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		try {
			ClassAdmin classAdmin = mapper.mapToEntity((ClassAdminDto)obj, ClassAdmin.class);
			Optional<User> userOpt = userRepository.findById(classAdmin.getUsername());
            if(userOpt.isPresent()) {
                return ResponseDto.<BaseDto>builder().message("Trainer is exist in system !!!")
                        .statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
            }
            
            ((ClassAdminDto) obj).setPassword(BCrypt.hashpw(((ClassAdminDto) obj).getPassword(), BCrypt.gensalt(12)));
            userRepository.save(new User(((ClassAdminDto) obj).getUsername(), ((ClassAdminDto) obj).getPassword(), Role.ADMIN));
			repository.save(classAdmin);
			ClassAdminDto dto = classAdminMapper.mapToDto(classAdmin, ClassAdminDto.class);
			dto.setPassword(((ClassAdminDto)obj).getPassword());
			return ResponseDto.<BaseDto>builder().message("Create admin infomation completed !!!")
					.obj(dto)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create admin profile failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
	try {	
			ClassAdmin classAdmin = repository.findById(((ClassAdminDto)obj).getId()).get();
			classAdmin = mapper.mapToEntity((ClassAdminDto) obj, ClassAdmin.class);
			repository.save(classAdmin);
			return ResponseDto.<BaseDto>builder().message("Update admin profile completed !!!")
					.obj(mapper.mapToDto(classAdmin, ClassAdminDto.class)).createdTime(LocalDateTime.now())
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update admin profile failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		
		try {
			
			ClassAdmin classAdmin = repository.findById(((ClassAdminDto)obj).getId()).get();
			repository.delete(classAdmin);
			return ResponseDto.<BaseDto>builder().message("Delete admin profile completed !!!")
					.obj(mapper.mapToDto(classAdmin, ClassAdminDto.class)).createdTime(LocalDateTime.now())
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete admin profile failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {			
			ClassAdmin classAdmin = repository.findById(((ClassAdminDto)obj).getId()).get();
			return ResponseDto.<BaseDto>builder().message("Get admin profile completed !!!")
					.obj(mapper.mapToDto(classAdmin, ClassAdminDto.class)).createdTime(LocalDateTime.now())
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return ResponseDto.<BaseDto>builder().message("Get admin profile failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), ClassAdminDto.class);
			return ResponseDto.<BaseDto>builder().message("Get all admin profile completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all admin profile failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
