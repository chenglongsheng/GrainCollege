package com.atguigu.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenglongsheng
 * @create 2021-06-28 22:44
 */
@Configuration
@MapperScan("com.atguigu.eduservice.mapper")
public class EduConfig {
}
