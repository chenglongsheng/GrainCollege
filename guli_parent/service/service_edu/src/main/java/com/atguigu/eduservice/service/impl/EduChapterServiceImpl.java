package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-07-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;//注入小节服务

    // 课程大纲列表
    @Override
    public List<ChapterVo> getAllChapterVideoByCourseId(String courseId) {

        //根据课程id查章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);

        //根据课程id查小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> videoList = videoService.list(wrapperVideo);

        //最终的封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        //遍历查询章节list集合进行封装
        for (EduChapter chapter : chapterList) {
            // 每个章节
            // 复制到chapterVo对象中
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            finalList.add(chapterVo);

            //小节数据
            List<VideoVo> videoVoList = new ArrayList<>();

            //遍历查询小节list集合进行封装
            for (EduVideo video : videoList) {
                // 判断章节id和小节中的章节id相同
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }

        return finalList;
    }

    // 删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        // 根据chapterId查询小节表，如果查出数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) { //删除
            throw new GuliException(20001, "不能删除！");
        } else { //不删除
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }

    }
}
