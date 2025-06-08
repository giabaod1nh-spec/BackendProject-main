package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.request.evalution.time.EvaluationTimeRequest;
import com.example.trainning.point.dto.response.evalution.time.EvaluationTimeResponse;
import com.example.trainning.point.service.impl.EvaluationTimeService;

import java.util.List;

public interface IEvaluationTimeService {
   EvaluationTimeResponse create (EvaluationTimeRequest request);
   //EvaluationTimeResponse update (EvaluationTimeRequest request);
   EvaluationTimeResponse getEvaluationTimeRoleStudent (Long semesterId);
   EvaluationTimeResponse getEvaluationTimeRoleMonitor (Long semesterId);
   EvaluationTimeResponse getEvaluationTimeRoleCounselor (Long semesterId);
   List<EvaluationTimeResponse> getAllEvaluationTime (Long semesterId);
}
