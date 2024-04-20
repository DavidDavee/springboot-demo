package org.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-17 23:39
 *
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    // 当前页码
    private int currentPage;
    // 每页显示的数据量
    private int pageSize;
    // 总数据条数
    private long total;
    // 当前页的数据集合
    private List<T> data;
}

