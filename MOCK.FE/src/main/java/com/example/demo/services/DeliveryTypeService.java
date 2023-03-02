package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.DeliveryTypeDto;

public interface DeliveryTypeService {
	List<DeliveryTypeDto> getAll();
}
