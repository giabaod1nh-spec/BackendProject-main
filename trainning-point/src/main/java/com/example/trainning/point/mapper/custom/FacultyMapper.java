package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.faculty.FacultyRequest;
import com.example.trainning.point.dto.response.faculty.FacultyResponse;
import com.example.trainning.point.entity.Faculty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class FacultyMapper {
    ClassMapper classMapper;
    public Faculty convertToFaculty (FacultyRequest request){
        return Faculty.builder()
                .facultyName(request.getFacultyName())
                .build();
    }

    public FacultyResponse convertToFacultyResponse (Faculty faculty){
        return FacultyResponse.builder()
                .id(faculty.getFacultyId())
                .facultyName(faculty.getFacultyName())
                .classManagers(faculty.getClassManagers().stream().map(classMapper::convertToResponse).toList())
                .build();
    }

}