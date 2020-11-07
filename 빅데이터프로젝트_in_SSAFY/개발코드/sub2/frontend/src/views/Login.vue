<template>
  <v-container>
    <h3 class="login-title">로그인</h3>
    <v-card class="login-card">
      <v-row>
        <v-icon class="login-icon">mdi-lock-outline</v-icon>
      </v-row>
      <!-- 아이디 -->
      <v-text-field
        v-model="loginData.userId"
        label="아이디"
        required
        outlined
        class="login-input"
      />
      <!-- 비밀번호 -->
      <v-text-field
        v-model="loginData.userPw"
        :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
        :type="show ? 'text' : 'password'"
        label="비밀번호"
        required
        outlined
        class="login-input"
        @click:append="show = !show"
        @keyup.enter="login"
      />
      <v-row>
        <v-btn class="login-btn" @click="login">로그인</v-btn>
      </v-row>
      <v-row>
        <v-btn text class="login-btn" to="/join">회원가입</v-btn>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";
import axios from 'axios';

export default {
  // 로그인 페이지입니다.
  name: "Login",
  data() {
    return {
      loginData: {
        userId: "",   // 입력된 ID
        userPw: "",   // 입력된 PW
      },
      show: false,    // 비밀번호 표시 여부
    }
  },
  created() {
    // 로그인한 상태이면 home으로 리다이렉트
    if (this.$cookies.isKey('jwt')) {
      return this.$router.push('/');
    }
  },
  methods: {
    ...mapActions(["Login"]),
    
    // 로그인
    login() {
      axios.post(`/apis/login`, {
        user_id : this.loginData.userId,
        user_pw : this.loginData.userPw
      })
      .then((response) => {
        if(response.status === 200 && response.data.token){
          // 쿠키 저장
          this.$cookies.set('jwt', response.data.token);      // 토큰
          this.$cookies.set('userId', response.data.userId);  // 아이디

          axios.defaults.headers.common['Authorization'] = response.data.token;

          // store 저장
          this.Login(response.data.token);                        // 토큰
          this.$store.dispatch('UserId', response.data.userId);   // 아이디

          // 로그인 완료 후 home으로 리다이렉트
          this.$router.push('/');
        }else{
          alert("아이디와 비밀번호를 확인해주세요.");
        }
      })
      
    }
  }
}
</script>

<style scoped>
.login-card {
  padding: 20px 16px; 
  margin: 10px auto; 
  min-width: 360px;
  width: 60%;
  height: 500px;
}
.login-title {
  text-align: center; 
  margin: 20px;
}
.login-icon {
  margin: 20px auto 50px; 
  text-align: center;
  font-size: 100px;
}
.login-input {
  margin: 0px auto; 
  max-width: 320px;
}
.login-btn {
  margin: 0px auto 10px; 
  width: 100%; 
  max-width: 320px;
}
</style>