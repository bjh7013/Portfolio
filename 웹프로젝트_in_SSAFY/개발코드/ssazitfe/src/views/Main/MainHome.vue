<template>
  <div class="home"> 
    <v-carousel
      cycle
      width="100%"
      height="760"
      hide-delimiter-background
      show-arrows-on-hover
      font-family=""
    >
      <v-carousel-item>
        <v-sheet class="frame1" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" style="color:white;">
              <router-link id="logo" to="/main" style="text-decoration: none; color:white;"><h4>SSAZIT로 이동</h4></router-link>
              <p class="font-weight-medium">여러분의 이야기를 들려주세요.</p>
              <p class="font-weight-medium">SSAFY인의 아지트, <b class="font-weight-black">SSAZIT</b></p>
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet class="frame2" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" style="color:white;">
              <router-link id="logo" to="/main" style="text-decoration: none; color:white"><h4>SSAZIT로 이동</h4></router-link>
              <v-col style="padding-right:200px;">
                <p class="font-weight-medium">원하는대로</p>
                <p class="font-weight-medium">작품 스타일링</p>
              </v-col>
            </div>
            <img id="book" :src="book2">
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet class="frame3" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" style="color:white;">
              <router-link id="logo" to="/main" style="text-decoration: none; color:white;"><h4>SSAZIT로 이동</h4></router-link>
              <v-col style="padding-right:200px;">
                <p class="font-weight-medium">NOW <br>지금 <br> YOUR <br> 당신의 <br> 일상</p>
              </v-col>
            </div>
            <h2 style="color:white;" @click="gomain">SSAZIT에서</h2>
            <v-icon large style="bottom:5px;" @click="gomain">mdi-arrow-right-bold</v-icon>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet class="frame4" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" style="color:white;">
              <router-link id="logo" to="/main" style="text-decoration: none; color:white;"><h4>SSAZIT로 이동</h4></router-link>
              <p class="font-weight-medium">SSAZIT</p>
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
    <!-- <br><br><br><br><br> -->
    
    <!-- <h1>당신의 소중한 하루가 <br> 그저 지나가는 하루가 되지 않도록 해줄게요 <br> 일상이 작품이 되는 곳, SSAZIT</h1>
    <h2></h2>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
     -->
   
    <!-- 원고지 타다닥
    <br>
    SSAZIT 사용꿀팁!
    1. 글을 쓴다. (이것도 귀찮으면? 마음에 드는 글을 공유해온다.)
    2. 책 만들기에서 게시물을 드래그한다
    3. 표지를 고르면?
    작품 완성!
    <br>

    <br>
    SSAZIT 멤버를 모집합니다
    언제 어디서나, 나만의 책 
    (회원가입)
    <br> -->


    <!-- <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> -->
  </div>
</template>

<script>
export default {
  name: "MainHome",
  data() {
    return{
      book2: require("../images/book2.png"),
    }
  },
  components : {
  },
  methods : {
    gomain() {
      this.$router.push("/main");
    }
  }
}
</script>

<style scoped>
#logo {
  position: absolute;
  top: 30px;
  left: 100px;
}
#book {
  width: 300px;
  transform: rotateX(45deg) rotateY(0deg) rotateZ(-45deg);
  position: relative;
  -webkit-transform: perspective(60em) rotateX(58deg) rotateZ(-34deg) skewY(-7deg);
          transform: perspective(60em) rotateX(58deg) rotateZ(-34deg) skewY(-7deg);
  box-shadow: -1.4em 1.7em 0.3em -0.3em rgba(0,0,0,0.8), -1.6em 1.8em 0.9em -0.2em rgba(0,0,0,0.5), 0.3em 1.9em 1.3em rgba(0,0,0,0.3);
  border-top-right-radius: 0.4em;
}
#left {
  float: left;
  box-sizing: border-box;
}
#right {
  float: right;
  box-sizing: border-box;
}
.frame1 {
  border-radius: 2px;
	box-shadow: 4px 8px 16px 0 rgba(0,0,0,0.1);
	overflow: hidden;
  background: #fff;
  color: #333;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	/*  generated gridient  */
	
/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#1e5799+26,1e5799+26,80edeb+100 */
background: #FFA000; /* Old browsers */
background: -moz-linear-gradient(45deg, #FFA000 26%, #FFA000 26%, #FFEB3B 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(45deg, #FFA000 26%,#FFA000 26%,#FFEB3B 100%);
background: linear-gradient(45deg, #FFA000 26%,#FFA000 26%,#FFEB3B 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#FFA000', endColorstr='#FFEB3B',GradientType=1 );
}
.frame2 {
  border-radius: 2px;
	box-shadow: 4px 8px 16px 0 rgba(0,0,0,0.1);
	overflow: hidden;
  background: #fff;
  color: #333;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	/*  generated gridient  */
	
/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#1e5799+26,1e5799+26,80edeb+100 */
background: #1e5799; /* Old browsers */
background: -moz-linear-gradient(45deg, #1e5799 26%, #1e5799 26%, #80edeb 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(45deg, #1e5799 26%,#1e5799 26%,#80edeb 100%);
background: linear-gradient(45deg, #1e5799 26%,#1e5799 26%,#80edeb 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1e5799', endColorstr='#80edeb',GradientType=1 );
}
.frame3 {
  border-radius: 2px;
	box-shadow: 4px 8px 16px 0 rgba(0,0,0,0.1);
	overflow: hidden;
  background: #fff;
  color: #333;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	/*  generated gridient  */
	
/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#1e5799+26,1e5799+26,80edeb+100 */
background: #F4511E; /* Old browsers */
background: -moz-linear-gradient(45deg, #F4511E 20%, #F4511E 20%, #F9A825 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(45deg, #F4511E 20%,#F4511E 20%,#F9A825 100%);
background: linear-gradient(45deg, #F4511E 20%,#F4511E 20%,#F9A825 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#F4511E', endColorstr='#F9A825',GradientType=1 );
}
.frame4 {
  border-radius: 2px;
	box-shadow: 4px 8px 16px 0 rgba(0,0,0,0.1);
	overflow: hidden;
  background: #fff;
  color: #333;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	/*  generated gridient  */
	
/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#1e5799+26,1e5799+26,80edeb+100 */
background: #C6FF00; /* Old browsers */
background: -moz-linear-gradient(45deg, #C6FF00 26%, #C6FF00 26%, #E6EE9C 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(45deg, #C6FF00 26%,#C6FF00 26%,#E6EE9C 100%);
background: linear-gradient(45deg, #C6FF00 26%,#C6FF00 26%,#E6EE9C 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#C6FF00', endColorstr='#E6EE9C',GradientType=1 );
}
</style>