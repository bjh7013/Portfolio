import Vue from "vue";
import Router from "vue-router";

// View
import Home from "../views/Home.vue";
import Search from "../views/Search.vue";

import Login from "../views/Login.vue";
import Join from "../views/Join.vue";
import Welcome from "../views/Welcome.vue";
import Mypage from "../views/Mypage.vue";

import Calender from "../views/Calender.vue";
import Searchfood from "../views/Searchfood.vue";
import FoodDetail from "../views/FoodDetail.vue";
import Writefood from "../views/Writefood.vue";
import CompFood from "../views/CompFood.vue";

// Components
import TestFood from "../components/TestFood.vue";
import NutriPreDetail from "../components/NutriPreDetail.vue";
import Searchshop from "../components/Searchshop.vue";

import DietSearch from "../components/DietSearch.vue";
import DietForm from "../components/DietForm.vue";

import MypageInfo from "../components/MypageInfo.vue";
import MypagePw from "../components/MypagePw.vue";
import MypageUpdate from "../components/MypageUpdate.vue";

import YoutubeItem from "../components/youtubes/YoutubeItem.vue";
import YoutubeSearch from "../components/youtubes/YoutubeSearch.vue";
// import YoutubeItemDetail from "../components/YoutubeItemDetail.vue";

Vue.use(Router);

const routes = [
  // 메인 페이지
  {
    path: "/",
    name: "home",
    component: Home
  },
  // 개인 섭취량 상세
  {
    path: "/nutripredetail",
    name: "nutripredetail",
    component: NutriPreDetail
  },
  // 상세 검색 페이지
  {
    path: "/search",
    name: "search",
    component: Search,
    children: [
      {
        path: "/searchshop/:name",
        name: "searchshop",
        component: Searchshop
      },
      // {
      //   path: "/dislike",
      //   name: "dislike",
      //   component: Dislike
      // }
    ]
  },
  // 로그인 페이지
  {
    path: "/login",
    name: "login",
    component: Login
  },
  // 회원가입 페이지
  {
    path: "/join",
    name: "join",
    component: Join
  },
  // 회원가입 완료 페이지
  {
    path: "/welcome",
    name: "welcome",
    component: Welcome
  },
  // 식단 작성 페이지
  {
    path: "/writefood",
    name: "writefood",
    component: Writefood,
    children: [
      {
        path: "",
        name: "dietsearch",
        component: DietSearch
      },
      {
        path: "form",
        name: "dietform",
        component: DietForm
      }
    ]
  },
   //식사추가
   {
    path: "/compfood",
    name: "compfood",
    component: CompFood,
  },
  // 캘린더 페이지
  {
    path: "/calender",
    name: "calender",
    component: Calender,
  },
  // 선호도조사 페이지
  {
    path: "/testfood",
    name: "testfood",
    component: TestFood,
  },
  {
    path: "/searchfood/:name",
    name: "searchfood",
    component: Searchfood,
  },
  {
    path: "/fooddetail/:food_no",
    name: "fooddetail",
    component: FoodDetail,
    children: [
      {
        path: "",
        name: "youtubeitem",
        component: YoutubeItem
      },
      {
        path: "",
        name: "youtubesearch",
        component: YoutubeSearch
      },
      // {
      //   path: "",
      //   name: "youtubeitemdetail",
      //   component: YoutubeItemDetail
      // }
    ],
  },
  // 마이페이지
  {
    path: "/mypage",
    component: Mypage,
    children: [
      {
        path: "",
        name: "mypageinfo",
        component: MypageInfo
      },
      {
        path: "pw",
        name: "mypagepw",
        component: MypagePw
      },
      {
        path: "update",
        name: "mypageupdate",
        component: MypageUpdate
      },
    ]
  },
];

const router = new Router({
  mode: "history",
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    }
    if (to.hash) {
      return { selector: to.hash };
    }
    return { x: 0, y: 0 };
  }
});

export default router;

// const requireAuth = () => (from, to, next) => {
//   const isAuthenticated = false
//   if (isAuthenticated) return next()
//   next('/login?returnPate=me')
// }

// export default new Router({
//   {
//     path: '/me',
//     name: 'Me',
//     component: Me,
//     beforeEnter: requireAuth()
//   }