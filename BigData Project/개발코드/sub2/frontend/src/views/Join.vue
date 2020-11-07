<template>
  <v-container>
    <h3 class="join-title">회원가입</h3>
    <v-card class="join-card">
      <!-- 아이디 -->
      <v-row class="join-row">
        <div class="col-12 col-md-8">
          <v-text-field
            v-model="userId"
            label="아이디 (필수)"
            :rules="[rules.id.required]"
            @keyup.enter="idCheck"
          />
          <div>
            <p v-if="userIdChk==false" style="color:red;">중복된 아이디입니다.</p>
            <p v-else-if="userIdChk==true" style="color:blue;">사용가능한 아이디입니다.</p>
          </div>
        </div>
        <div class="col-12 col-md-4">
          <v-btn 
            :disabled="!idIsValid"
            class="join-duplication"
            @click="idCheck"
          >
            중복확인
          </v-btn>
        </div>
      </v-row>

      <div class="join-row join-px">
        <!-- 비밀번호 -->
        <v-text-field
          v-model="userPw"
          :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.password.required, rules.password.min]"
          :type="show ? 'text' : 'password'"
          label="비밀번호 (필수)"
          counter
          @click:append="show = !show"
        />
        <!-- 비밀번호 확인 -->
        <v-text-field
          v-model="userPwChk"
          :append-icon="showChk ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.password.required, rules.password.check]"
          :type="showChk ? 'text' : 'password'"
          label="비밀번호 확인 (필수)"
          counter
          @click:append="showChk = !showChk"
        />

        <!-- 이름 -->
        <v-text-field
          v-model="userName"
          :rules="[rules.name.required, rules.name.max]"
          label="이름 (필수)"
        />

        <!-- 닉네임 -->
        <v-text-field
          v-model="userNick"
          counter="20"
          :rules="[rules.nick.required, rules.nick.max]"
          label="닉네임 (필수)"
        />
      </div>

      <div class="join-row join-subcontent">
        <!-- 설명 -->
        <p class="join-description">
          출생년도, 키, 체중, 성별, 체중목표는 일일 권장 섭취량을 계산하는데 사용됩니다.
        </p>
        <p class="join-description">
          (해당사항을 입력하지 않는 경우 성인 일일 권장 섭취량을 기준으로 합니다.)
        </p>
      </div>

      <div class="join-row join-my">
        <!-- 성별 -->
        <v-subheader>성별 (필수)</v-subheader>
        <span v-if="gender=='f'" class="display-3 pa-2">🙋</span>
        <span v-else class="display-3 pa-2" @click="gender = 'f'">🙍</span>
        <span v-if="gender=='m'" class="display-3 pa-2">🙋‍♂️</span>
        <span v-else class="display-3 pa-2" @click="gender = 'm'">🙍‍♂️</span>
      </div>

      <div class="join-row join-px">
        <!-- 출생년도 -->
        <v-text-field
          v-model.number="birthYear"
          label="출생년도 (필수)"
          placeholder="ex) YYYY"
          type="number"
          :rules="[rules.age.min, rules.age.max]"
          prepend-icon="mdi-calendar-text"
          class="join-birthyear"
        />
      </div>

      <v-row class="join-row">
        <!-- 키 -->
        <v-col
          cols="12"
          md="6"
          class="join-px"
        >
          <v-text-field
            v-model.number="heightInfo"
            label="키"
            type="number"
            suffix="cm"
          />
        </v-col>
        <!-- 체중 -->
        <v-col
          cols="12"
          md="6"
          class="join-px"
        >
          <v-text-field
            v-model.number="weightInfo"
            label="체중"
            type="number"
            suffix="kg"
          />
        </v-col>
        <!-- 타입 -->
        <v-col
          cols="12"
          md="12"
          class="join-px"
        >
          <v-select
            v-model="typeInfo" 
            :items="typeCheck"
            label="체중 목표"
          />
        </v-col>
      </v-row>

      <div class="join-row join-px">
        <!-- 주소 -->
        <v-text-field
          v-model="address"
          prepend-icon="mdi-map-marker"
          label="주소(동)"
        />
      </div>

      <br>

      <div class="join-row join-px join-my">
        <agreement />
      </div>
      
      
      <v-row class="join-row">
        <v-btn 
          text
          :disabled="!formIsValid"
          class="join-btn"
          @click="Join"
        >
          가입하기
        </v-btn>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";
