package com.zqt.crowd.mapper.project;

import com.zqt.crowd.entity.vo.portal.PortalProjectVO;
import com.zqt.crowd.entity.vo.portal.PortalTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@MapperScan("com.zqt.crowd.mapper")
@SpringBootTest
class ProjectPOMapperTest {

    @Autowired
    private ProjectPOMapper projectPOMapper;


    @Test
    void testSelectPortalTypeVOList() {

        List<PortalTypeVO> typeVOList = projectPOMapper.selectPortalTypeVOList();

        for (PortalTypeVO portalTypeVO : typeVOList) {
            String name = portalTypeVO.getName();
            String remark = portalTypeVO.getRemark();
            log.info("name=" + name + " remark=" + remark);

            List<PortalProjectVO> projectVOList = portalTypeVO.getPortalProjectVOList();
            for (PortalProjectVO portalProjectVO : projectVOList) {

                if (portalProjectVO == null) {
                    continue;
                }

                log.info(portalProjectVO.toString());
            }

        }
    }
}