package com.example.trainning.point.dto.response.evalution.person;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EvaluationPersonCategoryResponse {
    Double studentCateScore;
    Double monitorCateScore;
    Double teacherCateScore;
}
