package com.example.trainning.point.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String userId;

    @Column(name = "code", unique = true)
    String code;
    @Column(name = "email", unique = true)
    String email;

    String email2;
    String address;
    String ethnic;

    String password;
    String fullName;
    LocalDate dob;
    String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ManyToMany
    Set<Role> roles;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    Admin admin;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "class_id")
    ClassManager classManager;
    @OneToMany(mappedBy = "user")
    List<EvalutionPerson> evalutionPersonList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    List<EvalutionResult> evalutionResultList;

    public User(String code, String email, String password, String fullName){
        this.code = code;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }
}
