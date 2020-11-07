<template>
  <div>
    <v-container id="calendar" ref="calendar" />
    <!-- 6. 미래 식단 작성 불가 -->
    <v-alert
      :value="detailSheet"
      color="white"
      border="top"
      transition="scale-transition"
      class="calender-detail"
    >
      <v-row>
        <h4 class="calender-detail-date">
          {{ foodMonth }}월 {{ foodDay }}일 · {{ eatType }}
        </h4>
        <v-spacer />
        <v-btn
          class=""
          text
          color="grey"
          @click="detailSheet = !detailSheet"
        >
          <v-icon>mdi-close-circle</v-icon>
        </v-btn>
      </v-row>
      <hr>
      <div class="py-3">
        <v-list-item two-line>
          <v-list-item-content style="padding-top:0px;">
            <v-list-item-title v-if="foodnutrient" style="font-weight:bold; margin-bottom:10px;">
              {{ foodName }} ({{ foodnutrient.eat_amount }}g)
            </v-list-item-title>
            <v-list-item-subtitle v-if="foodnutrient">
              {{ Math.round(foodKcal / foodnutrient.food.amount * foodnutrient.eat_amount * 100) / 100 }} Kcal
              <v-row style="padding:5px 10px 20px;">
                <span v-for="n in foodnutrient.nutrient" :key="n.nutrient" style="margin:0px 5px;">
                  <span v-if="n.nutrient == 'carb'">
                    탄수화물 : {{ Math.round(n.content / foodnutrient.food.amount * foodnutrient.eat_amount * 100) / 100 }} g
                  </span>
                  <span v-else-if="n.nutrient == 'pro'">
                    단백질 : {{ Math.round(n.content / foodnutrient.food.amount * foodnutrient.eat_amount * 100) / 100 }} g
                  </span>
                  <span v-else-if="n.nutrient == 'fat'">
                    지방 : {{ Math.round(n.content / foodnutrient.food.amount * foodnutrient.eat_amount * 100) / 100 }} g
                  </span>
                  <span v-else-if="n.nutrient == 'na'">
                    나트륨 : {{ Math.round(n.content / foodnutrient.food.amount * foodnutrient.eat_amount * 100) / 100 }} mg
                  </span>
                  <span v-else-if="n.nutrient == 'chol'">
                    콜레스테롤 : {{ Math.round(n.content / foodnutrient.food.amount * foodnutrient.eat_amount * 100) / 100 }} mg
                  </span>
                  <span v-else-if="n.nutrient == 'sug'">
                    당분 : {{ Math.round(n.content / foodnutrient.food.amount * foodnutrient.eat_amount * 100) / 100 }} g
                  </span>
                </span>
              </v-row>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-row>
          <v-btn 
            color="error" 
            class="calender-btn"
            @click="dietDelete"
          >
            삭제
          </v-btn>
        </v-row>  
      </div>
    </v-alert>

    <v-alert
      :value="dietSheet"
      color="white"
      border="top"
      transition="scale-transition"
      class="calender-detail"
    >
      <v-row>
        <h4 class="calender-detail-date">
          {{ selectMonth }}월 {{ selectDay }}일
        </h4>
        <v-spacer />
        <v-btn
          class=""
          text
          color="grey"
          @click="dietSheet = !dietSheet"
        >
          <v-icon>mdi-close-circle</v-icon>
        </v-btn>
      </v-row>
      <hr>
      <div class="py-3">
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-title style="text-align:center; margin-bottom:10px;">
              새 식단 작성하기👇
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-row>
          <v-btn 
            color="success" 
            class="calender-btn"
            @click="calenderWrite('/calender')"
          >
            식단 작성
          </v-btn>
        </v-row>  
      </div>
    </v-alert>
  </div>
</template>
<script>
import Axios from 'axios'
export default {
  name : 'Calender',
  data: () => {
    return{
      foodnutrient : '',
      foodMonth : '',         // 음식 먹은 월(조회)
      foodDay : '',           // 음식 먹은 일(조회)
      selectMonth : '',       // 선택한 월(작성)
      selectDay : '',         // 선택한 일(작성)
      foodName : '',          // 음식명
      foodKcal : '',          // 음식 칼로리
      eatType : '',           // 식사 타입(아침, 점심, 저녁, 간식)
      detailSheet : false,    // 상세보기창
      dietSheet : false,      // 작성창
      dietNo : null,          // 식단 기록 No
      selectDate : null,      
    }
  },
  mounted() {
    // 로그인 안한 상태이면 Login으로 리다이렉트
    if (!this.$cookies.isKey('jwt')) {
      return this.$router.push('/login')
    }
    $(() => {
      Axios.get(`/apis/diethistory`)
      .then(({data}) => {
        let events = [];
        for(let d of data){
          events.push({id: d.history_no,title: d.food.food_name, start: d.eat_date.substring(0,10)})
          // console.dir(events)
        }

        $('#calendar').fullCalendar({
          dayMaxEvents: true, // allow "more" link when too many events
          events: events,
          eventClick: (event) => {
            Axios.get(`/apis/diethistory/${event.id}`)
            .then(( {data} ) => {
              // console.dir(data)
              // alert(data)
              this.foodnutrient = data;

              const foodDate = this.foodnutrient.eat_date.substring(0,10);
              this.foodMonth = foodDate[5] + foodDate[6];
              this.foodDay = foodDate[8] + foodDate[9];

              this.dietNo = data.history_no
              this.foodName = this.foodnutrient.food.food_name
              this.foodKcal = this.foodnutrient.food.cal

              if (this.foodnutrient.eat_type == 'b') {
                this.eatType = '아침'
              } else if (this.foodnutrient.eat_type == 'l') {
                this.eatType = '점심'
              } else if (this.foodnutrient.eat_type == 'd') {
                this.eatType = '저녁'
              } else if (this.foodnutrient.eat_type == 's') {
                this.eatType = '간식'
              }

              this.detailSheet = true;
            })
            .catch(() => {
              alert('error')
            })    
          }
        })

        // let vm = this;

        $(".fc-day-top").click(() => {
          var fetchDate = $(event.currentTarget).data("date");
          fetchDate += 'T00:00';
          this.$store.dispatch('DietDate', fetchDate);
          this.selectMonth = fetchDate[5] + fetchDate[6];
          this.selectDay = fetchDate[8] + fetchDate[9];
          this.eatType = '아침'
          this.dietSheet = true;
          // vm.aa()
        })

      })
      .catch(() => {
        alert('error')
      })
      
    });
    
  },
  methods: {
    calenderWrite(data) {
      this.$store.dispatch('BeforePage', data);
      this.$router.push(`/writefood`)
    },
    dietDelete() {
      Axios.delete(`/apis/diethistory/${this.dietNo}`)
      .then(() => {
        this.$router.go()
      })
    }
  },
  
}
</script>

<style>
.fc-toolbar button:focus {
  z-index: 0;
}
.fc-scroller {
  overflow: hidden auto !important;
}
.fc-content{
  color: white;
}
.fc-event-container{
  cursor: pointer;
}
.fc-day-top{
  cursor: pointer;
}
#calendar {
  max-width: 900px;
}
.calender-detail {
  margin: 0px auto; 
  max-width: 900px; 
  width: 97%;
}
.calender-detail-date {
  margin: 10px 20px 0px;
}
.calender-rating {
  padding: 0px 20px;
}
.calender-point {
  margin:2px 5px;
}
.calender-btn {
  margin: 0px auto;
}
</style>