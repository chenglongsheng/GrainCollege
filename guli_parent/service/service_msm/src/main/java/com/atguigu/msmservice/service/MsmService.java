package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @author chenglongsheng
 * @create 2021-09-06 20:20
 */
public interface MsmService {
    // 发送短信
    Boolean send(Map<String, Object> param, String phone);
}
