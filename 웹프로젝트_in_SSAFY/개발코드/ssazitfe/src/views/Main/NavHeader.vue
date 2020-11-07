<template>
  <div id="navheader">
    <header class="header">
      <v-toolbar>
        <!-- 로그인 -->
        <div v-if="userInfo!=null && userInfo.userNo>-1" id="login">
          <!-- 메인로고 -->
          <router-link to="/main"><img id="logo" :src="logo"></router-link>
          <input class="menu-btn" type="checkbox" id="menu-btn" />
          <label class="menu-icon" for="menu-btn"><span class="navicon"></span></label>
          <ul class="menu">
            <div>   
              <!-- MY블로그없을때 -->
              <li style="margin-top:5px;"><div v-if="userInfo.blogNo==0" @click="blogcreate">
                <b-button variant="link" style="text-decoration: none;">
                  <router-link to="/blogcreate" style="text-decoration: none; color:black">블로그 생성</router-link>
                </b-button>
              </div></li>
              <!-- MY블로그있을때 -->
              <li><div v-if="userInfo.blogNo!=0" @click="myblog">
                <b-button variant="link" style="text-decoration: none;">
                  <router-link :to="{path: `/bloghome/${userInfo.userId}`}" style="text-decoration: none; color:black"><v-icon large color="blue lighten-1">mdi-home</v-icon>My 블로그</router-link>
                </b-button>
              </div></li>

              <!-- 랭킹 -->
              <!-- <div class="my-3">
                <b-button id="popover-reactive-1" variant="link" style="text-decoration: none; color:black">
                  <v-icon large color="yellow darken-2">mdi-trophy</v-icon>
                </b-button>
              </div>
              <b-popover
                target="popover-reactive-1"
                triggers="hover"
                placement="bottom"
              >
              <template v-slot:title>
                <div @click="goblog(userInfo.userId)"><v-avatar><img :src="serverURL + userInfo.profileImg"></v-avatar> {{userInfo.nickName}}님은 현재 {{userInfo.userLevel}}입니다</div>
              </template>
                <v-btn text><router-link to="/rank" style="text-decoration: none; color:black">랭킹보기</router-link></v-btn>
                <v-btn text @click="showPopup">계급표</v-btn>
              </b-popover> -->

              <!-- 이웃보기 -->
              <li>
                <div class="my-3">
                  <b-button id="popover-reactive-2" variant="link" style="text-decoration: none; color:black">
                    <v-icon large color="deep-purple lighten-3">mdi-account-multiple</v-icon>
                  </b-button>
                </div>
                <b-popover
                  target="popover-reactive-2"
                  triggers="hover"
                  placement="bottom"
                >
                <template>
                  <h6 class="mt-1 mb-0">이웃 목록</h6>
                    <hr class="mt-1">
                    <div v-for="(friend,index) in following" :key="friend.userNo">
                      <div v-if="index<8">
                        <button class="mb-2" cols="8" @click="goblog(friend.userId)">
                          <div v-if="index%2==0">
                            <v-avatar size="40" class="mr-2">
                              <img :src="userImage[index]">
                            </v-avatar>
                            <span style="font-size:16px; font-weight:bold;">{{friend.nickName}}</span>
                            <span style="font-size:16px; color:grey;">({{friend.userId}})</span>
                          </div>
                        </button>
                        <button class="mb-2" cols="8" @click="goblog(friend.userId)">
                          <div v-if="index%2==1">
                            <v-avatar size="40" class="mr-2">
                              <img :src="userImage[index]">
                            </v-avatar>
                            <span style="font-size:16px; font-weight:bold;">{{friend.nickName}}</span>
                            <span style="font-size:16px; color:grey;">({{friend.userId}})</span>
                          </div>
                        </button>
                        <br>
                      </div>
                    </div>
                  </template>
                  <div class="py-1 px-5" style="background-color:black; border-radius:5px;">
                    <router-link to="/friends" style="text-decoration:none; color:white; margin:0px auto;">
                      이웃 더보기
                      <v-icon small style="color:white;">mdi-arrow-right</v-icon>
                    </router-link>
                  </div>
                </b-popover>
              </li>

              <!-- 즐겨찾기 -->
              <li>
                <div class="my-3">
                  <b-button id="popover-reactive-3" variant="link" style="text-decoration: none; color:black">
                    <v-icon large color="yellow darken-2">mdi-star</v-icon>
                  </b-button>
                </div>
                <b-popover
                  target="popover-reactive-3"
                  triggers="hover"
                  placement="bottom"
                >
                  <template>
                    <h6 class="mt-1 mb-0">즐겨찾기 바로가기</h6>
                    <hr class="mt-1">
                    <div v-for="(Fav,index) in bookFavList" :key="Fav">
                      <div v-if="index<4">
                        <button cols="8" @click="gobook(Fav)">
                          <p>ο {{Fav.bookTitle}}</p>
                        </button>
                        <br>
                      </div>
                    </div>
                    <div v-for="(Fav,index) in articleFavList" :key="Fav">
                      <div v-if="index<4">
                        <button cols="8" @click="goboard(Fav,index)">
                          <p>ο {{Fav.articleTitle}}</p>
                        </button>
                        <br>
                      </div>
                    </div>
                </template>
                <div style="background-color:black; border-radius:5px; padding:4px 70px;">
                  <router-link to="/bookmark" style="text-decoration:none; color:white; margin:0px auto;">
                    목록보기
                    <v-icon small style="color:white;">mdi-arrow-right</v-icon>
                  </router-link>
                </div>
              </b-popover>
            </li>

              <!-- 알림 -->
              <li>
                <div class="my-3">
                  <b-button id="popover-reactive-5" variant="link" style="text-decoration:none; color:black;">
                    <v-icon large color="yellow darken-4" @click="$router.push('/myalarm')">mdi-bell</v-icon>
                  </b-button>
                </div>
              </li>

              <!-- 쪽지 -->
              <li>
                <div @click="messages">
                  <b-button variant="link" style="text-decoration: none;">
                    <router-link to="/messages">
                      <i class="fa fa-envelope fa-2x"></i>
                    </router-link>
                  <!-- <router-link to="/messages" style="text-decoration: none; color:black"><v-icon large color="blue darken-2">mdi-message-text</v-icon></router-link> -->
                  </b-button>
                </div>
              </li>

              <!-- 관리 -->
              <li v-if="is_admin == true" class="mt-1">
                <div>
                  <v-btn>
                    <router-link to="/managepage" style="text-decoration:none;">
                      <i class="fas fa-wrench fa-2x pt-3" style="color:grey;"></i>
                    </router-link>
                  </v-btn>
                </div>
              </li>

              <!-- 마이페이지 -->
              <li>
                <div class="my-3">
                  <b-button id="popover-reactive-4" variant="link" style="text-decoration: none; color:black; margin-top:3px;">
                    <v-avatar style="width:30px;height:30px;">
                        <img :src="serverURL + userInfo.profileImg">
                      </v-avatar>
                    {{ userInfo.userName }} 님
                  </b-button>
                </div>
                <b-popover
                  target="popover-reactive-4"
                  triggers="hover"
                  placement="bottom"
                >
                  <template v-slot:title>
                    <router-link to="/mypage" style="text-decoration: none; color:black;">
                      <v-avatar>
                        <img :src="serverURL + userInfo.profileImg">
                      </v-avatar>
                      <b> {{ userInfo.userName }}</b>님
                    </router-link>
                    <v-btn small rounded outlined color="info" @click="Logout" style="margin:0 0 0 10px;">로그아웃</v-btn>
                  </template>
                </b-popover>
              </li>
            </div>     
          </ul>

          <!-- 비로그인 -->
        </div>
        
        <div v-else id="non_login">
          <!-- 메인로고 -->
          <router-link to="/main"><img id="logo" :src="logo"></router-link>
          <input class="menu-btn" type="checkbox" id="menu-btn" />
          <label class="menu-icon" for="menu-btn"><span class="navicon"></span></label>
          <ul class="menu">
            <div>
              <li>
                <b-button variant="link" style="text-decoration: none;">
                  <router-link to="/login" style="text-decoration: none; color:black">로그인</router-link>
                </b-button>
              </li>
              <li>
                <b-button variant="link" style="text-decoration: none;">
                  <router-link to="/menu" style="text-decoration: none; color:black">회원가입</router-link>
                </b-button>
              </li>
            </div>
          </ul>
        </div>
      </v-toolbar>
    </header>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "NavHeader",
  data() {
    return {
      logo: require("../images/tempgif.gif"),
      logo2: require("../images/logo2.png"),
      logo3: require("../images/logo3.png"),
      following : [],
      userImage : [],
      is_admin : false,
      // showRank : false,
      // ranking: [
      //   { rank: "9ZIT", content1: "가입 후 막 활동을 시작하는 멤버", content2: "자동등업 : 경험치 0이상 40미만"},
      //   { rank: "8ZIT", content1: "SSAZIT 일반 멤버", content2: "자동등업 : 경험치 40이상 100미만"},
      //   { rank: "7ZIT", content1: "SSAZIT 성실 멤버", content2: "자동등업 : 경험치 100이상 200미만"},
      //   { rank: "6ZIT", content1: "SSAZIT 우수 멤버", content2: "자동등업 : 경험치 200이상 500미만"},
      //   { rank: "5ZIT", content1: "SSAZIT 특별 멤버", content2: "자동등업 : 경험치 500이상 1000미만"},
      //   { rank: "4ZIT", content1: "SSAZIT VIP 멤버", content2: "자동등업 : 경험치 1000이상 10000미만"},
      //   { rank: "SSAZIT", content1: "SSAZIT 매니저"},
      // ],
      bookFavList : [],
      articleFavList : [],
      articleFavuserNoList : []
    };
  },
  created() {
    if(this.userInfo){
      axios.get('/user/following')
      .then((Response)=>{ 
        // console.log(Response.data);
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
              this.blogInfo = Res.data[j]; // 해당 블로그 정보
              this.userImage.splice(i,1,this.serverURL + Res.data[j].profileImg);
              break;
            }
          }
      })
        }
        
      })

    this.getFavoriteArticles();
    this.getFavoriteBooks();
    }

  },
 
  
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    },
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  methods: {
    Logout() {
      this.$router.push('/logout');
    },
    blogcreate(){
      this.$router.push("/blogcreate");
    },
    myblog() {
      this.$router.push({path: `/bloghome/${this.userInfo.userId}`});
    },
    rank(){
      this.$router.push("/rank");
    },
    messages(){
      this.$router.push("/messages");
    },
    goblog(friendid){
      // this.$router.push('/bloghome/'+friendid);
      this.$router.push({ name: 'BoardDetail_friend', params: { userId: friendid, key: 'friend' }});
    },
    showPopup() { 
      window.open("http://127.0.0.1:5500/rank.html", "rank", "width=600, height=400, left=100, top=50"); 
    },
    gobook(data){
      this.$router.push('/bloghome/' + data.userId + '/bookdetail/' + data.bookNo)
    },
    goboard(data,index){
      // this.$router.push('/bloghome/' + this.articleFavuserNoList[index].userId + '/articledetail/' + data.articleNo)
      this.$router.push({ name: 'BlogHome', params: { userId: this.articleFavuserNoList[index].userId, key: this.articleFavuserNoList[index].userId+'friend' }})
    },
    getFavoriteArticles(){
      axios.get(`/article/articlefavorites?userNo=` + this.userInfo.userNo + '&limitStart=0&limitEnd=8')
      .then(({data}) => {
        for(let i=0;i<data.length;i++){
          // console.log(data[i])
          this.articleFavList.splice(i,1,data[i])
          axios.get(`/user/listone/${data[i].userNo}`)
          .then((Res)=>{
            this.articleFavuserNoList.splice(i,1,Res.data)
          })
        }
      })
    },
    getFavoriteBooks(){
      axios.get(`/book/bookfavorites?userNo=` + this.userInfo.userNo + '&limitStart=0&limitEnd=8')
      .then(({data}) => {
        for(let i=0;i<data.length;i++){
          axios.get(`/book/book/${data[i].bookNo}`)
          .then((Res)=>{
            this.bookFavList.splice(i,1,Res.data.book)
          })
        }
      })
    },
  }
};

