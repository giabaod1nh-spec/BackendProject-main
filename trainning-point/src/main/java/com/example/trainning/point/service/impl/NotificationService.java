package com.example.trainning.point.service.impl;

import com.example.trainning.point.dto.request.notification.NotificationRequest;
import com.example.trainning.point.dto.response.notification.NotificationResponse;
import com.example.trainning.point.entity.Notification;
import com.example.trainning.point.mapper.custom.NotificationMapper;
import com.example.trainning.point.repository.INotificationRepository;
import com.example.trainning.point.service.interfaces.INotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class NotificationService implements INotificationService {
    INotificationRepository notificationRepository;
    NotificationMapper notificationMapper;

    @Override
    public NotificationResponse createNotification(NotificationRequest request){
        Notification entity = notificationMapper.convertToEntity(request);
        return notificationMapper.convertToResponse(notificationRepository.save(entity));
    }

    public NotificationResponse createNotificationByCounselor(NotificationRequest request){
        Notification entity = notificationMapper.convertToEntityVerCounselor(request);
        return notificationMapper.convertToResponseVerCounselor(notificationRepository.save(entity));
    }

    @Override
    public List<NotificationResponse> getNotificationFromAdmin(){
        return notificationRepository.findByClassManagerIsNullOrderByCreatedAtDesc().stream().map(notificationMapper::convertToResponse).toList();
    }

    @Override
    public  List<NotificationResponse> getNotificationFromCounselor(Long classId){
        return notificationRepository.findByClassManagerIdOrderByCreatedAtDesc(classId).stream().map(notificationMapper::convertToResponse).toList();
    }

}
