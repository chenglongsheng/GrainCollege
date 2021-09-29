package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.Result;
import com.atguigu.commonutils.vo.UcenterMember;
import com.atguigu.eduservice.client.UCenterClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenglongsheng
 * @create 2021-09-29 16:32
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class CommentFrontController {

    @Autowired
    private UCenterClient UCenterClient;

    @Autowired
    private EduCommentService commentService;

    /**
     * 评论分页列表
     *
     * @param page     当前页
     * @param limit    每页限制数
     * @param courseId 课程id
     * @return map     分页数据
     */
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(required = true) @PathVariable Long page,
            @ApiParam(required = true) @PathVariable Long limit,
            @ApiParam(required = false) String courseId
    ) {
        Page<EduComment> commentPage = new Page<>(page, limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();

        wrapper.eq("course_id", courseId);

        commentService.page(commentPage, wrapper);
        List<EduComment> commentList = commentPage.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", commentPage.getCurrent());
        map.put("pages", commentPage.getPages());
        map.put("size", commentPage.getSize());
        map.put("total", commentPage.getTotal());
        map.put("hasNext", commentPage.hasNext());
        map.put("hasPrevious", commentPage.hasPrevious());
        return Result.ok().data(map);
    }

    /**
     * 添加评论
     *
     * @param comment 评论
     * @param request cookie传token
     * @return 无
     */
    @PostMapping("/save")
    public Result addComment(@RequestBody EduComment comment, HttpServletRequest request) {

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(memberId)) {
            return Result.error().code(28004).message("请登录");
        }
        comment.setMemberId(memberId);

        UcenterMember userInfo = UCenterClient.getUserInfo(memberId);
        comment.setNickname(userInfo.getNickname());
        comment.setAvatar(userInfo.getAvatar());

        commentService.save(comment);
        return Result.ok();
    }

}
