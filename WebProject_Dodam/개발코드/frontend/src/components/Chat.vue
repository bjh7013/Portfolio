<template>
<div id="chat">
  
  <div class="chatBox" id="chatBox">
    <div class="card">
      <header class="card-header header-title" @click="toggleChat()">
        <p v-if="chatBoxArea" class="card-header-title">
          <i class="fa fa-circle is-online"></i>
          &nbsp;{{headUser}}
        </p>
        <img src="../assets/images/chaticon.png" class="mimg" id="chaticon"/>
        <a class="no-read" v-if="!chatBoxArea">+{{newMessagesCount}}</a>
        <a class="card-header-icon">
          <span class="icon">
            <i class="fa fa-close"></i>
          </span>
        </a>
      </header>
    <div id="chatbox-area">
      <div id="scroll-chat" class="card-content chat-content" style="height:300px; overflow: auto;" >
        <button style="margin-left:95px;" @click="chatLog">이전 기록 불러오기</button>
        <div @click="playVoice(message)" :class=" message.data.chatType==3? (message.voiceOn ? 'content voice-content voice-on' :'content voice-content') :'content'" 
            v-for="(message, i) in messageList" :key="i">
        
          <div v-if="message.data.userId!==userId" style="text-align:left;"  class="chat-message-group">
            <a>{{message.data.nickName}}</a>
            <!-- <div class="chat-thumb">
              <figure class="image is-32x32">
                <img src="">
              </figure>
            </div> -->
            <div class="chat-messages" style="margin-left:10px">
              <p class="message" v-if="message.data.chatType==3" ><i class="fas fa-bullhorn voice"></i></p>
              <p class="message" v-else>{{message.data.text}}</p>
              <!-- <div class="from">Hoje 04:55</div> -->
            </div>
          </div>
          <div v-if="message.data.userId===userId" style="text-align:right;"  class="chat-message-group writer-user">
            <a>{{message.data.nickName}}</a>
            <div class="chat-messages">
              <p class="message" v-if="message.data.chatType==3" ><i class="fas fa-bullhorn voice myvoice"></i></p>
              <p class="message" v-else>{{message.data.text}}</p>
              <!-- <div class="from">Hoje 04:55</div> -->
            </div>
          </div> 
        </div>
      </div>
      <footer class="card-footer" id="chatBox-textbox">
        <v-row>
          <v-col class="chat-input" cols="9">
              <voice-record ref="record" @voice-mode="onVoiceMode" @audio-change="audioChange"/>
              <textarea v-if="chatType==2" id="chatTextarea" class="chat-textarea" placeholder="문자를 입력하세요" v-model="tmpmessage.content"></textarea>
              <i v-else @click="onTextMode" class="fas fa-keyboard"></i>
          </v-col>
          <v-col cols="3" style="vertical-align: middle;">
            <a @click="onMessageWasSent">send</a>
          </v-col>
        </v-row>
 
      </footer>
      </div>
    </div>
    </div>


</div>

</template>
<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import Axios from 'axios';
// import CloseIcon from "vue-beautiful-chat/src/assets/close-icon.png";
// import OpenIcon from "vue-beautiful-chat/src/assets/logo-no-bg.svg";
// import FileIcon from "vue-beautiful-chat/src/assets/file.svg";
// import CloseIconSvg from "vue-beautiful-chat/src/assets/close.svg";
// const PROFURL = `${process.env.VUE_APP_SERVER_URL}/user/infobyuserno`
 
 import $ from "jquery";
import VoiceRecord from './VoiceRecord.vue';
// var reconnect = 0;

