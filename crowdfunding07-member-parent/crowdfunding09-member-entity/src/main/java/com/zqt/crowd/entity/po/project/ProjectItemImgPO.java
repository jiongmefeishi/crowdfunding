package com.zqt.crowd.entity.po.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zqtao
 * @description: 项目详情图片实体类
 * @database_table: project_item_img
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectItemImgPO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 项目 id
     */
    private Integer projectId;

    /**
     * 图片存储路径
     */
    private String itemImgPath;
}