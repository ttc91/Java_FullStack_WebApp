package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Milestone;


@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Integer>{
	@Query(value = "SELECT m FROM Milestone m WHERE m.classBatch.classBatchId = ?1")
	public List<Milestone> findByClassId(int classId);
	
}
