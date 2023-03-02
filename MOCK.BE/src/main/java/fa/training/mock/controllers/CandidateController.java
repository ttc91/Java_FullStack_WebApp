package fa.training.mock.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.CandidateDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.req.candidate.TransferCandidateDto;
import fa.training.mock.remotes.services.CandidateService;
import fa.training.mock.remotes.services.TraineeCandidateProfileService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.CandidateDomain)
public class CandidateController {

	@Autowired
	CandidateService candidateService;

	@Autowired
	TraineeCandidateProfileService candidateProfileService;

	@Autowired
	TraineeCandidateProfileService traineeCandidateProfileService;

	@Autowired
	ModelMapper mapper;

	

	@PostMapping(ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody CandidateDto request) {
		try {
			ResponseDto<BaseDto> respone = candidateService.create(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(respone, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(ApiPath.updateDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> update(@RequestBody CandidateDto request,@PathVariable("id") Integer id) {
		try {
			ResponseDto<BaseDto> respone = candidateService.update(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(respone, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getAllDomain)
	public ResponseEntity<ResponseDto<BaseDto>> getAll() {
		try {
			return new ResponseEntity<ResponseDto<BaseDto>>(candidateService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getOneDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("id") Integer id) {
		try {
			CandidateDto candidate = new CandidateDto();
			candidate.setId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(candidateService.getOne(candidate), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(ApiPath.deleteDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> delete(@PathVariable("id") Integer id) {
		try {
			CandidateDto candidate = new CandidateDto();
			candidate.setId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(candidateService.delete(candidate), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/transfer")
	public ResponseEntity<ResponseDto<BaseDto>> transferCandidate(@RequestBody CandidateDto request) {
		request.setStatus("Transfered");
		candidateService.update(request);
	    
	    TransferCandidateDto transferCandidateDto = new TransferCandidateDto();
		transferCandidateDto.setCandidateId(request.getId());
		transferCandidateDto.setProfileId(request.getTraineeCandidateProfile().getProfileId());
		transferCandidateDto.setRemarks(null);
		return new ResponseEntity<ResponseDto<BaseDto>>(candidateService.tranferCadidate(transferCandidateDto), HttpStatus.OK);
	}
	
	@GetMapping(ApiPath.getAllDomain +"/page/{currentPage}")
	public ResponseEntity<ResponseDto<BaseDto>> getAllPaging(@PathVariable("currentPage") Integer currentPage) {
		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(candidateService.getAllPaging(currentPage), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
