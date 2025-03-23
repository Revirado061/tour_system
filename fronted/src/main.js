import Vue from 'vue'
import App from './App.vue'
import  ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'; // Element 2

Vue.config.productionTip = false

Vue.use(ElementUI); // Element 3

new Vue({
  render: h => h(App),
}).$mount('#app')
