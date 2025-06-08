package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.semester.SemesterRequest;
import com.example.trainning.point.dto.response.semester.SemesterResponse;
import com.example.trainning.point.entity.Semester;
import com.example.trainning.point.entity.SemesterStatus;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.repository.ISemesterRepository;
import com.example.trainning.point.service.impl.SemesterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SemesterMapper {
    ISemesterRepository semesterRepository;
    public Semester convertToEntity(SemesterRequest request){
        var activeSemester = semesterRepository.findActiveSemester();
        if (request.getStartDate().isAfter(activeSemester.get().getStartDate()) && request.getStartDate().isBefore(activeSemester.get().getEndDate())){
            throw new AppException(ErrorCode.INVALID_TIME_SEMESTER);
        }else{

        }
        return Semester.builder()
                .status(SemesterStatus.valueOf("PENDING"))
                .name(request.getName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    public SemesterResponse convertToResponse(Semester entity){
        return SemesterResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .semesterStatus(entity.getStatus())

                .build();
    }
}
