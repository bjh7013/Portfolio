<template>
  <div>
    <div class="main-header">
      <router-link to="/#">
        <logo class="logo">
        </logo>
      </router-link>
    </div>
    <div class="contact-us">
      <div class="guid-link">
        <p class="guid-ment">도담도담이 궁금하다면? </p> <router-link to="/dodam">알아보기</router-link>
      </div>
      <input placeholder="아이디" required="" type="text" v-model="userId" @keyup.enter="login"/>
      <input name="password" placeholder="비밀번호" type="password" v-model="userPw" @keyup.enter="login"/>
      <div class="login-btns">
        <button @click="login">들어가기</button><br>
        <p class="login-ment">아직 회원이 아니신가요?</p>
        <button @click="join">회원가입</button>
      </div>
    </div>
    <img src="../assets/images/back3.png" class="lmimg"/>
    <v-dialog v-model="NOK" fullscreen>
      <v-container>
        <div id="containeral" style="margin:20% 5%">
          <div id="error-box">
            <div class="face2">
              <div class="eye"></div>
              <div class="eye right"></div>
              <div class="mouth sad"></div>
            </div>
            <div class="shadow scale"></div>
            <div class="message">
              <h1 class="alert">로그인에 실패했어요</h1>
              <p>아이디 또는 비밀번호를 확인해주세요</p>
            </div>
            <button class="button-box green" style="margin: 3% auto">
              <h1 @click="clear_no">확인</h1>
            </button>
          </div>
        </div>
      </v-container>
    </v-dialog>
    <Loading />
  </div>
</template>

<script>
import Logo from '@/components/Logo.vue'
import axios from 'axios'
import Loading from "@/components/Loading"

export default {
  name: "main",
  components: {
    Logo,
    Loading,
  },
  methods:{
    clear_no(){
      this.NOK = false
    },
    logout() {
      this.$session.destroy();
      this.$store.dispatch('LOGOUT');
      this.$router.go();
    },
    login() {
      let User = {
        'userId':this.userId,
        'userPw':this.userPw
			}

			var config={
        header:{
          'Content-Type' : 'application/json',
        }
			}
			axios.post('/user/login',User,config)
			.then((response)=>{
				if (response.status === 200 && response.headers.authorization) {

          this.$session.start();
          this.$session.set('jwt', response.headers.authorization);
          axios.defaults.headers.common['Authorization'] = response.headers.authorization;
          this.$store.dispatch('LOGIN',response.data);
          if(this.$store.getters.userInfo.userType=='teacher'){
            this.$router.push('/teachermain');
          }else{
            this.$router.push('/contentsmain');
          }
        }else{
                alert('아이디와 비밀번호를 확인해주세요');
        }
			}).catch((err)=>{
				err
				this.NOK = true
			})
    },
    checkLogin(){
      if(this.$session.get('jwt')!=null){
        axios.get('user/info')
        .then((Response)=>{
          if(Response.data.userType=='teacher'){
            this.$router.push('/teachermain');
          }
          else{
            this.$router.push('/contentsmain');
          }
        })
      }
    },
    join(){
      this.$router.push('/join')
    }
  },
  computed : {
    userInfo(){
      return this.$store.getters.userInfo
    },
    userType(){
      return this.$store.getters.userInfo.userType
    }
  },
  created() {
    this.checkLogin()
  }
}
</script>

<style scoped>
.lmimg{
  width: 100%;
  position: absolute;
  /* max-height: 100%; */
  bottom: 0;
  right: 0;
  z-index: 0;
} 
.claimg{
  width: 100vw;
  height: auto;    
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 1;
}

.main-header{
  position: relative;
  z-index: 2;
  height: 16vh;
  min-height: 90px;
  margin: 2vh 0;
}
.logo{
  width: 100% !important;
}
.guid-link{
  display: flex;
}
.guid-ment{
  font-size: 20px;
  margin: 0;
  justify-content: left;
}
.contact-us {
  z-index: 2;
  position: relative;
  font-family: 'Jua', sans-serif;
	width: 40%;
	margin: 0 auto;
  max-width: 600px;
  background: #f8f4e5;
  padding: 50px 100px;
  border: 2px solid black;
  box-shadow: 15px 15px 1px #ffa580, 15px 15px 1px 2px black;
}
.login-ment{
  margin:0;
}
input {
  display: block;
  width: 100%;
  font-size: 14pt;
  line-height: 28pt;
  font-family: 'Jua', sans-serif;
  margin-bottom: 28pt;
  border: none;
  border-bottom: 5px solid black;
  background: #f8f4e5;
  min-width: 250px;
  padding-left: 5px;
  outline: none;
  color: black;
}

@media (min-width:701px) and (max-width: 1070px){
  .contact-us {
    width: 70%;
    padding: 50px auto;
  }
}

@media (min-width:301px) and (max-width: 700px){
  
  .guid-link{
    display: block;
    position: absolute;
    top: 3vh;
    right: 3vw;
  }
  .guid-ment{
    display: none;
  }
  .contact-us {
    width: 85%;
    height: 100%;
    padding: 50px 5vw;
  }
  input{
    width: 95%;
    min-width: 200px;
  }

  .login-ment{
    display: none;
  }
  .login-btns>br{
    display: none;
  }
  .login-btns{
    display: flex;
  }
  
}
@media (max-width: 300px){
  
  .guid-link{
    width: 100%;
    display: flex;
  }
  .guid-ment{
    display: none;
  }
  .contact-us {
    width: 90%;
    height: 100%;
    padding: 0 5vw;
  }

  .login-ment{
    display: none;
  }
  input{
    width: 95%;
    min-width: 150px;
  }
}


input:focus {
  border-bottom: 5px solid #ffa580;
}

