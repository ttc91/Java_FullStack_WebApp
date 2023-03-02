package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.RewardPenalty;

@Repository
public interface RewardPenaltyRepository extends JpaRepository<RewardPenalty, Integer>{

	@Query(value="SELECT * FROM reward_penalty t WHERE t.trainee_id =:traineeId AND t.milestone_id =:milestoneId", nativeQuery=true)
	public List<RewardPenalty> findByTraineeIdAndMilestoneId(Integer traineeId, Integer milestoneId);
}
