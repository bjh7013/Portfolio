<template>
  <div>
    <h1 class="products">단어 그리기</h1>
    <v-container class="my-5">
      <v-layout row wrap>
          <div class="cards">
          <div>
              {{index+1}}/{{totalword}}
          </div>
            <v-card flat class="ma-1 text-xs-center">
                
                <div class="ctitle">
                    <div class="cti">
                        <v-card-title primary-title>
                        <div class="center">    
                            <h3 class="cfont headline pink--text text--accent-2 text-truncate"> 
                                {{nowshow.word}}
                                <!-- <button id="btn-read" @click="clickread">읽기</button> -->
                                <!-- <img src="../assets/read.png" class="speakimg" @click="clickread" /> -->
                            </h3>
                            
                        </div>
                        
                        </v-card-title>
                    </div>
                        <div>
                            <label for="sig-canvas">그리기 : </label>
                            <canvas id="sig-canvas" width="640px" height="480px"  @mousemove="draw" @mousedown="beginDrawing" @mouseup="stopDrawing">
                            <!-- Get a better browser, bro. -->
                            </canvas>
                        </div>
                </div>
            </v-card>
            <button class="colorbox" style="background-color:red;" @click="drawingcolor = 'red'"></button>
            <button class="colorbox" style="background-color:orange;" @click="drawingcolor = 'orange'"></button>
            <button class="colorbox" style="background-color:pink;" @click="drawingcolor = 'pink'"></button>
            <button class="colorbox" style="background-color:yellow;" @click="drawingcolor = 'yellow'"></button>

            <button class="colorbox" style="background-color:greenyellow;" @click="drawingcolor = 'greenyellow'"></button>
            <button class="colorbox" style="background-color:green;" @click="drawingcolor = 'green'"></button>
            <button class="colorbox" style="background-color:aqua;" @click="drawingcolor = 'aqua'"></button>
            <button class="colorbox" style="background-color:blue;" @click="drawingcolor = 'blue'"></button>

            <button class="colorbox" style="background-color:navy;" @click="drawingcolor = 'navy'"></button>            
            <button class="colorbox" style="background-color:blueviolet;" @click="drawingcolor = 'blueviolet'"></button>
            <button class="colorbox" style="background-color:brown;" @click="drawingcolor = 'brown'"></button>
            <button class="colorbox" style="background-color:black;" @click="drawingcolor = 'black'"></button>
          
          
        </div>
          
    </v-layout>
    <label for="drawing-line-width">Line width:</label>
    <!-- <span class="info">30</span> -->
    <input type="range" value="1" min="1" max="50" id="drawing-line-width" v-model="drawinglinewidth"><br>
        
    <template v-if="index < totalword-1">
        <b-button @click="nextword">
            다음
        </b-button>
    </template>
    <template v-if="index >= totalword-1">
        <b-button @click="nextword">
            완료
        </b-button>
    </template>
  </v-container>
  <img id="ansimgid" :src="ansimg">
  </div>

</template>

<script>
import axios from "axios";

