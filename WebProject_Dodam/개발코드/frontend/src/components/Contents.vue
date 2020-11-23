<template>
    <div class="contents-items">
      <div class="contents-item">
        <router-link to="/alphabet">
          <v-img src="../assets/images/alphabet2.jpg" aspect-ratio="1" class="cimmimg"/>
        </router-link>
      </div>
      <div class="contents-item">
        <router-link to="/wordcategory">
          <v-img src="../assets/images/word2.jpg" aspect-ratio="1" class="cimmimg"/>
        </router-link>
      </div>
      <div class="contents-item">
        <router-link to="/dictation">
          <v-img src="../assets/images/dictation.jpg" aspect-ratio="1" class="cimmimg"/>
        </router-link>
      </div>
    </div>
</template>

<script>
export default {
  name: 'contents',

}
</script>

<style scoped>

.contents-items{
  display:flex;
  justify-content: space-around;
  /* flex-wrap:wrap-reverse; */
  flex-wrap:wrap;
  width: 100vw;
  
  max-width: 1600px;
  margin:0 auto;
  padding:0;
}
.cimmimg {
  border-radius: 30px;
  border: 5px solid #FFA680;
}
.contents-item{
  margin: 2vw;
  width: 29vw;
  height: 30vw;
  min-width: 200px;
  min-height: 200px;
  max-width: 400px;
  max-height: 400px;

}
</style>