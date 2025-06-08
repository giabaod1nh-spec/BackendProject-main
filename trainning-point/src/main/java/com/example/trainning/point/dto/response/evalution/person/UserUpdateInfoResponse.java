package com.example.trainning.point.dto.response.evalution.person;

import com.example.trainning.point.dto.response.RoleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserUpdateInfoResponse {
    String code;
    String fullName;
    LocalDate dob;
    String email;
    String phone;
    String gender;
    String className;
    String corhort;
}
