package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.TraineeDto;
import com.example.demo.services.dtos.TraineeDtoList;

public interface TraineeService {

	List<TraineeDto> getTraineeByClassBatch(Integer classPatchId);

	List<TraineeDto> getAll();
	
	Boolean updateAttendentStatus(TraineeDtoList traineeDtoList);

	Boolean updateTraineeLocation(List<TraineeDto> traineeDtos);

	Boolean updateTraineeStatus(List<TraineeDto> traineeDtos);

	Boolean updateTopicMark(List<TraineeDto> traineeDtos);

	Boolean updateRewardPenalty(List<TraineeDto> traineeDtos);

	List<TraineeDto> getByWaitingForClass(Integer classBatchId);

	Boolean addTraineeToClass(List<Integer> traineeIds, Integer classId);

	Boolean removeTraineesFromClass(List<Integer> traineeIds, Integer classId);

}
