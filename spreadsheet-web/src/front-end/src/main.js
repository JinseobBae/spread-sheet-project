import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import VueNavigationBar from "vue-navigation-bar";
import "vue-navigation-bar/dist/vue-navigation-bar.css";
import ExcelEditor from './editor/ExcelEditor'
import ContextMenu from "@/components/ContextMenu";
import ContextMenuItem from "@/components/ContextMenuItem";
import BootstrapVue from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.component("vue-navigation-bar", VueNavigationBar);
Vue.component('vue-excel-editor', ExcelEditor)
Vue.component('context-menu', ContextMenu)
Vue.component('context-menu-item', ContextMenuItem)
Vue.use(BootstrapVue)

new Vue({
  router,
  render: h => h(App),
  watch: {
    $route(to){
      document.title = to.params.name || to.name
    }
  }
}).$mount('#app')
