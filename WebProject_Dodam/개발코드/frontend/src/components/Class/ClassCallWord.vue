<template>
  <div>
        <div>
			<ul style="text-align:left">
				<li>과제명</li>
				<li><input type="text" placeholder="제목을 입력해주세요" style="border" v-model="homeworkname"></li>
				<li><input type="text" placeholder="과제설명을 입력해주세요" style="border" v-model="homeworkcontent"></li>
				<li><v-select
                :items="sessionInfo"
                label="세션목록"
                v-model="sessionindex"
                ></v-select></li>
			</ul>
		</div>
		<div>
			<ul>
				<li>
              과제마감날짜/
              <input v-model="enddate" id="endtime" type="date"/></li>
				<li><v-select
                :items="words"
                label="낱말세트"
                v-model="wordindex"
                ></v-select></li>
			</ul>
		</div>
        <v-btn @click="addWordSet">문제Set추가</v-btn>
		<div class='problem' v-for="(problem,i) of problems" :key="i"> 
            <v-avatar v-if="problem.wordimg!=null">
                <v-img :src="url + problem.wordimg" class="class_img"></v-img>
            </v-avatar>
            <v-avatar>
                <input v-if="problem.wordimg==null" type="file" id="photo" accept="image/*" @change="onFileChange" @click="index=i"/>
            </v-avatar>
			<div class='idx'>{{i + 1}}번:</div>
			<div class='problem-text' @keyup.enter="problemAdd"><input v-model="problem.wordSentence" type="text" name="test_text" placeholder="문제를 입력해주세요" maxlength="20"></div>
			<div class='problem-del' @click="problemDel(i)"><a><i class="far fa-minus-square fa-lg"></i></a></div>
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
					<p>낱말연습 과제 만들기 성공!</p>
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
import axios from 'axios'
export default {
    name: 'classcallword',
    data(){
		return {
            problems:[],
            index : 0,
            enddate : null,
            words: [],
            wordindex : 0,
            homeworkname : null,
            homeworkcontent : null,
            sessionindex : 0,
            sessionInfo:[],
            basewordindex : 0,
            files : [],
            checktemp : [],
            classimg : [],
            OK : false
		}
    },
    created(){
        this.get_wordset_list()
        this.get_classsession()
    },
	methods:{
        clear(){
            this.$router.go()
        },
        get_wordset_list(){
            axios.get('wordtest/category')
            .then((response)=>{
                for(let i=0;i<response.data.length;i++){
                    let temp = {
                        text : response.data[i].categoryName,
                        value : response.data[i].categoryNo
                    }
                    this.words.push(temp)
                }
                this.basewordindex = response.data.length
                axios.get('wordtest/wordset')
                .then((Response)=>{
                    for(let i=0;i<Response.data.length;i++){
                        let temp = {
                            text : Response.data[i].wordsetTitle,
                            value : Response.data[i].wordsetNo + response.data.length
                        }
                        this.words.push(temp)
                    }
                })
                .catch((error)=>{
                    error
                    alert("망함")
                })
            })

        },
		problemAdd(){
			this.problems.push({wordSentence:'', wordimg:null})
            this.files.push(null)
            this.checktemp.push(0)
		},
		problemDel(idx){
            this.problems.splice(idx,1)
            this.files.splice(idx,1)
            this.checktemp.splice(idx,1)
		},
		createProblems(){
            if(this.homeworkname ==null){
                alert("제목을 입력하세요.")
                return;
            }
            if(this.homeworkcontent ==null){
                alert("제목을 입력하세요.")
                return;
            }
            if(this.sessionindex == 0){
                alert("세션을 선택하세요.")
                return;
            }
            if(this.enddate == null){
                alert("제출 기한을 선택하세요.")
                return ;
            }
            let data = {
                homeworkName : this.homeworkname,
                homeworkType : "word",
                homeworkContent : this.homeworkcontent,
                sessionNo : this.sessionindex,
                dueDate : this.enddate,
                problems : this.problems,
                wordImgData : this.files,
                checkData : this.checktemp
            }
			axios.post(`/wordtest/makehomework`,data)
			.then(() => {
                this.OK = true;
			})
			.catch(()=>{
				alert('error')
			})
        },
        addWordSet(){
            if(this.basewordindex < this.wordindex){
                axios.get('wordtest/wordcontent/'+ (this.wordindex - this.basewordindex))
                .then((Response)=>{
                    for(let i=0;i<Response.data.length;i++){
                        let temp = {
                            wordSentence : Response.data[i].problemName,
                            wordimg : Response.data[i].problemImg
                        }
                        this.problems.push(temp)
                        this.files.push(null)
                        this.checktemp.push(0)
                    }
                })
            }else{
                axios.get('wordtest/categorywordtest/'+this.wordindex)
                .then((Response)=>{
                    for(let i=0;i<Response.data.length;i++){
                        let temp = {
                            wordSentence : Response.data[i].word,
                            wordimg : Response.data[i].wordImg
                        }
                        this.problems.push(temp)
                        this.files.push(null)
                        this.checktemp.push(0)
                    }
                })
            }
        },
        get_classsession(){
            axios.get(`classsession/selectclasssession/`+this.$route.params.classno)
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
        onFileChange(e) {
            //이미지 첨부
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length) return;
            this.createImage(files[0],this.index);
        },
        createImage(file,index) {
            index
            let reader = new FileReader();
            let vm = this;
            reader.onload = (e) => {
                vm.classimg = e.target.result;
                this.files.splice(this.index,1,e.target.result);
                this.checktemp.splice(this.index,1,1);
            };
            reader.readAsDataURL(file);
        },
        removeImage: function() {
        this.classimg = "";
        this.fileName = "";
        },
    },
    computed : {
        url() {
			return this.$store.getters.BACKEND_URL
		},
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