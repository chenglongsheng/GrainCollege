package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenglongsheng
 * @create 2021-07-09 22:11
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    // login
    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    // info
    @GetMapping("/info")
    public Result info() {
        return Result.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
