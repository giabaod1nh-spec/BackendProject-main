package com.example.trainning.point.dto.response.faculty;

import com.example.trainning.point.dto.response.classes.ClassResponse;
import com.example.trainning.point.entity.ClassManager;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class FacultyResponse {
    Long id ;
    String facultyName;
    List<ClassResponse> classManagers;
}
