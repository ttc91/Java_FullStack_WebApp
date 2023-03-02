package fa.training.mock.remotes.services.impl;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.DeliveryTypeDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.DeliveryTypeMapper;
import fa.training.mock.remotes.entities.DeliveryType;
import fa.training.mock.remotes.repositories.DeliveryTypeRepository;
import fa.training.mock.remotes.services.DeliveryTypeService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class DeliveryTypeServiceImpl extends BaseService<BaseDto> implements DeliveryTypeService{
	
	@Autowired
	DeliveryTypeRepository repository;
	
	@Autowired
	DeliveryTypeMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			DeliveryType deliveryType = mapper.mapToEntity((DeliveryTypeDto)obj, DeliveryType.class);
			deliveryType.setDeliveryTypeId(null);
			repository.save(deliveryType);
			
			return ResponseDto.<BaseDto>builder().message("Create delivery type completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(deliveryType, DeliveryTypeDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create delivery type failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			DeliveryType deliveryType = repository.findDeliveryTypeByDeliveryTypeName(((DeliveryTypeDto)obj).getDeliveryTypeName());
			deliveryType.setRemarks(((DeliveryTypeDto)obj).getRemarks());
			repository.save(deliveryType);
			
			return ResponseDto.<BaseDto>builder().message("Update delivery type completed !!!")
					.statusCode(ResponseCode.RESPONSE_OK_CODE).obj(mapper.mapToDto(deliveryType, DeliveryTypeDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update delivery type failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			DeliveryType deliveryType = repository.findDeliveryTypeByDeliveryTypeName(((DeliveryTypeDto)obj).getDeliveryTypeName());
			repository.delete(deliveryType);
			
			return ResponseDto.<BaseDto>builder().message("Delete delivery type completed !!!")
					.statusCode(ResponseCode.RESPONSE_OK_CODE).obj(mapper.mapToDto(deliveryType, DeliveryTypeDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Delete delivery type failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			DeliveryType deliveryType = repository.findDeliveryTypeByDeliveryTypeName(((DeliveryTypeDto)obj).getDeliveryTypeName());
			
			return ResponseDto.<BaseDto>builder().message("Get delivery type completed !!!")
					.statusCode(ResponseCode.RESPONSE_OK_CODE).obj(mapper.mapToDto(deliveryType, DeliveryTypeDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get delivery type failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			return ResponseDto.<BaseDto>builder().message("Get delivery type list completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.objList(mapper.mapToDtoList(repository.findAll(), DeliveryTypeDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get delivery type list failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
		}
	}


}
