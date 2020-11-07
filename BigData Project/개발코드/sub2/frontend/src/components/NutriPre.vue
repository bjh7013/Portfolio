<template>
  <div>
    <!--오늘  -->
    <v-card
      class="mx-auto"
    >
      <v-card-text>
        <v-row>
          <div class="display-1" style="margin:10px auto;">
            {{ nowMonth }}월 {{ nowDay }}일 ({{ nowWeek }})
          </div>
        </v-row>
      </v-card-text>
    </v-card>
    
    <!-- 오늘 섭취량 -->
    <v-card>
      <v-col cols="12">
        <v-card-text class="px-0">
          <v-row class="px-5">
            <div>
              <h3 class="pt-1">오늘 섭취량</h3>
            </div>
            <v-spacer />
            <v-btn small class="main-btn">
              <v-icon>
                mdi-calendar
              </v-icon>
              <router-link to="/calender" style="color:black;">더보기</router-link>
            </v-btn>
          </v-row>
          <br>
          <v-container class="main-cards daily-nutri">
            
            <!-- <b-progress :value="dailyCal" :max="dailyCalGoal" variant="success" show-progress animated></b-progress>
            <b-progress class="mt-2" :max="max" show-value>
              <b-progress-bar :value="value * (6 / 10)" variant="success"></b-progress-bar>
              <b-progress-bar :value="value * (2.5 / 10)" variant="warning"></b-progress-bar>
              <b-progress-bar :value="value * (1.5 / 10)" variant="danger"></b-progress-bar>
            </b-progress> -->

            <strong>총 섭취량 <span style="color: red;">{{ Math.round(dailyCal*10)/10 }}</span> / {{ dailyCalGoal }}kcal</strong>
            <b-progress height="25px" :max="dailyCalGoal" style="background-color:rgb(180, 217, 181);" show-progress animated>
              <b-progress-bar :value="dailyCal" :label="`${((dailyCal / dailyCalGoal) * 100).toFixed(0)}%`" variant="success" />
            </b-progress>
            <!-- <v-progress-linear
              v-model="caloriePer"
              height="25"
              class="progress-linear"
            >
              <strong>{{ Math.ceil(caloriePer) }}%</strong>
            </v-progress-linear> -->
            <br>
            <v-row>
              <v-col>
                <strong>탄수화물 <span style="color: red;">{{ Math.round(dailyCarb*10)/10 }}</span> / {{ dailyCarbGoal }}g</strong>
                <b-progress height="25px" :max="dailyCarbGoal" style="background-color:rgb(162, 218, 227);" show-progress animated>
                  <b-progress-bar :value="dailyCarb" :label="`${((dailyCarb / dailyCarbGoal) * 100).toFixed(0)}%`" variant="info" />
                </b-progress>
                <!-- <v-progress-linear
                  v-model="carboPer"
                  height="25"
                  color="orange"
                  class="progress-linear"
                >
                  <strong>{{ Math.ceil(carboPer) }}%</strong>
                </v-progress-linear> -->
              </v-col>
              <v-col>
                <strong>단백질 <span style="color: red;">{{ Math.round(dailyPro*10)/10 }}</span> / {{ dailyProGoal }}g</strong>
                <b-progress height="25px" :max="dailyProGoal" style="background-color:rgb(255, 230, 156);" show-progress animated>
                  <b-progress-bar :value="dailyPro" :label="`${((dailyPro / dailyProGoal) * 100).toFixed(0)}%`" variant="warning" />
                </b-progress>
                <!-- <v-progress-linear
                  v-model="proteinPer"
                  height="25"
                  color="green"
                  class="progress-linear"
                >
                  <strong>{{ Math.ceil(proteinPer) }}%</strong>
                </v-progress-linear> -->
              </v-col>
              <v-col>
                <strong>지방 <span style="color: red;">{{ Math.round(dailyFat*10)/10 }}</span> / {{ dailyFatGoal }}g</strong>
                <b-progress height="25px" :max="dailyFatGoal" style="background-color:rgb(241, 174, 181);" show-progress animated>
                  <b-progress-bar :value="dailyFat" :label="`${((dailyFat / dailyFatGoal) * 100).toFixed(0)}%`" variant="danger" />
                </b-progress>
                <!-- <v-progress-linear
                  v-model="fatPer"
                  height="25"
                  color="blue"
                  class="progress-linear"
                >
                  <strong>{{ Math.ceil(fatPer) }}%</strong>
                </v-progress-linear> -->
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-col>
    </v-card>
  </div>
