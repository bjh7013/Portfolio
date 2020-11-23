<template>
  <div>
		<div v-if="check==1"> <!-- 가입 가능하면-->
			<v-btn @click="joinclass">가입하기</v-btn>
		</div>
		<div v-else-if="check==3"> <!-- 가입대기중-->
			<v-btn>가입대기중</v-btn>
		</div>
		<div v-else-if="check==2"> <!-- 가입한 상태-->
			<v-btn @click="outclass">클래스 탈퇴</v-btn>
		</div>
		<div v-else-if="check==4"> <!-- 가입거절상태-->
			<v-btn>가입거절</v-btn>
		</div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
	name : 'Classinner',
	props:['classNo'],
	data() {
		return {
			classInfo : [],
			check : 1, //1이면 가입신청가능, 2이면 가입 한 상태, 3이면 대기중인 상태, 4이면 가입 거절 상태
			show: false,
			wishjoinlist : [],
			memberlist : [],
		}
	},
	created() {
		axios.get(`class/oneclass/`+this.$route.params.classno)
		.then((Response)=>{
			this.classInfo = Response.data
			if(this.classInfo.userNo == this.userInfo.userNo){ //관리자일때
				axios.get(`class/callwaitclass/`+this.$route.params.classno)
				.then((res)=>{
					this.wishjoinlist = res.data
				})
				.catch((err)=>{
					err
				})

				axios.get(`class/memberlist/`+this.$route.params.classno) //해당클래스 회원들 불러오기
				.then((res)=>{
					this.memberlist = res.data
				})
				.catch((err)=>{
					err
				})
			}
		})
		.catch((error)=>{
			error
		})

		this.checkjoin()
	},
	methods : {
		outclass() {
			axios.delete(`class/outclass/${this.$route.params.classno}`)
			.then((Response)=>{
				Response
				alert('클래스 탈퇴가 완료되었습니다.')
				this.$router.go()
			})
		},
		acceptjoin(userno) {
			let data = {
				classNo : this.$route.params.classno,
				studentNo : userno,
				isApproved : 1
			}
			axios.put('class/classjoin',data)
			.then((Response)=>{
				Response
				alert("가입 수락 완료.");
			})
		},
		rejectjoin(userno){
			let data = {
				classNo : this.$route.params.classno,
				studentNo : userno,
				isApproved : 0
			}
			axios.put('/class/classjoin',data)
			.then((Response)=>{
				Response
				alert("가입 거절 완료.");
			})
		},
		checkjoin(){
			axios.post('/class/checkjoin',{
				classNo : this.$route.params.classno,
				studentNo : this.userInfo.userNo
			})
			.then((Response)=>{
				this.check = Response.data
			})
		},
		joinclass() {
			axios.post('/class/joincall',{
				classNo : this.classNo
			})
			.then((Response)=>{
				Response
				alert('가입 신청이 완료되었습니다.')
			})
		}
	},
	computed : {
		userInfo() {
			return this.$store.getters.userInfo
		},
		url() {
			return this.$store.getters.BACKEND_URL
		}
	}
}
</script>

<style>

</style>