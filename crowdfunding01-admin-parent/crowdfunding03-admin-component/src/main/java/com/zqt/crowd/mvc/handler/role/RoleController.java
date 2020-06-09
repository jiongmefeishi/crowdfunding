package com.zqt.crowd.mvc.handler.role;

import com.github.pagehelper.PageInfo;
import com.zqt.crowd.entity.role.Role;
import com.zqt.crowd.service.api.role.RoleService;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: zqtao
 * @description:
 * @Date: 2020/5/31 8:54
 * @version: 1.0
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询 Role 记录
     *
     * @param keyword  查询关键词
     * @param pageNum  页码
     * @param pageSize 每页的数量，记录条数
     * @return ResultEntity<PageInfo < Role>> json数据
     */
    // 返回的是json 数据，所有请求路径也以json 结束
    @ResponseBody
    @RequestMapping(value = "/role/get/page/info.json", method = RequestMethod.POST)
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        // 1、调用service获取分页数据
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        // 2、封装数据到ResultEntity(如果某一层执行抛出异常，交给配置的全局Spring异常映射机制处理->1.注解 2.xml配置)
        return ResultEntity.successWithData(pageInfo);
    }

    /**
     * 新增一条 Role记录
     *
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "role/save.json", method = RequestMethod.POST)
    public ResultEntity<String> saveRole(Role role) {
        roleService.saveRole(role);

        return ResultEntity.successWithoutData();
    }

    /**
     * 更新一条 role 记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("role/update.json")
    public ResultEntity<String> updateRole(Role role) {
        roleService.updateRole(role);

        return ResultEntity.successWithoutData();
    }

    /**
     * 合并单条删除和批量删除，根据传入的 role id list 进行删除记录
     * @param roleIdList 角色id 集合
     */
    @ResponseBody
    @RequestMapping("/role/remove/by/role/id/array.json")
    public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList) {
        roleService.removeRole(roleIdList);
        return ResultEntity.successWithoutData();
    }
}
