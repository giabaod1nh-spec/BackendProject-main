package com.example.trainning.point.dto.request.notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    String notiMessage;
    Long classId;
}
