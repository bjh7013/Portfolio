<template>
  <v-container>
    <v-row class="main-calender">
      <v-col class="pa-0">
        <v-sheet height="80">
          <v-calendar
            :value="today"
            color="primary"
            type="week"
          />
        </v-sheet>
      </v-col>
    </v-row>

    <v-card>
      <v-col cols="12">
        <v-card-text>
          <v-row class="px-5">
            <div>
              <h2 class="pt-1">오늘 섭취량</h2>
            </div>
            <v-spacer />
            <v-btn small class="main-btn">
              <v-icon>
                mdi-calendar
              </v-icon>
              <router-link to="/nutripredetail">더보기</router-link>
            </v-btn>
          </v-row>
          <br>
          <v-container class="main-cards daily-nutri">
            <strong>총 섭취량 <span style="color: red;">{{ dailyCal }}</span> / {{ dailyCalGoal }}kcal</strong>
            <v-progress-linear
              v-model="caloriePer"
              height="25"
              class="progress-linear"
            >
              <strong>{{ Math.ceil(caloriePer) }}%</strong>
            </v-progress-linear>
            <br>
            <v-row>
              <v-col>
                <strong>탄수화물 <span style="color: red;">{{ dailyCarb }}</span> / {{ dailyCarbGoal }}g</strong>
                <v-progress-linear
                  v-model="carboPer"
                  height="25"
                  color="orange"
                  class="progress-linear"
                >
                  <strong>{{ Math.ceil(carboPer) }}%</strong>
                </v-progress-linear>
              </v-col>
              <v-col>
                <strong>단백질 <span style="color: red;">{{ dailyPro }}</span> / {{ dailyProGoal }}g</strong>
                <v-progress-linear
                  v-model="proteinPer"
                  height="25"
                  color="green"
                  class="progress-linear"
                >
                  <strong>{{ Math.ceil(proteinPer) }}%</strong>
                </v-progress-linear>
              </v-col>
              <v-col>
                <strong>지방 <span style="color: red;">{{ dailyFat }}</span> / {{ dailyFatGoal }}g</strong>
                <v-progress-linear
                  v-model="fatPer"
                  height="25"
                  color="blue"
                  class="progress-linear"
                >
                  <strong>{{ Math.ceil(fatPer) }}%</strong>
                </v-progress-linear>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-col>
    </v-card>
    <food-history />
  </v-container>
</template>

<script>
import FoodHistory from "@/components/FoodHistory";
export default {
  
  data() {
    return {
      today: '',
      // 칼로리
      caloriePer: 25.8,
      dailyCal: 576,
      dailyCalGoal: 1728,
      // 탄수화물
      carboPer: 33.6,
      dailyCarb: 71,
      dailyCarbGoal: 211,
      // 단백질
      proteinPer: 34.5,
      dailyPro: 29,
      dailyProGoal: 84,
      // 지방
      fatPer: 12.5,
      dailyFat: 7,
      dailyFatGoal: 56,

      labels: ['SU', 'MO', 'TU', 'WED', 'TH', 'FR', 'SA'],
        time: 0,
        forecast: [
          { day: 'Tuesday', icon: 'mdi-white-balance-sunny', temp: '24\xB0/12\xB0' },
          { day: 'Wednesday', icon: 'mdi-white-balance-sunny', temp: '22\xB0/14\xB0' },
          { day: 'Thursday', icon: 'mdi-cloud', temp: '25\xB0/15\xB0' },
        ],
    }
  },
  components : {
    FoodHistory
  }
}
</script>

<style>

</style>