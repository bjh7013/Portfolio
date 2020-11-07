<template>
  <div id="manageheader">
    <v-toolbar id="header" color="black">
      <router-link to="/main"><img id="logo" :src="logo"></router-link>
    </v-toolbar>
    <!-- <v-col id="sidebar" cols="2" style="height:1000px;"> -->
      <!-- <v-card id="side_card" :elevation="0">
        <br>
        <p><b>멤버관리</b></p>
        <router-link to="/tag" style="text-decoration: none; color:black"><b>태그관리</b></router-link><br><br>
        <router-link to="/managehome" style="text-decoration: none; color:black"><b>관리</b></router-link>
      </v-card> -->
    <!-- </v-col> -->
      
    <!-- <router-view></router-view> -->
    <!-- <nav-footer></nav-footer> -->
  </div>
</template>

<script>
export default {
  name : "ManageHeader",
  data() {
    return {
      logo: require("../images/logo_yong.png"),
    }
  }
}
</script>

<style scoped>
#logo {
  position: fixed;
  height: 60px;
  top: 1px;
  left: 1px;
  outline: none;
}
#header {
  padding: 0px 0px;
  height: 50px;
}
/* #sidebar {
  padding: 0px 0px;
}
#side_card {
  border-radius: 0px;
  background-color: gray;
  height: 100%;
} */
</style>