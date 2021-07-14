import request from '@/utils/request'

export default {
    // 条件查询分页
    // current
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            // url: '/eduservice/teacher/pageTeacherCondition/' + current + "/" + limit,//方式一
            url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,//方式二
            method: 'post',
            //后端使用ResponseBody 使用json传递数据，把json数据封装到对应对象里面
            data: teacherQuery
        })
    },
    // 删除讲师
    deleteTeacherById(id) {
        return request({
            url: `/eduservice/teacher/${id}`,
            method: 'delete',
        })
    },
    // 添加讲师
    addTeacher(teacher) {
        return request({
            url: `/eduservice/teacher/addTeacher`,
            method: 'post',
            data: teacher
        })
    },
    // 查询讲师
    getTeacherInfo(id) {
        return request({
            url: `/eduservice/teacher/getTeacher/${id}`,
            method: 'get'
        })
    }
}