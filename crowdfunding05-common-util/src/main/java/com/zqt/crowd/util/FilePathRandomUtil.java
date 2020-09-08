package com.zqt.crowd.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: zqtao
 * @description: 文件上传名称随机工具
 */
@Slf4j
public class FilePathRandomUtil {

    /**
     * @param fileName 文件名称
     * @return 云服务器 存储的 fileName
     * @description: 生成唯一图片名称
     */
    public static String getRandomImgName(String fileName) {

        log.debug(
                "执行方法: {} ，方法描述: {} \n",
                "FilePathRandomUtil: getRandomImgName",
                "文件上传名称随机工具, 生成云服务器 存储的 fileName"
        );

        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            throw new IllegalArgumentException();
        }
        // 获取文件后缀
        String suffix = fileName.substring(index);
        // 生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 生成上传至云服务器的路径
        return "code/duck/" + DateUtil.today() + "-" + uuid + suffix;
    }

}
