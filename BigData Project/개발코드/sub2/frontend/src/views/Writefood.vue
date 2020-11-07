<template>
  <v-container>
    <!-- 1. 새로고침,,ㅠ => 만능쿠키 -->
    <v-row>
      <v-spacer />
      <div>
        <div style="height:20px;" />
        <h2 style="vertical-align:middle; font-size:27px; margin-top:3px;">{{ dietMonth }}월 {{ dietDay }}일</h2>
      </div>
      <v-col class="col-6 col-md-2">
        <v-select
          v-model="eatType"
          style="font-weight:bold; font-size:26px; margin:0px auto;"
          :items="eatTypes"
        />
      </v-col>
      
      <v-spacer />
    </v-row>
    <v-card>
      <v-container>
        <router-view />
      </v-container>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      selectDate: null,
      dietMonth: null,
      dietDay: null,
      eatType: '아침',
      eatTypes: [
        '아침',
        '점심',
        '저녁',
        '간식',
      ],
    }
  },
  watch: {
    eatType() {        // 식사 유형을 store에 저장
      this.$store.dispatch('EatType', this.eatType);
    }
  },
  created() {
    this.eatType = this.$store.getters.eatType;

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;
    var yyyy = today.getFullYear();

    this.selectDate = String(yyyy) + '-' +  String(mm) + '-';
    if (dd < 10) {
      this.todayDate += '0';
    } 
    this.todayDate += String(dd);

    this.selectDate = this.$store.getters.dietDate;
    this.dietMonth = this.selectDate[5] + this.selectDate[6]
    if (this.selectDate[8] == '0') {
      this.dietDay = this.selectDate[9]
    } else {
      this.dietDay = this.selectDate[8] + this.selectDate[9]
    }
  },
  
}
</script>

<style>
.theme--light.v-text-field>.v-input__control>.v-input__slot:before {
  border-color: white !important;
}
.v-text-field>.v-input__control>.v-input__slot:before {
  border-style: solid !important;
}
.theme--light.v-text-field:not(.v-input--has-state)>.v-input__control>.v-input__slot:hover:before {
  border-color: white !important;
}
.theme--light.v-text-field>.v-input__control>.v-input__slot:before {
  border-color: white !important;
}
.v-select__selection--comma {
  overflow: visible;
}
</style>