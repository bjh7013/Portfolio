<template>
  <v-app>
		<nav-bar></nav-bar>
			<v-container>
				<v-card style="height:100%; min-height:80vh; background-color:white;" elevation="0">
					<v-card class="card-title">
						<div style="display:flex; width:100%;height:100%;justify-content:space-between; align-content:center;">
							<div style="margin:auto 1%; max-width:145px;">
								<h3>{{userInfo.userName}} 학생</h3>
							</div>
							<div style="margin:auto 1%;" v-if="userInfo.userType==='student'">
							
								<v-dialog v-model="dialog" persistent max-width="600px">
									<!-- 버튼 -->
									<!-- 학생일 때만 부모님 등록 보이게 -->
									<template  v-slot:activator="{ on, attrs }">
										<v-btn  color="primary" dark v-bind="attrs" v-on="on" style="">부모님 등록</v-btn>
									</template>
									<!-- 모달 -->
									<v-card>
										<v-card-title>
											<span class="headline">부모님 검색</span>
										</v-card-title>
										<v-card-text>
											<v-container>
												<v-row>
												<v-col cols="12" sm="6" md="4">
													<v-text-field	label="아이디입력" v-model="parentId" required></v-text-field>
												</v-col>
												</v-row>
											</v-container>
										</v-card-text>

										<v-card-actions>
										<v-spacer></v-spacer>
										<v-btn color="blue darken-1" text @click="dialog = false">닫기</v-btn>
										<v-btn color="blue darken-1" text @click="addParent">등록</v-btn>
										</v-card-actions>
									</v-card>
								</v-dialog>
							</div>
						</div>

						<!-- <make-parent></make-parent> -->
					</v-card>
					<v-cards>
						<div style="display:flex; flex-wrap:wrap; justify-content:space-between;">
							<div class="child-point">
								<v-card style="height:5vh">
									학습차수
								</v-card>
								<v-row>
									<v-col cols="4">
										<div class="pa-7 yellow rounded-circle d-inline-block" style="color:black">{{userStatus.korean}}%</div>	
										<p>글자공부</p>
									</v-col>
									<v-col cols="4">
										<div class="pa-7 red rounded-circle d-inline-block">{{userStatus.wordtest}}%</div>	
										<p>낱말연습</p>
									</v-col>
									<v-col cols="4">
										<div class="pa-7 green rounded-circle d-inline-block">{{userStatus.dictation}}%</div>	
										<p>받아쓰기</p>
									</v-col>
								</v-row>
							
							</div>
								
							<div class="child-ment">
								<v-card style="height:100%">
									<p style="margin-top:1%">우리아이는?</p>
									<!-- %, 점수에 따라 내용정해야함 -->
									<p>{{getKoreanStatus(userStatus.korean)}}</p>
									<p>{{getWordStatus(userStatus.wordtest)}}</p>
									<p>{{getDictationStatus(userStatus.dictation)}}</p>
								</v-card>
								
							</div> 
						</div>
						<div class="last-sol">
							<div style="margin: 10px 0;">
								<v-card style="height:5vh;">
									최근 푼 문제
								</v-card>
								<div class="last-sol" style="height:100%;">
									<v-expansion-panels popout>
										<v-expansion-panel v-for="(dictation, i) in dictation_results" :key="i">
											<v-expansion-panel-header>
												<v-row>
													<v-col cols="11">{{dictation.dictationNo}}번 문제</v-col>
												</v-row>
											</v-expansion-panel-header>
											<v-expansion-panel-content>
												<div style="text-align:left;">
													<p>정답여부:{{isCorrect(dictation.correct)}}</p>
													<img style="width:900px"  class="ocrimgs" :src="$store.getters.BACKEND_URL+dictation.submitImg"/>
												</div>
											</v-expansion-panel-content>
										</v-expansion-panel>
									</v-expansion-panels>
								</div>
							</div>
						</div>
					</v-cards>
				</v-card>
			</v-container>
		<foot-bar></foot-bar>
		<Loading />
  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import FootBar from '@/components/FootBar.vue'
