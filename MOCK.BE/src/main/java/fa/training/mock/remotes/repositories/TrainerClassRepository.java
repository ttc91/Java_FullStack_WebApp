package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.TrainerClass;

@Repository
public interface TrainerClassRepository extends JpaRepository<TrainerClass, Integer>{

}
