package fa.training.mock.remotes.services;

import java.util.List;

import fa.training.mock.models.dto.BudgetItemDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;

public interface BudgetItemService {

	ResponseDto<BaseDto> create(BaseDto obj);

	ResponseDto<BaseDto> update(BaseDto obj);

	ResponseDto<BaseDto> delete(BaseDto obj);

	ResponseDto<BaseDto> getAllByClassBatch(BaseDto obj);

	ResponseDto<BaseDto> createListDto(List<BudgetItemDto> objList);

}
