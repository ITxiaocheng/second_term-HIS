<template>
  <div class="addreg">
    <!-- <div class="daohangtitle">增加挂号信息</div> -->
    <div class="mainbox">
      <el-card shadow="never">
        <template #header>
          <div class="card-header">
            <el-icon style="vertical-align: middle"><Avatar /></el-icon>
            <span style="vertical-align: middle">挂号</span>
            <el-breadcrumb style="float: right">
              <el-breadcrumb-item :to="{ path: '/home' }"
                >预约挂号</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/aa' }"
                >普通挂号</el-breadcrumb-item
              >
              <el-breadcrumb-item :to="{ path: '/addReg' }"
                >添加挂号信息</el-breadcrumb-item
              >
            </el-breadcrumb>
          </div>
        </template>
        <el-form
          :model="form"
          label-width="120px"
          ref="validateForm"
          :rules="rules"
        >
          <el-form-item label="姓名：" prop="regName">
            <el-input v-model="form.regName" style="width: 200px" />
          </el-form-item>
          <el-form-item label="性别：" prop="regGreen">
            <el-radio-group v-model="form.regGender">
              <el-radio label="男">男</el-radio>
              <el-radio label="女">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="年龄：" prop="regAge" style="width: 300px">
            <el-input v-model="form.regAge" />
          </el-form-item>
          <el-form-item label="请选择科室：" prop="regChange">
            <el-cascader
              :options="options"
              ref="cascader"
              clearable
              @change="handleChange"
            />
          </el-form-item>
          <el-form-item label="日期：">
            <el-input
              type="date"
              v-model="form.regDate"
              placeholder="格式：2022-08-01"
              style="width: 200px"
              maxlength="10"
            />
          </el-form-item>
          <el-form-item label="身份证号：" prop="regIdNumber">
            <el-input v-model="form.regIdNumber" style="width: 200px" />
          </el-form-item>
          <el-form-item label="费用：">
            {{ form.regSite }}
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">Create</el-button>
            <el-button>Cancel</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ElNotification } from 'element-plus'