</script>

<style scoped>
body {
  margin: 0;
  font-family: Helvetica, sans-serif;
  background-color: #fff;
}

/* header */

.header {
  background-color: #fff;
  box-shadow: 1px 1px 4px 0 rgba(0,0,0,.1);
  width: 1540px;
  z-index: 3;
}

.header ul {
  margin: 0;
  padding: 0;
  list-style: none;
  overflow: hidden;
  background-color: #fff;
}


.header .logo {
  display: block;
  float: left;
  font-size: 2em;
  padding: 10px 20px;
  text-decoration: none;
}

/* menu */

.header .menu {
  clear: both;
  max-height: 0;
  transition: max-height .2s ease-out;
}

.header .menu .ul {
  bottom: 5px;
}

/* menu icon */

.header .menu-icon {
  cursor: pointer;
  display: inline-block;
  /* float: right; */
  padding: 15px 20px;
  position: relative;
  user-select: none;
}

.header .menu-icon .navicon {
  background: #333;
  display: block;
  height: 2px;
  position: relative;
  transition: background .2s ease-out;
  width: 18px;
}

.header .menu-icon .navicon:before,
.header .menu-icon .navicon:after {
  background: #fff;
  content: '';
  display: block;
  height: 100%;
  position: absolute;
  transition: all .2s ease-out;
  width: 100%;
}

