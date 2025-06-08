package com.example.trainning.point.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangepassRequest {
    String userId;
    String oldPass;
    String newPass;
}
