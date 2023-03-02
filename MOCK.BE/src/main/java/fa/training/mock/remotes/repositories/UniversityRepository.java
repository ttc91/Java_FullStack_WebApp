package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.AdminProfile;
import fa.training.mock.remotes.entities.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer>{
	List<University> findByUniversityName(String universityName);
}
