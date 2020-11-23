<template>
	<v-app>
		<nav-bar></nav-bar>
		<div style="height:100%">
      <h1 style="margin:auto auto;" v-if="nullMessage!=null">{{nullMessage}}</h1>
			<div class="child-form">
        <div class="card green" v-for="child in childrenInfo" :key="child.userNo">
          <div>
            <MyChildrenItem :child="child" />
          </div>
        </div>
			</div>
		</div>
    
		<foot-bar></foot-bar>
  <Loading />
	</v-app>
</template>

<script>
import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import FootBar from '@/components/FootBar.vue'
import MyChildrenItem from "@/components/MyChildrenItem";
import Loading from "@/components/Loading"

export default {
	name: 'mychildren',
	components: {
		NavBar,
    FootBar,
    Loading,
    
    MyChildrenItem,
	},
	data(){
		return {
      childrenInfo :[],
      nullMessage:null,
		}
	},
	created(){
		this.get_childrenInfo()
	},
	methods : {
		get_childrenInfo(){
			axios.get('user/childreninfo')
			.then((Response)=>{
        this.childrenInfo = Response.data
        if(this.childrenInfo.length==0){
          this.nullMessage="관리대상인 아이가 없어요....."
        }
        for(var i=0;i<this.childrenInfo.length;i++){
         this.getStatus(this.childrenInfo[i].userNo,i);
        }
			})
			.catch((error)=>{
				error
			})
    },
    getStatus(userNo,i){
			axios.get("/user/userstatusbyuserno/"+userNo).then((response)=>{
        var userStatus=response.data;
        this.$set(this.childrenInfo[i],"korean",Math.round((userStatus.korean/63)*100));
        this.$set(this.childrenInfo[i],"wordtest",Math.round((userStatus.wordtest/89)*100));
        this.$set(this.childrenInfo[i],"dictation",Math.round((userStatus.dictation/400)*100));
      }).catch((err)=>{
        err
      })
      
  
		},
		delparent(childno){
			axios.delete('user/child/'+childno)
			.then((response)=>{
				response
				alert('삭제되었습니다.')
				this.$router.go();
			})
    },
    childernDetail(userNo){
      this.$router.push(`/childdetail/${userNo}`)

    }
	},
}
</script>

<style scoped>
.child-form{
  width: 100%;
  max-width: 2200px;
  margin: 0 auto;
  display:flex;
  flex-wrap:wrap;
  height:90%;    
  justify-content: space-around;
  align-content: flex-start;
}

.card {
  width: 30%;
  height: 30vh;
  max-height: 300px;
  background-color: #fff;
  background: linear-gradient(#f8f8f8, #fff);
  box-shadow: 0 8px 16px -8px rgba(0,0,0,0.4);
  border-radius: 6px;
  overflow: hidden;
  position: relative;
  margin: 1.5rem;
}
.card-item{
  
  width: 45%;
  /* 474px해야 전부 볼 수 있다 */
  height: 40%;
  background-color: #fff;
  background: linear-gradient(#f8f8f8, #fff);
  box-shadow: 0 8px 16px -8px rgba(0,0,0,0.4);
  border-radius: 6px;
  overflow: hidden;
  position: relative;
  margin: 1.5rem;
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
    width: 96vw;
    margin: 1vw 2vw;

  }
  .more-info{
    width:200px;
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
}

@media (min-width:481px) and (max-width: 940px){
  .card{
    width: 96vw;
    margin: 1vw 2vw;

  }
}
@media (min-width:941px) and (max-width: 1410px){
  .card{
    width: 46vw;
    margin: 1vw 2vw;
  }
}
</style>