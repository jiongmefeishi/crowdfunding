package com.zqt.crowd.controller;

import com.zqt.crowd.api.MysqlRemoteApi;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.vo.member.MemberLoginVO;
import com.zqt.crowd.entity.vo.order.OrderAddressVO;
import com.zqt.crowd.entity.vo.order.OrderProjectVO;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("order")
public class OrderConsumerController {
    /**
     * 加载远程mysql的微服务接口操作api
     */
    @Autowired
    private MysqlRemoteApi mysqlRemoteApi;

    /**
     * 获取订单确认信息
     *
     * @param returnCount 项目回报数量
     * @param session 共享session
     */
    @RequestMapping("/confirm/order/{returnCount}")
    public String showConfirmOrderInfo(
            @PathVariable("returnCount") Integer returnCount,
            HttpSession session) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "showConfirmOrderInfo",
                "获取订单确认信息"
        );

        // 1.把接收到的回报数量合并到Session域
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

        orderProjectVO.setReturnCount(returnCount);

        session.setAttribute("orderProjectVO", orderProjectVO);

        // 2.获取当前已登录用户的id
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CommonConstant.ATTR_NAME_LOGIN_ADMIN);

        Integer memberId = memberLoginVO.getId();

        // 3.查询目前的收货地址数据
        ResultEntity<List<OrderAddressVO>> resultEntity = mysqlRemoteApi.getOrderAddressVORemote(memberId);

        if (ResultEntity.RESULT_SUCCESS.equals(resultEntity.getResult())) {
            List<OrderAddressVO> list = resultEntity.getData();
            session.setAttribute("orderAddressVOList", list);
        }

        return "confirm_order";
    }

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

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "OrderConsumerController: showReturnConfirmInfo",
                "查询回报确认信息"
        );

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
