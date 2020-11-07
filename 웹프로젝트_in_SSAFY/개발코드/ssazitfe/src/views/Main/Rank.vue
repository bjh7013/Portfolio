<template>
  <div id="ranking">
    <nav-header></nav-header>
    <div id="ranking_body">

      <div class="col-12" id="left">
        <!-- 랭킹 이미지 -->
        <div>
          <li><img id="level" :src="ssazit"/></li>
          <li><h4>{{ userInfo.nickName }}</h4></li>
          <li><p>님의 순위 : {{ userInfo.userlevel }}1위</p></li>
        </div>
        <!-- <p>SSAZIT</p> -->

        <!-- 경험치 애니메이션 -->
        <div class="container">
          <div class="progress progress-striped">
            <div class="progress-bar" style="width:80%"></div>                       
          </div> 
          <p>8000/10000</p>
        </div>
      </div> 

      <!-- 랭킹 목록 -->
      <!-- <v-row>
        <div class="col-3">
          <v-card class="elevation-6" style="background-color:#FFFAF0">
            <v-card-title class="layout justify-center">
              dd
            </v-card-title>
            <v-card-text>
              ddd
            </v-card-text>
            <v-card-footer>
              ss
            </v-card-footer>
          </v-card>
        </div>
      </v-row> -->

      <div>
        <b-table :items="items"></b-table>
        <!-- 페이지네이션 -->
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          align="center"
        ></b-pagination>
      </div>

    </div>

    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
export default {
  name: "Rank",
  components: {
    NavHeader,
    NavFooter
  },
  data() {
    return {
      ssazit: require("../images/ssazit.jpg"),
      items: [
        { 순위: "1", 계급: "4ZIT", 닉네임: "리뷰GO!", 경험치: "8000/10000" },
        { 순위: "2", 계급: "4ZIT", 닉네임: "Hundred", 경험치: "1500/10000" },
        { 순위: "3", 계급: "5ZIT", 닉네임: "Groot", 경험치: "800/10000" },
        { 순위: "4", 계급: "7ZIT", 닉네임: "ZeroZoo", 경험치: "100/10000" },
        { 순위: "5", 계급: "9ZIT", 닉네임: "Dragonwater", 경험치: "1/10000" }
      ],
      // pagination 이동하게 만들어야함
      rows: 100,
    }
  },
    computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    }
  },
};
</script>

<style scoped>
#ranking_body {
  width: 85%;
  margin: 0 100px;
}
li {
  display: inline-block;
  margin: 0 10px;
}
#level {
  width: 100px;
  height: 80px;
}

.container {
  margin: 50px auto;
  width: 500px;
  text-align: center;
}

.progress {
  padding: 6px;
  background: rgba(0, 0, 0, 0.25);
  border-radius: 6px;
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25), 0 1px rgba(255, 255, 255, 0.08);
}

.progress-bar {	
  height: 18px;
	background-color: #ee303c;  
  border-radius: 4px; 
  transition: 0.4s linear;  
  transition-property: width, background-color;    
}

.progress-striped .progress-bar { 	
  background-color: #FCBC51; 
  width: 100%; 
  background-image: linear-gradient(
        45deg, rgb(252,163,17) 25%, 
        transparent 25%, transparent 50%, 
        rgb(252,163,17) 50%, rgb(252,163,17) 75%,
        transparent 75%, transparent); 
  animation: progressAnimationStrike 6s;
}

@keyframes progressAnimationStrike {
     from { width: 0 }
     to   { width: 100% }
}
#left {
  top: 50px;
  width: 100%;
  float: center;
  box-sizing: border-box;
}
/* #right {
  width: 40%;
  right: 30px;
  float: center;
  box-sizing: border-box;
} */
</style>
