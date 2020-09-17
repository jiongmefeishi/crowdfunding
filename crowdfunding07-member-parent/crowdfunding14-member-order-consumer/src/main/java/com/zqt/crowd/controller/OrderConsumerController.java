package com.zqt.crowd.controller;

import com.zqt.crowd.api.MysqlRemoteApi;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.vo.order.OrderProjectVO;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("order")
public class OrderConsumerController {
    /**
     * 加载远程mysql的微服务接口操作api
     */
    @Autowired
    private MysqlRemoteApi mysqlRemoteApi;

    /**
     * 查询回报确认信息
     *
     * @param projectId 项目id
     * @param returnId  回报信息 id
     */
    @GetMapping("confirm/return/info/{projectId}/{returnId}")
    public String showReturnConfirmInfo(
            @PathVariable("projectId") Integer projectId,
            @PathVariable("returnId") Integer returnId,
            HttpSession session) {

        ResultEntity<OrderProjectVO> resultEntity =
                mysqlRemoteApi.getOrderProjectVORemote(projectId, returnId);

        if (ResultEntity.RESULT_SUCCESS.equals(resultEntity.getResult())) {
            OrderProjectVO orderProjectVO = resultEntity.getData();

            // 为了能够在后续操作中保持orderProjectVO数据，存入Session域
            session.setAttribute(CommonConstant.ATTR_NAME_ORDER_PROJECT, orderProjectVO);
        }

        return "confirm_return";
    }
}
