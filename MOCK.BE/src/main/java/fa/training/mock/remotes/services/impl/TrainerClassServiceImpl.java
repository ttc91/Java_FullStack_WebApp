package fa.training.mock.remotes.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.remotes.entities.TrainerClass;
import fa.training.mock.remotes.repositories.TrainerClassRepository;
import fa.training.mock.remotes.services.TrainerClassService;

@Service
public class TrainerClassServiceImpl implements TrainerClassService{

	@Autowired
	private TrainerClassRepository repository;
	
	@Override
	public void delete(TrainerClass entity) {
		repository.delete(entity);
	}

	@Override
	public Optional<TrainerClass> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<TrainerClass> findAll() {
		return repository.findAll();
	}

	@Override
	public <S extends TrainerClass> S save(S entity) {
		return repository.save(entity);
	}

}
