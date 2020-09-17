package com.zqt.crowd.controller.order;

import com.zqt.crowd.entity.vo.order.OrderProjectVO;
import com.zqt.crowd.service.order.OrderService;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("order")
@RestController
public class OrderProviderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取回报确认信息
     *
     * @param projectId 项目id
     * @param returnId  回报信息 id
     */
    @RequestMapping("get/order/project/vo/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVORemote(
            @RequestParam("projectId") Integer projectId,
            @RequestParam("returnId") Integer returnId) {

        try {
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(projectId, returnId);

            return ResultEntity.successWithData(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }
}
