import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BootstrapVue from "bootstrap-vue";
import VueSession from "vue-session";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import vuetify from "./plugins/vuetify";
import CKEditor from 'ckeditor4-vue';
import Vuelidate from 'vuelidate'
// import GAuth from 'vue-google-oauth2'
Vue.use(BootstrapVue);
Vue.use(VueSession);
Vue.use(CKEditor);
Vue.use(Vuelidate)
// Vue.use(GAuth, {clientId: '785859429532-5b4tcaoio3c15s8i0677b8gebfiantct.apps.googleusercontent.com', scope: 'profile email https://www.googleapis.com/auth/plus.login'})
// Vue.use(GAuth, {"web":{"client_id":"785859429532-5b4tcaoio3c15s8i0677b8gebfiantct.apps.googleusercontent.com","project_id":"ssazit","auth_uri":"https://accounts.google.com/o/oauth2/auth","token_uri":"https://oauth2.googleapis.com/token","auth_provider_x509_cert_url":"https://www.googleapis.com/oauth2/v1/certs","client_secret":"v51YbKrX5Z9Ageqi7Gs6zY-Z","redirect_uris":["http://localhost:8080/main"]}})

var options = {
  persist: true
};
Vue.use(VueSession, options);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");