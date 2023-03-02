package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.AuditDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Audit;

@Component
public class AuditMapper extends AbstractModelMapper<Audit, AuditDto>{

}
