package com.zqt.crowd.service.project;

import com.zqt.crowd.entity.vo.project.ProjectVO;

/**
 * @author: zqtao
 * @description: 发起项目业务层
 * @date: 2020/9/6
 */
public interface ProjectService {
    void saveProject(ProjectVO projectVO, Integer memberId);
}
