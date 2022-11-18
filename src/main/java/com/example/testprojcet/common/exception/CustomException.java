package com.example.testprojcet.common.exception;

import com.example.testprojcet.common.Constants;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    // 직렬화를 위한 시리얼 넘버
    private static final long serialVersionUID = 4663380430591151694L;

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public CustomException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
