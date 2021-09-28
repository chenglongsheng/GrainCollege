package com.atguigu.vodservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vodservice.service.VodService;
import com.atguigu.vodservice.utils.ConstantVodUtils;
import com.atguigu.vodservice.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

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

    // 删除多个阿里云视频 参数有多个视频id
    @Override
    public void removeMoreAliyunVideo(List videoIdList) {
        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            // 创建删除对象的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList转换1，2，3，4，
            String videoIds = StringUtils.join(videoIdList, ",");

            // 向request设置视频id
            request.setVideoIds(videoIds);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败！");
        }
    }

    // 根据视频id获取视频凭证
    @Override
    public String getPlayAuth(String id) {

        try {

            //创建初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

            //创建获取凭证的request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

            // 向request设置视频id
            request.setVideoId(id);

            //获取凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            return response.getPlayAuth();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
