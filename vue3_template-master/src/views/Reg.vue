<template>
  <div class="back">
    <!-- 头部导航栏 -->
    <div class="layout">
      <el-row class="">
        <el-col :span="4">
          <img
            src="./images/nkyylogo.png"
            style="width: 250px"
            @click="imgHome"
          />
        </el-col>
        <el-col :span="12">
          <el-row
            v-for="(options, index) in iconItem"
            :key="index"
            style="float: left"
          >
            <el-col>
              <el-icon
                size="35px"
                color="white"
                style="float: left; margin-left: 25px; margin-top: 15px"
              >
                <component :is="options.icon"></component>
              </el-icon>
              <div
                style="
                  float: left;
                  line-height: 65px;
                  margin-left: 15px;
                  color: white;
                  font-size: 18px;
                  font-weight: bold;
                "
              >
                <span @click="lidi(index)"> {{ options.title }}</span>
              </div>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="5" style="margin-top: 15px">
          <el-icon
            style="float: left; margin-right: 10px"
            size="35px"
            color="white"
            ><Phone
          /></el-icon>
          <div style="float: left; color: white">
            <h5 style="font-weight: 400">总值班电话：0731-85232209</h5>
            <h5 style="font-weight: 400">24小时急救：0731-85232650</h5>
          </div>
        </el-col>
        <el-col :span="3" style="margin-top: 15px">
          <!-- <el-icon
            style="float: left; margin-right: 10px"
            size="35px"
            color="white"
            ><UserFilled
          /></el-icon> -->
          <div style="float: left; color: white" class="differentLogin">
            <span style="line-height: 35px; cursor: pointer">用户登录</span>
            <span
              style="line-height: 35px; margin-left: 10px; cursor: pointer"
              @click="denglu"
              >管理员登录</span
            >
          </div>
        </el-col>
      </el-row>
    </div>
    <!-- 清除浮动 -->
    <div style="width: 100%; height: 100px"></div>
    <!-- 就医流程等 -->
    <div class="min-box">
      <div class="ghlc-box">
        <a class="ghlc-min-01" href="javascript:;">
          <div>挂号须知</div>
        </a>
        <a class="ghlc-min-02" href="javascript:;">
          <div>病员注册</div>
        </a>
        <a class="ghlc-min-03" href="javascript:;">
          <div>网上挂号</div>
        </a>
        <a class="ghlc-min-04" href="javascript:;">
          <div>挂号查询</div>
        </a>
      </div>
      <div class="ghxz-box">
        <div class="ghxz-01">
          <div></div>
          <h1>{{ showx2.leftTitle }}</h1>
        </div>
        <div class="ghxz-02">
          <h1>{{ showx2.autoTitle }}</h1>
          <div class="ksdh-right-min-03">
            <!-- 选择1级菜单 -->
            <div v-for="(ks, index) in outpatient" :key="index">
              <a
                href="javascript:;"
                @click="xuanz1(ks.title, index)"
                v-show="showx.vlog"
              >
                {{ ks.title }}</a
              >
              <!-- 选择2级菜单 v-show="showx.vlog2" -->
              <div v-for="(item, inde) in ks.secondMenus" :key="inde">
                <a
                  href="javascript:;"
                  v-show="(index == showx.index) & showx.vlog2"
                  @click="xuanz2(item.title, inde)"
                >
                  {{ item.title }}
                </a>
                <!-- 选择3级菜单 - 专家列表 showx2.vlog3 -->
                <div
                  v-for="(i, ind) in item.doctor"
                  :key="ind"
                  v-show="showx2.vlog3"
                >
                  <div v-show="inde == showx2.index">
                    <table border="1" style="border-style: none">
                      <tr class="mail-title">
                        <th>{{ i.title }}</th>
                        <td style="width: 140px">
                          {{ i.visitTime }}
                        </td>
                        <td>{{ i.docTotalNumber }}</td>
                        <td>
                          <!-- <div v-for="(t, ins) in scope.drn" :key="ins">
                            <span>{{ t }}</span>
                          </div> -->
                          <span
                            style="cursor: pointer; color: #2865be"
                            @click="make(i.title, i.visitTime)"
                            >预约</span
                          >
                        </td>
                        <td>{{ i.docIntroduction }}</td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 预约挂号 -->
          <div class="make" v-show="showx3.make">
            <div style="width: 40%; margin: 0 auto">
              <el-form :model="form" label-width="120px" :rules="rules">
                <el-form-item label="真实姓名" prop="regName">
                  <el-input v-model="form.regName" />
                </el-form-item>
                <!-- <el-form-item label="性别" prop="regGender">
                  <el-input v-model="form.regGender" />
                </el-form-item> -->
                <el-form-item label="性别">
                  <el-radio-group v-model="form.regGender">
                    <el-radio label="男" />
                    <el-radio label="女" />
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="年龄" prop="regAge">
                  <el-input v-model="form.regAge" />
                </el-form-item>
                <el-form-item label="身份证号" prop="regIdNumber">
                  <el-input v-model="form.regIdNumber" />
                </el-form-item>
                <el-form-item>
                  <el-button type="success" @click="registration"
                    >挂号</el-button
                  >
                  <el-button type="info">注册</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 级联菜单   npm install element-china-area-data -S  -->
    <!-- <div style="width: 1000px; height: 500px; background: pink">
      {{ options }}
      <el-cascader
        size="large"
        :options="options"
        v-model="selectedOptions"
        @change="handleChange"
      >
      </el-cascader>
    </div> -->
    <!-- 底部 -->
    <div class="nkyy_bottom">
      <div class="nkyy_flink">
        <div class="nkyy_flinki">
          <select name="product_name" class="select_input">
            <option value="#" selected="selected">=投诉建议=</option>
            <option value="#">书记信箱</option>
            <option value="#">院长信箱</option>
            <option value="#">我要表扬</option>
            <option value="#">我要投诉</option>
            <option value="#">我要建议</option>
          </select>
        </div>
        <div class="nkyy_flinki">
          <select name="product_name" class="select_input">
            <option value="#" selected="selected">=上级部门/单位=</option>
            <option value="#">湖南省卫生健康委员会</option>
            <option value="#">长沙市卫生健康委员会</option>
            <option value="#">中华人民共和国国家卫生健康委员会</option>
          </select>
        </div>
        <div class="nkyy_flinki">
          <select name="product_name" class="select_input">
            <option value="#" selected="selected">=兄弟医院=</option>
            <option value="#">长沙市中心医院</option>
            <option value="#">长沙市口腔医院</option>
            <option value="#">长沙市第八医院</option>
            <option value="#">长沙市第一医院</option>
            <option value="#">湖南省人民医院</option>
            <option value="#">中南大学湘雅三医院</option>
          </select>
        </div>
        <div class="nkyy_flinki">
          <select name="product_name" class="select_input">
            <option value="#" selected="selected">=学术站点/文献检索=</option>
            <option value="#">万方数据知识服务平台</option>
            <option value="#">中国医院数字图书馆</option>
          </select>
        </div>
      </div>
      <div class="nkyy_bottomtxt">
        医院地址：长沙市芙蓉中路三段（涂家冲）427号
        <br />
        总值班电话：0731-85232209
        <br />
        24小时急救：0731-85232650
      </div>
      <div class="nkyy_bottomicon">
        <img src="./images/bottom_cul.png" alt="" />
      </div>
      <div class="nkyy_footer">
        <div class="nkyy_footerc">
          <span>
            © 2021&nbsp;&nbsp;
            <a href="javascript:;">湖南省脑科医院（湖南省第二人民医院）</a>
            版权所有
            <br />
            <a href="javascript:;">湘ICP备11014917号</a>
            &nbsp;&nbsp;
            <a href="javascript:;">湘卫网申字（2014）第046号</a>
            &nbsp;&nbsp;技术支持
            <a href="javascript:;">铭巨网络</a>
          </span>
          <img src="./images/bottom_gov.png" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
