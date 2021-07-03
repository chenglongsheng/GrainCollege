package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

        try {
            int a = 10/0;// 手动制造异常
        } catch (Exception e) {
            throw new GuliException(20001, "执行了自定义异常处理");
        }

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //调用方法实现分页，底层会封装，把分页所有数据封装到pageTeacher
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

        return Result.ok().data("total", total).data("rows", records);
    }

    //4.条件查询带分页的方法
    @ApiOperation(value = "分页讲师列表")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(
            /*
            RequestBody(需要使用post提交方式)
                使用json传递数据，把json数据封装到对应对象里面
            ResponseBody
                返回数据，返回json数据
            @RequestBody(required = false) 参数值可以为空
             */
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQuery teacherQuery) {

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //多条组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //不为空，则拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return Result.ok().data("total", total).data("rows", records);
    }

    //5.添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Result addTeacher(
            @ApiParam(name = "eduTeacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    //6.根据讲师id查询
    @ApiOperation(value = "根据讲师id查询")
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        EduTeacher byId = teacherService.getById(id);
        return Result.ok().data("teacher", byId);
    }

    //7.根据id修改讲师
    @ApiOperation(value = "修改讲师")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean update = teacherService.updateById(eduTeacher);
        if (update) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

}

