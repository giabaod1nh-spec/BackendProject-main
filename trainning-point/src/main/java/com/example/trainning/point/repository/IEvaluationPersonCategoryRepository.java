package com.example.trainning.point.repository;

import com.example.trainning.point.entity.EvaluationPersonCategory;
import com.example.trainning.point.entity.EvalutionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvaluationPersonCategoryRepository extends JpaRepository<EvaluationPersonCategory, Long> {
    @Query("""
                select epc from EvaluationPersonCategory epc where epc.semester.id = :semesterId 
                and epc.user.userId = :userId and epc.evalutionCategory.id = :evCategoryId
            """)
    EvaluationPersonCategory findBySemesterIdAndUserIdAndEvCateId(@Param("semesterId") Long semesterId,
                                                         @Param("userId") String userId,
                                                         @Param("evCategoryId") Long evCategoryId);
}

