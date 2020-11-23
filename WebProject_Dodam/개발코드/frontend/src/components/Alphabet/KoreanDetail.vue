<template>
  <v-container>
    <section>
      <div @click="gocategory" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>{{ koreanlist[koreanno].cate }}목록</div>
    </section>
    <div v-if="tokki==1">  <!-- 성공 -->
      <h3>잘했어요! 다음 글자를 공부해봅시다!</h3>
      <v-img class="alphabet-img" src="../../assets/images/good.jpg" max-height="30%" max-width="15%" style="float:right; margin:11% auto;"></v-img>
      <v-img class="alphabet-img" src="../../assets/images/good2.jpg" max-height="30%" max-width="15%" style="float:left; margin:11% auto;"></v-img>
    </div>
    <div v-if="tokki==2"> <!-- 넥스트 -->
      <h3>다음 획을 써볼까요?</h3>
      <v-img class="alphabet-img" src="../../assets/images/next.jpg" max-height="30%" max-width="15%" style="float:left; margin:11% auto;"></v-img>
      <v-img class="alphabet-img" src="../../assets/images/next.jpg" max-height="30%" max-width="15%" style="float:right; margin:11% auto%;"></v-img>
    </div>
    <div v-if="tokki==3"> <!-- 실패 -->
      <h3>다시 해볼까요?</h3>
      <v-img class="alphabet-img" src="../../assets/images/nono.jpg" max-height="30%" max-width="15%" style="float:left; margin:11% auto;"></v-img>
      <v-img class="alphabet-img" src="../../assets/images/nono.jpg" max-height="30%" max-width="15%" style="float:right; margin:11% auto;"></v-img>
    </div>
    <div v-if="tokki==4"> <!-- 기본 -->
      <h3>따라 써볼까요?</h3>
      <v-img class="alphabet-img" src="../../assets/images/question2.jpg" max-height="30%" max-width="15%" style="float:left; margin:11% auto;"></v-img>
      <v-img class="alphabet-img" src="../../assets/images/question.jpg" max-height="30%" max-width="15%" style="float:right; margin:11% auto;"></v-img>
    </div>
    <v-row>
      <v-col>
        <div>
          <alphabet-writing v-on:success="test" @speak="speak" ref="canvas" v-if="flag" :letter="koreanlist[koreanno].name"></alphabet-writing>
          <v-row class="prev_next">
            <v-col>
              <template v-if="koreanno == 13 || koreanno == 23 || koreanno == 28 || koreanno == 39 || koreanno == 63">
                <i @click="behind" style="float:right" class="fas fa-angle-left fa-5x"></i>
              </template>
              <template v-else-if="koreanno < 63">
                <i @click="behind" style="float:right" class="fas fa-angle-left fa-5x"></i>
              </template>
            </v-col>
            <v-col>
              <v-img src="../../assets/images/whale.png" style="cursor:pointer" @click="speak" aspect-ratio="1" class="img-whale">
                <div class="whale-content">
                  <h2>{{koreanlist[koreanno].text}}</h2>
                </div>
              </v-img>
            </v-col>
            <v-col>
              <template v-if="koreanno == 13 || koreanno == 23 || koreanno == 28 || koreanno == 39 || koreanno == 63">
                <i @click="finish" style="float:left" class="fas fa-angle-right fa-5x"></i>
              </template>
              <template v-else-if="koreanno < 63">
                <i @click="next" style="float:left" class="fas fa-angle-right fa-5x"></i>
              </template>
            </v-col>
          </v-row>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AlphabetWriting from '@/components/AlphabetWriting'

