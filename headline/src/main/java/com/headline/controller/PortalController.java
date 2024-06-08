package com.headline.controller;

import com.headline.domain.vo.PortalVo;
import com.headline.domain.Type;
import com.headline.service.HeadlineService;
import com.headline.service.TypeService;
import com.headline.utils.ResultDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-06-07 21:47
 **/
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    /**
     * 查询全部类别信息
     *
     * @return
     */
    @GetMapping("findAllTypes")
    public ResultDomain findAllTypes() {
        //直接调用业务层,查询全部数据
        List<Type> list = typeService.list();
        return ResultDomain.ok(list);
    }

    /**
     * 首页分页查询
     *
     * @return
     */
    @PostMapping("findNewsPage")
    public ResultDomain findNewPage(@RequestBody PortalVo portalVo) {
        ResultDomain result = headlineService.findNewPage(portalVo);
        return result;
    }

    /**
     * 首页详情接口
     *
     * @param hid
     * @return
     */
    @PostMapping("showHeadlineDetail")
    public ResultDomain showHeadlineDetail(Integer hid) {
        ResultDomain result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
