package com.example.trainning.point.dto.response.semester;

import com.example.trainning.point.entity.SemesterStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SemesterResponse {
    Long id;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    SemesterStatus semesterStatus;
}
