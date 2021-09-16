import request from '@/utils/request'
export default {
  // 分页查询讲师
  getTeacherList(page, limit) {
    return request({
      url: `/eduservice/teacherfront/getTeacherList/${page}/${limit}`,
      method: 'post'
    })
  }
}
