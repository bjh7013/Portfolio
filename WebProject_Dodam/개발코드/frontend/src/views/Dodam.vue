<template>
<div style="background-color:white;">
	<v-btn rounded color="warning" @click="gologin" width="7vw" style="z-index: 1;margin:1vh 1vw; position: fixed; right: 0;bottom:0;">돌아가기</v-btn>
		
	<div class="section">
		<h1>도담도담은?</h1>
		<div style="height: 100%;line-height: 100vh;">
		<iframe src="https://www.youtube.com/embed/JxidKAf0Oc8" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
	</div>
	<div class="section">
		<div class="ment2">
		<h1 style="margin:0;">스스로 학습할 수 있는 공간을 제공합니다.</h1>
		</div>
		<v-container>
			<v-row>
				<v-col>
					<div class="alpha_card1" elevation="0">
						<div style="display:flex; justify-content: space-between;">
								<div class="cont-img">
									<img src="@/assets/images/alphabet2.jpg" aspect-ratio="1" class="mimg"/>
								</div>
							<v-col class="contentsinfo" style="height:25vh;">
								<br><h2>우리 아이 한글 공부의 시작</h2>
								<h4>'ㄱ,ㄴ,ㄷ,ㄹ-'부터 '-카,타,파,하'까지</h4>
								<h4>획순에 맞춰 쓰는 한글 공부</h4>
							</v-col>
							<v-col cols="3" class="hideimg" style="height:25vh;width:25vh;">
								<v-img src="@/assets/images/do1.png" class="doimg1"></v-img>
							</v-col>
						</div>
					</div>
					<v-card class="alpha_card2" elevation="0">
						<v-row>
							<v-col cols="3" class="hideimg" style="height:25vh;width:25vh;">
								<v-img src="@/assets/images/do2.png" class="doimg2"></v-img>
							</v-col>
							<v-col class="contentsinfo" style="height:25vh;">
								<br><h2>어휘력 쑥쑥</h2>
								<h4>카테고리별 사진과 음성으로</h4>
								<h4>보고 들으며 즐겁게 단어 공부</h4>
							</v-col>
								<v-card class="cont-img">
									<v-img src="@/assets/images/word2.jpg" aspect-ratio="1" class="mimg"/>
								</v-card>
						</v-row>
					</v-card>
					<v-card class="alpha_card3" elevation="0">
						<v-row>
							<v-card class="cont-img">
								<v-img src="@/assets/images/dictation.jpg" aspect-ratio="1" class="mimg"/>
							</v-card>
							<v-col class="contentsinfo" style="height:25vh;">
								<br><h2>맞춤법까지 완성</h2>
								<h4>총 40단계의 난이도로</h4>
								<h4>우리 아이 한글공부 끝</h4>
							</v-col>
							<v-col cols="4" class="hideimg" style="height:25vh">
								<v-img src="@/assets/images/do3.jpg" style="height:20vh; margin:4% -10%" class="doimg3"></v-img>
							</v-col>
						</v-row>
					</v-card>
				</v-col>
			</v-row>
		</v-container>
	</div>
	<div class="section">
		<div>
			<h1 style="margin:1% auto 0;">우리 아이의 학습현황을 볼 수 있습니다.</h1>				
			<div style="margin:2% 10%">
				<v-img src="@/assets/images/mychildren.jpg">
				</v-img>
			</div>
		</div>
	</div>
	<div class="section">
		<v-card elevation="0">
			<h1>선생님들은 교실을 운영할 수 있습니다.</h1>
			<div style="display:flex;">
				
				<img src="@/assets/images/forteacher.jpg" class="lastimg"/>
				<div class="text4">
					<h2 style="background-color:#2EC4B6;">교육자라면 누구나</h2>
					<h4>손쉽게 만드는 과제,</h4>
					<h4>선생님들끼리의 과제 공유,</h4>
					<h4>나만의 교실을 운영해 보세요.</h4>
				</div>
			</div>
		</v-card>
	</div>
</div>
</template>

<script>
 import $ from "jquery";

export default {
	data(){
		return {
			pageheight: 0,
			page:0,
		}
	},
	create(){

	},
	mounted(){
		window.addEventListener('resize',() => {
			var pageheight = $(window).height();
			for(var i = 0; i<5; i++){
				var sectionsize = document.getElementsByClassName('section')[i];
				sectionsize.style.width = $(window).width()+'px';
				sectionsize.style.height = $(window).height()+'px';
			}
			
			
			$('.section').each(function(index){
				$(this).attr("data-index",pageheight * index);
			});
            
		});
		$('.section').on("mousewheel",function(e){
			var pageheight = $(window).height();
			var sectionPos = parseInt($(this).attr("data-index"));
			$('.section').each(function(index){
				$(this).attr("data-index",pageheight * index);
			});
			if(e.originalEvent.wheelDelta >= 0){
				$("html,body").stop().animate({scrollTop:sectionPos - pageheight});
				return false;
			}
			else if(e.originalEvent.wheelDelta<0){
				$("html,body").stop().animate({scrollTop:sectionPos + pageheight});
				return false;
			}
		});
		this.page=0;
	},
	methods: {
		gologin() {
			this.$router.push('/')
		},
		imghide(){
			for(var i=0;i<3;i++){
				document.getElementsByClassName('hideimg')[i].style
				.display= ( $(window).height()> $(window).width())?"none":"block";
			}
		}
	}
}
</script>

<style scoped>
.head-gap{
	height: 40px;
}
.section{
	height: 100vh;
}

iframe{
	width: 100%;
	max-width: 142vh;
	height: 56vw;
	max-height: 80vh;
	margin:auto;
}
.lastimg{
    margin: 0 10vw;
    width: 80vw;
    height: 80vh;
}

.text4 {
	width: 40%;
	height: 35%;
	background-color:#EFFFE9;
	padding: 5% 3% 4% 3%; 
    margin: 50vh 0 0 50vw;
	z-index: 1;
    position: absolute;
}
.alpha_card1 {
	margin: 1%;
	border-radius: 30px;
	background-color: #FDD692;
}

.alpha_card2 {
	margin: 1%;
	border-radius: 30px;
	background-color: #d3e0f7;
}

.alpha_card3 {
	margin: 1%;
	border-radius: 30px;
	background-color: #FADAD8;
}

.cont-img {
	height:25vh; 
	width:25vh;
	border-radius: 30px;
	border: 5px solid #FFA680;
}

.mimg {
	height: 100%;
	width: 100%;
	border-radius: 30px;
}

.doimg1 {
	margin: 0 -10%;
	height: 98%;
	width: 100%;
}
.doimg2 {
	margin: 0 10%;
	height: 100%;
	width: 100%;
}
.doimg3 {
	margin: 0 -10%;
	height: 98%;
	width: 100%;
}
.contentsinfo{
	padding: 1vh 1vw;
}
@media only screen and (max-width: 798px) {
	.hideimg{
		display: none;
	}
}
@media only screen and (max-width: 600px) {
	.hideimg{
		display: none;
	}
	.lastimg{
		height: 40vh;
	}
	.cont-img{
		display: none;
	}
.text4 {
    width: 80vw;
    height: 40vh;
    margin: 40vh 10vw 0 10vw;
	z-index: 0;
}
.ment2{
	height: 20vh;
}
.contentsinfo>*{
	font-size:22px;
}
}

</style>