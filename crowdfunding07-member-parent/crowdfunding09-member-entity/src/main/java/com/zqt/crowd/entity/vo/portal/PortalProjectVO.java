package com.zqt.crowd.entity.vo.portal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 首页项目展示，项目具体信息
 * @date: 2020/9/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalProjectVO {

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目图像
     */
    private String headerPicturePath;

    /**
     * 项目期待筹集紫荆
     */
    private Integer money;

    /**
     * 项目发布日期
     */
    private String deployDate;

    /**
     * 项目筹集完成百分比
     */
    private Integer percentage;

    /**
     * 项目支持人数
     */
    private Integer supporter;

}
