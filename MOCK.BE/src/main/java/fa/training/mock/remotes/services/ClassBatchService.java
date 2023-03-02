package fa.training.mock.remotes.services;
import java.util.Date;

import fa.training.mock.config.enums.StatusClassEnum;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;

public interface ClassBatchService {

	ResponseDto<BaseDto> create(BaseDto obj);

	ResponseDto<BaseDto> update(BaseDto obj);

	ResponseDto<BaseDto> delete(BaseDto obj);

	ResponseDto<BaseDto> getOne(BaseDto obj);

	ResponseDto<BaseDto> getAll();

	ResponseDto<BaseDto> getAllPaging(Integer currentPage);

	ResponseDto<BaseDto> getAllSearch(String className, String locationName, Date actualStartDate,
			Date actualEndDate, StatusClassEnum status);

	ResponseDto<BaseDto> updateStatus(BaseDto obj);
}
