package com.zqt.crowd.mvc.config;

import com.zqt.crowd.entity.admin.Admin;
import com.zqt.crowd.entity.role.Role;
import com.zqt.crowd.service.api.admin.AdminService;
import com.zqt.crowd.service.api.auth.AuthService;
import com.zqt.crowd.service.api.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zqtao
 * @description: 扩展实现 spring security 登录认证需要的 UserDetailsService 服务
 * 用来对数据库查询的权限列表信息和角色列表信息进行封装加工
 * <p>
 * <p>
 * 将所有的权限名称和角色名称都存储进 List<GrantedAuthority> 集合
 * spring security 对集合中的角色和权限的区分，根据前缀进行区分
 * 角色名称添加前缀：ROLE_
 * 权限名称不做处理
 * @Date: 2020/6/22
 */

// @Component 标注当前类是一个组件，并且这个类组件交给Spring管理
// 当前组件用于扩展UserDetailsService， 使用需要在 WebAppSecurityConfig 配置类中注册使用
@Component
public class SecurityUserDetailService implements UserDetailsService {

    // 装配服务
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.根据 账号名称，查询 admin 对象
        Admin admin = adminService.getAdminByLoginAcct(username);
        Integer adminId = admin.getId();

        // 2.根据 adminId 查询角色信息
        List<Role> roleList = roleService.getAssignedRole(adminId);

        // 3.根据 adminId 查询权限名称信息
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);

        // 4.创建集合来存储 GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 5.遍历角色信息列表，将角色名称 封装进 GrantedAuthority 对象中进行存储
        for (Role role : roleList) {
            // 获取角色名称，并添加前缀 ROLE_
            String roleName = "ROLE_" + role.getName();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(grantedAuthority);
        }

        // 6.遍历权限信息列表，将权限名称 封装进 GrantedAuthority 对象中进行存储
        for (String authName : authNameList) {
            // 权限不需要添加前缀
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(grantedAuthority);
        }

        return new SecurityAdmin(admin, authorities);
    }
}
