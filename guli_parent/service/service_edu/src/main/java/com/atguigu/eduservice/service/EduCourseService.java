package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-07-20
 */
public interface EduCourseService extends IService<EduCourse> {

    // 添加课程信息
    void saveCourseInfo(CourseInfoVo courseInfoVo);
}
