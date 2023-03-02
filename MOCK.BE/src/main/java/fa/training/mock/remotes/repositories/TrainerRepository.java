package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{
	
	public Trainer findTrainerByUsername(String username);
	
}
