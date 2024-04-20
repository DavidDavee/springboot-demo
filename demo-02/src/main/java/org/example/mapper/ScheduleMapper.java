package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Schedule;

import java.util.List;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-17 23:46
 **/
@Mapper
public interface ScheduleMapper {
    List<Schedule> queryPage();

    int delete(Integer id);

    void insert(Schedule schedule);

    int update(Schedule schedule);
}
