<template>
  <div id="BlogBase" v-cloak>
    <!-- 프로필 점세개 버튼 추가해야함 -->
    <!-- 블로그 내부 공통 (nav + sidebar) -->
    <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ BlogNav ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
    <v-system-bar window class="pl-5" style="height:48px; border-bottom:1px solid #EEEE; background-color:white;">
      <router-link to="/main" style="text-decoration:none; color:black; font-weight:b; font-size:16px;">
        <span class="mr-3">SSAZIT 홈</span>
      </router-link>
      <v-spacer></v-spacer>
      <v-btn-toggle
        tile
        color="#EEEE"
        group
      >
        <v-row v-if="userInfo">
          <div>
            <!-- <div style="height:1px;"></div> -->
            <v-btn @click="myBlog" value="내블로그" class="mx-0" style="color:black;">
              내블로그
            </v-btn>
          </div>
          <div>
            <!-- <div style="height:1px"></div> -->
            <v-menu
              v-model="navFollow"
              :close-on-content-click="false"
              :nudge-width="200"
              offset-x
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  v-bind="attrs"
                  v-on="on"
                  class="mr-5"
                  style="color:black;"
                >
                  이웃블로그
                </v-btn>
              </template>

              <v-card>
                <div style="height:15px"></div>
                <b class="p-3">이웃블로그</b>

                <div v-if="following && following.length > 0" align="center" class="mt-3 px-9" style="width:100%; height: 300px; overflow-x: scroll;">
                  <!-- 여기부터 -->
                  <div v-for="friend in following" :key="friend.userNo">
                    <v-row v-if="friend.blogName !== null">
                      <div>
                        <div style="height:12px;"></div>
                        <v-avatar>
                          <img :src="serverURL + friend.profileImg">
                        </v-avatar>
                      </div>
                      <v-col cols="8">
                        <div @click="goblog(friend.userId)">
                          <a style="color:black;">{{friend.nickName}}({{friend.userId}})</a>
                        </div>
                        <!-- <v-list-item-title>{{friend.nickName}}({{friend.userId}})</v-list-item-title> -->
                        <div style="color:grey;">{{friend.blogName}}</div>
                      </v-col>

                      <v-col cols="1">
                        <v-btn
                          icon
                          @click="goblog(friend.userId)"
                        >
                          <v-icon>mdi-home</v-icon>
                        </v-btn>
                      </v-col>
                    </v-row>
                    <v-row v-else style="height:0px;"></v-row>
                  </div>
                  <!-- 여기까지 포문 -->
                </div>
                <div v-else>
                  <p class="ml-4 mt-4">이웃이 없습니다.</p>
                </div>
                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn v-if="following && following.length > 0" color="primary" text to="/friends" style="text-decoration:none;">더보기</v-btn>
                  <v-btn text @click="navFollow = false">취소</v-btn>
                </v-card-actions>
              </v-card>
            </v-menu>
          </div>
        </v-row>

        <!-- 로그인시 닉네임, 비로그인시 로그인버튼(주석) -->
        <div v-if="userInfo">
          <!-- <div style="height:1px"></div> -->
          <v-menu
            v-model="navProfile"
            :close-on-content-click="false"
            :nudge-width="200"
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                v-bind="attrs"
                v-on="on"
                style="color:black;"
              >
                {{ userInfo.userName }}님
              </v-btn>
            </template>

            <v-card style="border:1px solid lightgrey; width:300px;">
              <v-row class="mx-0" style="height:130px;">
                <v-col cols="5" class="ml-3" style="text-align:center;">
                  <div style="height:15px;"></div>
                  <v-avatar size="80">
                    <v-img :src="serverURL + userInfo.profileImg"></v-img>
                  </v-avatar>
                </v-col>
                <v-col class="px-0">
                  <div style="height:30px;"></div>
                  <v-row>
                    <div style="width:15px;"></div>
                    <b>{{ userInfo.userId }}</b>님
                    <v-btn @click="Logout" x-small tile dense outlined color="black" class="ml-2" style="border-radius: 5px;" >
                      로그아웃
                    </v-btn>
                  </v-row>
                  <a @click="myBlog" class="mb-0" style="color:black;">
                    {{ userInfo.blogName }}
                  </a>
                  <!-- 랭킹 -->
                  <!-- <p class="mb-0">
                    <router-link to="/rank" style="color:black; text-decoration:none;">
                      <v-icon small>mdi-trophy-variant</v-icon>
                      {{ userInfo.userLevel }}
                    </router-link>
                    <v-tooltip bottom>
                      <template v-slot:activator="{ on, attrs }">
                        <v-icon
                          small
                          color="black"
                          dark
                          v-bind="attrs"
                          v-on="on"
                        >mdi-information-outline</v-icon>
                      </template>
                      <span>
                        ssazit까지 2000점 남았습니다
                      </span>
                    </v-tooltip>
                  </p> -->
                </v-col>
              </v-row>
              <v-card-actions class="p-0" style="background-color:#EEEE;">
                <div style="height:50px;">
                  <v-row>
                    <div style="width:15px;"></div>
                    <v-btn x-large text to="/mypage" style="text-decoration:none;">내정보</v-btn>
                    <!-- <div style="width:5px;"></div> -->
                    <v-btn x-large text to="/messages" class="ml-3 px-4" style="text-decoration:none;">내쪽지함</v-btn>
                    <!-- <div style="width:5px;"></div> -->
                    <v-btn x-large text @click="navProfile = false">취소</v-btn>
                  </v-row>
                </div>
              </v-card-actions>
            </v-card>
          </v-menu>
        </div>

        <v-btn v-else to="/login" color="black" class="ml-0" style="text-decoration:none; border:none;">로그인</v-btn>
      </v-btn-toggle>
    
    </v-system-bar>
    <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
    <div v-if="blog_Info && blog_Info.blogNo">
      <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡBlogTopBannerㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
      <v-row>
        <v-col
          v-for="(bar, barIdx) in bars"
          :key="barIdx"
          cols="12"
          class="topBanner p-0 m-0"
        >

          <v-card color="white" flat>
            <v-toolbar :color="bar.class" :dark="bar.dark" prominent extended>
              <!-- 블로그 명 -->
              <!-- 현재 블로그로 보내주세요!!!!!! (내블로그x) -->
              <v-toolbar-title style="width:100%;">
                <h1 style=" cursor:pointer; font-size:35px; font-weight:bold; color:black; text-align:center; margin-bottom:0px;" @click="$router.push(`/bloghome/${searchInfo.userId}`)">{{searchInfo.blogName}}</h1>
              </v-toolbar-title>
            </v-toolbar>
          </v-card>
          <v-row style="justify-content:space-between; width:80%; margin:0px auto;">
            <v-spacer></v-spacer>
            <!-- 블로그명 수정 [본인블로그일때만] -->
            <v-btn-toggle
              borderless
              v-if="userInfo && (userInfo.blogNo == searchInfo.blogNo)"
            >
              <v-btn value="블로그명 수정" color="white" class="text-decoration-none" :to="{ path: `/bloghome/${userInfo.userId}/blogmanager`}">
                <span class="hidden-sm-and-down mr-1">블로그명 수정</span>
                <v-icon small color="grey">mdi-pencil</v-icon>
              </v-btn>
            </v-btn-toggle>
            <v-btn-toggle
              borderless
              v-else
              style="height:50px;"
            >
            </v-btn-toggle>
          </v-row>

        </v-col>
      </v-row>
      <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
      <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡBlogSidebarㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
      <div class="page-wrapper chiller-theme default" style="height:100%;">
        <a id="show-sidebar" class="btn btn-sm btn-dark offScroll">
          <i class="fas fa-bars"></i>
        </a>
        <nav id="sidebar" class="sidebar-wrapper offScroll">
          <div class="sidebar-content">
            <div class="sidebar-brand">
              <p class="pt-2 mb-1">메뉴</p>
              <div id="close-sidebar">
                <i class="fas fa-times"></i>
              </div>
            </div>
            <div class="sidebar-header">
              <div class="user-pic">
                <img class="img-responsive img-rounded" :src="userImage">
              </div>
              <div class="user-info">
                <span class="user-name pt-0">
                  <strong>{{ blogInfo.nickName }}</strong>
                  ({{blogInfo.userId}})
                  <template v-if="$store.getters.userInfo">
                    <v-btn icon @click="sendmessage(blogInfo.nickName)">
                      <i class="fa fa-comment-dots" style="color: #28a745;"></i>
                    </v-btn>
                  </template>
                  <div v-if="showModal" @close="showModal = false">
                    <transition name="modal">
                      <div class="modal-mask">
                        <div class="modal-wrapper">
                          <v-container fluid>
                            <v-row>
                              <v-col cols="4">
                                <v-sheet elevation="12" class="px-10 pt-5 pb-10" style="border-radius:10px;">
                                  <p class="pb-3" 
                                    style="border-top-left-radius:10px; 
                                      border-top-right-radius:10px;
                                      border-bottom:1px solid #EEEE;"
                                  >
                                    쪽지 쓰기
                                  </p>
                                  <v-text-field
                                    v-model="messageModel"
                                    :hint="hint"
                                    :placeholder="placeholder"
                                    maxlength="90"
                                    clearable
                                    counter
                                    class="mb-5"
                                  ></v-text-field>
                                  <div class="pa-3" style="background-color:#EEEE; border-radius:5px;">
                                    <div>
                                      <span style="font-weight:bold">To.</span>
                                      <span> {{ hint }}</span>
                                    </div>
                                    <div class="my-3" style="word-break:break-all;">
                                      {{ messageModel }}
                                    </div>
                                    <div style="text-align:right;">
                                      <span>From.</span>
                                      <span>  {{ userInfo.nickName }}</span>
                                    </div>
                                  </div>
                                  <div class="my-3">
                                    <button class="modal-default-button pl-5 pr-1 py-1" style="color:green;" @click="send_message()">
                                      전송
                                    </button>
                                    <button class="modal-default-button px-5 py-1" @click="cancel()">
                                      취소
                                    </button>
                                  </div>
                                </v-sheet>
                              </v-col>
                            </v-row>
                          </v-container>
                        </div>
                      </div>
                    </transition>
                  </div> 
                </span>
                <span class="user-role">
                  <!-- 차단해제 버튼 -->
                  <v-btn v-if="blockcheck" x-small tile dense outlined color="orange" style="border-radius: 5px;" @click="block_cancel">
                    <v-icon x-small>mdi-account-plus</v-icon> 차단해제
                  </v-btn>
                  <!-- 팔로우 버튼 -->
                  <v-btn v-else-if="$store.getters.userInfo && $store.getters.blogInfo && ($store.getters.blogInfo.userNo != $store.getters.userInfo.userNo && !this.check)" x-small tile dense outlined color="blue" style="border-radius: 5px;" @click="follow">
                    <v-icon x-small>mdi-account-plus</v-icon> Follow
                  </v-btn>
                  <!-- 팔로우삭제 버튼 -->
                  <v-btn v-else-if="$store.getters.userInfo && $store.getters.blogInfo && ($store.getters.blogInfo.userNo != $store.getters.userInfo.userNo && this.check)" x-small tile dense outlined color="red" style="border-radius: 5px;" @click="unfollow">
                    <v-icon x-small>mdi-account-plus</v-icon> UnFollow 
                  </v-btn>
                  <v-btn v-else-if="$store.getters.userInfo && $store.getters.blogInfo && ($store.getters.blogInfo.userNo == $store.getters.userInfo.userNo)" to="/mypage" x-small tile dense outlined color="#818896" style="border-radius:5px;" >
                    내정보
                  </v-btn>
                  
                </span>
              </div>
            </div>
            <!-- sidebar-header  -->
            <div class="sidebar-search">
              <div>
                <div class="input-group">
                  <input type="text" v-model="word" class="form-control search-menu" placeholder="Search..." @keyup.enter="searchArticle">
                  <div class="input-group-append" @click="searchArticle">
                    <span class="input-group-text">
                      <i class="fa fa-search" aria-hidden="true" style="cursor:pointer;"></i>
                    </span>
                  </div>
                </div>
              </div>
            </div>
            <!-- sidebar-search  -->
            <div class="sidebar-menu">
              <ul>
                <li class="header-menu">
                  <span>General</span>
                </li>
                <li v-for="board in treebabo" :key="board.id" class="sidebar-dropdown">
                  <a v-if="board.children.length" id="yes-sub">
                    <i v-if="board.type == 'N'" class="fa fa-folder"></i>
                    <i v-else class="fa fa-code"></i>
                    <span @click="boardnum(board.id)">{{ board.name }}</span>
                  </a>
                  <a v-else id="no-sub">
                    <i v-if="board.type == 'N'" class="fa fa-folder"></i>
                    <i v-else class="fa fa-code"></i>
                    <span @click="boardnum(board.id)">{{ board.name }}</span>
                  </a>
                  <div v-if="board.children" class="sidebar-submenu">
                    <ul v-for="subBoard in board.children" :key="subBoard.id">
                      <li>
                        <a @click="boardnum(subBoard.id)">{{ subBoard.name }}</a>
                      </li>
                    </ul>
                  </div>
                </li>
                
                <li class="header-menu">
                  <span>Extra<br></span>
                </li>
                <li>
                  <router-link :to="{ path: `/bloghome/${blogInfo.userId}/bloggallery`}">
                    <i class="fa fa-book"></i>
                    <span>작품 ({{bookscount}})</span>
                  </router-link>
                </li>
              </ul>
            </div>
            <!-- sidebar-menu  -->
          </div>
          <!-- sidebar-content  -->
          <div v-if="userInfo && (userInfo.blogNo == searchInfo.blogNo)" class="sidebar-footer">
            <!-- 알림 -->
            <router-link to="/myalarm">
              <i class="fa fa-bell"></i>
            </router-link>
            <!-- 쪽지 -->
            <router-link to="/messages">
              <i class="fa fa-envelope"></i>
            </router-link>
            <!-- 관리 -->
            <router-link :to="{ path: `/bloghome/${userInfo.userId}/boardmanager`}">
              <i class="fa fa-cog"></i>
              <span class="badge-sonar"></span>
            </router-link>
            <!-- 로그아웃 -->
            <a @click="Logout">
              <i class="fa fa-power-off"></i>
            </a>
          </div>
        </nav>
        
        <!-- sidebar-wrapper  -->
        <main class="page-content py-10">
          <div class="container pt-0" style="width:90% margin:auto;">
            <v-col class="p-0">
              <router-view :searchWord="searchWord" @set-search-word="setSearchWord" class="p-0" style="display:block; align-items:center; width:100%;">
              </router-view>
              
            </v-col>
          </div>
        </main>
      </div>
    </div>
    <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
    <div v-else-if="notfound">
      <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ블로그가 없는 경우ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
      <!-- 유효하지 않은 페이지입니다.
      <button @click="refresh">새로고침</button>
      <button @click="home">홈</button> -->
      <blog-not-found></blog-not-found>
      <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
    </div>
    <div v-else>
    </div>

    
  </div>
