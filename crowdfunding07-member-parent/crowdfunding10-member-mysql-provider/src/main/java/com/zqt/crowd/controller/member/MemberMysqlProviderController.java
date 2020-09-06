package com.zqt.crowd.controller.member;

import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.service.member.MemberService;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * @author zqtao
 * @description: Member 会员数据暴露控制层，向外暴露接口请求 Mysql 中会员信息
 */
@Slf4j
@RestController
public class MemberMysqlProviderController {

    @Autowired
    private MemberService memberService;

    /**
     * 根据登录账号获取会员信息
     *
     * @param loginAcct 登录账号
     * @return MemberPO 会员信息
     */
    @GetMapping("get/member/po/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct) {
        log.info(
                "\n\n执行方法: {}  ，方法描述: {} \n",
                "getMemberPOByLoginAcctRemote",
                "根据登录账号获取会员信息"
        );
        try {
            MemberPO m = memberService.getMemberPOByLoginAcctRemote(loginAcct);
            return ResultEntity.successWithData(m);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 保存一条会员记录
     *
     * @param memberPO 会员信息实体
     * @return 保存结果
     */
    @RequestMapping("save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO) {
        log.info(
                "\n\n执行方法: {}  ，方法描述: {} \n",
                "saveMember",
                "保存一条会员记录"
        );
        try {
            if (memberService.saveMember(memberPO) == 1) {
                return ResultEntity.successWithoutData();
            }
        } catch (Exception e) {

            // 新增记录已存在
            if (e instanceof DuplicateKeyException) {
                return ResultEntity.failed(CommonConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            return ResultEntity.failed(e.getMessage());
        }
        return ResultEntity.failedDefault();
    }
}
