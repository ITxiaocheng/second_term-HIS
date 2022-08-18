<template>
  <div>
    <el-container style="height: 100vh">
      <el-header class="head"><Top /></el-header>
      <el-container>
        <el-aside width="200px" class="left"><LeftNav /></el-aside>
        <el-main class="right"><router-view /></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Top from '@/views/home/Top'
import LeftNav from '@/views/home/Leftnav'
export default {
  components: { Top, LeftNav },
}
</script>

<style>
.head {
  background: #fff;
}
.left {
  background: #74b2d9;
}
.right {
  background: #eee;
}
</style>
