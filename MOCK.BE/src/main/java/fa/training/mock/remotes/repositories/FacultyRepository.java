package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer>{
	List<Faculty> findByMajorName(String majorName);
}
