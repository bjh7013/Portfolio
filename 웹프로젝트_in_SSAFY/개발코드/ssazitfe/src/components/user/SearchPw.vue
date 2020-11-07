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
                <v-toolbar-title>비밀번호 찾기</v-toolbar-title>
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
                  <v-text-field 
                    label="가입 시 아이디" 
                    name="아이디" 
                    type="text"
                    v-model="id"
                    maxlength="15"
                  ></v-text-field>
                <!-- </v-form> -->
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="searchpw" dark>전송</v-btn>
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
  name : 'SearchPw',
  data(){
    return{
      sendnum : false,
      name : "",
      id : "",
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
    sendEmail(){
      emailjs.init("user_ZCf4M1I5bg4ThT8XmI4DW");
      emailjs.send("ssazit@gmail.com", "template_kmV6n1ry", {"noreply_to":"","user_name":"백정훈","message_html":"312313"})
    },
    home() {
      this.$router.go(-1)
    },
    searchpw(){
      var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      this.message = parseInt(Math.random()*100000000)
      if(this.name.length==0){
        alert("이름을 입력해주세요.");
        return ;
      }
      if(this.id.length==0){
        alert("아이디를 입력해주세요.");
        return ;
      }
      if(this.email.length==0){
        alert("이메일을 입력해주세요");
        return ;
      }
       if(this.email.match(regExp) == null){
        alert("올바르지 않은 이메일 형식입니다.")
        return;
      }
      emailjs.init("user_ZCf4M1I5bg4ThT8XmI4DW");
      emailjs.send("ssazit@gmail.com", "template_kmV6n1ry", {"noreply_to":"","userName":this.name,"message":this.message,"eMail":this.email})
      .then(()=>{
        axios.get('/user/list')
        .then((Response)=>{
          for(let i=0;i<Response.data.length;i++){
            axios.get(`/user/listone/${Response.data[i].userNo}`)
            .then((Res)=>{
              if(this.id === Res.data.userId){
                alert(this.id);
                alert(Res.data.userId);
                axios.put('/user/setpass',{
                userNo : Res.data.userNo,
                userName : Res.data.userName,
                nickName : Res.data.nickName,
                userPw : parseInt(this.message),
                eMail : Res.data.eMail,
                phoneNum : Res.data.phoneNum
              }).then(({data})=>{
                this.$router.push('/main');
              }).catch((error)=>{
                alert(error);
              })
                alert("이메일을 확인 해주세요!")
              }
            })
          }
        })
      })


    }
  }
}
</script>