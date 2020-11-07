<template>
  <div class="mainuser">
    <!-- 네브바 -->
    <nav-header></nav-header>
    <div id="board_body">

      <!-- select box , 검색-->
      <v-container id="search" fluid>
        <v-row align="center">
          <v-col class="d-flex" cols="3" sm="3">
            <v-select
              :items="items1"
              :value="items1[3]"
              v-model="value"
            ></v-select>
          </v-col>
          <v-col cols="7">
            <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="searchdata"></v-text-field>
          </v-col>
          <v-btn icon @click="searchdata"><v-icon>mdi-magnify</v-icon></v-btn>
        </v-row>
      </v-container>

      <b-tabs content-class="mt-3">
        <!-- 유저 -->
        <b-tab title="게시판" @click="tab_board" > 
        </b-tab>
        <b-tab title="작품" @click="tab_book"> 
        </b-tab>
        <b-tab title="블로그" @click="tab_blog" > 
        </b-tab>
        
        <b-tab title="유저" @click="tab_user" active> 
          <div class="container list-article">
            <div class="clearfix"></div>
            <div class="row article-wrapper bloc col-xs-12 col-xs-4">
            <template  v-for="(user,index) in users">
              <div class="col-xs-3" :key="user.userId" v-if="userInfo.userNo==user.userNo" style="width:250px;"> <!--본인 제외 -->
                <article>
                  <div class="img-wrapper"><img :src="userImage[index]"></div>
                  <br>
                  <!-- <h1>Blog. {{user.blogName}}</h1> -->
                  <h1>
                    {{랭킹이미지}}{{user.level}} 
                    <p>{{user.nickName}}</p> 
                    <!-- 차단이면 차단해제 -->
                    <!-- 팔로우 -->
                  </h1>
                  <br>
                  <p @click="goblog(index)">{{user.blogName}}</p>
                </article>
              </div>
              <div class="col-xs-3" :key="user.userId" v-if="userInfo.userNo!=user.userNo" style="width:250px;"> <!--본인 제외 -->
                <article>
                  <div class="img-wrapper"><img :src="userImage[index]"></div>
                  <br>
                  <!-- <h1>Blog. {{user.blogName}}</h1> -->
                  <h1>
                    {{랭킹이미지}}{{user.level}} 
                    <p>{{user.nickName}}</p> 
                    <!-- 차단이면 차단해제 -->
                    <!-- 팔로우 -->
                    <v-btn class="ml-2" x-small tile dense outlined color="primary" style="border-radius: 5px;" v-if="!following[index] && !block[index]" @click="follow(user.userNo,index)">
                      <v-icon x-small>mdi-account-plus</v-icon> Follow
                    </v-btn>
                    <!-- 팔로우삭제 버튼 [글작성자가 아닌 경우에만] -->
                    <v-btn class="ml-2" x-small tile dense outlined color="error" style="border-radius: 5px;" v-if="following[index] && !block[index]" @click="unfollow(user.userNo,index)">
                      <v-icon x-small>mdi-account-plus</v-icon> UnFollow 
                    </v-btn>
                    <!-- 블록해제 버튼 [글작성자가 아닌 경우에만] -->
                    <v-btn class="ml-2" x-small tile dense outlined color="warning" style="border-radius: 5px;" v-if="block[index]" @click="unblock(user.userNo,index)">
                      <v-icon x-small>mdi-account-plus</v-icon> 차단해제
                    </v-btn>
                    <v-btn class="ml-2" x-small tile dense outlined color="primary" style="border-radius: 5px;" @click="sendmessage(user)">
                      <v-icon x-small>mdi-comment-processing-outline</v-icon> 쪽지
                    </v-btn>
                  </h1>
                  <br>
                  <p @click="goblog(index)">{{user.blogName}}</p>
                </article>
              </div>
            </template>
            </div>
          </div>
           <!-- 페이지네이션 -->
          <v-col cols="12">
            <v-container id="page" class="max-width" @click="clickpage">
              <v-pagination
                v-model="page"
                :circle="circle"
                :disabled="disabled"
                :length="msglength"
                :next-icon="nextIcon"
                :prev-icon="prevIcon"
                :page="page"
                total-visible="10"
              ></v-pagination>
            </v-container>
          </v-col>
        </b-tab>
      </b-tabs>

      <!-- 쪽지모달 -->
      <modal v-if="showModal" @close="showModal = false">
        <transition name="modal">
          <div class="modal-mask">
            <div class="modal-wrapper">
              <v-container fluid>
                <v-row style="justify-content:center;">
                  <v-col cols="12" md="8">
                    <v-sheet elevation="12" class="pa-12">
                      <v-text-field
                        v-model="model"
                        :label="label"
                        :hint="hint"
                        :placeholder="placeholder"
                        :outlined="outlined"
                        :solo="solo"
                        :persistent-hint="persistentHint"
                        :counter="counterEn ? counter : false"
                        maxlength="90"
                      ></v-text-field>
                      <button class="modal-default-button" @click="send_message()">
                        전송
                      </button>
                      <button class="modal-default-button" @click="cancel()">
                        취소
                      </button>
                    </v-sheet>
                  </v-col>
                </v-row>
              </v-container>
            </div>
          </div>
        </transition>
      </modal> 
    </div>
    <!-- 푸터 -->
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import axios from 'axios'
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
export default {
  name: "Mainuser", 
    data() {
      return{
        items1: ['게시판', '작품', '블로그', '유저'],
        selected1: '1',
        rows: 100,
        page: this.$route.params.page,
        totalVisible: 10,
        users : [],
        userImage : [],
        search:this.$route.params.searchValue,
        go_blog : [],
        following : [],
        placeholder: '내용을 입력하시오.',
        block: [],
        showModal : false,
        model: '',
        hint : "",
        my_index : "",
        your_index : "",
        msglength: 1,
        value : "유저",
      }
    },
  created() {
    this.usercount();
    this.get_user()
    this.check_follow()
    this.check_block()
    this.search = "";
  },
  components : {
    NavHeader,
    NavFooter
  },
  computed: { 
    serverURL(){
      return this.$store.getters.serverURL;
    },
    userInfo() {
      return this.$store.getters.userInfo;
    }
  },
  methods : {
      tab_board() {
          this.$router.push({ name: 'Mainboard', params: { searchValue: this.$route.params.searchValue, page:1, type:0 ,key: this.search+this.page }});
      },
      tab_book() {
          this.$router.push({ name: 'Mainbook', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
      },
      tab_blog() {
        this.$router.push({ name: 'Mainblog', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
      },
      tab_user() {
          this.$router.push({ name: 'Mainuser', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
      },
      get_user() {
        if(this.search=="　"){
          axios.get(`/user/searchlist/ /${(this.$route.params.page-1)*12}`)
        // axios.get(`/user/searchlist/bjh/1`)
        .then((Response)=>{
          this.user = []
          this.userImage = []
        // console.log(this.$route.params.searchValue)
        // console.log(this.$route.params.page)
          // this.users = Response.data;
          for(let i=0;i<Response.data.length;i++){
            this.users.splice(i,1,Response.data[i]);
            Response.data[i].userId
            this.userImage.splice(i,1,this.serverURL + Response.data[i].profileImg);
            axios.post('/user/list', {
              searchKey : "userId",
              searchValue : Response.data[i].userId
            }).then((Res)=>{
              for(let j=0;j<Response.data.length;j++){
                if(Res.data[j].userId==Response.data[i].userId){
                  this.blogInfo = Res.data[j]; // 해당 블로그 정보
                  // this.blogInfo.splice(i,0,Res.data[j])
                  // this.go_blog[i]=Res.data[j].userId;
                  this.go_blog.splice(i,0,Res.data[j].userId)
                  break;
                }
              }
            })
            axios.get('/blog/blogs?userNo='+ Response.data[i].userNo)
            .then((Res)=>{
              if(Res.data[0]!=null){
                this.users[i].blogName = "Blog. "+ Res.data[0].blogName;
              }else{
                this.users[i].blogName = "";
              }
              // console.log(Res.data[0].blogName);
            })
          }
        })
        }else{
          axios.get(`/user/searchlist/${this.$route.params.searchValue}/${(this.$route.params.page-1)*12}`)
          // axios.get(`/user/searchlist/bjh/1`)
          .then((Response)=>{
            this.user = []
            this.userImage = []
          // console.log(this.$route.params.searchValue)
          // console.log(this.$route.params.page)
            // this.users = Response.data;
            for(let i=0;i<Response.data.length;i++){
              this.users.splice(i,1,Response.data[i]);
              Response.data[i].userId
              this.userImage.splice(i,1,this.serverURL + Response.data[i].profileImg);
              axios.post('/user/list', {
                searchKey : "userId",
                searchValue : Response.data[i].userId
              }).then((Res)=>{
                for(let j=0;j<Response.data.length;j++){
                  if(Res.data[j].userId==Response.data[i].userId){
                    this.blogInfo = Res.data[j]; // 해당 블로그 정보
                    // this.blogInfo.splice(i,0,Res.data[j])
                    // this.go_blog[i]=Res.data[j].userId;
                    this.go_blog.splice(i,0,Res.data[j].userId)
                    break;
                  }
                }
              })
              axios.get('/blog/blogs?userNo='+ Response.data[i].userNo)
              .then((Res)=>{
                if(Res.data[0]!=null){
                  this.users[i].blogName = "Blog. "+ Res.data[0].blogName;
                }else{
                  this.users[i].blogName = "";
                }
                // console.log(Res.data[0].blogName);
              })
            }
          })

        }
      },
      usercount() {
        if(this.search == "　"){
          axios.get('/user/searchlistcount/' + encodeURI(" "))
        .then((Response)=>{
          this.msglength = parseInt(Math.ceil(Response.data/12))
        })
        }else{
          axios.get('/user/searchlistcount/'+ encodeURI(this.$route.params.searchValue))
          .then((Response)=>{
            this.msglength = parseInt(Math.ceil(Response.data/12))
          })

        }
      },
      check_follow(){
          this.get_user()
          axios.get('/user/following')
          .then((Response)=>{
          // console.log(Response);
          for(let i=0;i<Response.data.length;i++){
            for(let j=0;j<this.users.length;j++){
              if(this.users[j].userNo==Response.data[i].following){
                this.following[j]=true;
                break;
              }
            }
          }
        })
      },
      check_block(){
          axios.get('/user/block')
          .then((Response)=>{
          // console.log(Response);
          for(let i=0;i<Response.data.length;i++){
            for(let j=0;j<this.users.length;j++){
              if(this.users[j].userNo==Response.data[i].blocking){
                this.block[j]=true;
                break;
              }
            }
          }
        })
      },
      goblog(index){
      this.$router.push("/bloghome/"+this.go_blog[index]);
    },
    follow(number,index) {
      axios.post(`/user/follow/`+ number).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 되었습니다.");
        this.check_follow();
        this.following[index] = true;
      })
    },
    unfollow(number,index) {
      axios.delete(`/user/follow/`+ number).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 삭제되었습니다.");
        this.check_follow();
        this.following[index] = false;
      })
    },
    unblock(number,index) {
      axios.delete('/user/block/' + number).then((Response)=>{
        // console.log(Response);
        alert("차단해제되었습니다.");
        this.check_follow();
        this.block[index]=false;
      })
    },
    sendmessage(data){
        // alert(this.userInfo.userNo);
        // alert(data.userNo);
        this.showModal = true
        this.hint = data.nickName+"에게"
        this.my_index = this.userInfo.userNo;
        this.your_index = data.userNo;
        this.model='';
    },
    send_message(){
        this.showModal = false;
        axios.post('/user/sendmsg',{
          content : this.model,
          messageTo : this.your_index
        }).then((Response)=>{
          this.model='';
          // console.log(Response);
          alert("전송되었습니다.");
        }).catch((error)=>{
          // console.log(error);
          alert("전송에 실패했습니다.");
        })
      },
      cancel(){
        this.showModal=false;
        this.model='';
      },
      clickpage() {
        this.$router.push({ name: 'Mainuser', params: { searchValue: this.$route.params.searchValue, page:this.page ,key: this.$route.params.searchValue+this.page }});
      },
      searchdata(){
        if(this.serach == "" || !this.search){
          this.search ="　"
        }
        if(this.value==="게시판"){
          this.$router.push({ name: 'Mainboard', params: { searchValue: this.search, page:1, type:0 ,key: this.search+this.page }});
        }else if(this.value==="작품"){
          this.$router.push({ name: 'Mainbook', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }else if(this.value==="블로그"){
          this.$router.push({ name: 'Mainblog', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }else if(this.value==="유저"){
          this.$router.push({ name: 'Mainuser', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }
        this.search =""
      }
  }
};
</script>

<style scoped>
#board_body {
  width: 85%;
  margin: 0 100px;
}
#search {
  margin: 0 auto;
}
#card {
  padding: 0 20px;
}
p {
  margin-bottom: 0px;
}
hr {
  margin: 16px 20px;
  padding: 0 20px;
  border: 0;
  height: 2px;
  background: #ccc;
}
#btn_select {
  display: inline-block;
}
#btn {
  float: left;
  margin: 10px;
}
#select {
  float: right;
  padding: 0;
}
.cols {
  padding: 0 0px;
}
#tab {
  display: inline;
}
#left {
  padding: 0 0px;
  width: 50%;
  float: left;
  box-sizing: border-box;
}
#right {
  padding: 0 0px;
  width: 50%;
  float: right;
  box-sizing: border-box;
}
#board_title {
  margin: 0 0px;
}
#page {
  text-align: center;
  margin: 0 auto;
  width: 100%;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}

