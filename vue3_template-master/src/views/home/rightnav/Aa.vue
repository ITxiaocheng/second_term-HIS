<template>
  <div class="aa">
    <!-- <div class="daohangtitle">普通挂号</div> -->
    <div class="mainbox">
      <el-card shadow="never" style="position: relative; height: 550px">
        <template #header>
          <div class="card-header" style="margin-bottom: 5px">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">挂号信息列表</span>
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/home' }"
                >预约挂号</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/aa' }"
                >普通挂号</el-breadcrumb-item
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
            <el-button type="primary" @click="addStudentInfo">添加</el-button>
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
          <el-table-column label="状态" width="100">
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
              <!-- disabled -->
              <el-button
                type="primary"
                size="small"
                v-bind:disabled="scope.row.regState == '已就诊'"
                @click="writeOff(scope.row.regId, $event)"
                >核销</el-button
              >
              <el-button
                type="danger"
                size="small"
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
          :page-size="scope.pageSize"
          @current-change="changePage"
          style="position: fixed; bottom: 110px"
        />
      </el-card>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessageBox, ElNotification } from 'element-plus'
export default {
  name: 'Aa',
  setup() {
    const $router = useRouter()
    const search = ref('')
    const scope = reactive({
      tableData: [],

      regId: '',
      regName: '',
      regGender: '',
      regAge: '',
      regDate: '',
      regIdNumber: '',
      regSite: '',
      regDepartments: '',
      regDoctor: '',
      regState: '',

      total: 0, //总条数
      pageSize: 5, //每页显示行数
      currentPage: 1, //当前页码
    })

    const jy = {
      activeColor: 'green',
    }

    //配置请求的基准URL地址
    // var params = {
    //   currentPage: scope.currentPage,
    //   pageSize: scope.pageSize,
    // }
    axios.defaults.baseURL = 'http://localhost:8888/'

    // axios.post('/HisReg/del', { params }).then((res) => {
    //   console.log(res)
    //   scope.tableData = res.data
    // })
    axios
      .post(
        '/HisReg/pageing?currentPage=' +
          scope.currentPage +
          '&pageSize=' +
          scope.pageSize
      )
      .then((res) => {
        console.log(res)
        scope.tableData = res.data.list
        scope.total = res.data.total
        scope.pageSize = res.data.pageSize
        scope.currentPage = res.data.currentPage
      })
    //重新获取数据
    function loadData() {
      axios
        .post(
          '/HisReg/pageing?currentPage=' +
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
    //手动添加挂号信息
    function addStudentInfo() {
      $router.replace('/addReg')
    }
    //搜索
    function searchUser() {
      console.log(search.value)
      axios
        .post(
          '/HisReg/search?search=' +
            search.value +
            '&currentPage=' +
            scope.currentPage +
            '&pageSize=' +
            scope.pageSize
        )
        .then((res) => {
          console.log(scope.currentPage)
          scope.tableData = res.data.list
          scope.total = res.data.total
          scope.pageSize = res.data.pageSize
          scope.currentPage = res.data.currentPage
        })
        .catch((error) => {
          console.log(error)
        })
    }
    //分页
    function changePage(val) {
      scope.currentPage = val
      //如果
      if (search.value != '') {
        searchUser()
      } else {
        loadData()
      }
    }
    //更新就诊状态
    function writeOff(id) {
      axios
        .post('/HisReg/visitStatus?id=' + id)
        .then((res) => {
          loadData() //更新数据
          console.log(id + '被点击了')
          jy.isActive = true
        })
        .catch((error) => {
          console.log(error)
        })
    }
    //增加删除事件
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
            loadData()
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
    return {
      scope,
      addStudentInfo,
      useDelete,
      changePage,
      searchUser,
      search,
      writeOff,
      jy,
    }
  },
}
</script>

<style scoped>
.stateColor {
  color: green;
}
.aa {
  width: 100%;
  height: 100%;
  background: #fff;
  box-sizing: border-box;
}

.mainbox {
  width: 100%;
  margin: 0 auto;
}
.daohangtitle {
  height: 30px;
  font-size: 12px;
  background: #eee;
  margin-left: -20px;
}
.sur {
  margin-top: 20px;
  position: relative;
  padding-left: 10px;
  font-size: 20px;
  line-height: 1;
}
.sur:before {
  content: '';
  width: 5px;
  height: 25px;
  background: red;
  position: absolute;
  left: 0;
  top: 0;
  border-radius: 5px;
}
.sura {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 30px;
}
.surai {
  width: 30%;
  height: 125px;
  line-height: 125px;
  text-align: center;
  background: #eee;
  border-radius: 10px;
  color: #666;
  margin-right: 20px;
}
</style>