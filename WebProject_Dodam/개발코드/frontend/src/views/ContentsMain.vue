<template>
  <div class="contentsmain">
    
    <img src="../assets/images/leftanimal.png" class="cmimg leftimg"/>
    <img src="../assets/images/rightanimal.png" class="cmimg rightimg"/>
    <div>
        <div class="contentsmain-head">
          <div class="left">
            <router-link to="/#"><logo class="logo" style="margin:5% auto"></logo></router-link>
          </div>
          <!-- <div class="right"> -->
          <div class="right" v-if="userInfo">
            <ul style="margin:5%">
              <template v-if="userInfo.userType==='teacher'">
                <v-btn rounded color="success" @click="classhometeacher" width="7vw">모든 교실</v-btn>
              </template>
              <template v-if="userInfo.userType==='student'">
                <v-btn rounded color="success" @click="classhomestudent" width="7vw">모든 교실</v-btn>
              </template>
              <v-btn rounded color="primary" @click="logout" width="7vw">나가기</v-btn><br>
              <template v-if="userInfo.userType==='teacher'">
                <v-btn rounded color="error" @click="childrendetail" width="7vw">공부현황</v-btn>
              </template>
              <template v-if="userInfo.userType==='student'">
                <v-btn rounded color="error" @click="mystudy" width="7vw">공부현황</v-btn>
              </template>
                <v-btn rounded color="warning" @click="draw" width="7vw">그림놀이</v-btn>
            </ul>
          </div>
          <div class="right" v-if="!userInfo">
            로그인이 안됬습니다!
          </div>
        </div>
    </div>
    <div>
      <br><contents/>
    </div>
  <Loading />
  </div>
</template>

<script>
import Contents from '@/components/Contents.vue'
import Logo from '@/components/Logo.vue'
import Loading from "@/components/Loading";

export default {
  name: 'contentsmain',
  components: {
    Contents,
    Logo,
    Loading,
  },
  created(){
    if(this.$session.get('jwt')==null){
      this.$router.go(-1)
    }
  },
  methods : {
    mystudy(){
      this.$router.push('/childrendetail')
    },
    logout(){
        this.$session.destroy();
        this.$store.dispatch('LOGOUT');
        this.$router.push('/');
    },
    classhometeacher() {
      this.$router.push('/teachermain')
    },
    classhomestudent() {
      this.$router.push('/studentmain')
    },
    childrendetail() {
      this.$router.push('/mychildren')
    },
    draw() {
      this.$router.push('/draw')
    },
  },
  computed : {
    userInfo(){
      return this.$store.getters.userInfo
    }
  }
}
</script>

<style scoped>
.contentsmain-head{
  display: flex;
  justify-content: space-between;
}
div.container {
  padding: 0;
}
.v-img {
  width: 100%;
  height: 100%;
}

.left {
  width: 50vw;
  height: 15vh;
  position: relative;
}

.right {
  width: 20vw;
}
.cmimg{
  
  width: auto;
  height: 90vh;    
  position: absolute;
  z-index: 0;
}
.leftimg{
  bottom: 0;
  left: 0;
}
.rightimg{
  
  bottom: 0;
  right: 0;
}

ul {
  /* display: inline-block; */
  margin-left: 1%;
  padding: 1%;
}
.logo{
  height: 100% !important;
  width: 100% !important;
}
@media (min-width:701px) and (max-width: 1070px){
  .contentsmain-head{
    display: flex;
    justify-content: space-between;
  }
  
}
@media (min-width:301px) and (max-width: 700px){
  .contentsmain-head{
    display: block;
  }
  
  .left {
    width: 100vw;
    height: 15vh;
    position: relative;
    max-height: 140px;
  }
.right {
  width: 100vw;
}
}

</style>