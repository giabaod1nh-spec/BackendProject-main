package com.example.trainning.point.dto.response.notification;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    Long id;
    String notiMessage;
    String className;
    LocalDateTime createdAt;
}
