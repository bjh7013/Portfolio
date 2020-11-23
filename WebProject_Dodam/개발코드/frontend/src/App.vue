<template>
<div>
  <v-app id="app" style="background-color:#FFF7AF">
     <!-- <img id="js-carrot" class="carrot" src="./assets/images/carrot.png" /> -->
    <!-- <img id="js-rabbit" class="rabbit" src="./assets/images/rabbit_left.gif" /> -->
    <back-ground-music style="position:absolute; z-index:100"></back-ground-music>
    <transition name="slide-fade" mode="out-in">

      <router-view/>
      
    </transition>
  </v-app>
</div>
</template>

<script>
import axios from 'axios'
import BackGroundMusic from './components/BackGroundMusic.vue'
export default {
  components: { 
    BackGroundMusic,
  },
  data(){
    return {
      whiteList: ['/', '/join', '/dodam', '/dodam/', '/join/'],
      timerId:null,
      timeOut:null,
    }
  },
  created(){
    if(this.$session.get('jwt')!=null){
      const jwt = this.$session.get('jwt');
          axios.defaults.headers.common['Authorization'] = jwt;
          // jwt값을 디코딩 후 userId, no, type을 vuex에 다시 저장 
          axios.get('/user/reget').then((Response)=>{
            this.$store.dispatch('LOGIN',Response.data); 
          })
      // axios.defaults.withCredentials = true;//CORS 요청시에도 클라이언트의 쿠키값 전달
    }else{
      if(!this.whiteList.includes(window.location.pathname)){
          this.$router.push('/')
      }
    }
  },methods:{
    // onClick(event){
    //   var carrot = document.getElementById('js-carrot');
    //   var rabbit = document.getElementById('js-rabbit');
    //   var rabbitCur = document.getElementById('js-rabbit').getClientRects()[0];
    //   if( this.timeOut!=null){
    //     clearTimeout(this.timeOut);
    //   }
    //   if(this.timerId!=null){
    //     clearInterval(this.timerId);
    //     this.timerId=null;
    //   }
    //   var ratio=50;
    
    //   carrot.style.left=event.clientX-20+"px";
    //   carrot.style.top = event.clientY+window.pageYOffset-20+"px";    
    //   carrot.style.display="block";

    //   if(event.clientX-20-rabbitCur.left<0){
    //     rabbit.classList.remove('flip');
    //   }else{
    //     rabbit.classList.add('flip');
    //   }
    //   var left=(event.clientX-20-rabbitCur.left)/ratio;
    //   var top=(event.clientY-20-rabbitCur.top)/ratio;
    //   var cnt=1;       
    //   this.timerId = setInterval(() => 
    //   {
    //     rabbit.style.left=(rabbitCur.left+left*cnt)+"px";
    //     rabbit.style.top=(rabbitCur.top+window.pageYOffset+top*cnt)+"px";
    //     cnt+=1;
    //   }, 20);

    //   this.timeOut=setTimeout(() => {
    //      clearInterval(this.timerId); 
    //      this.timerId=null
    //      carrot.style.display="none";
    //      console.log()
    //    }, ratio*20+10);      
    // }
  },
  computed : {
    userInfo() {
      return this.$store.getters.userInfo
    }
  },mounted(){
    // document.addEventListener('click', this.onClick);
  }
}
</script>

<style>
@font-face {
    font-family: 'TmoneyRoundWindExtraBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#app {
  font-family: 'TmoneyRoundWindExtraBold'; 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#js-rabbit{
    width: 40px;
    height: 40px;
    position: absolute;
    z-index: 1000;
    left:10px;
    top:10px;

}
#js-carrot{
    width: 40px;
    height: 40px;
    position: absolute;
    z-index: 1000;
    left:10px;
    top:10px;
    display: none;
}
.flip{
  -webkit-transform: scaleX(-1);
  -moz-transform: scaleX(-1);
  -o-transform: scaleX(-1);
  transform: scaleX(-1);

  filter: FlipH;
  -ms-filter: "FlipH";
}


</style>
