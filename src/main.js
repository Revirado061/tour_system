import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router' // 路由 1
import router from './router' // 路由 2
import Plugin from 'v-fit-columns';
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import axios from 'axios';
import VueAxios from 'vue-axios';
import Prism from 'prismjs';
import { Autocomplete} from "element-ui";

VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});

Vue.use(VueMarkdownEditor);
// 关闭 Vue 的生产提示
Vue.config.productionTip = false
// 使用插件
Vue.use(ElementUI)
Vue.use(VueRouter)// 路由 3
Vue.use(Plugin)
Vue.prototype.$axios = axios
Vue.use(VueAxios,axios) // 使用axios插件
Vue.use(Autocomplete)
Vue.prototype.$bus = new Vue(); // 添加事件总线
// 创建 Vue 实例对象
new Vue({
  render: h => h(App),
  router // 路由 4
}).$mount('#app')

