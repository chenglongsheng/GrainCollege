import request from '@/utils/request'
export default {
    // 根据课程id查询所有章节和小节
    getAllChapterVideo(courseId) {
        return request({
            url: `/eduservice/chapter/getChapterVideo/${courseId}`,
            method: 'get'
        })
    }
}