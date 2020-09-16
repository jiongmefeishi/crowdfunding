package com.zqt.crowd.service.impl.project;

import cn.hutool.core.date.DateUtil;
import com.zqt.crowd.entity.po.member.MemberConfirmInfoPO;
import com.zqt.crowd.entity.po.member.MemberLaunchInfoPO;
import com.zqt.crowd.entity.po.project.ProjectPO;
import com.zqt.crowd.entity.vo.member.MemberConfirmInfoVO;
import com.zqt.crowd.entity.vo.member.MemberLaunchInfoVO;
import com.zqt.crowd.entity.vo.portal.DetailProjectVO;
import com.zqt.crowd.entity.vo.portal.PortalTypeVO;
import com.zqt.crowd.entity.vo.project.OrderReturnInfoVO;
import com.zqt.crowd.entity.vo.project.ProjectVO;
import com.zqt.crowd.mapper.member.MemberConfirmInfoPOMapper;
import com.zqt.crowd.mapper.member.MemberLaunchInfoPOMapper;
import com.zqt.crowd.mapper.project.OrderReturnInfoPOMapper;
import com.zqt.crowd.mapper.project.ProjectItemImgPOMapper;
import com.zqt.crowd.mapper.project.ProjectPOMapper;
import com.zqt.crowd.service.project.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: zqtao
 * @description: 发起项目业务层实现类
 * @date: 2020/9/6
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {

    /**
     * 加载项目确认信息数据层支持
     */
    @Autowired
    private MemberConfirmInfoPOMapper memberConfirmInfoPOMapper;

    /**
     * 加载项目回报信息数据层支持
     */
    @Autowired
    private OrderReturnInfoPOMapper orderReturnInfoPOMapper;

    /**
     * 加载 项目发起人信息数据层支持
     */
    @Autowired
    private MemberLaunchInfoPOMapper memberLaunchInfoPOMapper;

    /**
     * 加载项目数据层支持
     */
    @Autowired
    private ProjectPOMapper projectPOMapper;

    /**
     * 加载项目详情图片数据层支持
     */
    @Autowired
    private ProjectItemImgPOMapper projectItemImgPOMapper;

    /**
     * 新增一条众筹项目信息
     *
     * @param projectVO 众筹项目信息实体
     * @param memberId  会员 id
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveProject(ProjectVO projectVO, Integer memberId) {
        // 一、保存ProjectPO对象
        // 1.创建空的ProjectPO对象
        ProjectPO projectPO = new ProjectPO();

        // 2.把projectVO中的属性复制到projectPO中
        BeanUtils.copyProperties(projectVO, projectPO);

        // 3.把memberId设置到projectPO中
        projectPO.setMemberId(memberId);

        // 4.初始化创建时间
        projectPO.setCreateDate(DateUtil.now());

        // 5.初始化 status 设置成0，表示即将开始
        // 0-即将开始，1-众筹中，2-众筹成功，3-众筹失败
        projectPO.setStatus(0);

        // 3.保存projectPO
        // 为了能够获取到projectPO保存后的自增主键，需要到ProjectPOMapper.xml文件中进行相关设置
        // <insert id="insertSelective" useGeneratedKeys="true" keyProperty="id" ……
        projectPOMapper.insertSelective(projectPO);

        // 4.从projectPO对象这里获取自增主键
        Integer projectId = projectPO.getId();

        // 二、保存项目、分类的关联关系信息
        // 1.从ProjectVO对象中获取typeIdList
        List<Integer> typeIdList = projectVO.getTypeIdList();
        projectPOMapper.insertInnerTypeAndProject(typeIdList, projectId);

        // 三、保存项目、标签的关联关系信息
        List<Integer> tagIdList = projectVO.getTagIdList();
        projectPOMapper.insertInnerTagAndProject(tagIdList, projectId);

        // 四、保存项目中详情图片路径信息
        List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
        projectItemImgPOMapper.insertImgPathList(projectId, detailPicturePathList);

        // 五、保存项目发起人信息
        MemberLaunchInfoVO memberLaunchInfoVO = projectVO.getMemberLaunchInfoVO();
        // 创建PO对象，复制对象属性
        MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
        BeanUtils.copyProperties(memberLaunchInfoVO, memberLaunchInfoPO);
        memberLaunchInfoPO.setMemberId(memberId);

        memberLaunchInfoPOMapper.insert(memberLaunchInfoPO);

        // 六、保存项目回报信息
        List<OrderReturnInfoVO> returnVOList = projectVO.getOrderReturnInfoVOList();

        List<OrderReturnInfoVO> returnPOList = new ArrayList<>();

        for (OrderReturnInfoVO returnVO : returnVOList) {

            OrderReturnInfoVO returnPO = new OrderReturnInfoVO();
            BeanUtils.copyProperties(returnVO, returnPO);
            returnPOList.add(returnPO);
        }

        // 批量新增项目回报信息
        orderReturnInfoPOMapper.insertOrderReturnInfoPOBatch(returnPOList, projectId);

        // 七、保存项目确认信息
        MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
        BeanUtils.copyProperties(memberConfirmInfoVO, memberConfirmInfoPO);
        memberConfirmInfoPO.setMemberId(memberId);
        memberConfirmInfoPOMapper.insert(memberConfirmInfoPO);
    }

    /**
     * 获取首页展示数据列表
     */
    @Override
    public List<PortalTypeVO> getPortalTypeVO() {
        return projectPOMapper.selectPortalTypeVOList();
    }

    /**
     * 根据项目id查询项目详情
     *
     * @param projectId 项目id
     */
    @Override
    public DetailProjectVO getDetailProjectVO(Integer projectId) {

        // 1.查询得到DetailProjectVO对象
        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);

        // 2.根据status确定statusText
        Integer status = detailProjectVO.getStatus();

        switch (status) {
            case 0:
                detailProjectVO.setStatusText("审核中");
                break;
            case 1:
                detailProjectVO.setStatusText("众筹中");
                break;
            case 2:
                detailProjectVO.setStatusText("众筹成功");
                break;
            case 3:
                detailProjectVO.setStatusText("已关闭");
                break;

            default:
                break;
        }

        // 3.根据deployDate计算lastDay
        // 2020-10-15
        String deployDate = detailProjectVO.getDeployDate();

        // 获取当前日期
        Date currentDay = new Date();

        // 把众筹日期解析成Date类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date deployDay = format.parse(deployDate);

            // 获取当前当前日期的时间戳
            long currentTimeStamp = currentDay.getTime();

            // 获取众筹日期的时间戳
            long deployTimeStamp = deployDay.getTime();

            // 两个时间戳相减计算当前已经过去的时间
            long pastDays = (currentTimeStamp - deployTimeStamp) / 1000 / 60 / 60 / 24;

            // 获取总的众筹天数
            Integer totalDays = detailProjectVO.getDay();

            // 使用总的众筹天数减去已经过去的天数得到剩余天数
            Integer lastDay = (int) (totalDays - pastDays);

            detailProjectVO.setLastDay(lastDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return detailProjectVO;
    }

}
