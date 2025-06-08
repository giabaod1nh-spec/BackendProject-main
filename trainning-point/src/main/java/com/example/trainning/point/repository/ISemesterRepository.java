package com.example.trainning.point.repository;

import com.example.trainning.point.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ISemesterRepository extends JpaRepository<Semester, Long> {
    Optional<Semester> findById(Long id);

    @Query("""
            select s from Semester s where s.status = 'ACTIVE' and CURRENT_DATE between s.startDate and s.endDate
           """)
    Optional<Semester> findActiveSemester();


    @Query("""
            select s from Semester s order by s.startDate asc
            """)
    List<Semester> findAllSortingSemester();

    @Query("""
            select s from Semester s where s.id = :semesterId and s.status = 'ACTIVE' and CURRENT_DATE between s.startDate and s.endDate
           """)
    Semester findActiveSemesterBySemesterId(Long semesterId);


}
