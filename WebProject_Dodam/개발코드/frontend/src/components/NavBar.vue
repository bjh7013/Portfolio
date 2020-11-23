<template>
  <div>
    <section class="navigation">
      <div class="nav-container">
        <div class="brand">
          <router-link to="/class">도담도담</router-link>
        </div>
        <nav>
          <div class="nav-mobile" @click="shownav()"><a id="nav-toggle"><span></span></a></div>
          <ul id="navul" class="nav-list" v-if="userInfo">
            <li>
              <router-link to="/contentsmain">혼자 공부방</router-link>
            </li>
            <li v-if="userInfo.userType==='teacher'">
              <router-link to="/teachermain">모든 교실</router-link>
            </li>
            <li v-if="userInfo.userType==='student'">
              <router-link to="/studentmain">모든 교실</router-link>
            </li>
            <li v-if="userInfo.userType==='teacher'">
              <router-link to="/class">우리반</router-link>
            </li>
            <li v-if="userInfo.userType==='student'"> 
              <router-link to="/class">우리반</router-link>
            </li>
            <!-- 교육자 = 내아이관리 / 학생 = 내 학습현황 vue파일 생성해야함-->
            <li v-if="userInfo.userType==='teacher'">
              <router-link to="/mychildren">내 아이관리</router-link>
            </li>
            <li v-if="userInfo.userType==='student'">
              <router-link to="/childrendetail">공부현황</router-link>
            </li>
            <li>
              <router-link to="/"><button @click="logout">나가기</button></router-link>
            </li>
          </ul>
        </nav>
      </div>
    </section>
    <div v-if="userInfo" style="text-align:right" class="container">
      <!-- <router-link to="/mypage"> -->
        {{userInfo.nickName}}({{userInfo.userName}})님
      <!-- </router-link>님 -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'navbar',
  data() {
    return {
      downnav: false,
    }
  },
  methods : {
    logout(){
        this.$session.destroy();
        this.$store.dispatch('LOGOUT');
        setTimeout(function(){

        },1500);
        this.$router.push('/');
    },
    shownav(){
      var navstyle = document.getElementById("navul");
      if(!this.downnav){
        navstyle.style.display="contents";
        this.downnav = !this.downnav;
        return;
      }
      navstyle.style.display="none";
      this.downnav = !this.downnav;
      
    },
  },
  computed : {
    userInfo(){
      return this.$store.getters.userInfo
    }
  }
}
</script>

<style>
#logo-main {
  display: block;
  margin: auto;
}
#navbar-primary.navbar-default {
  background: transparent;
  border: none;
}
#navbar-primary.navbar-default .navbar-nav {
  width: 100%;
  text-align: center;
}
#navbar-primary.navbar-default .navbar-nav > li {
  display: inline-block;
  float: none;
}
#navbar-primary.navbar-default .navbar-nav > li > a {
  padding-left: 30px;
  padding-right: 30px;
}

/* nav */
.navigation {
  height: 70px;
  background:#79bd9a;
  font-family: 'Jua', sans-serif;
  font-size: 130%;
}

.brand {
  position: absolute;
  padding-left: 20px;
  float: left;
  line-height: 70px;
  text-transform: uppercase;
  font-size: 1.4em;
}
.brand a,
.brand a:visited {
  color: #000;
  text-decoration: none;
}

.nav-container {
  max-width: 1000px;
  margin: 0 auto;
}

nav {
  float: right;
}
nav ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
nav ul li {
  float: left;
  position: relative;
}
nav ul li a,
nav ul li a:visited {
  display: block;
  padding: 0 10px;
  line-height: 70px;
  background:#79bd9a;
  color: #000;
  text-decoration: none;
}
nav ul li a:hover,
nav ul li a:visited:hover {
  background: #2581DC;
  color: #ffffff;
}
nav ul li a:not(:only-child):after,
nav ul li a:visited:not(:only-child):after {
  padding-left: 4px;
  content: ' ▾';
}
nav ul li ul li {
  min-width: 190px;
}
nav ul li ul li a {
  padding: 15px;
  line-height: 20px;
}

.nav-dropdown {
  position: absolute;
  display: none;
  z-index: 1;
  box-shadow: 0 3px 12px rgba(0, 0, 0, 0.15);
}

/* Mobile navigation */
.nav-mobile {
  display: none;
  position: absolute;
  top: 0;
  right: 0;
  background: #262626;
  height: 70px;
  width: 70px;
}

@media only screen and (max-width: 798px) {
  .nav-mobile {
    display: block;
  }

  nav {
    width: 100%;
    padding: 70px 0 15px;
  }
  #navul{
    display: none;
    /* display: contents; */
  }
  nav ul li {
    float: none;
  }
  nav ul li a {
    padding: 15px;
    line-height: 20px;
  }
  nav ul li ul li a {
    padding-left: 30px;
  }

  .nav-dropdown {
    position: static;
  }
}
@media screen and (min-width: 799px) {
  .nav-list {
    display: block !important;
  }
}
#nav-toggle {
  position: absolute;
  left: 18px;
  top: 22px;
  cursor: pointer;
  padding: 10px 35px 16px 0px;
}
#nav-toggle span,
#nav-toggle span:before,
#nav-toggle span:after {
  cursor: pointer;
  border-radius: 1px;
  height: 5px;
  width: 35px;
  background: #ffffff;
  position: absolute;
  display: block;
  content: '';
  transition: all 300ms ease-in-out;
}
#nav-toggle span:before {
  top: -10px;
}
#nav-toggle span:after {
  bottom: -10px;
}
#nav-toggle.active span {
  background-color: transparent;
}
#nav-toggle.active span:before, #nav-toggle.active span:after {
  top: 0;
}
#nav-toggle.active span:before {
  transform: rotate(45deg);
}
#nav-toggle.active span:after {
  transform: rotate(-45deg);
}

article {
  max-width: 1000px;
  margin: 0 auto;
  padding: 10px;
}

</style>