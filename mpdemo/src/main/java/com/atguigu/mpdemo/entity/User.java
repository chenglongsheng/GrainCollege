package com.atguigu.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

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
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 第一步：在实体类里面进行自动填充属性添加注解
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