export default {
    name: 'WordTest',
    props:{
        kateno:{
            type:Number,
            default:0
        }
    },
    data() {
        return {
            //vue 생성할때 한번 받음
            homeworkno : this.kateno,
            // 캠버스 셋팅
            x:0,
            y:0,
            canvas: null,
            isDrawing: false,
            drawingcolor: 'black',
            drawinglinewidth: 1,
            anstext: "",
            ansimg:"",
            // 테스트 워드 수
            totalword:4,
            
            nowshow:[],
            
            // ====post 보낼 인자
            //word_test_no 는 testno
            studentno:1,
            // submit_answer:"",
            // submit_img:"",
            // ============= 
            index:0,
            show: true,
            wordlist:[],
            ttsList: [
                {
                    testno: 5,
                    wordimg: "http://cdn.kormedi.com/wp-content/uploads/2019/08/gettyimages-923832604_580.jpg",
                    word: "사과",
                    homeworkno:0,
                },
                {
                    testno: 6,
                    wordimg: "https://cdn.vuetifyjs.com/images/cards/desert.jpg",
                    word: "배",
                    homeworkno:0,
                },
                {
                    testno: 7,
                    wordimg: "http://mimg.segye.com/content/image/2017/08/30/20170830515039.jpg",
                    word: "포도",
                    homeworkno:0,
                },
                {
                    testno: 4,
                    wordimg: "https://i2.wp.com/sharehows.com/wp-content/uploads/2014/10/%EA%B0%80%EC%9D%84%EC%9D%84-%EB%8C%80%ED%91%9C%ED%95%98%EB%8A%94-%EA%B0%90-4.jpg?resize=800%2C533",
                    word: "감",
                    homeworkno:0,
                },
            ]
        };
    },
    watch:{
        drawinglinewidth: function(){
            this.setlinewidth(this.drawinglinewidth);
        },
    },
    mounted(){
        var c = document.getElementById("sig-canvas");
        this.canvas = c.getContext('2d');
        
    },
    methods:{
        setlinewidth: function(text){
            this.drawinglinewidth = parseInt(text, 10) || 1;
        },
        getWord(){
            axios.get('/wordtest/homeworkwordtest?homeworkNo='+this.homeworkno)
            .then(res=>{
                this.wordlist = res.data;
                this.totalword = this.wordlist.length;
                this.nowshow = this.wordlist[0];
            })
            .catch(err =>{
                err
            })
        },
        nextword(){
            this.index = this.index+1;
            var c = document.getElementById("sig-canvas");
            this.ansimg = c.toDataURL();

            var data = {
                'wordTestNo' : this.nowshow.testNo,
                'studentNo' : this.studentno,
                'submitAnswer' : this.anstext,
                'submitImg' : this.ansimg,
            }

            var config={
                header:{
                    'Content-Type' : 'application/json',
                }
            };

            axios.post('http://localhost:9999/wordtestsubmit/wordtestsubmit',data,config).then(
                res =>{
                    res
                    this.$store.state.postans =1
                    let configimg ={
                        header : {
                            'Content-Type' : 'multipart/form-data',
                            }
                        };
                        let form = new FormData();
                        form.append("file",this.ansimg);
                        form.append("testNo",this.studentno);
                        axios.put('http://localhost:9999/wordtestsubmit/imgword',form,configimg) //이미지업데이트
                        .then((data) =>{
                            data
                            this.intervalid = setInterval(()=>{
                            this.$store.state.loadingflag = false;
                            location.reload()
                        }, 1000);})
                        .catch((error)=>{
                            error
                        })
                })
                .catch(err => {
                    err
                })

            c.getContext("2d").clearRect(0,0,c.width,c.height);
            this.nowshow = this.wordlist[this.index];
            this.anstext = "";
        },
        makecanvas(){
            this.canvas = null;
        },
        changecolorred(){
            this.drawingcolor = 'red';
        },
        drawLine(x1, y1, x2, y2) {
            let ctx = this.canvas;
            ctx.beginPath();
            ctx.strokeStyle = this.drawingcolor;  //선 색
            ctx.lineWidth = this.drawinglinewidth;          //선 굵기
            ctx.moveTo(x1, y1);
            ctx.lineTo(x2, y2);
            ctx.stroke();
            ctx.closePath();
        },
        draw(e) {
            if(this.isDrawing) {
                this.drawLine(this.x, this.y, e.offsetX, e.offsetY);
                this.x = e.offsetX;
                this.y = e.offsetY;
            }
        },
        beginDrawing(e) {
            this.x = e.offsetX;
            this.y = e.offsetY;
            this.isDrawing = true;
        },
        stopDrawing(e) {
            if (this.isDrawing) {
                this.drawLine(this.x, this.y, e.offsetX, e.offsetY);
                this.x = 0;
                this.y = 0;
                this.isDrawing = false;
            }
        },

        clickread(){
            if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
                alert("이 브라우저는 음성 합성을 지원하지 않습니다.")
                return
            }
            
            window.speechSynthesis.cancel() // 현재 읽고있다면 초기화

            const speechMsg = new SpeechSynthesisUtterance()
            speechMsg.rate =  1; // 속도: 0.1 ~ 10      
            speechMsg.pitch = 1; // 음높이: 0 ~ 2
            speechMsg.lang = "ko-KR";
            speechMsg.text = this.nowshow.word;
            
            // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행
            window.speechSynthesis.speak(speechMsg)
        },
    },
    created(){
        this.getWord();
    },
    // watch: {
    //   userlimit: function() {
    //   this.userFail(this.userlimit);
    //   },
    // },
    components: {
        // TtsTest
        }
    }
</script>


<style scoped>
/* .v-enter-active, .v-leave-active { */
  /* transition: opacity 1s; */
/* } */
.colorbox{
    width:20px;
    height: 20px;
    padding: 5px;
    margin: 5px;
    border:saddlebrown solid 1px;
}
.v-leave-active {
  position: absolute;
}
.v-enter, .v-leave-to {
  opacity: 0;
}
#sig-canvas {
  border: 1px solid grey;
}
#classcreatemodal *{
  margin: 0;
}
#classcreatemodal{
  margin: 0 10px;
}
#classcreatemodal>div{
  padding: 6px 0;
}
#teenager,#userlimit,#classname{
  width:190px;
  margin: 6px 0;
  display: inline-block;
}
/* #userlimit{
  width: 190px;
} */
.speakimg{
    width: 30px;
    display: inline-block;
}
.floatleft{
  float:left;
  padding: 0 0 0 10px;
  line-height: 50px;
}
.classcreatemodaldiv2,
.classcreatemodaldiv4{
  display: flex;
  line-height: 50px;
}
#starttime,#endtime{
  border:solid 1px;
  border-color:rgb(200, 200, 200);
  border-radius: 5px;
}
#classname{
  width: 270px;
}
#classdesc{
  resize: none;
  height: 90px;
}

#photo{
  display: none;
}
.haveimg,
.img_upload,
.noimg,
.photo{
  height: 200px;
  width: 200px;
  margin:0px auto;
  line-height: 200px;
}
.noimg{
  border-style:dashed;
  background:violet;
}
.cards{
  padding: 1% 1%;
  border-radius: 50%;
}

.cimg{
    border-radius: 10%;
}
.cfont{
    font-size:1vh;
}

.cti{
    
    border-radius: 15%;
    background-color: rgb(196, 189, 189);
    height: 5vh;
}
</style>
