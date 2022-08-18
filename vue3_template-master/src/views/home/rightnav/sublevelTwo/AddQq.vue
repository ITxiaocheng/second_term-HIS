<template>
  <div class="addQq">
    <div class="daohangtitle">增加用户信息</div>
    <div class="mainbox">
      <el-card shadow="never">
        <template #header>
          <div class="card-header">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">添加用户</span>
            <el-breadcrumb :separator-icon="ArrowRight" style="float: right">
              <el-breadcrumb-item :to="{ path: '/qq' }"
                >权限管理</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/Qq' }"
                >用户管理</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/AddQq' }"
                >添加用户信息</el-breadcrumb-item
              >
            </el-breadcrumb>
          </div>
        </template>
        <el-form :model="form" label-width="120px">
          <el-form-item label="姓名：">
            <el-input v-model="form.name" style="width: 200px" />
          </el-form-item>
          <el-form-item label="身份证号：">
            <el-input v-model="form.idNumber" style="width: 200px" />
          </el-form-item>
          <el-form-item label="电话号码：">
            <el-input v-model="form.phoneNumber" style="width: 200px" />
          </el-form-item>
          <el-form-item label="密码：">
            <el-input v-model="form.password" style="width: 200px" />
          </el-form-item>
          <el-form-item label="权限：">
            <el-input v-model="form.permission" style="width: 200px" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">注册</el-button>
            <el-button>Cancel</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ElNotification, ElLoading } from 'element-plus'
export default {
  name: 'AddQq',
  setup() {
    const $router = useRouter()
    const form = reactive({
      name: '',
      idNumber: '',
      phoneNumber: '',
      password: '',
      permission: '',
    })
    axios.defaults.baseURL = 'http://localhost:8888/'
    //添加用户数据
    function onSubmit() {
      const parms = {
        name: form.name,
        idNumber: form.idNumber,
        phoneNumber: form.phoneNumber,
        password: form.password,
        permission: form.permission,
      }
      axios
        .post('/HisLogin/insert', parms)
        .then((res) => {
          if (res.data.code === 1) {
            ElNotification.success({
              title: res.data.msg,
              message: '数据插入成功！',
              offset: 100,
            })
            $router.replace('/Qq')
          } else {
            ElNotification({
              title: error.data.msg,
              message: '用户名或密码错误！请重新输入...',
              type: 'error',
            })
          }
        })
        .catch((error) => {
          console.log(error)
        })
    }
    return {
      form,
      onSubmit,
    }
  },
}
</script>

<style>
.addQq {
  width: 100%;
  height: 100%;
  background: #fff;
  padding-left: 20px;
  box-sizing: border-box;
}

.daohangtitle {
  height: 30px;
  font-size: 12px;
  background: #eee;
  margin-left: -20px;
}
</style>