package fa.training.mock.remotes.services;

import java.util.List;

import fa.training.mock.models.dto.TraineeDto;

import fa.training.mock.models.dto.TraineeDtoList;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.res.trainingResult.CommitmentDto;
import fa.training.mock.remotes.entities.Trainee;

public interface TraineeService  {

	ResponseDto<BaseDto> update(BaseDto obj);

	ResponseDto<BaseDto> create(BaseDto obj);

	ResponseDto<BaseDto> getAll();

	ResponseDto<BaseDto> getOne(BaseDto obj);

	ResponseDto<BaseDto> delete(BaseDto obj);
	
	List<Trainee> findAll();

	Trainee update(CommitmentDto dto, Integer traineeId);

	ResponseDto<BaseDto> getAllByClassBatchId(Integer classBatchId);

	ResponseDto<BaseDto> updateLocation(List<TraineeDto> obj);

	ResponseDto<BaseDto> updateTraineeStatus(List<TraineeDto> obj);

	ResponseDto<BaseDto> updateTopicMark(List<TraineeDto> obj);

	ResponseDto<BaseDto> updateRewardPenalty(List<TraineeDto> obj);

	ResponseDto<BaseDto> findByWaitingforClass(Integer classBatchId);

	ResponseDto<BaseDto> addTraineeToClass(List<Integer> traineeIds, Integer classId);

	ResponseDto<BaseDto> removeTraineeFromClass(List<Integer> traineeIds);
	Boolean updateAttendenceStatus(TraineeDtoList traineeDtoList);
}
