package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.EntryTestDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.EntryTestMapper;
import fa.training.mock.remotes.entities.Candidate;
import fa.training.mock.remotes.entities.EntryTest;
import fa.training.mock.remotes.repositories.EntryTestRepository;
import fa.training.mock.remotes.services.EntryTestService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class EntryTestServiceImpl extends BaseService<BaseDto> implements EntryTestService {

	@Autowired
	EntryTestRepository repository;

	@Autowired
	EntryTestMapper mapper;

	@Override
	public ResponseDto<BaseDto> create(BaseDto obj) {
		// TODO Auto-generated method stub
		try {
			EntryTest entryTest = mapper.mapToEntity((EntryTestDto) obj, EntryTest.class);
			repository.save(entryTest);

			return ResponseDto.<BaseDto>builder().message("Create entry test completed !!!")
					.obj(mapper.mapToDto(entryTest, EntryTestDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_CREATED).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create entry test failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj, Integer id) {
		// TODO Auto-generated method stub
		try {
			// TODO Watching here!
			EntryTest entryTest1 = repository.save(mapper.mapToEntity((EntryTestDto) obj, EntryTest.class));
//			EntryTest entryTest = repository
//					.findById(mapper.mapToEntity((EntryTestDto) obj, EntryTest.class).getEntryTestId()).get();
//			System.out.println("ENTRY TEST +++++++>"+entryTest);
//			EntryTest entryTest1 = repository.save(mapper.mapToEntity((EntryTestDto) obj, EntryTest.class));
			System.out.println("ENTRY TEST 1 +++++++>" + entryTest1);

			return ResponseDto.<BaseDto>builder().message("Update entry test completed !!!")
					.obj(mapper.mapToDto(entryTest1, EntryTestDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Update entry test failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> delete(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			EntryTest entryTest = repository
					.findById(mapper.mapToEntity((EntryTestDto) obj, EntryTest.class).getEntryTestId()).get();
			repository.delete(entryTest);

			return ResponseDto.<BaseDto>builder().message("Delete entry test completed !!!")
					.obj(mapper.mapToDto(entryTest, EntryTestDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Delete entry test failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getOne(BaseDto obj) {
		// TODO Auto-generated method stub
		try {

			EntryTest entryTest = repository
					.findById(mapper.mapToEntity((EntryTestDto) obj, EntryTest.class).getEntryTestId()).get();

			return ResponseDto.<BaseDto>builder().message("Get entry test completed !!!")
					.obj(mapper.mapToDto(entryTest, EntryTestDto.class)).createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get entry test failed !!!").createdTime(LocalDateTime.now())
					.statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> getAll() {
		// TODO Auto-generated method stub
		try {

			List<?> entryTests = mapper.mapToDtoList(repository.findAll(), EntryTestDto.class);

			return ResponseDto.<BaseDto>builder().message("Get all locations completed !!!").objList(entryTests)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get all locations failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> createList(Collection<?> objs, Integer id) {
		// TODO Auto-generated method stub
		try {
			Candidate candidate = new Candidate();
			candidate.setId(id);
			List<EntryTestDto> entryTests = (List<EntryTestDto>) objs;
			if (entryTests != null) {
				for (EntryTestDto entryTestDto : entryTests) {
				    if(entryTestDto.getWillDelete() != null) {
				        System.out.println(entryTestDto.getWillDelete().equals("true"));
	                    if (entryTestDto.getWillDelete().equals("true")) {
	                        repository.delete(mapper.mapToEntity(entryTestDto, EntryTest.class));
	                        System.out.println("delete entrytest: "+entryTestDto);
	                    } else {
	                        EntryTest entryTest = mapper.mapToEntity(entryTestDto, EntryTest.class);
	                        entryTest.setCandidate(candidate);
	                        repository.save(entryTest);
	                        System.out.println("create entrytest: "+entryTestDto);
	                    }
				    }
				}
				return ResponseDto.<BaseDto>builder().message("Create entry test list completed !!!")
						.objList(entryTests).createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_CREATED)
						.build();
			}

			else {
				return ResponseDto.<BaseDto>builder().message("Create entry test list failed !!!")
						.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseDto.<BaseDto>builder().message("Create entry test list failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> findByCandidateId(Integer candidateId) {
		// TODO Auto-generated method stub
		try {
			List<?> entryTests = mapper.mapToDtoList(repository.findByCandidateId(candidateId), EntryTestDto.class);
			return ResponseDto.<BaseDto>builder().message("Get entry test list completed !!!").objList(entryTests)
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_CREATED).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseDto.<BaseDto>builder().message("Get entry test list failed !!!")
					.createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseDto<BaseDto> update(BaseDto obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
