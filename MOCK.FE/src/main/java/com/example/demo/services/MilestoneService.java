package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.MilestoneDto;

public interface MilestoneService {

	List<MilestoneDto> getMilestoneByClass(Integer classId);

	List<MilestoneDto> getAll();

}