button {
  display: block;
  margin: 0 auto 1%;
  line-height: 28pt;
  padding: 0 20px;
  background: #ffa580;
  letter-spacing: 2px;
  transition: 0.2s all ease-in-out;
  outline: none;
  border: 1px solid black;
  box-shadow: 3px 3px 1px #95a4ff, 3px 3px 1px 1px black;
}
button:hover {
  background: black;
  color: white;
  border: 1px solid black;
}

::selection {
  background: #ffc8ff;
}

input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus {
  border-bottom: 5px solid #95a4ff;
  -webkit-text-fill-color: #2A293E;
  -webkit-box-shadow: 0 0 0px 1000px #f8f4e5 inset;
  transition: background-color 5000s ease-in-out 0s;
}

@import url("https://fonts.googleapis.com/css?family=Lato:400,700");
html {
  display: grid;
  min-height: 100%;
}

body {
  display: grid;
  overflow: hidden;
  font-family: "Lato", sans-serif;
  text-transform: uppercase;
  text-align: center;
}

#containeral {
  position: relative;
  margin: auto;
  overflow: hidden;
  width: 700px;
  height: 250px;
}

h1 {
  font-size: 0.9em;
  font-weight: 100;
  letter-spacing: 3px;
  padding-top: 5px;
  color: #FCFCFC;
  padding-bottom: 5px;
  text-transform: uppercase;
}

.green {
  color: #4ec07d;
}

.red {
  color: #e96075;
}

.alert {
  font-weight: 700;
  letter-spacing: 5px;
}

p {
  margin-top: -5px;
  font-size: 0.5em;
  font-weight: 100;
  color: #5e5e5e;
  letter-spacing: 1px;
}

button, .dot {
  cursor: pointer;
}

#success-box {
  position: absolute;
  width: 35%;
  height: 100%;
  left: 12%;
  background: linear-gradient(to bottom right, #B0DB7D 40%, #99DBB4 100%);
  border-radius: 20px;
  box-shadow: 5px 5px 20px #cbcdd3;
  perspective: 40px;
}

#error-box {
  position: absolute;
  width: 35%;
  height: 100%;
  right: 12%;
  background: linear-gradient(to bottom left, #EF8D9C 40%, #FFC39E 100%);
  border-radius: 20px;
  box-shadow: 5px 5px 20px #cbcdd3;
}

.dot {
  width: 8px;
  height: 8px;
  background: #FCFCFC;
  border-radius: 50%;
  position: absolute;
  top: 4%;
  right: 6%;
}
.dot:hover {
  background: #c9c9c9;
}

.two {
  right: 12%;
  opacity: .5;
}

.face {
  position: absolute;
  width: 22%;
  height: 22%;
  background: #FCFCFC;
  border-radius: 50%;
  border: 1px solid #777777;
  top: 21%;
  left: 37.5%;
  z-index: 2;
  animation: bounce 1s ease-in infinite;
}

.face2 {
  position: absolute;
  width: 22%;
  height: 22%;
  background: #FCFCFC;
  border-radius: 50%;
  border: 1px solid #777777;
  top: 21%;
  left: 37.5%;
  z-index: 2;
  animation: roll 3s ease-in-out infinite;
}

.eye {
  position: absolute;
  width: 5px;
  height: 5px;
  background: #777777;
  border-radius: 50%;
  top: 40%;
  left: 20%;
}

.right {
  left: 68%;
}

.mouth {
  position: absolute;
  top: 43%;
  left: 41%;
  width: 7px;
  height: 7px;
  border-radius: 50%;
}

.happy {
  border: 2px solid;
  border-color: transparent #777777 #777777 transparent;
  transform: rotate(45deg);
}

.sad {
  top: 49%;
  border: 2px solid;
  border-color: #777777 transparent transparent #777777;
  transform: rotate(45deg);
}

.shadow {
  position: absolute;
  width: 21%;
  height: 3%;
  opacity: .5;
  background: #777777;
  left: 40%;
  top: 43%;
  border-radius: 50%;
  z-index: 1;
}

.scale {
  animation: scale 1s ease-in infinite;
}

.move {
  animation: move 3s ease-in-out infinite;
}

.message {
  position: absolute;
  width: 100%;
  text-align: center;
  height: 40%;
  top: 47%;
}

.button-box {
  position: absolute;
  background: #FCFCFC;
  width: 50%;
  height: 15%;
  border-radius: 20px;
  top: 73%;
  left: 25%;
  outline: 0;
  border: none;
  box-shadow: 2px 2px 10px rgba(119, 119, 119, 0.5);
  transition: all .5s ease-in-out;
}
.button-box:hover {
  background: #efefef;
  transform: scale(1.05);
  transition: all .3s ease-in-out;
}

@keyframes bounce {
  50% {
    transform: translateY(-10px);
  }
}
@keyframes scale {
  50% {
    transform: scale(0.9);
  }
}
@keyframes roll {
  0% {
    transform: rotate(0deg);
    left: 25%;
  }
  50% {
    left: 60%;
    transform: rotate(168deg);
  }
  100% {
    transform: rotate(0deg);
    left: 25%;
  }
}
@keyframes move {
  0% {
    left: 25%;
  }
  50% {
    left: 60%;
  }
  100% {
    left: 25%;
  }
}
footer {
  position: absolute;
  bottom: 0;
  right: 0;
  text-align: center;
  font-size: 1em;
  text-transform: uppercase;
  padding: 10px;
  font-family: "Lato", sans-serif;
}
footer p {
  color: #EF8D9C;
  letter-spacing: 2px;
}
footer a {
  color: #B0DB7D;
  text-decoration: none;
}
footer a:hover {
  color: #FFC39E;
}

</style>