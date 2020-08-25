package com.zqt.crowd.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zqt.crowd.api.MysqlRemoteApi;
import com.zqt.crowd.api.RedisRemoteApi;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.entity.vo.MemberRegisterVO;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static com.zqt.crowd.constant.CommonConstant.MAIL_REDIS_CODE_PREFIX;

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

    /**
     * 加载远程MySQL 微服务 api
     */
    @Autowired
    private MysqlRemoteApi mysqlRemoteApi;


    @RequestMapping("/auth/do/register")
    public String register(MemberRegisterVO memberVO, ModelMap modelMap) {

        // 1.验证码验证，优先考虑使用短信验证
        // 1.1 获取手机号,组装redis key
        String key = CommonConstant.SMS_REDIS_CODE_PREFIX + memberVO.getPhoneNum();
        // 1.2 从redis 查询验证码，存在校验验证码
        ResultEntity<String> redis = redisRemoteApi.getRedisStringValueByKeyRemote(key);

        String code = redis.getData();
        // 短信验证码不存在或者和用户输入的验证码不相同，执行邮箱验证码校验
        if (StrUtil.hasEmpty(code) || !code.equals(memberVO.getCode())) {
            // 短信验证码校验失败
            // 获取邮箱,组装redis key
            key = CommonConstant.MAIL_REDIS_CODE_PREFIX + memberVO.getEmail();
            redis = redisRemoteApi.getRedisStringValueByKeyRemote(key);
            code = redis.getData();
            if (StrUtil.hasEmpty(code) || !code.equals(memberVO.getCode())) {
                // 邮箱验证码校验失败, 验证码校验失败，注册结束
                modelMap.put(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_REDIS_NOT_FIND_CODE);
                // 失败返回注册页面
                return "member-reg";
            }
        }
        // 校验成功，删除redis 中的验证码，执行密码加密
        // 遗留问题1：redis验证码删除操作可能会失败，虽然不影响后续操作，但是会带来恶意注册风险，所以需要处理，涉及分布式操作，现在暂时不处理
        redisRemoteApi.removeRedisKeyRemote(key);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(memberVO.getUserPassword());
        memberVO.setUserPassword(encode);

        // 复制 MemberVO 为 MemberPO
        MemberPO memberPO = new MemberPO();
        BeanUtil.copyProperties(memberVO, memberPO, true);

        // 调用远程 MySQL 微服务方法，执行保存
        ResultEntity<String> save = mysqlRemoteApi.saveMember(memberPO);
        if (save.getResult().equals(ResultEntity.RESULT_FAILED)) {
            modelMap.put(CommonConstant.ATTR_NAME_MESSAGE, save.getMsg());
            // 失败返回注册页面
            return "member-reg";
        }
        return "member-login";
    }

}
