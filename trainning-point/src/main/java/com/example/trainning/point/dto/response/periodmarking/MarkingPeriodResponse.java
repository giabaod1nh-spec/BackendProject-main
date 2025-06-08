package com.example.trainning.point.dto.response.periodmarking;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MarkingPeriodResponse {
    Long semesterId;
    LocalDate startDateMarking;
    LocalDate endDateMarking;
    String semesterName;
}
