package com.zqt.crowd.entity.po.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zqtao
 * @description: 发起人确认信息实体类
 * @database_table: member_confirm_info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberConfirmInfoPO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 会员 id
     */
    private Integer memberId;

    /**
     * 易付宝企业账号
     */
    private String payNum;

    /**
     * 法人身份证号
     */
    private String cardNum;
}