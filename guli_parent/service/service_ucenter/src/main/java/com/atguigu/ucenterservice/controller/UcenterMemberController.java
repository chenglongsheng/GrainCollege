package com.atguigu.ucenterservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-09-07
 */
@RestController
@CrossOrigin
@RequestMapping("/ucenterservice/ucenter-member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    // login
    @PostMapping("/login")
    public Result login(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return Result.ok().data("token", token);
    }

    // register

}

