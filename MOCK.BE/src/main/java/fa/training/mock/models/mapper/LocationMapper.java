package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.LocationDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Location;

@Component
public class LocationMapper extends AbstractModelMapper<Location, LocationDto>{
}
