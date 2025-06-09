package com.example.trainning.point.dto;

import com.example.trainning.point.dto.response.RoleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {
    String userId;
    String fullName;
    String code;
    String className;
    String corhort;
    String codeClass;
    Set<RoleResponse> roles;
    String roleNames;
    String gender;
    String email;
    String phone;
    //Them vao cac truong
    String email2;
    String ethnic;
    String address;
    String facultyName;
}
