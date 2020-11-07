<template>
  <div>
    <nav-header></nav-header>
    <!-- 회원가입 -->
    <!-- <v-img src="https://picsum.photos/id/1073/900/400" img-alt="Image"> -->
    <v-img :src="require('./../../views/images/login_background.jpg')" img-alt="Image" v-if="menu">
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar dark flat>
                <v-toolbar-title>SSAZIT를 바로 시작해보세요</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field 
                    label="아이디" 
                    name="아이디"
                    hint="6~16 영문, 숫자" 
                    type="text"
                    v-model="userId"
                    maxlength="16"
                    onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');"
                    autofocus
                  ></v-text-field>
                  <button type="button" @click="idcheck">중복확인</button>

                  <v-text-field
                    id="password"
                    label="비밀번호"
                    name="비밀번호"
                    hint="최소 4자 최대 12자" 
                    type="password"
                    v-model="userPw"
                    maxlength="12"
                  ></v-text-field>

                  <v-text-field
                    id="passwordre"
                    label="비밀번호 확인"
                    name="비밀번호 확인"
                    hint="비밀번호 재입력" 
                    type="password"
                    v-model="userPwcheck"
                  ></v-text-field>

                  <v-text-field
                    label="이름"
                    type="text"
                    v-model="userName"
                    maxlength="12"
                  ></v-text-field>

                  <v-text-field
                    label="닉네임"
                    type="text"
                    v-model="nickName"
                    maxlength="12"
                  ></v-text-field>

                  <v-text-field
                    label="이메일 주소"
                    type="text"
                    v-model="eMail"
                    maxlength="30"
                  ></v-text-field>

                  <v-text-field
                    label="휴대폰번호(-포함)"
                    type="text"
                    v-model="phoneNum"
                    maxlength="15"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="onSubmit" dark>회원가입</v-btn>
                <v-btn @click="home" dark>뒤로</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-img>
    <v-img v-else>
      잘못된 접근입니다.<br>
      <v-btn @click="home" dark>메인으로</v-btn>
    </v-img>

    <nav-footer></nav-footer>
  </div>
</template>

<script>
// import {mapActions,mapGetters,mapMutations} from 'vuex';
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from "axios";
import emailjs from 'emailjs-com';

export default {
  name: "Join",
  data() {
    return {
      userdata: {
        userId: "",
        userIdcheck: "",
        userPw: "",
        userPwcheck: "",
        userName: "",
        nickName: "",
        eMail: "",
        phoneNum: "",
        idcheckflag: false,
        message: "",
        checkmanual: false,
        menualone :"",
        menualtwo :"",
        menual : [{
          temp: ""
        }],
        checkone : false,
        checktwo : false,
        checkall : false,
      }
    };
  },
  computed:{
    menu() {
      return this.$store.getters.menu;
    },
  },
  created(){
    this.checkone = false;
    this.checktwo = false;
    this.checkall = false;
    this.checkmanual =false;
  },
  methods: {
    onSubmit() {
      var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      var regExpphone = /^\d{3}-\d{3,4}-\d{4}$/;

      if(this.userId.length<4){
        alert("아이디는 최소 4자 이상입니다.");
        return;
      }
      if(this.userPw.length<4){
        alert("비밀번호 최소 4자 이상입니다.");
        return;
      }
      if (this.eMail.match(regExp) == null) {
        alert('이메일 형식이 올바르지 않습니다.');
        return;
      }
      if (this.phoneNum.match(regExpphone) == null) {
        alert('-을 넣어주세요');
        return;
      }
      if (this.userPw == "" || this.userPwcheck == "") {
        alert("비밀번호를 입력 해주세요");
      } else if (this.userPw != this.userPwcheck) {
        alert("비밀번호를 다시 확인 해주세요");
      } else {
        this.joincomplete();
      }
    },
    idcheck() {
      if(this.userId.length<4){
        alert("아이디는 최소 4자 이상입니다.");
        return;
      }
      axios
        .post("/user/idcheck", {
          userId: this.userId
        })
        .then(response => {
          if (response.data) {
            this.idcheckflag = false;
            alert("중복된 아이디입니다.");
          } else {
            this.idcheckflag = true;
            this.userIdcheck = this.userId;
            alert("사용가능한 아이디입니다.");
          }
        });
    },
    joincomplete() {
      if (this.idcheckflag && this.userIdcheck == this.userId) {
        this.message = parseInt(Math.random()*1000000)
        let tempuser = {
          userId: this.userId,
          userPw: this.userPw,
          userName: this.userName,
          nickName: this.nickName,
          eMail: this.eMail,
          phoneNum: this.phoneNum,
          message : this.message
        }
        emailjs.init("user_ZCf4M1I5bg4ThT8XmI4DW");
        emailjs.send("ssazit@gmail.com", "template_kmV6n1ry", {"noreply_to":"","userName":this.userName,"message":this.message,"eMail":this.eMail})
        this.$store.dispatch('JOIN',tempuser);
        this.$router.push('/joincheck');
      //   axios
      //     .post("/user/join", {
      //       userId: this.userId,
      //       userPw: this.userPw,
      //       userName: this.userName,
      //       nickName: this.nickName,
      //       eMail: this.eMail,
      //       phoneNum: this.phoneNum
      //     })
      //     .then(response => {
      //       console.log(response);
      //       alert("회원 가입이 완료되었습니다.");
      //       this.$router.push("/main");
      //     })
      //     .catch(error => {
      //       console.log(error);
      //       alert("오류 발생!");
      //       this.$router.push("/main");
      //     });
      } else {
        alert("id가 중복이거나 확인이 되지 않았습니다.");
      }
    },
    home() {
      this.$router.push("/main");
    },
    next() {
      if(this.checkone==true && this.checktwo==true){
        this.checkmanual = true
      }else{
        alert("이용 약관에 동의 해주세요")
      }
    }
  },
  components: {
    NavHeader,
    NavFooter
  }
};
</script>

<style scoped>
.join {
  text-align: left;
}
</style>

