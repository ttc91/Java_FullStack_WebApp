package fa.training.mock.remotes.services;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;

public interface UserService {

	ResponseDto<BaseDto> login(BaseDto obj);

	ResponseDto<BaseDto> findUserByUsername(String username);
}
