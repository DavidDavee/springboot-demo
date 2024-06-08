package com.headline.service;

import com.headline.domain.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.headline.domain.vo.PortalVo;
import com.headline.utils.ResultDomain;

/**
* @author David
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-06-07 17:33:10
*/
public interface HeadlineService extends IService<Headline> {
    ResultDomain findNewPage(PortalVo portalVo);

    ResultDomain showHeadlineDetail(Integer hid);

    ResultDomain publish(Headline headline);

    ResultDomain findHeadlineByHid(Integer hid);

    ResultDomain updateHeadLine(Headline headline);
}
