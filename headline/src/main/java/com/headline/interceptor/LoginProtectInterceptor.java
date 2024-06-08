package com.headline.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.headline.enums.ResultCodeEnum;
import com.headline.utils.JwtHelper;
import com.headline.utils.ResultDomain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @program: springboot-demo
 * @description:登录检查拦截器
 * @author: David
 * @create: 2024-06-07 22:28
 **/
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            ResultDomain result = ResultDomain.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().print(json);
            //拦截
            return false;
        } else {
            //放行
            return true;
        }
    }
}