import axios from 'axios'
import { ElNotification } from 'element-plus'
// 测试
import { regionData } from 'element-china-area-data'
export default {
  name: 'Reg',
  data() {
    return {
      options: regionData,
      selectedOptions: [],
    }
  },

  setup() {
    //获取数据
    axios.defaults.baseURL = 'http://localhost:8888/'
    axios
      .get('/doctor/showAll')
      .then((res) => {
        scope.tableData = res.data
        scope.drn[0] = res.data[0].docRemainingNumber
        scope.drn[1] = res.data[1].docRemainingNumber
      })
      .catch((error) => {
        console.log(error)
      })
    const showx = reactive({
      vlog: true,
      vlog2: false,
      xz: '', //选中的值 1级
      index: 0, //选中的序号
    })
    const showx2 = reactive({
      vlog3: false,
      xz: '', //选中的值 1级
      index: 0, //选中的序号
      leftTitle: '网上挂号', //左上角标题
      autoTitle: '科室', //居中标题
    })
    const showx3 = reactive({
      vlog3: true,

      make: false,
    })
    const scope = reactive({
      tableData: [],

      drn: [],
    })
    const form = reactive({
      regName: '',
      regGender: '',
      regAge: '',
      regDate: '',
      regIdNumber: '',
      regSite: '18元',
      regDepartments: '',
      regDoctor: '',
    })
    const $router = useRouter()
    const iconItem = [
      { icon: 'Tickets', title: '预约挂号' },
      { icon: 'OfficeBuilding', title: '医院概括' },
      { icon: 'User', title: '党建人文' },
      { icon: 'DocumentCopy', title: '新闻宣教' },
    ]
    const outpatient = [
      {
        title: '中医门诊 ',
        secondMenus: [{ title: '中医外科门诊' }, { title: '中医门诊' }],
      },
      {
        title: '肾病内科门诊 ',
        secondMenus: [{ title: '血液净化门诊' }, { title: '肾病内科门诊' }],
      },
      {
        title: '急诊内科 ',
        secondMenus: [{ title: '急诊内科诊室一' }, { title: '急诊内科诊室二' }],
      },
      {
        title: '神经内科 ',
      },
      {
        title: '儿童外科 ',
      },
      {
        title: '儿科 ',
      },
      {
        title: '妇科 ',
      },
      {
        title: '产科 ',
      },
      {
        title: '生殖中心病区 ',
      },
      {
        title: '男科门诊 ',
      },
      {
        title: '临床营养科门诊 ',
      },
      {
        title: '感染肝病门诊 ',
      },
      {
        title: '疼痛科门诊 ',
      },
      {
        title: '放疗科门诊 ',
      },
      {
        title: '血管外科 ',
      },
      {
        title: '整形外科门诊 ',
      },
      {
        title: '泌尿病房门诊 ',
      },
      {
        title: '心脏大血管外科 ',
      },
      {
        title: '眼科门诊 ',
      },
      {
        title: '心理咨询门诊 ',
      },
      {
        title: '内分泌科门诊 ',
      },
      {
        title: '中医针灸推拿 ',
      },
      {
        title: '普通内科 ',
        secondMenus: [
          {
            title: '全科医学科一门诊',
            doctor: [{ title: '医生1' }, { title: '医生2' }],
          },
          {
            title: '内科门诊',
            doctor: [
              {
                title: '扁鹊',
                visitTime: '2022-08-11',
                docTotalNumber: 100,
                docRemainingNumber: scope.drn1,
                docIntroduction:
                  '呼吸系统常见病多发病（如：肺部感染、占位等疾病）诊治、鉴别及急危重病人的救治、肿瘤化疗，熟练掌握气管镜、呼吸机等的应用。',
              },
              {
                title: '怡成',
                visitTime: '2022-08-11',
                docTotalNumber: 100,
                docRemainingNumber: scope.drn2,
                docIntroduction:
                  '从事内科临床30多年，有丰富的内科疾病诊断和治疗经验。特别对心血管疾病、呼吸道疾病以及消化系统、内分泌系统、神经系统、血液等系统疾病有一定的诊疗技巧。对复杂、疑难的内科疾病有独特的诊治技能。',
              },
            ],
          },
        ],
      },
      {
        title: '消化内科 ',
      },
      {
        title: '心血管内科 ',
      },
      {
        title: '呼吸科 ',
      },
      {
        title: '血液门诊 ',
      },
      {
        title: '普通外科 ',
      },
      {
        title: '耳鼻喉门诊 ',
      },
      {
        title: '神经外科 ',
      },
      {
        title: '骨科 ',
      },
      {
        title: '胸外科 ',
      },
      {
        title: '乳腺甲状腺门诊 ',
      },
      {
        title: '皮肤科门诊 ',
      },
      {
        title: '肛肠门诊 ',
      },
      {
        title: '肿瘤科 ',
      },
    ]
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
      regIdNumber: [
        { required: true, message: 'Please input 身份证号', trigger: 'blur' },
        { min: 18, max: 18, message: '身份证号长度为18位', trigger: 'blur' },
      ],
    })
    //点击主页logo跳转到主页
    function imgHome() {
      $router.replace('/index')
    }
    //导航栏点击
    function lidi(index) {
      //预约挂号
      if (index == 0) {
        $router.replace('/reg')
      }
    }
    //点击跳转到login页面
    function denglu() {
      $router.replace('/login')
    }
    //选择1级菜单
    function xuanz1(name, index) {
      console.log(name + ' ' + index)
      showx.vlog2 = true
      showx.vlog = false
      showx.xz = name
      showx.index = index
    }
    //选择2级菜单
    function xuanz2(name, index) {
      console.log(name + ' ' + index)

      showx.vlog = false
      showx.vlog2 = false

      showx2.xz = name
      showx2.index = index
      showx2.leftTitle = '专家列表'
      showx2.autoTitle = ''
      showx2.vlog3 = true
    }
    //预约
    function make(name, date) {
      showx.vlog = false
      showx.vlog2 = false
      showx2.vlog3 = false

      showx3.make = true

      form.regDoctor = name
      form.regDate = date
      form.regDepartments = showx2.xz

      showx2.leftTitle = '网上挂号'
      showx2.autoTitle = ''
    }

    //挂号
    function registration() {
      if (
        form.regName == '' ||
        form.regGender == '' ||
        form.regAge == '' ||
        form.regIdNumber == ''
      ) {
        return
      }
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
      axios.post('/HisReg/insert', parms).then((res) => {
        ElNotification.success({
          title: 'message!',
          message: '挂号成功！',
          offset: 100,
        })
        console.log(form.regDoctor)
        axios
          .post('/doctor/srn?name=' + form.regDoctor)
          .then((res) => {
            console.log(res.data.msg)
          })
          .catch((error) => {
            console.log(error)
          })
        $router.replace('/index')
      })
    }
    return {
      outpatient,
      iconItem,
      imgHome,
      lidi,
      denglu,
      xuanz1,
      xuanz2,
      showx,
      showx2,
      showx3,
      scope,
      make,
      form,
      registration,
      rules,
    }
  },
}
</script>

