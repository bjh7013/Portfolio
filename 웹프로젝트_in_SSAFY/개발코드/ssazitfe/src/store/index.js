import Vue from "vue";
import Vuex from "vuex";
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

Vue.use(Vuex);

const state = {
  userId: "",
  errorState: "",
  userInfo: null,
  receive_message: [],
  send_message: [],
  blogInfo: [],
  boardInfo: [],
  sockConnected : false,
  boardInfo_delete: [],
  boardnumInfo:'',
  tempboard:{},
  tempuser:{},
  menu:false,
  // serverURL: "http://i3d104.p.ssafy.io/",
  serverURL: 'http://localhost/9999',
  noti: '',
  fromBoardNo:''
};
export default new Vuex.Store({
  state,
  getters: {
    fromBoardNo(){
      return state.fromBoardNo;
    },
    userInfo() {
      return state.userInfo;
    },
    menu() {
      return state.menu;
    },
    blogInfo() {
      return state.blogInfo;
    },
    boardInfo() {
      return state.boardInfo;
    },
    boardnumInfo() {
      return state.boardnumInfo;
    },
    receive_message() {
      return state.receive_message;
    },
    send_message() {
      return state.send_message;
    },
    tempuser(){
      return state.tempuser;
    },
    serverURL(){
      return state.serverURL;
    },
    sockConnected(){
      return state.sockConnected;
    },
    noti(){
      return state.noti;
    },
    message(){
      return state.message;
    }
  },
  mutations: {
    fromBoardNo(state, payload){
      state.fromBoardNo = payload;
    },
    noti(state, payload){
      state.noti = payload;
    },
    message(state, payload){
      state.message = payload;
    },
    LOGIN(state, payload) {
      state.userInfo = payload;
    },
    sockConnected(state, payload){
      state.sockConnected = payload;
    },
    JOIN(state, payload) {
      state.tempuser = payload;
    },
    Menu(state, payload) {
      state.menu = payload;
    },
    LOGOUT(state) {
      state.userInfo = null;
    },
    Re_userInfo(state, payload){
      state.userInfo = payload;
    },
    Re_blogInfo(state, payload){
      state.blogInfo = payload;
    },
    Re_boardInfo(state, payload){
      state.boardInfo = payload;
    },
    Re_boardInfo_manager(state, payload){
      state.boardInfo.splice(state.boardInfo.length,0,payload);
    },
    Re_boardInfo_before(state, payload){
      if(payload[2]=='up_sub_2'){
        state.boardInfo[payload[1]].seqNo++;
      }
    },
    Re_boardInfo_now(state, payload){
      if(payload[2]=='up_sub_2'){
        state.boardInfo[payload[1]].seqNo--;
      }
    },
    Re_boardInfo_after(state, payload){
      console.log(state);
      console.log(payload);
    },
    modify_boardInfo(state, payload){
      state.boardInfo[0] = payload;
      alert(state.boardInfo[0].boardName);
    },
    trans_board(state, payload) {
      state.tempboard = state.boardInfo[payload[0]];
      state.boardInfo[payload[0]] = state.boardInfo[payload[1]];
      state.boardInfo[payload[1]] = state.tempboard;
    },
    Re_boardnumInfo(state, payload){
      state.boardnumInfo = payload;
    },
    receive_message(state, payload){
      state.receive_message = payload;
    },
    send_message(state, payload){
      state.send_message = payload;
    },
  },
  actions: {
    LOGIN(context, data) {
      context.commit("LOGIN", data);
    },
    JOIN(context, data) {
      context.commit("JOIN", data);
    },
    Menu(context, data) {
      context.commit("Menu", data);
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
    },
    Re_userInfo(context, data){
      context.commit('Re_userInfo',data);
    },
    Re_blogInfo(context, data){
      context.commit('Re_blogInfo',data);
    },
    Re_boardInfo(context, data){
      context.commit('Re_boardInfo',data);
    },
    Re_boardInfo_manager(context, data){
      context.commit('Re_boardInfo_manager',data);
    },
    Re_boardInfo_before(context, data){
      console.log(data[0].boardName+  "index"+ data[1]+"type" + data[2]);
      context.commit('Re_boardInfo_before',data);
    },
    Re_boardInfo_now(context, data){
      console.log(data[0].boardName+  "index"+ data[1]+"type" + data[2]);
      context.commit('Re_boardInfo_now',data);
    },
    Re_boardInfo_after(context, data){
      context.commit('Re_boardInfo_after',data);
    },
    modify_boardInfo(context,data){
      context.commit('modify_boardInfo',data);
    },
    trans_board(context, data){
      context.commit('trans_board',data);
    },
    Re_boardnumInfo(context, data){
      context.commit('Re_boardnumInfo',data);
    },
    receive_message(context, data) {
      context.commit('receive_message',data)
    },
    send_message(context, data) {
      context.commit('send_message',data)
    },
    sendMessage (context, e) {
      console.log(context);
      if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },
    send(context, userNo) {
      console.log(context);
      alert('send')
      console.log("Send message:" + this.message);
      
        const msg = {
          content: "ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ" 
        };
        this.stompClient.send(`/app/sendmsg/${userNo}`, JSON.stringify(msg), {});
      
    },
    connectWS(context, userNo) {
      // const serverURL = "http://i3d104.p.ssafy.io/ws"
      const serverURL = "http://192.168.1.2/9999/ws"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          context.commit('sockConnected', true);
          console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          this.stompClient.subscribe(`/topic/pushnoti/${userNo}`, res => {
      
            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            context.commit('noti', JSON.parse(res.body));
          });

          this.stompClient.ws.onclose = function(){
            this.connected = false;
            context.commit('sockConnected', false);
            setInterval( () => {
              if(context.getters.userInfo && !context.getters.sockConnected){
                context.dispatch("connectWS", context.getters.userInfo.userNo)
              } 
            }, 1000)
          }
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );

      
    }
  }
});
