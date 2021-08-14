package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenglongsheng
 * @create 2021-08-14 21:38
 */
public interface VodService {
    // 上传视频到阿里云
    String uploadVideoToAliyun(MultipartFile file);
}
