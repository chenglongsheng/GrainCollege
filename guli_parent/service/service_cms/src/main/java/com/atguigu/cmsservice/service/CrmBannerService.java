package com.atguigu.cmsservice.service;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-09-01
 */
public interface CrmBannerService extends IService<CrmBanner> {

    //查询所有banner
    List<CrmBanner> selectAllBanner();
}
