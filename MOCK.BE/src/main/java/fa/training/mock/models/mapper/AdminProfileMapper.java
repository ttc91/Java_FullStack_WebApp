package fa.training.mock.models.mapper;

import org.springframework.stereotype.Component;

import fa.training.mock.models.dto.AdminProfileDto;
import fa.training.mock.models.mapper.base.AbstractModelMapper;
import fa.training.mock.remotes.entities.AdminProfile;

@Component
public class AdminProfileMapper extends AbstractModelMapper<AdminProfile, AdminProfileDto>{

}
