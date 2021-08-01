<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>

    <el-steps
      :active="3"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">返回修改</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="publish"
          >发布课程</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import course from '@/api/edu/course'
export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      publishCourse: {},
    }
  },

  created() {
    // 1 获取路由课程id值
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id
      // 2 调用接口方法根据课程id查询
      this.getCoursePublishId()
    }
    
  },

  methods: {
    // 根据课程id查询
    getCoursePublishId() {
      course.getCoursePublishInfo(this.courseId).then((response) => {
        this.publishCourse = response.data.publishCourse
      })
    },
    previous() {
      this.$router.push({ path: '/course/chapter/1' })
    },

    publish() {
      this.$router.push({ path: '/course/list' })
    },
  },
}
</script>