</template>

<script>
import { 
  mdiCommentMultipleOutline, 
  mdiBookMultiple, 
  mdiDotsHorizontal,
  } from '@mdi/js';
import axios from 'axios'
import BlogNotFound from "@/views/Blog/BlogNotFound.vue";

export default {
  name: "BlogBase",
  data () {
    return {
      show: false, 
      notfound : false,
      navFollow: false,
      navProfile: false,
      barProfile: false,
      message: false,
      hints: true,
      check:false,
      profile:false,
      showModal : false,
      model: '',
      hint : "누구에게",
      my_index : "",
      your_index : "",
      placeholder: '내용을 입력하시오.',
      following : [],
      articleIcon: mdiCommentMultipleOutline,
      bookIcon: mdiBookMultiple,
      profileIcon: mdiDotsHorizontal,
      messageModel: '',
      blogId :'',
      userImage: '',
      userImages: [],
      searchInfo:{}, //
      blogInfo:{}, // 해당 블로그 정보
      divider: true,
      header: 'board',
      boardsNo: '',
      blockcheck : false,
      boardNo: '',
      profileItems: [
        { user: 'Nickname', },
      ],

      BoardItems: [
      ],

      GalleryItems: [
        {
          action: 'label',
          title: '작품',
          link: 'bloggallery',
        },
      ],
      bars: [
        { class: 'elevation-0' },
      ],

      treebabo: [],
      bookscount: '',
      word:'',
      searchWord:'',
    }
  },
  components: {
    BlogNotFound,
  },
  mounted() {
    // 스크롤에 따라 사이드바 css 속성 변경
    if(this.$route.params.boardNo){
      this.$store.commit('fromBoardNo', this.$route.params.boardNo);
    }
    window.$(document).ready(function() {
      window.$(function() {
        window.$(window).scroll(function() {
          if (window.$(this).scrollTop() > 280) {
            if (window.$(".sidebar-wrapper").hasClass("offScroll")) {
              window.$(".sidebar-wrapper").removeClass("offScroll")
              window.$(".sidebar-wrapper").addClass("onScroll");
            }
          } else {
            if (window.$(".sidebar-wrapper").hasClass("onScroll")) {
              window.$(".sidebar-wrapper").removeClass("onScroll")
              window.$(".sidebar-wrapper").addClass("offScroll");
            }
          }
        });
      });
 
    });
    axios.get('/blog/searchbyuserid?userId=' + encodeURI(this.$route.params.userId))
    .then((Response)=>{
      // console.log(this.$route.params.userId)
      // console.log(Response.data + "어느 사람의 블로그인지");
      
      // console.dir(Response)
      if(!Response.data || !Response.data.blogNo){
        //해당 유저의 블로그가 없는 경우
        this.notfound = true;
        return;
      }
    
      
      this.searchInfo = Response.data; //해당 하는 루트의 블로그 정보
      this.$store.dispatch('Re_blogInfo',this.searchInfo)

      let requestUrl = `/book/bookscount?writer=` + encodeURI(Response.data.userNo);
      if(this.userInfo){
        requestUrl += `&loginUserNo=` + encodeURI(this.userInfo.userNo);
      }

      // searchInfo.userNo
      axios.get(requestUrl)
      .then((Response)=>{
        // console.log("here");
        // console.log(Response.data);
        // console.log(this.searchInfo);
        this.bookscount = Response.data;
      })

      axios.get('/user/block')
      .then((Res)=>{
        // console.log(Res);
        this.blocklist = Res.data;
        for(let i=0;i<Res.data.length;i++){
          if(Res.data[i].userId == Response.data.userId){
            this.blockcheck = true;
            break;
          } 
        }
      })
      .catch(() =>{
        alert('실패')
      })

      axios.get('/blog/boardlist?blogNo=' + Response.data.blogNo)
      .then((Res)=>{
        // console.log("get boardlist as blogNo");
        // console.log(Res);
        this.BoardItems = Res.data; // 해당 블로그의 게시판 정보
        var boardIndex;
        var preGroup = -1;
        var preBoardNo = -1;
        for(boardIndex = 0; boardIndex<this.BoardItems.length; boardIndex++){
          if(preGroup === this.BoardItems[boardIndex].groupNo){
            this.treebabo[preBoardNo].children.push({
              id: this.BoardItems[boardIndex].boardNo, 
              name: this.BoardItems[boardIndex].boardName, 
              count: this.BoardItems[boardIndex].articleCount, 
              type: this.BoardItems[boardIndex].boardType,
            });
          }else{
            this.treebabo.push({ 
              id: this.BoardItems[boardIndex].boardNo, 
              name: this.BoardItems[boardIndex].boardName, 
              count: this.BoardItems[boardIndex].articleCount, 
              type: this.BoardItems[boardIndex].boardType,
              children: []
            });
            preGroup = this.BoardItems[boardIndex].groupNo;
            preBoardNo += 1;
          }
        }
        this.$store.dispatch('Re_boardInfo',Res.data)
      })
    });
   
      
      axios.post('/user/list',{
      searchKey : "userId",
      searchValue : this.$route.params.userId
    }).then((Response)=>{
      // console.log(Response);
      // console.log("Hi");

      for(let i=0;i<Response.data.length;i++){
        if(Response.data[i].userId==this.$route.params.userId){
          this.blogInfo = Response.data[i]; // 해당 블로그 정보
          break;
        }
      }
      // this.blogInfo = Response.data[0];
      // console.log(this.blogInfo.profileImg);
      this.userImage = this.serverURL + this.blogInfo.profileImg;
    })
    // console.log(this.searchInfo + "어느 사람의 블로그인지");
    // console.log(this.blogInfo.blogNo + "블로그의 정보");

    axios.get('/user/following')
    .then((Response)=>{
      // console.log(Response);
      this.following = Response.data;
      for(let i=0;i<Response.data.length;i++){
        Response.data[i].userId
        // console.log(Response.data[i].userId+"이웃 아이디")
        axios.post('/user/list',{
        searchKey : "userId",
        searchValue : Response.data[i].userId
        }).then((Res)=>{
        // console.log(Res.data);
        // console.log("Hi");

        for(let j=0;j<Res.data.length;j++){
          if(Res.data[j].userId==Response.data[i].userId){
            // this.blogInfo = Res.data[j]; // 해당 블로그 정보
            this.userImages.push(this.serverURL +  Res.data[j].profileImg);
            break;
          }
        }
        // this.blogInfo = Response.data[0];
        // console.log(this.blogInfo)
        // console.log("누구인가");
        })
            if(this.$route.params.userId == Response.data[i].userId){
              this.check = true;
            }
        }
    })
    this.searchWord = this.word = this.$route.query.q;
  },
  methods : {
    setSearchWord(word){
      this.searchWord = this.word = word;
    },
    searchArticle(){
      this.searchWord = this.word;
      if(this.$route.fullPath.indexOf('articledetail') >= 0){
        this.$router.push(`/bloghome/${this.$route.params.userId}?q=${this.searchWord}`)
      }
    },
    home() {
      this.$router.push('/main');
    },
    boardnum(num) {
      this.$router.push('/bloghome/'+this.$route.params.userId+'/board/'+num);
      this.$router.go();
    },
    refresh() {
      this.$router.go();
    },
    myBlog(){
        // console.log('--------------------------------------------------------')
        if(this.$route.path.indexOf("bloghome/")){
          
           axios.get('/blog/searchbyuserid?userId=' + encodeURI(this.userInfo.userId))
          .then((Response)=>{
            // console.log(Response.data + "어느 사람의 블로그인지");
            this.searchInfo = Response.data;
            this.$store.dispatch('Re_blogInfo',this.searchInfo)
            axios.get('/blog/boardlist?blogNo=' + Response.data.blogNo)
            .then((Res)=>{
              // console.log(Res);
              this.BoardItems = Res.data;
              this.$store.dispatch('Re_boardInfo',Res.data)
            })
          }),
          axios.post('/user/list',{
            searchKey : "userId",
            searchValue : this.userInfo.userId
          }).then((Response)=>{
            // console.log(Response);
            // console.log("Hi");
            this.blogInfo = Response.data[0];

            // console.log(this.blogInfo.profileImg);

            this.userImage = this.serverURL + this.blogInfo.profileImg;
            
          })
          // console.log(this.searchInfo + "어느 사람의 블로그인지");
          // console.log(this.blogInfo.blogNo + "블로그의 정보");
        }
         this.$router.push(`/bloghome/${this.userInfo.userId}` )
    },
    follow() {
      axios.post(`/user/follow/`+ this.$store.getters.blogInfo.userNo).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 되었습니다.");
        this.$router.go();
      }).catch(()=>{
        alert("에러");
      })
    },
    unfollow(){
      axios.delete(`/user/follow/`+ this.$store.getters.blogInfo.userNo).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 삭제되었습니다.");
        this.$router.go();
      })
    },
    block(){
    },
    block_cancel(){
      axios.delete(`/user/block/`+ this.$store.getters.blogInfo.userNo).then((Response)=>{
        // console.log(Response);
        alert("차단이 해제되었습니다.");
        this.$router.go();
      })
    },
    Logout() {
      this.$router.push('/logout');
    },
    goblog(friendid){
      // this.$router.push('/bloghome/'+friendid);
      this.$router.push({ name: 'BoardDetail_friend', params: { userId: friendid, key: 'friend' }});
    },
    sendmessage(nickName){
        this.showModal = true
        this.hint = nickName+"에게"
        this.my_index = this.userInfo.userNo;
        this.your_index = this.blogInfo.userNo;
    },
    send_message(){
        this.showModal = false;
        axios.post('/user/sendmsg',{
          content : this.messageModel,
          messageTo : this.your_index
        }).then((Response)=>{
          this.messageModel='';
          // console.log(Response);
          alert("전송되었습니다.");
        }).catch((error)=>{
          // console.log(error);
          alert("전송에 실패했습니다.");
        })
      },
      cancel() {
        this.showModal=false;
        this.messageModel='';
      },
      searchAll(){
        this.$router.push({ name: 'BoardDetail_friend', params: { userId: this.blogInfo.userId, key: this.blogInfo.userNo }});
      }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    },
    blog_Info() {
      return this.$store.getters.blogInfo;
    },
    boardInfo() {
      return  this.$store.getters.boardInfo;
    },
    boardnumInfo() {
      return  this.$store.getters.boardnumInfo;
    },
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  watch:{
    treebabo: function(){
      setTimeout(() => {
        window.$(".sidebar-dropdown>a").click(function() {
          window.$(".sidebar-submenu").slideUp(200);
          if (
              window.$(this)
              .parent()
              .hasClass("active")
          ) {
            window.$(".sidebar-dropdown").removeClass("active");
            
            window.$(this)
              .parent()
              .removeClass("active");
          } else {
            window.$(".sidebar-dropdown").removeClass("active");
            window.$(this)
              .next(".sidebar-submenu")
              .slideDown(200);
            window.$(this)
              .parent()
              .addClass("active");
          }
        });
      }
      ,100);
      
      window.$("#close-sidebar").click(function() {
        if (window.$(".page-wrapper").hasClass("default")) {
          window.$("page-wrapper").removeClass("default")
        }
        window.$(".page-wrapper").removeClass("navon");
        window.$(".page-wrapper").addClass("navoff");
      });
      window.$("#show-sidebar").click(function() {
        if (window.$(".page-wrapper").hasClass("default")) {
          window.$("page-wrapper").removeClass("default")
        }
        window.$(".page-wrapper").removeClass("navoff");
        window.$(".page-wrapper").addClass("navon");
      });
    }
  }
}
</script>
[v-cloak]::before {
  content: 'loading...'
}
[v-cloak] > * { 
  display:none;
}


