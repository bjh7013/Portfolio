<template>
  <div class="privacy container col order">
    <div class="row py-3" v-if="userInfo!=null && userInfo.userNo>-1">
      <!-- data가 들어갈 부분 -->
      <div class="col" id="main">
        <!-- 처음 화면 -->
        <div v-if="!pwcheck">
          <v-col id="check">
            <v-container fluid>
              <v-row>
                <v-text-field
                  :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show3 ? 'text' : 'password'"
                  label="비밀번호를 입력해주세요"
                  hint="At least 1 characters"
                  class="input-group--focused"
                  @click:append="show3 = !show3"
                  v-model="userpw"
                  @keyup.enter="checkpw"
                ></v-text-field>
                <button @click="checkpw">확인</button>
              </v-row>
            </v-container>
          </v-col>
        </div>
          
        <div v-if="pwcheck">
          <v-col id="change">
            <v-text-field label="이름" type="text" v-model="userName"></v-text-field>
            <v-text-field label="닉네임" type="text" v-model="nickName"></v-text-field>
            <v-text-field label="비밀번호" type="password" v-model="userPw"></v-text-field>
            <v-text-field label="비밀번호확인" type="password" v-model="userPwcheck"></v-text-field>
            <v-text-field label="이메일" type="text" v-model="eMail"></v-text-field>
            <v-text-field label="연락처" type="text" v-model="phoneNum"></v-text-field>
          

            <v-col class="text-right"> 
              <div class="my-2">
                <li><v-btn color="primary" @click="modify">수정</v-btn></li>
                  <v-dialog v-model="dialog" persistent max-width="290">
                    <template v-slot:activator="{ on, attrs }">
                    <v-btn color="error" dark v-bind="attrs" v-on="on">
                      탈퇴하기
                    </v-btn>
                    </template>
                    <v-card>
                     <v-card-title class="headline">탈퇴하시겠습니까?</v-card-title>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="green darken-1" text @click="user_delete">예(YES)</v-btn>
                        <v-btn color="green darken-1" text @click="dialog = false">아니오(NO)</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
              </div>
            </v-col>



          </v-col>
        </div>
      </div>
    </div>
    <div class='pageBlogNotFound' v-else style="position:relative; left:-200px">
      <div>
        <h2>잘못된 접근입니다.</h2>
      </div>
    <router-link to="/menu">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>회원가입</h2>
          <h3>SSAZIT와 함께 하세요.</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circlBlogNotFounde_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc1.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc3.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc2.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
    <router-link to="/main">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>홈</h2>
          <h3>SSAZIT홈으로 돌아갑니다</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc4.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc5.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc6.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
    <router-link to="/login">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>로그인</h2>
          <h3>로그인 시 이용할 수 있습니다.</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc7.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc8.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc9.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name : "privacy",
  components: {
  },
  computed:{
    userInfo(){
      return this.$store.getters.userInfo;
    }
  },

  data() {
    return {
      pwcheck : false,
      userpw : null,
      userName : '',
      nickName : '',
      userPw : '',
      userPwcheck : '',
      eMail : '',
      phoneNum : '',
      dialog: false,
      show1: false,
      show2: true,
      show3: false,
      show4: false,
      v : 0,
      password: 'Password',
      rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 1 || 'Min 1 characters',
        emailMatch: () => ('The email and password you entered don\'t match'),
      },
    }
  },
  methods : {
    checkpw() {
      axios.post('/user/pwcheck',{
        userPw : this.userpw
      }).then((Response)=>{
        // console.log(Response);
        this.pwcheck = true;
        this.$store.dispatch('Re_userInfo',Response.data);
        this.settingval(Response.data);
      }).catch((error)=>{
        // console.log(error);
        alert("비밀번호가 틀렸습니다.");
      })
    },
    settingval(data){
      this.userName = data.userName;
      this.nickName = data.nickName;
      this.userPw = data.userPw;
      this.eMail = data.eMail;
      this.phoneNum = data.phoneNum;
    },
    modify() {
      if(this.userPw == this.userPwcheck){
        axios.put('/user/modify',{
          userName : this.userName,
          nickName : this.nickName,
          userPw : this.userPw,
          eMail : this.eMail,
          phoneNum : this.phoneNum
        }).then(({data})=>{
          this.$store.dispatch('Re_userInfo',data)
          alert("정보 수정이 완료되었습니다");
          this.$router.push('/main');
        }).catch((error)=>{
          alert(error);
        })
      }else{
        alert("비밀번호를 확인 해주세요");
      }
    },
    user_delete() {
      axios.delete('/user/withdrawal',{
            
      }).then((Response)=>{
        // console.log(Response);
        alert("계정 삭제가 완료되었습니다.");
        this.dialog = true;
        this.$router.push('/logout');
    }).catch((error)=>{
        // console.log(error);
        alert("에러뜸");
    })
  }
  }
}
</script>

<style scoped>
#check {
  max-width: 60%;
  top: 100px;
  max-height: 600px;
  left: 180px;
}
#change {
  max-width: 60%;
  max-height: 600px;
  left: 180px;
}
</style>