package com.projectpractice.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.common
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-03  20:01
 * @Description: 全局异常处理
 * @Version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public HttpResponseEntity exceptionHandle(Exception exception){
        exception.printStackTrace();
        return HttpResponseEntity.error(exception.getMessage());
    }
}
