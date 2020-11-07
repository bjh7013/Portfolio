<template>
  <v-container>
    <v-card class="welcome-card">
      <v-row>
        <v-icon class="welcome-icon">mdi-account-check</v-icon>
      </v-row>
      <h1 class="welcome-title">
        회원가입이 완료되었습니다.
      </h1>
      <p class="welcome-content">
        {{ userId }} 님 회원가입을 환영합니다.
      </p>
      <p class="welcome-content">
        입맛 테스트를 통해 오늘의 메뉴를 추천받아보세요!!
      </p>

      <v-row>
        <v-btn 
          color="success" 
          class="welcome-btn" 
          to="/testfood"
        >
          내 입맛 알아보기
        </v-btn>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  // 회원가입 완료 페이지입니다.
  name: "Welcome",
  computed: {
    ...mapGetters(['userId']),    // 아이디 불러오기
  }
}
</script>

<style scoped>
.welcome-card {
  padding: 20px 16px; 
  margin: 10px auto; 
  min-width: 360px;
  width: 60%;
  height: 500px;
}
.welcome-title {
  text-align: center; 
  margin: 20px;
}
.welcome-content {
  text-align: center;
  width: 90%;
  margin: 0px auto;
}
.welcome-icon {
  margin: 20px auto; 
  text-align: center;
  font-size: 100px;
}
.welcome-btn {
  margin: 50px auto 10px; 
  width: 90%; 
  max-width: 450px;
  
}
</style>