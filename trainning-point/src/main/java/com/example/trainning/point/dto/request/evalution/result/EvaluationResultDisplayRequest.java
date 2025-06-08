package com.example.trainning.point.dto.request.evalution.result;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvaluationResultDisplayRequest {
    String userId ;
    Long semesterId;
}
