package fa.training.mock.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
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

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.candidateResult)
public class CandidateResultController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	EntryTestService entryTestService;

	@Autowired
	InterviewService interviewService;

	@Autowired
	CandidateResultService candidateResultService;

	@PostMapping(ApiPath.insertDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody CandidateResultsDto request,
			@PathVariable("id") Integer id) {
		try {
			System.out.println(request);
			List<EntryTestDto> entryTests = (List<EntryTestDto>) entryTestService
					.createList(request.getEntryTests(), id).getObjList();
			List<InterviewDto> interviews = (List<InterviewDto>) interviewService
					.createList(request.getInterviews(), id).getObjList();
			CandidateResultsDto candidateResult = new CandidateResultsDto();
			candidateResult.setEntryTests(entryTests);
			candidateResult.setInterviews(interviews);

			return new ResponseEntity<ResponseDto<BaseDto>>(
					ResponseDto.<BaseDto>builder().message("Create location completed !!!").obj(candidateResult)
							.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_CREATED).build(),
					HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getAllDomain)
	public ResponseEntity<CandidateResultsDto> getAll() {
		try {
			CandidateResultsDto candidateResult = new CandidateResultsDto();
			candidateResult.setEntryTests((List<EntryTestDto>) entryTestService.getAll().getObjList());
			candidateResult.setInterviews((List<InterviewDto>) interviewService.getAll().getObjList());
			return new ResponseEntity<CandidateResultsDto>(candidateResult, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getOneDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("id") Integer id) {
		try {
			CandidateDto candidate = new CandidateDto();
			candidate.setId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(candidateResultService.getOneById(candidate),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(ApiPath.updateDomain + "/{id}")
	public ResponseEntity<CandidateResultsDto> update(@RequestBody CandidateResultsDto request,
			@PathVariable("id") Integer id) {
		try {
			System.out.println("REQUEST++++>" + request.getEntryTests());
			System.out.println("REQUEST++++>" + request.getInterviews());
			request.getEntryTests().stream().forEach(entryTest -> entryTestService.update(entryTest, id));
			request.getInterviews().stream().forEach(interview -> interviewService.update(interview, id));
			return new ResponseEntity<CandidateResultsDto>(request, HttpStatus.UPGRADE_REQUIRED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
