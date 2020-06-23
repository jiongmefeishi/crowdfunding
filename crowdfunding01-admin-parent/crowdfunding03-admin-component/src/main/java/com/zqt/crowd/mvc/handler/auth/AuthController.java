package com.zqt.crowd.mvc.handler.auth;

import com.zqt.crowd.service.api.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: zqtao
 * @description: 权限校验之权限控制层
 * @Date: 2020/6/11 10:18
 * @version: 1.0
 */
public class AuthController {

    @Autowired
    private AuthService authService;
}
