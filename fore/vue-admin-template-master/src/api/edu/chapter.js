import request from '@/utils/request'
export default {
    // 根据课程id查询所有章节和小节
    getAllChapterVideo(courseId) {
        return request({
            url: `/eduservice/chapter/getChapterVideo/${courseId}`,
            method: 'get'
        })
    },
    // 添加章节
    addChapter(chapter) {
        return request({
            url: `/eduservice/chapter/addChapter`,
            method: 'post',
            data: chapter
        })
    },
    // 查询章节
    getChapter(chapterId) {
        return request({
            url: '/eduservice/chapter/addChapter/' + chapterId,
            method: 'get'
        })
    },
    // 修改章节
    updateChapter(chapter) {
        return request({
            url: `/eduservice/chapter/updateChapter`,
            method: 'post',
            data: chapter
        })
    },
}