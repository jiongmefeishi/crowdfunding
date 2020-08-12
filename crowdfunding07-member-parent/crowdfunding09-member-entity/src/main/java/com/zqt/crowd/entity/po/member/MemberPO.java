package com.zqt.crowd.entity.po.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPO {
    private Integer id;

    private String loginAcct;

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