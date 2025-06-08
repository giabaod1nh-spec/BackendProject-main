package com.example.trainning.point.service.impl;

import com.example.trainning.point.dto.request.faculty.FacultyRequest;
import com.example.trainning.point.dto.response.faculty.FacultyResponse;
import com.example.trainning.point.entity.ClassManager;
import com.example.trainning.point.entity.Faculty;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.mapper.custom.FacultyMapper;
import com.example.trainning.point.repository.IClassManagerRepository;
import com.example.trainning.point.repository.IFacultyRepository;
import com.example.trainning.point.service.interfaces.IClassManagerService;
import com.example.trainning.point.service.interfaces.IFacultyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class FacultyService implements IFacultyService {
    IFacultyRepository facultyRepository;
    FacultyMapper facultyMapper;
    IClassManagerRepository classManagerRepository;

    @Override
    public Faculty create(FacultyRequest request) {
        Faculty faculty = facultyMapper.convertToFaculty(request);
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long facultyId) {
          facultyRepository.deleteById(facultyId);
    }

    @Override
    public Faculty findEntityById(Long facultyId) {
        var entity = facultyRepository.findById(facultyId).orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_FOUND));
        return entity;
    }

    @Override
    public List<FacultyResponse> findAll() {
        return facultyRepository.findAll().stream().map(ft -> facultyMapper.convertToFacultyResponse(ft)).toList();
    }

    @Override
    public void addClassToFaculty(String classCode, Long facultyId) {
        Faculty faculty = this.findEntityById(facultyId);

        ClassManager classManager = classManagerRepository.findByCodeClass(classCode);

        if (classManager.getFaculty() != null) {
            throw new AppException(ErrorCode.CLASS_ALREADY_ASSIGNED);
        }

        classManager.setFaculty(faculty);
        classManagerRepository.save(classManager);
    }

}
