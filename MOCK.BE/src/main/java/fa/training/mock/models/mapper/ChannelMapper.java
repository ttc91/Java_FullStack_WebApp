package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.ChannelDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.Channel;

@Component
public class ChannelMapper extends AbstractModelMapper<Channel, ChannelDto> {

}