import Agreement from "@/components/Agreement";
import axios from 'axios';

export default {
  // 회원가입 페이지입니다.
  name: 'Join',
  components: {
    Agreement,
  },
  data() {
    return {
      userId: "",             // 입력된 Id
      userPw: "",             // 입력된 pw
      userPwChk: "",          // 입력된 pw (확인용)
      userName: "",           // 입력된 이름
      userNick: "",           // 입력된 닉네임
      birthYear: null,        // 입력된 태어난 해
      gender: null,           // 입력된 성별
      heightInfo: null,       // 입력된 키
      weightInfo: null,       // 입력된 체중
      typeInfo: '체중유지',   // 입력된 체중 조절 목표
      weightType: 'e',        // DB용 체중 조절 목표
      address: null,          // 입력된 주소
      dayKcal: 1860,          // 하루 권장 칼로리
      typeCheck: [            // 목표 체중 선택지
        '체중감소', 
        '체중유지', 
        '체중증가'
      ],
      userIdChk: null,        // 아이디 중복 확인 여부
      show: false,            // 비밀번호 표기
      showChk: false,         // 비밀번호 표기 (확인용)
      rules:{
        id: {                 // 아이디 입력 조건
          required: v => !!v || '아이디를 입력해주세요.'
        },
        password: {           // 비밀번호 입력 조건
          required: value => !!value || '비밀번호를 입력해주세요.',
          min: v => v.length >= 8 || '최소 8이상 입력해주세요.',
          check: v => v == this.userPw || '비밀번호가 일치하지 않습니다.'
        },
        nick: {               // 닉네임 입력 조건
          required: v => !!v || '닉네임을 입력해주세요.',
          max: v => v.length <= 20 || '최대 20글자로 입력해주세요.',
        },
        name: {               // 이름 입력 조건
          required: v => !!v || '이름을 입력해주세요.',
          max: v => v.length <= 20 || '최대 20글자로 입력해주세요.',
        },
        age: {                // 출생년도 입력 조건
          min: v => 1900 < v || '출생년도를 입력해주세요.',
          max: v => v < new Date().getFullYear()+1 || '출생년도를 입력해주세요.',
        },
      }
    }
  },
  computed: {
    userAgree() {           // 약관 동의 여부
      return this.$store.getters.userAgree
    },
    idIsValid() {           // 아이디 입력 확인
      return this.userId
    },
    formIsValid() {         // 필수값 입력 확인
      return (
        this.userIdChk &&
        this.userPw &&
        this.userPwChk &&
        this.userNick &&
        this.userName &&
        this.gender &&
        this.birthYear &&
        this.userAgree 
      )
    },
    maleKcal() {            // 남성 하루 권장 칼로리 계산
      return 88.362 + (13.397 * this.weightInfo) + (4.799 * this.heightInfo) - (5.677 * (new Date().getFullYear() - this.birthYear))
    },
    femaleKcal() {          // 여성 하루 권장 칼로리 계산
      return 447.593 + (9.247 * this.weightInfo) + (3.098 * this.heightInfo) - (4.330 * (new Date().getFullYear() - this.birthYear))
    },
    nutriCarb() {           // 하루 권장 탄수화물 계산
      return Math.round(this.dayKcal * 0.65 / 4)
    },
    nutriProt() {           // 하루 권장 단백질 계산
      return Math.round(this.dayKcal * 0.1 / 4)
    },
    nutriFat() {            // 하루 권장 지방 계산
      return Math.round(this.dayKcal * 0.25 / 9)
    }
  },
  created() {
    // 로그인한 상태이면 home으로 리다이렉트
    if (this.$cookies.isKey('jwt')) {
      return this.$router.push('/')
    }
  },
  methods: {
    ...mapActions(["Login"]),

    // 아이디 중복 확인
    idCheck() {
      axios.post(`/apis/join/idcheck`, {
        user_id : this.userId
      })
      .then((response) => {
        if (response.data.result == 'duplicated') {
          this.userIdChk = false;
        } else if (response.data.result == 'useable') {
          this.userIdChk = true;
        }
      })
    },
    // 회원가입
    Join() {
      // 일일 섭취 칼로리 계산
      if (this.gender && this.weightInfo && this.heightInfo && this.birthYear) {
        if (this.gender == 'f') {
          this.dayKcal = this.femaleKcal;
        }
        else if (this.gender == 'm') {
          this.dayKcal = this.maleKcal;
        }
      } else {
        this.dayKcal = 1860;
      }
      // 체중 관리 타입별 칼로리 가중치
      if (this.typeInfo == '체중감소') {
        this.dayKcal = Math.round(this.dayKcal * 1.2);
        this.weightType = 'd';
      } else if (this.typeInfo == '체중유지') {
        this.dayKcal = Math.round(this.dayKcal * 1.55);
        this.weightType = 'e';
      } else if (this.typeInfo == '체중증가') {
        this.dayKcal = Math.round(this.dayKcal * 1.725);
        this.weightType = 'u';
      }
      // 유효성 체크
      if (!this.userIdChk) {
        alert("아이디 중복 확인을 해주세요.");
        return
      } else if (!this.userPw || !this.userPwChk) {
        alert("비밀번호를 입력해주세요.");
        return
      } else if (this.userPw != this.userPwChk) {
        alert("비밀번호가 일치하지 않습니다.");
        return
      } else if (this.userName == "" || this.userName.length > 20) {
        alert("이름을 다시 입력해주세요.");
        return
      } else if (this.userNick == "" || this.userNick.length > 20) {
        alert("닉네임을 다시 입력해주세요.");
        return
      } else if (this.birthYear != null && (1900 > this.birthYear || this.birthYear > new Date().getFullYear()+1)) {
        alert("출생년도를 다시 입력해주세요.");
        return
      } else if (this.gender == null) {
        alert("성별을 선택해주세요.");
        return
      } else if (this.heightInfo != null && (100 > this.heightInfo || this.heightInfo > 250)) {
        alert("키를 다시 입력해주세요.");
        return
      } else if (!this.userAgree) {
        alert("약관에 동의해주세요.");
        return
      } else {
        axios.post(`/apis/join`, {
          user_id : this.userId,
          user_pw : this.userPw,
          user_name : this.userName,   
          nick_name : this.userNick,     
          gender : this.gender,     
          born_year : this.birthYear,  
          address : this.address,    
          height : this.heightInfo, 
          weight : this.weightInfo, 
          weight_type : this.weightType,
          plan_carb : this.nutriCarb,   
          plan_protein : this.nutriProt,   
          plan_fat : this.nutriFat,
          plan_cal : this.dayKcal
        })
        .then((response) => {
          if(response.status === 200){
            // 회원가입 완료시 바로 로그인
            axios.post(`/apis/login`, {
              user_id : this.userId,
              user_pw : this.userPw
            })
            .then((response) => {
              if(response.status === 200 && response.data.token){
                // 쿠키 저장
                this.$cookies.set('jwt', response.data.token);      // 토큰
                this.$cookies.set('userId', response.data.userId);  // 아이디

                axios.defaults.headers.common['Authorization'] = response.data.token;

                // store 저장
                this.Login(response.data.token);                        // 토큰
                this.$store.dispatch('UserId', response.data.userId);   // 아이디

                // 로그인 완료 후 회원가입완료 페이지로 이동
                this.$router.push('/welcome');
              }
            })
          }else{
            alert("회원가입에 실패하였습니다.");
          }
        })
      }
    } 
  }
}
</script>

<style scoped>
.join-card {
  padding: 20px 16px; 
  margin: 10px auto; 
  min-width: 350px;
  width: 60%;
}
.join-title {
  text-align: center; 
  margin: 20px;
}
.join-row {
  min-width: 320px; 
  max-width: 500px; 
  margin: 0px auto;
}
.join-duplication {
  margin: 12px 0px; 
  width: 150px;
}
.join-subcontent {
  margin: 50px auto 20px; 
  padding: 0px 16px;
}
.join-description {
  font-size: 12px; 
  color: grey; 
  margin: 10px 0px;
}
.join-birthyear {
  padding: 30px 0px 0px;
}
.join-px {
  padding: 0px 10px;
}
.join-my {
  margin: 0px auto 20px;
}
.join-btn {
  width: 100%; 
  color: green; 
  margin: 10px 0px;
}
</style>