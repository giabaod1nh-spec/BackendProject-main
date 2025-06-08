package com.example.trainning.point.dto.request.user;

import com.example.trainning.point.entity.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserUpdateInfoRequest {
    String phone;
    LocalDate dob;
    Gender gender;
}
