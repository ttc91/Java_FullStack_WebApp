package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.BudgetItemDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.BudgetItem;

@Component
public class BudgetItemMapper extends AbstractModelMapper<BudgetItem, BudgetItemDto>{
}
