package com.zqt.crowd.controller;

import cn.hutool.core.util.StrUtil;
import com.zqt.crowd.api.qiniu.CloudStorageUtil;
import com.zqt.crowd.config.CloudStorageProperties;
import com.zqt.crowd.util.FilePathRandomUtil;
import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: zqtao
 * @description: 云对象存储
 * <p>
 * 目前使用的对象存储服务器是七牛云
 */
@Slf4j
@RequestMapping("oss/storage")
@RestController
public class CloudStorageController {

    /**
     * 加载云存储配置
     */
    @Autowired
    private CloudStorageProperties CloudStorageProperties;

    /**
     * 图片上传
     *
     * @param file 文件
     */
    @PostMapping(value = "image")
    private ResultEntity<String> upLoadImage(@RequestParam("file") MultipartFile file) throws IOException {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "CloudStorageController upLoadImage",
                "图片上传"
        );

        // 获取文件的名称
        String fileName = file.getOriginalFilename();

        if (!file.isEmpty()) {
            // 上传文件
            String path = CloudStorageUtil.uploadImg((FileInputStream) file.getInputStream(), fileName, CloudStorageProperties);
            if (!StrUtil.hasEmpty(path)) {
                log.info("\n\n{} : " + path, "文件上传成功，七牛云外链");
                return ResultEntity.successWithData(path);
            }
        }
        return ResultEntity.failedDefault();
    }
}
