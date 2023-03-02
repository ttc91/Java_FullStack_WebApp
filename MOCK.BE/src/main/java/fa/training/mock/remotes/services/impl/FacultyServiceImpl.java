package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.FacultyDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.FacultyMapper;
import fa.training.mock.remotes.entities.Faculty;
import fa.training.mock.remotes.repositories.FacultyRepository;
import fa.training.mock.remotes.services.FacultyService;

@Service
public class FacultyServiceImpl extends BaseDto implements FacultyService{
	
	@Autowired FacultyRepository repository;

	@Autowired
	FacultyMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			Faculty faculty = mapper.mapToEntity((FacultyDto)obj, Faculty.class);
			repository.save(faculty);
			
			return ResponseDto.<BaseDto>builder().message("Create faculty completed !!!")
					.obj(mapper.mapToDto(faculty, FacultyDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create faculty failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			Faculty faculty0= mapper.mapToEntity((FacultyDto)obj, Faculty.class);
			Faculty faculty = repository.findById(faculty0.getMajorId()).get();
			faculty = faculty0;
			repository.save(faculty);
			
			return ResponseDto.<BaseDto>builder().message("Update faculty completed !!!")
					.obj(mapper.mapToDto(faculty, FacultyDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update faculty failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			Faculty faculty = repository.findById(mapper.mapToEntity((FacultyDto)obj, Faculty.class).getMajorId()).get();
			repository.delete(faculty);
			
			return ResponseDto.<BaseDto>builder().message("Delete faculty completed !!!")
					.obj(mapper.mapToDto(faculty, FacultyDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete faculty failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Faculty faculty = repository.findById(mapper.mapToEntity((FacultyDto)obj, Faculty.class).getMajorId()).get();
			
			return ResponseDto.<BaseDto>builder().message("Get faculty completed !!!")
					.obj(mapper.mapToDto(faculty, FacultyDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get faculty failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			List<?> dtos = mapper.mapToDtoList(repository.findAll(),  FacultyDto.class);
			
			return ResponseDto.<BaseDto>builder().message("Get all faculty completed !!!")
					.objList(dtos)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all faculty failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}



}
