package fa.training.mock.remotes.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mock.models.dto.EntryTestDto;
import fa.training.mock.models.dto.InterviewDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.other.ResponseCode;
import fa.training.mock.models.mapper.InterviewMapper;
import fa.training.mock.remotes.entities.Candidate;
import fa.training.mock.remotes.entities.EntryTest;
import fa.training.mock.remotes.entities.Interview;
import fa.training.mock.remotes.repositories.InterviewRepository;
import fa.training.mock.remotes.services.InterviewService;
import fa.training.mock.remotes.services.base.BaseService;

@Service
public class InterviewServiceImpl extends BaseService<BaseDto> implements InterviewService {

    @Autowired
    InterviewRepository repository;

    @Autowired
    InterviewMapper mapper;

    @Override
    public ResponseDto<BaseDto> create(BaseDto obj) {
        // TODO Auto-generated method stub
        try {
            Interview interview = mapper.mapToEntity((InterviewDto) obj, Interview.class);
            repository.save(interview);

            return ResponseDto.<BaseDto>builder().message("Create interview completed !!!")
                    .obj(mapper.mapToDto(interview, InterviewDto.class)).createdTime(LocalDateTime.now())
                    .statusCode(ResponseCode.RESPONSE_CREATED).build();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDto.<BaseDto>builder().message("Create interview failed !!!")
                    .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseDto<BaseDto> update(BaseDto obj, Integer id) {
        // TODO Auto-generated method stub
        try {
            // TODO Watching here!
            Interview interview = repository
                    .findById(mapper.mapToEntity((InterviewDto) obj, Interview.class).getInteviewId()).get();
            if (interview != null) {
                repository.save(mapper.mapToEntity((InterviewDto) obj, Interview.class));
            }

            return ResponseDto.<BaseDto>builder().message("Update interview completed !!!")
                    .obj(mapper.mapToDto(interview, InterviewDto.class)).createdTime(LocalDateTime.now())
                    .statusCode(ResponseCode.RESPONSE_OK_CODE).build();

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseDto.<BaseDto>builder().message("Update interview failed !!!")
                    .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseDto<BaseDto> delete(BaseDto obj) {
        // TODO Auto-generated method stub
        try {

            Interview interview = repository
                    .findById(mapper.mapToEntity((InterviewDto) obj, Interview.class).getInteviewId()).get();
            repository.delete(interview);

            return ResponseDto.<BaseDto>builder().message("Delete interview completed !!!")
                    .obj(mapper.mapToDto(interview, InterviewDto.class)).createdTime(LocalDateTime.now())
                    .statusCode(ResponseCode.RESPONSE_OK_CODE).build();

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseDto.<BaseDto>builder().message("Delete interview failed !!!")
                    .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseDto<BaseDto> getOne(BaseDto obj) {
        // TODO Auto-generated method stub
        try {

            Interview interview = repository
                    .findById(mapper.mapToEntity((InterviewDto) obj, Interview.class).getInteviewId()).get();

            return ResponseDto.<BaseDto>builder().message("Get interview completed !!!")
                    .obj(mapper.mapToDto(interview, InterviewDto.class)).createdTime(LocalDateTime.now())
                    .statusCode(ResponseCode.RESPONSE_OK_CODE).build();

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseDto.<BaseDto>builder().message("Get interview failed !!!").createdTime(LocalDateTime.now())
                    .statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseDto<BaseDto> getAll() {
        // TODO Auto-generated method stub
        try {

            List<?> interviews = mapper.mapToDtoList(repository.findAll(), InterviewDto.class);

            return ResponseDto.<BaseDto>builder().message("Get interviews completed !!!").objList(interviews)
                    .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_OK_CODE).build();

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseDto.<BaseDto>builder().message("Get interviews failed !!!").createdTime(LocalDateTime.now())
                    .statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseDto<BaseDto> createList(Collection<?> objs, Integer id) {
        // TODO Auto-generated method stub

        try {
            Candidate candidate = new Candidate();
            candidate.setId(id);
            List<InterviewDto> interviewDtos = (List<InterviewDto>) objs;
            if (interviewDtos != null) {
                for (InterviewDto interviewDto : interviewDtos) {
                    if (interviewDto.getWillDelete() != null) {
                        if (interviewDto.getWillDelete().equals("true")) {
                            repository.delete(mapper.mapToEntity(interviewDto, Interview.class));

                        } else {
                            Interview interview = mapper.mapToEntity(interviewDto, Interview.class);
                            interview.setCandidate(candidate);
                            repository.save(interview);
                        }
                    }
                }
                return ResponseDto.<BaseDto>builder().message("Create interview list completed !!!")
                        .objList(interviewDtos).createdTime(LocalDateTime.now())
                        .statusCode(ResponseCode.RESPONSE_CREATED).build();
            }

            else {
                return ResponseDto.<BaseDto>builder().message("Create interview list failed !!!")
                        .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDto.<BaseDto>builder().message("Create interview list failed !!!")
                    .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseDto<BaseDto> findByCandidateId(Integer candidateId) {
        // TODO Auto-generated method stub
        try {
            List<?> interviews = mapper.mapToDtoList(repository.findByCandidateId(candidateId), InterviewDto.class);
            return ResponseDto.<BaseDto>builder().message("Get interview list completed !!!").objList(interviews)
                    .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_CREATED).build();
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseDto.<BaseDto>builder().message("Get interview list failed !!!")
                    .createdTime(LocalDateTime.now()).statusCode(ResponseCode.RESPONSE_ERROR_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseDto<BaseDto> update(BaseDto obj) {
        // TODO Auto-generated method stub
        return null;
    }

}
