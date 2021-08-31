package com.atguigu.eduservice.client;

import com.atguigu.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-08-31 20:28
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public Result deleteAliyunVideo(String id) {
        return null;
    }

    @Override
    public Result deleteBatch(List<String> videoIdList) {
        return null;
    }
}
