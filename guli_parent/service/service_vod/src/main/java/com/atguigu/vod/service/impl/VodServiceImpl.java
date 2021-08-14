package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author chenglongsheng
 * @create 2021-08-14 21:40
 */
@Service
public class VodServiceImpl implements VodService {
    // 上传视频到阿里云
    @Override
    public String uploadVideoToAliyun(MultipartFile file) {

        try {

            String fileName = file.getOriginalFilename();// 源文件名称

            String title = fileName.substring(0, fileName.lastIndexOf("."));// 上传之后文件名称

            InputStream inputStream = file.getInputStream();//文件输入流

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }

            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
