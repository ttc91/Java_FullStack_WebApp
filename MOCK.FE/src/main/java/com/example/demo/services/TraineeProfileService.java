package com.example.demo.services;

import com.example.demo.services.dtos.TraineeDto;

public interface TraineeProfileService {

	public TraineeDto getOne(Integer id);

	Boolean update(TraineeDto dto);

}
