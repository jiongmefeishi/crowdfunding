package com.zqt.crowd.api.aliyun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 封装阿里云短信API 发送短信请求返回数据 数据对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendSmsRespDO {

    /**
     * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
     */
    String bizId;

    /**
     * 请求状态码
     * 返回OK代表请求成功。
     * 其他错误码详见错误码列表。
     */
    String code;

    /**
     * 状态码的描述
     */
    String message;

    /**
     * 请求ID
     */
    String requestId;
}
