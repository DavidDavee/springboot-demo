package com.headline.controller;

import com.headline.domain.User;
import com.headline.enums.ResultCodeEnum;
import com.headline.service.UserService;
import com.headline.utils.JwtHelper;
import com.headline.utils.ResultDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-06-07 18:10
 **/
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录需求
     * 地址: /user/login
     * 方式: post
     * 参数:
     * {
     * "username":"zhangsan", //用户名
     * "userPwd":"123456"     //明文密码
     * }
     * 返回:
     * {
     * "code":"200",         // 成功状态码
     * "message":"success"   // 成功状态描述
     * "data":{
     * "token":"... ..." // 用户id的token
     * }
     * }
     * <p>
     * 大概流程:
     * 1. 账号进行数据库查询 返回用户对象
     * 2. 对比用户密码(md5加密)
     * 3. 成功,根据userId生成token -> map key=token value=token值 - result封装
     * 4. 失败,判断账号还是密码错误,封装对应的枚举错误即可
     */
    @PostMapping("login")
    public ResultDomain login(@RequestBody User user) {
        ResultDomain result = userService.login(user);
        System.out.println("result = " + result);
        return result;
    }

    /**
     * 地址: user/getUserInfo
     * 方式: get
     * 请求头: token = token内容
     * 返回:
     * {
     * "code": 200,
     * "message": "success",
     * "data": {
     * "loginUser": {
     * "uid": 1,
     * "username": "zhangsan",
     * "userPwd": "",
     * "nickName": "张三"
     * }
     * }
     * }
     * <p>
     * 大概流程:
     * 1.获取token,解析token对应的userId
     * 2.根据userId,查询用户数据
     * 3.将用户数据的密码置空,并且把用户数据封装到结果中key = loginUser
     * 4.失败返回504 (本次先写到当前业务,后期提取到拦截器和全局异常处理器)
     */
    @GetMapping("getUserInfo")
    public ResultDomain userInfo(@RequestHeader String token) {
        ResultDomain result = userService.getUserInfo(token);
        return result;
    }

    /**
     * url地址：user/checkUserName
     * 请求方式：POST
     * 请求参数：param形式
     * username=zhangsan
     * 响应数据:
     * {
     * "code":"200",
     * "message":"success"
     * "data":{}
     * }
     * <p>
     * 实现步骤:
     * 1. 获取账号数据
     * 2. 根据账号进行数据库查询
     * 3. 结果封装
     */
    @PostMapping("checkUserName")
    public ResultDomain checkUserName(String username) {
        ResultDomain result = userService.checkUserName(username);
        return result;
    }

    /**
     * url地址：user/regist
     * 请求方式：POST
     * 请求参数：
     * {
     * "username":"zhangsan",
     * "userPwd":"123456",
     * "nickName":"张三"
     * }
     * 响应数据：
     * {
     * "code":"200",
     * "message":"success"
     * "data":{}
     * }
     * <p>
     * 实现步骤:
     * 1. 将密码加密
     * 2. 将数据插入
     * 3. 判断结果,成 返回200 失败 505
     */

    @PostMapping("regist")
    public ResultDomain regist(@RequestBody User user) {
        ResultDomain result = userService.regist(user);
        return result;
    }

    @GetMapping("checkLogin")
    public ResultDomain checkLogin(@RequestHeader String token){
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            //没有传或者过期 未登录
            return ResultDomain.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return ResultDomain.ok(null);
    }
}