.header .menu-icon .navicon:before {
  top: 5px;
}

.header .menu-icon .navicon:after {
  top: -5px;
}

/* menu btn */

.header .menu-btn {
  display: none;
}

.header .menu-btn:checked ~ .menu {
  max-height: 240px;
}

.header .menu-btn:checked ~ .menu-icon .navicon {
  background: transparent;
}

.header .menu-btn:checked ~ .menu-icon .navicon:before {
  transform: rotate(-45deg);
}

.header .menu-btn:checked ~ .menu-icon .navicon:after {
  transform: rotate(45deg);
}

.header .menu-btn:checked ~ .menu-icon:not(.steps) .navicon:before,
.header .menu-btn:checked ~ .menu-icon:not(.steps) .navicon:after {
  top: 0;
}

/* 48em = 768px */

@media (min-width: 48em) {
  .header li {
    float: left;
  }
  .header li a {
    padding: 20px 0px;
  }
  .header .menu {
    clear: none;
    float: right;
    max-height: none;
  }
  .header .menu-icon {
    display: none;
  }
}

li {
  margin: 0 0px;
}
div {
  display: inline;
}
#logo {
  position: fixed;
  height: 50px;
  top: 5px;
  left: 100px;
  outline: none;
}
#friend {
  position: fixed;
  top: 10px;
  left: 200px;
}
#login {
  position: fixed;
  top: 7px;
  right: 100px;
}
#non_login {
  position: fixed;
  top: 10px;
  right: 100px;
}
#right {
  float: right;
  width: 100%;
}
.v-btn--contained {
  box-shadow: none;
}
.theme--light.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined) {
  background-color: white;
}
.v-btn:not(.v-btn--round).v-size--default {
  height: 0px;
  min-width: 0px;
  padding: 0 16px;
}
</style>