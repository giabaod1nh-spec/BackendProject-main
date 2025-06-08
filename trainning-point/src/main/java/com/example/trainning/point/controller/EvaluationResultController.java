package com.example.trainning.point.controller;

import com.example.trainning.point.dto.request.ApiResponse;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultResponse;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultWithSemester;
import com.example.trainning.point.entity.EvalutionResult;
import com.example.trainning.point.service.impl.EvalutionResultService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation/result")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class    EvaluationResultController {
    EvalutionResultService evalutionResultService;

    @GetMapping("result_per_semester")
    public ResponseEntity<EvalutionResult> getEvalutionResult(@RequestParam(name = "semesterId") Long semesterId , @RequestParam(name = "userId") String userId){
               EvalutionResult result = evalutionResultService.findBySemesterAndUser(semesterId , userId);
               return  ResponseEntity.ok(result);
    }
    @GetMapping("result_all_semester/{id}")
    public ResponseEntity<List<EvalutionResultWithSemester>> getEvalutionResultAll(@PathVariable("id") String userId){
        List<EvalutionResultWithSemester> result = evalutionResultService.findResultAllSemester(userId);
        return ResponseEntity.ok(result);
    }

    //Statictis for All Student
    @GetMapping("excellent_count")
    public ApiResponse<Long> getStudentHaveExcellentResult(@RequestParam(name = "semesterId") Long semesterId){
          return ApiResponse.<Long>builder()
                  .result(evalutionResultService.countStudentHaveExcellentStatus(semesterId))
                  .build();
    }

    @GetMapping("verygood_count")
    public ApiResponse<Long> getStudentHaveVeryGoodResult(@RequestParam(name = "semesterId") Long semesterId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countStudentHaveVeryGoodStatus(semesterId))
                .build();
    }

    @GetMapping("good_count")
    public ApiResponse<Long> getStudentHaveGoodResult(@RequestParam(name = "semesterId") Long semesterId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countStudentHaveGoodStatus(semesterId))
                .build();
    }

    @GetMapping("average_count")
    public ApiResponse<Long> getStudentHaveAverageResult(@RequestParam(name = "semesterId") Long semesterId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countStudentHaveAverageStatus(semesterId))
                .build();
    }

    @GetMapping("belowaverage_count")
    public ApiResponse<Long> getStudentHaveBelowAverageResult(@RequestParam(name = "semesterId") Long semesterId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countStudentHaveBelowAverageStatus(semesterId))
                .build();
    }

    @GetMapping("poor_count")
    public ApiResponse<Long> getStudentHavePoorResult(@RequestParam(name = "semesterId") Long semesterId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countStudentHavePoorStatus(semesterId))
                .build();
    }

    //Statistics for all student per class

    @GetMapping("excellent_per_class")
    public ApiResponse<Long> getExcellentPerClass(@RequestParam(name = "semesterId" ) Long semesterId,
                                             @RequestParam(name = "classId") Long classId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countExcellentPerClass(semesterId, classId))
                .build();
    }


    @GetMapping("verygood_per_class")
    public ApiResponse<Long> getVeryGoodPerClass(@RequestParam(name = "semesterId" ) Long semesterId,
                                             @RequestParam(name = "classId") Long classId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countVeryGoodPerClass(semesterId, classId))
                .build();
    }

    @GetMapping("good_per_class")
    public ApiResponse<Long> getGoodPerClass(@RequestParam(name = "semesterId" ) Long semesterId,
                                             @RequestParam(name = "classId") Long classId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countGoodPerClass(semesterId, classId))
                .build();
    }

    @GetMapping("average_per_class")
    public ApiResponse<Long> getAveragePerClass(@RequestParam(name = "semesterId" ) Long semesterId,
                                                @RequestParam(name = "classId") Long classId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countAveragePerClass(semesterId, classId))
                .build();
    }

    @GetMapping("belowaverage_per_class")
    public ApiResponse<Long> getBelowAveragePerClass(@RequestParam(name = "semesterId" ) Long semesterId,
                                                     @RequestParam(name = "classId") Long classId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countBelowAveragePerClass(semesterId, classId))
                .build();
    }

    @GetMapping("poor_per_class")
    public ApiResponse<Long> getPoorPerClass(@RequestParam(name = "semesterId" ) Long semesterId,
                                             @RequestParam(name = "classId") Long classId){
        return ApiResponse.<Long>builder()
                .result(evalutionResultService.countPoorPerClass(semesterId, classId))
                .build();
    }
}
