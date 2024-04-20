package org.example.controller;

import org.apache.ibatis.annotations.Param;
import org.example.DataSourceConfigurationProperties;
import org.example.service.ScheduleService;
import org.example.utils.ResultDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-20 00:07
 **/
@RestController
@ResponseBody
@RequestMapping("/say")
public class HelloController {

    @Autowired
    private DataSourceConfigurationProperties dataSourceConfigurationProperties;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/hi")
    public Object hello(){
        System.out.println("dataSourceConfigurationProperties = " + dataSourceConfigurationProperties);
        return scheduleService.findByPage(5, 1);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/remove")
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public ResultDomain removeSchedule(@RequestParam(value = "id") Integer id){
        scheduleService.removeById(id);
        int i = 1/0;
        return ResultDomain.ok(id);
    }
}
