package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public Result findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return Result.ok().data("items", list);
    }

    //2.逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")//通过路径传值
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {//@PathVariable String id 获取路径中的id
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    //3.分页查询讲师
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/page/{current}/{limit}")
    public Result pageList(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit) {

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //调用方法实现分页，底层会封装，把分页所有数据封装到pageTeacher
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

        return Result.ok().data("total", total).data("records", records);
    }

}

