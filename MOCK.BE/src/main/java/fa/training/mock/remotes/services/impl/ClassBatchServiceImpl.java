package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.config.enums.StatusClassEnum;
import fa.training.mock.models.dto.ClassBatchDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.ClassBatchMapper;
import fa.training.mock.models.mapper.LocationMapper;
import fa.training.mock.remotes.entities.BudgetItem;
import fa.training.mock.remotes.entities.ClassBatch;
import fa.training.mock.remotes.repositories.AuditRepository;
import fa.training.mock.remotes.repositories.BudgetItemRepository;
import fa.training.mock.remotes.repositories.ClassBatchRepository;
import fa.training.mock.remotes.services.ClassBatchService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class ClassBatchServiceImpl extends BaseService<BaseDto> implements ClassBatchService{
	
	@Autowired
	ClassBatchRepository repository;
	
	@Autowired
	BudgetItemRepository budgetItemRepository;
	
	@Autowired
	AuditRepository auditRepository;
	
	@Autowired
	ClassBatchMapper mapper;
	
	@Autowired
	LocationMapper locationMapper;
	
	public static int PAGE_SIZE = 4;
	
	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		try {
			ClassBatch classBatch = mapper.mapToEntity((ClassBatchDto)obj, ClassBatch.class);
			repository.save(classBatch);
			
			for(BudgetItem item : classBatch.getBudgetItems()) {
				item.setClassBatch(classBatch);
				budgetItemRepository.save(item);
			}
			
			return ResponseDto.<BaseDto>builder().message("Create class batch completed !!!")
					.obj(mapper.mapToDto(classBatch, ClassBatchDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create class batch failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		try {	
			ClassBatch classBatch = repository.findById(((ClassBatchDto)obj).getClassBatchId()).get();	
			classBatch = mapper.mapToEntity((ClassBatchDto)obj, ClassBatch.class);
			for(BudgetItem item : classBatch.getBudgetItems()) {
				item.setClassBatch(classBatch);
				budgetItemRepository.save(item);
			}
//			for(Audit a : classBatch.getAudit()) {
//				a.setClassBatch(classBatch);
//			}
			repository.save(classBatch);
			return ResponseDto.<BaseDto>builder().message("Update class batch completed !!!")
					.obj(mapper.mapToDto(classBatch, ClassBatchDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update class batch failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	@Override
	public ResponseDto<BaseDto> updateStatus(BaseDto obj) {
		try {	
			ClassBatch classBatch = repository.findById(((ClassBatchDto)obj).getClassBatchId()).get();
			classBatch = mapper.mapToEntity((ClassBatchDto)obj, ClassBatch.class);
			repository.setStatusForClassBatch(classBatch.getStatus(), classBatch.getClassBatchId());		
			return ResponseDto.<BaseDto>builder().message("Update status class batch completed !!!")
					.obj(mapper.mapToDto(classBatch, ClassBatchDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update status class batch failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			ClassBatch classBatch = repository
					.findById(((ClassBatchDto)obj).getClassBatchId()).get();
			repository.delete(classBatch);
			
			return ResponseDto.<BaseDto>builder().message("Delete class batch completed !!!")
					.obj(mapper.mapToDto(classBatch, ClassBatchDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete class batch failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {			
			ClassBatch classBatch = repository
					.findById(((ClassBatchDto)obj).getClassBatchId()).get();	
		
			return ResponseDto.<BaseDto>builder().message("Get class batch completed !!!")
					.obj(mapper.mapToDto(classBatch, ClassBatchDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return ResponseDto.<BaseDto>builder().message("Get class batch failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			List<?> dtos = mapper.mapToDtoList(repository.findAll(), ClassBatchDto.class);
			return ResponseDto.<BaseDto>builder().message("Get all class batch completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all class batch failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	@Override
	public ResponseDto<BaseDto> getAllPaging(Integer currentPage) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(currentPage - 1, PAGE_SIZE);
			Page<ClassBatch> page = repository.findAll(pageable);
			List<?> dtos = mapper.mapToDtoList(page.getContent(), ClassBatchDto.class);
			return ResponseDto.<BaseDto>builder().message("Get paging class batch completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get paging class batch failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	@Override
	public ResponseDto<BaseDto> getAllSearch(String className, String locationName, Date actualStartDate, Date actualEndDate, StatusClassEnum status) {
		// TODO Auto-generated method stub
		try {
			List<?> dtos = new ArrayList<>();
			if(!locationName.isEmpty())
			{
				dtos = mapper.mapToDtoList(repository.findByLocationLocationName(locationName),ClassBatchDto.class);
			}else {
				if(status != StatusClassEnum.NULL)
				{
					dtos = mapper.mapToDtoList(repository.findByStatus(status),ClassBatchDto.class);
				}else {
					if(actualStartDate != null || actualEndDate!= null)
					{
						dtos = mapper.mapToDtoList(repository.findByActualStartDateGreaterThanEqualAndActualEndDateLessThanEqual(actualStartDate, actualEndDate),ClassBatchDto.class);
					}else {
						if(!className.isEmpty())
						{
							dtos = mapper.mapToDtoList(repository.findByClassName(className),ClassBatchDto.class);
						} else {
							dtos = mapper.mapToDtoList(repository.findAll(), ClassBatchDto.class);
						}
					}
				}
			}
			return ResponseDto.<BaseDto>builder().message("Get filter class batch completed !!!")
					.objList(dtos).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get filter class batch failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
	
}
