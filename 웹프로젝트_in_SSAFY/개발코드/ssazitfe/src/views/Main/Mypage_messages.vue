<template>
  <div class="messages container col order">
    <div v-if="userInfo!=null && userInfo.userNo>-1">
      <div class="row">
        <!-- data가 들어갈 부분 -->
        <div class="col" id="main">
          <!-- 검색 -->
          <v-card :elevation="0" class="col-11" id="messages_card">
            <v-container fluid>
              <v-row align="center">
                <v-btn id="send_m" large outlined color="black" @click="send_msg_to">쪽지쓰기<v-icon small>mdi-pencil</v-icon></v-btn>
                <v-col md="4" class="ml-auto">
                  <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="search_message"></v-text-field>
                </v-col>
                <v-btn icon @click="search_message"><v-icon>mdi-magnify</v-icon></v-btn>
              </v-row>
            </v-container>

            <!-- 탭 -->
            <b-tabs content-class="mt-3">
              <b-tab title="받은쪽지함" @click="trans_receive" active></b-tab>
              <b-tab title="보낸쪽지함" @click="trans_send"></b-tab>
            </b-tabs>

            <!-- 받은 쪽지함 -->
            <div v-if="tabs">
              <div class="col-4" id="left" v-for="message in receive_data" :key="message.messageNo">
                <v-card max-width="300" max-height="300">
                  <v-card-text id="card-text">
                    <!-- <div v-if="message.read">읽음</div> -->
                    <p align="left">from.{{message.nickName}}</p>
                    <div class="text--primary">
                      {{message.content}}
                    </div>
                  </v-card-text>
                  <v-card-text id="card-text2">
                    <p>{{message.sendDate}}</p>
                  </v-card-text>
                  <v-card-actions>
                    <v-btn text color="deep-purple accent-4" @click="repeat_message(message)" style="position:relative; left:160px;">답장</v-btn>
                    <!-- <v-btn text color="deep-purple accent-4" @click="read_message(message)">읽음</v-btn> -->
                    <v-btn text color="deep-purple  accent-4" @click="delete_message(message)" style="position:relative; left:140px;">삭제</v-btn>
                  </v-card-actions>
                </v-card>
              </div>
              <!-- 답장 모달 -->
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

            <!-- 보낸 쪽지함 -->
            <div v-if="!tabs">
              <div class="col-4" id="left" v-for="message in send_data" :key="message.messageNo">
                <v-card max-width="300" max-height="300">
                  <v-card-text id="card-text">
                    <p align="left">to.{{message.nickName}}</p>
                    <div class="text--primary">
                    {{message.content}}
                    </div>
                  </v-card-text>
                  <v-card-text id="card-text2">
                    <p>{{message.sendDate}}</p>
                  </v-card-text>
                  <v-card-actions id="btn">
                    <v-btn text color="deep-purple accent-4" @click="delete_message(message)" style="position:relative; left:210px;">삭제</v-btn>
                  </v-card-actions>
                </v-card>
              </div>
            </div>
            <!-- 삭제 모달 -->
            <modal v-if="showDelete" @close="showDelete = false">
              <transition name="modal">
                <div class="modal-mask">
                  <div class="modal-wrapper">
                  
                      <div id="app">
                        <v-container fluid>
                          <v-row>
                            <v-col cols="12" md="5">
                              <v-sheet elevation="12" class="pa-12">
                                해당 쪽지를 삭제하시겠습니까?
                                <button class="modal-default-button" @click="canceldelete()">
                                취소
                                </button>
                                <button class="modal-default-button" @click="delete_message_ok()">
                                삭제
                                </button>
                              </v-sheet>
                            </v-col>
                          </v-row>
                        </v-container>
                      </div>
                  </div>
                </div>
              </transition>
            </modal>  

            <!-- 쪽지 보내기 -->
            <modal v-if="showSend" @close="showSend = false">
              <transition name="modal">
                <div class="modal-mask">
                  <div class="modal-wrapper">
                  
                    <div id="app">
                      <v-container fluid>
                        <v-row>
                          <v-col cols="12" md="8">
                            <v-sheet elevation="12" class="pa-12">
                            <v-text-field v-model="model_to" placeholder="상대방ID를 입력하시오." maxlength="20"></v-text-field>
                                <v-autocomplete
                                  v-model="model_to"
                                  @click="hoverfriend"
                                  :items="user"
                                  :loading="isLoading"
                                  :search-input.sync="search"
                                  color="white"
                                  hide-no-data
                                  hide-selected
                                  item-text="usernickname"
                                  item-value="user"
                                  label="이웃간편검색"
                                  placeholder="ID를 입력하시오"
                                  prepend-icon="mdi-account-search"
                                  return-object
                                >
                                </v-autocomplete>
                            <!-- </v-tooltip> -->
                              <v-text-field
                                v-model="model"
                                label="메세지"
                                :hint="model_to+'님에게'"
                                :placeholder="placeholder"
                                :outlined="outlined"
                                :solo="solo"
                                :persistent-hint="persistentHint"
                                :counter="counterEn ? counter : false"
                                maxlength="90"
                              ></v-text-field>
                              <button class="modal-default-button" @click="get_to_data()">
                              전송
                              </button>
                              <button class="modal-default-button" @click="cancel_send()">
                              취소
                              </button>
                            </v-sheet>
                          </v-col>
                        </v-row>
                      </v-container>
                    </div>
                  </div>
                </div>
              </transition>
            </modal>  
          </v-card>
        </div>
      </div>

      <!-- 페이지네이션 -->
      <v-col cols="12">
        <v-container id="page" class="max-width" @click="clickpage">
          <v-pagination
            v-model="page"
            :length="msglength"
            :page="page"
            total-visible="10"
          ></v-pagination>
        </v-container>
      </v-col>
    </div>
    <div class='pageBlogNotFound' v-else style="position:relative; left:-200px">
      <div>
        <h2>잘못된 접근입니다.</h2>
      </div>
    <router-link to="/menu">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>회원가입</h2>
          <h3>SSAZIT와 함께 하세요.</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circlBlogNotFounde_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc1.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc3.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc2.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
    <router-link to="/main">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>홈</h2>
          <h3>SSAZIT홈으로 돌아갑니다</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc4.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc5.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc6.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
    <router-link to="/login">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>로그인</h2>
          <h3>로그인 시 이용할 수 있습니다.</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc7.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc8.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc9.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
