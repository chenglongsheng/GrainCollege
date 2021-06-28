package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1.查询讲师表中所有数据
    //rest风格
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher() {
        final List<EduTeacher> list = teacherService.list(null);
        return list;
    }

}

