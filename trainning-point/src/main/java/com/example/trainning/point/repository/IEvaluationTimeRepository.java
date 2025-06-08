package com.example.trainning.point.repository;

import com.example.trainning.point.entity.EvaluationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEvaluationTimeRepository extends JpaRepository<EvaluationTime , Long> {

    @Query("""
              select et from EvaluationTime et where et.role.name = 'STUDENT' and et.semester.id = :semesterId
              and et.markingPeriod.semester.id = :semesterId
            """)
    EvaluationTime getEvaluationTimeStudent(@Param("semesterId") Long semesterId);


    @Query("""
              select et from EvaluationTime et where et.role.name = 'COMMITTEE' and et.semester.id = :semesterId
            """)
    EvaluationTime getEvaluationTimeMonitor(@Param("semesterId") Long semesterId);


    @Query("""
              select et from EvaluationTime et where et.role.name = 'COUNSELOR' and et.semester.id = :semesterId
            """)
    EvaluationTime getEvaluationTimeCounselor(@Param("semesterId") Long semesterId);


    @Query("""
              select et from EvaluationTime et where et.semester.id = :semesterId
            """)
    List<EvaluationTime> getAllEvaluationTime(@Param("semesterId") Long semesterId);

}
