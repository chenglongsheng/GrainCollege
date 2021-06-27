package com.atguigu.mpdemo.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenglongsheng
 * @create 2021-06-27 21:39
 */
@Configuration
@MapperScan("com.atguigu.mpdemo.mapper")
public class MpConfig {

    /*
    乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
