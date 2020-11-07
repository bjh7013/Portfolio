<template>
  <div>
    <v-row class="mypage-profile">
      <v-spacer />
      <v-avatar size="120" class="mypage-profile-avatar">
        <img
          v-if="userGender=='m'"
          src="https://raw.githubusercontent.com/kei9641/pjt-img-file/master/profile_m.png"
          alt="woman"
        >
        <img
          v-else
          src="https://raw.githubusercontent.com/kei9641/pjt-img-file/master/profile_f.png"
          alt="woman"
        >
      </v-avatar>
      <div class="mypage-profile-base">
        <p class="mypage-profile-base-content">
          {{ userNick }}
        </p>
        <p class="mypage-profile-base-content">
          ({{ userId }})
        </p>
        <div class="mypage-profile-base-btn">
          <v-btn>
            <router-link 
              to="/mypage/pw" 
              style="color:black;"
            >
              정보수정
            </router-link>
          </v-btn>
        </div>
      </div>
      <v-spacer />
    </v-row>

    <v-container>
      <v-card class="userinfo-card">
        <v-subheader class="userinfo-subheader">
          내정보
        </v-subheader>
        <v-row>
          <v-col class="col-12 col-md-4">
            <v-icon v-if="userName==null">mdi-checkbox-blank-outline</v-icon>
            <v-icon v-else>mdi-checkbox-marked-outline</v-icon>
            <span class="userinfo-title">  이름</span>
            <span class="userinfo-content">
              {{ userName }}
            </span>
          </v-col>
          <v-col class="col-12 col-md-4">
            <v-icon v-if="userGender==null">mdi-checkbox-blank-outline</v-icon>
            <v-icon v-else>mdi-checkbox-marked-outline</v-icon>
            <span class="userinfo-title">  성별</span>
            <span 
              v-if="userGender=='m'" 
              class="userinfo-content"
            >
              남성 ♂
            </span>
            <span 
              v-else-if="userGender=='f'"
              class="userinfo-content"
            >
              여성 ♀
            </span>
          </v-col>
          <v-col class="col-12 col-md-4">
            <v-icon v-if="userAge==null">mdi-checkbox-blank-outline</v-icon>
            <v-icon v-else>mdi-checkbox-marked-outline</v-icon>
            <span class="userinfo-title">  나이</span>
            <span class="userinfo-content">
              {{ userAge }}
            </span>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="col-12 col-md-4">
            <v-icon v-if="userHeight==null">mdi-checkbox-blank-outline</v-icon>
            <v-icon v-else>mdi-checkbox-marked-outline</v-icon>
            <span class="userinfo-title">  키</span>
            <span 
              class="userinfo-content" 
              style="margin-left:37px;"
            >
              {{ userHeight }}  cm
            </span>
          </v-col>
          <v-col class="col-12 col-md-4">
            <v-icon v-if="userWeight==null">mdi-checkbox-blank-outline</v-icon>
            <v-icon v-else>mdi-checkbox-marked-outline</v-icon>
            <span class="userinfo-title">  체중</span>
            <span class="userinfo-content">
              {{ userWeight }} kg
            </span>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="col-12">
            <v-icon>mdi-checkbox-marked-outline</v-icon>
            <span class="userinfo-title">  주소</span>
            <span class="userinfo-content">
              {{ userAddress }}
            </span>
          </v-col>
        </v-row>
      </v-card>
      
      <v-card class="userinfo-card">
        <v-subheader class="userinfo-subheader">
          일일 권장 섭취량
        </v-subheader>
        <v-row>
          <v-col class="col-12">
            <span class="userinfo-title">총 칼로리</span>
            <span class="userinfo-content">
              {{ userCal }} kcal
            </span>
          </v-col>
          <v-col class="col-12 col-md-4">
            <span class="userinfo-title">탄수화물</span>
            <span class="userinfo-content">
              {{ userCarb }}  g
            </span>
          </v-col>
          <v-col class="col-12 col-md-4">
            <span class="userinfo-title">단백질</span>
            <span class="userinfo-content">
              {{ userProt }}  g
            </span>
          </v-col>
          <v-col class="col-12 col-md-4">
            <span class="userinfo-title">지방</span>
            <span class="userinfo-content">
              {{ userFat }}  g
            </span>
          </v-col>
        </v-row>
      </v-card>

      <v-card class="userinfo-card">
        <v-subheader class="userinfo-subheader">
          내 입맛
        </v-subheader>
        <div style="text-align:center">
          <span v-for="flavor in flavor_pref" :key="flavor" style="margin:20px;font-weight:bold">{{ flavor }}</span>
        </div>
        <v-card-actions>
          <v-btn text color="warning" class="mypage-testfood-btn">
            <router-link 
              to="/testfood" 
              style="color:#fb8c00;"
            >
              다시하기
            </router-link>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  // 회원정보 조회 페이지입니다.
  name : 'MypageInfo',
  data() {
    return {
      // 유저 정보
      userNick : null,
      userId : null,
      userName : null,
      userGender: null,
      userAge: null,
      userHeight: null,
      userWeight: null,
      userAddress: null,
      userCal: null,
      userCarb: null,
      userProt: null,
      userFat: null,
      flavor_pref:{},
      flavor_dict:{}
    }
  },
  created(){
    // 유저 정보 가져오기

    this.flavor_dict["spicy"] = '매운맛'
    this.flavor_dict["sweet"] = '달콤한맛'
    this.flavor_dict["sour"] = '신맛'
    this.flavor_dict["salty"] = '짠맛'
    this.flavor_dict["oily"] = '기름진맛'
    this.flavor_dict["savory"] = '고소한맛'
    this.flavor_dict["bitter"] = '쓴맛'
    this.flavor_dict["mild"] = '담백한맛'
    this.flavor_dict["soft"] = '부드러움'
    this.flavor_dict["chewy"] = '쫄깃함'
    this.flavor_dict["dry"] = '퍽퍽함'
    this.flavor_dict["crispy"] = '바삭함'
    
    axios.get(`/apis/mypage`)
    .then((response) => {
      const userInfo = response.data
      this.userNick = userInfo.nick_name,
      this.userId = userInfo.user_id,
      this.userName = userInfo.user_name,
      this.userGender = userInfo.gender,
      this.userAge = new Date().getFullYear() - userInfo.born_year,
      this.userHeight = userInfo.height,
      this.userWeight = userInfo.weight,
      this.userAddress = userInfo.address,
      this.userCal = userInfo.plan_cal,
      this.userCarb = userInfo.plan_carb,
      this.userProt = userInfo.plan_protein,
      this.userFat = userInfo.plan_fat

      this.flavor_pref["spicy"] = userInfo.spicy
      this.flavor_pref["sweet"] = userInfo.sweet
      this.flavor_pref["sour"] = userInfo.sour
      this.flavor_pref["salty"] = userInfo.salty
      this.flavor_pref["oily"] = userInfo.oily
      this.flavor_pref["savory"] = userInfo.savory
      this.flavor_pref["bitter"] = userInfo.bitter
      this.flavor_pref["mild"] = userInfo.mild
      this.flavor_pref["soft"] = userInfo.soft
      this.flavor_pref["chewy"] = userInfo.chewy
      this.flavor_pref["dry"] = userInfo.dry
      this.flavor_pref["crispy"] = userInfo.crispy
      
      let sortobj = [];
      for (let key in this.flavor_pref) {
        sortobj.push([key, this.flavor_pref[key]]);
      }
      sortobj.sort(function(a, b) {
        return b[1] - a[1];
      });

      this.flavor_pref = []
     
      this.flavor_pref[0] = this.flavor_dict[sortobj[0][0]]
      this.flavor_pref[1] = this.flavor_dict[sortobj[1][0]]
      this.flavor_pref[2] = this.flavor_dict[sortobj[2][0]]

  
    })
  },
}
</script>

<style scoped>
.mypage-profile-avatar {
  margin: 30px;
}
.mypage-profile-base {
  margin: auto 0px;
}
.mypage-profile-base-content {
  font-size: 20px; 
  margin: 0px !important;
}
.mypage-profile-base-btn {
  margin-top: 10px;
}
.userinfo-card {
  padding: 0px 16px 10px; 
  margin: 10px auto; 
  width: 90%;
}
.userinfo-subheader {
  padding: 0px;
}
.userinfo-title {
  font-weight: bold;
}
.userinfo-content {
  margin-left: 20px;
}
.mypage-testfood-btn {
  text-align: center; 
  width: 100%;
}
</style>