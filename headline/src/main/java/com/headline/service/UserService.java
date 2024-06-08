package com.headline.service;

import com.headline.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.headline.utils.ResultDomain;

/**
* @author David
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-06-07 17:33:11
*/
public interface UserService extends IService<User> {

    ResultDomain login(User user);

    ResultDomain getUserInfo(String token);

    ResultDomain checkUserName(String username);

    ResultDomain regist(User user);
}
