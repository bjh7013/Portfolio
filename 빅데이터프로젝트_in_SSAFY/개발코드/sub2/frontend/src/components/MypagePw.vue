<template>
  <v-container>
    <h3 class="pwcheck-title">비밀번호 재인증</h3>
    <v-card class="pwcheck-card">
      <p class="pwcheck-icon">💬</p>
      <p class="pwcheck-content">
        개인정보를 안전하게 보호하기 위해
      </p> 
      <p class="pwcheck-content">
        비밀번호를 한번 더 입력해주세요.
      </p>
      <v-text-field
        v-model="userPwChk"
        :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
        :type="show ? 'text' : 'password'"
        label="비밀번호"
        outlined
        class="pwcheck-input"
        @click:append="show = !show"
        @keyup.enter="check"
      />
      <v-row>
        <v-btn 
          class="pwcheck-btn" 
          @click="check"
        >
          확인
        </v-btn>
      </v-row>
      <p 
        v-if="authentication==false" 
        class="pwcheck-message"
      >
        비밀번호가 일치하지 않습니다.
      </p>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  // 비밀번호 확인 페이지입니다.
  name: "MypagePw",
  data() {
    return {
      userPw: null,           // 저장된 PW
      userPwChk: null,        // 입력된 PW
      show: false,            // 비밀번호 표기
      authentication: null,   // 비밀번호 일치 여부
    }
  },
  created() {
    axios.get(`/apis/mypage`)
    .then((response) => {
      this.userPw = response.data.user_pw;
    })
  },
  methods: {
    // 비밀번호 일치 확인
    check() {
      if (this.userPw == this.userPwChk) {
        this.authentication = true;
        // 일치하면 수정 페이지로 이동
        this.$router.push('/mypage/update');
      }
      else {
        this.authentication = false;
      }
    },
  }
}
</script>

<style scoped>
.pwcheck-card {
  padding: 20px 16px; 
  margin: 10px auto; 
  min-width: 360px;
  width: 60%;
  height: 500px;
}
.pwcheck-title {
  text-align: center; 
  margin: 20px;
}
.pwcheck-icon {
  margin: 10px; 
  text-align: center; 
  font-size: 100px;
}
.pwcheck-content {
  margin: 0px; 
  text-align: center;
}
.pwcheck-input {
  margin: 50px auto 0px; 
  max-width: 320px;
}
.pwcheck-btn {
  margin: 0px auto; 
  width: 100%; 
  max-width: 320px;
}
.pwcheck-message {
  margin: 20px; 
  text-align: center; 
  color: red;
}
</style>