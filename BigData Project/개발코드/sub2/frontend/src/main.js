import Vue from "vue";
import App from "./App.vue";
import infiniteScroll from "vue-infinite-scroll";
import router from "./router";
import store from "./store";
import VueCookies from 'vue-cookies'
import VueGeolocationApi from 'vue-geolocation-api'
import * as VueGoogleMaps from "vue2-google-maps"
import vuetify from "@/plugins/vuetify";

Vue.config.productionTip = false;

Vue.use(infiniteScroll);
Vue.use(VueCookies);
// Vue.use(vuetify);
Vue.use(VueGeolocationApi);
Vue.use(VueGoogleMaps, {
  load: {
    key : "AIzaSyBGL_okWudyHsnkuIRASynyg_a1rTd9orA",
    libraries : "places,drawing,visualization"
  }
})

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
