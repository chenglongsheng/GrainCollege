package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-07-26 22:20
 */
@Data
public class chapterVo {

    private String id;
    private String title;

    // 小节
    private List<VideoVo> children = new ArrayList<>();

}
