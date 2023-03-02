package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.dto.res.trainingResult.MilestoneDto;
import fa.training.mock.models.dto.res.trainingResult.TopicDto;
import fa.training.mock.models.mapper.MilestoneMapper;
import fa.training.mock.remotes.entities.Milestone;
import fa.training.mock.remotes.entities.Topic;
import fa.training.mock.remotes.repositories.ClassBatchRepository;
import fa.training.mock.remotes.repositories.MilestoneRepository;
import fa.training.mock.remotes.repositories.TopicRepository;
import fa.training.mock.remotes.services.MilestoneService;

@Service
public class MilestoneServiceImpl implements MilestoneService {

	@Autowired
	MilestoneRepository milestoneRepository;

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	ClassBatchRepository classBatchRepository;
	
	@Autowired
	ModelMapper mapper;

	@Autowired
	MilestoneMapper milestoneMapper;

	@Override
	public Optional<Milestone> findById(Integer id) {
		return milestoneRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		milestoneRepository.deleteById(id);

	}

	@Override
	public Page<Milestone> findAll(Pageable pageable) {
		return milestoneRepository.findAll(pageable);
	}

	@Override
	public ResponseDto<BaseDto> findAll() {
		try {
			
			List<?> dtos = milestoneMapper.mapToDtoList(milestoneRepository.findAll(),  MilestoneDto.class);
			
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


	public Boolean saveAll(Collection<MilestoneDto> dtos, Integer classId) {
		for (MilestoneDto d :  dtos) {
			if (d.getMilestoneName() == null) continue;
			Milestone m = mapper.map(d, Milestone.class);
			m.setClassBatch(classBatchRepository.findById(classId).orElse(null));
			
			Milestone temp = milestoneRepository.save(m);
			
			for (TopicDto td : d.getTopics()) {
				Topic t = mapper.map(td, Topic.class);
				t.setMilestone(temp);
				if (td.getWillDelete().equals("false")){
					topicRepository.save(t);
				} else {
					System.out.println("delete");
					topicRepository.delete(t);
				}
			}
			
			if (d.getWillDelete().equals("true")) {
				milestoneRepository.delete(temp);
			}
		}

		return true;
	}

	@Override
	public Milestone update(MilestoneDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<BaseDto> findByClassId(int classId) {
			// TODO Auto-generated method stub
			try {
				
				List<?> dtos = milestoneMapper.mapToDtoList(milestoneRepository.findByClassId(classId),  MilestoneDto.class);
				
				return ResponseDto.<BaseDto>builder().message("Get all milestones by class id completed !!!")
						.objList(dtos)
						.createdTime(LocalDateTime.now())
						.statusCode(ResponseCode.RESPONSE_OK_CODE).build();
				
			}catch (Exception e) {
				// TODO: handle exception
				return ResponseDto.<BaseDto>builder().message("Get all milestones by class id failed !!!")
						.createdTime(LocalDateTime.now())
						.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			}
		}

}
