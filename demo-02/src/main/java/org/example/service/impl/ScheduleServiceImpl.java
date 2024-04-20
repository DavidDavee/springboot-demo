package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ScheduleMapper;
import org.example.pojo.Schedule;
import org.example.service.ScheduleService;
import org.example.utils.Constant;
import org.example.utils.PageBean;
import org.example.utils.ResultDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-17 23:44
 **/
@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * 分页数据查询,返回分页pageBean
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public PageBean<Schedule> findByPage(int pageSize, int currentPage) {
        //1.设置分页参数
        PageHelper.startPage(currentPage, pageSize);
        //2.数据库查询
        List<Schedule> list = scheduleMapper.queryPage();
        //3.结果获取
        PageInfo<Schedule> pageInfo = new PageInfo<>(list);
        //4.pageBean封装
        PageBean<Schedule> pageBean = new PageBean<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList());

        log.info("分页查询结果:{}", pageBean);

        return pageBean;
    }

    @Override
    public ResultDomain removeById(Integer id) {
        int rows = scheduleMapper.delete(id);
        if (rows > 0) {
            return ResultDomain.ok();
        } else {
            return ResultDomain.fail();
        }
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleMapper.insert(schedule);
    }

    @Override
    public ResultDomain updateSchedule(Schedule schedule) {
        if (schedule.getId() == null) {
            return ResultDomain.fail(Constant.PARAM_ERROR_MESSAGE);
        }
        int rows = scheduleMapper.update(schedule);
        if (rows > 0) {
            return ResultDomain.ok();
        } else {
            return ResultDomain.fail();
        }
    }
}
