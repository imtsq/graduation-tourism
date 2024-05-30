import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 样式
import '@/style/global.less'
import '@/utils/Element-ui.js'
import '@/utils/MyComponent.js'
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
