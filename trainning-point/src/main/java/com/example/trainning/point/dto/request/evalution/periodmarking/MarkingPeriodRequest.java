package com.example.trainning.point.dto.request.evalution.periodmarking;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MarkingPeriodRequest {
    Long semesterId;
    LocalDate startDateMarking;
    LocalDate endDateMarking;
}
