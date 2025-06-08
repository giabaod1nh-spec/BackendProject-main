package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.request.notification.NotificationRequest;
import com.example.trainning.point.dto.response.notification.NotificationResponse;

import java.util.List;

public interface INotificationService {
    NotificationResponse createNotification (NotificationRequest request);
    NotificationResponse createNotificationByCounselor (NotificationRequest request);
    List<NotificationResponse> getNotificationFromAdmin();
    List<NotificationResponse> getNotificationFromCounselor(Long classId);
}