export default {
  
  name: "Chat",
  components: {
    VoiceRecord
  },
  created() {
    this.openChat();
    this.classNo = this.$route.params.classno;
    this.chatRoomNo=this.classNo;
    this.setChatInit(this.classNo, this.userId)
  }, 
  watch: {
    isChatOpen() {
      if (this.isChatOpen)
        this.newMessagesCount = 0
    },
    messageList: {
      deep: true,
      handler() {
        if (!this.isChatOpen)
          this.newMessagesCount += 1
      },
    },
  },
  data() {
    return {
      isText: true,
      reconnected: false,
      classNo: "",  
      chatRoomNo:"",
      room: {},
      userId: "",
      nickName:"",
      chatType:2,
      tmpmessage:{
        content:"",
      
      },
      connected: false,
      user:null,
      // chat ui
      headUser: " ",
      chatBoxArea: false,
      currentChats: [],
      // the list of all the participant of the conversation. `name` is the user name, `id` is used to establish the author of a message, `imageUrl` is supposed to be the user avatar.
      participants: [],
      chatTitle: "Chatting",
      // titleImageUrl:
        // "https://a.slack-edge.com/66f9/img/avatars-teams/ava_0001-34.png",
      // the list of the messages to show, can be paginated and adjusted dynamically
      messageList: [],
      newMessagesCount: 0,
      isChatOpen: false, // to determine whether the chat window should be open or closed
      lastChat:null,
    };
  },
  methods: {
    playVoice(message){
      message.voiceOn = true
      if(message.data.chatType == 3){
        Axios.get(`/chataudio/${message.chatNo}`, {  responseType: 'blob' })
        .then(async ( {data} ) => {
            let audioUrl = URL.createObjectURL(data);
            
            setTimeout(() => {
              message.voiceOn = false;
            }, 3000)
            
            new Audio(audioUrl).play()
        })
      }
    },
    onTextMode(){
      this.chatType = 2
      this.$refs.record.replay=false
    },
    onVoiceMode(){
      this.chatType = 3
    },
    audioChange(i,base64Data){
      this.audioData = base64Data
		},
    toggleChat: function () {
      var chat = document.getElementById("chatBox");
      var chaticon = document.getElementById("chaticon");
      if (this.chatBoxArea) {
        chat.style.width = "50px";
        
        chaticon.style.display = "block";
      this.headUser= "",
        $("#chatbox-area").hide();
        this.closeChat()
      } else {
        
        chat.style.width = "300px";
        chaticon.style.display = "none";
      this.headUser= "클래스 채팅방",
        $("#chatbox-area").show();
        this.openChat();
      }
      this.chatBoxArea = !this.chatBoxArea;
    },
    openChatBox: function (info) {info},
    startChat: function (user) {user},
    onMessageWasSent() {
      // called when the user sends a message
      // 기존 코드
      // 밑에 꺼 사용하면, 두번 입력됨.
      // this.messageList = [...this.messageList, JSON.parse(JSON.stringify(message))];
      this.tmpmessage.author = this.userId
      let timerId = setInterval( () => {
        if(this.connected){
          this.send( this.tmpmessage)
          clearInterval(timerId)
        }
      }, 300)
      
    },
    async send(message) {
      if(this.chatType != 2 && this.chatType != 3){
        alert(this.chatType)
        alert('잘못된 입력입니다')
        return
      }
      
      this.stompClient.send("/pub/chat/message", 
        JSON.stringify({
            chatType:this.chatType,
            chatRoomNo: this.chatRoomNo,
            userId: this.userId,
            userNo:this.userNo,
            // content: message.data.text,
            // audioData : this.chat.audioData
            nickName:this.nickName,
            content: message.content,
            audioData:this.audioData
          })
      )
      if(this.connected){
        this.tmpmessage.content ='';

      }
    },
    async getUserInfo(){
      await Axios.get('/user/info').then((response)=>{
        this.user=response.data;
        this.userId =this.user.userId;
        this.userNo=this.user.userNo;
        this.nickName=this.user.nickName;
      }).catch((err)=>{err})
      this.handleOnType();
    },
    scorolling(preScrollHeight){
      var objDiv = document.getElementById("scroll-chat"); 
        let timerId = setInterval(() => {
          if(preScrollHeight != objDiv.scrollHeight){
            clearInterval(timerId)
            objDiv.scrollTop = objDiv.scrollHeight;
          }

        },100)
    },
    openChat() {
      // called when the user clicks on the fab button to open the chat
      this.isChatOpen = true;
      this.newMessagesCount = 0;
    },
    closeChat() {
      // called when the user clicks on the botton to close the chat
      this.isChatOpen = false;
    },
    handleScrollToTop() {
      // called when the user scrolls message list to top
      // leverage pagination for loading another page of messages
    },
    handleOnType() {
      // 누군가 타자칠 때 나오는 문구
      // console.log("Emit typing event");
    },

    async findRoomS(classNo) {
      await this.findRoom(classNo)
    },
    setChatInit(classNo, userId) {
      this.findRoomS(classNo)
      this.connect(classNo, userId)
    },
    // socket start
    findRoom(classNo) {
      Axios
        .get(
          `/chatroom/class?classNo=` +
            classNo
        )
        .then((response) => {
          this.room = response.data.object;
        });
    },
    async connect(classNo, userId) {
      userId
      let sock = new SockJS(this.$store.getters.WS_URL + `/ws-stomp`);
      this.stompClient = Stomp.over(sock);
      this.stompClient.debug = () => {};
      await this.getUserInfo();
      this.stompClient.connect(
        {},
        frame => {
          // frame;
          this.connected = true;
          this.stompClient.subscribe(
            "/sub/chatroom/class?classNo=" + classNo
            , chat => {
              var recv = JSON.parse(chat.body);
              const checkParticipant = this.participants.some(p => p.id === recv.userId)
              
              if(this.reconnected && recv.chatType == 1){
                return;
              }
              if(recv.chatType === 1 && checkParticipant){
                frame;
              }
              else
              {
                let msg = {
                  type: "text",
                  author: recv.userId === this.userId ? "me" : recv.userId ,
                  data: { text: `${recv.content}`, userId: `${recv.userId}`,nickName:`${recv.nickName}`, chatType:recv.chatType },
                  voiceOn: null,
                  chatNo: recv.chatNo
                };
                
                let preScrollHeight = document.getElementById('scroll-chat').scrollHeight;

                this.messageList.push(msg);

                if(recv.chatType == 3){
                    new Audio(recv.audioData).play()
                } 

                this.lastChat=this.messageList[0];
                for(let msg of this.messageList){
                  if(msg.chatNo){
                    this.lastChat = msg
                    break;
                  }
                }
                this.scorolling(preScrollHeight);

              }
            }
          );

          this.stompClient.ws.onclose = () => {
            this.connected = false;
            this.reconnected = true;
            let timerId = setInterval( () => {
              if(!this.connected){
                this.connect(this.classNo, this.userId)
              } else{
                clearInterval(timerId)
              }
            }, 3000)
          }
          this.stompClient.send(
            "/pub/chat/message",
            JSON.stringify({ chatType:1, chatRoomNo: this.chatRoomNo, userNo:this.userNo,userId:this.userId,nickName:this.nickName })
          );
        },
        // 재연결 함수. start
         () => {
          if (!this.connected) {
            setTimeout( () => {
              sock = new SockJS(this.$store.getters.WS_URL+`/ws-stomp`);
              this.stompClient = Stomp.over(sock);
              this.connect(this.classNo, this.userId);
            }, 1000);
          }
        }
        // 재연결 함수. end
      );
    },chatLog(){
      
        Axios.post("/chatroom/chatlog",{chatNo:this.lastChat.chatNo, chatRoomNo:this.chatRoomNo}).then((response)=>{
            
          if(response.data.length==0){
            alert("이전 대화가 없습니다.");
            return;
          }
          for(var i=0;i<response.data.length;i++){
             this.messageList.unshift({  
                type: "text",
                author: response.data[i].userId,
                data: { text: `${response.data[i].content}`, userId: `${response.data[i].userId}`,nickName:`${response.data[i].nickName}`,chatType:response.data[i].chatType },
                chatNo:response.data[i].chatNo,
                voiceOn:null
            });

          }
          this.lastChat=this.messageList[0];
          for(let msg of this.messageList){
            if(msg.chatNo){
              this.lastChat = msg
              break;
            }
          }
           
        }).catch((err)=>{err})
    }
  },
  mounted() {
    let timerId = setInterval( () => {
      
      let chatTextArea =document.getElementById('chatTextarea'); 
      if(chatTextArea){
        clearInterval(timerId)
        chatTextArea.addEventListener('keyup', (e) => {
          if(e.key == 'Enter' && !e.shiftKey){
              this.onMessageWasSent()
              e.preventDefault()
            }
        })
      }
    }, 300)
  
    $("#chatbox-area").hide();
  }
};
</script>

