<template>
  <div class="back">
    <!-- 主页轮播图 -->
    <el-carousel
      :interval="5000"
      arrow="always"
      height="720px"
      class="carousel"
    >
      <el-carousel-item v-for="item in 6" :key="item"> </el-carousel-item>
    </el-carousel>

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

    <div class="margin">
      <!-- 功能区域 -->
      <div class="nkyy_jyzn">
        <div
          class="contentheight"
          :class="{ boxtranslateY: true, testenter: testShow }"
          ref="testref"
        >
          <el-row
            :gutter="20"
            class="contentheight"
            :class="{ boxtranslateY: true, testenter: testShow }"
            ref="testref"
          >
            <el-col
              :span="6"
              :class="{ boxleftTo20: true, boxtestenterX: testShow }"
              class="nkyy_jyznitem"
              style="background: #328f8a"
            >
              <img src="./images/jyzn04.png" alt="" style="width: 50px" /><br />
              <h3>专家介绍</h3>
              <span>Introduction of experts</span>
            </el-col>
            <el-col
              :span="6"
              :class="{ boxleftTo20: true, boxtestenterX: testShow }"
              class="nkyy_jyznitem"
              style="background: #f5d58d"
            >
              <img src="./images/jyzn02.png" alt="" style="width: 50px" /><br />
              <h3>坐诊排版</h3>
              <span>Outpatient Scheduling</span>
            </el-col>

            <el-col
              :span="6"
              :class="{ boxtranslateXright: true, boxtestenterX: testShow2 }"
              class="nkyy_jyznitem"
              style="background: #ffffff"
            >
              <div
                class="contentheight"
                :class="{ boxtranslateY: true, testenter: testShow2 }"
                ref="test2ref"
              >
                <img
                  src="./images/jyzn01.png"
                  alt=""
                  style="width: 50px"
                /><br />
                <h3 style="color: #66727a">预约挂号</h3>
                <span style="color: #66727a">Make an Appointment</span>
              </div>
            </el-col>
            <el-col
              :span="6"
              :class="{ boxtranslateXright: true, boxtestenterX: testShow2 }"
              class="nkyy_jyznitem"
              style="background: #66727a"
            >
              <div
                class="contentheight"
                :class="{ boxtranslateY: true, testenter: testShow2 }"
                ref="test2ref"
              >
                <img
                  src="./images/jyzn03.png"
                  alt=""
                  style="width: 50px"
                /><br />
                <h3>交通导航</h3>
                <span>Traffic navigation</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>

      <!-- 医院新闻模块 -->
      <div class="nkyy_xwzx">
        <!-- 医院新闻top模块 -->
        <div class="nkyy_xwzxtit">
          <div
            style="float: left"
            :class="{ boxleftTo20: true, boxtestenterX: testShow }"
          >
            <h3>医院<span style="font-weight: 400">新闻</span></h3>
          </div>
          <div
            style="float: right"
            class="nkyy_xwzxtit3"
            :class="{ boxtranslateXright: true, boxtestenterX: testShow2 }"
          >
            <a href="#">查看更多</a>
          </div>
        </div>
        <!-- 医院新闻main模块 -->
        <div class="nkyy_xwzxcon">
          <el-row>
            <div
              style="width: 49%"
              :class="{ boxleftTo20: true, boxtestenterX: testShow }"
            >
              <el-carousel
                indicator-position="outside"
                class="nkyy_xwzxcon_carousel"
              >
                <el-carousel-item
                  v-for="item in 4"
                  :key="item"
                  style="border-radius: 10px"
                >
                </el-carousel-item>
              </el-carousel>
            </div>
            <div style="width: 2%"></div>
            <div
              :span="11.5"
              style="
                width: 49%;
                border-top: #e6e6ee 1px solid;
                border-radius: 0px;
              "
              :class="{ boxtranslateXright: true, boxtestenterX: testShow2 }"
            >
              <ul v-for="(item, index) in tableData" :key="index">
                <li class="nkyy_xwzxitem">{{ item.date }}</li>
              </ul>
            </div>
          </el-row>
        </div>
      </div>
      <!--  -->
      <div class="nkyy_ztlist">
        <!-- 特色专题top模块 -->
        <div class="nkyy_xwzxtit">
          <div
            style="float: left"
            :class="{ boxleftTo20: true, boxtestenterX: testShow }"
          >
            <h3>特色<span style="font-weight: 400">专题</span></h3>
          </div>
          <div
            style="float: right"
            class="nkyy_xwzxtit3"
            :class="{ boxtranslateXright: true, boxtestenterX: testShow2 }"
          >
            <a href="#">查看更多</a>
          </div>
        </div>
        <!--  -->
        <div class="nkyy_ztlistcc">
          <el-row style="height: 300px; margin: 0; padding: 0">
            <el-col :span="6" class="nkyy_ztlistc0">
              <img src="./images/nkyyzt01.jpg" alt="" />
            </el-col>
            <el-col :span="12" class="nkyy_ztlistc0">
              <img src="./images/nkyyzt02.jpg" alt="" />
              <div class="nkyy_ztlistcct">
                <span style="font-weight: bold">脑卒中</span>专题
              </div>
            </el-col>
            <el-col :span="6" class="nkyy_ztlistc0">
              <img src="./images/nkyyzt06.jpg" alt="" />
              <div class="nkyy_ztlistcct">
                <span style="font-weight: bold">电子</span>刊物
              </div>
            </el-col>
          </el-row>
          <el-row style="height: 300px; margin: 0; padding: 0">
            <el-col :span="6" class="nkyy_ztlistc0">
              <img src="./images/nkyyzt04.jpg" alt="" />
              <div class="nkyy_ztlistcct">
                <span style="font-weight: bold">医院</span>文化
              </div>
            </el-col>
            <el-col :span="6" class="nkyy_ztlistc0">
              <img src="./images/nkyyzt05.jpg" alt="" />
              <div class="nkyy_ztlistcct">
                <span style="font-weight: bold">网络</span>医院
              </div>
            </el-col>
            <el-col :span="12" class="nkyy_ztlistc0">
              <img src="./images/nkyyzt07.jpg" alt="" />
              <div class="nkyy_ztlistcct">
                <span style="font-weight: bold">医联体</span>建设
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>

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
import 'animate.css'
import { ArrowDown } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
export default {
  name: 'Index',
  setup() {
    const $router = useRouter()
    //导航栏点击
    function lidi(index) {
      //预约挂号
      if (index == 0) {
        $router.replace('/reg')
      }
    }
    //点击主页logo跳转到主页
    function imgHome() {
      $router.replace('/index')
    }
    //点击跳转到login页面
    function denglu() {
      $router.replace('/login')
    }
    return {
      lidi,
      imgHome,
      denglu,
    }
  },
  data() {
    return {
      iconItem: [
        { icon: 'Tickets', title: '预约挂号' },
        { icon: 'OfficeBuilding', title: '医院概括' },
        { icon: 'User', title: '党建人文' },
        { icon: 'DocumentCopy', title: '新闻宣教' },
      ],
      current: 1,
      isShow: true,
      currentScroll: 0,
      testShow: false,
      testShow2: false,
      testShow3: false,
      testShow4: false,
      testShow5: false,
      testShow6: false,
      testShow7: false,
      tableData: [
        {
          date: '湖南、宁夏精卫中心携手建跨省域医联体，推进委省共建国家精神医学中心建设',
        },
        { date: '湖南卒中联盟第三届脑心健康管理师培训班顺利结业' },
        { date: '浏阳市医保局及医疗机构来我院考察交流' },
        {
          date: '找差距补短板，明目标强推进 —— 医院召开国家三级公立医院绩效考核专题会议',
        },
        { date: '暑假进行时，快乐不打烊' },
        {
          date: '卓越服务“急”先行 精准把脉助发展 —— 湖南省卫生健康委副主任祝益民来我院调研指导',
        },
        {
          date: '喜讯！我院代表省卫健委参加“首届湖南省妇女健康运动会”拔河总决赛喜获三等奖！',
        },
        {
          date: '湖南卒中联盟第三届脑心健康管理师培训班开班，祝益民副主任受邀作专题讲座',
        },
      ],
    }
  },
  mounted() {
    window.addEventListener('scroll', this.handleScrollbox, true)
  },
  methods: {
    handleScrollbox() {
      this.currentScroll = window.pageYOffset //表示当前滚动的位置

      if (this.currentScroll >= this.$refs.testref.offsetTop - 700) {
        //当前滚动位置到达testref的时候，显示div（100作为调整用）
        this.testShow = true
      }
      if (this.currentScroll >= this.$refs.test2ref.offsetTop - 700) {
        this.testShow2 = true
      }
      if (this.currentScroll >= this.$refs.test3ref.offsetTop - 700) {
        this.testShow3 = true
      }

      if (this.currentScroll >= this.$refs.test4ref.offsetTop - 440) {
        this.testShow4 = true
      }
    },
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScrollbox, true)
  },
}
</script>

