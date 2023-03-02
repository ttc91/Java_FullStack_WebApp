package com.example.demo.services;

import com.example.demo.services.dtos.TrainingResultDto;

public interface TrainingResultService {

	TrainingResultDto get(Integer classBatchId, Integer traineeId);

	TrainingResultDto update(TrainingResultDto dto);


}
