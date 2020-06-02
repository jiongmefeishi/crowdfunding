package com.zqt.crowd.service.api.role;

import com.github.pagehelper.PageInfo;
import com.zqt.crowd.entity.role.Role;

/**
 * @auther: zqtao
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
}
