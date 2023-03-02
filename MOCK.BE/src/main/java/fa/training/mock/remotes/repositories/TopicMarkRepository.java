package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.TopicMark;

@Repository
public interface TopicMarkRepository extends JpaRepository<TopicMark, Integer>{
	
	@Query(value="SELECT * FROM Topic_mark t WHERE t.trainee_id =:traineeId AND t.topic_id =:topicId", nativeQuery=true)
	public List<TopicMark> findByTraineeIdAndTopicId(Integer traineeId, Integer topicId);

}
