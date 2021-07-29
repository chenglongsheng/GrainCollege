package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-07-20
 */
public interface EduChapterService extends IService<EduChapter> {

    // 课程大纲列表
    List<ChapterVo> getAllChapterVideoByCourseId(String courseId);

    // 删除章节
    boolean deleteChapter(String chapterId);
}
