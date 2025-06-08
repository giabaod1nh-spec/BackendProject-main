package com.example.trainning.point.controller;

import com.example.trainning.point.dto.request.*;
import com.example.trainning.point.dto.request.user.ChangepassRequest;
import com.example.trainning.point.dto.response.AuthenticationResponse;
import com.example.trainning.point.dto.response.IntrospectResponse;
import com.example.trainning.point.service.impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
         authenticationService.logout(request);
       return ApiResponse.<Void>builder()
                .build();
    }

    @PutMapping("/change-pass")
    ApiResponse<String> changePass(@RequestBody ChangepassRequest changepassRequest){
        authenticationService.changePass(changepassRequest.getUserId(), changepassRequest.getOldPass(),
                changepassRequest.getNewPass());

        return ApiResponse.<String>builder()
                .message("change pass ok")
                .build();
    }
}
