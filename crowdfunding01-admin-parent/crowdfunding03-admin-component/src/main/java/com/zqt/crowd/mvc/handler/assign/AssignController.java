package com.zqt.crowd.mvc.handler.assign;

import com.zqt.crowd.entity.role.Role;
import com.zqt.crowd.service.api.admin.AdminService;
import com.zqt.crowd.service.api.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 角色分配控制层
 * 给 Admin(管理员用户) 分配相应的 Role(角色)
 * @Date: 2020/6/10 15:20
 * @version: 1.0
 */
@Controller
public class AssignController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;


    /**
     * 转发到分配页面-将角色分配给管理用户
     *
     * @param adminId  adminId
     * @param modelMap request 的请求域
     * @return
     */
    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap) {

        // 1.查询已经分配的角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        // 2.查询未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        // 3.存入模型,实质就是放到了request的请求域
        // 本质上是：request.setAttribute(attrName, attrValue)
        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);
        return "assign/assign-role-to-admin";
    }

    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,

            // 我们允许用户在页面上取消所有已分配角色再提交表单，所以可以不提供roleIdList请求参数
            // 设置required=false表示这个请求参数不是必须的
            @RequestParam(value="roleIdList", required=false) List<Integer> roleIdList
    ) {

        adminService.saveAdminRoleRelationship(adminId, roleIdList);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }
}
