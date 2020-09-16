package com.zqt.crowd.mapper.project;

import com.zqt.crowd.entity.vo.portal.DetailProjectVO;
import com.zqt.crowd.entity.vo.portal.DetailReturnVO;
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
    void testSelectDetailProjectVO() {
        Integer projectId = 17;

        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);

        log.info(detailProjectVO.getProjectId() + "");
        log.info(detailProjectVO.getProjectName());
        log.info(detailProjectVO.getProjectDescription());
        log.info(detailProjectVO.getFollowerCount() + "");
        log.info(detailProjectVO.getStatus() + "");
        log.info(detailProjectVO.getMoney() + "");
        log.info(detailProjectVO.getSupportMoney() + "");
        log.info(detailProjectVO.getPercentage() + "");
        log.info(detailProjectVO.getDeployDate() + "");
        log.info(detailProjectVO.getSupporterCount() + "");
        log.info(detailProjectVO.getHeaderPicturePath());

        List<String> detailPicturePathList = detailProjectVO.getDetailPicturePathList();
        for (String path : detailPicturePathList) {
            log.info("detail path=" + path);
        }

        List<DetailReturnVO> detailReturnVOList = detailProjectVO.getDetailReturnVOList();
        for (DetailReturnVO detailReturnVO : detailReturnVOList) {
            log.info(detailReturnVO.getReturnId() + "");
            log.info(detailReturnVO.getSupportMoney() + "");
            log.info(detailReturnVO.getSignalPurchase() + "");
            log.info(detailReturnVO.getPurchase() + "");
            log.info(detailReturnVO.getSupporterCount() + "");
            log.info(detailReturnVO.getFreight() + "");
            log.info(detailReturnVO.getReturnDate() + "");
            log.info(detailReturnVO.getContent() + "");
            log.info(detailReturnVO.getFreight() + "");
        }
    }


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