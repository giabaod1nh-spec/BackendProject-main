package com.example.trainning.point.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATOGERIZED_EXCEPTION(9999 , "Uncategorized error" , HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error" , HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002 , "User existed" , HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1003, "User not existed" , HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    UNAUTHETICATED(1006, "Unauthenticated" , HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007 , "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be atleast {min} " , HttpStatus.BAD_REQUEST),

    PASS_NOT_MATCH(1009, "Password not match " , HttpStatus.BAD_REQUEST),

    NOT_FOUND(1100, "NOT_FOUND", HttpStatus.BAD_REQUEST),
    FACULTY_NOT_FOUND(1110, "FACULTY_NOT_FOUND", HttpStatus.BAD_REQUEST),
    CLASS_NOT_FOUND(1111, "CLASS_NOT_FOUND", HttpStatus.BAD_REQUEST),
    CLASS_ALREADY_ASSIGNED(1112, "CLASS_ALREADY_ASSIGNED", HttpStatus.BAD_REQUEST),
    SEMESTER_NOT_FOUND(1101, "SEMESTER_NOT_FOUND", HttpStatus.BAD_REQUEST),
    SEMESTER_EXPIRED(1102, "SEMESTER_EXPIRED", HttpStatus.BAD_REQUEST),
    INVALID_SEMESTER(1110, "SEMESTER_EXPIRED OR SEMESTER IS PENDING", HttpStatus.BAD_REQUEST),
    INVALID_TIME_SEMESTER(1103, "DUPLICATE TIME WITH OTHERS SEMESTER" , HttpStatus.BAD_REQUEST),
    INVALID_TIME_MARKING(1104, "NOT IN TIME TO MARK" , HttpStatus.BAD_REQUEST),
    INVALID_TIME_STUDENT_MARKING(1105, "NOT IN TIME FOR STUDENT TO MARK" , HttpStatus.BAD_REQUEST),
    INVALID_TIME_MONITOR_MARKING(1106, "NOT IN TIME FOR MONITOR TO MARK" , HttpStatus.BAD_REQUEST),
    INVALID_TIME_COUNSELOR_MARKING(1107, "NOT IN TIME FOR COUNSELOR TO MARK" , HttpStatus.BAD_REQUEST),
    INVALID_PERIOD_TIME_PER_ROLE(1108, "THE (START/END) TIME IS NOT BELONGS TO MARK TIME" , HttpStatus.BAD_REQUEST),
    INVALID_PERIOD_TIME(1109, "THE (START/END) DATE IS NOT BELONGS TO SEMESTER PERIOD TIME " , HttpStatus.BAD_REQUEST),








    CANNOT_UPDATE_MARK(1200, "CANNOT_UPDATE_MARK", HttpStatus.BAD_REQUEST),
    CANNOT_DELETE_CATEGORY_HAS_ANY_STANDARD(1200, "CANNOT_DELETE_CATEGORY", HttpStatus.BAD_REQUEST),
    ANOTHER_IS_MARKING(1201, "ANOTHER_IS_MARKING", HttpStatus.BAD_REQUEST),


    PASS_NOT_CORRECT(1202, "Pass word not corect", HttpStatus.BAD_REQUEST)
    ;
    ErrorCode(int code , String message , HttpStatusCode statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
