package com.zqt.crowd.entity.vo.project;

import com.zqt.crowd.entity.vo.member.MemberConfirmInfoVO;
import com.zqt.crowd.entity.vo.member.MemberLaunchInfoVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zqtao
 * @description: 项目信息封装
 * @date: 2020/9/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVO implements Serializable {

    private static final long serialVersionUID = 2056921123330080203L;
    /**
     * 分类id集合
     */
    private List<Integer> typeIdList;

    /**
     * 标签id集合
     */
    private List<Integer> tagIdList;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目描述
     */
    private String projectDescription;

    /**
     * 计划筹集的金额
     */
    private Long money;

    /**
     * 筹集资金的天数
     */
    private Integer day;

    /**
     * 创建项目的日期
     */
    private String createDate;

    /**
     * 头图路径
     */
    private String headerPicturePath;

    /**
     * 详情图片的路径
     */
    private List<String> detailPicturePathList;

    /**
     * 发起人信息
     */
    private MemberLaunchInfoVO memberLaunchInfoVO;

    /**
     * 回报信息集合
     */
    private List<OrderReturnInfoVO> orderReturnInfoVOList;

    /**
     * 发起人确认信息
     */
    private MemberConfirmInfoVO memberConfirmInfoVO;
}