<style scoped>
@keyframes swing {
  0% {
    transform: rotate(0deg);
  }
  10% {
    transform: rotate(10deg);
  }
  30% {
    transform: rotate(0deg);
  }
  40% {
    transform: rotate(-10deg);
  }
  50% {
    transform: rotate(0deg);
  }
  60% {
    transform: rotate(5deg);
  }
  70% {
    transform: rotate(0deg);
  }
  80% {
    transform: rotate(-5deg);
  }
  100% {
    transform: rotate(0deg);
  }
}
@keyframes hiding {
  0% {
    left:0px;
  }
  
  100% {
    left:-260px;
  }
}
@keyframes sliding {
  100% {
    left:0px;
  }
  
  0% {
    left:-260px;
  }

}
@keyframes lefting {
  0% {
    padding-left: 260px;
    margin-right: 50px;
  }
  
  100% {
    padding-left: 0px;
    margin-right: 0px;
  }
}
@keyframes righting {
  100% {
    padding-left: 260px;
    margin-right: 50px;
  }
  
  0% {
    padding-left: 0px;
    margin-right: 0px;
  }
}
@media screen and (min-width: 768px) {
  .page-wrapper.default .page-content {
    padding-left: 260px;
    margin-right: 50px;
  }
  .page-wrapper.navon .page-content {
    animation: righting ease-in-out 0.5s 1 alternate;
    padding-left: 260px;
    margin-right: 50px;
  }
  .page-wrapper.navoff .page-content {
  animation: lefting ease-in-out 0.5s 1 alternate;
  padding-left: 0px;
  margin-right: 0px;
  }
}
.sidebar-wrapper .sidebar-menu ul li a:hover i::before {
  display: inline-block;
  animation: swing ease-in-out 0.5s 1 alternate;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown.active > a:after {
  transform: rotate(90deg);
  right: 17px;
}
html {
  -webkit-tap-highlight-color: transparent;
}
body {
  font-size: 0.9rem;
  display: block;
}
body {
  margin: 0;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-weight: 400;
  line-height: 1.5;
  color: #212529;
  text-align: left;
  background-color: #fff;
}
*, *::before, *::after {
  box-sizing: border-box;
  text-align: left;
}

.page-wrapper {
  height: 100vh;
}
.page-wrapper.navoff #sidebar {
  animation: hiding ease-in-out 0.5s 1 alternate;
  left: -260px;
}
.page-wrapper.navon #sidebar {
  animation: sliding ease-in-out 0.5s 1 alternate;
  left: 0px;
}
#show-sidebar {
  position: fixed;
  bottom: 10px;
  border-radius: 0 4px 4px 0px;
  font-size:16px;
  /* width: 35px;  */
  /* transition-delay: 0.3s; */
}
#show-sidebar:hover {
  background: rgba(176, 224, 230, 0.493);
  border-color: transparent;
}

