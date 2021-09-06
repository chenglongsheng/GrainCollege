package com.atguigu.ossservice.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.ossservice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenglongsheng
 * @create 2021-07-15 21:08
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    // 上传头像的方法
    @PostMapping
    public Result uploadOssFile(MultipartFile file) {

        // 获取上传文件,返回头像地址存入数据库
        String url = ossService.uploadFileAvatar(file);

        return Result.ok().data("url", url);
    }

}