// import ReMessage from '@/views/Message/ReMessage.vue'
export default {
  name: "messages",
    data() {
      return{
        items1: ['전체선택', '보낸사람', '내용'],
        items2: ['유저ID', '유저이름', '닉네임'],
        receive_user: 'From.',
        receive_message: '안녕하세요 맞팔해요~',
        receive_data:[],
        send_data:[],
        tabs : true,
        showModal: false,
        showDelete: false,
        showSend: false,
        model_to : '',
        model: '',
        user : ['zz','ss'],
        usernickname : ['zz','ss'],
        userImage : {},
        label: '답글',
        hint: '누구에게',
        placeholder: '내용을 입력하시오.',
        outlined: true,
        persistentHint: true,
        counterEn: true,
        showfriendlist:false,
        counter: 90,
        my_index:'',
        your_index:'',
        msg_index:'',
        your_data : {},
        searchname : null,
        search : "",
        page: 1,
        msglength: 2,
      }
    },
    created(){
      if(this.userInfo!=null){
        axios.get('/user/rcvmsg/1').then((Response)=>{
          this.$store.dispatch('receive_message',Response.data);
          this.receive_data = this.$store.getters.receive_message;
        }),
        axios.get('/user/sendmsg/1').then((Response)=>{
          this.$store.dispatch('send_message',Response.data);
          this.send_data = this.$store.getters.send_message;
        })
        axios.get('/user/rcvmsgcount')
        .then((Response)=>{
          this.msglength = parseInt(1 + ((Response.data-1)/9))
          // console.log("카운트는!")
          // console.log(this.msglength)
        }).catch((error)=>{
          alert(error);
        })
        this.page = this.$route.params.page
      }
    },
    computed : {
      receive_vmessage() {
        return this.$store.getters.receive_message;
      },
      send_vmessage() {
        return this.$store.getters.send_message;
      },
      userInfo(){
        // console.dir(this.$store.getters.userInfo);
        return this.$store.getters.userInfo;
      }
    },
    methods: {  
      clickpage(){
        if(this.tabs){
          if(this.searchname!=null){
            axios.get('/user/rcvmsg/'+this.page+'?searchValue=' + encodeURI(this.search)).then((Response)=>{
            this.$store.dispatch('receive_message',Response.data);
            this.receive_data = this.$store.getters.receive_message;
          }) 
          }else{
            axios.get('/user/rcvmsg/'+this.page).then((Response)=>{
              this.$store.dispatch('receive_message',Response.data);
            this.receive_data = this.$store.getters.receive_message;
          })  
          }
        }else{
          if(this.searchname!=null){
          axios.get('/user/sendmsg/'+this.page+'?searchValue=' + encodeURI(this.search)).then((Response)=>{
          this.$store.dispatch('send_message',Response.data);
          this.send_data = this.$store.getters.send_message;
        })
        }else{
          axios.get('/user/sendmsg/'+this.page).then((Response)=>{
          this.$store.dispatch('send_message',Response.data);
          this.send_data = this.$store.getters.send_message;
        })  
        }
        }
      },
      trans_receive() {
        this.tabs=true;
        axios.get('/user/rcvmsg/1').then((Response)=>{
          this.$store.dispatch('receive_message',Response.data);
          this.searchname=null;
          this.page = 1;
          this.receive_data = this.$store.getters.receive_message;
          this.search="";
          axios.get('/user/rcvmsgcount')
          .then((Response)=>{
          this.msglength = parseInt(1 + ((Response.data-1)/9))
          // console.log("카운트는!")
          // console.log(this.msglength)
          }).catch((error)=>{
          alert(error);
        })
        })
        
      },
      trans_send() {
        this.tabs=false;
        axios.get('/user/sendmsg/1').then((Response)=>{
          this.$store.dispatch('send_message',Response.data);
          this.searchname=null;
          this.page = 1;
          this.send_data = this.$store.getters.send_message;
          this.search="";
          axios.get('/user/sendmsgcount')
        .then((Response)=>{
          this.msglength = parseInt(1 + ((Response.data-1)/9))
          // console.log("카운트는!")
          // console.log(this.msglength)
        }).catch((error)=>{
          alert(error);
        })
        })
      },
      repeat_message(data){
        this.showModal = true
        this.hint = data.nickName+"에게"
        this.my_index = data.messageTo
        this.your_index = data.messageFrom
      },
      read_message(data){
        axios.put(`/user/readmsg/${data.messageNo}`)
        .then((Response)=>{
          // console.log(Response);
          this.$router.go()
        })
      },
      delete_message(data) {
        this.showDelete = true
        this.msg_index = data.messageNo
      },
      cancel() {
        this.showModal = false;
        this.model="";
      },
      canceldelete() {
        this.showDelete = false;
      },
      cancel_send() {
        this.showSend = false;
        this.model_to="";
        this.model="";
      },
      send_message(){
        this.showModal = false;
        axios.post('/user/sendmsg',{
          content : this.model,
          messageTo : this.your_index
        }).then((Response)=>{
          // console.log(Response);
          alert("전송되었습니다.");
          this.$router.go()
        }).catch((error)=>{
          // console.log(error);
          alert("전송에 실패했습니다.");
        })
      },
      delete_message_ok(){
        this.showDelete = false;
        if(this.tabs){
          axios.delete(`/user/rcvmsg/${this.msg_index}`)
          .then((Response)=>{
            // console.log(Response);
            alert("삭제되었습니다.");
            this.$router.go()
          }).catch((error)=>{
            // console.log(error);
            alert("에러 발생");
          })
        }else{
          axios.delete(`/user/sendmsg/${this.msg_index}`)
          .then((Response)=>{
            // console.log(Response);
            alert("삭제되었습니다.");
            this.$router.go()
          }).catch((error)=>{
            // console.log(error);
            alert("에러 발생");
          })
        }
      },
      send_msg_to(data) {
        this.showSend = true
        this.hint = data.nickName+"에게"
        this.my_index = data.messageTo
        this.your_index = data.messageFrom
      },
      get_to_data(){
        axios.post('/user/list',{
            searchKey : 'userId',
            searchValue : this.model_to
          }).then((Response)=>{
            // console.log(Response);
            for(let i=0;i<Response.data.length;i++){
                if(Response.data[i].userId==this.model_to){
                this.your_index = Response.data[i].userNo;
                break;
              }
            }
            this.send_message();
            this.showSend=false;
          })
      },
      search_message() {
        if(this.tabs){
        axios.get('/user/rcvmsg/1?searchValue=' + encodeURI(this.search))
        .then((Response)=>{
          this.$store.dispatch('receive_message',Response.data);
          this.receive_data = Response.data;
          this.searchname = this.search;
          this.page=1;
        })
        axios.get('/user/rcvmsgcount?searchValue=' + encodeURI(this.search))
          .then((Response)=>{
          this.msglength = parseInt(1 + ((Response.data-1)/9))
        })
        }
        else{
          axios.get('/user/sendmsg/1?searchValue=' + encodeURI(this.search))
          .then((Response)=>{
          this.$store.dispatch('send_message',Response.data);
          this.send_data = Response.data;
          this.searchname = this.search;
          this.page=1;
          axios.get('/user/sendmsgcount?searchValue=' + encodeURI(this.search))
          .then((Response)=>{
          this.msglength = parseInt(1 + ((Response.data-1)/9))
        })
        })
        }
      },
      hoverfriend() {
        axios.get('/user/following')
        .then((Response)=>{
          // console.log(Response);
          this.user = Response.data;
          for(let i=0;i<Response.data.length;i++){
            this.user[i]=Response.data[i].userId
            // this.user[i]=Response.data[i]
            // this.user[i]=Response.data[i].nickName
            // this.user[i]=Response.data[i].profileImg
            // this.user[i].totalname=Response.data[i].userId+Response.data[i].userId
          }
        })
        
      },
      selectuser(){
        this.model_to = this.search
      },
    },
  components: {}
};
</script>

<style>
.container {
  margin: 0px 10px;
  width: 100%;  
  text-align: center;
}
#left {
  left: 20px;
  padding: 10 0px;
  width: 100%;
}
div#left.col-4 {
  width: 350px;
}
/* #right {
  float: right;
  width: 100%;
} */
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

#main {
  padding: 0 0px;
}
#card-text {
  height: 180px;
  text-align: center;
}
#card-text2 {
  padding: 0 15px;
  text-align: right;
}
#page {
  margin: 0;
  width: 100%;
}
#messages_card {
  padding: 0 30px;
}
#send_m {
  left: 33px;
}
</style>
