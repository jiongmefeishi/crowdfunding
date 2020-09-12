package com.zqt.crowd.controller;

import com.zqt.crowd.api.MysqlRemoteApi;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.vo.portal.PortalTypeVO;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: zqtao
 * @description: 前端首页访问控制层
 */
@Controller
public class PortalController {

    @Autowired
    private MysqlRemoteApi mysqlRemoteApi;

    /**
     * 浏览器访问 域名 如： www.test.com 进入门户首页
     *
     * @return 返回视图 portal
     */
    @RequestMapping("/")
    public String showPortalPage(Model model) {

        // 1、调用 mysqlRemoteApi 提供的方法查询首页要显示的数据
        ResultEntity<List<PortalTypeVO>> resultEntity =
                mysqlRemoteApi.getPortalTypeProjectDataRemote();

        // 2.检查查询结果
        String result = resultEntity.getResult();
        if (ResultEntity.RESULT_SUCCESS.equals(result)) {

            // 3.获取查询结果数据
            List<PortalTypeVO> list = resultEntity.getData();

            // 4.存入模型
            model.addAttribute(CommonConstant.ATTR_NAME_PORTAL_DATA, list);
        }

        return "portal";
    }

}
