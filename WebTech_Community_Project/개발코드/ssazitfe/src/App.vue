<template>
  <v-app>
    <div id="mainpage">
      <router-view :key="$route.fullPath"></router-view>
      <!-- 알림창 -->

      <div id="noti" v-if="notiOn" class="py-3 px-8 noti-hide" style="position:fixed; bottom:0px; right:0px; background-color:black; border-radius:10px;">
        <v-row class="mb-3" style="border-bottom:1px solid grey;">
          <p class="mb-0 mx-1" align="left" style="color:white;" @click="$router.push('/myalarm')">알림</p>
          <v-spacer></v-spacer>
          <div style="color:green; cursor:pointer;" v-if="noti.notiType=='message'" @click="showMessage(noti)">
            쪽지보기
            <i class="fa fa-envelope"></i>
          </div>
        </v-row>
        <v-row class="py-0 my-0">
          <v-col class="py-0 my-0">
            <p class="py-0 my-0" style="color:white;">{{noti.notiMessage}}</p>
            <p class="py-0 my-0" align="left" style="color:#EEEE;">{{noti.nickName}}</p>
          </v-col>
          <a href="#" @click.prevent="closeNoti" class="p-0 ml-0 my-0 mr-3" style="text-decoration:none;">
            <v-icon small color="grey">mdi-close</v-icon>
          </a>
        </v-row>
      </div>

      <div id="messageModal" v-if="showModal" @close="showModal = false">
        <transition name="modal">
          <div class="modal-mask">
            <div class="modal-wrapper">
              <v-container fluid>
                <v-row style="justify-content:center;">
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
                        v-model="message.content"
                        :hint="message.nickName"
                        :placeholder="placeholder"
                        maxlength="90"
                        clearable
                        counter
                        class="mb-5"
                      ></v-text-field>
                      <div class="pa-3" style="background-color:#EEEE; border-radius:5px;">
                        <div>
                          <span style="font-weight:bold">To.</span>
                          <span>  {{ message.nickName }}</span>
                        </div>
                        <div class="my-3">
                          {{ noti.messageContent }}
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
    </div>
  </v-app>
</template>

<script>
import axios from 'axios';
export default {
  name : 'Main',
  components: {
  },
  data(){
    return{
      notiOn: true,
      type: '',
      showModal : false,
      hint : "누구에게",
      placeholder: '내용을 입력하시오.',
      messageModel: '',
      message:'',
    }
  },
  created(){
    // axios.defaults.baseURL = 'http://i3d104.p.ssafy.io/'; //6이 우현이형 7이 용수
    axios.defaults.baseURL = 'http://localhost/9999';
    //페이지가 새로 로딩되면 vue-session에서 jwt값을 다시 받아오기
    const jwt = this.$session.get('jwt');
    // console.log(jwt);
    //axios의 default헤더로 Authorization설정후 헤더값으로 jwt설정
    if(jwt){
        axios.defaults.headers.common['Authorization'] = jwt;

        //jwt값을 디코딩 후 userId, no, type을 vuex에 다시 저장 
        axios.get('/user/mypage').then((Response)=>{
          this.$store.dispatch('Re_userInfo',Response.data); 
          this.$store.dispatch('connectWS', Response.data.userNo);
        })
        // console.dir(this.$store);
        // console.dir(axios.defaults.headers);

    }
    axios.defaults.withCredentials = true;//CORS 요청시에도 클라이언트의 쿠키값 전달
  },
  methods:{
    showMessage(noti){
      this.showModal = true;
      this.message = {
        messageTo : noti.sender,
        nickName : noti.nickName,
        content: ''
      };
    },
    cancel() {
        this.showModal=false;
        this.message.content='';
    },
    send_message(userNo){
        this.showModal = false;
        axios.post('/user/sendmsg',{
          content : this.message.content,
          messageTo : this.message.messageTo   
          })
          .then((Response)=>{
          this.message.content='';
          // console.log(Response);
          alert("전송되었습니다.");
        }).catch((error)=>{
          // console.log(error);
          alert("전송에 실패했습니다.");
        })
    },
    closeNoti(){
      this.hide();
    },
    show(){
      this.notiOn = true;
      setTimeout(() => {
        let noti = document.getElementById('noti');
        if(noti){
          noti.classList.remove("noti-hide");
          noti.classList.remove("noti-fadeout");
          noti.classList.add("noti-fadein");
        }
        else{
          this.show();
        }
      }, 100)
    },
    hide(){
      let noti = document.getElementById('noti');
      noti.classList.remove("noti-fadein")
      noti.classList.add("noti-fadeout")
      setTimeout(() => {
        this.notiOn = false;
      }, 5000)
    }
  },
  computed:{
    noti(){
      return this.$store.getters.noti;
    },
    userInfo(){
      return this.$store.getters.userInfo;
    }
  },
  watch:{
    noti: function(){
      this.type = 'noti';
      this.show();
      setTimeout(this.hide ,5000)
    }
  }
}
</script>

<style>
@font-face {
  font-family: 'paybooc';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Bold.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'BBTreeGR';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGR.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'Arita-dotum-Medium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Arita-dotum-Medium.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@keyframes fadeout {
  0% {
    opacity: 0.7;
  }
  100% {
    opacity: 0;
    display: none;
  }
}
@keyframes fadein {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 0.7;
    display: none;
  }
}

* {
  /* font-family: '페이북', 'paybooc', 'paybooc'; */
  /* font-family: '유토이미지고딕', 'BBTreeGR', 'BBTreeGR'; */
  font-family: '아리따돋움', 'Arita-dotum-Medium', 'Arita-dotum-Medium';
}
h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
  /* font-family: '페이북', 'paybooc', 'paybooc'!important; */
  /* font-family: '유토이미지고딕', 'BBTreeGR', 'BBTreeGR' !important; */
  font-family: '아리따돋움', 'Arita-dotum-Medium', 'Arita-dotum-Medium' !important;
}
#mainpage {
  font-family: Arial, Helvetica, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
.v-application code, .v-application kbd{
  all : unset !important;
  color: white !important;
}
pre>code .line-numbers-rows{
  top: 1em;
  left: 0;
}
.token.keyword{
  color: deepskyblue;
}
.token.number{
  color: greenyellow;
}
blockquote{
  padding-right: 8px !important;
  border-left-width: 5px !important;

  font-style: italic;
  font-family: Georgia, Times, "Times New Roman", serif;
  padding: 3px 20px !important;
  border-style: solid;
  border-color: #ccc !important;
  border-width: 0;
  text-align: left;

  margin-left: 20px !important;
  display: block !important;
  margin-block-start: 1em !important;
  margin-block-end: 1em !important;
  margin-inline-start: 40px !important;
  margin-inline-end: 40px !important;
}
.stf__item{
    overflow: auto !important;
}

.noti-fadein{
  animation: fadein 2s linear;
  animation-fill-mode: backwards;
}
.noti-fadeout{
  animation: fadeout 2s linear;
  animation-fill-mode: forwards;
}
.noti-hide{
  display: none;
}
.noti-show{
  display: block;
}
#messageModal{
  position: absolute !important;
  bottom: 100px !important;
  right: 100px !important;
}
.fa-envelope{
  color:green;
}
</style>
