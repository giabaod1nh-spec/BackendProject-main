package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.evalution.periodmarking.MarkingPeriodRequest;
import com.example.trainning.point.dto.response.periodmarking.MarkingPeriodResponse;
import com.example.trainning.point.entity.MarkingPeriod;
import com.example.trainning.point.entity.Semester;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.repository.ISemesterRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class MarkingPeriodMapper {
    ISemesterRepository semesterRepository;
    public MarkingPeriod convertToMarkingPeriod(MarkingPeriodRequest request){
        //Check xem time có available trong học kì ko
        Semester semester = semesterRepository.findActiveSemesterBySemesterId(request.getSemesterId());
        if(semester == null){
            throw new AppException(ErrorCode.INVALID_SEMESTER);
        }
        if (request.getStartDateMarking().isBefore(semester.getStartDate()) || request.getEndDateMarking().isAfter(semester.getEndDate())){
             throw new AppException(ErrorCode.INVALID_PERIOD_TIME);
            }

        return MarkingPeriod.builder()
                .startDateMarking(request.getStartDateMarking())
                .endDateMarking(request.getEndDateMarking())
                .semester(semesterRepository.findActiveSemesterBySemesterId(request.getSemesterId()))
                .build();
    }

    public MarkingPeriodResponse converToMarkingPeriodResponse(MarkingPeriod entity){
        return MarkingPeriodResponse.builder()
                .startDateMarking(entity.getStartDateMarking())
                .endDateMarking(entity.getEndDateMarking())
                .semesterId(entity.getSemester().getId())
                .semesterName(entity.getSemester().getName())
                .build();
    }
}
