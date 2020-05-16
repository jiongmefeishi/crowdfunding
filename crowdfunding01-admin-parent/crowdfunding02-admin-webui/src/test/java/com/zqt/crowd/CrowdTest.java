package com.zqt.crowd;

import com.zqt.crowd.entity.Admin;
import com.zqt.crowd.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sound.midi.Soundbank;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @auther: zqtao
 * @description: 测试数据库联通性
 * @Date: 2020/5/16 21:02
 * @version: 1.0
 */
// spring 整合Junit注解
@RunWith(SpringJUnit4ClassRunner.class)
// spring和mybatis配置文件
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testInsertAdmin() {
        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "jerry@qq.com", null);
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
}
