package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.request.evalution.person.EvaluationPersonCategoryRequest;
import com.example.trainning.point.dto.response.evalution.person.EvaluationPersonCategoryResponse;
import com.example.trainning.point.entity.EvaluationPersonCategory;

public interface IEvaluationPersonCategoryService {
    EvaluationPersonCategoryResponse create(EvaluationPersonCategoryRequest request , String userId);
    EvaluationPersonCategory findByUserAndSemesterAndEvCategory(String userId , Long classId , Long evCategoryId);
}
