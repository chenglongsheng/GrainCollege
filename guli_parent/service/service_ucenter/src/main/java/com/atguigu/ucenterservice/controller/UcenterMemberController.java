package com.atguigu.ucenterservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.Result;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.vo.RegisterVo;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Result.ok();
    }

    // 根据token获取用户信息
    @GetMapping("/getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        // 调用JWT工具类的方法，根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库，根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return Result.ok().data("memberInfo", member);
    }

}
