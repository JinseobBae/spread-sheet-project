import Vue from 'vue'
import App from './App.vue'
import VueExcelEditor from 'vue-excel-editor'

Vue.config.productionTip = false
Vue.use(VueExcelEditor)

new Vue({
  render: h => h(App),
}).$mount('#app')
