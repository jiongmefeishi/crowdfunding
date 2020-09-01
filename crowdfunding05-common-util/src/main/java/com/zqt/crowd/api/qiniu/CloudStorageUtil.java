package com.zqt.crowd.api.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zqt.crowd.api.qiniu.entity.CloudStorageReqDO;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;

/**
 * @author: zqtao
 * @description: OSS 对象存储上传工具
 */
@Slf4j
public class CloudStorageUtil {

    /**
     * 将图片上传到七牛云，并返回外链
     *
     * @param file              文件
     * @param fileName               文件名
     * @param cloudStorageReqDO 七牛云配置参数实体
     * @return 文件外链
     */
    public static String uploadImg(FileInputStream file, String fileName, CloudStorageReqDO cloudStorageReqDO) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "CloudStorageUtil uploadImg",
                "将图片上传到七牛云，并返回外链"
        );

        // 构造一个带指定Zone对象的配置类, 注意这里的 Region.region2需要根据主机选择
        // 具体参见 https://developer.qiniu.com/kodo/manual/1671/region-endpoint
        Configuration configuration = new Configuration(Region.region2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(configuration);

        try {
            // 生成上传凭证，然后准备上传
            Auth auth = Auth.create(cloudStorageReqDO.getAccessKey(), cloudStorageReqDO.getSecretKey());
            String upToken = auth.uploadToken(cloudStorageReqDO.getBucketName());
            try {
                Response response = uploadManager.put(file, fileName, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
                return cloudStorageReqDO.getDomain() + "/" + putRet.key;
            } catch (QiniuException e) {
                log.info("\n\n{} : " + e.getMessage(), "七牛云上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