.btn:not(:disabled):not(.disabled) {
  cursor: pointer;
}
.btn-sm, .btn-group-sm > .btn {
  padding: 0.5rem 1.25rem;
  font-size: 0.875rem;
  line-height: 1.5;
  /* font-weight: bold; */
}
.btn-dark {
  color: black;
  /* color: #818896; */
  /* background-color: #343a40;
  border-color: #343a40; */
  background-color: PowderBlue;
  border-color: white;
}
.btn {
  display: inline-block;
  font-weight: bold;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  user-select: none;
  /* border: 1px solid transparent; */
}
a {
  text-decoration: none;
}

.fa, .fas {
  font-weight: 900;
}
.fa, .far, .fas {
  font-family: Font Awesome\ 5 Free;
}
.fa, .fab, .fal, .far, .fas {
  -webkit-font-smoothing: antialiased;
  display: inline-block;
  font-style: normal;
  font-variant: normal;
  text-rendering: auto;
  line-height: 1;
}
.fa-bars:before {
  content: "\f0c9";
  /* color: #818896; */
  color: #818896;
}

.page-wrapper.navoff .sidebar-wrapper {
  left: 0px;
}
.chiller-theme .sidebar-wrapper {
  background: linear-gradient(0.73turn, white 10%, #EEEE);
  /* box-shadow: 1px 1px 4px grey; */
}
.sidebar-wrapper.offScroll {
  width: 260px;
  height: 100%;
  max-height: 100%;
  position: absolute;
  top: 280px;
  z-index: 999;
}
.sidebar-wrapper.onScroll {
  width: 260px;
  height: 100%;
  max-height: 100%;
  position: fixed;
  top: 0px;
  z-index: 999;
}

article, aside, figcaption, figure, footer, header, hgroup, main, nav, section {
  display: block;
}

.sidebar-content {
  max-height: calc(100% - 30px);
  height: calc(100% - 30px);
  overflow-y: auto;
  position: relative;
}
.sidebar-wrapper .sidebar-brand {
  padding: 10px 20px;
  display: flex;
  align-items: center;
}
.chiller-theme .sidebar-wrapper .sidebar-header .user-info .user-role, .chiller-theme .sidebar-footer>a, .chiller-theme .sidebar-wrapper .sidebar-menu ul li a, .chiller-theme .sidebar-wrapper .sidebar-search .input-group-text, .chiller-theme .sidebar-wrapper .sidebar-brand>p, .chiller-theme .sidebar-wrapper .sidebar-header .user-info .user-status {
  color: #818896;
}
.chiller-theme .sidebar-wrapper .sidebar-search input.search-menu {
  color: black;
}
.sidebar-wrapper .sidebar-brand > p {
  text-transform: uppercase;
  font-weight: bold;
  flex-grow: 1;
}
.sidebar-wrapper a {
  text-decoration: none;
}
.user-name {
  padding-top: 5px;
  color: #818896;
}
.page-wrapper.chiller-theme.navon #close-sidebar {
  color: #bdbdbd;
}
.sidebar-wrapper .sidebar-brand #close-sidebar {
  cursor: pointer;
  font-size: 20px;
}
.fa-times:before {
  content: "\f00d";
}

