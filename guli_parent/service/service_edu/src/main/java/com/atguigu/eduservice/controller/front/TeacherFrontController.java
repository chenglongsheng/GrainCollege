package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author chenglongsheng
 * @create 2021-09-16 15:49
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    // 1分页查询讲师
    @GetMapping("/getTeacherList/{page}/{limit}")
    public Result getTeacherList(@PathVariable long page, @PathVariable long limit) {

        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(teacherPage);

        return Result.ok().data(map);
    }

}
