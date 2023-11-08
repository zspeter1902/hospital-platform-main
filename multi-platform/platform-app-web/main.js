import App from './App'
import store from './store/index';
import mixin from './common/mixin'
import baseUtils from '@/utils/baseUtils.js'
import authUtils from '@/utils/authUtils.js'
import imageHandler from '@/utils/imageHandler.js'
import apiRequest from '@/utils/apiRequest.js'
import bizConstants from '@/utils/bizConstants.js'
import apiConstants from '@/utils/ApiConstants.js'
import uView from "@/components/uview-ui";
Vue.use(uView);
// #ifdef MP
// 引入uView对小程序分享的mixin封装
const mpShare = require('@/components/uview-ui/libs/mixin/mpShare.js')
Vue.mixin(mpShare)
// #endif
Vue.mixin(mixin)
//挂在请求函数到全局
Vue.prototype.$postRequest = apiRequest.postRequest
Vue.prototype.$getRequest = apiRequest.getRequest
Vue.prototype.$uploadRequest = apiRequest.uploadRequest
Vue.prototype.$putRequest = apiRequest.putRequest
Vue.prototype.$deleteRequest = apiRequest.deleteRequest
Vue.prototype.$apiConstants = apiConstants
Vue.prototype.$bizConstants = bizConstants
Vue.prototype.$baseUtils = baseUtils
Vue.prototype.$authUtils = authUtils
Vue.prototype.$verifyLogin = authUtils.verifyLogin
Vue.prototype.$store = store
Vue.prototype.$imageHandler = imageHandler 
Vue.prototype.$isNull = baseUtils.isNull
Vue.prototype.$platformName = '微信小程序'
Vue.prototype.$mainColor = 'rgb(42,132,255)'
Vue.prototype.$mainButtonColor = '#3b5090' //主要的按钮背景颜色
Vue.prototype.$pageViewBgColor = '#f3f4f6'
Vue.prototype.$commonDark= '#000000' //普通黑色
Vue.prototype.$commonRed= '#ed3f14' //普通红色
Vue.prototype.$commonWhite= '#FFFFFF' //普通白色
Vue.prototype.$sketchColor= '#7D7D7D' //简略信息 
Vue.prototype.$commonGreen= 'rgb(83,194,64)' //普通绿色
Vue.prototype.$clickedRed= '#c32b1e' //点击后的红色
Vue.prototype.$commonShallow= '#f2f3f5'; //普通的浅颜色
Vue.prototype.$commonBg= '#f0f0f0';//普通的背景颜色
Vue.prototype.$navbarHeight= 45 //导航栏高度
Vue.prototype.$platform = uni.$u.platform//当前平台
Vue.prototype.$wxPlatform = 'mp'//微信平台名称
Vue.prototype.$wxOrginalId = 'gh_7fdf615a0507'//微信小程序原始ID

// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif