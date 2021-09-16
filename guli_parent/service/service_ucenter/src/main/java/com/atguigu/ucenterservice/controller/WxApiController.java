package com.atguigu.ucenterservice.controller;

import com.atguigu.ucenterservice.utils.ConstantWxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

/**
 * @author chenglongsheng
 * @create 2021-09-15 19:50
 */
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    //1 生成微信二维码
    @GetMapping("/login")
    public String getWxCode() {
        // 固定地址后面拼接参数
        // 冗长易错
//        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + ConstantWxUtils.WX_OPEN_APP_ID + "&response_type=code";

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_url=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";


        try {
            // 对redirect_url进行编码
            String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_REDIRECT_URL,
                "atguigu"
        );

        // 从定向到请求微信地址里
        return "redirect:" + url;
    }

}
