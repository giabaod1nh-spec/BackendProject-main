package com.example.trainning.point.dto.request.user;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSearch {
    String keySearch;
    Boolean isDelete;
    String codeClass;
    String roleName;
}
