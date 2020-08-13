package com.zqt.crowd.controller;

import com.zqt.crowd.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zqtao
 * @description: 前端首页访问控制层
 */
@Controller
public class PortalController {

    /**
     * 浏览器访问 域名 如： www.test.com 进入门户首页
     * @return 返回视图 portal
     */
    @RequestMapping("/")
    public String showPortalPage() {

        // 这里实际开发中需要加载首页的各项数据……

        return "portal";
    }


    @ResponseBody
    @GetMapping("test")
    public ResultEntity<String> test() {
        return ResultEntity.successWithData("test");
    }
}
