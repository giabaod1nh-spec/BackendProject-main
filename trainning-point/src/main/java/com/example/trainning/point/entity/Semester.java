package com.example.trainning.point.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Semester extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @NotNull
    @Column(name = "start_date")
    LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    LocalDate endDate;

    @OneToMany(mappedBy = "semester")
    List<EvalutionPerson> evalutionPersonList;

    @OneToMany(mappedBy = "semester")
    List<EvalutionResult> evalutionResultList;

    @Enumerated(EnumType.STRING) // Store as STRING in the database
    @Column(name = "status")
    SemesterStatus status;

    public boolean isActive() {
        return status.isActive() && LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate.plusDays(1));
    }

    public boolean isExpired() {
        return status.isExpired() || LocalDate.now().isAfter(endDate);
    }
}
