<template>
  <div class="dd">
    <div class="mainbox">
      <el-card shadow="never">
        <template #header>
          <div class="card-header" style="margin-bottom: 5px">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle" v-if="!false">处方</span>
            <input type="text" v-if="false" />
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/dd' }"
                >电子处方</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/dd' }"
                >处方</el-breadcrumb-item
              >
            </el-breadcrumb>
          </div>
        </template>

        <el-form :model="form" label-width="120px">
          <el-form-item label="就诊卡号">
            <el-input v-model="form.card" style="width: 200px" />
            <el-button type="success" @click="inquire" style="margin-left: 10px"
              >查询</el-button
            >
          </el-form-item>
        </el-form>

        <el-row>
          <el-col :span="8">
            <!-- 选药 -->
            <div class="xuanYao">
              <!-- 查询药物 -->
              <el-form-item label="输入中文或者拼音查询">
                <el-input v-model="form.inquire" @blur="inquireYao" />
              </el-form-item>
              <el-scrollbar height="400px" style="width: 100%">
                <p
                  v-for="item in form.medicine"
                  :key="item"
                  class="scrollbar-demo-item"
                  @dblclick="yao(item.medName, item.medUsageAndDosage)"
                >
                  {{ item.medName }} {{ item.medUsualDose }}{{ item.medUnit }}
                </p>
              </el-scrollbar>
            </div>
            <span style="font-size: 12px; color: red">
              温馨提示：请先点击保存再进行下载图片！ </span
            ><br />
            <!-- 点击调用方法获取截图 -->
            <el-button type="primary" class="btn" @click="getPrintScreen">
              获取截图
            </el-button>
            <!-- 点击保存图片 -->
            <el-button
              type="primary"
              class="btn"
              @click="downloadIamge('电子处方笺')"
            >
              保存图片
            </el-button>
          </el-col>
          <el-col :span="12" style="margin-left: 100px">
            <!-- 电子处方笺 -->
            <div class="prescription" ref="content">
              <div class="top">
                <span>湖南省第二人民医院脑科医院</span><br />
                <span>处 方 笺</span>
              </div>
              <div class="list">
                <el-row class="row_top">
                  <el-col :span="6">
                    <el-row>
                      <el-col :span="8" class="title">姓名:</el-col>
                      <el-col :span="16">
                        <div class="borderBttom">
                          {{ form.caseName }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                  <el-col :span="5">
                    <el-row>
                      <el-col :span="8" class="title">性别:</el-col>
                      <el-col :span="16">
                        <div class="borderBttom">
                          {{ form.caseGender }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                  <el-col :span="5">
                    <el-row>
                      <el-col :span="8" class="title">年龄:</el-col>
                      <el-col :span="16">
                        <div class="borderBttom">
                          {{ form.caseAge }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="8" class="title">科别:</el-col>
                      <el-col :span="16">
                        <div class="borderBttom">
                          {{ form.caseDepartments }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
                <el-row class="row_top">
                  <el-col :span="16">
                    <el-row>
                      <el-col :span="6" class="title">住址/电话:</el-col>
                      <el-col :span="18">
                        <div class="borderBttom">
                          {{ form.caseIdNmber }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="6" class="title">日期:</el-col>
                      <el-col :span="18">
                        <div class="borderBttom">
                          {{ form.caseDate }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
                <el-row class="row_top">
                  <el-col :span="16">
                    <el-row>
                      <el-col :span="3" class="title">诊断:</el-col>
                      <el-col :span="21">
                        <div class="borderBttom">
                          {{ form.caseDiagnose }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="8" class="title">过敏史:</el-col>
                      <el-col :span="16">
                        <div class="borderBttom">
                          {{ form.caseAllergy }}
                        </div>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
              </div>
              <div class="main">
                <div class="main_RP">
                  <img src="./img/RP.png" alt="" />
                </div>
                <div class="main_yao">
                  <div
                    v-for="(v, i) in form.addRankData"
                    v-bind:key="i"
                    style="margin-bottom: 5px"
                  >
                    <div>{{ v[0] }}</div>
                    <div>{{ v[1] }}</div>
                  </div>
                </div>
              </div>
              <div class="bottom">
                <el-row class="row_top">
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="5" class="title">医师:</el-col>
                      <el-col :span="19">
                        <div class="borderBttom"></div
                      ></el-col> </el-row
                  ></el-col>
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="5" class="title">配药:</el-col>
                      <el-col :span="19">
                        <div class="borderBttom"></div
                      ></el-col> </el-row
                  ></el-col>
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="5" class="title">复核:</el-col>
                      <el-col :span="19">
                        <div class="borderBttom"></div
                      ></el-col> </el-row
                  ></el-col>
                </el-row>
                <el-row class="row_top">
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="5" class="title">药费:</el-col>
                      <el-col :span="19">
                        <div class="borderBttom"></div
                      ></el-col> </el-row
                  ></el-col>
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="5" class="title">其他:</el-col>
                      <el-col :span="19">
                        <div class="borderBttom"></div
                      ></el-col> </el-row
                  ></el-col>
                  <el-col :span="8">
                    <el-row>
                      <el-col :span="5" class="title">合计:</el-col>
                      <el-col :span="19">
                        <div class="borderBttom"></div
                      ></el-col> </el-row
                  ></el-col>
                </el-row>
              </div>
            </div>
          </el-col>
        </el-row>

        <div class="img-box">
          <h2>截图结果:</h2>
          <!-- 通过img标签把获取到的截图呈现出来 -->
          <img :src="imgUrl" alt="" id="picurl" />
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import axios from 'axios'
import html2canvas from 'html2canvas'
export default {
  name: 'Dd',
  data() {
    return {
      imgUrl: null, //截图地址
    }
  },
  methods: {
    //获取截图方法
    getPrintScreen() {
      // 第一个参数是需要生成截图的元素,第二个是自己需要配置的参数,宽高等
      html2canvas(this.$refs.content, {
        // width: 30, //截图宽度
        // height: 50, //截图高度
        backgroundColor: null, //画出来的图片有白色的边框,不要可设置背景为透明色（null）
        useCORS: true, //支持图片跨域
        scale: 1, //设置放大的倍数
      }).then((canvas) => {
        // 把生成的base64位图片上传到服务器,生成在线图片地址
        let url = canvas.toDataURL('image/png') // toDataURL: 图片格式转成 base64
        this.imgUrl = url
      })
    },
    //下载图片
    downloadIamge(name) {
      var image = new Image()
      //跨域会导致a标签在部分浏览器中会直接打开新标签页 解决跨域 Canvas 污染问题
      image.setAttribute('crossOrigin', 'anonymous')
      image.onload = function () {
        var canvas = document.createElement('canvas')
        canvas.width = image.width
        canvas.height = image.height

        var context = canvas.getContext('2d')
        context.drawImage(image, 0, 0, image.width, image.height)
        var url = canvas.toDataURL('image/png')

        // 生成一个a元素
        var a = document.createElement('a')
        // 创建一个单击事件
        var event = new MouseEvent('click')

        // 将a的download属性设置为我们想要下载的图片名称，若name不存在则使用‘下载图片名称’作为默认名称
        //下载图片的名称
        a.download = name || '下载图片名称'
        // 将生成的URL设置为a.href属性
        a.href = url

        // 触发a的单击事件
        a.dispatchEvent(event)
      }

      image.src = document.getElementById('picurl').src
    },
  },
  setup() {
    const form = reactive({
      medicine: [],
      addRankData: [],
      inquire: '', //搜索

      card: '', //门诊号
      caseName: '', //姓名
      caseGender: '', //性别
      caseAge: '', //年龄
      caseDepartments: '', //科室
      caseIdNmber: '', //住址/电话
      caseDate: '', //日期
      caseDiagnose: '', //诊断
      caseAllergy: '无', //过敏史
    })
    axios.defaults.baseURL = 'http://localhost:8888/'
    axios
      .get('/medicine/all')
      .then((res) => {
        form.medicine = res.data
      })
      .catch((error) => {
        console.log(error)
      })
    //点击药品
    function yao(medName, medUsageAndDosage) {
      form.addRankData.push([medName, medUsageAndDosage])
    }
    //查询
    function inquire() {
      console.log('卡号为：' + form.card)
      axios.get('/caseHistory/idShow?caseCard=' + form.card).then((res) => {
        console.log(res)
        form.caseName = res.data[0].caseName
        form.caseGender = res.data[0].caseGender
        form.caseAge = res.data[0].caseAge
        form.caseDepartments = res.data[0].caseDepartments
        form.caseDate = res.data[0].caseDate
        form.caseDiagnose = res.data[0].caseDiagnose
      })
    }
    //刷新
    function refresh() {
      axios
        .get('/medicine/all')
        .then((res) => {
          form.medicine = res.data
        })
        .catch((error) => {
          console.log(error)
        })
    }
    //搜索药物
    function inquireYao() {
      if (form.inquire != '') {
        axios
          .get('/medicine/inquire?name=' + form.inquire)
          .then((res) => {
            form.medicine = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      } else {
        refresh()
      }
    }

    return { form, inquire, yao, inquireYao }
  },
}
</script>

<style scoped>
.dd {
  width: 100%;
  height: 100%;
  background: #fff;
  /* padding-left: 20px; */
  box-sizing: border-box;
}

.mainbox {
  width: 100%;
  margin: 0 auto;
}

.el-scrollbar {
  overflow: hidden;
  position: relative;
  height: 400px;
}
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  margin: 5px;
  text-align: center;
  font-size: 12px;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}

.xuanYao {
  margin-bottom: 20px;
}

.btn {
  margin: 10px;
}

.prescription {
  width: 500px;
  /* height: 640px; */
  border: 2px solid #000;
  /* margin: 0 auto; */
}
.top {
  width: 100%;
  height: 60px;
  text-align: center;
  margin-top: 30px;
}
.top span {
  font-size: 20px;
}

.list {
  width: 95%;
  /* height: 150px; */
  margin: 0 auto;
}

.borderBttom {
  width: 100%;
  height: 100%;
  border-bottom: 1px solid #000;
  text-align: center;
  color: blue;
  font-size: 14px;
}

.title {
  font-weight: 700;
  font-size: 14px;
}
.row_top {
  padding: 5px 0;
}

.main {
  width: 95%;
  height: 370px;
  margin: 0 auto;
  border-bottom: 1px solid #000;
  overflow: hidden;
}

.main_RP {
  display: block;
}

.main_RP img {
  width: 30px;
  height: 30px;
}
.main_yao {
  text-align: center;
}
.bottom {
  width: 95%;
  height: 70px;
  margin: 0 auto;
  margin-top: 10px;
}
</style>