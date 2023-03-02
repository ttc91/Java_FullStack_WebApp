package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.CandidateDto;
import fa.training.mock.models.dto.CandidateResultsDto;
import fa.training.mock.models.dto.EntryTestDto;
import fa.training.mock.models.dto.InterviewDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.remotes.services.CandidateResultService;
import fa.training.mock.remotes.services.EntryTestService;
import fa.training.mock.remotes.services.InterviewService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class CandidateResultServiceImpl extends BaseService<BaseDto> implements CandidateResultService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	EntryTestService entryTestService;

	@Autowired
	InterviewService interviewService;

	@Override
	public ResponseDto<BaseDto> getOneById(BaseDto obj) {
		// TODO Auto-generated method stub

		try {
			CandidateResultsDto candidateResultsDto = new CandidateResultsDto();
			List<EntryTestDto> a =  (List<EntryTestDto>) entryTestService.findByCandidateId(((CandidateDto) obj).getId()).getObjList();
			System.out.println("Get EntryTest By ID Candidate:" +a);
			
			
			List<InterviewDto> b = (List<InterviewDto>) interviewService.findByCandidateId(((CandidateDto) obj).getId()).getObjList();
			System.out.println("Get Interview By ID Candidate:" +b);
			
			candidateResultsDto.setEntryTests(a);
			candidateResultsDto.setInterviews(b);

			return ResponseDto.<BaseDto>builder().message("Get candidate result success!").obj(candidateResultsDto)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get candidate result failed!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();

		}
	}

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
