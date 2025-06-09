package com.example.trainning.point.service.impl;

import com.example.trainning.point.dto.request.evalution.person.EvaluationPersonCategoryRequest;
import com.example.trainning.point.dto.response.evalution.person.EvaluationPersonCategoryResponse;
import com.example.trainning.point.entity.EvaluationPersonCategory;
import com.example.trainning.point.mapper.custom.EvaluationPersonCategoryMapper;
import com.example.trainning.point.repository.IEvaluationPersonCategoryRepository;
import com.example.trainning.point.repository.IEvalutionCategoryRepository;
import com.example.trainning.point.service.interfaces.IEvaluationPersonCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EvaluationPersonCategoryService implements IEvaluationPersonCategoryService {
    EvaluationPersonCategoryMapper evaluationPersonCategoryMapper;
    IEvaluationPersonCategoryRepository evaluationPersonCategoryRepository;

    @Override
    public EvaluationPersonCategoryResponse create(EvaluationPersonCategoryRequest request, String userId) {
        var entity = evaluationPersonCategoryMapper.convertToEpCategory(request , userId);
        return  evaluationPersonCategoryMapper.convertToEpCategoryResponse(evaluationPersonCategoryRepository.save(entity));
    }

    @Override
    public EvaluationPersonCategory findByUserAndSemesterAndEvCategory(String userId, Long semesterId, Long evCategoryId) {
        return evaluationPersonCategoryRepository.findBySemesterIdAndUserIdAndEvCateId(semesterId , userId , evCategoryId );
    }
}
