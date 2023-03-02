package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.SupplierPartnerDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.SupplierPartnerMapper;
import fa.training.mock.remotes.entities.SupplierPartner;
import fa.training.mock.remotes.repositories.SupplierPartnerRepository;
import fa.training.mock.remotes.services.SupplierPartnerService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class SupplierPartnerServiceImpl extends BaseService<BaseDto> implements SupplierPartnerService{

	@Autowired
	SupplierPartnerRepository repository;
	
	@Autowired
	SupplierPartnerMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			SupplierPartner supplierPartner = mapper.mapToEntity((SupplierPartnerDto)obj, SupplierPartner.class);
			supplierPartner.setId(null);
			repository.save(supplierPartner);
			
			return ResponseDto.<BaseDto>builder().message("Create supplier partner completed !!!")
					.statusCode(ResponseCode.RESPONSE_CREATED).obj(mapper.mapToDto(supplierPartner, SupplierPartnerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create supplier partner failed !!!")
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).createdTime(LocalDateTime.now()).build();
			}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			SupplierPartner supplierPartner = repository.findById(((SupplierPartnerDto) obj).getId()).get();
			supplierPartner.setSupplierPartnerName(((SupplierPartnerDto) obj).getSupplierPartnerName());
			supplierPartner.setRemarks(((SupplierPartnerDto) obj).getRemarks());
			repository.save(supplierPartner);
			
			return ResponseDto.<BaseDto>builder().message("Update supplier partner completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.obj(mapper.mapToDto(supplierPartner, SupplierPartnerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Update supplier partner failed !!!").statusCode(ResponseCode.RESPONSE_BAD_REQUEST) 
					.createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			SupplierPartner supplierPartner = repository.findById(((SupplierPartnerDto) obj).getId()).get();
			repository.delete(supplierPartner);
			
			return ResponseDto.<BaseDto>builder().message("Delete supplier partner completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Delete supplier partner failed !!!").statusCode(ResponseCode.RESPONSE_BAD_REQUEST) 
					.createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			SupplierPartner supplierPartner = repository.findById(((SupplierPartnerDto) obj).getId()).get();
			
			return ResponseDto.<BaseDto>builder().message("Get supplier partner completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.obj(mapper.mapToDto(supplierPartner, SupplierPartnerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get supplier partner failed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.createdTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			return ResponseDto.<BaseDto>builder().message("Get all supplier partners completed !!!").statusCode(ResponseCode.RESPONSE_OK_CODE) 
					.objList(mapper.mapToDtoList(repository.findAll(), SupplierPartnerDto.class)).createdTime(LocalDateTime.now()).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Get all supplier partners failed !!!").statusCode(ResponseCode.RESPONSE_BAD_REQUEST) 
					.createdTime(LocalDateTime.now()).build();
		}
	}
	
	
	
	
}
