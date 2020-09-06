package com.zqt.crowd.entity.vo.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zqtao
 * @description: 项目发起人信息封装
 * @date: 2020/9/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLaunchInfoVO implements Serializable {

    private static final long serialVersionUID = -4077247650440319231L;
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
