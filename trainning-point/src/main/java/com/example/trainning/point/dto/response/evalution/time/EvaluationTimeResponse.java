package com.example.trainning.point.dto.response.evalution.time;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvaluationTimeResponse {
    String roleName;
    String semesterName;
    Long semesterId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDate startMarkingDate;
    LocalDate endMarkingDate;
}
