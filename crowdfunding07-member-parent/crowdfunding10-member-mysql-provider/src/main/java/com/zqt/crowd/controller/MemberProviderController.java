package com.zqt.crowd.controller;

import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.service.MemberService;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zqtao
 * @description: Member 会员数据暴露控制层，向外暴露接口请求 Mysql 中会员信息
 */
@RestController
public class MemberProviderController {

    @Autowired
    private MemberService memberService;

    /**
     * 根据登录账号获取会员信息
     *
     * @param loginAcct 登录账号
     * @return MemberPO 会员信息
     */
    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberpoByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct) {
        try {
            MemberPO m = memberService.getMemberpoByLoginAcctRemote(loginAcct);
            return ResultEntity.successWithData(m);
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failedDefault();
        }
    }
}
