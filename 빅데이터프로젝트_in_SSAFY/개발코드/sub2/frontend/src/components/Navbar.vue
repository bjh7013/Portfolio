<template>
  <v-app-bar id="app-toolbar" app flat color="blue lighten-1">
    <v-btn dark icon @click="backspace">
      <v-icon>mdi-keyboard-backspace</v-icon>
    </v-btn>
    <router-link to="/" style="text-decoration:none;">
      <h3 class="title white--text" style="min-width:120px; margin:0px;">건강한세끼</h3>
    </router-link>
    <v-spacer />
    <v-text-field v-model="foodName" dark @keyup.enter="search_food" style="margin-top:20px;" />
    <v-btn dark icon @click="search_food">
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
    <v-btn dark icon @click.stop="onClickDrawer">
      <v-icon>mdi-view-list</v-icon>
    </v-btn>
    <span v-if="isLogin">
      <v-btn @click.native="logout">로그아웃</v-btn>
    </span>
    <span v-else style="min-width:50px;">
      <router-link to="/login" class="nav-login">로그인</router-link>
    </span>
  </v-app-bar>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import axios from 'axios';

export default {
  name: 'Navbar',
  data(){
    return {
      responsive: false,
      foodName: "",
    }
  },
  computed: {
    ...mapState(["drawer"]),
    // 로그인 상태 확인
    isLogin(){
      return this.$store.getters.isLogin
    },
  },
  created() {

  },
  mounted() {
    this.onResponsiveInverted();
    window.addEventListener("resize", this.onResponsiveInverted);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResponsiveInverted);
  },

  methods: {
    ...mapMutations(["setDrawer"]),
    onClickDrawer() {
      this.setDrawer(!this.drawer);
    },
    onResponsiveInverted() {
      if (window.innerWidth < 900) {
        this.responsive = true;
      } else {
        this.responsive = false;
      }
    },
    // 뒤로가기
    backspace() {
      this.$router.go(-1)
    },
    // 로그아웃
    logout(){
      // 쿠키 삭제
      this.$cookies.remove('jwt');
      this.$cookies.remove('userId');

      delete axios.defaults.headers.common['Authorization'];

      // store 유저 데이터 삭제
      this.$store.dispatch('Logout', null);
      this.$store.dispatch('UserId', null);

      // 로그아웃 완료 후 login으로 리다이렉트
      this.$router.push('/login')
    },
    search_food() {
      if (this.foodName) {
        this.$router.push({name:'searchfood', params: {key:this.foodName ,name:this.foodName}});
        this.$store.dispatch('searchFood', this.$route.params.name)
      } else {
        alert('검색어를 입력해주세요.')
      }
    }
  }
};
</script>
<style scoped>
.nav-login {
  color: white !important;
}
</style>