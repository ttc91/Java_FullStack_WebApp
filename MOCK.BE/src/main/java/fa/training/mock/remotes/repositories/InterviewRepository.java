package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
	@Query(value = "SELECT m FROM Interview m WHERE m.candidate.id = ?1")
	List<Interview> findByCandidateId(Integer candidateId);
}