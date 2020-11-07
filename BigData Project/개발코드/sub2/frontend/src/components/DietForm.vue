<template>
  <div>
    <!-- 2. 평가데이터 저장 -->
    <!-- 3. 맛점수 수정 -->
    <p style="margin:5px 0px 0px;">메뉴에 대해 평가해주세요✍</p>
    <v-divider style="margin-top:5px;" />
    <h2>{{ foodInfo.food_name }}</h2>
    <v-text-field
      v-model="foodAmount"
      :label="`1인분은 ${ foodInfo.amount }g 입니다.`"
      placeholder="식사량을 입력해주세요"
      suffix="g"
      style="width:30%; min-width:200px;"
    />
    <v-rating
      v-model="foodRating"
      background-color="grey"
      color="yellow accent-4"
      dense
      hover
      size="30"
    />
    <v-text-field
      v-model="foodReview"
      placeholder="한줄평을 남겨주세요 💬"
      clearable
    />
    <v-list-item two-line style="padding:5px;">
      <v-list-item-content>
        <v-list-item-title>맛태그</v-list-item-title>
        <v-list-item-subtitle>해당하는 맛을 모두 선택해주세요.</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-chip-group
      v-model="foodTaste"
      column
      multiple
    >
      <v-chip
        filter
        outlined
      >
        매운
      </v-chip>
      <v-chip
        filter
        outlined
      >
        달콤
      </v-chip>
      <v-chip
        filter
        outlined
      >
        새콤
      </v-chip>
      <v-chip
        filter
        outlined
      >
        짭짤
      </v-chip>
      <v-chip
        filter
        outlined
      >
        느끼
      </v-chip>
      <v-chip
        filter
        outlined
      >
        고소
      </v-chip>
      <v-chip
        filter
        outlined
      >
        쓴
      </v-chip>
      <v-chip
        filter
        outlined
      >
        담백
      </v-chip>
      <v-chip
        filter
        outlined
      >
        부드러운
      </v-chip>
      <v-chip
        filter
        outlined
      >
        쫄깃
      </v-chip>
      <v-chip
        filter
        outlined
      >
        퍽퍽
      </v-chip>
      <v-chip
        filter
        outlined
      >
        바삭
      </v-chip>
    </v-chip-group>
    <v-btn style="width:100%; margin:20px 0px 10px; box-shadow:none;" @click="dietComplete">작성</v-btn>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      foodInfo : null,
      foodRating : 0,         // 음식 평가
      foodReview: null,
      foodTaste: [],
      foodAmount: null,
      eatType: null,
      eatDate: null,
      beforePath: null,

      userInfo: [],
      user_spicy: 0,
      user_sweet: 0,
      user_sour: 0,
      user_salty: 0,
      user_oily: 0,
      user_savory: 0,
      user_bitter: 0,
      user_mild: 0,
      user_soft: 0,
      user_chewy: 0,
      user_dry: 0,
      user_crispy: 0,
      user_review_cnt: 0,

      spicy : null,
      sweet : null,
      sour : null,
      salty : null,
      oily : null,
      savory : null,
      bitter : null,
      mild : null,
      soft : null,
      chewy : null,
      dry : null,
      crispy : null,
      rateCount : null,

    }
  },
  watch: {
    // foodTaste() {
    //   alert(this.foodTaste);
    // }
  },
  created() {
    this.getMyInfo();
    this.eatDate = this.$store.getters.dietDate;
    this.foodInfo = this.$store.getters.pickFood;
    this.beforePath = this.$store.getters.beforePath;
    if (this.foodInfo.amount == null) {
      this.foodAmount = 0
    }

    var type = this.$store.getters.eatType;
    if (type == '아침') {
      this.eatType = 'b'
    } else if (type == '점심') {
      this.eatType = 'l'
    } else if (type == '저녁') {
      this.eatType = 'd'
    } else if (type == '간식') {
      this.eatType = 's'
    }
  },
  methods: {
    getMyInfo(){
      axios.get(`/apis/mypage`)
      .then((response) => {
        this.userInfo = response.data;
        this.user_spicy = response.data.spicy;
        this.user_sweet = response.data.sweet;
        this.user_sour = response.data.sour;
        this.user_salty = response.data.salty;
        this.user_oily = response.data.oily;
        this.user_savory = response.data.savory;
        this.user_bitter = response.data.bitter;
        this.user_mild = response.data.mild;
        this.user_soft = response.data.soft;
        this.user_chewy = response.data.chewy;
        this.user_dry = response.data.dry;
        this.user_crispy = response.data.crispy;
        this.user_review_cnt = response.data.review_cnt;
      })
    },
    calcUserFlavor(){
      let change_rate = 0;
      if(this.foodRating == 1){
        change_rate = -1;
      }
      else if(this.foodRating == 2){
        change_rate = -0.5;
      }
      else if(this.foodRating == 4){
        change_rate = 0.5;
      }
      else if(this.foodRating == 5){
        change_rate = 1;
      }

      // spicy
      let before = this.user_spicy;
      let after = ( this.user_spicy * this.user_review_cnt + this.foodInfo.spicy ) / ( this.user_review_cnt + 1 );
      let change_val = after - before;
      let spicy = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(spicy >= 10){
        spicy = 10;
      }
      else if(spicy <= 0){
        spicy = 0;
      }

      // sweet
      before = this.user_sweet;
      after = ( this.user_sweet * this.user_review_cnt + this.foodInfo.sweet ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let sweet = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(sweet >= 10){
        sweet = 10;
      }
      else if(sweet <= 0){
        sweet = 0;
      }

      // sour
      before = this.user_sour;
      after = ( this.user_sour * this.user_review_cnt + this.foodInfo.sour ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let sour = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(sour >= 10){
        sour = 10;
      }
      else if(sour <= 0){
        sour = 0;
      }

      // salty
      before = this.user_salty;
      after = ( this.user_salty * this.user_review_cnt + this.foodInfo.salty ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let salty = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(salty >= 10){
        salty = 10;
      }
      else if(salty <= 0){
        salty = 0;
      }

      // oily
      before = this.user_oily;
      after = ( this.user_oily * this.user_review_cnt + this.foodInfo.oily ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let oily = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(oily >= 10){
        oily = 10;
      }
      else if(oily <= 0){
        oily = 0;
      }

      // savory
      before = this.user_savory;
      after = ( this.user_savory * this.user_review_cnt + this.foodInfo.savory ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let savory = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(savory >= 10){
        savory = 10;
      }
      else if(savory <= 0){
        savory = 0;
      }

      // bitter
      before = this.user_bitter;
      after = ( this.user_bitter * this.user_review_cnt + this.foodInfo.bitter ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let bitter = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(bitter >= 10){
        bitter = 10;
      }
      else if(bitter <= 0){
        bitter = 0;
      }

      // mild
      before = this.user_mild;
      after = ( this.user_mild * this.user_review_cnt + this.foodInfo.mild ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let mild = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(mild >= 10){
        mild = 10;
      }
      else if(mild <= 0){
        mild = 0;
      }

      // soft
      before = this.user_soft;
      after = ( this.user_soft * this.user_review_cnt + this.foodInfo.soft ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let soft = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(soft >= 10){
        soft = 10;
      }
      else if(soft <= 0){
        soft = 0;
      }

      // chewy
      before = this.user_chewy;
      after = ( this.user_chewy * this.user_review_cnt + this.foodInfo.chewy ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let chewy = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(chewy >= 10){
        chewy = 10;
      }
      else if(chewy <= 0){
        chewy = 0;
      }

      // dry
      before = this.user_dry;
      after = ( this.user_dry * this.user_review_cnt + this.foodInfo.dry ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let dry = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(dry >= 10){
        dry = 10;
      }
      else if(dry <= 0){
        dry = 0;
      }

      // crispy
      before = this.user_crispy;
      after = ( this.user_crispy * this.user_review_cnt + this.foodInfo.crispy ) / ( this.user_review_cnt + 1 );
      change_val = after - before;
      let crispy = Math.round( ( before + ( change_val * change_rate ) ) * 100 ) / 100;
      if(crispy >= 10){
        crispy = 10;
      }
      else if(crispy <= 0){
        crispy = 0;
      }

      // console.log(spicy);      
      // console.log(sweet);
      // console.log(sour);
      // console.log(salty);
      // console.log(oily);
      // console.log(savory);
      // console.log(bitter);
      // console.log(mild);
      // console.log(soft);
      // console.log(chewy);
      // console.log(dry);
      // console.log(crispy);

      axios.put(`/apis/mypage`, {
        user_id : this.userInfo.userId,
        user_pw : this.userInfo.userPw,
        user_name : this.userInfo.userName,   
        nick_name : this.userInfo.userNick,     
        gender : this.userInfo.gender,     
        born_year : this.userInfo.birthYear,  
        address : this.userInfo.address,    
        height : this.userInfo.heightInfo, 
        weight : this.userInfo.weightInfo, 
        weight_type : this.userInfo.weightType,
        plan_carb : this.userInfo.nutriCarb,   
        plan_protein : this.userInfo.nutriProt,   
        plan_fat : this.userInfo.nutriFat,
        plan_cal : this.userInfo.dayKcal,
        spicy : spicy, 
        sweet : sweet,
        sour : sour,
        salty : salty,
        oily : oily,
        savory : savory,
        bitter : bitter,
        mild : mild,
        soft : soft,
        chewy : chewy,
        dry : dry,
        crispy : crispy,
        review_cnt : this.user_review_cnt + 1
      })
      .then((response) => {
        if (response.status === 200){
          // 안돼,, 글로 가지마ㅠ
          // 수정 완료 후 mypage으로 리다이렉트
          // this.$router.push('/mypage');
        } else {
          alert("회원정보 수정에 실패하였습니다.");
        }
      })

    },
    computeFlavor(){
      // console.log(this.contains(0));
      let spicy = this.foodInfo.spicy;
      let sweet = this.foodInfo.sweet;
      let sour = this.foodInfo.sour;
      let salty = this.foodInfo.salty;
      let oily = this.foodInfo.oily;
      let savory = this.foodInfo.savory;
      let bitter = this.foodInfo.bitter;
      let mild = this.foodInfo.mild;
      let soft = this.foodInfo.soft;
      let chewy = this.foodInfo.chewy;
      let dry = this.foodInfo.dry;
      let crispy = this.foodInfo.crispy;
      let rate_count = this.foodInfo.rate_count;
      
      // spicy
      if(this.contains(0)){ // 투표 시
        spicy = Math.round( ( ( spicy / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        spicy = Math.round( ( spicy / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // sweet
      if(this.contains(1)){ // 투표 시
        sweet = Math.round( ( ( sweet / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        sweet = Math.round( ( sweet / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // sour
      if(this.contains(2)){ // 투표 시
        sour = Math.round( ( ( sour / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        sour = Math.round( ( sour / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // salty
      if(this.contains(3)){ // 투표 시
        salty = Math.round( ( ( salty / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        salty = Math.round( ( salty / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // oily
      if(this.contains(4)){ // 투표 시
        oily = Math.round( ( ( oily / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        oily = Math.round( ( oily / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // savory
      if(this.contains(5)){ // 투표 시
        savory = Math.round( ( ( savory / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        savory = Math.round( ( savory / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // bitter
      if(this.contains(6)){ // 투표 시
        bitter = Math.round( ( ( bitter / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        bitter = Math.round( ( bitter / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // mild
      if(this.contains(7)){ // 투표 시
        mild = Math.round( ( ( mild / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        mild = Math.round( ( mild / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // soft
      if(this.contains(8)){ // 투표 시
        soft = Math.round( ( ( soft / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        soft = Math.round( ( soft / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // chewy
      if(this.contains(9)){ // 투표 시
        chewy = Math.round( ( ( chewy / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        chewy = Math.round( ( chewy / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // dry
      if(this.contains(10)){ // 투표 시
        dry = Math.round( ( ( dry / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        dry = Math.round( ( dry / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      // crispy
      if(this.contains(11)){ // 투표 시
        crispy = Math.round( ( ( crispy / 10 * rate_count ) + 1 ) / ( rate_count + 1 ) * 1000 ) / 100;
      }else{  // 투표 X시
        crispy = Math.round( ( crispy / 10 * rate_count ) / ( rate_count + 1 ) * 1000 ) / 100;
      }

      this.spicy = spicy
      this.sweet = sweet
      this.sour = sour
      this.salty = salty
      this.oily = oily
      this.savory = savory
      this.bitter = bitter
      this.mild = mild
      this.soft = soft
      this.chewy = chewy
      this.dry = dry
      this.crispy = crispy
      this.rateCount = rate_count + 1

      // console.log(spicy);      
      // console.log(sweet);
      // console.log(sour);
      // console.log(salty);
      // console.log(oily);
      // console.log(savory);
      // console.log(bitter);
      // console.log(mild);
      // console.log(soft);
      // console.log(chewy);
      // console.log(dry);
      // console.log(crispy);

      // axios로 food update 전송필요

    },
    contains(e){
      for(let i=0; i<this.foodTaste.length; i++){
        if(this.foodTaste[i] == e){
          return true;
        }
      }
      return false;
    },
    dietComplete() {
      if (this.eatType && this.foodAmount && this.eatDate) {
        this.computeFlavor();
        this.calcUserFlavor();
        axios.post(`/apis/diethistory`, {
          food_no : this.foodInfo.food_no,
          eat_type : this.eatType,
          eat_amount : this.foodAmount,
          eat_date : this.eatDate
        })
        .then(() => {
          if (this.foodRating) {
            axios.post(`/apis/review`, {
              food_no : this.foodInfo.food_no,
              score : this.foodRating,
              content : this.foodReview
            })
            .then(() => {
            })
          }
          if (this.foodTaste.length) {
            axios.put(`apis/foodupdate/${this.foodInfo.food_no}`, {
              food_name : this.foodInfo.food_name,
              food_type : this.foodInfo.food_type,
              cal : this.foodInfo.cal,
              manufacturer : this.foodInfo.manufacturer,
              group_no : this.foodInfo.group,
              amount : this.foodInfo.amount,
              spicy : this.spicy,
              sweet : this.sweet,
              sour : this.sour,
              salty : this.salty,
              oily : this.oily,
              savory : this.savory,
              bitter : this.bitter,
              mild : this.mild,
              soft : this.soft,
              chewy : this.chewy,
              dry : this.dry,
              crispy : this.crispy,
              rate_count : this.rateCount
            })
            .then(() => {
            })
          }
          this.$router.go(-2)
        })
      } else {
        if (this.foodAmount == null) {
          alert('식사량을 입력해주세요.')
        } else {
          alert('잘못된 접근입니다.')
        }
      }
      
    }
  }
}
</script>

<style>

</style>