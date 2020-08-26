package com.zqt.crowd.entity.vo.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员登录后显示信息实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

}
