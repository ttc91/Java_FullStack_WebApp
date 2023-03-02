package fa.training.mock.remotes.services;

import java.util.Collection;

import fa.training.mock.models.dto.res.trainingResult.AllocationDto;
import fa.training.mock.models.dto.res.trainingResult.AllowanceDto;
import fa.training.mock.remotes.entities.Allowance;

public interface AllocationService{
	public Boolean saveAll(Collection<AllocationDto> dtos, Integer traineeId);
}
