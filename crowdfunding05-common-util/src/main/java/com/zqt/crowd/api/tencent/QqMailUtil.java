package com.zqt.crowd.api.tencent;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
import com.zqt.crowd.api.tencent.entity.MailReqDO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: zqtao
 * @description: qq邮箱邮件发送工具
 */
@Slf4j
public class QqMailUtil {

    /**
     * 发送qq 邮件
     *
     * @param mail qq邮件请求实体
     */
    public static boolean qqMail(MailReqDO mail) {
        log.info("发送qq 邮件");
        try {
            // 拼接消息正文
            String content = mail.getContent();
            // 如果有验证码
            if (!StrUtil.hasEmpty()) {
                content = mail.getSignName()
                        + mail.getContent()
                        + "，您的验证码为："
                        + mail.getCode()
                        + "。工作人员不会索取，请妥善保存！";
            }
            MailUtil.send(mail.getTo(), mail.getSubject(), content, mail.getHtmlOrNot());
        } catch (Exception e) {
            log.info("邮件发送异常: " + e.getMessage());
            return false;
        }
        return true;
    }
}
