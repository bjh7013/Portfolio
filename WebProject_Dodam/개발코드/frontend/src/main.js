import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import store from './store'
import VueSession from "vue-session";
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'jquery/dist/jquery.min.js'

import VModal from 'vue-js-modal'
import axios from 'axios'
import moment from 'moment'
import VueMomentJS from 'vue-momentjs'
import Chat from 'vue-beautiful-chat'

axios.defaults.baseURL = 'https://k3d206.p.ssafy.io/dodam'

// axios.defaults.baseURL = 'http://localhost:9999/dodam'
// axios.defaults.baseURL = 'http://192.168.0.16:9999' // 정훈이집
// axios.defaults.baseURL = 'http://localhost:9999'
// axios.defaults.baseURL = 'http://192.168.0.16:9999/dodam' // 정훈이집
// axios.defaults.baseURL = 'http://172.30.1.11:9999'
// axios.defaults.baseURL = 'https://k3d206.p.ssafy.io/dodam'
// axios.defaults.baseURL = 'http://192.168.1.2:9999/dodam' // 정훈 url
// axios.defaults.baseURL = 'http://192.168.100.70:9999/dodam' // 정훈 ssafy url
// axios.defaults.baseURL = 'http://172.30.1.59:9999' // 정훈 투썸
var options = {
  // persist: true
};
Vue.use(VueMomentJS,moment);
Vue.use(VModal,{dynamic:true})
Vue.use(BootstrapVue)
Vue.config.silent = true

Vue.use(VueSession, options);
// Vue.config.productionTip = false

Vue.use(Chat)

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
