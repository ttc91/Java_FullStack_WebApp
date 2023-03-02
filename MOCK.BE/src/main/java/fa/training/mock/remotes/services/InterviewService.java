package fa.training.mock.remotes.services;

import java.util.Collection;
import java.util.List;

import fa.training.mock.models.dto.InterviewDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;

public interface InterviewService {

	ResponseDto<BaseDto> create(BaseDto obj);

	ResponseDto<BaseDto> update(BaseDto obj, Integer id);

	ResponseDto<BaseDto> delete(BaseDto obj);

	ResponseDto<BaseDto> getOne(BaseDto obj);

	ResponseDto<BaseDto> getAll();

	ResponseDto<BaseDto> createList(Collection<?> objs, Integer id);
	
	ResponseDto<BaseDto> findByCandidateId (Integer candidateId);

}
