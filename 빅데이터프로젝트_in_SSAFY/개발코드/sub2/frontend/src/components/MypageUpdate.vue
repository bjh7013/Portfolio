<template>
  <v-container>
    <h3 class="myupdate-title">회원정보 수정</h3>
    <v-card class="myupdate-card">
      <div class="myupdate-row myupdate-px">
        <!-- 아이디(변경불가) -->
        <v-text-field
          label="아이디"
          :placeholder="userId"
          readonly
        />

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

      <div class="myupdate-row myupdate-subcontent">
        <!-- 설명 -->
        <p class="myupdate-description">
          출생년도, 키, 체중, 성별, 체중목표는 일일 권장 섭취량을 계산하는데 사용됩니다.
        </p>
        <p class="myupdate-description">
          (해당사항을 입력하지 않는 경우 성인 일일 권장 섭취량을 기준으로 합니다.)
        </p>
      </div>

      <div class="myupdate-row myupdate-my">
        <!-- 성별 -->
        <v-subheader>성별 (필수)</v-subheader>
        <span v-if="gender=='f'" class="display-3 pa-2">🙋</span>
        <span v-else class="display-3 pa-2" @click="gender = 'f'">🙍</span>
        <span v-if="gender=='m'" class="display-3 pa-2">🙋‍♂️</span>
        <span v-else class="display-3 pa-2" @click="gender = 'm'">🙍‍♂️</span>
      </div>

      <div class="myupdate-row myupdate-px">
        <!-- 출생년도 -->
        <v-text-field
          v-model.number="birthYear"
          label="출생년도 (필수)"
          placeholder="ex) YYYY"
          type="number"
          :rules="[rules.age.min, rules.age.max]"
          prepend-icon="mdi-calendar-text"
          class="myupdate-birthyear"
        />
      </div>

      <v-row class="myupdate-row">
        <!-- 키 -->
        <v-col
          cols="12"
          md="6"
          class="myupdate-px"
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
          class="myupdate-px"
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
          class="myupdate-px"
        >
          <v-select
            v-model="typeInfo" 
            :items="typeCheck"
            label="체중 목표"
          />
        </v-col>
      </v-row>

      <div class="myupdate-row myupdate-px">
        <!-- 주소 -->
        <v-text-field
          v-model="address"
          prepend-icon="mdi-map-marker"
          label="주소(동)"
        />
      </div>
      <br>

      <v-row class="myupdate-row">
        <div class="col-12 col-md-10 p-0" style="padding:10px;">
          <v-btn 
            text
            :disabled="!formIsValid()"
            class="myupdate-btn"
            style="color:green;"
            @click="Update"
          >
            수정하기
          </v-btn>
        </div>

        <!-- 회원탈퇴 -->
        <div class="col-12 col-md-2" style="padding:10px;">
          <v-dialog v-model="userDelete" persistent max-width="300">
            <template v-slot:activator="{ on, attrs }">
              <v-btn 
                text
                class="myupdate-btn"
                style="color:red;"
                v-bind="attrs"
                v-on="on"
              >
                회원탈퇴
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                정말 탈퇴하실껀가요?😥
              </v-card-title>
              <v-card-text>
                탈퇴 후 회원정보 및 식단기록은 복구 불가능합니다.
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn 
                  text 
                  color="black darken-1" 
                  @click="userDelete = false"
                >
                  취소
                </v-btn>
                <v-btn 
                  text 
                  color="green darken-1" 
                  @click="Delete"
                >
                  탈퇴
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  // 회원정보 수정 페이지입니다.
  name: 'MypageUpdate',
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
      show: false,            // 비밀번호 표기
      showChk: false,         // 비밀번호 표기 (확인용)
      userDelete: false,      // 삭제 경고창
      rules:{
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
  // computed: {
    
  // },
  created() {
    // 유저 정보 가져오기
    axios.get(`/apis/mypage`)
    .then((response) => {
      const userInfo = response.data
      this.userNick = userInfo.nick_name,
      this.userId = userInfo.user_id,
      this.userPw = userInfo.user_pw,
      this.userPwChk = userInfo.user_pw,
      this.userName = userInfo.user_name,
      this.gender = userInfo.gender,
      this.birthYear = userInfo.born_year,
      this.heightInfo = userInfo.height,
      this.weightInfo = userInfo.weight,
      this.weightType = userInfo.weight_type,
      this.address = userInfo.address,
      this.dayKcal = userInfo.plan_cal

      if (this.weightType == 'u') {
        this.typeInfo = '체중증가';
      } else if (this.weightType == 'e') {
        this.typeInfo = '체중유지';
      } else if (this.weightType == 'd') {
        this.typeInfo = '체중감소';
      }
    })
  },
  methods: {
    formIsValid() {         // 필수값 입력 확인
      return (
        this.userPw &&
        this.userPwChk &&
        this.userNick &&
        this.userName &&
        this.gender &&
        this.birthYear
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
      return Math.round(this.dayKcal * 0.10 / 4)
    },
    nutriFat() {            // 하루 권장 지방 계산
      
      return Math.round(this.dayKcal * 0.25 / 9)
    },
    // 회원정보 수정
    Update() {
      
      // 선택값이 비어있으면 null로 변경
      if (this.heightInfo == '') {
        this.heightInfo = null;
      }
      if (this.weightInfo == '') {
        this.weightInfo = null;
      }
      if (this.address == '') {
        this.address = null;
      }
      // 일일 섭취 칼로리 계산
      if (this.gender && this.weightInfo && this.heightInfo && this.birthYear) {
        if (this.gender == 'f') {
          this.dayKcal = this.femaleKcal();
        }
        else if (this.gender == 'm') {
          this.dayKcal = this.maleKcal();
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
      if (!this.userPw || !this.userPwChk) {
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
      } else {
        axios.put(`/apis/mypage`, {
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
          plan_carb : this.nutriCarb(),   
          plan_protein : this.nutriProt(),   
          plan_fat : this.nutriFat(),
          plan_cal : this.dayKcal
        })
        .then((response) => {
          if (response.status === 200){
            // 수정 완료 후 mypage으로 리다이렉트
            this.$router.push('/mypage');
          } else {
            alert("회원정보 수정에 실패하였습니다.");
          }
        })
      }
    },
    Delete() {
      axios.delete(`/apis/mypage`)
      .then(() => {
        // 쿠키 삭제
        this.$cookies.remove('jwt');
        this.$cookies.remove('userId');

        axios.defaults.headers.common['Authorization'] = undefined;

        // store 유저 데이터 삭제
        this.$store.dispatch('Logout', null);
        this.$store.dispatch('UserId', null);

        // 탈퇴 완료 후 home으로 리다이렉트
        this.$router.push('/');
      })
    } 
  }
}
</script>

<style scoped>
.myupdate-card {
  padding: 50px 16px; 
  margin: 10px auto; 
  min-width: 360px;
  width: 60%;
}
.myupdate-title {
  text-align: center; 
  margin: 20px;
}
.myupdate-row {
  min-width: 320px; 
  max-width: 500px; 
  margin: 0px auto;
}
.myupdate-duplication {
  margin: 12px 0px; 
  width: 150px;
}
.myupdate-subcontent {
  margin: 50px auto 20px; 
  padding: 0px 16px;
}
.myupdate-description {
  font-size: 12px; 
  color: grey; 
  margin: 10px 0px;
}
.myupdate-birthyear {
  padding: 30px 0px 0px;
}
.myupdate-px {
  padding: 0px 10px;
}
.myupdate-my {
  margin: 0px auto 20px;
}
.myupdate-btn {
  width: 100%; 
}
</style>