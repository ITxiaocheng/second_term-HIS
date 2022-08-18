<template>
  <div class="bb">
    <!-- <div class="daohangtitle">就诊页面</div> -->
    <div class="mainbox">
      <el-card shadow="never" style="position: relative; height: 550px">
        <template #header>
          <div class="card-header" style="margin-bottom: 5px">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">本人就诊患者</span>
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/bb' }"
                >就诊</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/bb' }"
                >就诊信息</el-breadcrumb-item
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
            <el-button type="primary" @click="refresh">刷新</el-button>
          </el-col>
        </el-row>
        <el-table :data="scope.tableData" style="width: 100%">
          <el-table-column label="就诊卡号" width="100">
            <template #default="scope">
              <div>{{ scope.row.regCard }}</div>
            </template>
          </el-table-column>
          <el-table-column label="就诊人" width="80">
            <template #default="scope">
              <div>{{ scope.row.regName }}</div>
            </template>
          </el-table-column>
          <el-table-column label="性别" width="80">
            <template #default="scope">
              <div>{{ scope.row.regGender }}</div>
            </template>
          </el-table-column>
          <el-table-column label="年龄" width="80">
            <template #default="scope">
              <div>{{ scope.row.regAge }}</div>
            </template>
          </el-table-column>
          <el-table-column label="候诊日期" width="130">
            <template #default="scope">
              <div>{{ scope.row.regDate }}</div>
            </template>
          </el-table-column>
          <el-table-column label="身份证号" width="150">
            <template #default="scope">
              <div>{{ scope.row.regIdNumber }}</div>
            </template>
          </el-table-column>
          <el-table-column label="科室" width="130">
            <template #default="scope">
              <div>{{ scope.row.regDepartments }}</div>
            </template>
          </el-table-column>
          <el-table-column label="预约医生" width="100">
            <template #default="scope">
              <div>{{ scope.row.regDoctor }}</div>
            </template>
          </el-table-column>
          <el-table-column label="费用" width="100">
            <template #default="scope">
              <div>{{ scope.row.regSite }}</div>
            </template>
          </el-table-column>
          <el-table-column label="状态" width="80">
            <template #default="scope">
              <div
                style="font-weight: 700"
                v-bind:style="{
                  color: scope.row.regState == '已就诊' ? 'green' : 'red',
                }"
              >
                {{ scope.row.regState }}
              </div>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                type="success"
                size="small"
                v-bind:disabled="scope.row.regState == '已就诊'"
                @click="addDoctor(scope.row.regCard)"
                >就诊</el-button
              >
              <el-button
                type="danger"
                size="small"
                v-bind:disabled="scope.row.regState == '已就诊'"
                @click="useDelete(scope.row.regId)"
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
        <el-pagination
          background
          layout="prev, pager, next"
          :total="scope.total"
          @current-change="changePage"
          :page-size="scope.pageSize"
          style="position: fixed; bottom: 110px"
        />
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
  name: 'Bb',
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

      permission: sessionStorage.getItem('permission'),
    })

    axios.defaults.baseURL = 'http://localhost:8888/'
    console.log(sessionStorage.getItem('permission'))
    if (sessionStorage.getItem('permission') == '0') {
      axios
        .post(
          '/HisReg/all?currentPage=' +
            scope.currentPage +
            '&pageSize=' +
            scope.pageSize
        )
        .then((res) => {
          scope.tableData = res.data.list
          scope.total = res.data.total
          scope.pageSize = res.data.pageSize
          scope.currentPage = res.data.currentPage
        })
        .catch((error) => {
          console.log(error)
        })
    } else {
      axios
        .post(
          '/HisReg/doctor?username=' +
            sessionStorage.getItem('hisRegDoctor') +
            '&currentPage=' +
            scope.currentPage +
            '&pageSize=' +
            scope.pageSize
        )
        .then((res) => {
          scope.tableData = res.data.list
          scope.total = res.data.total
          scope.pageSize = res.data.pageSize
          scope.currentPage = res.data.currentPage
        })
    }
    //刷新数据
    function refresh() {
      axios
        .post(
          '/HisReg/all?currentPage=' +
            scope.currentPage +
            '&pageSize=' +
            scope.pageSize
        )
        .then((res) => {
          scope.tableData = res.data.list
          scope.total = res.data.total
          scope.pageSize = res.data.pageSize
          scope.currentPage = res.data.currentPage
        })
        .catch((error) => {
          console.log(error)
        })
    }
    //管理员刷新数据
    function adminRefresh() {
      axios
        .post(
          '/HisReg/all?currentPage=' +
            scope.currentPage +
            '&pageSize=' +
            scope.pageSize
        )
        .then((res) => {
          scope.tableData = res.data.list
          scope.total = res.data.total
          scope.pageSize = res.data.pageSize
          scope.currentPage = res.data.currentPage
        })
        .catch((error) => {
          console.log(error)
        })
    }
    //就诊页面
    function addDoctor(regCard) {
      sessionStorage.setItem('regCard', regCard)
      $router.replace('/addDoctor')
    }
    //删除事件
    function useDelete(state) {
      console.log(state)
      //alert('删除失败！权限不够！')

      ElMessageBox.confirm('你确定要删除记录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        axios.post('/HisReg/del?id=' + state).then((res) => {
          console.log('后端：' + res.data.code)
          if (res.data.code == '0') {
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
    //分页
    function changePage(val) {
      scope.currentPage = val
      if (sessionStorage.getItem('permission') == '0') {
        if (search.value != '') {
          searchUser()
        } else {
          adminRefresh()
        }
      } else {
        if (search.value != '') {
          searchUser()
        } else {
          refresh()
        }
      }
    }
    //搜索
    function searchUser() {
      console.log(search.value)
      axios
        .post(
          '/caseHistory/search?search=' +
            search.value +
            '&currentPage=' +
            scope.currentPage +
            '&pageSize=' +
            scope.pageSize +
            '&username=' +
            sessionStorage.getItem('hisRegDoctor')
        )
        .then((res) => {
          scope.tableData = res.data.list
          scope.total = res.data.total
          scope.pageSize = res.data.pageSize
          scope.currentPage = res.data.currentPage
        })
        .catch((error) => {
          console.log(error)
        })
    }
    return {
      scope,
      refresh,
      addDoctor,
      useDelete,
      changePage,
      search,
      searchUser,
    }
  },
}
</script>

<style scoped>
.bb {
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

.my-label {
  background: var(--el-color-success-light-9);
}
.my-content {
  background: var(--el-color-danger-light-9);
}
</style>