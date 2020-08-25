package com.zqt.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 会员注册表单信息实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterVO {
    /**
     * 登录账户
     */
    private String loginAcct;

    /**
     * 登录密码
     */
    private String userPassword;
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 短信验证码 or 邮箱验证码
     */
    private String code;
}
