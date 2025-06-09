package com.example.trainning.point.controller;

import com.example.trainning.point.dto.request.ApiResponse;
import com.example.trainning.point.dto.request.evalution.time.EvaluationTimeRequest;
import com.example.trainning.point.dto.request.evalution.time.EvaluationTimeUpdateRequest;
import com.example.trainning.point.dto.response.evalution.time.EvaluationTimeResponse;
import com.example.trainning.point.service.impl.EvaluationTimeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation_time")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EvaluationTimeController {
    EvaluationTimeService evaluationTimeService;
    @PostMapping("/create")
    ApiResponse<EvaluationTimeResponse> createPeriodToTakeGrade(@RequestBody EvaluationTimeRequest request){
        return ApiResponse.<EvaluationTimeResponse>builder()
                .result(evaluationTimeService.create(request))
                .build();
    }

    @PatchMapping("/update/{evaCategoryId}")
    ApiResponse<EvaluationTimeResponse> updatePeriodToTakeGrade(@RequestBody EvaluationTimeUpdateRequest request , @PathVariable Long evaCategoryId){
        return ApiResponse.<EvaluationTimeResponse>builder()
                .result(evaluationTimeService.update(request , evaCategoryId))
                .build();
    }

    @GetMapping("/student_time")
    ApiResponse<EvaluationTimeResponse> getStudentPeriodToTakeGrade(@RequestParam Long semesterId){
        return ApiResponse.<EvaluationTimeResponse>builder()
                .result(evaluationTimeService.getEvaluationTimeRoleStudent(semesterId))
                .build();
    }

    @GetMapping("/monitor_time")
    ApiResponse<EvaluationTimeResponse> getMonitorPeriodToTakeGrade(@RequestParam Long semesterId){
        return ApiResponse.<EvaluationTimeResponse>builder()
                .result(evaluationTimeService.getEvaluationTimeRoleMonitor(semesterId))
                .build();
    }

    @GetMapping("/counselor_time")
    ApiResponse<EvaluationTimeResponse> getCounselorPeriodToTakeGrade(@RequestParam Long semesterId){
        return ApiResponse.<EvaluationTimeResponse>builder()
                .result(evaluationTimeService.getEvaluationTimeRoleCounselor(semesterId))
                .build();
    }

    @GetMapping("/time_per_semester")
    ApiResponse<List<EvaluationTimeResponse>> getAllPeriodAllRole(@RequestParam Long semesterId){
        return ApiResponse.<List<EvaluationTimeResponse>>builder()
                .result(evaluationTimeService.getAllEvaluationTime(semesterId))
                .build();
    }

    @DeleteMapping("/delete/{evaluationTimeId}")
    ApiResponse<Boolean> deleteMarkingPeriod (@PathVariable Long evaluationTimeId){
        evaluationTimeService.delete(evaluationTimeId);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .build();
    }

}
