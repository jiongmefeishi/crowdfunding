package com.zqt.crowd.api.tencent;

import cn.hutool.extra.mail.MailUtil;
import com.zqt.crowd.api.tencent.entity.MailReqDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zqtao
 * @description: qq邮箱邮件发送工具
 */
public class QqMailUtil {

    /**
     * 发送qq 邮件
     *
     * @param mail qq邮件请求实体
     */
    public static boolean qqMail(MailReqDO mail) {
        Logger logger = LoggerFactory.getLogger(QqMailUtil.class);
        logger.info("发送qq 邮件");
        try {
            MailUtil.send(mail.getTo(), mail.getSubject(), mail.getContent(), mail.getHtmlOrNot(), mail.getFiles());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }
}
