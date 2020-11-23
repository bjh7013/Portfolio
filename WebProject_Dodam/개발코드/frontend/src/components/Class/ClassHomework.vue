<template>
  <div>
		<v-data-table
			:headers="headers"
			:items="homeworkList"
			@click:row="show" item-key="name"
			class="elevation-1"		
		></v-data-table>
		<modal name="modal-homework" :width="modalWidth+'px'" :height="modalHeight+'px'">
			<h2>{{selectedHomework.homeworkName}}-과제 제출 현황</h2>
			<hr>
			<div class="modal-content">
				<!-- <h3 style="margin-left:10px;text-align:left">받아쓰기</h3> -->
				<div style="margin-left:10px;text-align:left" v-for="(dictation,i) in showSelectedHomework.dictationList" :key="i">
						<v-expansion-panels popout>
							<v-expansion-panel>
								<v-expansion-panel-header>
									<p>문제번호:{{dictation.dictationNo}}</p>
									<p>문제:{{dictation.dictationSentence}}</p>
								</v-expansion-panel-header>
								<template  v-for="(dictation_submit, index) in dictation.dictationSubmitList">
									<v-expansion-panel-content :key="index">
										<!-- 정답여부:{{isCorrect(dictation_submit.correct)}} <i @click="modifyDictation(dictation_submit)" class="fas fa-edit"></i> -->
										<!-- <br> -->
										<v-row>
											<v-col cols="4">
												제출번호:{{dictation_submit.submitNo}}
											</v-col>
										
											<v-col cols="4">
												정답여부:{{isCorrect(dictation_submit.correct)}}  <i @click="modifyDictation(i,index,dictation)" class="fas fa-edit"></i>
											</v-col>
										</v-row>
										<img style="width:80%"  class="ocrimgs" :src="$store.getters.BACKEND_URL+dictation_submit.submitImg"/>
										<hr>
									</v-expansion-panel-content>
								</template>
							</v-expansion-panel>
						</v-expansion-panels>
				</div>
				<div style="margin-left:10px;text-align:left" v-for="(word,i) in showSelectedHomework.wordList" :key="i">
						<v-expansion-panels popout>
							<v-expansion-panel>
								<v-expansion-panel-header>
									<p>문제번호:{{word.testNo}}</p>
									<p>문제:{{word.word}}</p>
								</v-expansion-panel-header>
								<template  v-for="(word_submit, index) in word.wordSubmitList">
									<v-expansion-panel-content :key="index">
										<v-row>
											<v-col cols="4">
												제출번호:{{word_submit.submitNo}}
											</v-col>
											<v-col cols="4">
												제출정답:{{word_submit.submitAnswer}}
											</v-col>
											<v-col cols="4">
												정답여부:{{isCorrect(word_submit.correct)}} <i @click="modifyWord(i,index,word)" class="fas fa-edit"></i>
											</v-col>	
										</v-row>

										<br>
										<img style="width:80%"  class="ocrimgs" :src="$store.getters.BACKEND_URL+word_submit.submitImg"/>
										<hr>
									</v-expansion-panel-content>
								</template>
							</v-expansion-panel>
						</v-expansion-panels>
				</div>
			</div>		
			<!-- <div v-for></div> -->
		</modal>

		<modal name="modal-submit-homework" height="1000px" width="1000px">
			<h2>나의 과제 보기</h2>
			<div>
				<v-card>
					<v-card-text>
						<v-container style="text-align:left;"> 
							<p>과제명:{{selectedHomework.homeworkName}}</p>
							<p>마감일:{{selectedHomework.dueDate}}</p>
							<p>과제유형:{{homeworkType(selectedHomework.homeworkType)}}</p>						
						</v-container>
					</v-card-text>
						<button v-if="selectedHomework.homeworkType == 'word'" color="primary" @click="solveWord(selectedHomework.homeworkNo)">문제 풀기</button>				
						<button v-if="selectedHomework.homeworkType == 'dictation'" @click="solveDictation(selectedHomework.homeworkNo)">문제 풀기</button>				
				</v-card>
			</div>
			<div style="overflow-y:auto; height:760px;">
				<h3 style="margin-left:10px;text-align:left">나의 제출 현황</h3>
				<div style="margin-left:10px;text-align:left" v-for="(dictation,i) in showSelectedHomework.dictationList" :key="i">
						<div v-for="(dictation_submit,j) in dictation.dictationSubmitList" :key="j">
							<div v-if="userInfo.userNo==dictation_submit.studentNo">
								<p>문제번호:{{dictation.dictationNo}}</p>
								<p>문제:{{dictation.dictationSentence}}</p>
								<v-row>
									<v-col cols="4">
										제출번호:{{dictation_submit.submitNo}}
									</v-col>

									<v-col cols="4">
										정답여부:{{isCorrect(dictation_submit.correct)}} 
									</v-col>
								</v-row>
								<img style="width:900px;border: 1px solid gray;"  class="ocrimgs" :src="$store.getters.BACKEND_URL+dictation_submit.submitImg"/>
								<hr>
							</div>
							
						</div>
							
				</div>
				<div style="margin-left:10px;text-align:left" v-for="(word,i) in showSelectedHomework.wordList" :key="i">
						<div v-for="(word_submit,j) in word.wordSubmitList" :key="j">
							<div v-if="userInfo.userNo==word_submit.studentNo">
								<v-row>
									<v-col cols="4">
										제출번호:{{word_submit.submitNo}}
									</v-col>
									<v-col cols="4">
										제출정답:{{word_submit.submitAnswer}}
									</v-col>
									<v-col cols="4">
										정답여부:{{isCorrect(word_submit.correct)}} 
									</v-col>	
								</v-row>

								<br>
								<img style="width:400px; 1px solid gray;" class="ocrimgs" :src="$store.getters.BACKEND_URL+word_submit.submitImg"/>
								<hr>
							</div>
						</div>
				</div>
			</div>			
		</modal>
  </div>
