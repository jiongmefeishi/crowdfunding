package com.zqt.crowd.api.qiniu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 云存储配置参数实体
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CloudStorageReqDO {

    /**
     * 七牛域名domain
     */
    private String domain;
    /**
     * 七牛ACCESS_KEY
     */
    private String accessKey;
    /**
     * 七牛SECRET_KEY
     */
    private String secretKey;
    /**
     * 七牛空间名
     */
    private String bucketName;

}
