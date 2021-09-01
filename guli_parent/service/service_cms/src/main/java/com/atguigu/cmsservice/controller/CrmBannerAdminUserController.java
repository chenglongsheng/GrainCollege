package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-09-01
 */
@RestController
@RequestMapping("/cmsservice/bannerAdmin")
public class CrmBannerAdminUserController {

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询
    @GetMapping("pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable long page, @PathVariable long limit) {

        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        bannerService.page(pageBanner, null);

        return Result.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
    }

}

