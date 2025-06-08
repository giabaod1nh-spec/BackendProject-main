package com.example.trainning.point.controller;

import com.example.trainning.point.dto.request.ApiResponse;
import com.example.trainning.point.dto.request.evalution.periodmarking.MarkingPeriodRequest;
import com.example.trainning.point.dto.response.periodmarking.MarkingPeriodResponse;
import com.example.trainning.point.service.impl.MarkingPeriodService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequestMapping("/marking_period")
public class MarkingPeriodController {
    MarkingPeriodService markingPeriodService;

    @PostMapping("/create_marking_period")
    ApiResponse<MarkingPeriodResponse> createMarkingPeriod(@RequestBody MarkingPeriodRequest request){
        return ApiResponse.<MarkingPeriodResponse>builder()
                .result(markingPeriodService.createMarkingPeriod(request))
                .build();
    }

    @GetMapping("/all_period")
    ApiResponse<List<MarkingPeriodResponse>> getAll(){
        return ApiResponse.<List<MarkingPeriodResponse>>builder()
                .result(markingPeriodService.getAllMarkingPeriod())
                .build();
    }
}
