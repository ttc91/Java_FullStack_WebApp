package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import fa.training.mock.models.dto.SubjectTypeDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.SubjectTypeMapper;
import fa.training.mock.remotes.entities.SubjectType;
import fa.training.mock.remotes.repositories.SubjectTypeRepository;
import fa.training.mock.remotes.services.SubjectTypeService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class SubjectTypeServiceImpl extends BaseService<BaseDto> implements SubjectTypeService {

	@Autowired
	SubjectTypeRepository repository;

	@Autowired
	SubjectTypeMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		try {
			SubjectType subjectType = mapper.mapToEntity((SubjectTypeDto)obj, SubjectType.class);
			repository.save(subjectType);

			return ResponseDto.<BaseDto>builder().message("Create subject type completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(subjectType, SubjectTypeDto.class))
					.createdTime(LocalDateTime.now()).build();

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create subject type failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		try {

			SubjectType subjectType = repository.findById(mapper.mapToEntity((SubjectTypeDto)obj, SubjectType.class).getSubjectTypeid()).get();
			subjectType = mapper.mapToEntity((SubjectTypeDto)obj, SubjectType.class);
			repository.save(subjectType);

			return ResponseDto.<BaseDto>builder().message("Update subject type completed !!!")
					.obj(mapper.mapToDto(subjectType, SubjectTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update subject type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		try {

			SubjectType subjectType = repository.findById(mapper.mapToEntity((SubjectTypeDto)obj, SubjectType.class).getSubjectTypeid()).get();
			subjectType = mapper.mapToEntity((SubjectTypeDto)obj, SubjectType.class);
			repository.delete(subjectType);

			return ResponseDto.<BaseDto>builder().message("Delete subject type completed !!!")
					.obj(mapper.mapToDto(subjectType, SubjectTypeDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete subject type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		try {

			SubjectType subjectType = repository.findById(mapper.mapToEntity((SubjectTypeDto)obj, SubjectType.class).getSubjectTypeid()).get();
			return ResponseDto.<BaseDto>builder().message("Get subject type completed !!!")
					.obj(mapper.mapToDto(subjectType, SubjectTypeDto.class)).createdTime(LocalDateTime.now())
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
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), SubjectTypeDto.class);
			return ResponseDto.<BaseDto>builder().message("Get all subject type completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all subject type failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
