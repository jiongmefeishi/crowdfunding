package com.zqt.crowd.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;

/**
 * @author: zqtao
 * @description: 文件上传名称随机工具
 */
public class FilePathRandomUtil {

    /**
     * @param fileName 文件名称
     * @return 云服务器 存储的 fileName
     * @description: 生成唯一图片名称
     */
    public static String getRandomImgName(String fileName) {

        int index = fileName.lastIndexOf(".");

        if ((fileName == null || fileName.isEmpty()) || index == -1) {
            throw new IllegalArgumentException();
        }
        // 获取文件后缀
        String suffix = fileName.substring(index);
        // 生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 生成上传至云服务器的路径
        String path = "code/duck/" + DateUtil.today() + "-" + uuid + suffix;
        return path;
    }

}
