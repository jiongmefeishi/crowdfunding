package com.zqt.crowd.service.member;

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
    MemberPO getMemberPOByLoginAcctRemote(String loginAcct);

    /**
     * 新增一条会员记录
     *
     * @param memberPO 会员信息
     * @return 影响行数
     */
    int saveMember(MemberPO memberPO);
}
