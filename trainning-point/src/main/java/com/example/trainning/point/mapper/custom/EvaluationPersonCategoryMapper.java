package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.evalution.person.EvaluationPersonCategoryRequest;
import com.example.trainning.point.dto.response.evalution.person.EvaluationPersonCategoryResponse;
import com.example.trainning.point.entity.EvaluationPersonCategory;
import com.example.trainning.point.entity.EvalutionCategory;
import com.example.trainning.point.entity.Semester;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.repository.IEvalutionCategoryRepository;
import com.example.trainning.point.repository.ISemesterRepository;
import com.example.trainning.point.repository.UserRepository;
import com.example.trainning.point.service.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EvaluationPersonCategoryMapper {
    ISemesterRepository semesterRepository;
    IUserService userService;
    UserRepository userRepository;
    IEvalutionCategoryRepository evalutionCategoryRepository;

    public EvaluationPersonCategory convertToEpCategory(EvaluationPersonCategoryRequest request, String userId){
        var user = userRepository.findById(userId).orElse(null);

        Semester semester = semesterRepository.findById(request.getSemesterId()).orElseThrow(
                () -> new AppException(ErrorCode.SEMESTER_NOT_FOUND));

        EvalutionCategory evCategory = evalutionCategoryRepository.findById(request.getEvCategoryId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        return EvaluationPersonCategory.builder()
                .evalutionCategory(evCategory)
                .semester(semester)
                .user(user)
                .build();
    }

    public EvaluationPersonCategoryResponse convertToEpCategoryResponse(EvaluationPersonCategory entity){
        return EvaluationPersonCategoryResponse.builder()
                .studentCateScore(entity.getStudentCateScore())
                .monitorCateScore(entity.getMonitorCateScore())
                .teacherCateScore(entity.getTeacherCateScore())
                .build();
    }
}
