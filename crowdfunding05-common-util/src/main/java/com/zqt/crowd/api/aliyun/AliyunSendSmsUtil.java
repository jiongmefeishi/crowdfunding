package com.zqt.crowd.api.aliyun;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zqt.crowd.api.aliyun.entity.SendSmsReqDO;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.util.ResultEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zqtao
 * @description: 阿里云短信发送API工具
 */
public class AliyunSendSmsUtil {

    /**
     * 发送短信验证码
     *
     * @param sendSmsReqDO 封装发送短信的请求信息
     * @return 成功返回验证码，失败返回错误原因
     */
    public static ResultEntity<String> sendSms(SendSmsReqDO sendSmsReqDO) {
        DefaultProfile profile = DefaultProfile
                .getProfile(sendSmsReqDO.getRegionId(), sendSmsReqDO.getAccessKeyId(), sendSmsReqDO.getAccessSecret());

        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        // 设置请求类型
        request.setSysMethod(MethodType.POST);
        // 设置短信主机
        request.setSysDomain(sendSmsReqDO.getSysDomain());
        // 设置版本
        request.setSysVersion(sendSmsReqDO.getSysVersion());
        // 设置系统规定参数。取值：SendSms。
        request.setSysAction(sendSmsReqDO.getAction());
        // 设置服务器所在区域id
        request.putQueryParameter("RegionId", sendSmsReqDO.getRegionId());
        // 设置接收短信的手机号码
        request.putQueryParameter("PhoneNumbers", sendSmsReqDO.getPhoneNumbers());
        // 设置短信签名名称
        request.putQueryParameter("SignName", sendSmsReqDO.getSignName());
        // 设置短信模板ID
        request.putQueryParameter("TemplateCode", sendSmsReqDO.getTemplateCode());

        // 设置验证码, 随机6位数
        Map<String, String> map = new HashMap<>();
        map.put("code", RandomUtil.randomNumbers(6));
        sendSmsReqDO.setTemplateParam(map);
        System.out.println(JSONUtil.toJsonStr(sendSmsReqDO.getTemplateParam()));
        // 设置短信模板变量对应的实际值
        request.putQueryParameter("TemplateParam", JSONUtil.toJsonStr(sendSmsReqDO.getTemplateParam()));
        try {
            // 发送短信
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            // {"Message": "OK", "RequestId": "873043ac-bcda-44db-9052-2e204c6ed20f", "BizId": "607300000000000000^0", "Code": "OK"}
            // {"Message":"账户余额不足","RequestId":"308AE26C-6247-4B5C-8DD0-0F90DABC4BD3","Code":"isv.AMOUNT_NOT_ENOUGH"}

            JSONObject jsonObject = JSONUtil.parseObj(data);
            String code = jsonObject.getStr("Code");
            // 发送正常，返回验证码
            if (CommonConstant.SMS_SEND_STATUS_SUCCESS.equals(code)) {
                return ResultEntity.successWithData(sendSmsReqDO.getTemplateParam().get("code"));
            }

            // 发送不正常，返回错误信息
            return ResultEntity.failed(jsonObject.getStr("Message"));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return ResultEntity.failedDefault();
    }
}


