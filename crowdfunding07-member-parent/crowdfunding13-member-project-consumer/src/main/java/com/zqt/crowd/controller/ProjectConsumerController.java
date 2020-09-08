package com.zqt.crowd.controller;

import cn.hutool.core.util.StrUtil;
import com.netflix.discovery.converters.Auto;
import com.zqt.crowd.api.MysqlRemoteApi;
import com.zqt.crowd.api.qiniu.CloudStorageUtil;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.vo.member.MemberConfirmInfoVO;
import com.zqt.crowd.entity.vo.member.MemberLoginVO;
import com.zqt.crowd.entity.vo.project.OrderReturnInfoVO;
import com.zqt.crowd.entity.vo.project.ProjectVO;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("project")
@Slf4j
public class ProjectConsumerController {

    /**
     * 加载远程mysql的微服务接口操作api
     */
    @Autowired
    private MysqlRemoteApi mysqlRemoteApi;

    /**
     * 网关统一重定向地址
     */
    @Value("${my.redirect.path}")
    private String REDIRECT_PATH;

    /**
     * 加载云存储配置
     */
    @Autowired
    private com.zqt.crowd.config.CloudStorageProperties CloudStorageProperties;

    /**
     * 保存用户基本信息，上传头像和项目详情图片
     *
     * @param projectVO         接收除了上传图片之外的其他普通数据
     * @param headerPicture     接收上传的头图
     * @param detailPictureList 接收上传的详情图片
     * @param session           用来将收集了一部分数据的ProjectVO对象存入Session域
     * @param modelMap          用来在当前操作失败后返回上一个表单页面时携带提示消息
     */
    @PostMapping("save/basic/info")
    public String saveProjectBasicInfo(
            ProjectVO projectVO,
            MultipartFile headerPicture,
            List<MultipartFile> detailPictureList,
            HttpSession session,
            ModelMap modelMap
    ) throws IOException {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "ProjectConsumerController: saveProjectBasicInfo",
                "保存用户基本信息，上传头像和项目详情图片"
        );

        // 一、完成头图上传
        // 1.获取当前headerPicture对象是否为空
        boolean headerPictureIsEmpty = headerPicture.isEmpty();

        if (headerPictureIsEmpty) {

            // 2.如果没有上传头图则返回到表单页面并显示错误消息
            modelMap.addAttribute(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_HEADER_PIC_EMPTY);
            return "project-launch";
        }

        // 用户上传了头像，执行上传
        String path = CloudStorageUtil.uploadImg((FileInputStream) headerPicture.getInputStream(), headerPicture.getOriginalFilename(), CloudStorageProperties);
        if (!StrUtil.hasEmpty(path)) {
            log.info("\n\n{} : " + path, "文件上传成功，七牛云外链");
            // 3.如果成功则从返回的数据中获取图片访问路径存入ProjectVO对象中
            projectVO.setHeaderPicturePath(path);
        } else {
            // 4.如果上传失败则返回到表单页面并显示错误消息
            modelMap.addAttribute(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);

            return "project-launch";
        }

        // 二、上传详情图片
        // 1.创建一个用来存放详情图片路径的集合
        List<String> detailPicturePathList = new ArrayList<String>();

        // 2.检查detailPictureList是否有效
        if (detailPictureList == null || detailPictureList.size() == 0) {
            modelMap.addAttribute(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_DETAIL_PIC_EMPTY);

            return "project-launch";
        }

        // 3、遍历detailPictureList集合，执行上传

        for (MultipartFile detailPicture : detailPictureList) {

            // 4.当前detailPicture是否为空
            if (detailPicture.isEmpty()) {
                // 5.检测到详情图片中单个文件为空也是回去显示错误消息
                modelMap.addAttribute(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_DETAIL_PIC_EMPTY);
                return "project-launch";
            }

            // 6.执行上传
            String detailPicturePath = CloudStorageUtil.uploadImg((FileInputStream) detailPicture.getInputStream(), detailPicture.getOriginalFilename(), CloudStorageProperties);

            if (!StrUtil.hasEmpty(detailPicturePath)) {
                log.info("\n\n{} : " + path, "文件上传成功，七牛云外链");
                // 7.如果成功则从返回的数据中获取图片访问路径存入ProjectVO对象中
                detailPicturePathList.add(detailPicturePath);
            } else {
                // 8.如果上传失败则返回到表单页面并显示错误消息
                modelMap.addAttribute(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);

                return "project-launch";
            }

            // 9.将存放了详情图片访问路径的集合存入ProjectVO中
            projectVO.setDetailPicturePathList(detailPicturePathList);
        }