<style>
.example-showcase .el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

html,
body {
  max-width: 100%;
  /* overflow-x: hidden;
     禁止左右滑轮 */
}

.margin {
  width: 1230px;
  margin: 0 auto;
}

.differentLogin span {
  font-weight: bold;
}

.differentLogin span:hover {
  color: red;
}

/* //向上划入样式 */
.boxUp {
  /* //滚动后的位置 */
  transform: translateY(0%) !important;
  /* //滚动后显现 */
  opacity: 1 !important;
  transition: all 0.5s ease;
}

.boxUpTo10 {
  /* //滚动前的位置 */
  transform: translateY(10%);
  /* //滚动前设置透明隐藏 */
  opacity: 0;
}

/* //开始位置在左侧20%位置 */
.boxleftTo20 {
  transform: translateX(-20%);
  opacity: 0;
}
/* //开始位置在右侧120%位置 */
.boxtranslateXright {
  transform: translateX(120%);
  opacity: 0;
}

/* //滚动到指定位置时回到原点 */
.boxtestenterX {
  transform: translateX(0%) !important;
  opacity: 1 !important;
  transition: all 1s ease;
}

.back {
  width: 100%;
  height: 100%;
  background-color: #fff; /* 解决局部引入变成全局引入（覆盖js特效） */
}

