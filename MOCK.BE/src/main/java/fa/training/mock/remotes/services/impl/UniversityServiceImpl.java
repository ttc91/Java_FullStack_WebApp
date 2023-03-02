package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.UniversityDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.UniversityMapper;
import fa.training.mock.remotes.entities.University;
import fa.training.mock.remotes.repositories.UniversityRepository;
import fa.training.mock.remotes.services.UniversityService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class UniversityServiceImpl extends BaseService<BaseDto> implements UniversityService{
	
	@Autowired
	UniversityRepository repository;
	
	@Autowired
	UniversityMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			University university = mapper.mapToEntity((UniversityDto)obj, University.class);
			repository.save(university);
			
			return ResponseDto.<BaseDto>builder().message("Create university completed !!!")
					.obj(mapper.mapToDto(university, UniversityDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create university failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			University university0 = mapper.mapToEntity((UniversityDto)obj, University.class);
			University university = repository.findById(university0.getUniversityId()).get();
			university = university0;
			repository.save(university);
			
			return ResponseDto.<BaseDto>builder().message("Update university completed !!!")
					.obj(mapper.mapToDto(university, UniversityDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update university failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			University university = repository.findById(mapper.mapToEntity((UniversityDto)obj, University.class).getUniversityId()).get();
			repository.delete(university);
			
			return ResponseDto.<BaseDto>builder().message("Delete university completed !!!")
					.obj(mapper.mapToDto(university, UniversityDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete university failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			University university = repository.findById(mapper.mapToEntity((UniversityDto)obj, University.class).getUniversityId()).get();
			
			return ResponseDto.<BaseDto>builder().message("Get university completed !!!")
					.obj(mapper.mapToDto(university, UniversityDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get university failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			List<?> dtos = mapper.mapToDtoList(repository.findAll(),  UniversityDto.class);
			
			return ResponseDto.<BaseDto>builder().message("Get all university completed !!!")
					.objList(dtos)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all university failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}


}
