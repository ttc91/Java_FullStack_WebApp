package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.AllowanceGroupDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.AllowanceGroupMapper;
import fa.training.mock.remotes.entities.AllowanceGroup;
import fa.training.mock.remotes.repositories.AllowanceGroupRepository;
import fa.training.mock.remotes.services.AllowanceGroupService;

@Service
public class AllowanceGroupServiceImpl extends BaseDto implements AllowanceGroupService{
	
	@Autowired
	AllowanceGroupRepository repository;

	@Autowired
	AllowanceGroupMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			AllowanceGroup allowanceGroup = mapper.mapToEntity((AllowanceGroupDto)obj, AllowanceGroup.class);
			repository.save(allowanceGroup);
			
			return ResponseDto.<BaseDto>builder().message("Create allowance group completed !!!")
					.obj(mapper.mapToDto(allowanceGroup, AllowanceGroupDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create allowance group failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			AllowanceGroup allowanceGroup0 = mapper.mapToEntity((AllowanceGroupDto)obj, AllowanceGroup.class);
			AllowanceGroup allowanceGroup = repository.findById(allowanceGroup0.getAllowanceGroupId()).get();
			allowanceGroup = allowanceGroup0;
			repository.save(allowanceGroup);
			
			return ResponseDto.<BaseDto>builder().message("Update allowance group completed !!!")
					.obj(mapper.mapToDto(allowanceGroup, AllowanceGroupDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update allowance group failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		
		try {
			
			AllowanceGroup allowanceGroup = repository.findById(mapper.mapToEntity((AllowanceGroupDto)obj, AllowanceGroup.class).getAllowanceGroupId()).get();
			repository.delete(allowanceGroup);
			
			return ResponseDto.<BaseDto>builder().message("Delete allowance group completed !!!")
					.obj(mapper.mapToDto(allowanceGroup, AllowanceGroupDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete allowance group failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			
			AllowanceGroup allowanceGroup = repository.findById(mapper.mapToEntity((AllowanceGroupDto)obj, AllowanceGroup.class).getAllowanceGroupId()).get();
			
			return ResponseDto.<BaseDto>builder().message("Get allowance group completed !!!")
					.obj(mapper.mapToDto(allowanceGroup, AllowanceGroupDto.class))
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get allowance group failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {
			
			List<?> dtos = mapper.mapToDtoList(repository.findAll(),  AllowanceGroupDto.class);
			
			return ResponseDto.<BaseDto>builder().message("Get all allowance group completed !!!")
					.objList(dtos)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all allowance group failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}


}
