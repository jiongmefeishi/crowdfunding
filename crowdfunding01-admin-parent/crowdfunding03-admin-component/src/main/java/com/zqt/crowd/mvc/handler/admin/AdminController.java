package com.zqt.crowd.mvc.handler.admin;

import com.github.pagehelper.PageInfo;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.admin.Admin;
import com.zqt.crowd.service.api.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @auther: zqtao
 * @description: 管理员控制层
 * @Date: 2020/5/22 15:43
 * @version: 1.0
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 注销
     *
     * @param session session
     * @return 重定向到登录页面
     */
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {

        // 强制Session失效
        session.invalidate();

        return "redirect:/admin/to/login/page.html";
    }


    /**
     * 登录
     *
     * @param loginAcct 登录账户
     * @param userPswd  登录密码
     * @param session   session
     * @return 重定向到主页
     */
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

        return "redirect:/admin/to/main/page.html";
    }

    /**
     * 获取用户信息
     *
     * @param keyword  查询条件关键词
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param modelMap 存放记录
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            // 使用@RequestParam注解的defaultValue属性，指定默认值，在请求中没有携带对应参数时使用默认值
            // keyword默认值使用空字符串，和SQL语句配合实现两种情况适配
            @RequestParam(value = "keyword", defaultValue = "") String keyword,

            // pageNum默认值使用1，页码默认是第一页
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,

            // pageSize默认值使用5，每页默认显示5条记录
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,

            ModelMap modelMap) {

        // 调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);

        // 将PageInfo对象存入模型
        modelMap.addAttribute(CommonConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "admin/admin-page";
    }

    /**
     * 删除一条 admin 记录
     * @param adminId adminId
     * @param pageNum 页码
     * @param keyword 关键词
     * @param session session
     * @return 重定向到原本所在页面，不能使用转发，转发会重复提交删除
     */
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword,
            HttpSession session
    ) {

        // 获取当前登录用户 ID
        Admin admin = (Admin) session.getAttribute(CommonConstant.ATTR_NAME_LOGIN_ADMIN);
        Integer currentUserId = admin.getId();
        if (adminId.equals(currentUserId))
            throw new RuntimeException("非法操作：请勿删除自己");

        // 执行删除
        adminService.remove(adminId);
        // 页面跳转：回到分页页面

        // 尝试方案1：直接转发到admin-page.jsp会无法显示分页数据
        // return "admin-page";

        // 尝试方案2：转发到/admin/get/page.html地址，一旦刷新页面会重复执行删除浪费性能
        // return "forward:/admin/get/page.html";

        // 尝试方案3：重定向到/admin/get/page.html地址
        // 同时为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    /**
     * 新增一条 admin 记录
     * @param admin Admin
     * @return 重定向到分页页面
     */
    @RequestMapping("/admin/save.html")
    public String addAdmin(Admin admin) {
        // 执行保存
        adminService.saveAdmin(admin);
        // 重定向到分页页面，使用重定向是为了避免刷新浏览器重复提交表单
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    /**
     * 点击修改按钮，查询需要修改的Admin的信息，保存到ModelMap中，并转发到修改页面
     *
     * @param adminId 需要修改的目标Admin的 adminID
     * @return 转发到修改页面
     */
    @RequestMapping("/admin/to/edit/page.html")
    public String toEditPage(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap
    ) {

        // 1、根据ID查询管理员信息
        Admin admin = adminService.getAdminById(adminId);
        // 2、封装到 ModelMap 中
        modelMap.addAttribute("admin", admin);

        return "admin/admin-edit";
    }

    /**
     * 更新admin 信息
     *
     * @param admin   需要更新的信息封装到Admin 对象
     * @param pageNum 页码
     * @param keyword 关键词
     * @return
     */
    @RequestMapping("/admin/update.html")
    public String update(
            Admin admin,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword
    ) {

        // 更新Admin 信息
        adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

}
