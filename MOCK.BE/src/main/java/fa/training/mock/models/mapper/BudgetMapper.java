package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.BudgetDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Budget;

@Component
public class BudgetMapper extends AbstractModelMapper<Budget, BudgetDto>{

}