import axios from 'axios'
import Axios from 'axios'
import Loading from "@/components/Loading"

export default {
	name: 'childrendetail',
	components: {
		NavBar,
		FootBar,
		Loading,
		// MakeParent
	},
	data(){
		return {
			dialog: false,
			parentId : "",
			contentsnames: ['글자공부-한글','낱말연습','받아쓰기'],
			score:["0%","0%","0%"],
			contentsdetails: [
				{ id:'1', name: '자음', result: '%'},
				{ id:'1', name: '모음', result: '%' },
				{ id:'1', name: '쌍자음', result: '%' },
				{ id:'1', name: '이중모음', result: '%'},
				{ id:'1', name: '글자', result: '%' },
			
			],
			dictation_results:[
				
			],
			userStatus:[]


		}
	},created(){
		if(this.$session.get('jwt')==null){
			this.$router.push('/')
		}

		if(this.$route.params.userno){
			this.getScoreByNo();
			this.getStatusByNo();
			this.getDictationByNo();	
		}else{
			this.getScore();
			this.getStatus();
			this.getDictation();
		}
    },
	methods : {
		addParent() {
			this.dialog = false
			axios.get(`user/info/${this.parentId}`)
			.then((Response)=>{
				axios.get('user/makeparent/'+Response.data.userNo)
				.then((response)=>{
					response
					alert("부모 등록 신청 완료.")
				})
				.catch((error)=>{
					error
					alert("이미 등록되어 있습니다.")
				})
			})
		},
		getScore(){
			Axios.get("/koreandetail/score").then((response)=>{
					this.contentsdetails[0].result=Math.round((response.data[1]/14)*100)+"%";
					this.contentsdetails[1].result=Math.round((response.data[2]/10)*100)+"%";
					this.contentsdetails[2].result=Math.round((response.data[3]/5)*100)+"%";
					this.contentsdetails[3].result=Math.round((response.data[4]/11)*100)+"%";
					this.contentsdetails[4].result=Math.round((response.data[5]/14)*100)+"%";
					var sum=response.data[1]+response.data[2]+response.data[3]+response.data[4]+response.data[5];
					this.score[0]=Math.round((sum/63)*100)+"%";
				}).catch((err)=>{
					err
				})
		},
		getStatus(){
			Axios.get("/user/userstatus").then((response)=>{
					this.userStatus=response.data;
					this.userStatus.dictation=Math.round((this.userStatus.dictation/400)*100);
					this.userStatus.korean=Math.round((this.userStatus.korean/63)*100);
					this.userStatus.wordtest=Math.round((this.userStatus.wordtest/89)*100);
				}).catch((err)=>{
					err
				})
		
		},getDictation(){
			Axios.get("/dictationsubmit/dictationsubmituserno").then((response)=>{
						this.dictation_results=response.data;
			
                    }).catch((err)=>{
                        err
                    })
		
		},getScoreByNo(){
			Axios.get("/koreandetail/scoreuserno/"+this.$route.params.userno).then((response)=>{
						this.contentsdetails[0].result=Math.round((response.data[1]/14)*100)+"%";
						this.contentsdetails[1].result=Math.round((response.data[2]/10)*100)+"%";
						this.contentsdetails[2].result=Math.round((response.data[3]/5)*100)+"%";
						this.contentsdetails[3].result=Math.round((response.data[4]/11)*100)+"%";
						this.contentsdetails[4].result=Math.round((response.data[5]/14)*100)+"%";
						var sum=response.data[1]+response.data[2]+response.data[3]+response.data[4]+response.data[5];
						this.score[0]=Math.round((sum/54)*100)+"%";
                    }).catch((err)=>{
                        err
                    })
		},
		getStatusByNo(){
			Axios.get("/user/userstatusbyuserno/"+this.$route.params.userno).then((response)=>{
					this.userStatus=response.data;
					this.userStatus.dictation=Math.round((this.userStatus.dictation/400)*100);
					this.userStatus.korean=Math.round((this.userStatus.korean/63)*100);
					this.userStatus.wordtest=Math.round((this.userStatus.wordtest/89)*100);
				}).catch((err)=>{
					err
				})
		
		},getDictationByNo(){
			Axios.get("/dictationsubmit/dictationsubmitusernobydirect/"+this.$route.params.userno).then((response)=>{
						this.dictation_results=response.data;
			
                    }).catch((err)=>{
                        err
                    })
		
		},
		getKoreanStatus(score){
			
			if(score<=10){
				return "글자공부 진도가 몹시 느려요, 추가학습이 필요해요!";
			}else if(score<=30){
				return "글자공부 진도가 느려요";
			}else if(score<=60){
				return "글자공부 진도가 평균이에요!"
			}else{
				return "글자공부 진도가 빨라요!"
			}

			
		},getWordStatus(score){
			if(score<=10){
				return "낱말연습 진도가 몹시 느려요, 추가학습이 필요해요!";
			}else if(score<=30){
				return "낱말연습 진도가 느려요";
			}else if(score<=60){
				return "낱말연습 진도가 평균이에요!"
			}else{
				return "낱말연습 진도가 빨라요!"
			}
		},getDictationStatus(score){
			if(score<=10){
				return "받아쓰기 진도가 몹시 느려요, 추가학습이 필요해요!";
			}else if(score<=30){
				return "받아쓰기 진도가 느려요";
			}else if(score<=60){
				return "받아쓰기 진도가 평균이에요!"
			}else{
				return "받아쓰기 진도가 빨라요!"
			}
		},calcul(score,index){
			return Math.round((score/index)*100);
		},isCorrect(bool){
			if(bool){
				return "정답";
			}else{
				return "오답";
			}
		}
	},computed:{
		userInfo:function(){
			return this.$store.getters.userInfo;
		}
	}
}
</script>

