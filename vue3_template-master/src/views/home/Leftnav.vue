<template>
  <el-menu
    :default-active="nowPath"
    class="el-menu-vertical-demo"
    router
    unique-opened
    active-text-color="#fe4800"
    background-color="#74B2D9"
  >
    <div v-for="item in menu" :key="item" class="elMenu">
      <el-menu-item
        :index="item.menu_path"
        @click="pathItem(item)"
        v-if="!item.childMenu"
        >{{ item.menu_name }}</el-menu-item
      >
      <el-sub-menu
        :index="item.menu_name"
        style="width: 200px"
        v-if="item.childMenu"
      >
        <template #title>
          <!-- 图标 -->
          <el-icon>
            <component :is="item.menu_icon" color="#fff"></component>
          </el-icon>
          <span style="color: #fff">{{ item.menu_name }}</span>
        </template>
        <el-menu-item-group v-for="itemc in item.childMenu" :key="itemc">
          <el-menu-item :index="itemc.menu_path" @click="pathItem(itemc)">{{
            itemc.menu_name
          }}</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
    </div>
  </el-menu>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { reactive, ref, toRefs, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
export default {
  components: { Document, Location, Setting, IconMenu },
  setup() {
    const $route = useRouter()
    const nowPath = ref('/aa')
    const state = reactive({
      menu: [],
      permission: sessionStorage.getItem('permission'),
    })
    onMounted(() => {
      axios
        .get('http://localhost:8888/MenuServlet?permission=' + state.permission)
        .then((res) => {
          state.menu = res.data
        })
    })
    const pathItem = (item) => {
      nowPath.value = item.menu_path
    }
    return {
      ...toRefs(state),
      nowPath,
      pathItem,
    }
  },
}
</script>

<style scoped>
</style>