<style scoped>
a {
  text-decoration: none;
}

.make {
  width: 100%;
}

.differentLogin span {
  font-weight: bold;
}

.differentLogin span:hover {
  color: red;
}

.mail-title th {
  width: 90px;
  background-color: #6db2ef;
  font-size: 15px;
  /* font-weight: bold; */
  color: #fff;
  padding-left: 4px;
  padding-right: 4px;
}

.mail-title td {
  font-size: 14px;
  padding-left: 4px;
  padding-right: 4px;
  text-align: center;
  color: #666;
  padding: 20px;
}

.layout {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 150px;
  background: url(./images/nav_bg.png);
  background-repeat: repeat-x;
  z-index: 99999;
}

.back {
  width: 100%;
  height: 950px;
  background: url(./images/ny_bg.jpg) no-repeat;
  background-repeat: no-repeat;
  background-position: bottom;
}

.title-box {
  width: 1200px;
  height: 156px;
  margin: auto;
  clear: both;
  overflow: hidden;
}

.title-box img {
  float: left;
  margin-top: 88px;
}

.title-box h1 {
  float: right;
  font-size: 40px;
  color: #f04c44;
  margin: 0;
  margin-top: 88px;
  margin-left: 20px;
  margin-right: 70px;
  padding: 0;
}

