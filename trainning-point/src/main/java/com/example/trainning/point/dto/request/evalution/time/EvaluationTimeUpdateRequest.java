package com.example.trainning.point.dto.request.evalution.time;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EvaluationTimeUpdateRequest {
    String roleName;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
