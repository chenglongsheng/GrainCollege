package com.atguigu.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-08-14 21:38
 */
public interface VodService {
    // 上传视频到阿里云
    String uploadVideoToAliyun(MultipartFile file);

    // 删除多个阿里云视频 参数有多个视频id
    void removeMoreAliyunVideo(List videoIdList);
}
