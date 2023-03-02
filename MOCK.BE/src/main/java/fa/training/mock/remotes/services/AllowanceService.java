package fa.training.mock.remotes.services;

import java.util.Collection;

import fa.training.mock.models.dto.res.trainingResult.AllocationDto;
import fa.training.mock.models.dto.res.trainingResult.AllowanceDto;


public interface AllowanceService {
	public Boolean saveAll(Collection<AllowanceDto> dtos, Integer traineeId);
}
