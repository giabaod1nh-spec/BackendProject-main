package com.example.trainning.point.service.impl;

import com.example.trainning.point.dto.request.evalution.standard.EvaluationStandardDetailRequest;
import com.example.trainning.point.dto.response.evalution.standard.EvalutionStandardDetailResponse;
import com.example.trainning.point.entity.EvaluationStandardDetail;
import com.example.trainning.point.mapper.custom.EvaluationStandardDetailMapper;
import com.example.trainning.point.repository.IEvaluationStandardDetailRepository;
import com.example.trainning.point.repository.IEvalutionStandardRepository;
import com.example.trainning.point.service.interfaces.IEvalutionStandardDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EvaluationStandardDetailService implements IEvalutionStandardDetailService {

    IEvaluationStandardDetailRepository evaluationStandardDetailRepository;
    EvaluationStandardDetailMapper evaluationStandardDetailMapper;

    @Override
    public EvalutionStandardDetailResponse create(EvaluationStandardDetailRequest request) {
        EvaluationStandardDetail evaluationStandardDetail = evaluationStandardDetailMapper.convertToEsDetail(request);
        return  evaluationStandardDetailMapper.convertToEsResponse(evaluationStandardDetailRepository.save(evaluationStandardDetail));
    }

    @Override
    public void deleteStandarDetail(Long esDetailId) {
        evaluationStandardDetailRepository.deleteById(esDetailId);
    }

    @Override
    public List<EvalutionStandardDetailResponse> getAll() {
        return evaluationStandardDetailRepository.findAll().stream().map(esd -> evaluationStandardDetailMapper.convertToEsResponse(esd)).toList();
    }
}