<style scoped>
.no-read{
    position: absolute;
    top: -10%;
    right: -10%;
    background: #0063DC;
    /* background: #FF8868; */
    /* background: #FF9ACD; */
    /* background: #98DFD8; */
    /* background: #BBDC58; */
    border-radius: 50%;
    color: #fff;
}
.mimg{
  width: auto;
  height: 100%;    
  position: absolute;
  max-height: 40px;
  bottom: 5%;
  right: 10%;
  z-index: 11110;
}

#chat{
  position:relative;
  text-align:left;
  bottom:-10px;
}
.allUsersList{
  width: 300px;
  margin: 20px;
}
.allUsersList .card-header{
  background: #683db8;
  color: #FFF;
  padding: 10px;
}
.allUsersList .image img{
  border-radius: 16px;
}
.usersChatList{
  position: absolute;
  width: 250px;
  bottom: 0;
  margin-bottom: 0;
  right: 360px;
}
.show{
  display: block;
}
.thumb-user-list{
  display: none;
}
.thumb-user-list .image img{
  border-radius: 30px;
}
.usersChatList .card-header{
 background: #79bd9A;
  font-size: 13px;
}
.chatBox{
  position: fixed;
  bottom: 0;
  z-index: 999;
  right: 0;
  width: 50px;
  font-size: 13px;
  margin: 0 2% 2% 0;
}
.chat-content{
  overflow: auto;
  /* height: 300px; */
}
.chatBox .card{
  border-radius: 4px;
}
.chatBox .card-header{
 background: #fff56E;
}
.header-title{
  height: 50px;
}
.card-header-title i{
  font-size: 10px;
  color: #32e4cd;
  margin-right: 6px;
}
.card-header .card-header-title{
  color: #111111;
}
.chat-content small{
  margin: 0;
}
.chat-content p{
  background: #ecf1f8;
  padding: 10px;
  border-radius: 8px;
  margin-bottom: 0;
}
.my-content .media-content{
  width: 80%;
}
.my-content .message{
  background: #683db8;
  color: #FFF;
  text-align: right;
  padding: 10px;
  margin-bottom: 4px;
  font-size: 13px;
}

