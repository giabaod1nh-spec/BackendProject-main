package com.example.trainning.point.entity;

import com.example.trainning.point.enums.EvalutionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "tbl_evalution_result")
public class EvalutionResult extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "status")
    String status = EvalutionStatus.WAIT_CONFIRMATION.name();

    @Column(name = "total_mark")
    Double mark;

    @Column(name = "feedback")
    String counselorFeedBack;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "semeter_id")
    Semester semester;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
