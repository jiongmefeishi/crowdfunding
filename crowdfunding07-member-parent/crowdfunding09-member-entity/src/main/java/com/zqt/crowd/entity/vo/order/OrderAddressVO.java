package com.zqt.crowd.entity.vo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zqtao
 * @description: 订单信息封装
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderAddressVO implements Serializable {

    private static final long serialVersionUID = 7172966651876636381L;

    /**
     * 收货地址主键
     */
    private Integer id;

    /**
     * 收件人
     */
    private String receiveName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 会员 id
     */
    private Integer memberId;
}