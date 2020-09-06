package com.zqt.crowd.service.impl.project;

import com.zqt.crowd.mapper.project.ProjectPOMapper;
import com.zqt.crowd.service.project.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zqtao
 * @description:
 * @date: 2020/9/6
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectPOMapper projectPOMapper;
}
