package com.zqt.crowd;

import com.zqt.crowd.util.ResultEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zqtao
 * @description: 会员中心微服务
 * <p>
 * 前端页面系统在本工程
 */
@SpringBootApplication
public class MemberAuthenticationConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberAuthenticationConsumerApplication.class, args);
    }

}
