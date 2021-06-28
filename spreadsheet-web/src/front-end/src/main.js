import Vue from 'vue'
import App from './App.vue'
import VueExcelEditor from 'vue-excel-editor'
import router from './router/router'

Vue.config.productionTip = false
Vue.use(VueExcelEditor)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
