package com.example.trainning.point.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "evaluation_time")
public class EvaluationTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    Semester semester;

    @ManyToOne
    @JoinColumn(name = "role_name")
    Role role;

    @ManyToOne
    @JoinColumn(name = "marking_period_id")
    MarkingPeriod markingPeriod;

    @NotNull
    @Column(name = "start_time")
    LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time")
    LocalDateTime endTime;

}
