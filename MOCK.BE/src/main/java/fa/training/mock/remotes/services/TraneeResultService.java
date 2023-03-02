package fa.training.mock.remotes.services;

import java.util.List;

import fa.training.mock.remotes.entities.Milestone;

public interface TraneeResultService {

	List<Milestone> getMilestone(Integer classBatchId, Integer traneeId);

}
