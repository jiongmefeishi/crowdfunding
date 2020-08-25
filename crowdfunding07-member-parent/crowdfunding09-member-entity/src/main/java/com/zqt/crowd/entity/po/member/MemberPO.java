package com.zqt.crowd.entity.po.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zqtao
 * @description: 会员实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPO {

    /**
     * 数据库自增主键
     */
    private Integer id;

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
     * 实名认证状态: 0- 未实名认证， 1- 实名认证申请中， 2- 已实名认证
     */
    private Integer authStatus;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证
     */
    private String cardNum;

    /**
     * 账户类型: 0- 企业， 1- 个体， 2- 个人， 3- 政府
     */
    private Integer acctType;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}