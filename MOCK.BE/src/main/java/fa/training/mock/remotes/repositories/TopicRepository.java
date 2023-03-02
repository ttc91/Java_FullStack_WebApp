package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
	@Query(value = "SELECT t FROM Topic t WHERE t.milestone.milestoneId = ?1")
	public List<Topic> findByMilestoneId(int milestoneId);
	
	@Query(value = "SELECT t FROM Topic t WHERE t.milestone.classBatch.classBatchId = ?1")
	public List<Topic> findByClassBatchId(int classId);
	
}
