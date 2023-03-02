package fa.training.mock.remotes.services;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;

public interface SupplierPartnerService {

	ResponseDto<BaseDto> getAll();

	ResponseDto<BaseDto> getOne(BaseDto obj);

	ResponseDto<BaseDto> delete(BaseDto obj);

	ResponseDto<BaseDto> update(BaseDto obj);

	ResponseDto<BaseDto> create(BaseDto obj);

}
