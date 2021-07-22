<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>

    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>

      <!-- 所属分类 TODO -->
      <el-form-item label="课程分类">
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="一级分类"
          @change="firstLevelSubjectChanged"
        >
          <el-option
            v-for="course in oneSubjectList"
            :key="course.id"
            :label="course.title"
            :value="course.id"
          />
        </el-select>
        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option
            v-for="course in twoSubjectList"
            :key="course.id"
            :label="course.title"
            :value="course.id"
          />
        </el-select>
      </el-form-item>

      <!-- 课程讲师 TODO -->
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>

      <!-- 课程简介 TODO -->
      <el-form-item label="课程简介">
        <el-input
          v-model="courseInfo.description"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>

      <!-- 课程封面 TODO -->
      <!-- 课程封面 -->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarUpload"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API + '/eduoss/fileoss'"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover" />
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />
        元
      </el-form-item>

      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate"
          >保存并下一步</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import course from '@/api/edu/course'
import subject from '@/api/edu/subject'
export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseInfo: {
        title: '',
        subjectId: '', // 二级分类id
        subjectParentId: '', // 一级分类id
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: '/static/3.jpg',
        price: 0,
      },
      teacherList: [], // 封装所有讲师
      oneSubjectList: [], //一级分类
      twoSubjectList: [], //二级分类
      BASE_API: process.env.BASE_API, //接口API地址
    }
  },

  created() {
    // console.log('info created')
    this.getAllTeacherList()
    this.getFirstLevelSubject()
  },

  methods: {
    saveOrUpdate() {
      course.addCourseInfo(this.courseInfo).then((response) => {
        this.$message({
          type: 'success',
          message: '课程信息添加成功！',
        })
        this.$router.push({ path: '/course/chapter/' + response.data.courseId })
      })
    },
    getAllTeacherList() {
      course.getAllTeacher().then((response) => {
        this.teacherList = response.data.items
      })
    },
    getFirstLevelSubject() {
      subject.getSubjectList().then((response) => {
        this.oneSubjectList = response.data.list
      })
    },
    firstLevelSubjectChanged(value) {
      // alert(value)
      // 清空二级分类下拉中上一次选择的值
      this.courseInfo.subjectId = ''
      // 遍历一级分类（一级分类中包括二级分类，就是children）
      for (var i = 0; i < this.oneSubjectList.length; i++) {
        // 判断一级分类id，把children赋值给twoSubjectList
        if (value === this.oneSubjectList[i].id) {
          this.twoSubjectList = this.oneSubjectList[i].children
        }
      }
    },
    // 封面上传成功
    handleAvatarUpload(res, file) {
      this.courseInfo.cover = res.data.url
    },
    // 封面上传之前 做规定
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLimit2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式！')
      }
      if (!isLimit2M) {
        this.$message.error('上传头像图片大小不能超过 2MB！')
      }
      return isJPG && isLimit2M
    },
  },
}
</script>