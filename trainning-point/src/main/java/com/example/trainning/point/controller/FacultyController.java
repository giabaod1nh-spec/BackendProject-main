package com.example.trainning.point.controller;

import com.example.trainning.point.dto.request.ApiResponse;
import com.example.trainning.point.dto.request.faculty.AddClassRequest;
import com.example.trainning.point.dto.request.faculty.FacultyRequest;
import com.example.trainning.point.dto.response.faculty.FacultyResponse;
import com.example.trainning.point.entity.Faculty;
import com.example.trainning.point.service.impl.FacultyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class FacultyController {
    FacultyService facultyService;

    @PostMapping("/create")
    ApiResponse<Faculty> createFaculty (@RequestBody FacultyRequest request){
        return ApiResponse.<Faculty>builder()
                .result(facultyService.create(request))
                .build();
    }

    @GetMapping("/list")
    ApiResponse<List<FacultyResponse>> listFaculty(){
        return ApiResponse.<List<FacultyResponse>>builder()
                .result(facultyService.findAll())
                .build();
    }


    @PostMapping("/add_class")
     ApiResponse<Boolean> addFacultyToClass(@RequestBody AddClassRequest request){
        facultyService.addClassToFaculty(request.getClassCode(), request.getFacultyId());
        return  ApiResponse.<Boolean>builder()
                .result(true)
                .build();

    }


    @DeleteMapping("/delete/{facultyId}")
    ApiResponse<Boolean> deleteFaculty (@PathVariable Long facultyId ){
        facultyService.deleteFaculty(facultyId);
        return  ApiResponse.<Boolean>builder()
                .result(true)
                .build();
    }
}