<style scoped>
.last-sol{
	width: 100%;
	min-width: 300px;
}
.child-point{
	width: 25%;
	min-width:300px;
}
.child-ment{
	width:60%;
	min-width: 300px;
}

.v-card {
	background-color: #EFFFE9;
	border-radius: 5px;
	padding: 1%;
}
.card-title {
	width: 100%;
	min-width: 300px;
	height: 7%;
	text-align: left;
}

@media (min-width:740px) and (max-width: 1000px){
	
	.card-title {
		width: 700px;
		margin: 10px auto;
	}
	.child-point{
		width:700px;
		margin: 10px auto;
	}
	.child-ment{
		width:700px;
		margin: 10px auto;
	}
	.last-sol{
		width: 700px;
		margin: 10px auto;

	}
}
@media (min-width:540px) and (max-width: 740px){
	
	.card-title {
		width: 500px;
		margin: 10px auto;
	}
	.child-point{
		width:500px;
		margin: 10px auto;
	}
	.child-ment{
		width:500px;
		margin: 10px auto;
	}
	.last-sol{
		width: 500px;
		margin: 10px auto;

	}
}
@media (min-width:440px) and (max-width: 540px){
	
	.card-title {
		width: 400px;
		margin: 10px auto;
	}
	.child-point{
		width:400px;
		margin: 10px auto;
	}
	.child-ment{
		width:400px;
		margin: 10px auto;
	}
	.last-sol{
		width: 400px;
		margin: 10px auto;

	}
}

@media (max-width: 440px){
	
	.card-title {
		width: 300px;
		margin: 10px auto;
	}
	.child-point{
		width:300px;
		margin: 10px auto;
	}
	.child-ment{
		width:300px;
		margin: 10px auto;
	}
	.last-sol{
		width: 300px;
		margin: 10px auto;

	}
}
</style>