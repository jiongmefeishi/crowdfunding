package com.zqt.crowd.service.impl.auth;

import com.zqt.crowd.mapper.AuthMapper;
import com.zqt.crowd.service.api.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: zqtao
 * @description: 权限校验之权限业务层实现类
 * @Date: 2020/6/11 10:17
 * @version: 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;
}
