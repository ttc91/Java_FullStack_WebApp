package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.DeliveryTypeDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.DeliveryType;

@Component
public class DeliveryTypeMapper extends AbstractModelMapper<DeliveryType, DeliveryTypeDto>{

}
