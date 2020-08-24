package com.zqt.crowd.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zqt.crowd.api.RedisRemoteApi;
import com.zqt.crowd.api.aliyun.AliyunSendSmsUtil;
import com.zqt.crowd.api.tencent.QqMailUtil;
import com.zqt.crowd.config.MailMessageProperties;
import com.zqt.crowd.config.SmsMessageProperties;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author: zqtao
 * @description: 处理短信发送，邮箱发送控制层
 */
@Slf4j
@RequestMapping("message")
@RestController
public class MessageController {

    /**
     * 加载短信发送参数（写在application.yml文件中的信息）
     */
    @Autowired
    private SmsMessageProperties sms;

    /**
     * 加载邮箱发送参数
     */
    @Autowired
    private MailMessageProperties mail;

    /**
     * 加载redis 微服务 api
     */
    @Autowired
    private RedisRemoteApi redisRemoteApi;

    /**
     * 加载配置文件属性：是否开启短信发送功能
     */
    @Value("${sms.active}")
    private boolean smsActive;

    /**
     * 加载配置文件属性：是否开启邮箱发送功能
     */
    @Value("${mail.active}")
    private boolean mailActive;

    /**
     * 发送短信验证码
     *
     * @param phoneNum 手机号
     * @return 失败返回错误原因
     */
    @ResponseBody
    @GetMapping("send/sms")
    public ResultEntity<String> sendSms(@RequestParam("phoneNum") String phoneNum) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "sendSms",
                "发送短信验证码"
        );

        // 查看是否开启短信发送功能
        if (!smsActive) {
            return ResultEntity.failed(CommonConstant.SMS_NOT_ACTIVE_MESSAGE);
        }

        // 1.发送验证码到phoneNum手机
        // 设置短信接收人手机号
        sms.setPhoneNumbers(phoneNum);

        ResultEntity<String> res = AliyunSendSmsUtil.sendSms(sms);

        // 2.判断短信发送结果
        if (res.getCode() == 0 && !StrUtil.hasEmpty(res.getData())) {
            // 3.如果发送成功，则将验证码存入Redis
            // ①从上一步操作的结果中获取随机生成的验证码
            String code = res.getData();
            // ②拼接一个用于在Redis中存储数据的key
            String key = CommonConstant.SMS_REDIS_CODE_PREFIX + phoneNum;
            // ③调用远程接口存入Redis, 设置 5 分钟内有效
            // 存储成功或失败都返回
            return redisRemoteApi.setRedisKeyValueWithTimeoutRemote(key, code, 5, TimeUnit.MINUTES);
        }
        // 发送短信失败，返回失败原因
        return res;
    }

    /**
     * 发送邮件验证码
     *
     * @param to 邮件接收人邮箱
     * @return 失败返回错误原因
     */
    @ResponseBody
    @GetMapping("send/mail")
    public ResultEntity<String> sendMail(@RequestParam("to") String to) {
        log.info(
                "\n\n执行方法: {} ，方法描述: {} \n",
                "sendMail",
                "发送邮件验证码"
        );
        // 查看是否开启邮箱发送功能
        if (!mailActive) {
            return ResultEntity.failed(CommonConstant.MAIL_NOT_ACTIVE_MESSAGE);
        }
        // 设置邮件接收人
        mail.setTo(to);
        // 设置验证码
        mail.setCode(RandomUtil.randomNumbers(6));
        // 设置邮件具体内容
        boolean success = QqMailUtil.qqMail(mail);
        if (success) {
            // 发送成功，存入redis
            String key = CommonConstant.MAIL_REDIS_CODE_PREFIX + to;
            log.info("code: " + mail.getCode());
            return redisRemoteApi.setRedisKeyValueWithTimeoutRemote(key, mail.getCode(), 5, TimeUnit.MINUTES);
        }
        return ResultEntity.failedDefault();
    }
}
