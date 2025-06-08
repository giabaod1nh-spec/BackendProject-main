package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.request.faculty.FacultyRequest;
import com.example.trainning.point.dto.response.faculty.FacultyResponse;
import com.example.trainning.point.entity.Faculty;

import java.util.List;

public interface IFacultyService {
    Faculty create (FacultyRequest request);
    void deleteFaculty(Long id);
    Faculty findEntityById (Long facultyId);
    List<FacultyResponse> findAll();
    void addClassToFaculty(String codeClass, Long facultyId);
}
