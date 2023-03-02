package fa.training.mock.remotes.services;

import java.util.Collection;
import java.util.List;

import fa.training.mock.models.dto.EntryTestDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.entities.EntryTest;

public interface EntryTestService {

	ResponseDto<BaseDto> create(BaseDto obj);

	ResponseDto<BaseDto> update(BaseDto obj, Integer id);

	ResponseDto<BaseDto> delete(BaseDto obj);

	ResponseDto<BaseDto> getOne(BaseDto obj);

	ResponseDto<BaseDto> getAll();
	
	ResponseDto<BaseDto> createList(Collection<?> objs, Integer id);
	
	ResponseDto<BaseDto> findByCandidateId (Integer candidateId);

}
