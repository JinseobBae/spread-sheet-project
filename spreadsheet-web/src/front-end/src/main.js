import Vue from 'vue'
import App from './App.vue'
import VueExcelEditor from 'vue-excel-editor'
import router from './router/router'
import VueNavigationBar from "vue-navigation-bar";
import "vue-navigation-bar/dist/vue-navigation-bar.css";

Vue.config.productionTip = false
Vue.use(VueExcelEditor)
Vue.component("vue-navigation-bar", VueNavigationBar);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
