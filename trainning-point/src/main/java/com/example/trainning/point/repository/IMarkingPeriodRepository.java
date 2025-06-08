package com.example.trainning.point.repository;

import com.example.trainning.point.entity.MarkingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMarkingPeriodRepository extends JpaRepository<MarkingPeriod , Long> {
    MarkingPeriod findBySemesterId(Long semesterId);

    @Query("""
              select mk from MarkingPeriod mk where mk.semester.id = :semesterId
            """)
    List<MarkingPeriod> findAllBySemesterId(@Param("semesterId") Long semesterId);
}