@import url(https://fonts.googleapis.com/css?family=Pacifico);

.article-wrapper {
  -moz-transition: width 0.4s;
  -o-transition: width 0.4s;
  -webkit-transition: width 0.4s;
  transition: width 0.4s;
}

article {
  position: relative;
  padding-left: 180px;
  padding-right: 75px;
  height: 100px;
  margin-bottom: 30px;
  background-color: #ECEFF1;
  -moz-border-radius: 100px;
  -webkit-border-radius: 100px;
  border-radius: 100px;
  cursor: pointer;
}

article .img-wrapper {
  position: absolute;
  top: -10px;
  left: -10px;
  -moz-border-radius: 100px;
  -webkit-border-radius: 100px;
  border-radius: 100px;
  background-color: #C5CAE9;
  padding: 10px;
  z-index: 1000;
  -moz-transition: all 0.2s;
  -o-transition: all 0.2s;
  -webkit-transition: all 0.2s;
  transition: all 0.2s;
}
article .img-wrapper img {
  -moz-border-radius: 100px;
  -webkit-border-radius: 100px;
  border-radius: 100px;
  -moz-transition: all 0.2s;
  -o-transition: all 0.2s;
  -webkit-transition: all 0.2s;
  transition: all 0.2s;
  width: 100px;
  height: 100px;
}
article h1 {
  height: 75px;
  line-height: 75px;
  font-size: 24px;
  border-bottom: 2px solid #cde;
  color: #000000;
  -moz-transition: all 0.4s;
  -o-transition: all 0.4s;
  -webkit-transition: all 0.4s;
  transition: all 0.4s;
}
article p {
  color: #616161;
}
article a {
  position: absolute;
  display: block;
  top: 0;
  left: 0;
  height: 100%;
  line-height: 100px;
  text-align: center;
  width: 100%;
  background-color: #D1C4E9;
  font-size: 48px;
  font-style: italic;
  opacity: 0;
  color: #FF9E80 !important; 
  -moz-transition: all 0.4s;
  -o-transition: all 0.4s;
  -webkit-transition: all 0.4s;
  transition: all 0.4s;
  text-decoration: none !important;
  -moz-border-radius: 150px;
  -webkit-border-radius: 150px;
  border-radius: 100px;
  font-family: "Pacifico";
  text-shadow: 0 1px 1px #aaa;
}
article:hover .img-wrapper {
  padding: 7px;
  -moz-transform: rotate(7deg);
  -ms-transform: rotate(7deg);
  -webkit-transform: rotate(7deg);
  transform: rotate(7deg);
}
article:hover .img-wrapper img {
  width: 100px;
}
article:hover a {
  opacity: 1;
}

.bloc article {
  padding: 10px;
  height: 150px;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  margin-top: 75px;
}
.bloc article .img-wrapper {
  position: relative;
  top: -85px;
  width: 120px;
  left: inherit;
  margin: auto;
  margin-bottom: -100px;
}
.bloc article h1 {
  font-size: 14px;
  height: 37.5px;
  line-height: 37.5px;
}
.bloc article p {
  font-size: 12px;
}
.bloc article a {
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  line-height: 255px;
}
/* .mainuser{
  width: 1560px;
} */
</style>