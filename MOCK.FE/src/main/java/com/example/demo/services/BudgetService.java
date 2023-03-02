package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.BudgetDto;

public interface BudgetService {
	List<BudgetDto> getAll();
}
