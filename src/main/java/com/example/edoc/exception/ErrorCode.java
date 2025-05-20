package com.example.edoc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(101, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(102, "User existed", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND(103, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(104, "Unauthenticated", HttpStatus.UNAUTHORIZED);
    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
