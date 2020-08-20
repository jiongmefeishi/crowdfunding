package com.zqt.crowd.api.tencent.entity;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
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
    private boolean isHtml;

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

    public String getContent() {

        // 验证码是否存在
        if (!StrUtil.hasEmpty(this.code)) {
            this.content = this.signName
                    + this.content
                    + "，您的验证码为："
                    + this.code
                    + "。工作人员不会索取，请妥善保存！";
        }
        return this.content;
    }
}