.my-content small{
  display: block;
  width: 100%;
  text-align: right;
}
.chat-textarea{
  font-size: 14px;
  padding: 8px;
  height: 40px;
  width: 100%;
  border: none;
  overflow: auto;
  outline: none;

  -webkit-box-shadow: none;
  -moz-box-shadow: none;
  box-shadow: none;
  resize: none;
}

.chat-message-group .chat-thumb{
  width: 20%;
}
.chat-message-group .chat-messages{
    width: 80%;
  margin-bottom: 20px;
}
.chat-message-group .message{
  padding: 10px;
  background: #ecf1f8;
  display:inline-block;

  font-size: 13px;
  border-radius: 5px;
  margin-bottom: 3px;
}
.chat-messages .from{
  display: block;
  width: 100%;
  text-align: left;
  font-size: 11px;
}
.chat-thumb img{
  border-radius: 40px;
}
.writer-user .chat-messages{
  width: 100%;
}
.writer-user .chat-messages .message{
  background: #683db8;
  display:inline-block;

  color: #FFF;
}
.writer-user .chat-messages .from{
  display: block;
  width: 100%;
  text-align: right;
}

.chatBox .card-header-icon i{
  color: #FFF;
  font-size: 13px;
}
/* CSSS */
.outside-box{
  height: 100px;
  background: #F8C;
  width: 200px;
  margin: 20px;
  overflow: auto;
}
.outside-box .content-insider{
  height: 300px;
  background: #C9C;
}
/* CSS Spinner */
.spinner {
  margin: 0 30px;
  width: 70px;
  text-align: center;
}

.spinner > div {
  width: 4px;
  height: 4px;
  background-color: #888;

  border-radius: 100%;
  display: inline-block;
  -webkit-animation: sk-bouncedelay 1.4s infinite ease-in-out both;
  animation: sk-bouncedelay 1.4s infinite ease-in-out both;
}

.spinner .bounce1 {
  -webkit-animation-delay: -0.32s;
  animation-delay: -0.32s;
}

.spinner .bounce2 {
  -webkit-animation-delay: -0.16s;
  animation-delay: -0.16s;
}

@-webkit-keyframes sk-bouncedelay {
  0%, 80%, 100% { -webkit-transform: scale(0) }
  40% { -webkit-transform: scale(1.0) }
}

@keyframes sk-bouncedelay {
  0%, 80%, 100% { 
    -webkit-transform: scale(0);
    transform: scale(0);
  } 40% { 
    -webkit-transform: scale(1.0);
    transform: scale(1.0);
  }
}
/* EmojiBox */
.emojiBox{
  width: 150px;
  margin: 30px;
}
.emojiBox .box{
  height: 100px;
  padding: 4px;
}
/* */
.card-header-title img{
  border-radius: 40px;
}

.chat-input {
  display: flex;
  justify-content: left;
  align-items: center;
}
.fas{
  margin-left : 9px;
}
.voice-content{
  width: 100%;
  height: 40%;
}
.voice-content .chat-message-group{
  /* width: 100%; */
  height: 100%;
}
.voice-content .chat-messages{
  /* width: 100%; */
  height: 100%;
}

.voice-content .message{
  height: 70%;
  width : 50%;
  text-align: center;
}
.voice-content .voice{
  font-size:500%;
}

.myvoice{
  transform:rotate(0deg);
  -moz-transform: scaleX(-1); 
  -o-transform: scaleX(-1); 
  -webkit-transform: scaleX(-1); 
  transform: scaleX(-1);   
  filter: FlipH;
  -ms-filter: "FlipH";
}
.voice-on .voice{
  animation-name: blink;
  animation-duration: 0.5s;
  animation-iteration-count: 8;
  animation-direction:alternate;
}

@keyframes blink {
  0%   {color: tomato;}
  100%  {color: white;}
}

@media (max-width: 700px){
  .chatBox{
    
  }
}


</style>
