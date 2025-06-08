package com.example.trainning.point.dto.request.evalution.time;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvaluationTimeRequest {
    Long semesterId;
    String roleName;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
