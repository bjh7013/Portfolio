<template>
<div>
    <nav-header></nav-header>
   <!-- 회원가입 -->
    <!-- <v-img src="https://picsum.photos/id/1073/900/400" img-alt="Image"> -->
    <v-img :src="require('./../../views/images/login_background.jpg')" img-alt="Image">
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar dark flat>
                <v-toolbar-title>SSAZIT를 바로 시작해보세요</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-textarea
                  value="제1조 (목적) 
본 약관은 회원(본 약관에 동의한 자를 말하며 이하 '회원'이라고 합니다)이 주식회사 SSAZIT(이하 '회사'라고 합니다)가 제공하는 싸지트(SSAZIT) 서비스(이하 '서비스'라고 합니다)를 이용함에 있어 회사와 회원의 권리·의무 및 책임사항을 규정함을 목적으로 합니다.
제2조 (약관의 명시, 효력 및 개정)
(1) 회사는 본 약관의 내용을 회원이 알 수 있도록 서비스 화면(www.tistory.com)에 게시함으로써 이를 공지합니다.
(2) 회사는 콘텐츠산업 진흥법, 전자상거래 등에서의 소비자보호에 관한 법률, 약관의 규제에 관한 법률, 소비자기본법 등 관련법을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.
(3) 회사가 약관을 개정할 경우에는 기존약관과 개정약관 및 개정약관의 적용일자와 개정사유를 명시하여 현행약관과 함께 그 적용일자 일십오(15)일 전부터 적용일 이후 상당한 기간 동안 공지만을 하고, 개정 내용이 회원에게 불리한 경우에는 그 적용일자 삼십(30)일 전부터 적용일 이후 상당한 기간 동안 각각 이를 서비스 홈페이지에 공지하고 회사가 부여한 이메일 주소로 약관 개정 사실을 발송하여 고지합니다.
(4) 회사가 전항에 따라 회원에게 통지하면서 공지 또는 공지∙고지일로부터 개정약관 시행일 7일 후까지 거부의사를 표시하지 아니하면 승인한 것으로 본다는 뜻을 명확하게 고지하였음에도 불구하고 거부의 의사표시가 없는 경우에는 변경된 약관을 승인한 것으로 봅니다. 회원이 개정약관에 동의하지 않을 경우 회원은 제14조 제1항의 규정에 따라 이용계약을 해지할 수 있습니다."

                  readonly
                  >
                  </v-textarea>
                  <v-checkbox  v-model="checkone"  label="SSAZIT 서비스 이용 약관" color="success" hide-details></v-checkbox>
                                    <v-textarea
                  value="1. 수집하는 개인정보 항목
- 필수정보의 수집 : 회원가입 시 이용자의 이메일 주소, 비밀번호를 수집하고 있습니다. 이용자의 이벤트 참여 시 해당 시점에 이용자의 이름, 휴대폰 번호, 유선 전화번호, 배송지 주소 추가 수집을 위한 별도의 동의를 받도록 하고 있습니다.
서비스 이용과정에서 아래와 같은 정보들이 자동으로 생성되어 수집될 수 있습니다.
o IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록, 기기정보

2. 개인정보의 수집 • 이용목적
1) 회원으로 가입한 이용자를 식별하고 가입의사 확인, 불량회원의 부정한 이용을 방지하기 위하여 사용합니다.
2) 이용자에게 카카오의 다양한 서비스를 제공하고 서비스 이용 과정에서 이용자의 문의사항이나 불만을 처리하고 공지사항 등을 전달하기 위해 사용합니다.
3) 이용자와 약속한 서비스를 제공하고 유료 서비스 구매 및 이용이 이루어지는 경우 이에 따른 요금 정산을 위해 사용됩니다.
4) 신규 서비스가 개발되거나 이벤트 행사 시 참여기회를 알리기 위한 정보 전달 및 마케팅 및 광고 등에도 사용됩니다.
5) 맞춤형 서비스 제공 및 신규 서비스 개발 등에 참고하기 위하여 이를 익명화 하여, 이용패턴과 접속빈도 분석 및 서비스 이용에 대한 통계에서도 사용됩니다."

                  readonly
                  >
                  </v-textarea>
                  <v-checkbox  v-model="checktwo"  label="개인정보 수집 및 이용 동의" color="success" hide-details></v-checkbox>

                  
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="next" dark>다음</v-btn>
                <v-btn @click="home" dark>뒤로</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-img>
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
export default {
    name : "Menu",
    components: {
    NavHeader,
    NavFooter
    },
    created(){
    this.checkone = false;
    this.checktwo = false;
    this.checkall = false;
    this.checkmanual =false;
    },
    data(){
        return{
            checkone : false,
            checktwo : false,
            checkall : false,
            checkmanual : false,
        }
    },
    methods : {
        next(){
            if(this.checkone==true && this.checktwo==true){
                this.$store.dispatch('Menu',true);
                this.$router.push('/join')
            }else{
                alert("약관 동의를 해주세요");
            }
        },
        home() {
          this.$router.go(-1)
        }
    }
}
</script>

<style>

</style>