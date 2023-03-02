package fa.training.mock.remotes.services;

import java.util.List;
import java.util.Optional;

import fa.training.mock.remotes.entities.TrainerClass;

public interface TrainerClassService {
	void delete(TrainerClass entity);

	Optional<TrainerClass> findById(Integer id);

	List<TrainerClass> findAll();

	<S extends TrainerClass> S save(S entity);
}
