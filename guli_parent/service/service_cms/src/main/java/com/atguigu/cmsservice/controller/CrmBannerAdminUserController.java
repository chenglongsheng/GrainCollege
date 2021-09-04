package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-09-01
 */
@RestController
@RequestMapping("/educms/bannerAdmin")
@CrossOrigin
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

    //2 增加
    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return Result.ok();
    }

    //3 删除
    @DeleteMapping("/removeBanner/{id}")
    public Result removeBanner(@PathVariable String id) {
        bannerService.removeById(id);
        return Result.ok();
    }

    //4 修改
    @PostMapping("/updateBanner")
    public Result updateBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.updateById(crmBanner);
        return Result.ok();
    }

    //5 查找
    @GetMapping("/getBanner/{id}")
    public Result getBanner(@PathVariable String id) {
        CrmBanner crmBanner = bannerService.getById(id);
        return Result.ok().data("crmBanner", crmBanner);
    }

}

