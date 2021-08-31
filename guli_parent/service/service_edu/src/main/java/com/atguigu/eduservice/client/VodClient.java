package com.atguigu.eduservice.client;

import com.atguigu.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-08-31 9:18
 */
@Component
@FeignClient("service-vod")
public interface VodClient {

    //定义调用的方法路径
    // 根据视频id删除阿里云视频
    @DeleteMapping(value = "/eduvod/video/deleteAliyunVideo/{id}")
    public Result deleteAliyunVideo(@PathVariable("id") String id);

    // 删除多个阿里云视频 参数有多个视频id
    @DeleteMapping("/eduvod/video/delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
