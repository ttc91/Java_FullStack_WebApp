package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Milestone;

@Repository
public interface TraneeResultRepository extends JpaRepository<Milestone, Integer>{

	@Query(value = "SELECT * FROM Milestone m WHERE m.class_batch_id =:classBatchId", nativeQuery = true)
	public List<Milestone> getMilestone(Integer classBatchId);
		
}
