package com.example.trainning.point.dto.request.evalution.person;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
@Builder
public class EvaluationPersonCategoryRequest {
    Long semesterId;
    Long evCategoryId;
}
