package com.example.trainning.point.controller;

import com.example.trainning.point.dto.request.ApiResponse;
import com.example.trainning.point.dto.request.evalution.standard.EvaluationStandardDetailRequest;
import com.example.trainning.point.dto.response.evalution.standard.EvalutionStandardDetailResponse;
import com.example.trainning.point.entity.EvaluationStandardDetail;
import com.example.trainning.point.service.impl.EvaluationStandardDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/standard_detail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class ESDetailController {

    EvaluationStandardDetailService evaluationStandardDetailService;

    @GetMapping("/list")
    ApiResponse<List<EvalutionStandardDetailResponse>> getAll(){
        return  ApiResponse.<List<EvalutionStandardDetailResponse>>builder()
                .result(evaluationStandardDetailService.getAll())
                .build();
    }


    @PostMapping("/create")
    ApiResponse<EvalutionStandardDetailResponse> createESDetail (@RequestBody EvaluationStandardDetailRequest request){
        return ApiResponse.<EvalutionStandardDetailResponse>builder()
                .result(evaluationStandardDetailService.create(request))
                .build();
    }

    @DeleteMapping("/delete/{esDetailId}")
    ApiResponse<Boolean> deleteESDetail (@PathVariable  Long esDetailId){
        evaluationStandardDetailService.deleteStandarDetail(esDetailId);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .build();
    }
}
