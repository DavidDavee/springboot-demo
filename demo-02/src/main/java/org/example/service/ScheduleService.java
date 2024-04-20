package org.example.service;

import org.example.pojo.Schedule;
import org.example.utils.PageBean;
import org.example.utils.ResultDomain;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-17 23:43
 **/
public interface ScheduleService {
    PageBean<Schedule> findByPage(int pageSize, int currentPage);

    ResultDomain removeById(Integer id);

    void saveSchedule(Schedule schedule);

    ResultDomain updateSchedule(Schedule schedule);
}
