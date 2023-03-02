package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.res.trainingResult.MilestoneDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Milestone;

@Component
public class MilestoneMapper extends AbstractModelMapper<Milestone, MilestoneDto>{

}