.carousel {
  position: relative;
  width: 100%;
  height: 100%;
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

.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-carousel__item1 h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.carousel .el-carousel__item:nth-child(6n) {
  background: url(./images/banner00.jpg);
  background-size: 100% 100%;
}

.carousel .el-carousel__item:nth-child(6n + 1) {
  background: url(./images/banner01.jpg);
  background-size: 100% 100%;
}

.carousel .el-carousel__item:nth-child(6n + 2) {
  background: url(./images/banner02.jpg);
  background-size: 100% 100%;
}

.carousel .el-carousel__item:nth-child(6n + 3) {
  background: url(./images/banner03.jpg);
  background-size: 100% 100%;
}

.carousel .el-carousel__item:nth-child(6n + 4) {
  background: url(./images/banner04.jpg);
  background-size: 100% 100%;
}

.carousel .el-carousel__item:nth-child(6n + 5) {
  background: url(./images/banner05.jpg);
  background-size: 100% 100%;
}

/*  */
.nkyy_jyzn {
  width: 1210px;
  margin: 0 auto;
  margin-top: 56px;
  height: 200px;
}

.nkyy_jyznitem {
  padding: 50px 0px;
  text-align: center;
  font-size: 18px;
  line-height: 25px;
  color: #ffffff;
  box-shadow: 0px 0px 15px #e0e0e6;
  font-weight: bold;
}

.nkyy_xwzx {
  width: 1230px;
  margin: 0 auto;
  margin-top: 56px;
}

.nkyy_xwzxtit {
  position: relative;
  width: 100%;
  height: 34px;
  line-height: 34px;
  font-size: 22px;
  text-align: left;
  padding-bottom: 10px;
}

.nkyy_xwzxtit3 {
  width: 100px;
  height: 34px;
  text-align: right;
  color: #989898;
  float: right;
  font-size: 14px;
  margin: 0px;
}

.nkyy_xwzxtit3 a {
  width: 98px;
  display: block;
  height: 32px;
  line-height: 32px;
  text-align: center;
  background-color: #ffffff;
  color: #989898;
  text-decoration: none;
  border: #e6e6ee 1px solid;
  border-radius: 32px;
}

.nkyy_xwzxtit3 a:hover {
  transition: 0.3s;
  background-color: royalblue;
}

.nkyy_xwzxcon {
  width: 100%;
  height: 361px;
  margin-top: 6px;
}

.el-carousel__item2 h3 {
  display: flex;
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.nkyy_xwzxcon_carousel {
  /* height: 400px; */
  border-radius: 10px;
}

.nkyy_xwzxcon_carousel .el-carousel__item:nth-child(4n) {
  background: url(./images/journalism_01.jpg);
  background-size: 100% 100%;
}

.nkyy_xwzxcon_carousel .el-carousel__item:nth-child(4n + 1) {
  background: url(./images/journalism_02.jpg);
  background-size: 100% 100%;
}

.nkyy_xwzxcon_carousel .el-carousel__item:nth-child(4n + 2) {
  background: url(./images/journalism_03.png);
  background-size: 100% 100%;
}

.nkyy_xwzxcon_carousel .el-carousel__item:nth-child(4n + 3) {
  background: url(./images/journalism_04.jpg);
}

.nkyy_xwzxitem {
  width: 100%;
  height: 44px;
  line-height: 44px;
  font-size: 16px;
  text-align: left;
  overflow: hidden;
  border-bottom: #e6e6ee 1px solid;
}

.el-carousel__container {
  height: 340px;
}

.nkyy_ztlist {
  width: 1200px;
  margin: 0 auto;
  margin-top: 56px;
  padding: 0;
}

.nkyy_ztlistcc {
  width: 100%;
  height: 600px;
  margin: 0 auto;
  box-shadow: 0px 0px 26px #e0e0e6;
}

.nkyy_ztlistcct {
  position: absolute;
  width: 100%;
  height: 56px;
  line-height: 56px;
  color: #ffffff;
  font-size: 34px;
  text-align: center;
  left: 0px;
  top: 50%;
  margin-top: -28px;
  z-index: 99;
  text-shadow: 0px 0px 4px #989898;
  font-weight: normal;
}

.nkyy_ztlistcct2 {
  position: absolute;
  color: #ffffff;
  font-size: 34px;
  text-align: center;
  top: 100px;
  left: 100px;
}

.nkyy_ztlistc0 {
  position: relative;
  overflow: hidden;
}

.nkyy_ztlistc0 img {
  width: 100%;
  cursor: pointer;
  transition: all 0.6s;
}

.nkyy_ztlistc0 img:hover {
  transform: scale(1.2);
}

/* 底部 */
.nkyy_bottom {
  width: 100%;
  margin: 0 auto;
  margin-top: 56px;
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