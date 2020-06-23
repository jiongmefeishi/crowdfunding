package com.zqt.crowd.service.api.auth;

import com.zqt.crowd.entity.auth.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author: zqtao
 * @description: 权限校验之权限业务层
 * @Date: 2020/6/11 10:17
 * @version: 1.0
 */
public interface AuthService {

    /**
     * 根据角色id 查询分配到的 权限列表
     * @param roleId 角色id
     * @return 权限id列表
     */
    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    /**
     * 根据管理员 id 查询已经分配到的 权限列表 名称
     * @param adminId 角色id
     * @return 权限id列表
     */
    List<String> getAssignedAuthNameByAdminId(Integer adminId);

    /**
     * 获取权限(Auth)列表
     * @return 权限列表
     */
    List<Auth> getAll();

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);
}
