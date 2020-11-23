<template>
  <v-app>
		<div>
			<h1>모든 교실</h1>
			<!-- 검색 -->
			<div class="search-bar">
				<b-select  class="search-select" v-model="selected" :options="options">
				</b-select>
				<b-input class="search-input" type="text" v-model="searchText" placeholder="검색어를 입력하세요." @keyup.enter="search"/>
				<b-button class="search-btn" @click="search" style="height: calc(1.5em + .75rem + 2px);display: flex;"><i class="fas fa-search" style="margin: auto;"></i><p class="nomobile"> 검색</p></b-button>
			</div>
			<!-- 교실 전체 -->
			<v-card elevation="0">
				<v-container>
					<v-row dense>
						<v-flex xs12 sm6 md6 v-for="(clazz, index) in classes" :key="index" style="padding:1%">
							<!-- 교실 하나 -->
							<v-card class="class_cards">
								<div class="d-flex flex-no-wrap justify-space-between ismobile">
									<div>
										<v-card-title class="justify-center" v-text="clazz.className" max-height="21%" style="height:21%"></v-card-title>
										<v-card-subtitle max-height="53%" style="height:53%; margin:0;">
											<div style="min-width:220px;">
												<p>수강인원 : {{clazz.studentCnt}}/{{ clazz.userLimit }}</p>
												<p>수강연령 : {{ageTranslator(clazz.targetAge)}}</p>
												<p>수업기간 : {{clazz.startDate }} ~ {{ clazz.endDate }}</p>
												<p>선생님 : {{clazz.teacherName}}</p>
											</div>
										</v-card-subtitle>
										<v-card-actions class="justify-center" v-if="userType == 'student'" max-height="20%" style="height:20%">
											
											<div v-if="clazz.studentCnt >= clazz.userLimit">
												<v-btn class="ml-2 mt-5" outlined rounded medium>
													<div v-if="clazz.approved==1"> <!-- 가입한 상태-->
														<button @click="outclass(clazz.classNo)">교실 탈퇴</button>
													</div>
													<div v-else>
														<button>인원 초과</button>
													</div>
												</v-btn>
											</div>
											<div v-else-if="clazz.endDate > `2020-11-22`">

												<v-btn class="ml-2 mt-5" outlined rounded medium>
													<!-- <class-inner :classNo="clazz.classNo"></class-inner> -->
													<div v-if="clazz.approved==0"> <!-- 가입 가능하면-->
														<button @click="joinclass(clazz.classNo,index)">가입하기</button>
													</div>
													<div v-else-if="clazz.approved==2"> <!-- 가입대기중-->
														<button>가입대기중</button>
													</div>
													<div v-else-if="clazz.approved==1"> <!-- 가입한 상태-->
														<button @click="outclass(clazz.classNo)">교실 탈퇴</button>
													</div>
													<div v-else-if="clazz.approved==3"> <!-- 가입거절상태-->
														<button>가입거절</button>
													</div>
													
												</v-btn>
											
											</div>
											<div v-else>
												<div v-if="clazz.approved==1"> <!-- 가입한 상태-->
													<v-btn class="ml-2 mt-5" outlined rounded medium>
														<button @click="outclass(clazz.classNo)">교실 탈퇴</button>
													
													</v-btn>
												</div>
												<div v-else>
													<div style="height:56px">
													</div>
												</div>
											</div>
										</v-card-actions>
									</div>
									<!-- 교실 이미지 -->
									<div style="width:100%">
										<v-avatar class="" size="90%" tile>
											<v-img :src="url + clazz.classImg" aspect-ratio="1" class="class_img"></v-img>
										</v-avatar>
										<div v-if="clazz.startDate >`2020-11-22`">
											<v-btn class="ml-2 mt-5 class-status" outlined rounded medium style="background-color:#006fff">
												<button>
													예정
												</button>
											</v-btn>
										</div>
										<div v-else-if="clazz.startDate <`2020-11-22`&& clazz.endDate >`2020-11-22`">
											<v-btn class="ml-2 mt-5 class-status" outlined rounded medium style="background-color:#6fff00">
												<button>
													진행
												</button>
											</v-btn>
										</div>
										<div v-else-if="clazz.endDate <`2020-11-22`">
											<v-btn class="ml-2 mt-5 class-status" outlined rounded medium style="background-color:#ff006f">
												<button>
													종료
												</button>
											</v-btn>
										</div>
									</div>
								</div>
								<div style="background-color:#bbbbbb;" @click="clickdesc(index)">
									교실 설명 보기
									<div :id="`desc`+index" class="div-desc" style="display:none;">
										소개 : {{ clazz.classDesc }}
									</div>
								</div>
							</v-card>
						</v-flex>
					</v-row>
				</v-container>
			</v-card>
		</div>
  </v-app>
