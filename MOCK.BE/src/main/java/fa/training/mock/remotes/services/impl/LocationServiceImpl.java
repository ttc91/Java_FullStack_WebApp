package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.LocationDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.LocationMapper;
import fa.training.mock.remotes.entities.Location;
import fa.training.mock.remotes.repositories.LocationRepository;
import fa.training.mock.remotes.services.LocationService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class LocationServiceImpl extends BaseService<BaseDto> implements LocationService{
	
	@Autowired
	LocationRepository repository;
	
	@Autowired
	LocationMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			Location location = mapper.mapToEntity((LocationDto)obj, Location.class);
			repository.save(location);
			
			return ResponseDto.<BaseDto>builder().message("Create location completed !!!")
					.obj(mapper.mapToDto(location, LocationDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create location failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			//TODO Watching here!
			Location location = repository.findById(mapper.mapToEntity((LocationDto)obj, Location.class).getLocationId()).get();
			location = mapper.mapToEntity((LocationDto)obj, Location.class);
			repository.save(location);
			
			return ResponseDto.<BaseDto>builder().message("Update location completed !!!")
					.obj(mapper.mapToDto(location, LocationDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update location failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			Location location = repository.findById(mapper.mapToEntity((LocationDto)obj, Location.class).getLocationId()).get();
			repository.delete(location);
			
			return ResponseDto.<BaseDto>builder().message("Delete location completed !!!")
					.obj(mapper.mapToDto(location, LocationDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete location failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Location location = repository.findById(mapper.mapToEntity((LocationDto)obj, Location.class).getLocationId()).get();
			
			return ResponseDto.<BaseDto>builder().message("Get location completed !!!")
					.obj(mapper.mapToDto(location, LocationDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get location failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			List<?> dtos = mapper.mapToDtoList(repository.findAll(),  LocationDto.class);
			
			return ResponseDto.<BaseDto>builder().message("Get all locations completed !!!")
					.objList(dtos)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all locations failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
