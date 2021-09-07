package com.atguigu.ucenterservice.service.impl;

import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.mapper.UcenterMemberMapper;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-09-07
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    // login
    @Override
    public String login(UcenterMember member) {
        return null;
    }
}
