package com.zqt.crowd.api;

import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zqtao
 * @description: Mysql 远程服务调用接口
 * 利用feign实现远程调用member-mysql-provider 微服务中提供的接口
 *
 * 注解 @FeignClient 开启 Feign 客户端功能，此注解开启表示当前的 API接口和一个 Provider 对应
 * value 中指定要调用的 provider 的服务名
 * fallbackFactory 中指定 consumer 调用的 provider 不可用时提供的备用方案的工厂类型s
 */
@FeignClient(value = "crowdfunding-member-mysql-provider")
public interface MysqlRemoteApi {

    /**
     * 根据登录账号获取会员信息
     *
     * @param loginAcct 登录账号
     * @return MemberPO 会员信息
     */
    @GetMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberpoByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct);

    /**
     * 新增一条会员记录
     * @param memberPO 会员信息
     * @return 新增结果
     */
    @RequestMapping("/save/member/remote")
    ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);
}
