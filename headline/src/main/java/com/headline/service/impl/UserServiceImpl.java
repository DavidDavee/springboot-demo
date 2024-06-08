package com.headline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.headline.domain.User;
import com.headline.enums.ResultCodeEnum;
import com.headline.service.UserService;
import com.headline.mapper.UserMapper;
import com.headline.utils.JwtHelper;
import com.headline.utils.MD5Util;
import com.headline.utils.ResultDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-06-07 17:33:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录业务实现
     *
     * @param user
     * @return result封装
     */
    @Override
    public ResultDomain login(User user) {

        //根据账号查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);

        //账号判断
        if (loginUser == null) {
            //账号错误
            return ResultDomain.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //判断密码
        if (!StringUtils.isEmpty(user.getUserPwd())
                && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            //账号密码正确
            //根据用户唯一标识生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            Map data = new HashMap();
            data.put("token", token);

            return ResultDomain.ok(data);
        }

        //密码错误
        return ResultDomain.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    /**
     * 查询用户数据
     *
     * @param token
     * @return result封装
     */
    @Override
    public ResultDomain getUserInfo(String token) {

        //1.判定是否有效期
        if (jwtHelper.isExpiration(token)) {
            //true过期,直接返回未登录
            return ResultDomain.build(null, ResultCodeEnum.NOTLOGIN);
        }

        //2.获取token对应的用户
        int userId = jwtHelper.getUserId(token).intValue();

        //3.查询数据
        User user = userMapper.selectById(userId);

        if (user != null) {
            user.setUserPwd(null);
            Map data = new HashMap();
            data.put("loginUser", user);
            return ResultDomain.ok(data);
        }

        return ResultDomain.build(null, ResultCodeEnum.NOTLOGIN);
    }

    /**
     * 检查账号是否可以注册
     *
     * @param username 账号信息
     * @return
     */
    @Override
    public ResultDomain checkUserName(String username) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);

        if (user != null) {
            return ResultDomain.build(null, ResultCodeEnum.USERNAME_USED);
        }

        return ResultDomain.ok(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public ResultDomain regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0) {
            return ResultDomain.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int rows = userMapper.insert(user);
        System.out.println("rows = " + rows);
        return ResultDomain.ok(null);
    }
}




