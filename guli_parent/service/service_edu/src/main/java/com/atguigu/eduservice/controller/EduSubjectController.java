package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类
    // 获取上传的文件并读取
    @PostMapping("/addSubject")
    public Result addSubject(MultipartFile file) {
        subjectService.saveSubject(file, subjectService);
        return Result.ok();
    }

    // 课程分类列表(树形)
    @GetMapping("/getAllSubject")
    public Result getAllSubject() {

        // 泛型一级分类包括二级分类
        List<OneSubject> list = subjectService.getAllOneAndTwoSubject();

        return Result.ok().data("list", list);
    }
}

