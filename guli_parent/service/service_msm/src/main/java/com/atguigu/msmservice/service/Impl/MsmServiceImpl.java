package com.atguigu.msmservice.service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author chenglongsheng
 * @create 2021-09-06 20:20
 */
@Service
public class MsmServiceImpl implements MsmService {
    // 发送短信
    @Override
    public Boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI5t7zEyNP7cPU7tbaiUrm", "Sw1YLSKSHUdD14dyPvfBKaCDvoMByU");
        IAcsClient client = new DefaultAcsClient(profile);

        // 设置固定参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi");
        request.setVersion("2017-05-15");
        request.setAction("sendSms");

        request.putQueryParameter("PhoneNumbers", phone);// 手机号码
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");// 签名名称
        request.putQueryParameter("TemplateCode", "");// 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
