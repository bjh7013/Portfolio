<template>
  <!-- 1. 평가하면 넘어가기 -->
  <v-container>
    <p style="text-align:center">정확한 음식 추천을 위하여 당신의 입맛을 측정하는 테스트입니다.</p>
    <v-row align="center" justify="center">
      <div v-for="(food, i) in foods" :key="i" style="text-align:center">
        <div v-if="count==i">
          <img :src="getSrc(food.img)" width="400px" height="400px">
          <h3>{{ food.name }}</h3><br>
        </div>
      </div>
    </v-row>

    <div style="text-align:center">
      <v-btn class="score 1" @click="pref(1)">😨너무싫어</v-btn>
      <v-btn class="score 2" @click="pref(2)">😕싫어</v-btn>
      <v-btn class="score 3" @click="pref(3)">🙂쏘쏘</v-btn>
      <v-btn class="score 4" @click="pref(4)">😆좋아</v-btn>
      <v-btn class="score 5" @click="pref(5)">😍완전좋아</v-btn>
    </div>
    <div style="text-align:center">
      <br>
      <template v-if="count < foods.length - 1">
        <v-btn @click="countdown">이전</v-btn>
        <v-btn @click="countup">다음</v-btn>
      </template>
      <template v-else>
        <v-btn @click="submit">제출</v-btn>
      </template>
    </div>
  </v-container>
</template>

<script>
import Axios from 'axios';
// import Test from '@/components/Test.vue'
export default {
  name:"TestFood",
  components: {
    // Test,
  },
  data() {
    return {
      count : 0,
      tempimg : "@/images/ramyeon.jpg",
      foodpref:{},
      foods : [
        {id: 1, food_id: 6990, name: "라면", img: "ramyeon"},
        {id: 2, food_id: 7083, name: "떡볶이", img: "tteokbokki"},
        {id: 3, food_id: 222, name: "김치찌개", img: "kimchisoup"},
        {id: 4, food_id: 6837, name: "찐고구마", img: "sweetpotato"},
        {id: 5, food_id: 7009, name: "쫄면", img: "jjolnoodle"},
        {id: 6, food_id: 6994, name: "물냉면", img: "coldnoodle"},
        {id: 7, food_id: 6848, name: "꽁치구이", img: "fish"},
        {id: 8, food_id: 7374, name: "후라이드치킨", img: "chicken"},
        {id: 9, food_id: 276, name: "등심돈까스", img: "donkatsu"},
        {id: 10, food_id: 6906, name: "삼계탕", img: "chickensoup"},
        {id: 11, food_id: 7340, name: "달걀찜", img: "eggcustard"},
        {id: 12, food_id: 70, name: "찹쌀떡", img: "ricecake"},
        {id: 13, food_id: 7211, name: "육회", img: "rawmeat"},
        {id: 14, food_id: 7169, name: "닭가슴살샐러드", img: "chickenbreast"},
        {id: 15, food_id: 6860, name: "삼겹살구이", img: "porkbelly"},
      ],
    }
  },
  mounted(){
    $('.score').click(function() {
      $('.score').removeClass('selected')
      $(this).addClass('selected')
    })
  }, 
  methods: {
    pref(score){
      this.foodpref[this.foods[this.count].food_id] = score;
    },
    submit(){
      Axios.post('/apis/preftest',this.foodpref)
      .then(() => {
        alert("테스트 완료")
        this.$router.push('/');
      })
      .catch(() => {
        alert('error')
      })
    },
    getSrc(name) {
      var images = require.context('@/images/', false, /\.jpg$/);
      return images('./' + name + '.jpg')
    },
    countup() {
      if(this.count==14){
        return;
      }
      if(!this.foodpref[this.foods[this.count].food_id]){
        alert('선호도를 입력해주세요!')
        return;
      }

      this.count = this.count+1

      $('.score').removeClass('selected')
      if(this.foodpref[this.foods[this.count].food_id]){
        $(`.${this.foodpref[this.foods[this.count].food_id]}`).addClass('selected')
      }
    },
    countdown() {
      if(this.count==0){
        return;
      }
      this.count = this.count-1

      $('.score').removeClass('selected')
      if(this.foodpref[this.foods[this.count].food_id]){
        $(`.${this.foodpref[this.foods[this.count].food_id]}`).addClass('selected')
      }
    }
  },
  // created() {

  // },
  // }
}
</script>
<style>
.selected{
  background-color:grey !important;
}
</style>
