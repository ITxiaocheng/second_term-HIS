<template>
  <div class="box">
    <div class="float"></div>
    <div class="login" v-show="formLabelAlign.showD">
      <h2 class="title">怡成医疗系统 欢迎登录</h2>
      <el-form
        label-width="60px"
        :model="formLabelAlign"
        style="max-width: 300px"
      >
        <el-form-item label="账号：">
          <el-input v-model="formLabelAlign.name" style="height: 40px" />
        </el-form-item>
        <el-form-item label="密码：">
          <el-input
            type="password"
            v-model="formLabelAlign.password"
            show-password
            style="height: 40px"
          />
        </el-form-item>
        <el-button plain type="primary" @click="denglu" class="denglu"
          >登录</el-button
        >
      </el-form>
      <div style="margin-left: 0px; margin-top: 20px">
        没有账号，立即<el-link type="primary" @click="reg">注册</el-link>
      </div>
    </div>
    <!-- --------------------------- -->
    <div class="loginReg" v-show="formLabelAlign.showZ">
      <h2 class="title">怡成医疗系统 欢迎注册</h2>
      <el-form
        label-width="90px"
        :model="formLabelAlign"
        style="max-width: 320px"
      >
        <el-form-item label="*姓名：">
          <el-input v-model="formReg.regName" />
        </el-form-item>
        <el-form-item label="身份证号：">
          <el-input v-model="formReg.regIdNumber" />
        </el-form-item>
        <el-form-item label="手机号码：">
          <el-input v-model="formReg.regPhoneNumber" />
        </el-form-item>
        <el-form-item label="密码：">
          <el-input
            type="password"
            v-model="formReg.regPassword"
            show-password
          />
        </el-form-item>
        <el-button plain type="primary" @click="register" class="denglu"
          >注册</el-button
        >
      </el-form>
      <div style="margin-left: 0px; margin-top: 20px">
        已有账号，立即<el-link type="primary" @click="log">登录</el-link>
      </div>
    </div>
  </div>
</template>

<script>
import '../store/ribbon.js'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
import { ElNotification, ElLoading } from 'element-plus'

export default {
  name: 'Login',
  setup() {
    //路由
    const $router = useRouter()
    //表单对象
    const formLabelAlign = reactive({
      name: '19808006488',
      password: '123456',
      showD: true,
      showZ: false,
    })

    //注册表单
    const formReg = reactive({
      regName: '',
      regIdNumber: '',
      regPhoneNumber: '',
      regPassword: '',
    })

    //注册页面显示
    function reg() {
      formLabelAlign.showD = false
      formLabelAlign.showZ = true
    }

    //登录页面显示
    function log() {
      formLabelAlign.showD = true
      formLabelAlign.showZ = false
    }

    //配置请求的基准URL地址
    axios.defaults.baseURL = 'http://localhost:8888/'
    function denglu() {
      //第一次登录 交互后台需要时间，添加加载组件
      const loading = ElLoading.service({
        lock: true,
        text: 'Loading',
        background: 'rgba(0, 0, 0, 0.7)',
      })
      //向后端发请求 接口
      axios
        .post('/HisLogin/all', {
          phoneNumber: formLabelAlign.name,
          password: formLabelAlign.password,
        })
        .then((res) => {
          if (res.data.code) {
            console.log('医生姓名：' + res.data.yisheng)
            ElNotification.success({
              title: res.data.msg,
              message: '欢迎使用怡成信息管理系统！',
              offset: 100,
            })
            sessionStorage.setItem('username', formLabelAlign.name)
            loading.close() //交互成功，关闭加载事件

            //会话传递 账号权限
            sessionStorage.setItem('permission', res.data.qx)
            axios
              .get(
                'http://localhost:8888/MenuServlet?permission=' + res.data.qx
              )
              .then((res) => {
                $router.replace(res.data[0].childMenu[0].menu_path)
              })

            //传递该账号的姓名
            sessionStorage.setItem('hisRegDoctor', res.data.yisheng)
            // axios
            //   .post('/HisReg/doctor?username=' + res.data.yisheng)
            //   .then((res) => {})
            //   .catch((error) => {
            //     console.log(error)
            //   })
          } else {
            ElNotification({
              title: res.data.msg,
              message: '用户名或密码错误！请重新输入...',
              type: 'error',
            })
            loading.close() //交互失败或用户名密码错误，关闭加载事件
            //登录失败清除表单数据
            formLabelAlign.name = ''
            formLabelAlign.password = ''
          }
        })
        .catch((error) => {
          console.log(error)
        })
    }

    axios.defaults.baseURL = 'http://localhost:8888/'
    function register() {
      const parms = {
        name: formReg.regName,
        idNumber: formReg.regIdNumber,
        phoneNumber: formReg.regPhoneNumber,
        password: formReg.regPassword,
      }
      axios
        .post('/HisLogin/insert', parms)
        .then((res) => {
          console.log(res)
          if (res.data.code == 1) {
            console.log(res.data.msg)
            ElNotification({
              title: '成功',
              message: '账户注册成功！',
              type: 'success',
            })
            log()
          } else {
            console.log(res.data.msg)
          }
        })
        .catch((error) => {
          console.log(error)
        })
    }
    return {
      formLabelAlign,
      denglu,
      reg,
      log,
      formReg,
      register,
    }
  },
}
</script>

