package com.headline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.headline.domain.Headline;
import com.headline.domain.vo.PortalVo;
import com.headline.service.HeadlineService;
import com.headline.mapper.HeadlineMapper;
import com.headline.utils.ResultDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2024-06-07 17:33:10
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {
    @Autowired
    private HeadlineMapper headlineMapper;

    /**
     * 首页数据查询
     *
     * @param portalVo
     * @return
     */
    @Override
    public ResultDomain findNewPage(PortalVo portalVo) {

        //1.条件拼接 需要非空判断
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()), Headline::getTitle, portalVo.getKeyWords())
                .eq(portalVo.getType() != null, Headline::getType, portalVo.getType());

        //2.分页参数
        IPage<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());

        //3.分页查询
        //查询的结果 "pastHours":"3"   // 发布时间已过小时数 我们查询返回一个map
        //自定义方法
        headlineMapper.selectPageMap(page, portalVo);

        //4.结果封装
        //分页数据封装
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        // 响应JSON
        return ResultDomain.ok(pageInfoMap);
    }

    /**
     * 详情数据查询
     * "headline":{
     * "hid":"1",                     // 新闻id
     * "title":"马斯克宣布 ... ...",   // 新闻标题
     * "article":"... ..."            // 新闻正文
     * "type":"1",                    // 新闻所属类别编号
     * "typeName":"科技",             // 新闻所属类别
     * "pageViews":"40",              // 新闻浏览量
     * "pastHours":"3" ,              // 发布时间已过小时数
     * "publisher":"1" ,              // 发布用户ID
     * "author":"张三"                 // 新闻作者
     * }
     * 注意: 是多表查询 , 需要更新浏览量+1
     *
     * @param hid
     * @return
     */
    @Override
    public ResultDomain showHeadlineDetail(Integer hid) {

        //1.实现根据id的查询(多表
        Map headLineDetail = headlineMapper.selectDetailMap(hid);
        //2.拼接头条对象(阅读量和version)进行数据更新
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headLineDetail.get("pageViews") + 1); //阅读量+1
        headline.setVersion((Integer) headLineDetail.get("version")); //设置版本
        headlineMapper.updateById(headline);

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("headline", headLineDetail);
        return ResultDomain.ok(pageInfoMap);
    }

    /**
     * 发布数据
     *
     * @param headline
     * @return
     */
    @Override
    public ResultDomain publish(Headline headline) {
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return ResultDomain.ok(null);
    }

    /**
     * 根据id查询详情
     *
     * @param hid
     * @return
     */
    @Override
    public ResultDomain findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("headline", headline);
        return ResultDomain.ok(pageInfoMap);
    }

    /**
     * 修改业务
     * 1.查询version版本
     * 2.补全属性,修改时间 , 版本!
     *
     * @param headline
     * @return
     */
    @Override
    public ResultDomain updateHeadLine(Headline headline) {

        //读取版本
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return ResultDomain.ok(null);
    }
}




