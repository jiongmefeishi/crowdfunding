package com.zqt.crowd.entity.po.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zqtao
 * @description: 项目发起人信息实体类
 * @database_table: member_launch_info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLaunchInfoPO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 会员 id
     */
    private Integer memberId;

    /**
     * 简单介绍
     */
    private String descriptionSimple;

    /**
     * 详细介绍
     */
    private String descriptionDetail;

    /**
     * 联系电话
     */
    private String phoneNum;

    /**
     * 客服电话
     */
    private String serviceNum;
}