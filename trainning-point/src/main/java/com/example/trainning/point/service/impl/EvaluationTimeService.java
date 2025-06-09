package com.example.trainning.point.service.impl;

import com.example.trainning.point.dto.request.evalution.time.EvaluationTimeRequest;
import com.example.trainning.point.dto.request.evalution.time.EvaluationTimeUpdateRequest;
import com.example.trainning.point.dto.response.evalution.time.EvaluationTimeResponse;
import com.example.trainning.point.entity.EvaluationTime;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.mapper.custom.EvaluationTimeMapper;
import com.example.trainning.point.repository.IEvaluationTimeRepository;
import com.example.trainning.point.service.interfaces.IEvaluationTimeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DialectOverride;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EvaluationTimeService implements IEvaluationTimeService {
    EvaluationTimeMapper evaluationTimeMapper;
    IEvaluationTimeRepository evaluationTimeRepository;
    @Override
    public EvaluationTimeResponse create(EvaluationTimeRequest request) {
        EvaluationTime entity = evaluationTimeMapper.convertToEvaluationTime(request);
        return evaluationTimeMapper.convertToEvaluationResponse(evaluationTimeRepository.save(entity));
    }

    @Override
    public EvaluationTimeResponse update(EvaluationTimeUpdateRequest request , Long evaCategoryId) {
        EvaluationTime evaluationTime = evaluationTimeRepository.findById(evaCategoryId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        evaluationTime.setStartTime(request.getStartTime());
        evaluationTime.setEndTime(request.getEndTime());
        return evaluationTimeMapper.convertToEvaluationResponse(evaluationTimeRepository.save(evaluationTime));
    }

    @Override
    public  EvaluationTimeResponse getEvaluationTimeRoleStudent(Long semesterId){
        return  evaluationTimeMapper.convertToEvaluationResponse(evaluationTimeRepository.getEvaluationTimeStudent(semesterId));
    }

    @Override
    public EvaluationTimeResponse getEvaluationTimeRoleMonitor(Long semesterId){
        return evaluationTimeMapper.convertToEvaluationResponse(evaluationTimeRepository.getEvaluationTimeMonitor(semesterId));
    }

    @Override
    public  EvaluationTimeResponse getEvaluationTimeRoleCounselor(Long semesterId){
        return evaluationTimeMapper.convertToEvaluationResponse(evaluationTimeRepository.getEvaluationTimeCounselor(semesterId));
    }

    @Override
    public List<EvaluationTimeResponse> getAllEvaluationTime (Long semesterId){
        return evaluationTimeRepository.getAllEvaluationTime(semesterId).stream().map(evaluationTimeMapper::convertToEvaluationResponse).toList();
    }

    @Override
    public void delete(Long evaluationTimeId) {
        evaluationTimeRepository.deleteById(evaluationTimeId);
    }

}
