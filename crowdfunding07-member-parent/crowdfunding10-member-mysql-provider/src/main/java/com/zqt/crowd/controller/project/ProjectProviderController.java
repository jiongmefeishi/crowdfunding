package com.zqt.crowd.controller.project;

import com.zqt.crowd.service.project.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
