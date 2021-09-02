package com.atguigu.cmsservice.service.impl;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.mapper.CrmBannerMapper;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-09-01
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //查询所有banner
    @Override
    public List<CrmBanner> selectAllBanner() {
        return baseMapper.selectList(null);
    }
}
