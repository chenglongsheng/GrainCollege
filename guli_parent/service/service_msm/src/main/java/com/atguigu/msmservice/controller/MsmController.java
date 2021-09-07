package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.RandomUtil;
import com.atguigu.commonutils.Result;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chenglongsheng
 * @create 2021-09-06 20:18
 */
@RestController
@CrossOrigin
@RequestMapping("/Edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 发送短信
    @GetMapping("/send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok();
        }

        //2 如果redis获取不到，进行阿里云发送
        // 生成随机值，传递阿里云发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        Boolean isSend = msmService.send(param, phone);
        if (isSend) {
            // 发送成功，把验证码放到redis
            // 设置有效时间
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.error().message("短信发送失败！");
        }

    }

}
