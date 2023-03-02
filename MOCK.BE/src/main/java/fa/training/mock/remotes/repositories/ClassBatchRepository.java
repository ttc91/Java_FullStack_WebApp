package fa.training.mock.remotes.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fa.training.mock.config.enums.StatusClassEnum;
import fa.training.mock.remotes.entities.ClassBatch;

@Repository
public interface ClassBatchRepository extends JpaRepository<ClassBatch, Integer>{

	List<ClassBatch> findByClassName(String className);
	
	ClassBatch findByClassCode(String classCode);
	
	List<ClassBatch> findByLocationLocationName(String locationName);
	
	List<ClassBatch> findByActualStartDateGreaterThanEqualAndActualEndDateLessThanEqual(Date actualStartDate, Date actualEndDate);
	
	List<ClassBatch> findByStatus(StatusClassEnum status);
	
	@Transactional
	@Modifying
	@Query("update ClassBatch c set c.status = ?1 where c.classBatchId = ?2")
	int setStatusForClassBatch(StatusClassEnum status, Integer classBatchId);
}
