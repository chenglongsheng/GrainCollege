package com.atguigu.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author chenglongsheng
 * @create 2021-06-26 22:56
 */
@Data
public class User {

    /*
    mp自带的主键生成策略
    AUTO：自动增长

    ID_WORKER：mp自带策略，生成19位值，数字类型使用这种策略，如long
    ID_WORKER_STR：mp自带策略，生成19位值，字符串类型使用这种策略

    INPUT：设置id值
    NONE：无策略，输入
    UUID：随机唯一值
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
