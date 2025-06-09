package com.example.trainning.point.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

@Table(name = "evaluation_person_category",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "evaluation_category_id", "semesterId"})
        })
public class EvaluationPersonCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long personCateId;

    @Column(name = "student_cate_score")
    Double studentCateScore;

    @Column(name = "monitor_cate_score")
    Double monitorCateScore;

    @Column(name = "teacher_cate_score")
    Double teacherCateScore;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @JoinColumn(name = "evaluation_category_id")
    EvalutionCategory evalutionCategory;

    @ManyToOne
    @JoinColumn(name = "semesterId")
    Semester semester;

}
