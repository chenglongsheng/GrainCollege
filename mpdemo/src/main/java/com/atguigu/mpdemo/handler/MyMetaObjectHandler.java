package com.atguigu.mpdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 第二步：创建类，实现接口MetaObjectHandler
 *                  实现接口里的方法
 *
 * @author chenglongsheng
 * @create 2021-06-27 11:45
 */
@Component// 注解不能忘
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);// metaObject：元数据对象
        this.setFieldValByName("updateTime", new Date(), metaObject);

        this.setFieldValByName("version", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
