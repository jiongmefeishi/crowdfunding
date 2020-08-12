package com.zqt.crowd.service;

import com.zqt.crowd.entity.po.member.MemberPO;

/**
 * @author: zqtao
 * @description: 会员业务层接口
 */
public interface MemberService {

    /**
     * 根据登录账户获取用户信息
     * @param loginAcct 登录账户
     * @return 用户信息
     */
    MemberPO getMemberpoByLoginAcctRemote(String loginAcct);
}
