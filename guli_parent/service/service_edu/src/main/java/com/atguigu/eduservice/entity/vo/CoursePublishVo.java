package com.atguigu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author chenglongsheng
 * @create 2021-07-29 22:31
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price; // 只用于显示

}