export default {
  name: 'AddReg',
  data() {
    return {}
  },
  methods: {
    handleChange(value) {
      this.form.regDepartments =
        this.$refs['cascader'].getCheckedNodes()[0].pathLabels[0] +
        '-' +
        this.$refs['cascader'].getCheckedNodes()[0].pathLabels[1]
      this.form.regDoctor =
        this.$refs['cascader'].getCheckedNodes()[0].pathLabels[2]
    },
  },
  setup() {
    const $router = useRouter()
    //ref属性：获取单个DOM或者组件
    const validateForm = ref()
    const form = reactive({
      regName: '',
      regGender: '男',
      regAge: '',
      regDate: '',
      regIdNumber: '',
      regSite: '18元',
      regDepartments: '',
      regDoctor: '',
    })

    //表单验证
    const rules = reactive({
      regName: [
        { required: true, message: 'Please input 姓名', trigger: 'blur' },
      ],
      regGender: [
        { required: true, message: 'Please input 性别', trigger: 'blur' },
      ],
      regAge: [
        { required: true, message: 'Please input 年龄', trigger: 'blur' },
      ],
      regChange: [
        { required: true, message: 'Please input 科室', trigger: 'blur' },
      ],
      regDate: [
        { required: true, message: 'Please input 日期', trigger: 'blur' },
      ],
      regIdNumber: [
        { required: true, message: 'Please input 身份证号', trigger: 'blur' },
        { min: 18, max: 18, message: '身份证号长度为18位', trigger: 'blur' },
      ],
    })

    //提交
    const onSubmit = () => {
      if (form.regName == '' || form.regAge == '') {
        return
      }
      console.log('submit!')
      const parms = {
        regName: form.regName,
        regGender: form.regGender,
        regAge: form.regAge,
        regDate: form.regDate,
        regIdNumber: form.regIdNumber,
        regSite: form.regSite,
        regDepartments: form.regDepartments,
        regDoctor: form.regDoctor,
      }
      axios.defaults.baseURL = 'http://localhost:8888/'
      axios.post('/HisReg/insert', parms).then((res) => {
        ElNotification.success({
          title: res.data.msg,
          message: '数据插入成功！',
          offset: 100,
        })
        $router.replace('/aa')
      })
    }

    //选择科室数据
    const options = [
      {
        value: 'guide',
        label: '普通内科',
        children: [
          {
            value: 'disciplines',
            label: '全科医学科一门诊',
            children: [
              {
                value: 'consistency',
                label: '华佗',
              },
            ],
          },
          {
            value: 'navigation',
            label: '内科门诊',
            children: [
              {
                value: 'side nav',
                label: '扁鹊',
              },
              {
                value: 'top nav',
                label: '李时珍',
              },
              {
                value: 'top nav',
                label: 'obtk',
              },
              {
                value: 'top nav',
                label: '欧柏泰克',
              },
              {
                value: 'top nav',
                label: '怡成',
              },
            ],
          },
        ],
      },
      {
        value: 'component',
        label: '普通外科',
        children: [
          {
            value: 'basic',
            label: '普通外科胃肠一病区',
            children: [
              {
                value: 'layout',
                label: '医生1',
              },
              {
                value: 'color',
                label: '医生2',
              },
              {
                value: 'typography',
                label: '医生3',
              },
              {
                value: 'icon',
                label: '医生4',
              },
              {
                value: 'button',
                label: '医生5',
              },
            ],
          },
          {
            value: 'form',
            label: '普通外科胃肠二病区',
            children: [
              {
                value: 'radio',
                label: '医生1',
              },
              {
                value: 'checkbox',
                label: '医生2',
              },
              {
                value: 'input',
                label: '医生3',
              },
            ],
          },
          {
            value: 'data',
            label: '普通外科微创外科门诊',
            children: [
              {
                value: 'table',
                label: '医生1',
              },
              {
                value: 'tag',
                label: '医生2',
              },
              {
                value: 'progress',
                label: '医生3',
              },
              {
                value: 'tree',
                label: '医生4',
              },
            ],
          },
          {
            value: 'notice',
            label: '肝脏普外科门诊',
            children: [
              {
                value: 'alert',
                label: '医生1',
              },
              {
                value: 'loading',
                label: '医生2',
              },
            ],
          },
          {
            value: 'navigation',
            label: '胆道普外科门诊',
            children: [
              {
                value: 'menu',
                label: '医生1',
              },
              {
                value: 'tabs',
                label: '医生2',
              },
              {
                value: 'breadcrumb',
                label: '医生3',
              },
              {
                value: 'dropdown',
                label: '医生4',
              },
              {
                value: 'steps',
                label: '医生5',
              },
            ],
          },
        ],
      },
      {
        value: 'resource',
        label: '儿科',
        children: [
          {
            value: 'axure',
            label: '儿科中医门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'sketch',
            label: '儿科门诊一',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '儿科门诊二',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '儿科门诊三',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '儿科门诊四',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '儿科门诊五',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '儿科门诊六',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '儿科门诊七',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '高危儿门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '儿科急诊',
            children: [{ value: '01', label: '医生1' }],
          },
        ],
      },
      {
        value: 'resource',
        label: '妇科',
        children: [
          {
            value: 'axure',
            label: '生殖中心门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'sketch',
            label: '妇科门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '妇科特需一病区门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '妇科特需二病区门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '妇一病区门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '妇二病区门诊',
            children: [{ value: '01', label: '医生1' }],
          },
          {
            value: 'docs',
            label: '妇科三区门诊',
            children: [{ value: '01', label: '医生1' }],
          },
        ],
      },
    ]

    return {
      form,
      onSubmit,
      options,
      rules,
      validateForm,
    }
  },
}
</script>

<style>
.addreg {
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

.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.demo-date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.demo-date-picker .block:last-child {
  border-right: none;
}
.demo-date-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
</style>