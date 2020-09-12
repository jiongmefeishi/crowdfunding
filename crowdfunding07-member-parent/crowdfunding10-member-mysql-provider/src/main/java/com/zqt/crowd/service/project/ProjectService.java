package com.zqt.crowd.service.project;

import com.zqt.crowd.entity.vo.portal.PortalTypeVO;
import com.zqt.crowd.entity.vo.project.ProjectVO;

import java.util.List;

/**
 * @author: zqtao
 * @description: 发起项目业务层
 * @date: 2020/9/6
 */
public interface ProjectService {

    /**
     * 新增一条众筹项目信息
     *
     * @param projectVO 众筹项目信息实体
     * @param memberId  会员 id
     */
    void saveProject(ProjectVO projectVO, Integer memberId);

    /**
     * 获取首页展示数据列表
     */
    List<PortalTypeVO> getPortalTypeVO();

}
