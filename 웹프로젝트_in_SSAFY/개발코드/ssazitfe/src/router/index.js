import Vue from "vue";
import VueRouter from "vue-router";

import BlogBase from "../views/Blog/BlogBase.vue";
// import BlogHome from "../views/Blog/BlogHome.vue";
import BlogEditor from "../views/Blog/BlogEditor.vue";
import BlogManager from "../views/Blog/BlogManager.vue";
import BlogGallery from "../views/Blog/BlogGallery.vue";
import BlogCreate from "../views/Blog/BlogCreate.vue";
import WriteBook from "../views/Blog/WriteBook.vue";
import ModifyBook from "../views/Blog/ModifyBook.vue";
// import BlogArticle from "../views/Blog/BlogArticle.vue";

import BookAni from "../views/Blog/BookAni.vue";
import BoardManager from "../views/Blog/BoardManager.vue";
// import BlogNotFound from "../views/Blog/BlogNotFound.vue";

import ModifyArticle from "../views/Blog/ModifyArticle.vue";

import ArticleDetail from "../views/Blog/ArticleDetail.vue";
// import BookDetail from "../views/Blog/BookDetail.vue";
const BookDetail = () => import("../views/Blog/BookDetail.vue");
// 관리자
import ManageHeader from "../views/Manage/ManageHeader.vue";
import ManagePage from "../views/Manage/ManagePage.vue";
import ManageBar from "../views/Manage/ManageBar.vue";
import tmpTag from "../views/Manage/tmpTag.vue";
import Tag from "../views/Manage/Tag.vue";

// 메인
import MainHome from "../views/Main/MainHome.vue";
import Main from "../views/Main/Main.vue";
import Rank from "../views/Main/Rank.vue";
import Main_board from "../views/Main/Main_board.vue";
import Main_screen from "../views/Main/Main_screen.vue";
import Main_book from "../views/Main/Main_book.vue";
import Main_blog from "../views/Main/Main_blog.vue";
import Main_user from "../views/Main/Main_user.vue";
import MainMypage from "../views/Main/MainMypage.vue";

import Mypage_alarm from "../views/Main/Mypage_alarm.vue";
// const Mypage_alarm = () => import("@/views/Main/Mypage_alarm.vue");
import Mypage_accounts from "../views/Main/Mypage_accounts.vue";
// const Mypage_accounts = () => import("@/views/Main/Mypage_accounts.vue");
// import Mypage_privacy from "../views/Main/Mypage_privacy.vue";
const Mypage_privacy = () => import("@/views/Main/Mypage_privacy.vue");
import Mypage_messages from "../views/Main/Mypage_messages.vue";
// const Mypage_messages = () => import("@/views/Main/Mypage_messages.vue");
// import Mypage_friends from "../views/Main/Mypage_friends.vue";
const Mypage_friends = () => import("@/views/Main/Mypage_friends.vue");
// import Mypage_blocklist from "../views/Main/Mypage_blocklist.vue";
const Mypage_blocklist = () => import("@/views/Main/Mypage_blocklist.vue");
// import Mypage_bookmark from "../views/Main/Mypage_bookmark.vue";
const Mypage_bookmark = () => import("@/views/Main/Mypage_bookmark.vue");
// import Mypage_mycomments from "../views/Main/Mypage_mycomments.vue";
const Mypage_mycomments = () => import("@/views/Main/Mypage_mycomments.vue");
// import Remessage from "../views/Message/Remessage.vue";
const Remessage = () => import("@/views/Message/Remessage.vue");
// import WriteArticle from "@/views/Blog/WriteArticle";
const WriteArticle = () => import("@/views/Blog/WriteArticle");
import BoardDetail from "@/views/Blog/BoardDetail.vue";
// const BoardDetail = () => import("@/views/Blog/BoardDetail");

import Login from "@/components/user/Login.vue";
import Menu from "@/components/user/Menu.vue";
import Logout from "@/components/user/Logout.vue";
import SearchId from "@/components/user/SearchId.vue";
import SearchPw from "@/components/user/SearchPw.vue";
import Join from "@/components/user/Join.vue";
import JoinCheck from "@/components/user/JoinCheck.vue";
import HelloWorld from "@/components/user/Login.vue";
import ViewIds from "@/components/user/ViewIds.vue";
// 지은 test메인
import MainHome2 from "../views/Main/MainHome2.vue";

Vue.use(VueRouter);

