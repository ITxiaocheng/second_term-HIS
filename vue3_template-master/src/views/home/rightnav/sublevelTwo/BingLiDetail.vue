<template>
  <div class="bingLiDetail">
    <div class="daohangtitle">查看病历页面</div>
    <div class="mainbox">
      <el-card shadow="never">
        <template #header>
          <div class="card-header" style="margin-bottom: 5px">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">电子病历</span>
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/cc' }"
                >电子病历</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/cc' }"
                >病历列表</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/bingLiDetail' }"
                >详情病历</el-breadcrumb-item
              >
            </el-breadcrumb>
          </div>
        </template>
        <!-- 截图区域 -->
        <div class="caseHistoryTable" ref="content">
          <div class="nei">
            <!-- 电子病历标签 -->
            <div class="chTitle">
              <h3>湖南省第二人民医院</h3>
              <h3>门诊病历</h3>
            </div>
            <!-- 电子病历详细 -->
            <div class="chMain">
              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="就诊卡号"
                  label-align="right"
                  align="left"
                >
                  {{ scope.caseCard }}
                </el-descriptions-item>
                <el-descriptions-item
                  label="就诊日期"
                  label-align="right"
                  align="left"
                >
                  {{ scope.caseDate }}
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="4" border>
                <el-descriptions-item
                  label="姓名"
                  label-align="right"
                  align="left"
                >
                  {{ scope.caseName }}
                </el-descriptions-item>
                <el-descriptions-item
                  label="性别"
                  label-align="right"
                  align="left"
                >
                  {{ scope.caseGender }}
                </el-descriptions-item>
                <el-descriptions-item
                  label="年龄"
                  label-align="right"
                  align="left"
                >
                  {{ scope.caseAge }}
                </el-descriptions-item>
                <el-descriptions-item
                  label="费别"
                  label-align="right"
                  align="left"
                >
                  {{ scope.caseMoney }}
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="科室"
                  label-align="right"
                  align="left"
                  width="140px"
                >
                  {{ scope.caseDepartments }}
                </el-descriptions-item>
                <el-descriptions-item
                  label="过敏史"
                  label-align="right"
                  align="left"
                >
                  无
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="现病史"
                  label-align="right"
                  align="left"
                  width="140px"
                >
                  {{ scope.casePHI }}
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="既往史"
                  label-align="right"
                  align="left"
                  width="140px"
                >
                  {{ scope.casePreviousHistory }}
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="体格检查"
                  label-align="right"
                  align="left"
                  width="140px"
                >
                  {{ scope.caseHealthCheckup }}
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="辅助检查"
                  label-align="right"
                  align="left"
                  width="140px"
                >
                  {{ scope.caseAuxiliaryExamination }}
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="诊断"
                  label-align="right"
                  align="left"
                  width="140px"
                >
                  {{ scope.caseDiagnose }}
                </el-descriptions-item>
              </el-descriptions>

              <el-descriptions :column="2" border>
                <el-descriptions-item
                  label="处理意见"
                  label-align="right"
                  align="left"
                  width="140px"
                >
                  {{ scope.caseOpinion }}
                </el-descriptions-item>
              </el-descriptions>
              <div style="padding: 20px 0; font-size: 14px">
                <span style="margin-left: 20px">
                  书写日期：{{ scope.caseWriteDate }}
                </span>
                <span style="margin-left: 100px"
                  >医生：{{ scope.caseDoctor }}</span
                >
              </div>
            </div>
          </div>
        </div>
        <!-- 点击调用方法获取截图 -->
        <button class="btn" @click="getPrintScreen">获取截图</button>
        <div class="img-box">
          <h2>截图结果:</h2>
          <!-- 通过img标签把获取到的截图呈现出来 -->
          <img :src="imgUrl" alt="" id="picurl" />
        </div>
        <div class="btn" @click="downloadIamge('下载')">保存图片</div>
      </el-card>
    </div>
  </div>
</template>

<script>
// 引入 html2canvas
import html2canvas from 'html2canvas'
import axios from 'axios'
import { reactive } from 'vue'
export default {
  name: 'bingLiDetail',
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
    const scope = reactive({
      caseCard: '',
      caseDate: '',
      caseName: '',
      caseGender: '',
      caseAge: '',
      caseDepartments: '',
      casePHI: '',
      casePreviousHistory: '',
      caseHealthCheckup: '',
      caseAuxiliaryExamination: '',
      caseDiagnose: '',
      caseOpinion: '',
      caseWriteDate: '',
      caseDoctor: '',
      caseMoney: '',
    })
    axios.defaults.baseURL = 'http://localhost:8888/'
    axios
      .get('/caseHistory/showOne?caseId=' + sessionStorage.getItem('caseId'))
      .then((res) => {
        scope.caseCard = res.data[0].caseCard
        scope.caseDate = res.data[0].caseDate
        scope.caseName = res.data[0].caseName
        scope.caseGender = res.data[0].caseGender
        scope.caseAge = res.data[0].caseAge
        scope.caseDepartments = res.data[0].caseDepartments
        scope.casePHI = res.data[0].casePHI
        scope.casePreviousHistory = res.data[0].casePreviousHistory
        scope.caseHealthCheckup = res.data[0].caseHealthCheckup
        scope.caseAuxiliaryExamination = res.data[0].caseAuxiliaryExamination
        scope.caseDiagnose = res.data[0].caseDiagnose
        scope.caseOpinion = res.data[0].caseOpinion
        scope.caseWriteDate = res.data[0].caseWriteDate
        scope.caseDoctor = res.data[0].caseDoctor
        scope.caseMoney = res.data[0].caseMoney
      })
      .catch((error) => {
        console.log(error)
      })
    return {
      scope,
    }
  },
}
</script>

<style scoped>
.bingLiDetail {
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

.caseHistoryTable {
  width: 55%;
  margin: 10px auto;
  border: 2px solid #eee;
}

.nei {
  width: 95%;
  margin: 0 auto;
}

.btn {
  display: block;
  width: 300px;
  height: 50px;
  background: #79c76f;
  border-radius: 2vw;
  border: none;
  font-size: 20px;
  font-family: Source Han Sans CN;
  font-weight: 400;
  color: #ffffff;
  line-height: 50px;
  letter-spacing: 4px;
  text-align: center;
  margin: 30px 0 20px 40px;
}

.chTitle {
  width: 100%;
  margin-top: 50px;
}

.chTitle h3 {
  text-align: center;
  /* line-height: 50px; */
}

.chMain {
  margin-top: 30px;
}

.el-descriptions__label.el-descriptions__cell.is-bordered-label {
  font-weight: 700;
  color: var(--el-text-color-regular);
  background: transparent !important;
}
</style>