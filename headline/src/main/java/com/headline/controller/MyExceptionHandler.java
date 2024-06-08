package com.headline.controller;

import com.headline.utils.ResultDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-17 23:56
 **/

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResultDomain myRuntimeException(RuntimeException e) {
        log.error("内部错误:{}", e.getMessage(), e.fillInStackTrace());
        return ResultDomain.build(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
