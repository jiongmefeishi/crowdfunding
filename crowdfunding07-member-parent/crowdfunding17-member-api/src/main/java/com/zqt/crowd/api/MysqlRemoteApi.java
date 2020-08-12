package com.zqt.crowd.api;

import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zqtao
 * @description: Mysql 远程服务调用接口
 * 利用feign实现远程调用member-mysql-provider 微服务中提供的接口
 */
public interface MysqlRemoteApi {

    /**
     * 根据登录账号获取会员信息
     *
     * @param loginAcct 登录账号
     * @return MemberPO 会员信息
     */
    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberpoByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct);

}
