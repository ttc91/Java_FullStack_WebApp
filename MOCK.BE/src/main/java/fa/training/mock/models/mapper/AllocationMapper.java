package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.res.trainingResult.AllocationDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Allocation;

@Component
public class AllocationMapper extends AbstractModelMapper<Allocation, AllocationDto>{

}
