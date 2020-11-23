<template>
  <div>
		<div>
			<ul style="text-align:left">
				<li>과제분류</li>
				<div class="btn">
					<li><v-btn  @click="get_word" :color="wordTestSelected ? 'primary' : 'secondary'">낱말연습</v-btn></li>
					<li><v-btn  @click="get_dictation" :color="dictationSelected ? 'primary' : 'secondary'">받아쓰기</v-btn></li>
				</div>
			</ul>
		</div>
				<router-view></router-view>
		
  </div>
</template>

<script>
export default {
	name: 'classhwcreate',
	data(){
		return {
			wordTestSelected: false,
			dictationSelected: false
		}
	},
	methods : {
		get_dictation(){
			this.$router.push('/calldictation/' + this.$route.params.classno)
			this.wordTestSelected = false
			this.dictationSelected = true
		},
		get_word(){
			this.$router.push('/callword/' + this.$route.params.classno)
			this.wordTestSelected = true
			this.dictationSelected = false
		},
	},
	mounted(){
		this.get_word();
	}
}
</script>

<style scoped>
.btn{
	margin: 10px;
}
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
</style>
