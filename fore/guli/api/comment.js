import request from '@/utils/request'
export default {
  // 条件分页查询评论
  getCommentList(page, limit, courseId) {
    return request({
      url: `/eduservice/comment/${page}/${limit}`,
      method: 'get',
      params: { courseId }
    })
  },
  // 添加评论
  addComment(comment) {
    return request({
      url: `/eduservice/comment/save`,
      method: 'post',
      data: comment
    })
  }
}
