package com.zqt.crowd.entity.po.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zqtao
 * @description: 项目分类实体类
 * @database_table: project_type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTypePO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类介绍
     */
    private String remark;
}