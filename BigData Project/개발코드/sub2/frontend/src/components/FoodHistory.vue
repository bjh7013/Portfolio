<template>
  <!-- 오늘 먹은 식단 -->
  <div>
    <v-card class="mb-5">
      <v-col cols="12">
        <v-card-text class="px-0">
          <v-row class="px-5">
            <div>
              <h3 class="pt-1">오늘의 식단</h3>
            </div>
            <v-spacer />
          </v-row>
          <br>
          <v-row class="px-5">
            <!-- 아침 -->
            <v-container class="main-cards daily-diet col-md-6 col-12" style="border-left:10px solid white; border-right:10px solid white; max-height:450px;">
              <v-row class="px-5">
                <div class="mr-3">
                  <h4 class="pt-2" style="font-weight:bold;">
                    아침 :
                    <span>{{ Math.round(breakfast_Cal*10)/10 }} </span>
                    kcal
                  </h4>
                </div>
                <v-spacer />
                <v-btn small icon style="margin-top:5px;" @click="writefood('아침', '/')">
                  <v-icon>
                    mdi-pencil
                  </v-icon>
                </v-btn>
              </v-row>
              <hr style="margin:0px;">
              <div v-if="diethistory_breakfast.length <= 0" style="line-height:300px;">
                <p style="text-align:center;">식단을 입력해주세요</p>
              </div>
              <div v-else class="scroll-box" style="height:315px; overflow:auto;">
                <div v-for="breakfast in diethistory_breakfast" :key="breakfast.history_no" style="maring-bottom:50px;">
                  <v-list-item two-line style="padding:0px;">
                    <v-list-item-content style="padding-bottom:0px; margin-top:5px;">
                      <v-list-item-title>
                        <v-row style="padding:0px 20px;">
                          <span style="padding:10px 0px 0px;">
                            {{ breakfast.food.food_name }} ({{ breakfast.eat_amount }}g) 
                          </span>
                          <v-spacer />
                          <v-btn small icon style="margin:5px 0px;" @click="dietDelete(breakfast.history_no)">
                            <v-icon>
                              mdi-close
                            </v-icon>
                          </v-btn>
                        </v-row>
                      </v-list-item-title>
                      <v-list-item-subtitle>
                        <p style="margin:0px 0px 5px 8px;">
                          칼로리 : {{ Math.round(breakfast.correct_cal*10)/10 }}kcal
                        </p>
                        <p style="margin:0px 10px 0px 8px;">
                          탄수화물 : {{ Math.round(breakfast.correct_carb*10)/10 }}g | 
                          단백질 : {{ Math.round(breakfast.correct_pro*10)/10 }}g | 
                          지방 : {{ Math.round(breakfast.correct_fat*10)/10 }}g
                        </p>
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </div>
              </div>
              <v-row class="px-6">
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:orange;">탄</strong>
                    {{ Math.round(breakfast_Carb*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:green;">단</strong>
                    {{ Math.round(breakfast_Pro*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:blue;">지</strong>
                    {{ Math.round(breakfast_Fat*10)/10 }}g
                  </span>
                </div>
              </v-row>
            </v-container>

            <v-spacer />
            <!-- 점심 -->
            <v-container class="main-cards daily-diet col-md-6 col-12" style="border-right:10px solid white; border-left:10px solid white;">
              <v-row class="px-5">
                <div class="mr-3">
                  <h4 class="pt-2" style="font-weight:bold;">
                    점심 :
                    <span>{{ Math.round(lunch_Cal*10)/10 }} </span>
                    kcal
                  </h4>
                </div>
                <v-spacer />
                <v-btn small icon style="margin-top:5px;" @click="writefood('점심', '/')">
                  <v-icon>
                    mdi-pencil
                  </v-icon>
                </v-btn>
              </v-row>
              <hr style="margin:0px;">
              <div v-if="diethistory_lunch.length <= 0" style="line-height:300px;">
                <p style="text-align:center;">식단을 입력해주세요</p>
              </div>
              <div v-else class="scroll-box" style="height:315px; overflow:auto;">
                <div v-for="lunch in diethistory_lunch" :key="lunch.history_no" style="maring-bottom:50px;">
                  <v-list-item two-line style="padding:0px;">
                    <v-list-item-content style="padding-bottom:0px; margin-top:5px;">
                      <v-list-item-title>
                        <v-row style="padding:0px 20px;">
                          <span style="padding:10px 0px 0px;">
                            {{ lunch.food.food_name }} ({{ lunch.eat_amount }}g) 
                          </span>
                          <v-spacer />
                          <v-btn small icon style="margin:5px 0px;" @click="dietDelete(lunch.history_no)">
                            <v-icon>
                              mdi-close
                            </v-icon>
                          </v-btn>
                        </v-row>
                      </v-list-item-title>
                      <v-list-item-subtitle>
                        <p style="margin:0px 0px 5px 8px;">
                          칼로리 : {{ Math.round(lunch.correct_cal*10)/10 }}kcal
                        </p>
                        <p style="margin:0px 10px 0px 8px;">
                          탄수화물 : {{ Math.round(lunch.correct_carb*10)/10 }}g | 
                          단백질 : {{ Math.round(lunch.correct_pro*10)/10 }}g | 
                          지방 : {{ Math.round(lunch.correct_fat*10)/10 }}g
                        </p>
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </div>
              </div>
              <v-row class="px-6">
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:orange;">탄</strong>
                    {{ Math.round(lunch_Carb*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:green;">단</strong>
                    {{ Math.round(lunch_Pro*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:blue;">지</strong>
                    {{ Math.round(lunch_Fat*10)/10 }}g
                  </span>
                </div>
              </v-row>
            </v-container>
          </v-row>
          <br>

          <v-row class="px-5">
            <!-- 저녁 -->
            <v-container class="main-cards daily-diet col-md-6 col-12 mb-5" style="border-left:10px solid white; border-right:10px solid white;">
              <v-row class="px-5">
                <div class="mr-3">
                  <h4 class="pt-2" style="font-weight:bold;">
                    저녁 :
                    <span>{{ Math.round(dinner_Cal*10)/10 }} </span>
                    kcal
                  </h4>
                </div>
                <v-spacer />
                <v-btn small icon style="margin-top:5px;" @click="writefood('저녁', '/')">
                  <v-icon>
                    mdi-pencil
                  </v-icon>
                </v-btn>
              </v-row>
              <hr style="margin:0px;">
              <div v-if="diethistory_dinner.length <= 0" style="line-height:300px;">
                <p style="text-align:center;">식단을 입력해주세요</p>
              </div>
              <div v-else class="scroll-box" style="height:315px; overflow:auto;">
                <div v-for="dinner in diethistory_dinner" :key="dinner.history_no" style="maring-bottom:50px;">
                  <v-list-item two-line style="padding:0px;">
                    <v-list-item-content style="padding-bottom:0px; margin-top:5px;">
                      <v-list-item-title>
                        <v-row style="padding:0px 20px;">
                          <span style="padding:10px 0px 0px;">
                            {{ dinner.food.food_name }} ({{ dinner.eat_amount }}g) 
                          </span>
                          <v-spacer />
                          <v-btn small icon style="margin:5px 0px;" @click="dietDelete(dinner.history_no)">
                            <v-icon>
                              mdi-close
                            </v-icon>
                          </v-btn>
                        </v-row>
                      </v-list-item-title>
                      <v-list-item-subtitle>
                        <p style="margin:0px 0px 5px 8px;">
                          칼로리 : {{ Math.round(dinner.correct_cal*10)/10 }}kcal
                        </p>
                        <p style="margin:0px 10px 0px 8px;">
                          탄수화물 : {{ Math.round(dinner.correct_carb*10)/10 }}g | 
                          단백질 : {{ Math.round(dinner.correct_pro*10)/10 }}g | 
                          지방 : {{ Math.round(dinner.correct_fat*10)/10 }}g
                        </p>
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </div>
              </div>
              <v-row class="px-6">
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:orange;">탄</strong>
                    {{ Math.round(dinner_Carb*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:green;">단</strong>
                    {{ Math.round(dinner_Pro*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:blue;">지</strong>
                    {{ Math.round(dinner_Fat*10)/10 }}g
                  </span>
                </div>
              </v-row>
            </v-container>

            <v-spacer />
            <!-- 간식 -->
            <v-container class="main-cards daily-diet col-md-6 col-12 mb-5" style="border-left:10px solid white; border-right:10px solid white;">
              <v-row class="px-5">
                <div class="mr-3">
                  <h4 class="pt-2" style="font-weight:bold;">
                    간식 :
                    <span>{{ Math.round(snack_Cal*10)/10 }} </span>
                    kcal
                  </h4>
                </div>
                <v-spacer />
                <v-btn small icon style="margin-top:5px;" @click="writefood('간식', '/')">
                  <v-icon>
                    mdi-pencil
                  </v-icon>
                </v-btn>
              </v-row>
              <hr style="margin:0px;">
              <div v-if="diethistory_snack.length <= 0" style="line-height:300px;">
                <p style="text-align:center;">식단을 입력해주세요</p>
              </div>
              <div v-else class="scroll-box" style="height:315px; overflow:auto;">
                <div v-for="snack in diethistory_snack" :key="snack.history_no" style="maring-bottom:50px;">
                  <v-list-item two-line style="padding:0px;">
                    <v-list-item-content style="padding-bottom:0px; margin-top:5px;">
                      <v-list-item-title>
                        <v-row style="padding:0px 20px;">
                          <span style="padding:10px 0px 0px;">
                            {{ snack.food.food_name }} ({{ snack.eat_amount }}g) 
                          </span>
                          <v-spacer />
                          <v-btn small icon style="margin:5px 0px;" @click="dietDelete(snack.history_no)">
                            <v-icon>
                              mdi-close
                            </v-icon>
                          </v-btn>
                        </v-row>
                      </v-list-item-title>
                      <v-list-item-subtitle>
                        <p style="margin:0px 0px 5px 8px;">
                          칼로리 : {{ Math.round(snack.correct_cal*10)/10 }}kcal
                        </p>
                        <p style="margin:0px 10px 0px 8px;">
                          탄수화물 : {{ Math.round(snack.correct_carb*10)/10 }}g | 
                          단백질 : {{ Math.round(snack.correct_pro*10)/10 }}g | 
                          지방 : {{ Math.round(snack.correct_fat*10)/10 }}g
                        </p>
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </div>
              </div>
              <v-row class="px-6">
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:orange;">탄</strong>
                    {{ Math.round(snack_Carb*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:green;">단</strong>
                    {{ Math.round(snack_Pro*10)/10 }}g
                  </span>
                </div>
                <div class="pb-3 pt-7 pr-2">
                  <span>
                    <strong class="nutri-chips" style="background-color:blue;">지</strong>
                    {{ Math.round(snack_Fat*10)/10 }}g
                  </span>
                </div>
              </v-row>
            </v-container>
          </v-row>
        </v-card-text>
      </v-col>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      // 아침
      diethistory_breakfast: [],
      breakfast_nut: [],
      breakfast_Cal: 0,
      breakfast_Carb: 0,
      breakfast_Pro: 0,
      breakfast_Fat: 0,

      // 점심
      diethistory_lunch: [],
      lunch_nut: [],
      lunch_Cal: 0,
      lunch_Carb: 0,
      lunch_Pro: 0,
      lunch_Fat: 0,

      // 저녁
      diethistory_dinner: [],
      dinner_nut: [],
      dinner_Cal: 0,
      dinner_Carb: 0,
      dinner_Pro: 0,
      dinner_Fat: 0,
      
      // 간식
      diethistory_snack: [],
      snack_nut: [],
      snack_Cal: 0,
      snack_Carb: 0,
      snack_Pro: 0,
      snack_Fat: 0,

      // 오늘 날짜(식단 작성용)
      todayDate: null,
      // 식단 삭제 확인창
      deleteMessage: false,
    }
  },
  created(){
    this.getDailyDietHistory();
  },
  methods:{
    writefood(type, path){
      // breakfast, lunch, dinner, snack 으로 변경 (현재 morning)
      this.$store.dispatch('EatType', type);
      this.$store.dispatch('BeforePage', path);
      this.$router.push(`/writefood`)
    },
    dietDelete(data) {
      // 식단삭제
      axios.delete(`/apis/diethistory/${data}`)
      .then(() => {
        this.$router.go()
      })
    },

    getDailyDietHistory(){
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth()+1;
      var yyyy = today.getFullYear();

      this.todayDate = String(yyyy) + '-' +  String(mm) + '-';
      if (dd < 10) {
        this.todayDate += '0'
      } 
      this.todayDate += String(dd) + 'T00:00';

      this.$store.dispatch('DietDate', this.todayDate);

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
  .daily-diet {
    width: 49%;
  }
  .nutri-chips {
    border-radius: 5px;
    font-size: 10px;
    padding: 3px;
    color: white;
  }
  .scroll-box::-webkit-scrollbar {
    width: 10px;
  }
  .scroll-box::-webkit-scrollbar-thumb {
    background-color: #2f3542;
    border-radius: 10px;
  }
  .scroll-box::-webkit-scrollbar-track {
    background-color: grey;
    border-radius: 10px;
  }
</style>