.title-box div {
  float: right;
  margin-top: 115px;
  width: 130px;
  height: 1px;
  background-color: #f04c44;
}

.min-box {
  width: 1200px;
  height: auto;
  margin: auto;
  clear: both;
  overflow: hidden;
  margin-top: 100px;
}

.ghlc-box {
  width: 220px;
  height: auto;
  float: left;
  position: relative;
  top: 0px;
  margin-top: 64px;
}

.ghlc-box a {
  width: 220px;
  height: 66px;
  display: block;
  text-decoration: none;
  transition: transform 0.3s;
  border-radius: 12px;
  background-size: 20px auto;
  background-position: 46px center;
  background-repeat: no-repeat;
  margin-bottom: 36px;
}

.ghlc-box a div {
  font-size: 20px;
  color: #fff;
  line-height: 66px;
  padding-left: 87px;
}

.ghlc-min-01 {
  background-image: url(./images/jylc-img-01.png);
  background-color: #04c8d9;
}

.ghlc-min-02 {
  background-image: url(./images/jylc-img-02.png);
  background-color: #ffa64b;
}

.ghlc-min-03 {
  background-image: url(./images/jylc-img-03.png);
  background-color: #ffcd37;
}

.ghlc-min-04 {
  background-image: url(./images/jylc-img-04.png);
  background-color: #fc805b;
}

