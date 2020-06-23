package com.zqt.crowd.service.api.role;

import com.github.pagehelper.PageInfo;
import com.zqt.crowd.entity.role.Role;

import java.util.List;

/**
 * @author: zqtao
 * @description: 权限校验之角色业务层接口
 * @Date: 2020/5/31 8:51
 * @version: 1.0
 */
public interface RoleService {

    /**
     * 分页查询 Role 记录
     *
     * @param keyword  查询关键词
     * @param pageNum  页码
     * @param pageSize 每页的数量，记录条数
     * @return PageInfo<Role>
     */
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);


    /**
     * 新增一条 Role 记录
     * @param role role
     */
    void saveRole(Role role);

    /**
     * 更新一条 Role 记录
     * @param role Role
     */
    void updateRole(Role role);

    /**
     * 合并单条删除和批量删除
     * @param roleIdList role id 集合
     * @return 影响条数
     */
    int removeRole(List<Integer> roleIdList);

    /**
     * 根据 管理员id 查询已经分配的角色
     * @param adminId 管理员 id
     * @return List<Role>
     */
    List<Role> getAssignedRole(Integer adminId);

    /**
     * 根据 管理员id 查询已经分配的角色
     * @param adminId 管理员 id
     * @return List<Role>
     */
    List<Role> getUnAssignedRole(Integer adminId);
}
