<template>
	<div class="contact-us">
      <h2>회원가입</h2>
			<input v-model="user" placeholder="아이디" required="" type="text"/>
			<input v-model="pass" name="password" placeholder="비밀번호" type="password"/>
			<input v-model="passcheck" name="password2" placeholder="비밀번호확인" type="password"/>
			<input v-model="email" placeholder="이메일주소" required="" type="text"/>
			<input v-model="name" placeholder="이름" required="" type="text"/>
			<input v-model="nickname" placeholder="별명" required="" type="text"/>
			<input v-model="birth" placeholder="생년월일 (ex 2020-01-01)" required="" type="text"/>
      <select v-model="user_type" style="border:3px solid">
        <option value="student" selected>학생</option>
        <option value="teacher">교육자</option>
			</select>
      <div class="join-btns">
        <button @click="join">가입하기</button><br>
        <p class="join-ment">이미 회원이신가요?</p>
        <button @click="home">돌아가기</button>
      </div>
	</div>
</template>

<script>
import axios from 'axios'
export default {
  name: "join",
  data(){
    return {
      user : "",
			pass : "",
			passcheck : "",
			email : "",
			name : "",
			nickname : "",
			birth : "",
			user_type : "student",

			loginuser : "",
			loginpass : ""
    }
  },
  methods :{
    home(){
      this.$router.push('/')
    },
    join() {
			let emailpattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			let birthpattern = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
			let User = {
				'userId' : this.user,
				'userPw' : this.pass,
				'userName' : this.name,
				'nickName' : this.nickname,
				'userType' : this.user_type,
				'birthDate' : this.birth,
				'email' : this.email
			}
			var config={
                header:{
                    'Content-Type' : 'application/json',
                }
			};
			if(this.user.length < 6  || this.user.length > 12){
				alert("아이디 길이를 확인하세요.")
				return;
			}
			if(this.pass.length < 6  || this.pass.length > 12){
				alert("패스워드 길이를 확인하세요.")
				return;
			}
			if(emailpattern.test(this.email)==false){
				alert("이메일 형식을 확인하세요.")
				return;
			}
			if(birthpattern.test(this.birth)==false){
				alert("생일 형식을 확인하세요.")
				return;
			}

			axios.post('/user/sign',User,config)
			.then((res)=>{
				res
        alert("회원가입완료")
        let User = {
				'userId':this.user,
				'userPw':this.pass
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
            this.$router.push('/');
          }else{
                  alert('아이디와 비밀번호를 확인해주세요');
          }
        }).catch((err)=>{
          err
          alert('아이디와 비밀번호를 확인해주세요System Error');
        })
      })
      .catch((error)=>{
        error
        alert('아이디가 중복입니다.')
      })
			
		},
  }
}
</script>

<style scoped>
.contact-us {
  font-family: 'Jua', sans-serif;
	width: 40%;
	margin: 1% auto;
  background: #f8f4e5;
  padding: 50px 100px;
  border: 2px solid black;
  box-shadow: 15px 15px 1px #ffa580, 15px 15px 1px 2px black;
}

input {
  font-family: 'Jua', sans-serif;
  display: block;
  width: 100%;
  font-size: 14pt;
  line-height: 28pt;
  margin-bottom: 28pt;
  border: none;
  border-bottom: 5px solid black;
  background: #f8f4e5;
  min-width: 250px;
  padding-left: 5px;
  outline: none;
  color: black;
}
.join-ment{
  margin:0;
}
input:focus {
  border-bottom: 5px solid #ffa580;
}

button {
  display: block;
  margin: 0 auto;
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

@media (min-width:701px) and (max-width: 1070px){
  .contact-us {
    font-family: 'Jua', sans-serif;
    width: 70%;
    margin: 1% auto;
    background: #f8f4e5;
    padding: 50px 100px;
  }
}

@media (min-width:301px) and (max-width: 700px){
    .contact-us {
    font-family: 'Jua', sans-serif;
    width: 90%;
    margin: 1% auto;
    background: #f8f4e5;
    padding: 50px 5vw;
  }
  .join-ment{
    display: none;
  }
  .join-btns{
    display: flex;
  }

}


@media (max-width: 300px){
  
  .join-ment{
    display: none;
  }
  .join-btns{
    display: flex;
  }
}

</style>