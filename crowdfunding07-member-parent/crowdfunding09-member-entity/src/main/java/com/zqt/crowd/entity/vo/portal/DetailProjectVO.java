package com.zqt.crowd.entity.vo.portal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zqtao
 * @description: 项目详情信息实体
 * @date: 2020/9/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProjectVO {

    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目描述
     */
    private String projectDescription;

    /**
     * 关注人数
     */
    private Integer followerCount;
    /**
     * 众筹状态 0-即将开始，1-众筹中，2-众筹成功，3-众筹失败
     */
    private Integer status;
    private Integer day;
    /**
     * 众筹状态描述
     */
    private String statusText;
    /**
     * 计划筹集的金额
     */
    private Long money;
    /**
     * 已筹集到的金额
     */
    private Long supportMoney;
    /**
     * 项目筹集完成百分比
     */
    private Integer percentage;
    /**
     * 项目发起时间
     */
    private String deployDate;
    /**
     * 项目众筹剩余时间
     */
    private Integer lastDay;
    /**
     * 支持人数
     */
    private Integer supporterCount;
    /**
     * 项目样例图片
     */
    private String headerPicturePath;
    /**
     * 项目详情图片列表
     */
    private List<String> detailPicturePathList;
    /**
     * 项目回报信息列表
     */
    private List<DetailReturnVO> detailReturnVOList;

}

