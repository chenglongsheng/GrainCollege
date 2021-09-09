package com.atguigu.ucenterservice.service;

import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-09-07
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    // login
    String login(UcenterMember member);

    // register
    void register(RegisterVo registerVo);
}
