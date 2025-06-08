package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.notification.NotificationRequest;
import com.example.trainning.point.dto.response.notification.NotificationResponse;
import com.example.trainning.point.entity.Notification;
import com.example.trainning.point.repository.IClassManagerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class NotificationMapper {
    IClassManagerRepository classManagerRepository;
    public Notification convertToEntity(NotificationRequest request){
        return Notification.builder()
                .notificationMessage(request.getNotiMessage())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public NotificationResponse convertToResponse(Notification entity){
        return NotificationResponse.builder()
                .id(entity.getId())
                .notiMessage(entity.getNotificationMessage())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public Notification convertToEntityVerCounselor(NotificationRequest request){

        return  Notification.builder()
                .notificationMessage(request.getNotiMessage())
                .createdAt(LocalDateTime.now())
                .classManager(classManagerRepository.findByIdOrderByStartDateAsc(request.getClassId()))
                .build();
    }

    public NotificationResponse convertToResponseVerCounselor (Notification entity){
        return NotificationResponse.builder()
                .id(entity.getId())
                .notiMessage(entity.getNotificationMessage())
                .createdAt(entity.getCreatedAt())
                .className(entity.getClassManager().getName())
                .build();
    }
}