</template>

<script>
// import DictationMake from '@/components/Class/DictationMake.vue'
// import WordMake from '@/components/Class/WordMake.vue'
import Axios from 'axios';

export default {
	
	name: 'classhomework',
	components : {

	},
	created(){
		this.getHomework();
		this.modalHeight = window.innerHeight * 0.7
		this.modalWidth = window.innerWidth * 0.6
	},
	data() {
		return {
			modalHeight: 0,
			modalWidth: 0,
			headers: [
				{
					text: '번호',
					align: 'start',
					value: 'homeworkNo',
				},
				{ text: '과제명', value: 'homeworkName' },
				{ text: '과제내용', value: 'homeworkContent' },
				{ text: '마감날짜', value: 'dueDate' },
				// { text: '과제현황', value: 'hw_now' },
			],
			desserts: [
				{
					hw_num: 1,
					hw_name: '오늘의 과제 1',
					startday: '11/07',
					submitday: '11/11',
					hw_now: '제출완료',
				},
				{
					hw_num: 2,
					hw_name: '오늘의 과제 2',
					startday: '11/07',
					submitday: '11/11',
					hw_now: '과제하기',
				},
			],
			homeworkList:[

			],
			dictationList:[],
			selectedHomework:{},
		}
	},
	methods:{
		show:function (item, row) {      
			row.select(true);
			if(this.$store.getters.userInfo.userType==='teacher'){
				this.$modal.show("modal-homework");
				this.selectedHomework=item;
			}else{
				this.$modal.show("modal-submit-homework")
				this.selectedHomework=item;
			}
		
		},getHomework(){
			var url=location.pathname.split("/")[2];
			Axios.get("/homework/select/"+url).then((response)=>{
				this.homeworkList=response.data;
				for(var i=0;i<this.homeworkList.length;i++){
					this.getDictation(this.homeworkList[i].homeworkNo,i);
					this.getWord(this.homeworkList[i].homeworkNo,i);
				}
				
			}).catch((err)=>{
				err
			})
		},getWord(homeworkNo,index){
			Axios.get("/wordtest/homeworkwordtest/"+homeworkNo).then((response)=>
			{
				this.homeworkList[index].wordList=response.data;
				for(var i=0;i<this.homeworkList[index].wordList.length;i++){
					this.getWordSubmit(index,i,this.homeworkList[index].wordList[i].testNo);
				}				
			}).catch((err)=>{err});
		},getWordSubmit(index,i,testNo){
			Axios.get("/wordtestsubmit/wordtestsubmittestno/"+testNo).then((response)=>
			{	

				this.homeworkList[index].wordList[i].wordSubmitList=response.data;
			}).catch((err)=>{err});
		},
		getDictation(homeworkNo,index){
			Axios.get("/dictation/dictationhomework/"+homeworkNo).then((response)=>
			{
				this.homeworkList[index].dictationList=response.data;
				for(var i=0;i<this.homeworkList[index].dictationList.length;i++){
					this.getDictationSubmit(index,i,this.homeworkList[index].dictationList[i].dictationNo);
				}				
			}).catch((err)=>{err});
		},
		getDictationSubmit(index,i,dictationNo){
			Axios.get("/dictationsubmit/dictationSubmitdictationNo/"+dictationNo).then((response)=>
			{	
				this.homeworkList[index].dictationList[i].dictationSubmitList=response.data;
			}).catch((err)=>{err});
		},solveDictation(homeworkNo){
			this.$router.push(`/dictationhomework/${homeworkNo}`);
		},solveWord(homeworkNo){
			this.$router.push(`/wordtesthomework/${homeworkNo}`);
		},
		
		homeworkType(type){
			if(type=="dictation"){
				return "받아쓰기";
			}else{
				return "글자공부";
			}
		},isCorrect(bool){
			if(bool){
				return "정답";
			}else{
				return "오답";
			}
		},modifyDictation(i,index,dictation_submit){
			for(var j=0;j<this.homeworkList.length;j++){
				if(this.homeworkList[j]===this.selectedHomework){
					this.$set(this.homeworkList[j].dictationList[i].dictationSubmitList[index],"correct",!this.homeworkList[j].dictationList[i].dictationSubmitList[index].correct);
					this.$set(this.selectedHomework.dictationList[i].dictationSubmitList[index],"correct",!this.selectedHomework.dictationList[i].dictationSubmitList[index].correct);
					var tmp=this.selectedHomework;
					this.selectedHomework={};
					tmp.dictationList[i].dictationSubmitList[index].correct=!tmp.dictationList[i].dictationSubmitList[index].correct;
					this.selectedHomework=tmp;
					Axios.put("/dictationsubmit/dictationsubmit",this.homeworkList[j].dictationList[i].dictationSubmitList[index]).then((response)=>{
						response
						alert("변경 완료");
					}).catch((err)=>{err});

					break;
				}
			}
			dictation_submit.correct=!dictation_submit.correct;
		},modifyWord(i,index,word_submit){
			word_submit.correct=!word_submit.correct;

			for(var j=0;j<this.homeworkList.length;j++){
					if(this.homeworkList[j]===this.selectedHomework){
						this.$set(this.homeworkList[j].wordList[i].wordSubmitList[index],"correct",!this.homeworkList[j].wordList[i].wordSubmitList[index].correct);
						this.$set(this.selectedHomework.wordList[i].wordSubmitList[index],"correct",!this.selectedHomework.wordList[i].wordSubmitList[index].correct);

						var tmp=this.selectedHomework;
						this.selectedHomework={};
						tmp.wordList[i].wordSubmitList[index].correct=!tmp.wordList[i].wordSubmitList[index].correct;
						this.selectedHomework=tmp;
						
						Axios.put("/wordtestsubmit/wordtestsubmit",this.homeworkList[j].wordList[i].wordSubmitList[index]).then((response)=>{
							response
							alert("변경 완료");

						}).catch((err)=>{err});

						break;
					}
			}
		
		}
	},computed:{
		showSelectedHomework: function() {
			return this.selectedHomework;
		},
		showHomeworkList:function(){
			return this.homeworkList;
		},userInfo:function(){
			return this.$store.getters.userInfo;
		}
	}
}
</script>

<style>
 .modal-content{
	width: 100%;
	height: 80%;
	overflow:auto;
} 
</style>