package com.example.trainning.point.repository;

import com.example.trainning.point.dto.response.evalution.result.EvalutionResultResponse;
import com.example.trainning.point.entity.EvalutionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEvalutionResultRepository extends JpaRepository<EvalutionResult, Long> {

    @Query("""
                select ev from EvalutionResult ev where ev.semester.id = :semesterId 
                and ev.user.userId = :userId
            """)
    EvalutionResult findBySemesterIdAndUserId(@Param("semesterId") Long semesterId,
                                              @Param("userId") String userId);

    @Query("select ev from EvalutionResult ev where ev.user.userId = :idUser")
    EvalutionResult findByUser(@Param("idUser") String userId);

    @Query("select ev from EvalutionResult ev join ev.semester where ev.user.userId = :idUser order by ev.createDate desc")
    List<EvalutionResult> findResultAllSemester(@Param("idUser") String userId);


    //For Student in all class
    @Query("""
            select ev from EvalutionResult ev left join ev.user us
            where us.classManager.id = :classId and ev.semester.id = :semesterId
            """)
List<EvalutionResult> findResultPerClassSemester(@Param("classId") Long classId ,
                                                 @Param("semesterId") Long semesterId);

    @Query("""
            select count(ev) from EvalutionResult ev where ev.mark > 90 and ev.semester.id = :semesterId
            """)
    Long countByStudentHaveExcellentStatus (@Param("semesterId") Long semesterId);

    @Query("""
            select count(ev) from EvalutionResult ev where ev.mark >= 80 and ev.mark < 90 and ev.semester.id = :semesterId
            """)
    Long countByStudentHaveVeryGoodStatus (@Param("semesterId") Long semesterId);

    @Query("""
            select count(ev) from EvalutionResult ev where ev.mark >= 65 and ev.mark < 80 and ev.semester.id = :semesterId
            """)
    Long countByStudentHaveGoodStatus (@Param("semesterId") Long semesterId);

    @Query("""
            select count(ev) from EvalutionResult ev where ev.mark >= 50 and ev.mark < 65 and ev.semester.id = :semesterId
            """)
    Long countByStudentHaveAverageStatus (@Param("semesterId") Long semesterId);

    @Query("""
            select count(ev) from EvalutionResult ev where ev.mark >= 35 and ev.mark < 50 and ev.semester.id = :semesterId
            """)
    Long countByStudentHaveBelowAverageStatus (@Param("semesterId") Long semesterId);

    @Query("""
            select count(ev) from EvalutionResult ev where ev.mark < 35 and ev.semester.id = :semesterId
            """)
    Long countByStudentHavePoorStatus (@Param("semesterId") Long semesterId);

    //For student per class

    @Query("""
            select count(ev) from EvalutionResult ev left join ev.user us
            where us.classManager.id = :classId and ev.mark > 90 and ev.semester.id = :semesterId
            """)
    Long countByExcellentPerClass(@Param("classId") Long classId,
                                  @Param("semesterId") Long semesterId);



    @Query("""
            select count(ev) from EvalutionResult ev left join ev.user us
            where us.classManager.id = :classId and ev.mark >= 80 and ev.mark < 90 and ev.semester.id = :semesterId
            """)
    Long countByVeryGoodPerClass(@Param("classId") Long classId,
                                  @Param("semesterId") Long semesterId);

    @Query("""
            select count(ev) from EvalutionResult ev left join ev.user us
            where us.classManager.id = :classId and ev.mark >= 65 and ev.mark < 80 and ev.semester.id = :semesterId
            """)
    Long countByGoodPerClass(@Param("classId") Long classId,
                                  @Param("semesterId") Long semesterId);


    @Query("""
            select count(ev) from EvalutionResult ev left join ev.user us
            where us.classManager.id = :classId and ev.mark >= 50 and ev.mark < 65 and ev.semester.id = :semesterId
            """)
    Long countByAveragePerClass(@Param("classId") Long classId,
                                  @Param("semesterId") Long semesterId);


    @Query("""
            select count(ev) from EvalutionResult ev left join ev.user us
            where us.classManager.id = :classId and ev.mark >= 35 and ev.mark < 50 and ev.semester.id = :semesterId
            """)
    Long countByBelowAveragePerClass(@Param("classId") Long classId,
                                  @Param("semesterId") Long semesterId);


    @Query("""
            select count(ev) from EvalutionResult ev left join ev.user us
            where us.classManager.id = :classId and ev.mark < 35 and ev.semester.id = :semesterId
            """)
    Long countByPoorPerClass(@Param("classId") Long classId,
                                  @Param("semesterId") Long semesterId);



}
