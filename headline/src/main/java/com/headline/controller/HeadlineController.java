package com.headline.controller;

import com.headline.domain.Headline;
import com.headline.service.HeadlineService;
import com.headline.utils.JwtHelper;
import com.headline.utils.ResultDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-06-07 22:34
 **/
@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 实现步骤:
     * 1. token获取userId [无需校验,拦截器会校验]
     * 2. 封装headline数据
     * 3. 插入数据即可
     */
    @PostMapping("publish")
    public ResultDomain publish(@RequestBody Headline headline, @RequestHeader String token) {

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        ResultDomain result = headlineService.publish(headline);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public ResultDomain findHeadlineByHid(Integer hid) {
        ResultDomain result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("update")
    public ResultDomain update(@RequestBody Headline headline) {
        ResultDomain result = headlineService.updateHeadLine(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public ResultDomain removeById(Integer hid) {
        headlineService.removeById(hid);
        return ResultDomain.ok(null);
    }
}
