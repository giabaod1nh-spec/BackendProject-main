package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.request.evalution.standard.EvaluationStandardDetailRequest;
import com.example.trainning.point.dto.response.evalution.standard.EvalutionStandardDetailResponse;
import com.example.trainning.point.dto.response.evalution.standard.EvalutionStandardResponse;

import java.util.List;

public interface IEvalutionStandardDetailService {
    EvalutionStandardDetailResponse create (EvaluationStandardDetailRequest request);
    void deleteStandarDetail(Long esDetailId);
    List<EvalutionStandardDetailResponse> getAll ();
}
