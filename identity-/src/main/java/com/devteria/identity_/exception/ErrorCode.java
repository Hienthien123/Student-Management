package com.devteria.identity_.exception;

import lombok.Getter;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter

public enum ErrorCode {
    UNCATEGORIZED(999,"Ucategorized", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001,"user existed",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1002,"Invalid message key",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003,"Password must be at least 3 characters ",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004,"Password must be at least 8 characters ",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005,"user not existed",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006,"unauthenticatec",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007,"You do not have permission",HttpStatus.FORBIDDEN),
    INVALID_DOB(1008,"You  age must be at least 18",HttpStatus.BAD_REQUEST),
;
    private int code;

    public int getCode() {
        return code;
    }
    private HttpStatusCode statuscode;

    public String getMessage() {
        return message;
    }

    private String message;

    ErrorCode(int code, String message,HttpStatusCode statuscode) {
        this.statuscode = statuscode;
        this.code = code;
        this.message = message;
    }
}
