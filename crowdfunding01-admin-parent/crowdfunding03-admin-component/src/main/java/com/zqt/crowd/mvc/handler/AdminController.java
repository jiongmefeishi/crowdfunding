package com.zqt.crowd.mvc.handler;

import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.Admin;
import com.zqt.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @auther: zqtao
 * @description:
 * @Date: 2020/5/22 15:43
 * @version: 1.0
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd,
            HttpSession session
    ) {

        // 调用Service方法执行登录检查
        // 这个方法如果能够返回admin对象说明登录成功，如果账号、密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);

        System.out.println(admin);
        // 将登录成功返回的admin对象存入Session域
        session.setAttribute(CommonConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        return "admin-main";
    }
}