export default {
  name: 'koreandetail',
  components:{'AlphabetWriting':AlphabetWriting},
  data(){
    return {
      tokki : 4,
      flag: true,
      namelist: ['자음', '모음', '쌍자음', '이중모음', '글자'],
      koreanno : 0,
      koreanlist: [
        { id:"0", name: 'ㄱ', text: '기역', cate: '자음' }, 
        { id:"1", name: 'ㄴ', text: '니은', cate: '자음' }, 
        { id:"2", name: 'ㄷ', text: '디귿', cate: '자음' }, 
        { id:"3", name: 'ㄹ', text: '리을', cate: '자음' }, 
        { id:"4", name: 'ㅁ', text: '미음', cate: '자음' }, 
        { id:"5", name: 'ㅂ', text: '비읍', cate: '자음' }, 
        { id:"6", name: 'ㅅ', text: '시옷', cate: '자음' }, 
        { id:"7", name: 'ㅇ', text: '이응', cate: '자음' }, 
        { id:"8", name: 'ㅈ', text: '지읒', cate: '자음' }, 
        { id:"9", name: 'ㅊ', text: '치옻', cate: '자음' }, 
        { id:"10", name: 'ㅋ', text: '키읔', cate: '자음' }, 
        { id:"11", name: 'ㅌ', text: '티읕', cate: '자음' }, 
        { id:"12", name: 'ㅍ', text: '피읖', cate: '자음' }, 
        { id:"13", name: 'ㅎ', text: '히읗', cate: '자음' }, 
        { id:"14", name: 'ㅏ', text: '아', cate: '모음' }, 
        { id:"15", name: 'ㅑ', text: '야', cate: '모음' }, 
        { id:"16", name: 'ㅓ', text: '어', cate: '모음' }, 
        { id:"17", name: 'ㅕ', text: '여', cate: '모음' }, 
        { id:"18", name: 'ㅗ', text: '오', cate: '모음' }, 
        { id:"19", name: 'ㅛ', text: '요', cate: '모음' }, 
        { id:"20", name: 'ㅜ', text: '우', cate: '모음' }, 
        { id:"21", name: 'ㅠ', text: '유', cate: '모음' }, 
        { id:"22", name: 'ㅡ', text: '으', cate: '모음' }, 
        { id:"23", name: 'ㅣ', text: '이', cate: '모음' }, 
        { id:"24", name: 'ㄲ', text: '쌍기역', cate: '쌍자음' }, 
        { id:"25", name: 'ㄸ', text: '쌍디귿', cate: '쌍자음' }, 
        { id:"26", name: 'ㅃ', text: '쌍비읍', cate: '쌍자음' }, 
        { id:"27", name: 'ㅆ', text: '쌍시옷', cate: '쌍자음' }, 
        { id:"28", name: 'ㅉ', text: '쌍지읒', cate: '쌍자음' }, 
        { id:"29", name: 'ㅐ', text: '애', cate: '이중모음' }, 
        { id:"30", name: 'ㅒ', text: '얘', cate: '이중모음' }, 
        { id:"31", name: 'ㅔ', text: '에', cate: '이중모음' }, 
        { id:"32", name: 'ㅖ', text: '예', cate: '이중모음' }, 
        { id:"33", name: 'ㅘ', text: '와', cate: '이중모음' }, 
        { id:"34", name: 'ㅙ', text: '왜', cate: '이중모음' }, 
        { id:"35", name: 'ㅚ', text: '외', cate: '이중모음' },
        { id:"36", name: 'ㅝ', text: '워', cate: '이중모음' }, 
        { id:"37", name: 'ㅞ', text: '웨', cate: '이중모음' }, 
        { id:"38", name: 'ㅟ', text: '위', cate: '이중모음' }, 
        { id:"39", name: 'ㅢ', text: '의', cate: '이중모음' },
        { id:"40", name: '가', text: '가', cate: '글자' }, 
        { id:"41", name: '나', text: '나', cate: '글자' }, 
        { id:"42", name: '다', text: '다', cate: '글자' }, 
        { id:"43", name: '라', text: '라', cate: '글자' }, 
        { id:"44", name: '마', text: '마', cate: '글자' }, 
        { id:"45", name: '바', text: '바', cate: '글자' }, 
        { id:"46", name: '사', text: '사', cate: '글자' }, 
        { id:"47", name: '아', text: '아', cate: '글자' }, 
        { id:"48", name: '자', text: '자', cate: '글자' }, 
        { id:"49", name: '차', text: '차', cate: '글자' }, 
        { id:"50", name: '카', text: '카', cate: '글자' }, 
        { id:"51", name: '타', text: '타', cate: '글자' }, 
        { id:"52", name: '파', text: '파', cate: '글자' }, 
        { id:"53", name: '하', text: '하', cate: '글자' }, 
        { id:"54", name: '값', text: '값', cate: '글자' }, 
        { id:"55", name: '고', text: '고', cate: '글자' }, 
        { id:"56", name: '긁', text: '긁', cate: '글자' }, 
        { id:"57", name: '너', text: '너', cate: '글자' }, 
        { id:"58", name: '넓', text: '넓', cate: '글자' }, 
        { id:"59", name: '닮', text: '닮', cate: '글자' }, 
        { id:"60", name: '며', text: '며', cate: '글자' }, 
        { id:"61", name: '몫', text: '몫', cate: '글자' }, 
        { id:"62", name: '빠', text: '빠', cate: '글자' }, 
        { id:"63", name: '티', text: '티', cate: '글자' }, 
      ],
    }
  },
  created(){
    this.koreanno = this.$route.params.koreanno
    for(let i in this.koreanlist){
      if(this.koreanlist[i].id == this.koreanno){
        this.koreanno = i
        break
      }
    }
  },
  methods: {
    speak(){
      new Audio(`${this.$store.getters.BACKEND_URL}/letter/${this.koreanlist[this.koreanno].name}.mp3`).play()
    },
    gocategory() {
      if(this.koreanno >= 0 && this.koreanno <= 13){
        this.$router.push('/koreanconsonant')
      }
      if(this.koreanno >= 14 && this.koreanno <= 23){
        this.$router.push('/koreanvowel')
      }
      if(this.koreanno >= 24 && this.koreanno <= 28){
        this.$router.push('/koreandoubleconsonant')
      }
      if(this.koreanno >= 29 && this.koreanno <= 39){
        this.$router.push('/koreandoublevowel')
      }
      if(this.koreanno >= 40 && this.koreanno <= 63){
        this.$router.push('/koreanletter')
      }
    },
    recreate(){
      this.flag = false;
      let timerId = setInterval(()=>{
        if(!this.$refs.canvas){
          clearInterval(timerId)
          this.flag = true;
        }
      },100)
      
    },
    next() {
      this.koreanno = parseInt(this.koreanno) + parseInt(1)
      this.$router.push(`/koreandetail/${this.koreanno}`)
      this.recreate()
      this.tokki = 4
    },
    behind() {
      if(this.koreanno == 0){
        return;
      }
      if(this.koreanno == 14){
       return;
      }
      if(this.koreanno == 24){
       return;
      }
      if(this.koreanno == 29){
        return;
      }
      if(this.koreanno == 40){
       return;
      }
      this.koreanno = this.koreanno - 1
      this.recreate()
    },
    finish() {
      if(this.koreanno == 13){
        this.$router.push('/alphabet')
        alert('와! 자음공부를 완료했어요!')
      }
      if(this.koreanno == 23){
        this.$router.push('/alphabet')
        alert('와! 모음공부를 완료했어요!')
      }
      if(this.koreanno == 28){
        this.$router.push('/alphabet')
        alert('와! 쌍자음공부를 완료했어요!')
      }
      if(this.koreanno == 39){
        this.$router.push('/alphabet')
        alert('와! 이중모음공부를 완료했어요!')
      }
      if(this.koreanno == 63){
        this.$router.push('/alphabet')
        alert('와! 글자공부를 완료했어요!')
      }
    },
    test(val){
      this.tokki = val
    }
  }
}
</script>

<style scoped>
.container {
  padding: 0;
}
.back_menu {
  display: flex;
  font-family: 'Jua', sans-serif;
  font-size: 250%;
  color:#ffba00;
  margin: 10px;
}
h3 {
  font-family: 'Jua', sans-serif;
  margin: 0;
  font-size: 250%;
}
article {
  font-size: 50px;
}
span {
  font-size: 250px;
}
.prev_next .col {
  padding: 0px;
  margin: -8% auto;  
}
.img-whale{
  position: relative;                                                            
  width: 70%;
  height: 120%;
  background-size: cover;
}

.whale-content{
  font-family: 'Jua', sans-serif;
  position: absolute;
  top:60%;
  left:40%;
  transform: translate(-50%, -50%);                                                                   
  font-size:5rem;
  z-index: 2;
  text-align: center;
}

.row{
  padding : auto !important;
  display: flex;
  justify-content: center;
  align-items: center;
}
.img-whale{
  margin: 1vh auto !important;
}

div .col {
  padding: 0;  
}

@media only screen and (max-width: 650px) {
  .alphabet-img{
    display: none;
  }
}
</style>