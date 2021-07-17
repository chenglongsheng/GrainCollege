<template>
  <div id="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number
          v-model="teacher.sort"
          controls-position="right"
          min="0"
        />
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career" />
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 讲师头像：TODO -->
      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacher.avatar" />
        <!-- 文件上传按钮 -->
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="imagecropperShow = true"
          >更换头像
        </el-button>

        <!--
          v-show：是否显示上传组件
          :key：类似于id，如果一个页面多个图片上传控件，可以做区分
          :url：后台上传的url地址
          @close：关闭上传组件
          @crop-upload-success：上传成功后的回调 
        -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/eduoss/fileoss'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate"
          >保存</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherAPI from '@/api/edu/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },
      imagecropperShow: false,// 上传弹框组件是否显示
      imagecropperKey: 0,// 上传组件key值
      BASE_API: process.env.BASE_API,// 获取dev.env.js里面的地址
      saveBtnDisabled: false // 保存按钮是否禁用,
    }
  },

  created() {
    this.extract()
  },

  // 监听
  watch: {
    $route(to, from) {// 路由变化方式
      this.extract()
    }
  },

  methods: {
    // 关闭上传弹框
    close() {
      this.imagecropperShow = false
      // 上传组件初始化
      this.imagecropperKey = this.imagecropperKey + 1
    },
    // 上传成功的方法
    cropSuccess(data) {
      this.imagecropperShow = false
      this.imagecropperKey = this.imagecropperKey + 1
      this.teacher.avatar = data.url
    },

    extract() {
      // 判断路径中有没有id值
      if (this.$route.params && this.$route.params.id) {
        // 从路径中获取id
        const id = this.$route.params.id
        // 调用查询方法
        this.getInfo(id)
      } else {
        this.teacher = {}
      }
    },

    saveOrUpdate() {
      // 判断修改还是添加
      if (!this.teacher.id) {
        // 添加
        // this.saveBtnDisabled = false
        this.addTeacher()
      } else {
        // 修改
        this.updateTeacher()
      }
    },

    // 添加的方法
    addTeacher() {
      teacherAPI
        .addTeacher(this.teacher)
        .then(response => {
          // 1.提示信息
          this.$message({
            type: 'success',
            message: '添加成功！'
          })
          // 2.回到列表页面，路由跳转
          this.$router.push({ path: '/teacher/list' })
        })
    },

    // 根据讲师id查询
    getInfo(id) {
      teacherAPI
        .getTeacherInfo(id)
        .then(response => {
          this.teacher = response.data.teacher
        })
    },
    // 修改讲师
    updateTeacher() {
      teacherAPI
        .updateTeacherInfo(this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功！'
          })
          // 回到列表页面，路由跳转
          this.$router.push({ path: '/teacher/list' })
        })
    }
  }
}
</script>