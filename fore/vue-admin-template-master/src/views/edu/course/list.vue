<template>
  <div id="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称" />
      </el-form-item>

      <el-form-item>
        <el-select
          v-model="courseQuery.status"
          clearable
          placeholder="课程状态"
        >
          <el-option :value="Nomal" label="已发布" />
          <el-option :value="Draft" label="未发布" />
        </el-select>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()"
        >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <!-- 表格 -->
    <el-table :data="list" border fit highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="课程名称" width="80" />

      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 'Nomal' ? '已发布' : '未发布' }}
        </template>
      </el-table-column>

      <el-table-column prop="lessonNum" label="课时数" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="viewCount" label="浏览数" width="80" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改课程基本信息</el-button
            >
          </router-link>
          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改课程大纲</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
            >删除课程</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :total="total"
      :current-page="page"
      :page-size="limit"
      @current-change="getList"
      layout="total, prev, pager, next, jumper"
      style="padding: 30px 0; text-align: center"
    ></el-pagination>
  </div>
</template>

<script>
import course from '@/api/edu/course'
export default {
  data() {
    return {
      list: null, //查询之后接口返回集合
      page: 1, //当前页
      limit: 10, //每页记录数
      total: 0, //总记录数
      courseQuery: {}, //条件封装对象
    }
  },
  created() {
    this.getList()
  },
  methods: {
    //讲师列表
    getList() {
      course.getCourseList().then((response) => {
        this.list = response.data.list
      })
    },
    // 清空方法
    resetData() {
      this.courserQuery = {}
      this.getList()
    },
    // 删除讲师
    removeDataById(id) {
      this.$confirm('此操作将永久删除该讲师记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        // 确定调用then
        course.deleteCourseById(id).then((response) => {
          // 提示信息
          this.$message({
            type: 'success',
            message: '删除成功!',
          })
          // 回到列表页面
          this.getList()
        })
      })
    },
  },
}
</script>