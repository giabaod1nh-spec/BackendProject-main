package com.example.trainning.point.dto.request.evalution.standard;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EvaluationStandardDetailRequest {
    Long evaluationStandardId;
    String esDetailName;
    Double minPoint;
    Double maxPoint;
}
