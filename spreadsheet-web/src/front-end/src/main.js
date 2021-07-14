import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import VueNavigationBar from "vue-navigation-bar";
import "vue-navigation-bar/dist/vue-navigation-bar.css";
import ExcelEditor from './editor/ExcelEditor'
import ContextMenu from "@/components/ContextMenu";
import ContextMenuItem from "@/components/ContextMenuItem";

Vue.config.productionTip = false
Vue.component("vue-navigation-bar", VueNavigationBar);
Vue.component('vue-excel-editor', ExcelEditor)
Vue.component('context-menu', ContextMenu)
Vue.component('context-menu-item', ContextMenuItem)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
