package com.atguigu.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenglongsheng
 * @create 2021-07-15 21:09
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
