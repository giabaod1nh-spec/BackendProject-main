package com.example.trainning.point.controller;

import com.example.trainning.point.dto.request.ApiResponse;
import com.example.trainning.point.dto.request.notification.NotificationRequest;
import com.example.trainning.point.dto.response.notification.NotificationResponse;
import com.example.trainning.point.service.impl.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class NotificationController {
    NotificationService notificationService;
    @PostMapping("/create")
    ApiResponse<NotificationResponse> createNotification(@RequestBody  NotificationRequest request){
        return ApiResponse.<NotificationResponse>builder()
                .result(notificationService.createNotification(request))
                .build();
    }

    @PostMapping("/create_counselor")
    ApiResponse<NotificationResponse> createNotificationForCounselor(@RequestBody  NotificationRequest request){
        return ApiResponse.<NotificationResponse>builder()
                .result(notificationService.createNotificationByCounselor(request))
                .build();
    }

    @GetMapping("/show")
    ApiResponse<List<NotificationResponse>> getAdminNotification(){
        return ApiResponse.<List<NotificationResponse>>builder()
                .result(notificationService.getNotificationFromAdmin())
                .build();
    }

    @GetMapping("/show_counselor")
    ApiResponse<List<NotificationResponse>> getCounselorNotification(@RequestParam (value = "classId") Long classId){
        return  ApiResponse.<List<NotificationResponse>>builder()
                .result((notificationService.getNotificationFromCounselor(classId)))
                .build();
    }

}