</template>

<script>
import axios from 'axios'
// import ClassInner from '@/components/'

export default {
	name : 'Classsearch',
	props:['classNo'],
	data() {
		return {
			// showdesc: false,
			classes : [],
			selected: 1,
			searchText:"",
			options: [
			{ value: 1, text: '교사' },
			{ value: 2, text: '클래스' }
			],
			saveClasses:[],

			classInfo : [],
			check : 1, //1이면 가입신청가능, 2이면 가입 한 상태, 3이면 대기중인 상태, 4이면 가입 거절 상태
			show: false,
			wishjoinlist : [],
			memberlist : [],
		}
	},
	created() {
		axios.get('class/allclass')
		.then((Response)=>{
			this.classes = Response.data
			this.saveClasses=this.classes
		})
		.catch((error)=>{
			error
		})
	},
	computed : {
		url() {
			return this.$store.getters.BACKEND_URL
		},
		userInfo(){
			return this.$store.getters.userInfo
		},
		userNo(){
			return this.$store.getters.userInfo.userNo
		},
		userType(){
			return this.$store.getters.userInfo.userType
		},
	},
	methods: {
		clickdesc(index){
			var divdesc = document.getElementById("desc"+index);
			divdesc.style.display = (divdesc.style.display=="none")?"block":"none"; 
		},
		search(){
			var searchType=this.selected;
			var text=this.searchText;
			this.classes=this.saveClasses.filter(function(element){
				if(searchType==1){
					if(element.teacherName.indexOf(text) != -1){
						return element;
					}
				}else{
					if(element.className.indexOf(text) != -1){
						return element;
					}
				}
			})
		},
		ageTranslator(age){
			var ageList=["유치원생","초등학생(저학년)","초등학생(고학년)","중학생","고등학생","대학생","기타"]
			return ageList[age];
		},
		outclass(val) {
			axios.delete(`class/outclass/`+val)
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
				isApproved : 2
			}
			axios.put('/class/classjoin',data)
			.then((Response)=>{
				Response
				alert("가입 거절 완료.");
			})
		},
		joinclass(val,index) {
			axios.post('/class/joincall',{
				classNo : val
			})
			.then((Response)=>{
				Response
				alert('가입 신청이 완료되었습니다.')
				this.classes[index].approved = 2
			})
		}
	}
}
</script>

<style scoped>
.class-status{
	top:0;
	position: absolute;
	right: 20px;
}
body {
  color: #555;
  background: #222;
  text-align: center;
  padding: 1em;
}

h1 {
  font-size: 2.5em;
}

.class_img {
	margin-top: 10%;
	height: 100%;
	width: 50%;
}

.class_cards {
	height: 100%;
	width: 100%;
}
.search-select{
    width: 90px;
}
.search-input{
	width:40%;
}
.search-bar{
	display: flex;
	justify-content: center;
}
.search-btn{
	min-width: 80px;
	width: 10vw;
    max-width: 85px;
	/* height: calc(1.5em + .75rem + 2px);
	display: flex; */
}
.div-desc{
	
    border-radius: 0 0 20px 20px;
    padding: 6px;
    position: absolute;
    background: #dddddd;
    width: 100%;
    z-index: 1;
    opacity: 80%;
	
}
@media (max-width: 1000px){
	.nomobile{
		display: none;
	}
	.search-bar{
		margin: 0;
	}
.search-btn{
	width: calc(1.5em + .75rem + 2px);
	
	min-width: calc(1.5em + .75rem + 2px);
}
.ismobile{
    flex-direction: column-reverse;
}
}
</style>