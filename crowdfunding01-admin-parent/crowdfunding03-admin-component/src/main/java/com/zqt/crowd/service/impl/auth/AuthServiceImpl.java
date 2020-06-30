package com.zqt.crowd.service.impl.auth;

import com.zqt.crowd.entity.auth.Auth;
import com.zqt.crowd.entity.auth.AuthExample;
import com.zqt.crowd.mapper.AuthMapper;
import com.zqt.crowd.service.api.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zqtao
 * @description: 权限校验之权限业务层实现类
 * @Date: 2020/6/11 10:17
 * @version: 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }

    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {

        // 获取roleId 的值
        List<Integer> list = map.get("roleId");
        Integer roleId = list.get(0);

        // 根据roleId删除久的关联关系
        authMapper.deleteOldRelationship(roleId);

        // 获取authIdList
        List<Integer> authIdList = map.get("authIdArray");
        // 有效性
        if (authIdList != null && !authIdList.isEmpty()) {
            // 建立新的关联关系
            authMapper.insertNewRelationship(roleId, authIdList);
        }
    }
}
