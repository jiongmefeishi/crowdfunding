package com.zqt.crowd.api.tencent;

import cn.hutool.core.util.StrUtil;
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
     * @param mail qq邮件请求实体
     */
    public static boolean qqMail(MailReqDO mail) {

        Logger logger = LoggerFactory.getLogger(QqMailUtil.class);

        logger.info("发送qq 邮件");
        try {
            String send = MailUtil.send(mail.getTo(), mail.getSubject(), mail.getContent(), mail.getHtmlOrNot(), mail.getFiles());
            if (StrUtil.hasEmpty(send)) {
                return false;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String send = MailUtil.send("482988002@qq.com", "测试", "邮件来自Hutool测试", false);
        System.out.println(send);
    }
}
