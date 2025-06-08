package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.StudentResponse;
import com.example.trainning.point.dto.request.user.UserRequest;
import com.example.trainning.point.dto.request.user.UserUpdateInfoRequest;
import com.example.trainning.point.dto.response.RoleResponse;
import com.example.trainning.point.dto.response.UserResponse;
import com.example.trainning.point.dto.response.evalution.person.UserUpdateInfoResponse;
import com.example.trainning.point.entity.*;
import com.example.trainning.point.mapper.RoleMapper;
import com.example.trainning.point.service.impl.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserMapperCustom {
    PasswordEncoder passwordEncoder;
    RoleService roleService;
    RoleMapper roleMapper;

    public User convertToEntity(UserRequest request){
        Set<Role> roles = new HashSet<>();
        if (request.getRoleNames() != null){
            for(var it: request.getRoleNames()){
                Role role = roleService.findEntityByName(it);
                if (role != null)
                    roles.add(role);
            }
        }

        ClassManager classManager = ClassManager.builder()
                .id(request.getId())
                .build();

        return User.builder()
                .code(request.getCode())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .dob(request.getDob())
                .phone(request.getPhone())
                .gender(request.getGender())
                .roles(roles)
                .classManager(classManager)
                .build();
    }

    public UserResponse convertToResponse(User user){
        String resultEvalu = "WAIT_CONFIRMATION";
        try {
            resultEvalu = user.getEvalutionResultList().get(0).getStatus();
        }catch (Exception e){
            resultEvalu = "WAIT_CONFIRMATION";
        }

        Set<RoleResponse> roleResponseList = new HashSet<>();
        String roleNames = "";
        if (user.getRoles() != null){
            for (var it: user.getRoles()){
                roleResponseList.add(roleMapper.toRoleResponse(it));

                if(roleNames.length() != 0)
                    roleNames += ", ";
                roleNames += it.getName();
            }
        }

        String className = "";
        String corhort = "";
        Long classId = null;
        String gender = null;
        Double mark = null;

        if(user.getClassManager() != null){
            classId = user.getClassManager().getId();
            corhort = user.getClassManager().getAcademicCohort();
            className = user.getClassManager().getName();
        }

        if(user.getGender() != null)
            gender = user.getGender().toString();

        return UserResponse.builder()
                .code(user.getCode())
                .userId(user.getUserId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .dob(user.getDob())
                .phone(user.getPhone())
                .gender(gender)
                .roles(roleResponseList)
                .isDelete(user.getIsDelete())
                .evaluResult(resultEvalu)

                .classId(classId)
                .corhort(corhort)
                .className(className)
                .roleNames(roleNames)
                .build();
    }

    public StudentResponse convertToStudentResponse(User user){

        String className = "";
        String corhort = "";
        String codeClass = "";
        Long classId = null;
        String gender = null;

        Set<RoleResponse> roleResponseList = new HashSet<>();
        String roleNames = "";
        if (user.getRoles() != null){
            for (var it: user.getRoles()){
                roleResponseList.add(roleMapper.toRoleResponse(it));

                if(roleNames.length() != 0)
                    roleNames += ", ";
                roleNames += it.getName();
            }
        }

        if(user.getClassManager() != null){
            classId = user.getClassManager().getId();
            corhort = user.getClassManager().getAcademicCohort();
            className = user.getClassManager().getName();
            codeClass = user.getClassManager().getCodeClass();
        }

        if(user.getGender() != null)
            gender = user.getGender().toString();

        return StudentResponse.builder()
                .userId(user.getUserId())
                .className(className)
                .roleNames(roleNames)
                .gender(gender)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .code(user.getCode())
                .corhort(corhort)
                .codeClass(codeClass)
                .phone(user.getPhone())
                .build();
    }


    public User convertToInfoResponse(UserUpdateInfoRequest request){
        return User.builder()
                .dob(request.getDob())
                .phone(request.getPhone())
                .gender(request.getGender())
                .build();
    }


    public UserUpdateInfoResponse convertToInfoResponse(User user){
        String className = "";
        String corhort = "";
        Long classId = null;
        String gender = null;

        if(user.getClassManager() != null){
            classId = user.getClassManager().getId();
            corhort = user.getClassManager().getAcademicCohort();
            className = user.getClassManager().getName();
        }

        if(user.getGender() != null)
            gender = user.getGender().toString();

        return UserUpdateInfoResponse.builder()
                .code(user.getCode())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .dob(user.getDob())
                .phone(user.getPhone())
                .gender(gender)
                .corhort(corhort)
                .className(className)
                .build();
    }

}
