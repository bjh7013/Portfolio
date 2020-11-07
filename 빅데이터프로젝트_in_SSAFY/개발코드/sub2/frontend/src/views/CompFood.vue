<template>
  <div style="background-color:#f5f5f5; margin:0px auto;">
    <v-expansion-panels v-if="complist.length" style="margin:10px; width:98%;">
      <v-expansion-panel
        v-for="comp in complist"
        :key="comp.id"
      >
        <v-expansion-panel-header>{{ comp.food.food_name }}</v-expansion-panel-header>
        <v-expansion-panel-content>
          <p style="font-size:20px; font-weight:bold;">상세정보</p>
          <v-row style="margin:5px;">
            <p style="font-size:20px; margin-right:10px;">칼로리 : {{ comp.food.cal }}kcal</p>
            <p style="font-size:20px; margin-right:10px;">탄수화물 : {{ comp.Carb }}g</p>
            <p style="font-size:20px; margin-right:10px;">단백질 : {{ comp.Pro }}g</p>
            <p style="font-size:20px; margin-right:10px;">지방 : {{ comp.Fat }}g</p>
          </v-row>
          <hr>
          <p style="font-size:20px; font-weight:bold;">내상태</p>
          <v-row>
            <v-col>
              <template v-if="dailyCal + comp.food.cal >= dailyCalGoal">
                <span style="font-size:20px; color:red;">섭취 후 칼로리 : {{ Math.round((dailyCal+comp.food.cal)*10)/10 }}  ({{ Math.round(dailyCal*10)/10 }} + {{ Math.round(comp.food.cal*10)/10 }}), 권장 칼로리 : {{ dailyCalGoal }}kcal({{ Math.ceil((dailyCal+comp.food.cal)/dailyCalGoal*100) }}%)</span>
              </template>
              <template v-else>
                <span style="font-size:20px;">섭취 후 칼로리 : {{ Math.round((dailyCal+comp.food.cal)*10)/10 }}({{ Math.round(dailyCal*10)/10 }} + {{ Math.round(comp.food.cal*10)/10 }}), 권장 칼로리 : {{ dailyCalGoal }}kcal({{ Math.ceil((dailyCal+comp.food.cal)/dailyCalGoal*100) }}%)</span>
              </template>
              <b-progress class="mt-2" height="30px" :max="dailyCalGoal" show-value style="margin-bottom:15px;">
                <b-progress-bar :value="dailyCal" :label="`${((dailyCal / dailyCalGoal) * 100).toFixed(0)}%`" variant="success" />
                <b-progress-bar :value="comp.food.cal" :label="`${((comp.food.cal / dailyCalGoal) * 100).toFixed(0)}%`" variant="info" />
              </b-progress>

              <template v-if="dailyCarb + comp.Carb >= dailyCalGoal">
                <span style="font-size:20px; color:red;">섭취 후 탄수화물 : {{ Math.round((dailyCarb+comp.Carb)*10)/10 }}({{ Math.round(dailyCarb*10)/10 }} + {{ Math.round(comp.Carb*10)/10 }}), 권장 탄수화물 : {{ dailyCarbGoal }}g({{ Math.ceil((dailyCarb+comp.Carb)/dailyCarbGoal*100) }}%)</span>
              </template>
              <template v-else>
                <span style="font-size:20px;">섭취 후 탄수화물 : {{ Math.round((dailyCarb+comp.Carb)*10)/10 }}({{ Math.round(dailyCarb*10)/10 }} + {{ Math.round(comp.Carb*10)/10 }}), 권장 탄수화물 : {{ dailyCarbGoal }}g({{ Math.ceil((dailyCarb+comp.Carb)/dailyCarbGoal*100) }}%)</span>
              </template>
              <b-progress class="mt-2" height="30px" :max="dailyCarbGoal" show-value style="margin-bottom:15px;">
                <b-progress-bar :value="dailyCarb" :label="`${((dailyCarb / dailyCarbGoal) * 100).toFixed(0)}%`" variant="success" />
                <b-progress-bar :value="comp.Carb" :label="`${((comp.Carb / dailyCarbGoal) * 100).toFixed(0)}%`" variant="info" />
              </b-progress>
              
              <template v-if="dailyPro + comp.Pro >= dailyProGoal">
                <span style="font-size:20px; color:red;">섭취 후 단백질 : {{ Math.round((dailyPro+comp.Pro)*10)/10 }}({{ Math.round(dailyPro*10)/10 }} + {{ Math.round(comp.Pro*10)/10 }}), 권장 단백질 : {{ dailyProGoal }}g({{ Math.ceil((dailyPro+comp.Pro)/dailyProGoal*100) }}%)</span>
              </template>
              <template v-else>
                <span style="font-size:20px;">섭취 후 단백질 : {{ Math.round((dailyPro+comp.Pro)*10)/10 }}({{ Math.round(dailyPro*10)/10 }} + {{ Math.round(comp.Pro*10)/10 }}), 권장 단백질 : {{ dailyProGoal }}g({{ Math.ceil((dailyPro+comp.Pro)/dailyProGoal*100) }}%)</span>
              </template>
              <b-progress class="mt-2" height="30px" :max="dailyProGoal" show-value style="margin-bottom:15px;">
                <b-progress-bar :value="dailyPro" :label="`${((dailyPro / dailyProGoal) * 100).toFixed(0)}%`" variant="success" />
                <b-progress-bar :value="comp.Pro" :label="`${((comp.Pro / dailyProGoal) * 100).toFixed(0)}%`" variant="info" />
              </b-progress>

              <template v-if="dailyFat + comp.Fat >= dailyFatGoal">
                <span style="font-size:20px; color:red;">섭취 후 지방 : {{ Math.round((dailyFat+comp.Fat)*10)/10 }}({{ Math.round(dailyFat*10)/10 }} + {{ Math.round(comp.Fat*10)/10 }}), 권장 지방 : {{ dailyFatGoal }}g({{ Math.ceil((dailyFat+comp.Fat)/dailyFatGoal*100) }}%)</span>
              </template>
              <template v-else>
                <span style="font-size:20px;">섭취 후 지방 : {{ Math.round((dailyFat+comp.Fat)*10)/10 }}({{ Math.round(dailyFat*10)/10 }} + {{ Math.round(comp.Fat*10)/10 }}), 권장 지방 : {{ dailyFatGoal }}g({{ Math.ceil((dailyFat+comp.Fat)/dailyFatGoal*100) }}%)</span>
              </template>
              <b-progress class="mt-2" height="30px" :max="dailyFatGoal" show-value style="margin-bottom:15px;">
                <b-progress-bar :value="dailyFat" :label="`${((dailyFat / dailyFatGoal) * 100).toFixed(0)}%`" variant="success" />
                <b-progress-bar :value="comp.Fat" :label="`${((comp.Fat / dailyFatGoal) * 100).toFixed(0)}%`" variant="info" />
              </b-progress>

            </v-col>
          </v-row>
          <v-btn text style="width:100%; margin-bottom:10px;" @click="deleteComp(comp.id)">
            비교해보기 삭제 ❌
          </v-btn>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <v-expansion-panels v-else style="margin:300px 10px 0px; width:98%;">
      비교할 음식 정보가 없습니다.
    </v-expansion-panels>
    <v-row>
      <v-btn style="margin:10px auto; width:30%; min-width:200px;">
        <router-link to="/">
          메인으로 돌아가기
        </router-link>
      </v-btn>
    </v-row>
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
  data(){
    return {
      // 내상태
      value: 1000,
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
      
      // 비교리스트
      complist: [],

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
    }
  },
  mounted() {
    // 로그인 안한 상태이면 Login으로 리다이렉트
    if (!this.$cookies.isKey('jwt')) {
      return this.$router.push('/login')
    }
  },
  created(){
    this.getNutGoal();
    this.getDailyDietHistory();
    this.getMyNut();
    this.getComplist();
  },
  methods:{
    // 내 상태 가져오기
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
        this.caloriePer = Math.ceil(this.dailyCal / this.dailyCalGoal * 100);
      }
      if(this.dailyCarb != 0){
        this.carboPer = Math.ceil(this.dailyCarb / this.dailyCarbGoal * 100);
      }
      if(this.dailyPro != 0){
        this.proteinPer = Math.ceil(this.dailyPro / this.dailyProGoal * 100);
      }
      if(this.dailyFat != 0){
        this.fatPer = Math.ceil(this.dailyFat / this.dailyFatGoal * 100);
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

    // 비교리스트 가져오기
    getComplist(){
      axios.get(`/apis/complist`)
      .then((response) => {
        // console.log(response.data);
        // this.complist = response.data;
        for(let comp of response.data){
          // let compitem = [];
          let Carb = 0;
          let Pro = 0;
          let Fat = 0;
          axios.get(`/apis/foodnutrient/${comp.food.food_no}`)
          .then((nutrients)=>{
            for(let nutrient of nutrients.data){
              // compitem.push({nut:nutrient.nutrient, content:nutrient.content})
              if(nutrient.nutrient == "carb"){
                Carb = nutrient.content;
              }
              else if(nutrient.nutrient == "pro"){
                Pro = nutrient.content;
              }
              else if(nutrient.nutrient == "fat"){
                Fat = nutrient.content;
              }
            }
            // console.log(compitem);
            this.complist.push({id:comp.id, food:comp.food, Carb:Carb, Pro:Pro, Fat:Fat});
            // this.complist.push({id:comp.id, food:comp.food, nut:compitem})
            // console.log(response.data[compindex]);
            // this.complist.push({id:response.data[compindex].id, food:response.data[compindex].food, nut:compitem})
            // this.complist.push({nut:compitem});
          })
        }

      })
    },
    deleteComp(data){
      axios.delete(`/apis/compitem/${data}`)
      .then(() => {
        // alert(data + "가 삭제되었습니다.");
        this.complist = [];
        this.getComplist();
      })
    },
  },
}
</script>

<style>

</style>