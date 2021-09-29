package com.atguigu.eduservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author chenglongsheng
 * @create 2021-09-29 13:53
 */
@Component
@FeignClient(name = "service-ucenter", fallback = UCenterClientImpl.class)
public interface UCenterClient {

    /**.
     * 根据用户id获取信息/
     */
    @PostMapping("/ucenterservice/ucenter-member/getUserInfo/{id}")
    public com.atguigu.commonutils.vo.UcenterMember getUserInfo(@PathVariable String id);

}
