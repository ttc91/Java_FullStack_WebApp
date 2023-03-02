package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.ClassBatchDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.ClassBatch;

@Component
public class ClassBatchMapper extends AbstractModelMapper<ClassBatch, ClassBatchDto> {

}
