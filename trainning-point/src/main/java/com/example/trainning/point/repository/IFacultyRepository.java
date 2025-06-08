package com.example.trainning.point.repository;

import com.example.trainning.point.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacultyRepository extends JpaRepository<Faculty , Long> {

}
