package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.evalution.result.EvalutionResultRequest;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultResponse;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultWithSemester;
import com.example.trainning.point.entity.EvalutionResult;
import com.example.trainning.point.entity.Semester;
import com.example.trainning.point.entity.User;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.repository.ISemesterRepository;
import com.example.trainning.point.repository.UserRepository;
import com.example.trainning.point.service.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EvalutionResultMapper {
    ISemesterRepository semesterRepository;
    IUserService userService;
    UserRepository userRepository;

    public EvalutionResult convertToEntity(EvalutionResultRequest request){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getName();
        var user = userService.findByEmail(email);

        Semester semester = semesterRepository.findById(request.getSemesterId()).orElseThrow(
                () -> new AppException(ErrorCode.SEMESTER_NOT_FOUND));

        return EvalutionResult.builder()
                .status(request.getStatus())
                .semester(semester)
                .user(user)
                .build();
    }

    public EvalutionResult convertToEntity(EvalutionResultRequest request, String userId){
        var user = userRepository.findById(userId).orElse(null);

        Semester semester = semesterRepository.findById(request.getSemesterId()).orElseThrow(
                () -> new AppException(ErrorCode.SEMESTER_NOT_FOUND));

        return EvalutionResult.builder()
                .status(request.getStatus())
                .semester(semester)
                .user(user)
                .build();
    }

    public EvalutionResultResponse convertToResponse(EvalutionResult entity){
        return EvalutionResultResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .counselorFeedBack(entity.getCounselorFeedBack())
                .build();
    }

    public EvalutionResultWithSemester convertToAllResult(EvalutionResult entity){
        return  EvalutionResultWithSemester.builder()
                .status(entity.getStatus())
                .mark(entity.getMark())
                .counselorFeedBack(entity.getCounselorFeedBack())
                .name(entity.getSemester().getName())
                .startDate(entity.getSemester().getStartDate())
                .endDate(entity.getSemester().getEndDate())
                .updateDate(entity.getSemester().getUpdateDate())
                .build();
    }
}


