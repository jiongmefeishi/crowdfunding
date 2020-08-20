package com.zqt.crowd.api.aliyun;

import cn.hutool.core.lang.Console;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zqt.crowd.api.aliyun.entity.SendSmsReqDO;

/**
 * @author: zqtao
 * @description: 阿里云短信发送API工具
 */
public class AliyunSendSmsUtil {

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GFD8QwgVyBrvNJBGj8Jz", "wwzImeidhhe3iWoZ2TCF17FiXGiARmz");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "15670255898");
        request.putQueryParameter("SignName", "怪兽营");
        request.putQueryParameter("TemplateCode", "SMS_200190922");
        request.putQueryParameter("TemplateParam", "{\"code\":\"2345\"}");
        try {

            // 发送短信
            CommonResponse response = client.getCommonResponse(request);

            String data = response.getData();
            HttpResponse httpResponse = response.getHttpResponse();
            int httpStatus = response.getHttpStatus();

            Console.log("response ：" + response);
            System.out.println();
            Console.log("data : " + data);
            System.out.println();
            Console.log("http : " + httpResponse);
            System.out.println();
            Console.log("status : " + httpStatus);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static CommonResponse sendSms(SendSmsReqDO sendSmsReqDO) {
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
        // 设置短信模板变量对应的实际值
        request.putQueryParameter("TemplateParam", sendSmsReqDO.getTemplateParam());
        try {

            // 发送短信
            return client.getCommonResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}


