package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.vo.front.CourseFrontVo;
import com.atguigu.eduservice.entity.vo.front.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chenglongsheng
 * @create 2021-09-16 19:52
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    //1 条件查询带分页查询课程
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public Result getFrontCourseList(
            @PathVariable long page,
            @PathVariable long limit,
            @RequestBody(required = false) CourseFrontVo courseFrontVo
    ) {
        Page<EduCourse> coursePage = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(coursePage, courseFrontVo);
        // 返回分页所有数据
        return Result.ok().data(map);
    }

    //2 课程详情
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId) {
        // 根据课程id，编写SQL语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        // 根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getAllChapterVideoByCourseId(courseId);
        return Result.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList);
    }

}
