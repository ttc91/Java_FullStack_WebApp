package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{
	List<Status> findByStatusName(String statusName);
}
