<template>
  <div class="addDoctor">
    <!-- <div class="daohangtitle">增加就诊信息</div> -->
    <div class="mainbox">
      <el-card shadow="never">
        <template #header>
          <div class="card-header">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">添加就诊信息</span>
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/bb' }"
                >就诊</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/bb' }"
                >开始就诊</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/addDoctor' }"
                >添加就诊信息</el-breadcrumb-item
              >
            </el-breadcrumb>
          </div>
        </template>
        <el-descriptions title="Customized style list" :column="3" border>
          <el-descriptions-item
            label="就诊卡号"
            label-align="right"
            align="center"
            label-class-name="my-label"
            class-name="my-content"
            width="150px"
          >
            <el-tag size="small"> {{ scope.regCard }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="姓名" label-align="right" align="center">
            <div>
              {{ scope.regName }}
            </div>
          </el-descriptions-item>
          <el-descriptions-item
            label="候诊日期"
            label-align="right"
            align="center"
          >
            {{ scope.regDate }}
          </el-descriptions-item>
          <el-descriptions-item label="性别" label-align="right" align="center">
            {{ scope.regGender }}
          </el-descriptions-item>
          <el-descriptions-item label="年龄" label-align="right" align="center">
            {{ scope.regAge }}
          </el-descriptions-item>
          <el-descriptions-item
            label="身份证号"
            label-align="right"
            align="center"
          >
            {{ scope.regIdNumber }}
          </el-descriptions-item>
          <el-descriptions-item label="费别" label-align="right" align="center">
            {{ scope.regMoney }}
          </el-descriptions-item>
          <el-descriptions-item label="科室" label-align="right" align="center">
            {{ scope.regDepartments }}
          </el-descriptions-item>
          <el-descriptions-item
            label="过敏史"
            label-align="right"
            align="center"
          >
            无
          </el-descriptions-item>
        </el-descriptions>
        <el-form :model="scope" ref="validateForm">
          <el-descriptions border>
            <el-descriptions-item
              label="主诉"
              label-align="right"
              align="left"
              width="150px"
            >
              <el-input v-model="scope.regComplaint" />
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions border>
            <el-descriptions-item
              label="现病史"
              label-align="right"
              align="left"
              width="150px"
            >
              <el-input v-model="scope.regPHI" type="textarea" />
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions border>
            <el-descriptions-item
              label="既往史"
              label-align="right"
              align="left"
              width="150px"
            >
              {{ scope.regPreviousHistory }}
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions border>
            <el-descriptions-item
              label="体格检查"
              label-align="right"
              align="left"
              width="150px"
            >
              <el-input v-model="scope.regHealthCheckup" type="textarea" />
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions border>
            <el-descriptions-item
              label="辅助检查"
              label-align="right"
              align="left"
              width="150px"
            >
              <el-input
                v-model="scope.regAuxiliaryExamination"
                type="textarea"
              />
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions border>
            <el-descriptions-item
              label="诊断"
              label-align="right"
              align="left"
              width="150px"
            >
              <el-input v-model="scope.regDiagnose" />
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions border>
            <el-descriptions-item
              label="处理意见"
              label-align="right"
              align="left"
              width="150px"
            >
              <el-input v-model="scope.regOpinion" />
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions border>
            <el-descriptions-item
              label="操作"
              label-align="right"
              align="left"
              width="150px"
            >
              <el-button type="primary" @click="submit">保存</el-button>
            </el-descriptions-item>
          </el-descriptions>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElNotification, ElLoading } from 'element-plus'
import axios from 'axios'
export default {
  name: 'addDoctor',
  setup() {
    const validateForm = ref()
    const $router = useRouter()
    const scope = reactive({
      tableData: [],
      regCard: '', //卡号
      regName: '', //姓名
      regDate: '', //候诊日期
      regGender: '', //性别
      regAge: '', //年龄
      regDepartments: '', //科室
      regDoctor: '', //预约医生
      regIdNumber: '', //身份证号
      regMoney: '', //费别

      regComplaint: '', //主诉
      regPHI: '', //现病史
      regPreviousHistory: '无', //既往史
      regHealthCheckup: '', //体格检查
      regAuxiliaryExamination: '无', //辅助检查
      regDiagnose: '', //诊断
      regOpinion: '', //处理意见
    })
    axios
      .post('/HisReg/card?regCard=' + sessionStorage.getItem('regCard'))
      .then((res) => {
        scope.tableData = res.data.list
        scope.regCard = res.data[0].regCard
        scope.regName = res.data[0].regName
        scope.regDate = res.data[0].regDate
        scope.regGender = res.data[0].regGender
        scope.regAge = res.data[0].regAge
        scope.regDepartments = res.data[0].regDepartments
        scope.regDoctor = res.data[0].regDoctor
        scope.regIdNumber = res.data[0].regIdNumber
        scope.regMoney = res.data[0].regSite
      })
      .catch((error) => {
        console.log(error)
      })

    function submit() {
      const parmt = {
        caseCard: scope.regCard,
        caseName: scope.regName,
        caseGender: scope.regGender,
        caseAge: scope.regAge,
        caseDate: scope.regDate,
        caseDepartments: scope.regDepartments,
        caseDoctor: scope.regDoctor,
        caseIdNumber: scope.regIdNumber,
        caseMoney: scope.regMoney,

        caseComplaint: scope.regComplaint,
        casePHI: scope.regPHI,
        casePreviousHistory: scope.regPreviousHistory,
        caseHealthCheckup: scope.regHealthCheckup,
        caseAuxiliaryExamination: scope.regAuxiliaryExamination,
        caseDiagnose: scope.regDiagnose,
        caseOpinion: scope.regOpinion,
      }
      axios
        .post('/caseHistory/insert', parmt)
        .then((res) => {
          if (res.data.code == 1) {
            ElNotification.success({
              title: '成功！',
              message: res.data.msg,
              offset: 100,
            })
            $router.replace('/Bb')
          } else {
            console.log(res.data.msg)
          }
        })
        .catch((error) => {
          console.log(error)
        })
    }
    return {
      scope,
      validateForm,
      submit,
    }
  },
}
</script>

<style scoped>
.addDoctor {
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
</style>