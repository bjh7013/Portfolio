<template>
  <v-container>
    <section>
      <div @click="gocontents" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i><p class="nomobile">학습컨텐츠로 </p>돌아가기</div>
    </section>
    <section>
      <div class="nomobile">
        <img src="../../assets/images/leftanimal.png" class="mimg leftimg"/>
        <img src="../../assets/images/rightanimal.png" class="mimg rightimg"/>
    
      <div v-for="i in 40" :key="i">
        <a><article @click="dic_test(i)"><span>{{i}}</span></article></a>
      </div>
      </div>
      <div class="ismobile">
        모바일은 지원하지 않습니다. 죄송합니다.
      </div>
    </section>
  </v-container>
</template>
<script>
export default {
  name: 'dictation',
  methods : {
    dic_test(number) {
      this.$router.push(`/dictationtest/${number}`)
    },
    gocontents() {
      this.$router.push('/contentsmain')
    }
  }
}
</script>

<style scoped>
.mimg{
  
  width: auto;
  height: 90vh;    
  position: fixed;
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

.back_menu {
  display: flex;
  font-family: 'Jua', sans-serif;
  font-size: 40%;
  color:#ffba00;
}

section {
  margin: auto;
  width: 80%;
  font-size: 100px;
  text-align: center;
}

a {
  font-family: 'Hi Melody', cursive;
  display: inline-block;
  position: relative;
  float: left;
  width: 150px;
  height: 150px;
  margin: 10px;
}

article {
  cursor: pointer;
  display: block;
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: orange;
  box-shadow: 0 0 6px -2px red, 0 0 45px -20px red;
  color: #fff;
  transform: perspective(300px) rotateY(0deg);
  transition: transform 0.4s linear, background-color 0s linear 0.2s, color 0s linear 0.2s;
}
a:hover article {
  transform: perspective(300px) rotateY(180deg);
  color: #eee;
  transition: transform 0.2s linear, background-color 0s linear 0.1s, color 0s linear 0.1s;
}
a:nth-child(1):hover article {
  background-color: greenyellow;
}
a:nth-child(2):hover article {
  background-color: #00ACED;
}
a:nth-child(3):hover article {
  background-color: #2C4762;
}
a:nth-child(4):hover article {
  background-color: #CB2027;
}
a:nth-child(5):hover article {
  background-color: #1769FF;
}
a:nth-child(6):hover article {
  background-color: #4183C4;
}
a:nth-child(7):hover article {
  background-color: #3399CC;
}
a:nth-child(8):hover article {
  background-color: #EA4C89;
}
a:nth-child(9):hover article {
  background-color: #3F729B;
}
a:nth-child(10):hover article {
  background-color: #FF0084;
}
a:nth-child(11):hover article {
  background-color: #F26300;
}
a:nth-child(12):hover article {
  background-color: #445566;
}
a:nth-child(13):hover article {
  background-color: greenyellow;
}
a:nth-child(14):hover article {
  background-color: #00ACED;
}
a:nth-child(15):hover article {
  background-color: #2C4762;
}
a:nth-child(16):hover article {
  background-color: #CB2027;
}
a:nth-child(17):hover article {
  background-color: #1769FF;
}
a:nth-child(18):hover article {
  background-color: #4183C4;
}
a:nth-child(19):hover article {
  background-color: #3399CC;
}
a:nth-child(20):hover article {
  background-color: #EA4C89;
}
a:nth-child(21):hover article {
  background-color: #3F729B;
}
a:nth-child(22):hover article {
  background-color: #FF0084;
}
a:nth-child(23):hover article {
  background-color: #F26300;
}
a:nth-child(24):hover article {
  background-color: #445566;
}
span {
  transition: transform 0s linear 0.2s, text-shadow 0s linear 0.2s;
  display: block;
  -webkit-font-smoothing: subpixel-antialiased;
  font-smoothing: antialiased;
}
a:hover span {
  transition: transform 0s linear 0.1s, text-shadow 0s linear 0.1s;
  transform: scale(-1, 1);
  text-shadow: 0 0 50px rgba(255, 255, 255, 0.5);
}
.ismobile{
  display: none;
}
@media (max-width: 500px) {
  .nomobile{
    display: none;
  }
  .ismobile{
    display: block;
    font-size: 40px;
  }

}

</style>