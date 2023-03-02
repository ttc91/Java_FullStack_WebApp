package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.BudgetDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.BudgetMapper;
import fa.training.mock.remotes.entities.Budget;
import fa.training.mock.remotes.repositories.BudgetRepository;
import fa.training.mock.remotes.services.BudgetService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class BudgetServiceImpl extends BaseService<BaseDto> implements BudgetService{

	@Autowired
	BudgetRepository repository;
	
	@Autowired
	BudgetMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			Budget budget = mapper.mapToEntity((BudgetDto)obj, Budget.class);
			budget.setId(null);
			repository.save(budget);
			
			return ResponseDto.<BaseDto>builder().message("Create budget completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(budget, BudgetDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create budget failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
		
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			Budget budget = repository.findBudgetByBudgetCode(((BudgetDto) obj).getBudgetCode());
			budget.setTotal(((BudgetDto) obj).getTotal());
			budget.setOverBudget(((BudgetDto) obj).getOverBudget());
			repository.save(budget);
			
			return ResponseDto.<BaseDto>builder().message("Update budget completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.obj(mapper.mapToDto(budget, BudgetDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update budget failed !!!").statusCode(ResponseCode.RESPONSE_BAD_REQUEST) 
					.createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Budget budget = repository.findBudgetByBudgetCode(((BudgetDto) obj).getBudgetCode());
			repository.delete(budget);
			
			return ResponseDto.<BaseDto>builder().message("Delete budget completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Delete budget failed !!!").statusCode(ResponseCode.RESPONSE_BAD_REQUEST) 
					.createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			Budget budget = repository.findBudgetByBudgetCode(((BudgetDto) obj).getBudgetCode());
			
			return ResponseDto.<BaseDto>builder().message("Get budget completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.obj(mapper.mapToDto(budget, BudgetDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get budget failed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			return ResponseDto.<BaseDto>builder().message("Get all budgets completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.objList(mapper.mapToDtoList(repository.findAll(), BudgetDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get all budgets failed !!!").statusCode(ResponseCode.RESPONSE_BAD_REQUEST) 
					.createdTime(LocalDateTime.now()).build();
		}
	}
	

}
