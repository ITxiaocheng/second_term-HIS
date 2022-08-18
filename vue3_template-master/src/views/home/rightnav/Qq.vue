<template>
  <div class="aa">
    <!-- <div class="daohangtitle">用户管理</div> -->
    <div class="mainbox">
      <el-card shadow="never" style="position: relative; height: 550px">
        <template #header>
          <div class="card-header" style="margin-bottom: 5px">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">用户信息列表</span>
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/home' }"
                >权限管理</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/qq' }"
                >用户管理</el-breadcrumb-item
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
            <el-button type="primary" @click="addUserInfo">添加</el-button>
          </el-col>
        </el-row>
        <el-table :data="scope.tableData" style="width: 100%">
          <el-table-column label="序号" width="100">
            <template #default="scope">
              <div>{{ scope.row.id }}</div>
            </template>
          </el-table-column>
          <el-table-column label="姓名" width="130">
            <template #default="scope">
              <div>{{ scope.row.name }}</div>
            </template>
          </el-table-column>
          <el-table-column label="身份证号" width="200">
            <template #default="scope">
              <div>{{ scope.row.idNumber }}</div>
            </template>
          </el-table-column>
          <el-table-column label="电话号码" width="150">
            <template #default="scope">
              <div>{{ scope.row.phoneNumber }}</div>
            </template>
          </el-table-column>
          <el-table-column label="密码" width="200">
            <template #default="scope">
              <div>{{ scope.row.password }}</div>
            </template>
          </el-table-column>
          <el-table-column label="权限" width="100">
            <template #default="scope">
              <div>{{ scope.row.permission }}</div>
            </template>
          </el-table-column>

          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                type="primary"
                size="small"
                @click="
                  redact(
                    scope.row.id,
                    scope.row.name,
                    scope.row.idNumber,
                    scope.row.phoneNumber,
                    scope.row.password,
                    scope.row.permission
                  ),
                    (centerDialogVisible = true)
                "
                >编辑</el-button
              >
              <el-button
                type="danger"
                size="small"
                @click="useDelete(scope.row.id)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-dialog
          v-model="centerDialogVisible"
          title="编辑"
          width="30%"
          center
        >
          <el-form label-width="120px">
            <el-form-item label="姓名：">
              <el-input v-model="scope.name" style="width: 200px" />
            </el-form-item>
            <el-form-item label="身份证号：">
              <el-input v-model="scope.idNumber" style="width: 200px" />
            </el-form-item>
            <el-form-item label="电话号码：">
              <el-input v-model="scope.phoneNumber" style="width: 200px" />
            </el-form-item>
            <el-form-item label="密码：">
              <el-input v-model="scope.password" style="width: 200px" />
            </el-form-item>
            <el-form-item label="权限：">
              <el-input v-model="scope.permission" style="width: 200px" />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="centerDialogVisible = false">关闭</el-button>
              <el-button type="primary" @click="submit">提交</el-button>
            </span>
          </template>
        </el-dialog>
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
          style="position: fixed; bottom: 100px"
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
  name: 'Qq',
  setup() {
    const $router = useRouter()
    const search = ref('')
    const centerDialogVisible = ref(false)
    const scope = reactive({
      tableData: [],

      id: '',
      name: '',
      idNumber: '',
      phoneNumber: '',
      password: '',
      permission: '',

      total: 0, //总条数
      pageSize: 5, //每页显示行数
      currentPage: 1, //当前页码
    })

    //配置请求的基准URL地址
    axios.defaults.baseURL = 'http://localhost:8888/'
    axios
      .post(
        '/HisLogin/pageing?pageSize=' +
          scope.pageSize +
          '&currentPage=' +
          scope.currentPage
      )
      .then((res) => {
        console.log(res.data)
        scope.tableData = res.data.list
        scope.total = res.data.total
        scope.pageSize = res.data.pageSize
        scope.currentPage = res.data.currentPage
      })
    //重新获取数据
    function loadData() {
      axios
        .post(
          '/HisLogin/pageing?pageSize=' +
            scope.pageSize +
            '&currentPage=' +
            scope.currentPage
        )
        .then((res) => {
          console.log(res.data)
          scope.tableData = res.data.list
          scope.total = res.data.total
          scope.pageSize = res.data.pageSize
          scope.currentPage = res.data.currentPage
        })
    }
    //手动添加挂号信息
    function addUserInfo() {
      $router.replace('/addQq')
    }
    //搜索
    function searchUser() {
      console.log(search.value)
      axios
        .post(
          '/HisLogin/search?search=' +
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
      console.log(val)
      //如果
      if (search.value != '') {
        searchUser()
      } else {
        loadData()
      }
    }
    //增加删除事件
    function useDelete(state) {
      console.log(state)
      ElMessageBox.confirm('你确定要删除记录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        axios.post('/HisPower/del?id=' + state).then((res) => {
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

    //编辑-传递数据给编辑框
    function redact(id, name, idNumber, phoneNumber, password, permission) {
      scope.id = id
      scope.name = name
      scope.idNumber = idNumber
      scope.phoneNumber = phoneNumber
      scope.password = password
      scope.permission = permission
    }
    //提交-向后台发送请求
    function submit() {
      const params = {
        id: scope.id,
        name: scope.name,
        idNumber: scope.idNumber,
        phoneNumber: scope.phoneNumber,
        password: scope.password,
        permission: scope.permission,
      }
      axios
        .post('/HisLogin/update', params)
        .then((res) => {
          console.log(res.data.msg)
          ElNotification({
            title: '成功',
            message: '数据更新成功',
            type: 'success',
          })
          //重新加载数据
          loadData()
          centerDialogVisible.value = false
          console.log(centerDialogVisible.value)
        })
        .catch((error) => {
          console.log(error)
        })
    }
    return {
      scope,
      addUserInfo,
      useDelete,
      changePage,
      searchUser,
      search,
      redact,
      centerDialogVisible,
      submit,
    }
  },
}
</script>

<style>
.aa {
  width: 100%;
  height: 100%;
  background: #fff;
  box-sizing: border-box;
}
.daohangtitle {
  height: 30px;
  font-size: 12px;
  background: #eee;
  margin-left: -20px;
}
.mainbox {
  width: 100%;
  margin: 0 auto;
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

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>