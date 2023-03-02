package fa.training.mock.remotes.services;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.req.candidate.TransferCandidateDto;

public interface CandidateService {

	ResponseDto<BaseDto> create(BaseDto obj);

	ResponseDto<BaseDto> update(BaseDto obj);

	ResponseDto<BaseDto> delete(BaseDto obj);
	
	
	ResponseDto<BaseDto> getOne(BaseDto obj);

	ResponseDto<BaseDto> getAll();

	ResponseDto<BaseDto> tranferCadidate(TransferCandidateDto transferCandidateDto);

	ResponseDto<BaseDto> getAllPaging(Integer currentPage);

}
