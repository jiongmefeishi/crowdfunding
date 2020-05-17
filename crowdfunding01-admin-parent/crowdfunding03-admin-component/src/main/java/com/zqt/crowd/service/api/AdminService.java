package com.zqt.crowd.service.api;

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
}
