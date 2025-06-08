package com.example.trainning.point.dto.request.faculty;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class FacultyRequest {
    String facultyName;
}
