import UserAuth from '@/views/UserAuth.vue'

export default [
  // 메인 페이지
  {
    path: "",
    view: "Home",
    name: "home"
  },
  // 상세 검색 페이지
  {
    path: "/search",
    view: "Search",
    name: "search"
  },
  // 로그인 페이지
  {
    path: "/login",
    view: "Login",
    name: "login"
  },
  // 회원가입 페이지
  {
    path: "/join",
    view: "Join",
    name: "join"
  },
  // 마이페이지
  {
    path: "/userinfo",
    view: "UserInfo",
    name: "userinfo",
    children: [{
      path: "/userauth",
      name: "UserAuth",
      component: UserAuth
    }],
  },
  {
    path: "/userauth",
    view: "UserAuth",
    name: "userauth"
  },
  {
    path: "/userupdate",
    view: "UserUpdate",
    name: "userupdate"
  }
];
