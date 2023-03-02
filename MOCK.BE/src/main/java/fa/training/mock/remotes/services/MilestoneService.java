package fa.training.mock.remotes.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.res.trainingResult.MilestoneDto;
import fa.training.mock.remotes.entities.Milestone;

public interface MilestoneService {
	Optional<Milestone> findById(Integer id);

	void delete(Integer id);

	Page<Milestone> findAll(Pageable pageable);

	ResponseDto<BaseDto> findAll();

	public Boolean saveAll(Collection<MilestoneDto> dtos, Integer classId);

	public Milestone update(MilestoneDto dto);

	ResponseDto<BaseDto> findByClassId(int classId);

}
