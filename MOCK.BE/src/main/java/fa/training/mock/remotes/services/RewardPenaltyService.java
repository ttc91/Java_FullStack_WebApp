package fa.training.mock.remotes.services;

import java.util.Collection;
import fa.training.mock.models.dto.res.trainingResult.RewardPenaltyDto;

public interface RewardPenaltyService{
	Boolean saveAll(Collection<RewardPenaltyDto> dtos, Integer traineeId);
}
