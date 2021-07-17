package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类
 *
 * @author chenglongsheng
 * @create 2021-07-17 22:18
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    // 一个一级分类中包含多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
