<template>
  <div>
    <nav-header></nav-header>
    <!-- 로그인 폼 -->
    <!-- <v-img src="https://picsum.photos/id/1073/900/400" img-alt="Image"> -->
    <v-img :src="require('./../../views/images/login_background.jpg')" img-alt="Image">
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar dark flat>
                <v-toolbar-title>SSAZIT에 로그인하세요</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                
                  <v-text-field 
                    label="ID(최대 16자)" 
                    name="login" 
                    prepend-icon="mdi-account" 
                    type="text"
                    v-model="userId"
                    maxlength="16"
                    @keyup.enter="login"
                    >
                  </v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    v-model="userPw" 
                    @keyup.enter="login"
                  ></v-text-field>
                  <v-btn @click="login" style="width:100%" dark>로그인</v-btn>
                  <p></p>
                <div style="text-align:left; font-size:13px;">혹시 아직 회원이 아니신가요?&nbsp;<button><b @click="join">회원가입</b></button></div>
                <p style="text-align:left;">
                <button @click="searchid" style="font-size:12px; font-weight:bold;">아이디찾기</button>&nbsp;
                &nbsp;<button @click="searchpw" style="font-size:12px; font-weight:bold;">비밀번호찾기</button>
                </p>
                
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-img>

    <nav-footer></nav-footer>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from "axios";



export default {
  name: "Login",
  data: () => ({
    userId: "",
    userPw: "",
    rules: [
      value => !!value || 'Required.',
      value => (value && value.length >= 3) || 'Min 3 characters',
    ],
  }),
  methods: {
    ...mapActions(["login"]),
    login() {
      axios.post('/user/login',{
        userId : this.userId,
        userPw : this.userPw
      })
      .then((response)=>{
        if (response.status === 200) {
                        this.$session.start();
                        this.$session.set('jwt', response.headers.authorization);
                        axios.defaults.headers.common['Authorization'] = response.headers.authorization;
                        this.$store.dispatch('LOGIN',response.data);
                        // console.dir(response.data)
                        
                        //////////////////웹소켓 연결
                        this.$store.dispatch('connectWS',response.data.userNo);
                        this.$router.push('/main');
          }else{
                        alert('아이디와 비밀번호를 확인해주세요2');
          }
      }).catch(()=> {
            alert('아이디와 비밀번호를 확인해주세요');
      })
    },
    searchid() {
      this.$router.push('/searchid');
    },
    searchpw() {
      this.$router.push('/searchpw');
    },
    goToPages() {
      this.$router.push({
        name: "HelloWorld"
      });
    },
    googlelogin(){
      this.$router.push('/googlelogin');
    },
    join(){
      this.$router.push('/menu');
    }
  },
  computed: {
    ...mapGetters({
      errorState: "getErrorState" // getters로 errorState받기
    })
  },
  components: {
    NavHeader,
    NavFooter
  }
};
</script>

<style scoped>
  .fill-height{
    margin: auto;
  }
</style>