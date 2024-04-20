package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.ResultDomain;
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
        return ResultDomain.fail("结果异常");
    }

}
