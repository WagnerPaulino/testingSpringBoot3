package com.example.testingSpringBoot3.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler
    ProblemDetail handleIlegalStateException(IllegalStateException exception){
        var pd = ProblemDetail.forStatus(HttpStatusCode.valueOf(404));
        pd.setDetail(exception.getMessage());
        return pd;
    }
}