</template>

<script>
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios';
Vue.use(BootstrapVue)
export default {
  name: 'Nutripre',
  data() {
    return {
      value: 1500,
      value2: 100,
      value3: 10,
      today: '',
      // 기초대사량 -> 필요한가? 
      // ㄴRe: 글쎄,,
      BMR:1,
      // 칼로리
      caloriePer: 0.0,
      dailyCal: 0,
      dailyCalGoal: 0,
      // 탄수화물
      carboPer: 0.0,
      dailyCarb: 0,
      dailyCarbGoal: 0,
      // 단백질
      proteinPer: 0.0,
      dailyPro: 0,
      dailyProGoal: 0,
      // 지방
      fatPer: 0.0,
      dailyFat: 0,
      dailyFatGoal: 0,

      // 먹은 음식 
      diethistory_breakfast: [],
      diethistory_lunch: [],
      diethistory_dinner: [],
      diethistory_snack: [],
      breakfast_nut: [],
      lunch_nut: [],
      dinner_nut: [],
      snack_nut: [],
      breakfast_Cal: 0,
      breakfast_Carb: 0,
      breakfast_Pro: 0,
      breakfast_Fat: 0,
      lunch_Cal: 0,
      lunch_Carb: 0,
      lunch_Pro: 0,
      lunch_Fat: 0,
      dinner_Cal: 0,
      dinner_Carb: 0,
      dinner_Pro: 0,
      dinner_Fat: 0,
      snack_Cal: 0,
      snack_Carb: 0,
      snack_Pro: 0,
      snack_Fat: 0,

      // 현재 날짜
      nowMonth: '',
      nowDay: '',
      nowWeek: '',

      labels: ['SU', 'MO', 'TU', 'WED', 'TH', 'FR', 'SA'],
        time: 0,
        forecast: [
          { day: 'Tuesday', icon: 'mdi-white-balance-sunny', temp: '24\xB0/12\xB0' },
          { day: 'Wednesday', icon: 'mdi-white-balance-sunny', temp: '22\xB0/14\xB0' },
          { day: 'Thursday', icon: 'mdi-cloud', temp: '25\xB0/15\xB0' },
        ],
    }
  },
  mounted() {
    // 현재 날짜
    var now = new Date();
    var todayYear = now.getFullYear()
    var todayMonth = now.getMonth() + 1
    if(todayMonth < 10){
      todayMonth = "0" + todayMonth;
    }
    var todayDate = now.getDate()
    this.today = todayYear + '-' + todayMonth + '-' + todayDate

    this.nowMonth = todayMonth
    this.nowDay = todayDate
    var todayWeek = now.getDay()
    if (todayWeek == 1) {
      this.nowWeek = '월'
    } else if (todayWeek == 2) {
      this.nowWeek = '화'
    } else if (todayWeek == 3) {
      this.nowWeek = '수'
    } else if (todayWeek == 4) {
      this.nowWeek = '목'
    } else if (todayWeek == 5) {
      this.nowWeek = '금'
    } else if (todayWeek == 6) {
      this.nowWeek = '토'
    } else if (todayWeek == 0) {
      this.nowWeek = '일'
    }
  },
  created(){
    this.getNutGoal();
    this.getDailyDietHistory();
    this.getMyNut();
  },
  methods: {
    // 내 영양상태 불려오기
    getMyNut(){
      this.getNutNow();
      this.getNutPer();
    },
    getNutGoal(){
      axios.get(`/apis/mypage`)
      .then((response) => {
        this.dailyCalGoal = response.data.plan_cal;
        this.dailyCarbGoal = response.data.plan_carb;
        this.dailyProGoal = response.data.plan_protein;
        this.dailyFatGoal = response.data.plan_fat;
      })
    },
    getNutNow(){
      this.dailyCal = this.breakfast_Cal + this.lunch_Cal + this.dinner_Cal + this.snack_Cal;
      this.dailyCarb = this.breakfast_Carb + this.lunch_Carb + this.dinner_Carb + this.snack_Carb;
      this.dailyPro = this.breakfast_Pro + this.lunch_Pro + this.dinner_Pro + this.snack_Pro;
      this.dailyFat = this.breakfast_Fat + this.lunch_Fat + this.dinner_Fat + this.snack_Fat;
    },
    getNutPer(){
      if(this.dailyCal != 0){
        this.caloriePer = this.dailyCal / this.dailyCalGoal * 100;
      }
      if(this.dailyCarb != 0){
        this.carboPer = this.dailyCarb / this.dailyCarbGoal * 100;
      }
      if(this.dailyPro != 0){
        this.proteinPer = this.dailyPro / this.dailyProGoal * 100;
      }
      if(this.dailyFat != 0){
        this.fatPer = this.dailyFat / this.dailyFatGoal * 100;
      }
    },
    getDailyDietHistory(){
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth()+1;
      var yyyy = today.getFullYear();

      today = yyyy*10000+mm*100+dd;
      var tomorrow = yyyy*10000+mm*100+dd+1;
      // console.log(today);
      // console.log(tomorrow);

      axios.get(`/apis/diethistory/${today}/${tomorrow}`)
      .then((response) => {
        // console.log(response.data);
        for(let history of response.data){
          
          // 아침 식사
          if(history.eat_type == "b"){
            let Cal = 0;
            Cal = history.food.cal / history.food.amount * history.eat_amount;
            this.breakfast_Cal += Cal;
            axios.get(`/apis/foodnutrient/${history.food.food_no}`)
            .then((nutrients)=>{
              this.breakfast_nut = nutrients.data;
              // console.log(this.breakfast_nut);
              // console.log(history);
              let Carb = 0;
              let Pro = 0;
              let Fat = 0;
              for(let nut of nutrients.data){
                if(nut.nutrient == "carb"){
                  Carb = nut.content / history.food.amount * history.eat_amount;
                  this.breakfast_Carb += Carb;
                }
                else if(nut.nutrient == "pro"){
                  Pro = nut.content / history.food.amount * history.eat_amount;
                  this.breakfast_Pro += Pro;
                }
                else if(nut.nutrient == "fat"){
                  Fat = nut.content / history.food.amount * history.eat_amount;
                  this.breakfast_Fat += Fat;
                }
                this.getMyNut();
              }
              this.diethistory_breakfast.push({
                eat_date: history.eat_date, 
                eat_type: history.eat_type,
                food: history.food,
                history_no: history.history_no,
                eat_amount: history.eat_amount,
                correct_cal: Cal,
                correct_carb: Carb,
                correct_pro: Pro,
                correct_fat: Fat
              });
            })
          } // if(아침)

          // 점심 식사
          if(history.eat_type == "l"){
            let Cal = 0;
            Cal = history.food.cal / history.food.amount * history.eat_amount;
            this.lunch_Cal += Cal;
            axios.get(`/apis/foodnutrient/${history.food.food_no}`)
            .then((nutrients)=>{
              this.lunch_nut = nutrients.data;
              // console.log(this.lunch_nut);
              // console.log(history);
              let Carb = 0;
              let Pro = 0;
              let Fat = 0;
              for(let nut of nutrients.data){
                if(nut.nutrient == "carb"){
                  Carb = nut.content / history.food.amount * history.eat_amount;
                  this.lunch_Carb += Carb;
                }
                else if(nut.nutrient == "pro"){
                  Pro = nut.content / history.food.amount * history.eat_amount;
                  this.lunch_Pro += Pro;
                }
                else if(nut.nutrient == "fat"){
                  Fat = nut.content / history.food.amount * history.eat_amount;
                  this.lunch_Fat += Fat;
                }
                this.getMyNut();
              }
              this.diethistory_lunch.push({
                eat_date: history.eat_date, 
                eat_type: history.eat_type,
                food: history.food,
                history_no: history.history_no,
                eat_amount: history.eat_amount,
                correct_cal: Cal,
                correct_carb: Carb,
                correct_pro: Pro,
                correct_fat: Fat
              });
            })
          } // if(점심)

          // 저녁 식사
          if(history.eat_type == "d"){
            let Cal = 0;
            Cal = history.food.cal / history.food.amount * history.eat_amount;
            this.dinner_Cal += Cal;
            axios.get(`/apis/foodnutrient/${history.food.food_no}`)
            .then((nutrients)=>{
              this.dinner_nut = nutrients.data;
              // console.log(this.dinner_nut);
              // console.log(history);
              let Carb = 0;
              let Pro = 0;
              let Fat = 0;
              for(let nut of nutrients.data){
                if(nut.nutrient == "carb"){
                  Carb = nut.content / history.food.amount * history.eat_amount;
                  this.dinner_Carb += Carb;
                }
                else if(nut.nutrient == "pro"){
                  Pro = nut.content / history.food.amount * history.eat_amount;
                  this.dinner_Pro += Pro;
                }
                else if(nut.nutrient == "fat"){
                  Fat = nut.content / history.food.amount * history.eat_amount;
                  this.dinner_Fat += Fat;
                }
                this.getMyNut();
              }
              this.diethistory_dinner.push({
                eat_date: history.eat_date, 
                eat_type: history.eat_type,
                food: history.food,
                history_no: history.history_no,
                eat_amount: history.eat_amount,
                correct_cal: Cal,
                correct_carb: Carb,
                correct_pro: Pro,
                correct_fat: Fat
              });
            })
          } // if(저녁)

          // 간식 식사
          if(history.eat_type == "s"){
            let Cal = 0;
            Cal = history.food.cal / history.food.amount * history.eat_amount;
            this.snack_Cal += Cal;
            axios.get(`/apis/foodnutrient/${history.food.food_no}`)
            .then((nutrients)=>{
              this.snack_nut = nutrients.data;
              // console.log(this.snack_nut);
              // console.log(history);
              let Carb = 0;
              let Pro = 0;
              let Fat = 0;
              for(let nut of nutrients.data){
                if(nut.nutrient == "carb"){
                  Carb = nut.content / history.food.amount * history.eat_amount;
                  this.snack_Carb += Carb;
                }
                else if(nut.nutrient == "pro"){
                  Pro = nut.content / history.food.amount * history.eat_amount;
                  this.snack_Pro += Pro;
                }
                else if(nut.nutrient == "fat"){
                  Fat = nut.content / history.food.amount * history.eat_amount;
                  this.snack_Fat += Fat;
                }
                this.getMyNut();
              }
              this.diethistory_snack.push({
                eat_date: history.eat_date, 
                eat_type: history.eat_type,
                food: history.food,
                history_no: history.history_no,
                eat_amount: history.eat_amount,
                correct_cal: Cal,
                correct_carb: Carb,
                correct_pro: Pro,
                correct_fat: Fat
              });
            })
          } // if(간식)
        } // 반복문(b/l/d/s)
      })  // today diethistory list
      
    },
  },
}
</script>

<style>
  .v-calendar-daily__intervals-head {
    width: 0px;
  }
  .v-calendar-daily__head {
    margin-right: 0px !important;
    background-color: #75befa;
  }
  .progress-linear {
    border-radius: 50px;
  }
  .daily-nutri {
    max-width: 100% !important;
  }
</style>