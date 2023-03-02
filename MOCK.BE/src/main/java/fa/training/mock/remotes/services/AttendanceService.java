package fa.training.mock.remotes.services;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.training.mock.models.dto.TraineeDto;
import fa.training.mock.models.dto.res.trainingResult.AttendanceStatusDto;
import fa.training.mock.remotes.entities.AttendantStatus;
import fa.training.mock.remotes.entities.Trainee;

public interface AttendanceService {
	Optional<AttendantStatus> findById(Integer id);

	void delete(Integer id);

	Page<AttendantStatus> findAll(Pageable pageable);

	List<AttendantStatus> findAll();

	public AttendantStatus save(AttendanceStatusDto dto);

	public AttendantStatus update(AttendanceStatusDto dto);
}
