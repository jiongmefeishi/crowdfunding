package com.zqt.crowd.api.tencent.entity;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * @author: zqtao
 * @description: 封装qq邮箱请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailReqDO {

    /**
     * 收件人账号如：3455533@qq.com
     */
    private String to;

    /**
     * 主题
     */
    private String subject;

    /**
     * 是否是HTML
     */
    private Boolean htmlOrNot;

    /**
     * 附件列表
     */
    private File[] files;

    /**
     * 随机码(验证码)
     */
    private String code;

    /**
     * 签名名称
     */
    private String signName;

    /**
     * 正文
     * 签名名称 + 消息内容 + 验证码（有则带上）
     */
    private String content;
}
