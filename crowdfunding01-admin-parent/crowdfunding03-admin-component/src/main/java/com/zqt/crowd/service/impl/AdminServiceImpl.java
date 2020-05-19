package com.zqt.crowd.service.impl;

import com.zqt.crowd.entity.Admin;
import com.zqt.crowd.entity.AdminExample;
import com.zqt.crowd.mapper.AdminMapper;
import com.zqt.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: zqtao
 * @description:
 * @Date: 2020/5/17 22:35
 * @version: 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) {
        int insert = adminMapper.insert(admin);
    }

    public List<Admin> getAll() {
//        空Example就是查全部
        List<Admin> adminList = adminMapper.selectByExample(new AdminExample());
        return adminList;
    }
}
