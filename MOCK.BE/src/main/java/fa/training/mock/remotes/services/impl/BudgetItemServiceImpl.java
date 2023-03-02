package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.BudgetItemDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.BudgetItemMapper;
import fa.training.mock.remotes.entities.Budget;
import fa.training.mock.remotes.entities.BudgetItem;
import fa.training.mock.remotes.entities.ClassBatch;
import fa.training.mock.remotes.repositories.BudgetItemRepository;
import fa.training.mock.remotes.repositories.BudgetRepository;
import fa.training.mock.remotes.repositories.ClassBatchRepository;
import fa.training.mock.remotes.services.BudgetItemService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class BudgetItemServiceImpl extends BaseService<BaseDto> implements BudgetItemService{
	
	@Autowired
	BudgetItemRepository budgetItemRepository;
	
	@Autowired
	BudgetRepository budgetRepository;
	
	@Autowired
	ClassBatchRepository classBatchRepository;
	
	@Autowired
	BudgetItemMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			BudgetItem budgetItem = mapper.mapToEntity((BudgetItemDto) obj, BudgetItem.class);
			Budget budget = budgetRepository.findBudgetByBudgetCode(budgetItem.getBudget().getBudgetCode());
			ClassBatch classBatch = classBatchRepository.findByClassCode(budgetItem.getClassBatch().getClassCode());
			
			budgetItem.setBudget(budget);
			budgetItem.setClassBatch(classBatch);
			
			budgetItemRepository.save(budgetItem);
			
			return ResponseDto.<BaseDto>builder()
					.message("Create budget item complete !")
					.createdTime(LocalDateTime.now())
					.obj(mapper.mapToDto(budgetItem, BudgetItemDto.class))
					.statusCode(ResponseCode.RESPONSE_CREATED)
					.build();
			
			
		}catch(Exception e) {
			return ResponseDto.<BaseDto>builder()
					.message("Create budget item fail !")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_BAD_REQUEST)
					.build();
		}
	
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			BudgetItem budgetItem = mapper.mapToEntity((BudgetItemDto) obj, BudgetItem.class);
			Budget budget = budgetRepository.findBudgetByBudgetCode(budgetItem.getBudget().getBudgetCode());
			ClassBatch classBatch = classBatchRepository.findByClassCode(budgetItem.getClassBatch().getClassCode());
			
			budgetItem.setBudget(budget);
			budgetItem.setClassBatch(classBatch);
			
			budgetItemRepository.save(budgetItem);
			
			return ResponseDto.<BaseDto>builder()
					.message("Update budget item complete !")
					.createdTime(LocalDateTime.now())
					.obj(mapper.mapToDto(budgetItem, BudgetItemDto.class))
					.statusCode(ResponseCode.RESPONSE_CREATED)
					.build();
			
			
		}catch(Exception e) {
			return ResponseDto.<BaseDto>builder()
					.message("Update budget item fail !")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_BAD_REQUEST)
					.build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			BudgetItem budgetItem = mapper.mapToEntity((BudgetItemDto) obj, BudgetItem.class);
			
			budgetItemRepository.delete(budgetItemRepository.findById(budgetItem.getId()).get());
			
			return ResponseDto.<BaseDto>builder()
					.message("Delete budget item complete !")
					.createdTime(LocalDateTime.now())
					.obj(mapper.mapToDto(budgetItem, BudgetItemDto.class))
					.statusCode(ResponseCode.RESPONSE_CREATED)
					.build();
			
			
		}catch(Exception e) {
			return ResponseDto.<BaseDto>builder()
					.message("Delete budget item fail !")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_BAD_REQUEST)
					.build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseDto<BaseDto> getAllByClassBatch(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			ClassBatch classBatch = classBatchRepository.findByClassCode(((BudgetItemDto)obj).getClassBatch().getClassCode());
			List<BudgetItem>  list = budgetItemRepository.findByClassBatch(classBatch);
			
			return ResponseDto.<BaseDto>builder().message("Get all budget items completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.objList(mapper.mapToDtoList(list, BudgetItemDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all budget items failed !!!").statusCode(ResponseCode.RESPONSE_BAD_REQUEST) 
					.createdTime(LocalDateTime.now()).build();
		}

	}
	
	@Override
	public ResponseDto<BaseDto> createListDto(List<BudgetItemDto> objList) {
		// TODO Auto-generated method stub
		
		try {
			
			List<BudgetItem> budgetItems = objList.stream().map(obj -> mapper.mapToEntity(obj, BudgetItem.class)).collect(Collectors.toList());
			for (BudgetItem b : budgetItems) {
				b.setBudget(budgetRepository.findBudgetByBudgetCode(b.getBudget().getBudgetCode()));
				b.setClassBatch(classBatchRepository.findByClassCode(b.getClassBatch().getClassCode()));
				budgetItemRepository.save(b);
			}
			
			return ResponseDto.<BaseDto>builder().message("Create budget item list completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).objList(mapper.mapToDtoList(budgetItems, BudgetItemDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create budget item list failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
		
	}
	
}
