package com.zqt.crowd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("project")
@Slf4j
public class ProjectConsumerController {

    @PostMapping("save/basic/info")
    public String saveProjectBasicInfo() {
        return null;
    }
}
