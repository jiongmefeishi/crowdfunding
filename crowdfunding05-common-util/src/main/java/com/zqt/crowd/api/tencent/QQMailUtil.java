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
public class QQMailUtil {

    /**
     * 发送qq 邮件
     * @param mail qq邮件请求实体
     */
    public static boolean qqMail(MailReqDO mail) {

        Logger logger = LoggerFactory.getLogger(QQMailUtil.class);

        logger.info("发送qq 邮件");
        try {
            String send = MailUtil.send(mail.getTo(), mail.getSubject(), mail.getContent(), mail.isHtml(), mail.getFiles());
            if (StrUtil.hasEmpty(send)) {
                return false;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    // 测试发送邮件
    public static void main(String[] args) {
        String send = MailUtil.send("482988002@qq.com", "测试", "邮件来自Hutool测试", false);
//        MailReqDO mail = new MailReqDO("482988002@qq.com", "怪兽营测试", false, null, "123456", "怪兽营", "友情提示，我在测试");
//        boolean success = qqMail(mail);
//        System.out.println(success);
        System.out.println(send);
    }
}
