package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.AuditDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.AuditMapper;
import fa.training.mock.remotes.entities.Audit;
import fa.training.mock.remotes.entities.ClassBatch;
import fa.training.mock.remotes.repositories.AuditRepository;
import fa.training.mock.remotes.repositories.ClassBatchRepository;
import fa.training.mock.remotes.services.AuditService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class AuditServiceImpl extends BaseService<BaseDto> implements AuditService {

	@Autowired
	AuditRepository repository;

	@Autowired
	ClassBatchRepository classBatchRepository;
	
	@Autowired
	AuditMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			Audit audit = mapper.mapToEntity((AuditDto) obj, Audit.class);
			repository.save(audit);
			Optional<ClassBatch> classBatch = classBatchRepository.findById(audit.getClassBatch().getClassBatchId());
			audit.setClassBatch(classBatch.get());
			return ResponseDto.<BaseDto>builder().message("Create audit completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(audit, AuditDto.class))
					.createdTime(LocalDateTime.now()).build();

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create audit failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {	
			Audit audit = repository.findById(((AuditDto)obj).getAuditId()).get();
			audit = mapper.mapToEntity((AuditDto)obj, Audit.class);
			repository.save(audit);		
			Optional<ClassBatch> classBatch = classBatchRepository.findById(audit.getClassBatch().getClassBatchId());
			audit.setClassBatch(classBatch.get());
			return ResponseDto.<BaseDto>builder().message("Update audit completed !!!")
					.obj(mapper.mapToDto(audit, AuditDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update audit failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Audit audit = repository.findById(((AuditDto)obj).getAuditId()).get();
			repository.delete(audit);

			return ResponseDto.<BaseDto>builder().message("Delete audit completed !!!")
					.obj(mapper.mapToDto(audit, AuditDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete audit failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Audit audit = repository.findById(((AuditDto)obj).getAuditId()).get();
			return ResponseDto.<BaseDto>builder().message("Get audit completed !!!")
					.obj(mapper.mapToDto(audit, AuditDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get audit failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), AuditDto.class);
			return ResponseDto.<BaseDto>builder().message("Get all audits completed !!!").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all audits failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
