<template>
  <div class="cc">
    <!-- <div class="daohangtitle">病历页面</div> -->
    <div class="mainbox">
      <el-card shadow="never" style="position: relative; height: 550px">
        <template #header>
          <div class="card-header" style="margin-bottom: 5px">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">电子病历</span>
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/cc' }"
                >电子病历</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/cc' }"
                >病历</el-breadcrumb-item
              >
            </el-breadcrumb>
          </div>
        </template>

        <!-- gutter 属性来指定列之间的间距，默认值为0 -->
        <el-row :gutter="10">
          <el-col :span="4">
            <el-input
              v-model="search"
              placeholder="请输入查找的用户信息"
            ></el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="searchUser">搜索</el-button>
            <!-- <el-button type="primary" @click="refresh">刷新</el-button> -->
          </el-col>
        </el-row>
        <el-table :data="scope.tableData" style="width: 100%">
          <el-table-column label="就诊卡号" width="180">
            <template #default="scope">
              <div>{{ scope.row.caseCard }}</div>
            </template>
          </el-table-column>
          <el-table-column label="就诊人" width="180">
            <template #default="scope">
              <el-tag>{{ scope.row.caseName }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="候诊日期" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><timer /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.caseDate }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="身份证号" width="230">
            <template #default="scope">
              <div>{{ scope.row.caseIdNumber }}</div>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                type="success"
                size="small"
                @click="detail(scope.row.caseId)"
                >详情</el-button
              >
              <el-button
                type="danger"
                size="small"
                @click="useDelete(scope.row.caseId)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <!--
        total 总行数
        page-size 每页显示条目个数
      current-change 改变页码时触发
        currentPage:当前页码
        -->
        <!-- <el-pagination
          background
          layout="prev, pager, next"
          :total="scope.total"
          :page-size="scope.pageSize"
          @current-change="changePage"
          style="position: fixed; bottom: 70px"
        /> -->
      </el-card>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ElMessageBox, ElNotification } from 'element-plus'
export default {
  name: 'Cc',
  setup() {
    const $router = useRouter()
    const search = ref('')
    const scope = reactive({
      tableData: [],

      regName: '',
      regGender: '',
      regAge: '',
      regDate: '',
      regIdNumber: '',
      regSite: '',
      regDepartments: '',
      regDoctor: '',

      total: 0, //总条数
      pageSize: 5, //每页显示行数
      currentPage: 1, //当前页码
    })

    axios.defaults.baseURL = 'http://localhost:8888/'
    axios.get('/caseHistory/show').then((res) => {
      scope.tableData = res.data
    })

    //刷新数据
    function refresh() {
      axios.get('/caseHistory/show').then((res) => {
        scope.tableData = res.data
      })
    }
    //详情
    function detail(caseId) {
      sessionStorage.setItem('caseId', caseId)
      $router.replace('/bingLiDetail')
    }
    //删除事件
    function useDelete(state) {
      console.log(state)
      ElMessageBox.confirm('你确定要删除记录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        axios.post('/caseHistory/delete?caseId=' + state).then((res) => {
          console.log(res.data.code)
          if (res.data.code === 1) {
            //通知提示框
            ElNotification({
              title: '成功',
              message: '数据删除成功',
              type: 'success',
            })
            //重新获取数据
            refresh()
          } else {
            ElNotification({
              title: '失败',
              message: '数据删除失败',
              type: 'error',
            })
          }
        })
      })
    }
    //搜索
    function searchUser() {
      axios
        .get('/caseHistory/BLsearch?search=' + search.value)
        .then((res) => {
          scope.tableData = res.data
        })
        .catch((error) => {
          console.log(error)
        })
    }

    return {
      scope,
      useDelete,
      detail,
      search,
      searchUser,
    }
  },
}
</script>

<style scoped>
.cc {
  width: 100%;
  height: 100%;
  background: #fff;
  /* padding-left: 20px; */
  box-sizing: border-box;
}
.daohangtitle {
  height: 30px;
  font-size: 12px;
  background: #eee;
}

.mainbox {
  width: 100%;
  margin: 0 auto;
}
</style>