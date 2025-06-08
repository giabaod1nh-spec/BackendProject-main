package com.example.trainning.point.dto.request.faculty;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class AddClassRequest {
    String classCode;
    Long facultyId;
}
