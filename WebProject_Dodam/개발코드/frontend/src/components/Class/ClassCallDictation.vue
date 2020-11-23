<template>
  <div class="homeworkWrapper">
    <div>
			<div style="text-align:left;margin:0 5%;display:flex;flex-wrap: wrap;width:100%">
				<div style="display:flex; min-width: 300px; width:45%;">
          <div>과제명</div>
          <input type="text" v-model="homeworkName" placeholder="제목을 입력해주세요" style="width:75%;margin:auto;">
        </div>
				<div style="min-width:300px; width:45%;">
          과제마감날짜/
          <input v-model="dueDate" id="endtime" type="date" style="width:60%;margin:auto;" /></div>
				<div style="min-width:90%;"><input type="text" placeholder="과제설명을 입력해주세요" style="border" v-model="homeworkcontent"></div>
				
        <div style="min-width:300px; width:45%;"><v-select
          :items="sessionInfo"
          label="세션목록"
          v-model="sessionNo"
          ></v-select></div>
				<div style="min-width:300px; width:45%;">
          <v-select
          :items="dictations"
          label="받아쓰기세트"
          v-model="dictationindex"
          ></v-select></div>
			</div>
		</div>
		<v-btn @click="addDictationSet">문제Set추가</v-btn>
		<div class='problem' v-for="(problem,i) of dictationList" :key="i"> 
			<div class='idx'>{{i + 1}}번:</div>
			<div class='problem-text' @keyup.enter="problemAdd"><input v-model="problem.dictationSentence" type="text" name="test_text" placeholder="문제를 입력해주세요" maxlength="20"></div>
			<div class='problem-del' @click="problemDel(i)"><a><i class="far fa-minus-square fa-lg"></i></a></div>
			{{setAudio}}
			<voice-recorder :idx='i' :setAudio="problem.setAudio" @audio-change="audioChange" class="recorder"></voice-recorder>
		</div>
		
		<div class='problem-add' @click="problemAdd"><li><a><i class="far fa-plus-square fa-lg"></i></a></li></div>
		<v-btn class='problems-create' @click="createProblems">과제생성</v-btn>
		<v-dialog v-model="OK" fullscreen>
			<v-container>
				<div id="container" style="margin:20% 30%">
				<div id="success-box">
					<div class="face">
					<div class="eye"></div>
					<div class="eye right"></div>
					<div class="mouth happy"></div>
					</div>
					<div class="shadow scale"></div>
					<div class="message">
					<h1 class="alert">성공했어요!</h1>
					<p>받아쓰기 과제 만들기 성공!</p>
					</div>
					<button class="button-box green">
					<router-link :to="`/homework/`+this.$route.params.classno"><h1>확인</h1></router-link>
					</button>
				</div>
				</div>
			</v-container>
		</v-dialog>
  </div>
</template>

<script>
import VoiceRecorder from '@/components/VoiceRecord.vue'
import axios from 'axios'
export default {
    name: 'classcalldictation',
    components:{VoiceRecorder: VoiceRecorder},
    data(){
		return {
			homeworkName:'',
			homeworkType:'dictation',
			dueDate: null,
			dictationList:[],
			dictations :[],
			dictationindex : 0,
      startdate : "",
			sessionNo : 0,
			sessionInfo:[],
			enddate : "",
			homeworkcontent : null,
			basewordindex : 0,
			OK : false
		}
	},
	created(){
		this.get_classsession()
		this.get_dictationset_list()
	},
	methods:{
		clear(){
			this.$router.push(`/homework/`+this.$route.params.classno);
		},
		get_classsession(){
      axios.get(`/classsession/selectclasssession/`+this.$route.params.classno)
      .then((Response)=>{
        for(let i=0;i<Response.data.length;i++){
          let temp = {
            text : Response.data[i].sessionName,
            value : Response.data[i].sessionNo
          }
          this.sessionInfo.push(temp);
        }
      })
      .catch((error)=>{
        error
      })
    },
		problemAdd(){
			this.dictationList.push({dictationSentence:'', audioString: null, homeworkNo:4})
		},
		problemDel(idx){
			this.dictationList.splice(idx,1)
		},
		audioChange(i,base64Data){
			this.dictationList[i].audioString = base64Data
		},
		createProblems(){
			if(!this.homeworkName){
				alert('과제명을 입력해주세요!')
				return
			} 
			if(!this.dueDate){
				alert('마감일을 입력해주세요');
				return
			}
			if(this.dictationList.length == 0){
				alert('문제를 입력해주세요')
				return
			}
			if(!this.sessionNo){
				alert('과제를 등록할 세션을 선택해주세요')
				return
			}
			
			axios.post(`/dictation/dictation`,{
				homeworkName: this.homeworkName,
				homeworkType:this.homeworkType,
				dueDate:this.dueDate,
				dictationList:this.dictationList,
				sessionNo : this.sessionNo,
			})
			.then(() => {
				this.OK = true;
			})
			.catch(()=>{
				alert('error')
			})
		},
		get_dictationset_list(){
            axios.get('dictation/level')
            .then((response)=>{
                for(let i=0;i < response.data.length;i++){
					if(response.data[i] == null){
						continue;
					}
                    let temp = {
                        text : response.data[i].dictationLevel +"번세트",
                        value : response.data[i].dictationLevel
					}
					this.dictations.unshift(temp)
				}
				this.basewordindex = response.data.length
				
                axios.get('dictation/dictationset')
                .then((Response)=>{
                    for(let i=0;i<Response.data.length;i++){
                        let temp = {
                            text : Response.data[i].dictationsetName,
                            value : Response.data[i].dictationsetNo + response.data.length
						}
                        this.dictations.unshift(temp)
					}

                })
                .catch((error)=>{
                    error
                    alert("받아쓰기 호출에 실패했습니다.")
                })
            })

		},
		
		addDictationSet(){
			if(this.basewordindex < this.dictationindex){
				axios.get('dictation/dictationcustom/'+ (this.dictationindex - this.basewordindex))
                .then((Response)=>{
						
					for(let i=0;i<Response.data.length;i++){
						let audioFullPath = this.$store.getters.BACKEND_URL + Response.data[i].audioPath
						
						let temp = {
							dictationSentence : Response.data[i].dictationProblem,
							audioString : 'fromSet',
							setAudio : new Audio(audioFullPath),
							audioPath : Response.data[i].audioPath
						}
						this.dictationList.push(temp)
						// axios.get(audioFullPath)
						// .then( ({data}) => {
						// 	let audio = new Audio(audioFullPath)
							
						// 	this.dictationList[idx].setAudio = audio
						// })

                    }
                })
            }else{
				axios.get('dictation/dictationlevel/'+ this.dictationindex)
                .then(({data})=>{				
					for(let i=0; i< data.length; i++){
						
						let audioFullPath = this.$store.getters.BACKEND_URL + data[i].audioPath
						
						let temp = {
							dictationSentence : data[i].dictationSentence,
							audioString : 'fromSet',
							setAudio : new Audio(audioFullPath),
							audioPath : data[i].audioPath
						}

						this.dictationList.push(temp)
          }
        })
      }
    }
	}
}
</script>

<style scoped>

ul {
	list-style: none;
}
li {
	display: inline;
	text-align: -webkit-match-parent;
}
.problem{
	display: flex;
	justify-content: center;
	align-items: center;
}
.homeworkWrapper{
	width : 94%;
}
.recorder{
	width:100px;
	height: 50px;
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

#container {
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