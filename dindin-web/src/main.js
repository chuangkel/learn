import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import './styles/element-variables.scss'
import 'font-awesome/css/font-awesome.min.css'
import './utils/filter_utils.js'
//编辑表格
import VXETable from 'vxe-table'
import 'vxe-table/lib/index.css'
Vue.use(VXETable)


import global_ from './utils/Global'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
Vue.config.productionTip = false

Vue.use(Antd)

localStorage.setItem("base",global_.base)
// Vue.use(VXETable, {
//   size: 'small',
//   version: 0,
//   tooltipConfig: {
//     zIndex: 3000
//   }
// })

Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.GLOBAL = global_;
window.bus = new Vue();
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})

