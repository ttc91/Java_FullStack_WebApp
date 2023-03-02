package fa.training.mock.remotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.remotes.entities.BudgetItem;
import fa.training.mock.remotes.entities.ClassBatch;

@Repository
public interface BudgetItemRepository extends JpaRepository<BudgetItem, Integer>{
	
	List<BudgetItem> findByClassBatch(ClassBatch classBatch);
	
}