.chiller-theme .sidebar-wrapper .sidebar-header, .chiller-theme .sidebar-wrapper .sidebar-search, .chiller-theme .sidebar-wrapper .sidebar-menu {
  border-top: 1px solid #EEEE;
}
.sidebar-wrapper .sidebar-header {
  padding: 20px;
  overflow: hidden;
}

.sidebar-wrapper .sidebar-header .user-pic {
  float: left;
  width: 60px;
  height: 60px;
  padding: 2px;
  border-radius: 12px;
  margin-right: 15px;
  overflow: hidden;
  align-items: center;
  justify-content: center;
  /* box-shadow: 0px 0px 100px 1px rgba(204, 204, 204); */
}
.sidebar-wrapper .sidebar-header .user-pic img {
  object-fit: cover;
  height: 100%;
  width: 100%;
}
img {
  vertical-align: middle;
  border-style: none;
}

.chiller-theme .sidebar-wrapper .sidebar-menu ul li:hover>a, .chiller-theme .sidebar-wrapper .sidebar-menu .sidebar-dropdown.active>a, .chiller-theme .sidebar-footer>a:hover i {
  color: #a1a7b4;
}
.sidebar-wrapper .sidebar-header .user-info {
  float: left;
}
.sidebar-wrapper .sidebar-header .user-info > span {
  display: block;
}
.sidebar-wrapper .sidebar-header .user-info .user-role {
  font-size: 12px;
}
.sidebar-wrapper .sidebar-header .user-info .user-status {
  font-size: 11px;
  margin-top: 4px;
}
.sidebar-wrapper .sidebar-search > div {
  padding: 10px 20px;
}
.input-group {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  align-items: stretch;
  width: 100%;
}
.chiller-theme .sidebar-wrapper .sidebar-menu ul li a i {
  background: rgba(243, 243, 243, 0.933);
}
.chiller-theme .sidebar-wrapper .sidebar-search input.search-menu, .chiller-theme .sidebar-wrapper .sidebar-search .input-group-text {
  background: white;
}
.chiller-theme .sidebar-wrapper .sidebar-search input.search-menu {
  opacity: 0.5;
}
.chiller-theme .sidebar-wrapper .sidebar-search input.search-menu, .chiller-theme .sidebar-wrapper .sidebar-search .input-group-text {
  border-color: transparent;
  box-shadow: none;
}
.input-group > .form-control:not(:last-child), .input-group > .custom-select:not(:last-child) {
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
.input-group > .form-control, .input-group > .custom-select, .input-group > .custom-file {
  position: relative;
  flex: 1 1 auto;
  width: 1%;
  margin-bottom: 0;
}
.form-control {
  display: block;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
button, input {
  overflow: visible;
}
input, button, select, optgroup, textarea {
  margin: 0;
  font-family: inherit;
}
.input-group-append {
  margin-left: -1px;
}
.input-group-prepend, .input-group-append {
  display: flex;
}
.fa-search:before {
  content: "\f002";
  color: #c1c7cc;
}

.sidebar-wrapper .sidebar-menu {
  padding-bottom: 10px;
}
.sidebar-wrapper ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}
ul {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}
li {
  display: list-item;
  text-align: -webkit-match-parent;
}
.sidebar-wrapper ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}
.chiller-theme .sidebar-wrapper .sidebar-menu .header-menu span {
  color: #6c7b88;
}
.sidebar-wrapper .sidebar-menu .header-menu span {
  font-weight: bold;
  font-size: 14px;
  padding: 15px 20px 5px 20px;
  display: inline-block;
}
.sidebar-wrapper .sidebar-menu ul li a {
  display: inline-block;
  width: 100%;
  text-decoration: none;
  position: relative;
  padding: 8px 30px 8px 20px;
}
a:-webkit-any-link {
  cursor: pointer;
}
.sidebar-wrapper .sidebar-menu ul li a i {
  margin-right: 10px;
  font-size: 12px;
  width: 30px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  border-radius: 4px;
}
.badge-warning {
  color: #212529;
  background-color: #ffc107;
}
.badge-pill {
  padding-right: 0.6em;
  padding-left: 0.6em;
  border-radius: 10rem;
}
.badge {
  display: inline-block;
  padding: 0.25em 0.4em;
  font-size: 75%;
  font-weight: 700;
  line-height: 1;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown #no-sub:after {
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  content: "\f105";
  display: none;
  font-style: normal;
  font-variant: normal;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  text-align: center;
  background: 0 0;
  position: absolute;
  right: 15px;
  top: 14px;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown #yes-sub:after {
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  content: "\f105";
  display: inline-block;
  font-style: normal;
  font-variant: normal;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  text-align: center;
  background: 0 0;
  position: absolute;
  right: 15px;
  top: 14px;
}
.sidebar-wrapper .sidebar-menu .sidebar-submenu {
  display: none;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu ul {
  padding: 5px 0;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu li {
  padding-left: 25px;
  font-size: 13px;
}
.badge-success {
  color: #fff;
  background-color: #28a745;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu li a:before {
  content: "\f111";
  font-family: "Font Awesome 5 Free";
  font-weight: 400;
  font-style: normal;
  display: inline-block;
  text-align: center;
  text-decoration: none;
  -webkit-font-smoothing: antialiased;
  margin-right: 10px;
  font-size: 8px;
}
.sidebar-wrapper ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}
.fa-book:before {
  content: "\f02d";
  /* color: #a1a7b4; */
}
.fa-folder:before {
  content: "\f07b";
  /* color: #a1a7b4; */
}
.fa-code:before {
  content: "\f121";
  /* color: #a1a7b4; */
}

.chiller-theme .sidebar-footer {
  background: #EEEE;
  box-shadow: 0px -1px 5px rgba(224, 224, 224, 0.933);
  /* border-top: 1px solid #464a52; */
}
.sidebar-footer {
  position: fixed;
  width: 258px;
  /* position: absolute;
  width: 100%; */
  bottom: 0;
  display: flex;
}
.chiller-theme .sidebar-footer>a:first-child {
  border-left: none;
}
.sidebar-footer > a {
  flex-grow: 1;
  text-align: center;
  height: 30px;
  line-height: 30px;
  position: relative;
}
.fa-bell:before {
  content: "\f0f3";
}
.sidebar-footer > a .notification {
  position: absolute;
  top: 0;
}
.fa-envelope:before {
  content: "\f0e0";
}
.fa-bell {
  color:orange;
}
.fa-power-off {
  color:FireBrick;
}
.fa-cog:before {
  content: "\f013";
}
.chiller-theme .sidebar-footer>a:last-child {
  border-right: none;
}
.fa-power-off:before {
  content: "\f011";
}

::-webkit-scrollbar {
  width: 5px;
  height: 7px;
}
::-webkit-scrollbar-button {
  width: 0px;
  height: 0px;
}
::-webkit-scrollbar-corner {
  background: transparent;
}
::-webkit-scrollbar-thumb {
  background: #EEEE;
  border: 0px none #ffffff;
  border-radius: 0px;
}
::-webkit-scrollbar-track {
  background: transparent;
  border: 0px none #ffffff;
  border-radius: 50px;
}
.theme--light.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined) {
  background-color: white;
}
.v-btn--contained {
  box-shadow: none;
}
.topBanner:after {
  content: "";
  display: block;
  width: 80%;
  border-bottom: 5px solid black;
  margin: 0px auto;
}
.v-application--is-ltr .v-btn-toggle > .v-btn.v-btn:not(:first-child) {
  border-width: 0;
}
.container {
  padding-bottom: 450px;
}
</style>