package com.atguigu.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-08-11 22:05
 */
public class TestVod {
    public static void main(String[] args) throws Exception {

        // 根据视频id获取视频播放凭证
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5t7zEyNP7cPU7tbaiUrm", "Sw1YLSKSHUdD14dyPvfBKaCDvoMByU");

        // 创建获取视频凭证request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        // 向request对象里面设置视频id
        request.setVideoId("05328dd149e84e389ab6fb8fdd5c02c6");

        // 调用初始化对象里面的方法传递request对象，获取数据
        response = client.getAcsResponse(request);

        System.out.println("PlayAuth:" + response.getPlayAuth());

    }

    public static void getVideoPlayURL() throws Exception {
        // 根据视频id获取视频播放地址
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5t7zEyNP7cPU7tbaiUrm", "Sw1YLSKSHUdD14dyPvfBKaCDvoMByU");

        // 创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        // 向request对象里面设置视频id
        request.setVideoId("05328dd149e84e389ab6fb8fdd5c02c6");

        // 调用初始化对象里面的方法传递request对象，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfosList = response.getPlayInfoList();
        // 播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfosList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        // Base信息
        System.out.println("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

    }

}
