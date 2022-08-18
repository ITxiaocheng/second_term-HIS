<template>
  <div class="ee">
    <div class="mainbox">
      <!-- 头部搜索 -->
      <div class="topSearch">
        <el-row>
          <el-col :span="14">
            <span class="topSearch_span">输入中文或者拼音查询</span>
            <el-input
              style="width: 200px; height: 30px"
              v-model="search1"
              @blur="search(search1)"
            />
          </el-col>
          <el-col :span="4">
            <span class="topSearch_span">药品售价利润率</span>
            <el-input style="width: 60px; height: 30px" v-model="search2" />
          </el-col>
          <el-col :span="3">
            <span class="topSearch_span">库存量少于</span>
            <el-input style="width: 60px; height: 30px" v-model="search3" />
          </el-col>
          <el-col :span="3">
            <div style="width: 100%; height: 100%">
              <el-button
                style="height: 30px; margin-top: 10px; margin-left: 20px"
                @click="inventoryWarning(search3)"
                >库存报警查询</el-button
              >
            </div>
          </el-col>
        </el-row>
      </div>
      <!-- 分类 -->
      <div class="classify">
        <div v-for="(item, index) in classify" :key="index">
          <el-button class="classifyButton" @click="classifyBtn(item.title)">{{
            item.title
          }}</el-button>
        </div>
      </div>
      <!-- 药物数据 -->
      <el-table
        :data="scope.tableData"
        border
        style="width: 100%; height: 450px; overflow-x: hidden; overflow-y: auto"
      >
        <el-table-column
          prop="medName"
          label="药物名称[规格/商品名]"
          width="230"
        />
        <el-table-column prop="medUsualDose" label="常用量" width="130" />
        <el-table-column prop="medUnit" label="单位" width="64" />
        <el-table-column
          prop="medUsageAndDosage"
          label="用法用量"
          width="180"
        />
        <el-table-column prop="medSpell" label="拼音" width="125" />
        <el-table-column prop="medShapeCode" label="条形码" width="125" />
        <el-table-column prop="medClassification" label="分类" width="125" />
        <el-table-column prop="medPurchasingPrice" label="进价" width="100" />
        <el-table-column prop="medSellingPrice" label="售价" width="100" />
        <el-table-column prop="medStock" label="库存量" width="100" />
      </el-table>
      <!-- 增删改查 -->
      <div class="operation">
        <el-row>
          <el-col :span="12">
            <span
              style="
                font-size: 14px;
                color: red;
                line-height: 50px;
                margin-left: 10px;
              "
              >可直接在表格中修改或添加药物！</span
            >
          </el-col>
          <el-col :span="12">
            <el-row>
              <el-col :span="6">
                <el-button style="margin: 0; margin-top: 10px"
                  >药物维护教程</el-button
                >
              </el-col>
              <el-col :span="9">
                <el-button
                  style="margin: 0; margin-top: 10px"
                  @click="addMedicine, (centerDialogVisible = true)"
                  >添 加</el-button
                >
                <el-button style="margin: 0; margin-top: 10px" disabled
                  >保 存</el-button
                >
                <el-button style="margin: 0; margin-top: 10px" disabled
                  >删 除</el-button
                >
              </el-col>
              <el-col :span="9">
                <el-button style="margin: 0; margin-top: 10px" disabled
                  >导出Excel</el-button
                >
                <el-button style="margin: 0; margin-top: 10px" disabled
                  >导 入</el-button
                >
                <el-button style="margin: 0; margin-top: 10px" disabled
                  >清 空</el-button
                >
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </div>

      <!-- 添加 -->
      <el-dialog
        v-model="centerDialogVisible"
        title="添加药物信息~"
        width="80%"
        center
      >
        <table border="">
          <tr>
            <th style="width: 180px">药物名称[规格/商品名]</th>
            <th style="width: 120px">常用量</th>
            <th>单位</th>
            <th style="width: 150px">用法用量</th>
            <th style="width: 120px">拼音</th>
            <th style="width: 120px">条形码</th>
            <th style="width: 120px">分类</th>
            <th>进价</th>
            <th>售价</th>
            <th>库存量</th>
          </tr>
          <tr>
            <td>
              <el-input v-model="addmed.medName" />
            </td>
            <td>
              <el-input v-model="addmed.medUsualDose" />
            </td>
            <td>
              <el-select
                v-model="scope.medUnit"
                class="m-2"
                @change="chooseMedUnit($event)"
              >
                <el-option
                  v-for="item in options_medUnit"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </td>
            <td>
              <el-select v-model="addmed.medUsageAndDosage" class="m-2">
                <el-option
                  v-for="item in options_medUsageAndDosage"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  @change="chooseMedUsageAndDosage($event)"
                />
              </el-select>
            </td>
            <td>
              <el-input v-model="addmed.medSpell" />
            </td>
            <td>
              <el-input v-model="addmed.medShapeCode" />
            </td>
            <td>
              <el-select v-model="addmed.medClassification" class="m-2">
                <el-option
                  v-for="item in options_medClassification"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  @change="chooseMedClassification($event)"
                />
              </el-select>
            </td>
            <td>
              <el-input
                v-model="addmed.medPurchasingPrice"
                @blur="blurPurchasingPrice(addmed.medPurchasingPrice)"
              />
            </td>
            <td>
              <el-input v-model="addmed.medSellingPrice" />
            </td>
            <td>
              <el-input v-model="addmed.medStock" />
            </td>
          </tr>
        </table>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="centerDialogVisible = false">关闭</el-button>
            <el-button type="primary" @click="submitAddMedicine"
              >提交</el-button
            >
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { reactive, ref } from 'vue'
import { ElNotification } from 'element-plus'
export default {
  name: 'Ee',
  setup() {
    const centerDialogVisible = ref(false)
    const search1 = ref('')
    const search2 = ref('1.25')
    const search3 = ref('500')
    const scope = reactive({
      tableData: [],
    })
    const options_medUnit = [
      {
        value: 'ml',
        label: 'ml',
      },
      {
        value: 'mg',
        label: 'mg',
      },
      {
        value: 'g',
        label: 'g',
      },
      {
        value: '粒',
        label: '粒',
      },
      {
        value: '片',
        label: '片',
      },
      {
        value: '个',
        label: '个',
      },
      {
        value: '包',
        label: '包',
      },
      {
        value: '盒',
        label: '盒',
      },
      {
        value: '支',
        label: '支',
      },
      {
        value: '瓶',
        label: '瓶',
      },
      {
        value: '贴',
        label: '贴',
      },
      {
        value: '丸',
        label: '丸',
      },
      {
        value: '袋',
        label: '袋',
      },
    ]
    const options_medUsageAndDosage = [
      {
        value: '2粒 每日3次 口服',
        label: '2粒 每日3次 口服',
      },
      {
        value: '1粒 每日1次 口服',
        label: '1粒 每日1次 口服',
      },
      {
        value: '1粒 每晚1次 口服',
        label: '1粒 每晚1次 口服',
      },
      {
        value: '静脉滴注 每日1次',
        label: '静脉滴注 每日1次',
      },
      {
        value: '静脉推注 每日1次',
        label: '静脉推注 每日1次',
      },
      {
        value: '1支 肌肉注射 每日1次',
        label: '1支 肌肉注射 每日1次',
      },
      {
        value: '雾化吸入 每日1次',
        label: '雾化吸入 每日1次',
      },
      {
        value: '肛塞 立即',
        label: '肛塞 立即',
      },
      {
        value: '滴眼 每日3次',
        label: '滴眼 每日3次',
      },
      {
        value: '外用',
        label: '外用',
      },
    ]
    const options_medClassification = [
      {
        value: '抗生素',
        label: '抗生素',
      },
      {
        value: '输液',
        label: '输液',
      },
      {
        value: '心血管',
        label: '心血管',
      },
      {
        value: '呼吸',
        label: '呼吸',
      },
      {
        value: '消化',
        label: '消化',
      },
      {
        value: '神经',
        label: '神经',
      },
      {
        value: '内分泌',
        label: '内分泌',
      },
      {
        value: '解热镇痛',
        label: '解热镇痛',
      },
      {
        value: '外用',
        label: '外用',
      },
      {
        value: '其他',
        label: '其他',
      },
      {
        value: '中成药',
        label: '中成药',
      },
      {
        value: '颗粒',
        label: '颗粒',
      },
    ]
    //按钮name
    const classify = [
      { title: '全部西药' },
      { title: '抗生素' },
      { title: '输液' },
      { title: '心血管' },
      { title: '呼吸' },
      { title: '消化' },
      { title: '神经' },
      { title: '内分泌' },
      { title: '解热镇痛' },
      { title: '外用' },
      { title: '其他' },
      { title: '中成药' },
      { title: '中药' },
      { title: '颗粒' },
      { title: '收费项目' },
      { title: '用法' },
    ]
    //添加药物信息
    const addmed = reactive({
      medName: '',
      medUsualDose: '',
      medUnit: '',
      medUsageAndDosage: '',
      medSpell: '',
      medShapeCode: '',
      medClassification: '',
      medPurchasingPrice: 0, //进价
      medSellingPrice: 0, //售价
      medStock: 0, //库存
    })
    axios.defaults.baseURL = 'http://localhost:8888/'
    axios
      .get('/medicine/all')
      .then((res) => {
        scope.tableData = res.data
      })
      .catch((error) => {
        console.log(error)
      })

    //分类
    function classifyBtn(title) {
      if (title == '全部西药') {
        axios
          .get('/medicine/all')
          .then((res) => {
            scope.tableData = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      } else {
        axios
          .get('/medicine/classify?medClassifioation=' + title)
          .then((res) => {
            scope.tableData = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }
    //库存报警查询
    function inventoryWarning(search3) {
      axios
        .get('/medicine/inventoryWarning?medStock=' + search3)
        .then((res) => {
          scope.tableData = res.data
        })
        .catch((error) => {
          console.log(error)
        })
    }
    //搜索
    function search(search1) {
      axios
        .get('/medicine/inquire?name=' + search1)
        .then((res) => {
          scope.tableData = res.data
        })
        .catch((error) => {
          console.log(error)
        })
    }
    //
    function chooseMedUnit(e) {
      addmed.medUnit = e
    }
    //
    function chooseMedUsageAndDosage(e) {
      addmed.medUsageAndDosage = e
    }
    //
    function chooseMedClassification(e) {
      addmed.medClassification = e
    }
    //
    function blurPurchasingPrice(price) {
      addmed.medSellingPrice = price * search2.value
      console.log(addmed.medSellingPrice)
    }
    //------------------增删改查
    //添加药物信息
    //提交
    function submitAddMedicine() {
      const parms = {
        medName: addmed.medName,
        medUsualDose: addmed.medUsualDose,
        medUnit: addmed.medUnit,
        medUsageAndDosage: addmed.medUsageAndDosage,
        medSpell: addmed.medSpell,
        medShapeCode: addmed.medShapeCode,
        medClassification: addmed.medClassification,
        medPurchasingPrice: addmed.medPurchasingPrice,
        medSellingPrice: addmed.medSellingPrice,
        medStock: addmed.medStock,
      }
      axios
        .post('/medicine/insertAll', parms)
        .then((res) => {
          if (res.data.code == 1) {
            ElNotification.success({
              title: res.data.msg,
              message: '数据插入成功！',
              offset: 100,
            })
            centerDialogVisible.value = false
          } else {
            alert('插入失败！')
          }
        })
        .catch((error) => {
          console.log(error)
        })
    }
    return {
      scope,
      search1,
      search2,
      search3,
      classify,
      classifyBtn,
      inventoryWarning,
      search,
      centerDialogVisible,
      addmed,
      options_medUnit,
      options_medUsageAndDosage,
      options_medClassification,
      submitAddMedicine,
      chooseMedUnit,
      chooseMedUsageAndDosage,
      chooseMedClassification,
      blurPurchasingPrice,
    }
  },
}
</script>

<style scoped>
.ee {
  width: 100%;
  height: 100%;
  background: #fff;
  box-sizing: border-box;
}

.mainbox {
  width: 100%;
  margin: 0 auto;
}

.topSearch {
  width: 100%;
  height: 50px;
  border-bottom: 1px solid #ebeef5;
}

.topSearch_span {
  font-size: 14px;
  line-height: 50px;
  margin: 0px 10px;
}

.classify {
  width: 100%;
  height: 50px;
}

.classifyButton {
  float: left;
  margin: 0 1px;
  padding: 0 19.5px;
  margin-top: 10px;
}

.operation {
  width: 100%;
  height: 50px;
  margin-top: 10px;
}
</style>
