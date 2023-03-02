package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.SubSubjectTypeDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.SubSubjectTypeMapper;
import fa.training.mock.remotes.entities.SubSubjectType;
import fa.training.mock.remotes.repositories.SubSubjectTypeRepository;
import fa.training.mock.remotes.services.SubSubjectTypeService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class SubSubjectTypeServiceImpl extends BaseService<BaseDto> implements SubSubjectTypeService{

	@Autowired
	SubSubjectTypeRepository repository;
	
	@Autowired
	SubSubjectTypeMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		try {
			SubSubjectType subSubjectType = mapper.mapToEntity((SubSubjectTypeDto)obj, SubSubjectType.class);
			repository.save(subSubjectType);

			return ResponseDto.<BaseDto>builder().message("Create sub subject type completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(subSubjectType, SubSubjectTypeDto.class))
					.createdTime(LocalDateTime.now()).build();

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create sub subject type failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		try {

			SubSubjectType subSubjectType = repository.findById(mapper.mapToEntity((SubSubjectTypeDto)obj, SubSubjectType.class).getSubSubjectTypeId()).get();
			subSubjectType = mapper.mapToEntity((SubSubjectTypeDto)obj, SubSubjectType.class);
			repository.save(subSubjectType);

			return ResponseDto.<BaseDto>builder().message("Update sub subject type completed !!!")
					.obj(mapper.mapToDto(subSubjectType, SubSubjectTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update sub subject type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		try {

			SubSubjectType subSubjectType = repository.findById(mapper.mapToEntity((SubSubjectTypeDto)obj, SubSubjectType.class).getSubSubjectTypeId()).get();
			subSubjectType = mapper.mapToEntity((SubSubjectTypeDto)obj, SubSubjectType.class);
			repository.delete(subSubjectType);

			return ResponseDto.<BaseDto>builder().message("Delete sub subject type completed !!!")
					.obj(mapper.mapToDto(subSubjectType, SubSubjectTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete sub subject type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		try {

			SubSubjectType subSubjectType = repository.findById(mapper.mapToEntity((SubSubjectTypeDto)obj, SubSubjectType.class).getSubSubjectTypeId()).get();
			return ResponseDto.<BaseDto>builder().message("Get sub subject type completed !!!")
					.obj(mapper.mapToDto(subSubjectType, SubSubjectTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get subject type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		try {
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), SubSubjectTypeDto.class);
			return ResponseDto.<BaseDto>builder().message("Get all sub subject type completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all sub subject type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
}
