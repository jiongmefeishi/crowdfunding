package com.zqt.crowd.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zqt.crowd.api.MysqlRemoteApi;
import com.zqt.crowd.api.RedisRemoteApi;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.entity.vo.member.MemberLoginVO;
import com.zqt.crowd.entity.vo.member.MemberRegisterVO;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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

    /**
     * 会员登录
     *
     * @param loginAcct    登录账户
     * @param userPassword 登录密码
     * @param modelMap     返回值存储 map
     * @param session      session
     */
    @PostMapping("/auth/do/login")
    public String login(@RequestParam("loginAcct") String loginAcct,
                        @RequestParam("userPassword") String userPassword,
                        ModelMap modelMap,
                        HttpSession session) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "login",
                "会员登录"
        );
        // 1.验证账号
        ResultEntity<MemberPO> mysql = mysqlRemoteApi.getMemberPOByLoginAcctRemote(loginAcct);

        MemberPO memberPO = mysql.getData();
        if (memberPO == null) {
            // 账号不存在
            modelMap.put(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_LOGIN_ACCT_INVALID);
            return "member-reg";
        }

        // 2.验证密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(userPassword, memberPO.getUserPassword());

        if (!matches) {
            // 密码错误
            modelMap.put(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }

        // 创建MemberLoginVO对象存入Session域
        MemberLoginVO loginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUserName(), memberPO.getEmail());
        session.setAttribute(CommonConstant.MESSAGE_LOGIN_USER_MEMBER, loginVO);

        return "member-center";
    }


    /**
     * 会员注册
     *
     * @param memberVO 注册信息
     * @return 注册成功跳转登录页
     */
    @PostMapping("/auth/do/register")
    public String register(MemberRegisterVO memberVO, ModelMap modelMap) {
        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "register",
                "会员注册"
        );

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
