package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.EntryTest;
@Repository
public interface EntryTestRepository  extends JpaRepository<EntryTest, Integer>{
//	@Query(value = "SELECT m FROM EntryTest m WHERE m.candidate.id = ?1")
    List<EntryTest> findByCandidateId(Integer candidateId);
	
}
