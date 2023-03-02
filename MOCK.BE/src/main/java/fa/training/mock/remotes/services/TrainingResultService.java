package fa.training.mock.remotes.services;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.res.trainingResult.TrainingResultDto;

public interface TrainingResultService {

	ResponseDto<BaseDto> update(TrainingResultDto dto);

	ResponseDto<BaseDto> getOne(int classId, int traineeId);
	
}