const routes = [
  // {
  //   path: "/",
  //   name: "MainHome",
  //   component: MainHome,
  // },
  {
    path: "/managepage",
    name: "ManagePage",
    component: ManagePage,
    children: [
      {
        path: "/",
        name: "tmpTag",
        component: tmpTag
      },
      {
        path: "/tag",
        name: "Tag",
        component: Tag
      },
    ]
  },
  {
    path: "/manageheader",
    name: "ManageHeader",
    component: ManageHeader,
  },
  {
    path: "/managebar",
    name: "ManageBar",
    component: ManageBar,
  },

  {
    path:"/writearticle",
    name:"writearticle",
    component:WriteArticle
  },
  // {
  //   path: "/",
  //   name: "Mainpage",
  //   component: Main
  // },
  {
    path: "/",
    name: "MainHome",
    component: MainHome
  },
  {
    path: "/home2",
    name: "MainHome2",
    component: MainHome2
  },
  {
    path: "/main",
    name: "Mainpage",
    component: Main
  },
  {
    path: "/rank",
    name: "Rank",
    component: Rank
  },
  {
    path: "/mainboard/:searchValue/:page/:type",
    name: "Mainboard",
    component: Main_board
  },
  {
    path: "/mainblog/:searchValue/:page",
    name: "Mainblog",
    component: Main_blog
  },
  {
    path: "/mainuser/:searchValue/:page",
    name: "Mainuser",
    component: Main_user
  },
  {
    path: "/mainscreen/:select",
    name: "Main_Screen",
    component: Main_screen
  },
  {
    path: "/blogcreate",
    name: "BlogCreate",
    component: BlogCreate
  },
  {
    path: "/mainbook/:searchValue/:page",
    name: "Mainbook",
    component: Main_book
  },
  {
    path: "/mypage",
    name: "MainMypage",
    component: MainMypage,
    children: [
      {
        path: "/",
        name: "Mypage_accounts",
        component: Mypage_accounts
      },
      {
        path: "/accounts",
        name: "Mypage_accounts",
        component: Mypage_accounts
      },
      {
        path: "/privacy",
        name: "Mypage_privacy",
        component: Mypage_privacy
      },
      {
        path: "/messages",
        name: "Mypage_messages",
        component: Mypage_messages,
        params : []
      },
      {
        path: "/friends",
        name: "Mypage_friends",
        component: Mypage_friends
      },
      {
        path: "/blocklist",
        name: "Mypage_blocklist",
        component: Mypage_blocklist
      },
      {
        path: "/bookmark",
        name: "Mypage_bookmark",
        component: Mypage_bookmark
      },
      {
        path: "/mycomments",
        name: "Mypage_mycomments",
        component: Mypage_mycomments
      },
      {
        path: "/myalarm",
        name: "Mypage_alarm",
        component: Mypage_alarm
      }
    ]
  },
  // {
  //   path: "/friends",
  //   name: "Mypage_friends",
  //   component: Mypage_friends
  // },
  {
    path: "/bookani",
    name: "BookAni",
    component: BookAni
  },
  // blog 내부
  {
    path: "/bloghome/:userId",
    name: "BlogBase",
    component: BlogBase,
    children: [
      {
        path: "",
        name: "BoardDetail_friend",
        component: BoardDetail
        // path: "",
        // name: "BlogHome",
        // component: BlogHome,
        // children: [
        // ]
      },
      {
        path: "board/:boardNo",
        name: "BoardDetail",
        component: BoardDetail
      },
      {
        path: "blogmanager",
        name: "BlogManager",
        component: BlogManager
      },
      {
        path: "blogeditor",
        name: "BlogEditor",
        component: BlogEditor
      },
      {
        path: "writebook",
        name: "WriteBook",
        component: WriteBook,
      },
      {
        path: "modifybook/:bookNo",
        name: "ModifyBook",
        component: ModifyBook,
      },
      {
        path: "bloggallery",
        name: "BlogGallery",
        component: BlogGallery,
      },
      {
        path: "articledetail/:articleNo",
        name: "ArticleDetail",
        component: ArticleDetail,
      },
      {
        path: "modifyarticle/:articleNo",
        name: "ModifyArticle",
        component: ModifyArticle,

      },
      {
        path: "bookdetail/:bookNo",
        // path: "bookdetail",
        name: "BookDetail",
        component: BookDetail,
      },
      {
        path: "boardmanager",
        name: "BoardManager",
        component: BoardManager,
      },
    ]
  },
  {
    path: "/login", // 로그인 화면을 설정한다
    name: "Login",
    component: Login
  },
  {
    path: "/menu", // 로그인 화면을 설정한다
    name: "Menu",
    component: Menu
  },
  {
    path: "/viewids", // 로그인 화면을 설정한다
    name: "ViewIds",
    component: ViewIds
  },
  {
    path: "/searchid", // 로그인 화면을 설정한다
    name: "SearchId",
    component: SearchId
  },
  {
    path: "/searchpw", // 로그인 화면을 설정한다
    name: "SearchPw",
    component: SearchPw
  },
  {
    path: '/logout', // 로그인 화면을 설정한다
    name: 'Logout',
    component: Logout
  },
  {
    path: '/helloWorld', // 추가하는 path
    name: 'HelloWorld',
    component: HelloWorld // 추가하는 컴포넌트
  },
  {
    path: "/join", // 추가하는 path
    name: "Join",
    component: Join // 추가하는 컴포넌트
  },
  {
    path: "/joincheck", // 추가하는 path
    name: "JoinCheck",
    component: JoinCheck // 추가하는 컴포넌트
  },
  {
    path: "/remessage", // 추가하는 path
    name: "Remessage",
    component: Remessage // 추가하는 컴포넌트
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;