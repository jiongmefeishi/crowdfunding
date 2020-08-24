package com.zqt.crowd.controller;

import com.zqt.crowd.api.RedisRemoteApi;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * @author: zqtao
 * @description: 会员控制层
 */
@Slf4j
@RequestMapping("member")
@Controller
public class MemberController {

    /**
     * 加载redis 微服务 api
     */
    @Autowired
    private RedisRemoteApi redisRemoteApi;


    @RequestMapping("/auth/do/member/register")
    public String register(MemberPO memberVO, ModelMap modelMap) {


        return "";
    }

}
