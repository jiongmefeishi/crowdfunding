package com.zqt.crowd.api.aliyun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 支付宝请求信息封装
 * @date: 2020/10/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlipayReqDO {

    private String appId;
    private String merchantPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;
    private String returnUrl;
    private String signType;
    private String charset;
    private String gatewayUrl;
}
