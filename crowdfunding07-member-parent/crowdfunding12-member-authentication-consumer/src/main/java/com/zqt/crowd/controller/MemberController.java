package com.zqt.crowd.controller;

import cn.hutool.core.util.StrUtil;
import com.zqt.crowd.api.RedisRemoteApi;
import com.zqt.crowd.api.aliyun.AliyunSendSmsUtil;
import com.zqt.crowd.config.SmsMessageProperties;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @author: zqtao
 * @description: 会员控制层
 */
@Controller
public class MemberController {

    /**
     * 加载短信发送参数（写在application.yml文件中的信息）
     */
    @Autowired
    private SmsMessageProperties sms;

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

    @ResponseBody
    @RequestMapping("/auth/member/send/sms")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {

        // 查看是否开启短信发送功能
        if (!smsActive) {
            return ResultEntity.failed(CommonConstant.MAIL_NOT_ACTIVE_MESSAGE);
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

}
