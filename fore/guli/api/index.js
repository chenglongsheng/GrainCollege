import request from '@/utils/request'

export default {
  // 查询热门课程
  getIndexCourse() {
    return request({
      url: '/eduservice/indexfront/indexHotCourse',
      method: 'get'
    })
  },
  // 查询热门讲师
  getIndexTeacher() {
    return request({
      url: '/eduservice/indexfront/indexHotTeacher',
      method: 'get'
    })
  }
}
