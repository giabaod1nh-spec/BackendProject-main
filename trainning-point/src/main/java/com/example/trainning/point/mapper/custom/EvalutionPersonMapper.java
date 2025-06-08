package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.evalution.category.EvalutionCategoryRequest;
import com.example.trainning.point.dto.request.evalution.person.EvalutionPersonRequest;
import com.example.trainning.point.dto.response.evalution.category.EvalutionCategoryResponse;
import com.example.trainning.point.dto.response.evalution.person.EvalutionPersonResponse;
import com.example.trainning.point.dto.response.evalution.person.EvalutionResponse;
import com.example.trainning.point.dto.response.evalution.time.EvaluationTimeResponse;
import com.example.trainning.point.entity.*;
import com.example.trainning.point.enums.RoleEnum;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.repository.*;
import com.example.trainning.point.service.impl.EvaluationTimeService;
import com.example.trainning.point.service.impl.SemesterService;
import com.example.trainning.point.service.interfaces.IEvalutionStandardService;
import com.example.trainning.point.service.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EvalutionPersonMapper {
    private static final Logger log = LoggerFactory.getLogger(EvalutionPersonMapper.class);
    IEvalutionStandardRepository evalutionStandardRepository;
    IMarkingPeriodRepository markingPeriodRepository;
    IUserService userService;
    ISemesterRepository semesterRepository;
    SemesterService semesterService;
    IEvaluationTimeRepository evaluationTimeRepository;
    private final UserRepository userRepository;

    public EvalutionPerson convertToEntity(EvalutionPersonRequest request){
        EvalutionStandard standard = evalutionStandardRepository
                .findById(request.getEvalutionStandardId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        var email = authentication.getName();
//        var user = userService.findByEmail(email);

        var user = userRepository.findById(request.getUserId()).orElse(null);
        log.info("Check semester co dang ton tai hay ko ");
        Semester semester = semesterRepository.findById(request.getSemesterId()).orElseThrow(() ->
                new AppException(ErrorCode.SEMESTER_NOT_FOUND));

        var activeSemester = semesterService.getActiveSemester();

        if (semester == activeSemester){
            log.info("Check semester co dang Active hay ko ");
        } else {
           throw new AppException(ErrorCode.SEMESTER_EXPIRED);
        }

        //Phan nay phai thay EvaluationTime bang Marking Period de catch loi

          MarkingPeriod config = markingPeriodRepository.findBySemesterId(request.getSemesterId());
          if(LocalDate.now().isBefore(config.getStartDateMarking()) || LocalDate.now().isAfter(config.getEndDateMarking())){
          log.error("User marking attempted outside marking period");
               throw new AppException(ErrorCode.INVALID_TIME_MARKING);
          }

          EvaluationTime configTimeStudent = evaluationTimeRepository.getEvaluationTimeStudent(request.getSemesterId());
          EvaluationTime configTimeMonitor = evaluationTimeRepository.getEvaluationTimeMonitor(request.getSemesterId());
          EvaluationTime configTimeCounselor = evaluationTimeRepository.getEvaluationTimeCounselor(request.getSemesterId());

          if(LocalDateTime.now().isBefore(configTimeStudent.getStartTime()) || LocalDateTime.now().isAfter(configTimeStudent.getEndTime())){
            log.error("Student marking attempted outside marking period");
             throw new AppException(ErrorCode.INVALID_TIME_STUDENT_MARKING);
          }

        if(LocalDateTime.now().isBefore(configTimeMonitor.getStartTime()) || LocalDateTime.now().isAfter(configTimeMonitor.getEndTime())){
            log.error("Monitor marking attempted outside marking period");
            throw new AppException(ErrorCode.INVALID_TIME_MONITOR_MARKING);
        }

        if(LocalDateTime.now().isBefore(configTimeCounselor.getStartTime()) || LocalDateTime.now().isAfter(configTimeCounselor.getEndTime())){
            log.error("Counselor marking attempted outside marking period");
            throw new AppException(ErrorCode.INVALID_TIME_COUNSELOR_MARKING);
        }

        EvalutionPerson evalutionPerson =  EvalutionPerson.builder()
                .studentScore(request.getStudentScore())
                .monitorScore(request.getMonitorScore())
                .teacherScore(null)
                .evalutionStandard(standard)
                .user(user)
                .semester(activeSemester)
                .build();

        Set<Role> roles = user.getRoles();
        for (var it: roles){
            if(it.getName().equals(RoleEnum.STUDENT.name())){
                evalutionPerson.setMonitorScore(null);
            }
        }
        return evalutionPerson;
    }

    public EvalutionPersonResponse convertToResponse(EvalutionPerson entity){

        if(entity == null || entity.getId() == null){
            return EvalutionPersonResponse.builder()
                    .id(null)
                    .studentScore(0.0)
                    .monitorScore(0.0)
                    .teacherScore(0.0)
                    .evalutionStandardId(null)
                    .userId(null)
                    .build();
        }

        Long evalutionStandardId = (entity.getEvalutionStandard() != null)
                ? entity.getEvalutionStandard().getId()
                : null;



        return EvalutionPersonResponse.builder()
                .id(entity.getId())
                .studentScore(entity.getStudentScore())
                .monitorScore(entity.getMonitorScore())
                .teacherScore(entity.getTeacherScore())
                .evalutionStandardId(evalutionStandardId)
                .userId(entity.getUser().getUserId())
                .build();
    }


}
