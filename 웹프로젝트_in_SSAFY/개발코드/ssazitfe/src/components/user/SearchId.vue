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
                <v-toolbar-title>아이디 찾기</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <!-- <v-form> -->
                  <v-text-field 
                    label="가입 시 이름" 
                    name="이름" 
                    type="text"
                    v-model="name"
                    maxlength="10"
                    autofocus
                  ></v-text-field>
                  <v-text-field 
                    label="가입 시 이메일" 
                    name="이메일" 
                    type="text"
                    v-model="email"
                    maxlength="25"
                  ></v-text-field>
                  <button type="button" @click="sendnumber" v-if="!sendnum">인증번호전송</button>
                  <button type="button" @click="sendnumber" v-if="sendnum">인증번호재전송</button>
                  <div v-if="sendnum">
                    <v-text-field 
                      label="인증번호" 
                      name="인증번호"
                      type="text"
                      v-model="AuthNum"
                      maxlength="25"
                      autofocus
                    ></v-text-field>
                  </div>
                <!-- </v-form> -->
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="emailCheck" dark>전송</v-btn>
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
import emailjs from 'emailjs-com';
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from "axios";
export default {
  name :'SearchId',
  data(){
    return{
      sendnum : false,
      name : "",
      email : "",
      AuthNum : "",
      message : ""
    }
  },
  components:{
    NavHeader,
    NavFooter
  },
  methods: {
    sendnumber(){
      var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      this.sendnum = true;
      this.message = parseInt(Math.random()*1000000)
      if(this.name.length==0){
        alert("이름을 입력해주세요")
        return;
      }
      if(this.email.length==0){
        alert("이메일을 입력해주세요")
        return;
      }
      if(this.email.match(regExp) == null){
        alert("올바르지 않은 이메일 형식입니다.")
        return;
      }
      emailjs.init("user_ZCf4M1I5bg4ThT8XmI4DW");
      emailjs.send("ssazit@gmail.com", "template_kmV6n1ry", {"noreply_to":"","userName":this.name,"message":this.message,"eMail":this.email})
    },
    emailCheck(){
      var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       if(this.name.length==0){
        alert("이름을 입력해주세요")
        return;
      }
      if(this.email.length==0){
        alert("이메일을 입력해주세요")
        return;
      }
      if(this.email.match(regExp) == null){
        alert("올바르지 않은 이메일 형식입니다.")
        return;
      }
      let tempuser = {
          userName: this.name,
          eMail: this.email,
      }
      if(parseInt(this.message)==parseInt(this.AuthNum)){
        this.$store.dispatch('JOIN',tempuser);
        this.$router.push('/viewids');
      }else{
        alert("인증번호가 맞지 않습니다.");
      }
    },
    home() {
      this.$router.go(-1)
    }
  }
}
</script>