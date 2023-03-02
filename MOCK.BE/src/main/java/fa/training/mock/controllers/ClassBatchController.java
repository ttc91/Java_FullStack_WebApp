package fa.training.mock.controllers;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.config.enums.StatusClassEnum;
import fa.training.mock.models.dto.ClassBatchDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.ClassBatchService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.CLASSBATCHDOMAIN)
public class ClassBatchController {

	@Autowired
	private ClassBatchService classBatchService;

	@PostMapping(value = ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody ClassBatchDto request) {
		try {
			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.create(request), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = ApiPath.updateDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> update(@RequestBody ClassBatchDto request,
			@PathVariable("id") Integer id) {
		try {
			request.setClassBatchId(id);
			System.out.println(request.toString());
			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.update(request),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = ApiPath.updateDomain + "/edit_status/"+"{id}")
	public ResponseEntity<ResponseDto<BaseDto>> updateStatus(@RequestBody ClassBatchDto request,
			@PathVariable("id") Integer id) {
		try {
			request.setClassBatchId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.updateStatus(request),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(ApiPath.deleteDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> delete(@PathVariable("id") Integer id) {
		try {
			ClassBatchDto dto = new ClassBatchDto();
			dto.setClassBatchId(id);
			
			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.delete(dto), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getOneDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("id") Integer id) {
		try {
			ClassBatchDto dto = new ClassBatchDto();
			dto.setClassBatchId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.getOne(dto), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getAllDomain)
	public ResponseEntity<ResponseDto<BaseDto>> getAll() {
		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.getAll(), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(ApiPath.getAllDomain +"/page/{currentPage}")
	public ResponseEntity<ResponseDto<BaseDto>> getAllPaging(@PathVariable("currentPage") Integer currentPage) {
		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.getAllPaging(currentPage), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(ApiPath.getAllDomain +"/filter")
	public ResponseEntity<ResponseDto<BaseDto>> getSearch(@RequestParam(value ="className") String className,
			@RequestParam(value ="locationName") String locationName, @RequestParam(value ="actualStartDate") String actualStartDate, 
			@RequestParam(value ="actualEndDate") String actualEndDate, @RequestParam(value ="status") String status) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!actualEndDate.isEmpty() && !actualStartDate.isEmpty())
			{
				return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.getAllSearch(className, locationName, 
						format.parse(actualStartDate),format.parse(actualEndDate), StatusClassEnum.valueOf(status)), HttpStatus.OK);
			}
			return new ResponseEntity<ResponseDto<BaseDto>>(classBatchService.getAllSearch(className, locationName, 
					null,null, StatusClassEnum.valueOf(status)), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
