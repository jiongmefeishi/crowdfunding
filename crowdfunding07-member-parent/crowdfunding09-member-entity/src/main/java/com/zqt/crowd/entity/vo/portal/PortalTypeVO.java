package com.zqt.crowd.entity.vo.portal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zqtao
 * @description: 首页项目展示数据实体
 * @date: 2020/9/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalTypeVO {

    /**
     * 项目类型 id
     */
    private Integer id;

    /**
     * 项目类型名称
     */
    private String name;

    /**
     * 项目类型描述
     */
    private String remark;

    /**
     * 首页展示项目信息集合
     */
    private List<PortalProjectVO> portalProjectVOList;
}
