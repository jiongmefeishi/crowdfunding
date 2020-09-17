package com.zqt.crowd.controller.order;

import com.zqt.crowd.entity.vo.order.OrderAddressVO;
import com.zqt.crowd.entity.vo.order.OrderProjectVO;
import com.zqt.crowd.service.order.OrderService;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("order")
@RestController
public class OrderProviderController {

    @Autowired
    private OrderService orderService;


    /**
     * 保存收货地址
     *
     * @param orderAddressVO 收货地址信息
     */
    @PostMapping("save/address/remote")
    public ResultEntity<String> saveAddressRemote(@RequestBody OrderAddressVO orderAddressVO) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "api : saveAddressRemote",
                "保存收货地址"
        );

        try {
            orderService.saveAddress(orderAddressVO);

            return ResultEntity.successWithoutData();

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

    /**
     * 查询收货地址
     *
     * @param memberId 会员id
     */
    @GetMapping("get/address/vo/remote")
    ResultEntity<List<OrderAddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "api : getAddressVORemote",
                "查询收货地址"
        );

        try {
            List<OrderAddressVO> addressVOList = orderService.getAddressVOList(memberId);

            return ResultEntity.successWithData(addressVOList);

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

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

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "api : getOrderProjectVORemote",
                "获取回报确认信息"
        );

        try {
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(projectId, returnId);

            return ResultEntity.successWithData(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }
}
