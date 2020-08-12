package com.zqt.crowd;

import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.mapper.member.MemberPOMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@MapperScan("com.zqt.crowd.mapper")
@SpringBootTest
class MemberMysqlProviderApplicationTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    private Logger logger = LoggerFactory.getLogger(MemberMysqlProviderApplicationTest.class);

    @Test
    void contextLoads() {
    }

    @Test
    void testInsertMember() {
        MemberPO memberPO = new MemberPO();
        memberPO.setLoginAcct("pc111");

        memberPO.setGmtCreate(new Date());
        memberPO.setGmtModified(new Date());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String orignPassword = "123456";
        String curPassword = encoder.encode(orignPassword);
        memberPO.setUserPassword(curPassword);
        int insert = memberPOMapper.insert(memberPO);
        logger.info("insert : " + insert);
    }

    @Test
    void testConnect() throws SQLException {
        Connection connection = dataSource.getConnection();
        logger.debug(connection.toString());
    }
}