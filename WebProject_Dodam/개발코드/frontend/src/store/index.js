import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    BACKEND_URL : 'https://k3d206.p.ssafy.io',
    WS_URL : 'https://k3d206.p.ssafy.io/dodam',
    // BACKEND_URL : 'http://localhost:9999/dodam',
    // WS_URL : 'http://localhost:9999/dodam',
    // BACKEND_URL : 'http://localhost:9999/dodam',
    // BACKEND_URL : 'https://k3d206.p.ssafy.io/dodam',
    // BACKEND_URL : 'http://localhost:9999/dodam',
    // BACKEND_URL : 'https://k3d206.p.ssafy.io',
    
    // BACKEND_URL : 'http://localhost:9999/dodam',
    // BACKEND_URL : 'http://192.168.0.16:9999/dodam', // 정훈이집
    // BACKEND_URL : 'http://172.30.1.11:9999',
    // BACKEND_URL : 'http://192.168.1.2:9999/dodam', // 정훈url
    // BACKEND_URL : 'http://192.168.100.70:9999/dodam', // 정훈 ssafy url
    // BACKEND_URL : 'http://172.30.1.59:9999', // 정훈 투썸
    userInfo : null,
    class : null
  },

  getters: {
    WS_URL(state){
      return state.WS_URL
    },
    BACKEND_URL(state){
      return state.BACKEND_URL
    },
    userInfo(state) {
      return state.userInfo
    },
    classInfo(state){
      return state.class
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.userInfo = payload;
    },
    LOGOUT(state) {
      state.userInfo = null;
    },
    CLASS(state, payload) {
      state.class = payload;
    },
  },
  actions: {
    LOGIN(context, data) {
      context.commit("LOGIN", data);
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
    },
    CLASS(context, data) {
      context.commit("CLASS", data);
    },
  }, 
  modules: {
  }
})
