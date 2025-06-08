package com.example.trainning.point.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long facultyId ;

    @Column(name = "faculty_name" , nullable = false)
    String facultyName;

    @JsonManagedReference
    @OneToMany(mappedBy = "faculty")
    List<ClassManager> classManagers;

}
