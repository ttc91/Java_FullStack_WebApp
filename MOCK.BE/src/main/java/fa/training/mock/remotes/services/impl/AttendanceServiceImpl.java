package fa.training.mock.remotes.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.res.trainingResult.AttendanceStatusDto;
import fa.training.mock.remotes.entities.AttendantStatus;
import fa.training.mock.remotes.repositories.AttendanceStatusRepository;
import fa.training.mock.remotes.services.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService{

	@Autowired
	AttendanceStatusRepository attendanceStatusRepository;
	
	@Override
	public Optional<AttendantStatus> findById(Integer id) {
		return attendanceStatusRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		attendanceStatusRepository.deleteById(id);
	}

	@Override
	public Page<AttendantStatus> findAll(Pageable pageable) {
		return attendanceStatusRepository.findAll(pageable);
	}

	@Override
	public List<AttendantStatus> findAll() {
		return attendanceStatusRepository.findAll();
	}

	@Override
	public AttendantStatus save(AttendanceStatusDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttendantStatus update(AttendanceStatusDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
