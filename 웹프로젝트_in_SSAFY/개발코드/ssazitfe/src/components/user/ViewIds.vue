<template>
  <div>
    <nav-header></nav-header>

    <v-img :src="require('./../../views/images/login_background.jpg')" img-alt="Image">
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar dark flat>
                <v-toolbar-title>아이디 목록</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                {{this.userdata.userName}}님의 계정
                <div v-for="id in ids" :key="id">
                    <p style="text-align:left;">{{id}}</p>
                </div>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="searchpw">비밀번호 찾기</v-btn>
                <v-btn @click="login">Log In</v-btn>
                <v-btn @click="home">Back</v-btn>
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
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from "axios";
export default {
    name: "ViewIds",
    data() {
        return {
            userdata :  this.$store.getters.tempuser,
            ids : []
        }
    },
     components:{
        NavHeader,
        NavFooter
    },
    created() {
        axios.get('/user/list')
        .then((Response)=>{
            for(let i=0;i<Response.data.length;i++){
                axios.get(`/user/listone/${Response.data[i].userNo}`)
                .then((Res)=>{
                    if(Res.data.eMail == this.userdata.eMail){
                        this.ids.push(Response.data[i].userId)
                    }
                })
            }
        })
    },
    methods : {
        searchpw(){
            this.$router.push('/searchpw')
        },
        login(){
            this.$router.push('/login')
        },
        home(){
            this.$router.push('/main')
        }
    }
}
</script>

<style>

</style>