package com.example.trainning.point.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EvaluationStandardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long esDetailId;

    @NotNull
    String esDetailName;


    @Column(name = "min_point")
    Double minPoint;

    @Column(name = "max_point")
    Double maxPoint;

    @ManyToOne
    @JoinColumn(name = "evaluation_standard_id")
    EvalutionStandard evalutionStandard;
}
