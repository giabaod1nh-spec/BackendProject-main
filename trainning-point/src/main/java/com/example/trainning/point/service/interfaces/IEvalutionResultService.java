package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.request.evalution.result.EvalutionResultRequest;
import com.example.trainning.point.dto.request.semester.SemesterRequest;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultResponse;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultWithSemester;
import com.example.trainning.point.dto.response.semester.SemesterResponse;
import com.example.trainning.point.entity.EvalutionResult;
import com.example.trainning.point.entity.Semester;
import com.example.trainning.point.enums.EvalutionStatus;
import com.example.trainning.point.service.impl.EvalutionResultService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEvalutionResultService {
    EvalutionResult findEntityById(Long id);
    EvalutionResultResponse findById(Long id);
    List<EvalutionResultResponse> findAll();
    EvalutionResultResponse create(EvalutionResultRequest request, String userId);
    EvalutionResultResponse update(Long id, EvalutionResultRequest request);
    void delete(Long id);

    List<EvalutionResultWithSemester> findResultAllSemester(String userId);

    //Manage Admin Dashboard API for evaluation result all user statistics
    Long countStudentHaveExcellentStatus(Long semesterId);
    Long countStudentHaveVeryGoodStatus(Long semesterId);
    Long countStudentHaveGoodStatus(Long semesterId);
    Long countStudentHaveAverageStatus(Long semesterId);
    Long countStudentHaveBelowAverageStatus(Long semesterId);
    Long countStudentHavePoorStatus(Long semesterId);
   //Manage Admin Dashboard API for evaluation result student per class statistics
    Long countExcellentPerClass(Long semesterId , Long classId);
    Long countVeryGoodPerClass(Long semesterId , Long classId);
    Long countGoodPerClass(Long semesterId , Long classId);
    Long countAveragePerClass(Long semesterId , Long classId);
    Long countBelowAveragePerClass(Long semesterId , Long classId);
    Long countPoorPerClass(Long semesterId , Long classId);


    EvalutionResult findBySemesterAndUser(Long idSemester, String userId);
    EvalutionResult findByUser(String userId);
}
