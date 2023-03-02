package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.ChannelDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.ChannelMapper;
import fa.training.mock.remotes.entities.Channel;
import fa.training.mock.remotes.repositories.ChannelRepository;
import fa.training.mock.remotes.services.ChannelService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class ChannelServiceImpl extends BaseService<BaseDto> implements ChannelService {

	@Autowired
	ChannelRepository repository;

	@Autowired
	ChannelMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			Channel channel = mapper.mapToEntity((ChannelDto) obj, Channel.class);
			repository.save(channel);

			return ResponseDto.<BaseDto>builder().message("Create channel completed !!!")
					.obj(mapper.mapToDto(channel, ChannelDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create channel failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			Channel channel = mapper.mapToEntity((ChannelDto) obj, Channel.class);
			Channel channelOld = repository.findById(channel.getId()).get();
			channelOld = channel;
			repository.save(channelOld);

			return ResponseDto.<BaseDto>builder().message("Update channel completed !!!")
					.obj(mapper.mapToDto(channelOld, ChannelDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update channel failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Channel channel = repository.findById(mapper.mapToEntity((ChannelDto) obj, Channel.class).getId()).get();
			repository.delete(channel);

			return ResponseDto.<BaseDto>builder().message("Delete channel completed !!!")
					.obj(mapper.mapToDto(channel, ChannelDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete channel failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			Channel channel = repository.findById(mapper.mapToEntity((ChannelDto) obj, Channel.class).getId()).get();

			return ResponseDto.<BaseDto>builder().message("Get channel completed !!!")
					.obj(mapper.mapToDto(channel, ChannelDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get channel failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {

			List<?> dtos = mapper.mapToDtoList(repository.findAll(), ChannelDto.class);

			return ResponseDto.<BaseDto>builder().message("Get all channel completed !!!").objList(dtos)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all channel failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

}