        // 三、后续操作
        // 1.将ProjectVO对象存入Session域
        session.setAttribute(CommonConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);

        // 2.以完整的访问路径前往下一个收集回报信息的页面
        return "redirect:" + REDIRECT_PATH + "/pj/return/info/page";
    }


    /**
     * 填充回报信息
     *
     * @param session      session
     * @param returnInfoVO 回报信息
     */
    @ResponseBody
    @PostMapping("save/return/info")
    public ResultEntity<String> saveOrderReturnInfo(HttpSession session, OrderReturnInfoVO returnInfoVO) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "ProjectConsumerController: saveOrderReturnInfo",
                "填充回报信息"
        );

        try {
            // 1.从session域中读取缓存的ProjectVO对象
            ProjectVO projectVO = (ProjectVO) session.getAttribute(CommonConstant.ATTR_NAME_TEMPLE_PROJECT);
            // 2.判断对象有效性
            if (projectVO == null) {
                return ResultEntity.failed(CommonConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
            }
            // 3.获取ProjectVO存储回报信息的集合
            List<OrderReturnInfoVO> orderReturnInfoVOList = projectVO.getOrderReturnInfoVOList();
            if (orderReturnInfoVOList == null || orderReturnInfoVOList.isEmpty()) {
                // 如果不存在，创建集合对象对returnVOList进行初始化
                orderReturnInfoVOList = new ArrayList<OrderReturnInfoVO>();
                // 设置回ProjectVO 对象
                projectVO.setOrderReturnInfoVOList(orderReturnInfoVOList);
            }

            // 4.填充前端收集到的回报信息
            orderReturnInfoVOList.add(returnInfoVO);
            // 5.把数据有变化的ProjectVO对象重新存入Session域，以确保新的数据最终能够存入Redis
            session.setAttribute(CommonConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
            // 6.所有操作成功完成返回成功
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 上传回报图片
     * <p>
     * JavaScript代码：formData.append("returnPicture", file);
     * returnPicture是请求参数的名字
     * file是请求参数的值，也就是要上传的文件
     *
     * @param file 回报图片
     */
    @ResponseBody
    @PostMapping("save/return/picture")
    public ResultEntity<String> saveOrderReturnPicture(@RequestParam("returnPicture") MultipartFile file) throws IOException {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "ProjectConsumerController: saveOrderReturnPicture",
                "上传回报图片"
        );

        if (file != null) {
            // 执行上传
            String path = CloudStorageUtil.uploadImg((FileInputStream) file.getInputStream(), file.getOriginalFilename(), CloudStorageProperties);
            if (!StrUtil.hasEmpty(path)) {
                log.info("\n\n{} : " + path, "文件上传成功，七牛云外链");
                return ResultEntity.successWithoutData();
            }
        }
        return ResultEntity.failedDefault();
    }

    @PostMapping("save/confirm/info")
    public String saveConfirmInfo(HttpSession session, MemberConfirmInfoVO memberConfirmInfoVO, ModelMap modelMap) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "ProjectConsumerController: saveConfirmInfo",
                "填充确认信息"
        );

        // 1.从Session域读取之前临时存储的ProjectVO对象
        ProjectVO projectVO = (ProjectVO) session.getAttribute(CommonConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 2.如果projectVO为null
        if (projectVO == null) {
            throw new RuntimeException(CommonConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
        }

        // 3.将确认信息数据设置到projectVO对象中
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        // 4.从Session域读取当前登录的用户
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CommonConstant.MESSAGE_LOGIN_USER_MEMBER);

        Integer memberId = memberLoginVO.getId();


        // 5.调用远程方法保存projectVO对象
        ResultEntity<String> save = mysqlRemoteApi.saveProjectVORemote(projectVO, memberId);

        // 6.判断远程的保存操作是否成功
        String result = save.getResult();
        if (ResultEntity.RESULT_FAILED.equals(result)) {

            modelMap.addAttribute(CommonConstant.ATTR_NAME_MESSAGE, save.getMsg());
            return "project-confirm";
        }

        // 7.将临时的ProjectVO对象从Session域移除
        session.removeAttribute(CommonConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 8.如果远程保存成功则跳转到最终完成页面
        return "redirect:" + REDIRECT_PATH + "project/create/success";
    }
}
