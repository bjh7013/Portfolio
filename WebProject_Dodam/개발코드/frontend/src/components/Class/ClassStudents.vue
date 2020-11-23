<template>
  <v-app>

  <!-- <div> -->
    학생목록
    <div class="member-list">
      <div class="card green" v-for="(member,index) in memberlist" :key="index">
        
				<div class="additional">
					<div class="user-card">
						<br>
						<v-avatar size="50%">
							<v-img src="../../assets/images/boy.png">
							</v-img>
						</v-avatar><br>
                    <v-btn @click="rejectjoin(member.userNo,index)">추방</v-btn>
						<!-- <a @click="childernDetail(child.userNo)">자세히보기</a> -->
					</div>
					<div class="more-info"> 
						<h1> {{member.userName}}</h1>
            <h3>({{ member.userId }})</h3>
				
						<div class="stats">
							<div class="stat">
								<div class="title">글자공부</div>
								<i class="fa fa-trophy"></i>
								<div class="value">{{member.korean}}%</div>
							</div>
							<div class="stat">
								<div class="title">낱말연습</div>
								<i class="fa fa-gamepad"></i>
								<div class="value">{{member.wordtest}}%</div>
							</div>
							<div class="stat">
								<div class="title">받아쓰기</div>
								<i class="fa fa-users icon-item"></i>
								<div class="value">{{member.dictation}}%</div>
							</div>
						</div>
					</div>
				</div>
        
        <div style="justify-content: flex-end;display: flex;">
          <div style="width:60%;">
          <h1> {{member.userName}}</h1>
          <h3>({{ member.userId }})</h3>
          </div>
        </div>
				<div class="general">
				</div>
			</div>
    </div>
  </v-app>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return{
      classInfo : [],
      memberlist : [],
      show: false,
    }
  },
  created(){
    this.get_member()
  },
  methods :{
    get_member(){
      axios.get(`class/oneclass/`+this.$route.params.classno)
        .then((Response)=>{
            this.classInfo = Response.data
            if(this.classInfo.userNo == this.userInfo.userNo){ //관리자일때
                axios.get(`class/memberlist/`+this.$route.params.classno) //해당클래스 회원들 불러오기
                .then((res)=>{
                    this.memberlist = res.data
                    for(var i=0;i<this.memberlist.length;i++){
                      this.getStatus(this.memberlist[i].userNo,i)
                    }
                })
                .catch((err)=>{
                    err
                })
            }
        })
        .catch((error)=>{
            error
        })
    }, getStatus(userNo,i){
			axios.get("/user/userstatusbyuserno/"+userNo).then((response)=>{
						var userStatus=response.data;
             this.$set(this.memberlist[i],"korean",Math.round((userStatus.korean/63)*100));
            this.$set(this.memberlist[i],"wordtest",Math.round((userStatus.wordtest/89)*100));
            this.$set(this.memberlist[i],"dictation",Math.round((userStatus.dictation/400)*100));
            }).catch((err)=>{
                        err
            })
		},
    rejectjoin(userno, index){
        let data = {
                classNo : this.$route.params.classno,
                studentNo : userno,
                isApproved : 0,
            }
            axios.put('class/classjoin',data)
            .then((Response)=>{
                Response
                alert("추방완료.");
                this.memberlist.splice(index,1)
            })
        }
  },
  computed : {
    userInfo(){
      return this.$store.getters.userInfo
    },
    url(){
      return this.$store.getters.BACKEND_URL
    }
  }
}
</script>

<style scoped>
.member-list{
  width:100%;
  height:100%;
  display:flex;
  flex-wrap:wrap;
  align-content: flex-start;
  justify-content: space-around;
}
.card {
  width: 30%;
  height: 40%;
  background-color: #fff;
  background: linear-gradient(#f8f8f8, #fff);
  box-shadow: 0 8px 16px -8px rgba(0,0,0,0.4);
  border-radius: 6px;
  min-width:450px;
  overflow: hidden;
  position: relative;
  margin: 1.5rem;
  max-height: 240px;
}

.card h1 {
  text-align: center;
}

.card .additional {
  position: absolute;
  width: 150px;
  height: 100%;
  background: linear-gradient(#dE685E, #EE786E);
  transition: width 0.4s;
  overflow: hidden;
  z-index: 2;
}

.card.green .additional {
  background: linear-gradient(#92bCa6, #A2CCB6);
}


.card:hover .additional {
  width: 100%;
  border-radius: 0 5px 5px 0;
}

.card .additional .user-card {
  width: 150px;
  height: 100%;
  position: relative;
  float: left;
}

.card .additional .user-card::after {
  content: "";
  display: block;
  position: absolute;
  top: 10%;
  right: -2px;
  height: 80%;
  border-left: 2px solid rgba(0,0,0,0.025);
}

.card .additional .user-card .level,
.card .additional .user-card .points {
  top: 15%;
  color: #fff;
  text-transform: uppercase;
  font-size: 0.75em;
  font-weight: bold;
  background: rgba(0,0,0,0.15);
  padding: 0.125rem 0.75rem;
  border-radius: 100px;
  white-space: nowrap;
}

.card .additional .user-card .points {
  top: 85%;
}

.card .additional .user-card svg {
  top: 50%;
}

.card .additional .more-info {
  width: 300px;
  float: left;
  position: absolute;
  left: 150px;
  height: 100%;
}

.card .additional .more-info h1 {
  color: #fff;
  margin-bottom: 0;
}

.card.green .additional .more-info h1 {
  color: #224C36;
}

.card .additional .coords {
  margin: 0 1rem;
  color: #fff;
  font-size: 1rem;
}

.card.green .additional .coords {
  color: #325C46;
}

.card .additional .coords span + span {
  float: right;
}

.card .additional .stats {
  font-size: 2rem;
  display: flex;
  position: absolute;
  bottom: 1rem;
  left: 1rem;
  right: 1rem;
  top: auto;
  color: #fff;
}

.card.green .additional .stats {
  color: #325C46;
}

.card .additional .stats > div {
  flex: 1;
  text-align: center;
}

.card .additional .stats i {
  display: block;
}

.card .additional .stats div.title {
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
}

.card .additional .stats div.value {
  font-size: 1.5rem;
  font-weight: bold;
  line-height: 1.5rem;
}

.card .additional .stats div.value.infinity {
  font-size: 2.5rem;
}

.card .general {
  width: 300px;
  height: 100%;
  position: absolute;
  top: 0;
  right: 0;
  z-index: 1;
  box-sizing: border-box;
  padding: 1rem;
  padding-top: 0;
}

.card .general .more {
  position: absolute;
  bottom: 1rem;
  right: 1rem;
  font-size: 0.9em;
}

@media (max-width: 480px) {
  /* 모바일 환경 */
  .card{
    width: 90vw;
    margin: 1vw 2vw;

  min-width:300px;
  }
  .card .general {
    width: 150px;
  }
  
  .more-info{
    width:150px;
  }
  .more-info>*{
    width:150px;
  }
  .icon-item{
    display: none;
  }
  .general{
    width: 200px;
  }
  .stat{
    display: flex;
  }
  .stats{
    display:contents;
  }
  .coords{
    display:none;
  }
  .card .additional .stats{
      display: block;
  }
  .card .additional .stats .stat{
      display: flex;
  }
  .card .additional .stats i{
      display: none;
  }
}



</style>