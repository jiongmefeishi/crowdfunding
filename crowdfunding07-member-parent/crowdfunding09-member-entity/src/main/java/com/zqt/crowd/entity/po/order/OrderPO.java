package com.zqt.crowd.entity.po.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 订单信息封装
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderPO {

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
     * 是否开发票（0 不开，1 开）
     */
    private Integer invoice;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 订单备注
     */
    private String orderRemark;

    /**
     * 订单收货地址 id
     */
    private String addressId;
}