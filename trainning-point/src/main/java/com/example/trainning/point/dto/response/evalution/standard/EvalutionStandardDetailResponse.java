package com.example.trainning.point.dto.response.evalution.standard;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EvalutionStandardDetailResponse {
    Long esDetailId;
    String esDetailName;
    Double minPoint;
    Double maxPoint;
    Long evaluationStandardId;
    String evaluationStandardName;
}
