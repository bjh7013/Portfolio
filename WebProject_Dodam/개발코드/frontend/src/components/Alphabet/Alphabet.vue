<template>
  <v-container>  
    
    <img src="../../assets/images/leftanimal.png" class="mimg leftimg"/>
    <img src="../../assets/images/rightanimal.png" class="mimg rightimg"/>
    <section>
      <div @click="gocontents" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>학습컨텐츠<p class="nomobile">로 돌아가기</p></div>
    </section>
    <div style="clear:both">
      <h1><b>[한글공부]</b></h1>
      <router-link to="/koreanconsonant">
        <v-img src="../../assets/images/consonant.jpg" aspect-ratio="1" class="aimg"/>
      </router-link>
      <router-link to="/koreandoubleconsonant">
        <v-img src="../../assets/images/doubleconsonant.jpg" aspect-ratio="1" class="aimg"/>
      </router-link>
      <router-link to="/koreanvowel">
        <v-img src="../../assets/images/vowel.jpg" aspect-ratio="1" class="aimg"/>
      </router-link>
      <router-link to="/koreandoublevowel">
        <v-img src="../../assets/images/doublevowel.png" aspect-ratio="1" class="aimg"/>
      </router-link>
      <router-link to="/koreanletter">
        <v-img src="../../assets/images/letter.jpg" aspect-ratio="1" class="aimg"/>
      </router-link>
    </div>
  </v-container>
</template>

<script>
export default {
  name: 'alphabet',
  methods: {
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
.aimg {
  border-radius: 30px;
  border: 3px solid #fff;
}

a {
  display: inline-block;
  position: relative;
  /* float: left; */
  width: 24%;
  height: 24%;
  min-width: 200px;
  min-height: 200px;
  margin: 1%;
}
h1 {
  font-family: 'Hi Melody', cursive; 
  color:#ff9642;
  font-size: 350%;
}

@media only screen and (max-width: 698px) {
  .nomobile{
    display:none;
  }
}
</style>