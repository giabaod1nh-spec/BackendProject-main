package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.evalution.time.EvaluationTimeRequest;
import com.example.trainning.point.dto.response.evalution.time.EvaluationTimeResponse;
import com.example.trainning.point.entity.EvaluationTime;
import com.example.trainning.point.entity.MarkingPeriod;
import com.example.trainning.point.entity.Semester;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.repository.IEvaluationTimeRepository;
import com.example.trainning.point.repository.IMarkingPeriodRepository;
import com.example.trainning.point.repository.ISemesterRepository;
import com.example.trainning.point.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EvaluationTimeMapper {
    RoleRepository roleRepository;
    IMarkingPeriodRepository markingPeriodRepository;
    ISemesterRepository semesterRepository;
    public EvaluationTime convertToEvaluationTime(EvaluationTimeRequest request){

        //Phan nay phai dua Marking Period vao
        MarkingPeriod mk = markingPeriodRepository.findBySemesterId(request.getSemesterId());

        if (request.getStartTime().isBefore(mk.getStartDateMarking().atStartOfDay()) || request.getEndTime().isAfter(mk.getEndDateMarking().atStartOfDay())){
               throw new AppException(ErrorCode.INVALID_PERIOD_TIME_PER_ROLE);
        }

        return EvaluationTime.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .role(roleRepository.findByName(request.getRoleName()))
                .semester(semesterRepository.findActiveSemesterBySemesterId(request.getSemesterId()))
                .markingPeriod(markingPeriodRepository.findBySemesterId(request.getSemesterId()))
                .build();
    }

    public EvaluationTimeResponse convertToEvaluationResponse(EvaluationTime entity){
        return EvaluationTimeResponse.builder()
                .evaluationTimeId(entity.getId())
                .semesterName(entity.getSemester().getName())
                .semesterId(entity.getSemester().getId())
                .roleName(entity.getRole().getName())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .startMarkingDate(entity.getMarkingPeriod().getStartDateMarking())
                .endMarkingDate(entity.getMarkingPeriod().getEndDateMarking())
                .build();
    }
}
