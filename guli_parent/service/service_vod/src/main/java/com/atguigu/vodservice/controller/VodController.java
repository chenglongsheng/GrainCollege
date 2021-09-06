package com.atguigu.vodservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.Result;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vodservice.service.VodService;
import com.atguigu.vodservice.utils.ConstantVodUtils;
import com.atguigu.vodservice.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-08-14 21:37
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    // 上传视频到阿里云
    @PostMapping("/uploadAliyunVideo")
    public Result uploadAliVideo(MultipartFile file) {

        String videoId = vodService.uploadVideoToAliyun(file);

        return Result.ok().data("videoId", videoId);
    }

    // 根据视频id删除阿里云视频
    @DeleteMapping("/deleteAliyunVideo/{id}")
    public Result deleteAliyunVideo(@PathVariable String id) {

        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            // 创建删除对象的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            // 向request设置视频id
            request.setVideoIds(id);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败！");
        }

    }

    // 删除多个阿里云视频 参数有多个视频id
    @DeleteMapping("/delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAliyunVideo(videoIdList);
        return Result.ok();
    }

}
