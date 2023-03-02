package fa.training.mock.remotes.services;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;

public interface AuditService {

	ResponseDto<BaseDto> create(BaseDto obj);

	ResponseDto<BaseDto> update(BaseDto obj);

	ResponseDto<BaseDto> delete(BaseDto obj);

	ResponseDto<BaseDto> getOne(BaseDto obj);

	ResponseDto<BaseDto> getAll();
}
