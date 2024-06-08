package com.headline.domain.vo;

import lombok.Data;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-06-07 21:54
 **/
@Data
public class PortalVo {
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
