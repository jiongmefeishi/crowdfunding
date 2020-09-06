package com.zqt.crowd.entity.vo.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zqtao
 * @description: 发起人确认信息封装
 * @date: 2020/9/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberConfirmInfoVO implements Serializable {

    private static final long serialVersionUID = 3752917093272429690L;
    /**
     * 易付宝企业账号
     */
    private String payNum;

    /**
     * 法人身份证号
     */
    private String cardNum;
}
