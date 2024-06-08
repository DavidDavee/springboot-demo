package com.headline.utils;

import com.headline.enums.ResultCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @program: springboot-demo
 * @description:全局统一返回结果类
 * @author: David
 * @create: 2024-06-07 17:22
 **/
public class ResultDomain<T> {
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;

    public ResultDomain() {
    }

    // 返回数据
    protected static <T> ResultDomain<T> build(T data) {
        ResultDomain<T> ResultDomain = new ResultDomain<T>();
        if (data != null) {
            ResultDomain.setData(data);
        }
        return ResultDomain;
    }

    public static <T> ResultDomain<T> build(T body, Integer code, String message) {
        ResultDomain<T> ResultDomain = build(body);
        ResultDomain.setCode(code);
        ResultDomain.setMessage(message);
        return ResultDomain;
    }

    public static <T> ResultDomain<T> build(T body, HttpStatus httpStatus) {
        ResultDomain<T> ResultDomain = build(body);
        ResultDomain.setCode(httpStatus.value());
        ResultDomain.setMessage(httpStatus.getReasonPhrase());
        return ResultDomain;
    }

    public static <T> ResultDomain<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResultDomain<T> ResultDomain = build(body);
        ResultDomain.setCode(resultCodeEnum.getCode());
        ResultDomain.setMessage(resultCodeEnum.getMessage());
        return ResultDomain;
    }

    /**
     * 操作成功
     *
     * @param data baseCategory1List
     * @param <T>
     * @return
     */
    public static <T> ResultDomain<T> ok(T data) {
        ResultDomain<T> ResultDomain = build(data);
        return build(data, HttpStatus.OK);
    }

    public ResultDomain<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResultDomain<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
