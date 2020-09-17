package com.zqt.crowd.api;

import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.entity.vo.order.OrderProjectVO;
import com.zqt.crowd.entity.vo.portal.DetailProjectVO;
import com.zqt.crowd.entity.vo.portal.PortalTypeVO;
import com.zqt.crowd.entity.vo.project.ProjectVO;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zqtao
 * @description: Mysql 远程服务调用接口
 * 利用feign实现远程调用member-mysql-provider 微服务中提供的接口
 * <p>
 * 注解 @FeignClient 开启 Feign 客户端功能，此注解开启表示当前的 API接口和一个 Provider 对应
 * value 中指定要调用的 provider 的服务名
 * fallbackFactory 中指定 consumer 调用的 provider 不可用时提供的备用方案的工厂类型s
 */
@FeignClient(value = "crowdfunding-member-mysql-provider")
public interface MysqlRemoteApi {
    /**
     * 获取首页展示数据列表
     */
    @GetMapping("project/get/portal/type/project/data/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    /**
     * 根据登录账号获取会员信息
     *
     * @param loginAcct 登录账号
     * @return MemberPO 会员信息
     */
    @GetMapping("get/member/po/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct);

    /**
     * 新增一条会员记录
     *
     * @param memberPO 会员信息
     * @return 新增结果
     */
    @RequestMapping("save/member/remote")
    ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);


    /**
     * 新增一条项目信息
     *
     * @param projectVO 项目信息
     * @param memberId  会员id
     */
    @RequestMapping("project/save/project/vo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

    /**
     * 根据项目id查询项目详情
     *
     * @param projectId 项目id
     */
    @GetMapping("project/get/project/detail/remote/{projectId}")
    ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);

    /**
     * 获取回报确认信息
     *
     * @param projectId 项目id
     * @param returnId  回报信息 id
     */
    @RequestMapping("order/get/order/project/vo/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId, @RequestParam("returnId") Integer returnId);

}
