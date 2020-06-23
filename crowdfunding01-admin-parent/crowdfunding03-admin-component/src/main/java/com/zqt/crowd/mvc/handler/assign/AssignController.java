package com.zqt.crowd.mvc.handler.assign;

import com.zqt.crowd.entity.auth.Auth;
import com.zqt.crowd.entity.role.Role;
import com.zqt.crowd.service.api.admin.AdminService;
import com.zqt.crowd.service.api.auth.AuthService;
import com.zqt.crowd.service.api.role.RoleService;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author: zqtao
 * @description: 角色分配（Assign）控制层
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

    @Autowired
    private AuthService authService;


    /**
     * 转发到分配页面-将角色分配给管理用户
     *
     * @param adminId  adminId
     * @param modelMap request 的请求域
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

    /**
     * 根据adminId 和 roleIdList 保存关联关系
     *
     * @param adminId    管理员用户的 id
     * @param pageNum    页码
     * @param keyword    关键词
     * @param roleIdList 角色id 集合
     * @return 重定向到管理员列表
     */
    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,

            // 我们允许用户在页面上取消所有已分配角色再提交表单，所以可以不提供roleIdList请求参数
            // 设置required=false表示这个请求参数不是必须的
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList
    ) {

        adminService.saveAdminRoleRelationship(adminId, roleIdList);

        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    /**
     * 根据 roleId 查询关联的 authId 列表
     *
     * @param roleId 角色 id
     * @return 权限列表
     */
    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId) {

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithData(authIdList);
    }

    /**
     * 获取权限(Auth)列表
     *
     * @return 权限列表
     */
    @ResponseBody
    @RequestMapping("/assign/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth() {
        List<Auth> authList = authService.getAll();
        return ResultEntity.successWithData(authList);
    }


    /**
     * 根据角色id和权限id列表,进行关系关联
     *
     * @param map 存放的是角色id, 和权限列表
     */
    @ResponseBody
    @RequestMapping("/assign/do/role/assign/auth.json")
    public ResultEntity<String> saveRoleAuthRelationship(
            @RequestBody Map<String, List<Integer>> map) {
        authService.saveRoleAuthRelationship(map);
        return ResultEntity.successWithoutData();
    }

}
