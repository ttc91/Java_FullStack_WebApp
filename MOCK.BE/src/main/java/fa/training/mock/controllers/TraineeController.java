package fa.training.mock.controllers;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.TraineeDto;
import fa.training.mock.models.dto.TraineeDtoList;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.remotes.services.TraineeService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.traineeDomain)
public class TraineeController {

	@Autowired
	private TraineeService service;

	@PostMapping(ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody TraineeDto request) {

		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(service.create(request), HttpStatus.CREATED);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping(ApiPath.updateDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> update(@RequestBody TraineeDto request,
			@PathVariable("id") Integer id) {

		try {
			request.setTraineeId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.update(request), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(ApiPath.deleteDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> delete(@PathVariable("id") Integer id) {
		try {
			TraineeDto dto = new TraineeDto();
			dto.setTraineeId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.delete(dto), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getOneDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("id") Integer id) {
		try {
			TraineeDto dto = new TraineeDto();
			dto.setTraineeId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getOne(dto), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getAllDomain)
	public ResponseEntity<ResponseDto<BaseDto>> getAll() {
		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAll(), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(ApiPath.getAllDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> getAllByClassId(@PathVariable("id") Integer id) {
		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAllByClassBatchId(id), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(ApiPath.getAllDomain + ApiPath.ALLOCATION_DOMAIN)
	public ResponseEntity<ResponseDto<BaseDto>> updateLocation(@RequestBody List<TraineeDto> request) {

		try {
			System.out.println("======================");
			System.out.println(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.updateLocation(request), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(ApiPath.getAllDomain + ApiPath.statusDomain)
	public ResponseEntity<ResponseDto<BaseDto>> updateTraineeStatus(@RequestBody List<TraineeDto> request) {

		try {
			System.out.println("======================");
			System.out.println(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.updateTraineeStatus(request), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(ApiPath.getAllDomain + ApiPath.topicMarkDomain)
	public ResponseEntity<ResponseDto<BaseDto>> updateTopicMark(@RequestBody List<TraineeDto> request) {

		try {
			System.out.println("======================");
			System.out.println(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.updateTopicMark(request), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(ApiPath.getAllDomain + ApiPath.rewardPenaltyDomain)
	public ResponseEntity<ResponseDto<BaseDto>> updateRewardPenalty(@RequestBody List<TraineeDto> request) {

		try {
			System.out.println("======================");
			System.out.println(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.updateRewardPenalty(request), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(ApiPath.getAllDomain + "/{classBatchId}" + ApiPath.statusDomain + "/WaitingforClass")
	public ResponseEntity<ResponseDto<BaseDto>> findByWaitingforClass(
			@PathVariable("classBatchId") Integer classBatchId) {

		try {
			return new ResponseEntity<ResponseDto<BaseDto>>(service.findByWaitingforClass(classBatchId), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(ApiPath.ADD_TO_CLASS + "/{classId}")
	public ResponseEntity<ResponseDto<BaseDto>> removeTraineeFROMClass(@RequestBody List<Integer> traineeIds,
			@PathVariable("classId") Integer classId) {

		try {
			System.out.println("==================traineeIds===============");
			System.out.println(traineeIds);
			System.out.println("==================classId===============");
			System.out.println(classId);

			return new ResponseEntity<ResponseDto<BaseDto>>(service.addTraineeToClass(traineeIds, classId),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(ApiPath.ATTENDENCE_STATUS_DOMAIN + ApiPath.updateDomain)
	public ResponseEntity<ResponseDto<BaseDto>> updateAttendenceStatus(@RequestBody TraineeDtoList request) {

		try {
			System.out.println(request);
			boolean result = service.updateAttendenceStatus(request);

			return new ResponseEntity<ResponseDto<BaseDto>>(
					ResponseDto.<BaseDto>builder().message("Update attendence status completed !!!").obj(null)
							.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build(),
					HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(ApiPath.DELETE_FROM_CLASS + "/{classId}")
	public ResponseEntity<ResponseDto<BaseDto>> addTraineeToClass(@RequestBody List<Integer> traineeIds,
			@PathVariable("classId") Integer classId) {

		try {
			System.out.println("==================traineeIds===============");
			System.out.println(traineeIds);
			System.out.println("==================classId===============");
			System.out.println(classId);

			return new ResponseEntity<ResponseDto<BaseDto>>(service.removeTraineeFromClass(traineeIds), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
