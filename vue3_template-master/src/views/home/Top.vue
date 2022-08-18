<template>
  <div class="top" style="position: relative">
    <div class="topTitle">怡成医疗管理系统</div>
    <div style="position: absolute; left: 200px"></div>
    <div
      style="
        width: 180px;
        height: 100%;
        position: absolute;
        top: 0;
        right: 60px;
      "
    >
      <div
        style="
          float: left;
          width: 35px;
          height: 35px;
          margin-top: 13px;
          margin-right: 5px;
        "
      >
        <img
          src="../images/宣传照片.png"
          style="
            width: 100%;
            height: 100%;
            border-radius: 50%;
            border: 1px solid #000;
          "
          alt=""
        />
      </div>
      <span class="userName">{{ username }}</span>
    </div>
    <el-button type="primary" @click="exit">退出</el-button>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { ElNotification } from 'element-plus'
import axios from 'axios'

export default {
  name: 'top',
  setup() {
    const $router = useRouter()

    function exit() {
      if (confirm('确定退出吗？')) {
        $router.replace('/login')
        ElNotification({
          title: 'Success',
          message: 'This is a success message',
          type: 'success',
        })
        //清除会话
        sessionStorage.getItem('username') //使用getItem()方法获取指定的会话存储
        sessionStorage.clear() //使用clear()方法清除会话存储
      } else {
      }
    }
    return {
      exit,
    }
  },
  data() {
    return {
      username: 'ads',
    }
  },
  mounted() {
    this.username = sessionStorage.getItem('username')
  },
}
</script>

<style scoped>
.top {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  background-color: #fff;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.topTitle {
  /* position: fixed; */
  height: 100%;
  width: 200px;
  background-color: #76acd7;
  margin-left: -20px;
  text-align: center;
  line-height: 60px;
  font-size: 20px;
}

.userName {
  float: left;
  line-height: 60px;
}
</style>