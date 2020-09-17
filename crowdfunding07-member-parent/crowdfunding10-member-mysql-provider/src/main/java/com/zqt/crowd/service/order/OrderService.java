package com.zqt.crowd.service.order;

import com.zqt.crowd.entity.vo.order.OrderAddressVO;
import com.zqt.crowd.entity.vo.order.OrderProjectVO;

import java.util.List;

/**
 * @author: zqtao
 * @description: 订单业务层接口
 */
public interface OrderService {


    /**
     * 获取回报确认信息
     *
     * @param projectId 项目id
     * @param returnId  回报信息 id
     */
    OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

    /**
     * 获取会员收货地址列表
     *
     * @param memberId 会员id
     * @return List<OrderAddressVO>
     */
    List<OrderAddressVO> getAddressVOList(Integer memberId);

    /**
     * 保存收货地址
     *
     * @param addressVO 收货地址信息
     */
    void saveAddress(OrderAddressVO addressVO);
}
