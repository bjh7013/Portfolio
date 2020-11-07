<template>
  <div class="home2">
    <v-card>
      .<br>.
    </v-card>
  <!-- <div> -->
    <br><br>
    <v-container>
      <v-row>
        <v-col cols="3">
          <v-card id="main_card">
            <v-img max-width="100%" max-height="550" src="https://picsum.photos/id/1073/250/550" img-alt="Image" img-top>작품ㅋ</v-img>
          </v-card>
        </v-col>
        <v-col cols="3">
          <v-card id="main_card">
            <v-img max-width="100%" max-height="550" src="https://picsum.photos/id/367/250/550" img-alt="Image" img-top>게시판ㅋ</v-img>
          </v-card>
        </v-col>
        <v-col cols="3">
          <v-card id="main_card">
            <v-img max-width="100%" max-height="550" src="https://picsum.photos/id/445/250/550" img-alt="Image" img-top>블로그</v-img>
          </v-card>
        </v-col>
        <v-col cols="3">
          <v-card id="main_card">
            <v-img max-width="100%" max-height="550" src="https://picsum.photos/id/513/250/550" img-alt="Image" img-top>유저</v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  <!-- </div> -->

  </div>
</template>

<script>
  export default {
  }
</script>

<style scoped>
#main_card {
  height: 550px;
  text-align: center;
}
</style>