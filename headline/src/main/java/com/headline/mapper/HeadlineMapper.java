package com.headline.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.headline.domain.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.headline.domain.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author David
 * @description 针对表【news_headline】的数据库操作Mapper
 * @createDate 2024-06-07 17:33:10
 * @Entity com.headline.domain.Headline
 */
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectPageMap(IPage<Headline> page, @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




