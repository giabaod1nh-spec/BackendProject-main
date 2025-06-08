package com.example.trainning.point.repository;

import com.example.trainning.point.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository<Notification , Long> {
    List<Notification> findAllByOrderByCreatedAtDesc();
    List<Notification> findByClassManagerIsNullOrderByCreatedAtDesc();
    List<Notification> findByClassManagerIdOrderByCreatedAtDesc(Long classId);

}
