package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.UserDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.config.UserMapper;
import fa.training.mock.remotes.entities.User;
import fa.training.mock.remotes.repositories.UserRepository;
import fa.training.mock.remotes.services.UserService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class UserServiceImpl extends BaseService<BaseDto> implements UserService{
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> login(BaseDto obj){
		
		try {

			User user = repository.findById(((UserDto)obj).getUsername()).get();
			Boolean valuate = BCrypt.checkpw(((UserDto)obj).getPassword(), user.getPassword());
			
			if(valuate) {
				return ResponseDto.<BaseDto>builder().message("Get user completed !!!")
						.obj(mapper.mapToDto(user, UserDto.class)).createdTime(LocalDateTime.now())
						.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			}
			return ResponseDto.<BaseDto>builder().message("Get user failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get user failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	@Override
	public ResponseDto<BaseDto> findUserByUsername(String username){
		
		try {
			User user = repository.findById(username).get();
			
			if(user != null) {
				return ResponseDto.<BaseDto>builder().message("Get user completed !!!")
						.obj(mapper.mapToDto(user, UserDto.class)).createdTime(LocalDateTime.now())
						.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			}
			return ResponseDto.<BaseDto>builder().message("Get user failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get user failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
}
