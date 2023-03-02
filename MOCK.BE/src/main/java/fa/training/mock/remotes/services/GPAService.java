package fa.training.mock.remotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.training.mock.models.dto.res.trainingResult.GPADto;
import fa.training.mock.remotes.entities.Allowance;
import fa.training.mock.remotes.entities.GPA;

public interface GPAService {
	Optional<GPA> findById(Integer id);

	void delete(Integer id);

	Page<GPA> findAll(Pageable pageable);

	List<GPA> findAll();

	public Allowance save(GPADto dto);

	public Allowance update(GPADto dto);

}
