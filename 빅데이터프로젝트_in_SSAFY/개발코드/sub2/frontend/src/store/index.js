/*
getters : state값 리턴
actions -> mutations -> state값 변경
*/

import Vue from "vue";
import Vuex from "vuex";
import Axios from 'axios';

Vue.use(Vuex);

// 햄버거 동작용
const set = property => (state, payload) => (state[property] = payload);
const toggle = property => state => (state[property] = !state[property]);

const state = {
  drawer: null,
  userToken : null,  // 로그인시 토큰
  userId : null,     // 로그인한 유저 아이디
  isLogin : false,   // 로그인 상태
  userAgree : false, // 회원가입 약관 동의
  eatType : '아침',  // 식단 유형(아침, 점심, 저녁, 간식)
  dietDate : null,   // 식단 작성 날짜
  pickFood : null,   // 식단 작성에 선택된 음식명
  beforePage : null, // 식단 작성 전 페이지명
  serverURL : 'https://j3d201.p.ssafy.io:8000',
  searchResult : '',
};

export default new Vuex.Store({
  state,
  getters: {
    searchResult() {
      return state.searchResult;
    },
    userId() {
      return state.userId;
    },
    isLogin() {
      return state.isLogin;
    },
    userAgree() {
      return state.userAgree;
    },
    eatType() {
      return state.eatType;
    },
    dietDate() {
      return state.dietDate;
    },
    pickFood() {
      return state.pickFood;
    },
    beforePage() {
      return state.beforePage;
    }
  },
  mutations: {
    Login(state, payload) {
      state.userToken = payload;
      state.isLogin = true;
    },
    Logout(state, payload) {
      state.userToken = payload;
      state.isLogin = false;
    },
    UserId(state, payload) {
      state.userId = payload;
    },
    UserAgree(state, payload) {
      state.userAgree = payload;
    },
    setSearchResult(state, payload){
      // console.log(payload)
      state.searchResult = payload;
      // console.log(state.searchResult)
    },
    EatType(state, payload) {
      state.eatType = payload;
      // console.log(state.eatType)
    },
    DietDate(state, payload) {
      state.dietDate = payload;
    },
    PickFood(state, payload) {
      state.pickFood = payload;
    },
    BeforePage(state, payload) {
      state.beforePage = payload;
    },
    // Drawer 동작용
    setDrawer: set("drawer"),
    toggleDrawer: toggle("drawer"),
  },
  actions: {
    searchFood(context, word){
     
      Axios.get(`/apis/namefood/${word}`)
      .then( ({data}) => {
        context.commit('setSearchResult', data)
      })
      .catch(() =>{
          alert('error')
        }
      )
    },
    Login(context, data) {
      context.commit("Login", data);
    },
    Logout(context, data) {
      context.commit("Logout", data);
    },
    UserId(context, data) {
      context.commit("UserId", data);
    },
    UserAgree(context, data) {
      context.commit("UserAgree", data);
    },
    EatType(context, data) {
      context.commit("EatType", data);
    },
    DietDate(context, data) {
      context.commit("DietDate", data);
    },
    PickFood(context, data) {
      context.commit("PickFood", data);
    },
    BeforePage(context, data) {
      context.commit("BeforePage", data);
    }
  }
});