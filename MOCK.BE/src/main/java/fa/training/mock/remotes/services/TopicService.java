package fa.training.mock.remotes.services;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;

public interface TopicService {

	ResponseDto<BaseDto> findAllByClassId(Integer classId);

}
