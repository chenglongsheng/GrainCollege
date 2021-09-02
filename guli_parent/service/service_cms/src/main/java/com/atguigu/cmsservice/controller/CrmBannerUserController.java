package com.atguigu.cmsservice.controller;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台banner
 *
 * @author chenglongsheng
 * @create 2021-09-01 21:10
 */
@RestController
@RequestMapping("/cmsservice/bannerUser")
@CrossOrigin
public class CrmBannerUserController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("/getAllBanner")
    public Result getBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return Result.ok().data("list", list);
    }

}
