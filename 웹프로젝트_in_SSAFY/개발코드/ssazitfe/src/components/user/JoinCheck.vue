<template>
  <div>
    <nav-header></nav-header>

    <!-- 회원가입 -->
    <!-- <v-img src="https://picsum.photos/id/1073/900/400" img-alt="Image"> -->
    <v-img :src="require('./../../views/images/login_background.jpg')" img-alt="Image">
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar dark flat>
                <v-toolbar-title>이메일 인증 페이지</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <!-- <v-form> -->
                  <v-text-field 
                    label="인증코드" 
                    name="인증코드" 
                    type="text"
                    v-model="AuthNum"
                    maxlength="6"
                    @keyup.enter="emailCheck"
                    autofocus
                  ></v-text-field>
                  <p @click="repeat"><button>인증번호 재전송</button></p>
                <!-- </v-form> -->
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="emailCheck" dark>회원가입</v-btn>
                <v-btn @click="home" dark>뒤로</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-img>

    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from 'axios'
import emailjs from 'emailjs-com';

export default {
    name:'JoinCheck',
    data(){
        return {
        AuthNum :'',
        tempuser : this.$store.getters.tempuser
        }
    },
    components: {
        NavHeader,
        NavFooter
    },
    methods : {
        emailCheck() {
            this.tempuser = this.$store.getters.tempuser
            if(parseInt(this.AuthNum) == parseInt(this.tempuser.message)){
                axios
                .post("/user/join", {
                    userId: this.tempuser.userId,
                    userPw: this.tempuser.userPw,
                    userName: this.tempuser.userName,
                    nickName: this.tempuser.nickName,
                    eMail: this.tempuser.eMail,
                    phoneNum: this.tempuser.phoneNum
                })
                .then(response => {
                    // console.log(response);
                    alert("회원 가입이 완료되었습니다.");
                    this.$router.push("/main");
                })
                .catch(error => {
                    // console.log(error);
                    alert("오류 발생!");
                    this.$router.push("/main");
                });
            }else{
                alert("인증 번호가 맞지 않습니다.");
            }
        },
        repeat(){
            let remessage = parseInt(Math.random()*1000000)
            emailjs.init("user_ZCf4M1I5bg4ThT8XmI4DW");
            emailjs.send("ssazit@gmail.com", "template_kmV6n1ry", {"noreply_to":"","userName":this.tempuser.userName,"message":remessage,"eMail":this.tempuser.eMail})
            let tempuser = {
            userId: this.tempuser.userId,
            userPw: this.tempuser.userPw,
            userName: this.tempuser.userName,
            nickName: this.tempuser.nickName,
            eMail: this.tempuser.eMail,
            phoneNum: this.tempuser.phoneNum,
            message : remessage
            }
            this.$store.dispatch('JOIN',tempuser);
            alert("재전송 되었습니다.");
        },
        home() {
            this.$router.push("/main");
        }
    },
    destroyed(){
        let tempuser = {}
        this.$store.dispatch('JOIN',tempuser);
    }
}
</script>

<style>

</style>