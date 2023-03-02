package fa.training.mock.models.mapper.config;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.UserDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.User;

@Component
public class UserMapper extends AbstractModelMapper<User, UserDto>{

}
