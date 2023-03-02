package fa.training.mock.remotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.mock.config.enums.BudgetEnum;
import fa.training.mock.remotes.entities.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer>{
	
	public Budget findBudgetByBudgetCode(BudgetEnum budgetCode);
	
	
}
