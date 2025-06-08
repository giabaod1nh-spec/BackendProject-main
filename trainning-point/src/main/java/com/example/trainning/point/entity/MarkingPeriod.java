package com.example.trainning.point.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "marking_period")
public class MarkingPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "start_date")
    LocalDate startDateMarking;

    @NotNull
    @Column(name = "end_date")
    LocalDate endDateMarking;

    @OneToOne
    @JoinColumn(name = "semester_id" , nullable = false , unique = true)
    Semester semester;
}
