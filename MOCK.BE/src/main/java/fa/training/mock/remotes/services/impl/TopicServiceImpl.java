package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.dto.res.trainingResult.TopicDto;
import fa.training.mock.models.mapper.TopicMapper;
import fa.training.mock.remotes.repositories.TopicRepository;
import fa.training.mock.remotes.services.TopicService;

@Service
public class TopicServiceImpl implements TopicService{
	
	@Autowired
	TopicRepository repository;
	
	@Autowired
	TopicMapper mapper;
	
	@Override
	public ResponseDto<BaseDto> findAllByClassId(Integer classId) {
		try {
			
			List<?> dtos = mapper.mapToDtoList(repository.findByClassBatchId(classId),  TopicDto.class);
			
			return ResponseDto.<BaseDto>builder().message("Get all milestones completed !!!")
					.objList(dtos)
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all milestones failed !!!")
					.createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}
	
}
