package com.zqt.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.zqt.crowd.entity.Admin;

import java.util.List;

/**
 * @auther: zqtao
 * @description:
 * @Date: 2020/5/17 22:39
 * @version: 1.0
 */
public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

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
     * @param adminId 用户ID
     */
    void remove(Integer adminId);
}
