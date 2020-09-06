package com.zqt.crowd.entity.po.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zqtao
 * @description: 项目标签实体类
 * @database_table: project_tag
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTagPO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 项目标签父id
     */
    private Integer pid;

    /**
     * 标签描述
     */
    private String name;
}