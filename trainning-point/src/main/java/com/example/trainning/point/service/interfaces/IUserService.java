package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.StudentResponse;
import com.example.trainning.point.dto.request.UserCreationRequest;
import com.example.trainning.point.dto.request.UserUpdateRequest;
import com.example.trainning.point.dto.request.user.*;
import com.example.trainning.point.dto.response.UserResponse;
import com.example.trainning.point.dto.response.evalution.person.UserUpdateInfoResponse;
import com.example.trainning.point.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
//    public UserResponse createUser(UserCreationRequest request);
    Long count();
    Long countByStudent(Long id );
    User findByEmail(String email);
    //User findByClassId(Long class_id);
    public UserResponse creatUser(UserRequest request);
    public UserResponse getMyInfo();
//    public UserResponse updateUser(String userId , UserUpdateRequest request);
    public UserResponse updateUser(String userId , UserRequest request);
    public UserUpdateInfoResponse updateUserInfo(String userId , UserUpdateInfoRequest request);
    UserResponse changePassword(PasswordRequest request);
    public List<UserResponse> getUsers(UserSearch userSearch);
    public List<StudentResponse> getStudents(StudentSearch studentSearch);
    Page<StudentResponse> getStudents(StudentSearch studentSearch , Pageable pageable);
    public List<StudentResponse> findByClass(Long id);
    Page<UserResponse> getUsers(UserSearch userSearch, Pageable pageable);
    public UserResponse getUser(String userId);

    void deleteUser(String id);
    void activeUser(String id);
}
