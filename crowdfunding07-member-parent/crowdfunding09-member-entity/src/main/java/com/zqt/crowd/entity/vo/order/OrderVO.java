package com.zqt.crowd.entity.vo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zqtao
 * @description: 订单VO 对象
 * @date: 2020/10/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 支付宝流水单号
     */
    private String payOrderNum;

    /**
     * 订单金额
     */
    private Double orderAmount;

    /**
     * 是否开发票
     */
    private Integer invoice;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 备注
     */
    private String orderRemark;

    /**
     * 收货地址id
     */
    private String addressId;

    /**
     * 订单支持项目信息
     */
    private OrderProjectVO orderProjectVO;

}

