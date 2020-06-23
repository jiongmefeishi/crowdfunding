package com.zqt.crowd.service.api.admin;

import com.github.pagehelper.PageInfo;
import com.zqt.crowd.entity.admin.Admin;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 管理员业务层接口
 * @Date: 2020/5/17 22:39
 * @version: 1.0
 */
public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    /**
     * 根据登录账号和密码查询 Admin
     *
     * @param loginAcct 登录账号
     * @param userPswd  登录密码
     * @return Admin对象
     */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);


    /**
     * 根据登录账号查询 Admin
     *
     * @param loginAcct 登录账号
     * @return Admin对象
     */
    Admin getAdminByLoginAcct(String loginAcct);

    /**
     * 分页查询 Admin 记录
     *
     * @param keyword  查询关键词
     * @param pageNum  页码
     * @param pageSize 每页的数量，记录条数
     * @return PageInfo<Admin>
     */
    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据 admin ID 删除一条记录
     *
     * @param adminId 用户ID
     */
    void remove(Integer adminId);

    /**
     * 根据adminID 查询管理员记录
     *
     * @param adminId 管理员ID
     * @return Admin
     */
    Admin getAdminById(Integer adminId);

    /**
     * 根据用户信息更新信息
     *
     * @param admin 管理员新信息
     */
    void update(Admin admin);

    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);
}
