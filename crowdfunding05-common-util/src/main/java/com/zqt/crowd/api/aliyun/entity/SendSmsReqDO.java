package com.zqt.crowd.api.aliyun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 封装阿里云短信API 发送短信请求参数 数据对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendSmsReqDO {


    /**
     * 阿里云短信服务器
     * dysmsapi.aliyuncs.com
     */
    private String sysDomain;

    /**
     * 版本
     * 默认：2017-05-25
     */
    private String sysVersion;

    /**
     * 服务器所在区域id
     * cn-hangzhou 表示华东（杭州）
     */
    private String regionId;

    /**
     * 接收短信的手机号码
     */
    private String phoneNumbers;
    /**
     * 短信签名名称。请在控制台签名管理页面签名名称一列查看
     * 必须是已添加、并通过审核的短信签名。
     */
    private String signName;
    /**
     * 短信模板ID。请在控制台模板管理页面模板CODE一列查看
     * 必须是已添加、并通过审核的短信签名；且发送国际/港澳台消息时，请使用国际/港澳台短信模版。
     */
    private String templateCode;

    /**
     * 主账号AccessKey的ID
     */
    private String accessKeyId;

    /**
     * 系统规定参数。取值：SendSms。
     */
    private String action;
}
