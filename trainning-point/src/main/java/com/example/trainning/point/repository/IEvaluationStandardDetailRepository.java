package com.example.trainning.point.repository;

import com.example.trainning.point.entity.EvaluationStandardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvaluationStandardDetailRepository extends JpaRepository<EvaluationStandardDetail , Long> {
}
