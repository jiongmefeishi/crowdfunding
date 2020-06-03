package com.zqt.crowd.service.impl.role;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zqt.crowd.entity.role.Role;
import com.zqt.crowd.entity.role.RoleExample;
import com.zqt.crowd.mapper.role.RoleMapper;
import com.zqt.crowd.service.api.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 权限校验之角色业务层实现类
 * @Date: 2020/5/31 8:52
 * @version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1、开启分页查询功能
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询
        List<Role> roleList = roleMapper.selectByKeyword(keyword);
        // 封装为PageInfo对象返回
        return new PageInfo<>(roleList);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int removeRole(List<Integer> roleIdList) {
        // 创建 Example 模式查询对象
        RoleExample roleExample = new RoleExample();
        // 创建条件对象 Criteria
        RoleExample.Criteria criteria = roleExample.createCriteria();
        // 增加匹配条件
        // where id in (1,2,3,4)
        criteria.andIdIn(roleIdList);
        // 执行删除 delete form t_role where id in (1,2,3,4)
        int num = roleMapper.deleteByExample(roleExample);
        return num;
    }
}
