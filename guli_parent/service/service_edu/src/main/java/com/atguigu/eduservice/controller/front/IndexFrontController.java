package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-09-02 20:09
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //查询前八条热门课程和前四条名师
    @GetMapping("/index")
    public Result index() {
        //查询前八条课程
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("id");
        courseWrapper.last("limit 8");// 拼接字符
        List<EduCourse> courseList = courseService.list(courseWrapper);

        //查询前四条名师
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");// 拼接字符
        List<EduTeacher> teacherList = teacherService.list(teacherWrapper);

        return Result.ok().data("courseList", courseList).data("teacherList", teacherList);
    }

}
