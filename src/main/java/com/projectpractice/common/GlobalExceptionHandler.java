package com.projectpractice.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


 //全局异常处理

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public HttpResponseEntity exceptionHandle(Exception exception){
        return HttpResponseEntity.error(exception.getMessage());
    }
}


