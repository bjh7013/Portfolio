<template>
  <v-app id="app" style="background-color:#f5f5f5">
    <navbar />
    <drawer />
    <route-view />
    <go-top />
  </v-app>
</template>

<script>
import Drawer from "@/components/Drawer";
import Navbar from "@/components/Navbar";
import RouteView from "@/components/RouteView";
import GoTop from "@/components/GoTop";
import axios from 'axios';

// const BACK_URL = 'http://127.0.0.1:8000'

export default {
  name: 'App',
  components: {
    Drawer,
    Navbar,
    RouteView,
    GoTop
  },
  created(){
    axios.defaults.baseURL = 'https://j3d201.p.ssafy.io';

    // axios.defaults.baseURL = 'https://localhost:8000';
    
    if (this.$cookies.isKey('jwt')) {
      const userToken = this.$cookies.get('jwt');   // 토큰
      const userId = this.$cookies.get('userId');   // 아이디

      axios.defaults.headers.common['Authorization'] = userToken;
      
      // 새로고침시 store 저장
      this.$store.dispatch('Login', userToken); // 토큰
      this.$store.dispatch('UserId', userId);   // 아이디
    }
  },
  
};
</script>

<style>
  a {
    text-decoration: none !important;
  }
</style>