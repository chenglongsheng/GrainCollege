package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    // 添加小节
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return Result.ok();
    }

    // 删除小节 同时删除小节中的视频
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id) {
        //根据小节获取视频id
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        // 判断小节中是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            Result result = vodClient.deleteAliyunVideo(videoSourceId);//删除视频
            if (result.getCode() == 20001) {
                throw new GuliException(20001, "删除视频失败----熔断器");
            }
        }
        videoService.removeById(id);//删除小节
        return Result.ok();
    }

    // 修改小节
    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return Result.ok();
    }

    // 查询小节
    @GetMapping("/getVideo/{id}")
    public Result getVideo(@PathVariable String id) {
        EduVideo video = videoService.getById(id);
        return Result.ok().data("video", video);
    }

}