<style>
.box {
  width: 100%;
  height: 720px;
  /* background: url(./images/login_bj.jpg); */
}

.login {
  width: 400px;
  height: 360px;
  box-sizing: border-box;
  padding: 0 50px;
  border-radius: 5px;
  box-shadow: 0px 2px 12px 0px rgba(105, 105, 105, 0.07);
  background: rgba(255, 255, 255, 0.5);
  /* box-shadow: 0px 0px 10px 5px rgba(255, 255, 255, 0.5);
        background-color: #fff; */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -60%);
  animation: mymove 1s ease-in-out alternate;
  overflow: hidden;
  transition: 1.5s;
}

.loginReg {
  width: 420px;
  height: 440px;
  box-sizing: border-box;
  padding: 0 60px;
  border-radius: 5px;
  box-shadow: 0px 2px 12px 0px rgba(105, 105, 105, 0.07);
  background: rgba(255, 255, 255, 0.5);
  /* box-shadow: 0px 0px 10px 5px rgba(255, 255, 255, 0.5);
        background-color: #fff; */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -60%);
  animation: mymove 1s ease-in-out alternate;
  overflow: hidden;
  transition: 1.5s;
}

@keyframes mymove {
  0% {
    width: 0px;
    height: 5px;
  }

  10% {
    width: 44px;
    height: 5px;
  }

  15% {
    width: 88px;
    height: 5px;
  }

  20% {
    width: 132px;
    height: 5px;
  }

  25% {
    width: 176px;
    height: 5px;
  }

  30% {
    width: 220px;
    height: 5px;
  }

  35% {
    width: 264px;
    height: 5px;
  }

  40% {
    width: 308px;
    height: 5px;
  }

  45% {
    width: 352px;
    height: 5px;
  }

  50% {
    width: 400px;
    height: 5px;
  }

  55% {
    height: 36px;
  }

  60% {
    height: 72px;
  }

  65% {
    height: 108px;
  }

  70% {
    height: 144px;
  }

  75% {
    height: 180px;
  }

  80% {
    height: 216px;
  }

  85% {
    height: 252px;
  }

  90% {
    height: 288px;
  }

  95% {
    height: 324px;
  }

  100% {
    height: 360px;
  }
}

/* 清除浮动 */
.float {
  width: 100%;
  height: 10px;
}
.title {
  text-align: center;
  margin: 30px 0px;
}

.denglu {
  width: 300px;
  height: 40px;
  margin-left: 0px;
  margin-top: 20px;
}
</style>