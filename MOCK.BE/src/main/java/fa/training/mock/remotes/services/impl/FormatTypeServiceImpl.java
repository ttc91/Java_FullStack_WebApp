package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.FormatTypeDto;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.FormatTypeMapper;
import fa.training.mock.remotes.entities.FormatType;
import fa.training.mock.remotes.repositories.FormatTypeRepository;
import fa.training.mock.remotes.services.FormatTypeService;

@Service
public class FormatTypeServiceImpl implements FormatTypeService {

	@Autowired
	FormatTypeRepository repository;

	@Autowired
	FormatTypeMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		try {
			FormatType formatType = mapper.mapToEntity((FormatTypeDto) obj, FormatType.class);
			repository.save(formatType);
			return ResponseDto.<BaseDto>builder().message("Create format type completed !!!")
					.obj(mapper.mapToDto(formatType, FormatTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create format type failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			FormatType formatType = repository
					.findById(((FormatTypeDto)obj).getId()).get();
			formatType = mapper.mapToEntity((FormatTypeDto) obj, FormatType.class);
			repository.save(formatType);
			return ResponseDto.<BaseDto>builder().message("Update format type completed !!!")
					.obj(mapper.mapToDto(formatType, FormatTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update format type failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			FormatType formatType = repository
					.findById(((FormatTypeDto)obj).getId()).get();
			repository.delete(formatType);

			return ResponseDto.<BaseDto>builder().message("Delete format type completed !!!")
					.obj(mapper.mapToDto(formatType, FormatTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete format type failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			FormatType formatType = repository
					.findById(((FormatTypeDto)obj).getId()).get();

			return ResponseDto.<BaseDto>builder().message("Get format type completed !!!")
					.obj(mapper.mapToDto(formatType, FormatTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return ResponseDto.<BaseDto>builder().message("Get format type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		try {
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), FormatTypeDto.class);
			return ResponseDto.<BaseDto>builder().message("Get all format type completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all format type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