.ghxz-box {
  float: right;
  width: 940px;
  height: auto;
  background-color: #fff;
  border-radius: 10px;
  margin-top: 52px;
}

.ghxz-01 {
  width: 100%;
  height: 86px;
  background-color: #fff;
  border-radius: 10px 10px 0 0;
}

.ghxz-01 div {
  margin-left: 48px;
  float: left;
  width: 8px;
  height: 24px;
  background-color: #474747;
  margin-top: 31px;
}

.ghxz-01 h1 {
  display: block;
  float: left;
  padding-left: 14px;
  font-size: 24px;
  color: #474747;
  line-height: 86px;
  margin: 0;
}

.ghxz-02 {
  width: 100%;
  height: auto;
  background-color: #fff;
  border-radius: 0 0 10px 10px;
  margin-top: 18px;
}

.ghxz-02 h1 {
  text-align: center;
  color: #4d8ef0;
  font-size: 24px;
  margin-bottom: 28px;
}

.ksdh-right-min-03 {
  width: 790px;
  height: auto;
  margin: auto;
  clear: both;
  overflow: hidden;
  padding-bottom: 30px;
}

.ksdh-right-min-03 a:hover {
  background-color: #39b6bf;
}

.ksdh-right-min-03 a {
  width: 148px;
  height: 40px;
  float: left;
  margin-left: 4px;
  margin-right: 4px;
  border: solid 1px #ccc;
  border-radius: 5px;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  color: #333;
  margin-bottom: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

/* 底部 */
.nkyy_bottom {
  width: 100%;
  margin: 0 auto;
  background: url(./images/bottom_bg.jpg) no-repeat;
  background-size: 100% auto;
  background-position: center top;
  padding-top: 56px;
}

.nkyy_flink {
  width: 1210px;
  height: 64px;
  margin: 0px auto;
  padding: 0px;
}

.nkyy_bottomicon {
  width: 80%;
  margin: 15px auto;
  text-align: center;
}

.nkyy_footer {
  width: 100%;
  height: 56px;
  padding: 30px 0px;
  margin: 0px;
  background-color: #343536;
  text-align: center;
  margin-top: 49px;
}

.nkyy_flinki {
  width: calc(25% - 20px);
  float: left;
  margin: 0 5px;
  border: rgba(255, 255, 255, 0.26) 5px solid;
  height: 44px;
}

.select_input {
  width: 100%;
  height: 44px;
  line-height: 44px;
  color: #343536;
  background: rgba(255, 255, 255, 0.74);
  font-size: 16px;
  border: #ffffff 1px solid !important;
  text-indent: 10px;
  cursor: pointer;
}

.nkyy_bottomtxt {
  width: 80%;
  margin: 15px auto;
  text-align: center;
  font-size: 16px;
  line-height: 26px;
  color: #ffffff;
}

.nkyy_footerc {
  width: 1200px;
  line-height: 20px;
  font-size: 14px;
  color: #e0e0e6;
  text-align: left;
  margin: 0 auto;
}

.nkyy_footerc span {
  width: 80%;
  height: 40px;
  float: left;
  text-align: left;
  display: block;
  margin: 8px 0px;
  line-height: 20px;
}

.nkyy_footerc span a {
  color: #fff;
  text-decoration: none;
}

.nkyy_footerc img {
  margin: 0px;
  float: right;
}
</style>