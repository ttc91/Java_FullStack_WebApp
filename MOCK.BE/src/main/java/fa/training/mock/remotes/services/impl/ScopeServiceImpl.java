package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.ClassAdminDto;
import fa.training.mock.models.dto.ScopeDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.ScopeMapper;
import fa.training.mock.remotes.entities.Scope;
import fa.training.mock.remotes.repositories.ScopeRepository;
import fa.training.mock.remotes.services.ScopeService;

@Service
public class ScopeServiceImpl implements ScopeService {

	@Autowired
	ScopeRepository repository;

	@Autowired
	ScopeMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub

		try {
			Scope scope = mapper.mapToEntity((ScopeDto) obj, Scope.class);
			repository.save(scope);

			return ResponseDto.<BaseDto>builder().message("Create scope completed !!!")
					.obj(mapper.mapToDto(scope, ScopeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create scope failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Scope scope = repository
					.findById(((ClassAdminDto)obj).getId()).get();
			scope = mapper.mapToEntity((ScopeDto) obj, Scope.class);
			repository.save(scope);

			return ResponseDto.<BaseDto>builder().message("Update scope completed !!!")
					.obj(mapper.mapToDto(scope, ScopeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update scope failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Scope scope = repository
					.findById(((ClassAdminDto)obj).getId()).get();
			repository.delete(scope);

			return ResponseDto.<BaseDto>builder().message("Delete scope completed !!!")
					.obj(mapper.mapToDto(scope, ScopeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete scope failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Scope scope = repository
					.findById(((ClassAdminDto)obj).getId()).get();

			return ResponseDto.<BaseDto>builder().message("Get scope completed !!!")
					.obj(mapper.mapToDto(scope, ScopeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get scope failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {

			List<?> dtos = mapper.mapToDtoList(repository.findAll(), ScopeDto.class);

			return ResponseDto.<BaseDto>builder().message("Get all scopes completed !!!").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all scopes failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
