<template>
  <div id="blogManager">
    <nav-header></nav-header> 
    <div class="container p-0" style="height:500px;" v-if="userInfo.blogNo==0">
      <div style="height:100px;"></div>
      <h4 align="left" class="font-weight-bold" cols="3">블로그 생성</h4>
      <hr> 

      <v-row class="pl-3" justify="start" style="width:1050px;">
        <v-col cols="5" class="px-0">
          <v-text-field
            v-model="blogname"
            label="블로그명 입력"
            clearable
            :rules="nameRules"
            :counter="25"
            class="p-0 m-0"
            required
          ></v-text-field>
        </v-col>
        <v-col align="left" class="grey--text font-weight-light">한글, 영문, 숫자 혼용가능 (한글 기준25자 이내)</v-col>
      </v-row>
      <b-button @click="create_blog">생성하기</b-button>
      <br>
    <hr>
    </div>
    <div class="container p-0" style="height:500px;" v-if="userInfo.blogNo!=0">
        <h2>블로그가 이미 존재하거나. 오류페이지입니다.</h2>
        <b-button @click="home">홈으로</b-button>
    </div>
    <nav-footer></nav-footer>
  </div>
</template>
<script>
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from 'axios';
export default {
  name: "BlogCreate",
  data() {
    return {
      blogname: '',
      updateInfo:'',
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 25 || 'Name must be less than 25 characters',
      ],
      sortItems: ['자유', '알고리즘'],
    };
  },
  components : {
    NavHeader,
    NavFooter
  },
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    }
  },
  methods : {
      home(){
          this.$router.push("/");
      },
      create_blog(){
          axios.post('/blog/CUD?blogName='+encodeURI(this.blogname))
          .then((Response)=>{
              // console.log(Response);
              alert("블로그가 생성되었습니다!");
              axios.get('/user/mypage').then((Response)=>{
                this.$store.dispatch('Re_userInfo',Response.data)
            })
              this.$router.push("/main");
          }).catch((Error)=>{
              // console.log(Error);
              alert("오류 발생");
          })
      }
  }
};
</script>

<style></style>