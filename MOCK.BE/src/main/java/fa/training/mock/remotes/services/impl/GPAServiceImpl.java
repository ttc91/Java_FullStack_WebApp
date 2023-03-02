package fa.training.mock.remotes.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.res.trainingResult.GPADto;
import fa.training.mock.remotes.entities.Allowance;
import fa.training.mock.remotes.entities.GPA;
import fa.training.mock.remotes.repositories.GPARepository;
import fa.training.mock.remotes.services.GPAService;

@Service
public class GPAServiceImpl implements GPAService{

	@Autowired
	GPARepository gpaRepository;
	
	@Override
	public Optional<GPA> findById(Integer id) {
		return gpaRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		gpaRepository.deleteById(id);
		
	}

	@Override
	public Page<GPA> findAll(Pageable pageable) {
		return gpaRepository.findAll(pageable);
	}

	@Override
	public List<GPA> findAll() {
		return gpaRepository.findAll();
	}

	@Override
	public Allowance save(GPADto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Allowance update(GPADto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
