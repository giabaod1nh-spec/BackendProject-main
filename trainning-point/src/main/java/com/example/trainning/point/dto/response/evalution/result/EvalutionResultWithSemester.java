package com.example.trainning.point.dto.response.evalution.result;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvalutionResultWithSemester {
    String status ;
    Double mark ;
    String name;
    String counselorFeedBack;
    LocalDate startDate;
    LocalDate endDate;
    LocalDateTime updateDate;
}
