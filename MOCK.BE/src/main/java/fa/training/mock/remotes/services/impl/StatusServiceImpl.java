package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.StatusDto;
import fa.training.mock.models.dto.UniversityDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.StatusMapper;
import fa.training.mock.models.mapper.UniversityMapper;
import fa.training.mock.remotes.entities.Status;
import fa.training.mock.remotes.entities.University;
import fa.training.mock.remotes.repositories.StatusRepository;
import fa.training.mock.remotes.services.StatusService;

@Service
public class StatusServiceImpl extends BaseDto implements StatusService{

	@Autowired
	private StatusRepository repository;

	@Autowired
	StatusMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			Status status = mapper.mapToEntity((StatusDto)obj, Status.class);
			repository.save(status);
			
			return ResponseDto.<BaseDto>builder().message("Create status completed !!!")
					.obj(mapper.mapToDto(status, StatusDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create status failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			Status status0 = mapper.mapToEntity((StatusDto)obj, Status.class);
			Status status = repository.findById(status0.getId()).get();
			status = status0;
			repository.save(status);
			
			return ResponseDto.<BaseDto>builder().message("Update status completed !!!")
					.obj(mapper.mapToDto(status, StatusDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update status failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			Status status = repository.findById(mapper.mapToEntity((StatusDto)obj, Status.class).getId()).get();
			repository.delete(status);
			
			return ResponseDto.<BaseDto>builder().message("Delete status completed !!!")
					.obj(mapper.mapToDto(status, StatusDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete status failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Status status = repository.findById(mapper.mapToEntity((StatusDto)obj, Status.class).getId()).get();
			
			return ResponseDto.<BaseDto>builder().message("Get status completed !!!")
					.obj(mapper.mapToDto(status, StatusDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get status failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			List<?> dtos = mapper.mapToDtoList(repository.findAll(),  StatusDto.class);
			
			return ResponseDto.<BaseDto>builder().message("Get all status completed !!!")
					.objList(dtos)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all status failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}


}
