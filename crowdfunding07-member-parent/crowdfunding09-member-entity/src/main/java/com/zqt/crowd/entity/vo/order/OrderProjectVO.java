package com.zqt.crowd.entity.vo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zqtao
 * @description: 订单支持项目信息封装
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProjectVO implements Serializable {

    private static final long serialVersionUID = -3510304817506945174L;

    /**
     * 订单支持项目信息主键
     */
    private Integer id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 发起人
     */
    private String launchName;

    /**
     * 回报内容
     */
    private String returnContent;

    /**
     * 回报数量
     */
    private Integer returnCount;

    /**
     * 支持单价
     */
    private Integer supportPrice;

    /**
     * 配送费用
     */
    private Integer freight;

    /**
     * 订单 id
     */
    private Integer orderId;

    /**
     * 是否限制单笔购买数量，0表示不限购，1表示限购
     */
    private Integer signalPurchase;

    /**
     * 具体限购数量，如果单笔限购，那么具体的限购数量
     */
    private Integer purchase;
}