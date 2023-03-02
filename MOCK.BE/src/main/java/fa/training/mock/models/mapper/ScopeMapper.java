package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.ScopeDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Scope;

@Component
public class ScopeMapper extends AbstractModelMapper<Scope, ScopeDto> {

}
