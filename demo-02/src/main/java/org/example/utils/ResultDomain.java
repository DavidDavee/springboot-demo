package org.example.utils;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-17 23:36
 **/
public class ResultDomain {

    /**
     * 200成功状态码
     */
    private int code = 200;

    /**
     * 返回状态
     */
    private boolean flag = true;

    /**
     * 返回具体数据
     */
    private Object data;

    public static ResultDomain ok() {
        ResultDomain r = new ResultDomain();
        return r;
    }

    public static ResultDomain ok(Object data) {
        ResultDomain r = new ResultDomain();
        r.data = data;
        return r;
    }

    public static ResultDomain fail() {
        ResultDomain r = new ResultDomain();
        //错误码
        r.code = 500;
        //错误状态
        r.flag = false;
        return r;
    }

    public static ResultDomain fail(Object data) {
        ResultDomain r = new ResultDomain();
        //错误码
        r.code = 500;
        //错误状态
        r.flag = false;
        r.data = data;
        return r;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
