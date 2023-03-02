package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.ClassBatch;
import fa.training.mock.remotes.entities.TopicMark;
import fa.training.mock.remotes.entities.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer>{
	List<Trainee> findByTraineeCandidateProfileProfileId(Integer profileId);
	List<Trainee> findByClassBatch(ClassBatch classBatch);
	@Query(value="SELECT t FROM Trainee t WHERE (t.classBatch = NULL OR t.classBatch.classBatchId !=:classBatchId) AND (t.status.statusName = 'Waiting for Class' OR t.status.statusName = 'Waiting for Allocation' OR t.status.statusName = 'Deferred' OR t.status.statusName = 'Drop-out')")
	public List<Trainee> findByWaitingforClass(Integer classBatchId);
}
