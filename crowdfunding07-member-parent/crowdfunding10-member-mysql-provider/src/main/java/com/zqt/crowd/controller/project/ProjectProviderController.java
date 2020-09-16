package com.zqt.crowd.controller.project;

import com.zqt.crowd.entity.vo.portal.DetailProjectVO;
import com.zqt.crowd.entity.vo.portal.PortalTypeVO;
import com.zqt.crowd.entity.vo.project.ProjectVO;
import com.zqt.crowd.service.project.ProjectService;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zqtao
 * @description: 发起项目控制层
 * @date: 2020/9/6
 */
@Slf4j
@RequestMapping("project")
@RestController
public class ProjectProviderController {

    @Autowired
    private ProjectService projectService;

    /**
     * 根据项目id查询项目详情
     *
     * @param projectId 项目id
     */
    @RequestMapping("/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId) {

        try {
            DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);

            return ResultEntity.successWithData(detailProjectVO);

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }


    /**
     * 获取首页展示数据列表
     */
    @GetMapping("get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote() {

        try {
            List<PortalTypeVO> portalTypeVOList = projectService.getPortalTypeVO();

            return ResultEntity.successWithData(portalTypeVOList);
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

    /**
     * 新增一条众筹项目信息
     *
     * @param projectVO 众筹项目信息实体
     * @param memberId  会员 id
     */
    @RequestMapping("save/project/vo/remote")
    public ResultEntity<String> saveProjectVORemote(
            @RequestBody ProjectVO projectVO,
            @RequestParam("memberId") Integer memberId) {

        try {
            // 调用“本地”Service执行保存
            projectService.saveProject(projectVO, memberId);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }
}
