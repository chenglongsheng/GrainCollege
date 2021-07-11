<template>
  <div id="list">
    list
  </div>
</template>

<script>
import teacher from '@/api/edu/teacher'
export default {
    data(){
        return{
            list:null,//查询之后接口返回集合
            page:1,//当前页
            limit:10,//每页记录数
            total:0,//总记录数
            teacherQuery:{}//条件封装对象
        }
    },
    created(){
        this.getList()
    },
    methods:{
        //讲师列表
        getList() {
            teacher.getTeacherListPage(this.page, this.limit, this.teacherQuery)
            .then(response=>{
                // console.log(response)
                this.list = response.data.rows
                this.total = response.data.total
                console.log(this.list)
                console.log(this.total)
            })
            .catch(error=>{
                console.log(error)
            })
        }
    }
}
</script>

<style>

</style>