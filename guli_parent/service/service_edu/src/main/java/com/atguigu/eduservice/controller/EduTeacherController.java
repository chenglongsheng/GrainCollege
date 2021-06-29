package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-06-28
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1.查询讲师表中所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    //2.逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")//通过路径传值
    public boolean remove(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {//@PathVariable String id 获取路径中的id
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

