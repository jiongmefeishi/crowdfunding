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

    private String userName;

    private String email;

    private Integer authStatus;

    private Integer userType;

    private String realName;

    private String cardNum;

    private Integer acctType;

    private Date gmtCreate;

    private Date gmtModified;

}