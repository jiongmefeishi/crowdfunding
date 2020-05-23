package com.zqt.crowd.service.impl;

import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.Admin;
import com.zqt.crowd.entity.AdminExample;
import com.zqt.crowd.exception.LoginFailedException;
import com.zqt.crowd.mapper.AdminMapper;
import com.zqt.crowd.service.api.AdminService;
import com.zqt.crowd.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1、根据登录账号查询Admin对象
        // 创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        // 创建Criteria 对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // 在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        // 查询
        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        // 2、判断Admin对象是否为null
        if (adminList == null || adminList.size() == 0)
            // 3、null 抛出异常
            throw new LoginFailedException(CommonConstant.MESSAGE_LOGIN_FAILED);
        if (adminList.size() > 1)
            throw new RuntimeException(CommonConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);

        // 4、Admin不为null，则将数据库密码从admin对象中取出
        Admin admin = adminList.get(0);
        if (admin == null)
            throw new LoginFailedException(CommonConstant.MESSAGE_LOGIN_FAILED);
        String userPswdDB = admin.getUserPswd();

        // 5、将表单提交的明文密码进行加密处理
        String userPswdForm = MD5Util.md5(userPswd);

        // 6、比较密文
        if (!Objects.equals(userPswdDB, userPswdForm))
            // 7、不一致，抛出异常
            throw new LoginFailedException(CommonConstant.MESSAGE_LOGIN_FAILED);
        // 8、一致，返回
        return admin;
    }
}
