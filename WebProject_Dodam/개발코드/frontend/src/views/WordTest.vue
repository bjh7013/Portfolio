<template>
  <div class="contain">
      <div class="wordheader">
        <section style="margin-left:10%">
          <div v-if="!classCheck" @click="gowordcategory" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>낱말연습</div>
          <div v-if="classCheck" @click="goclass"  class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>클래스</div>
        </section>
        <div style="margin-right:10%;">{{this.$route.params.categoryName}}</div>
        <!-- <h1 class="nomobile">{{this.$route.params.categoryName}}</h1> -->
      </div>
      <div class="maincontent">
        <!-- <div>
          {{index+1}}/{{totalword}}
        </div> -->
        <div class="goallist" style="margin-left:12%;margin-right:10%">
          <div v-for="(x) in wordList.length" :key="x">
            <template v-if="index == x-1">
              <img src="../assets/images/flag.png" class="goalimg" style="height:3vw; width:3vw"/>
              <img src="../assets/images/tur.png" class="goalimg"/>
            </template>
            <template v-if="index != x-1 && x != wordList.length">
              <img src="../assets/images/flag.png" class="goalimg" style="height:3vw; width:3vw"/>
              <img src="../assets/images/backgroundnull.png" class="goalimg" />
            </template>
            <template v-if="index!= wordList.length-1 && x == wordList.length">
              <img src="../assets/images/flag.png" class="goalimg" />
            </template>
          </div>
        </div>

        <div class="center">    
        </div>
        
        <div class="conmain">
          <div v-if="nowshow.wordImg">
            <img :src="$store.getters.BACKEND_URL + nowshow.wordImg" class="cimg" @click="clickread"/>
            <div @click="clickread" style="display:flex; justify-content: space-between;margin:2vh">
              <div @click="showhint()" class="cfont ismobile">
                정답<p class="nomobile">확인</p>
              </div>
              <div class="cfont" id="hint" style="opacity:0;"> 
                {{nowshow.word}}
              </div>
              
              <i v-if="korAudioLoaded" class="fas fa-play-circle fa-2x" style="color:#baeb34;"/>
              <loading-icon v-else></loading-icon>
            </div>
          </div>
          <div class="noimg"> 
            <div>
              <button id="clear-ans" @click="clearCanvas" class="btn btn-info"><i class="fas fa-eraser"></i></button>
          
              <div class="inputdiv radiusboard">
                <div id="keyboard">
                  <label>따라치기 : </label>
                  <b-form-input v-model="anstext" type="text" maxlength="7"/>
                  <div class="rewrite">
                  {{anstext}}
                  </div>
                </div>
                <div id="penandmouse">
                  <canvas ref="c" id="c" style="border: 1px solid rgb(170, 170, 170);  touch-action: none; user-select: none;" class="lower-canvas"></canvas>
                  <div class="cfont" style="margin:2vh;">{{this.result}}</div>
                </div>
              </div>
            
            </div>
            <div class="btn-wrapper">
              <div class="clickbtns" >
                <v-btn @click="keyboardInput" id="iconkeyboard" class="clickbtn"><i class="fas fa-keyboard"></i><p class="nomobile">키보드 </p></v-btn>
                <v-btn @click="touchInput" id="iconpenandmouse" class="clickbtn"><i class="fas fa-pencil-alt"></i><p class="nomobile">펜/<i class="mdi mdi-mouse"></i>마우스</p></v-btn>
                <template v-if="index < totalword-1">
                    <b-button class="clicknextbtn" @click="nextword">
                        다음
                    </b-button>
                </template>
                <template v-if="index >= totalword-1">
                    <b-button class="clicknextbtn" @click="finish">
                        완료
                    </b-button>
                </template>
              </div>
            </div>
          </div>

        </div>
      </div>
    <img id="ansimgid" :src="ansimg">
  </div>
</template>

<script>
import axios from "axios";
import {fabric} from 'fabric';
import LoadingIcon from '@/components/LoadingIcon.vue'

export default {
    name: 'WordTest',
    components:{
        LoadingIcon:LoadingIcon
    },
    data() {
        return {
          API_ENDPOINT : 'https://inputtools.google.com/request?ime=handwriting&app=autodraw&dbg=1&cs=1&oe=UTF-8',
          wordList: [],
          categoryNo:null,
          categoryName:null,
          homeworkNo:null,
          homeworkName:null,
          korAudioLoaded: true,
          correct: false,
          canvas: null,
          url: "http://k3d206.p.ssafy.io",
          anstext: "",
          ansimg:"",
          // 테스트 워드 수
          totalword:4,
          nowshow:[],
          // ====post 보낼 인자
          // studentno:1,
          result:"",
          stencils: [],
          suggestions:[],
          preX: 0,
          preY: 0,
          curX:  0,
          curY : 0,
          pressedAt: 0,
          isPressing: false,
          maxX: 100000,
          minX: 0,
          maxY: 100000,
          minY: 0,
          shapes: [],
          curShape:[],
          inputtype:0,
          index:0,
          show: true,      
          classCheck:false,

        };
    },
    created(){
      // 배포시
        this.getWord();
        
    },
    mounted(){
        if(document.readyState == 'complete'){
            prepareCanvas();
            this.canvas = window.__canvas;
            this.setEventHandlers();
            this.touchInput();
            this.createCanvas();
        }else{
            window.onload = () => {
                prepareCanvas();
                this.canvas = window.__canvas
                this.setEventHandlers();
                this.touchInput();
                this.createCanvas();
            } 
        }
        
        function prepareCanvas() {
            var canvas  = window.__canvas = new fabric.Canvas('c', {
                isDrawingMode: true
            });
            if(window.innerWidth>480){
              canvas.setWidth(window.innerWidth*3.5/10);
              canvas.setHeight(window.innerWidth*3.5/10);
            } else {
              canvas.setWidth(window.innerWidth*7/10);
              canvas.setHeight(window.innerWidth*7/10);

            }
            fabric.Object.prototype.transparentCorners = false;

            var canvases = window.__canvases || window.canvases;

            canvas && canvas.calcOffset && canvas.calcOffset();

            if (canvases && canvases.length) {
                for (var i = 0, len = canvases.length; i < len; i++) {
                    canvases[i].calcOffset();
                }
            }
        }
    },
    methods:{ 
      getWord(){
        let requestURL;
        if(this.$route.params.categoryNo){
          this.categoryNo = this.$route.params.categoryNo
          requestURL = '/wordtest/categorywordtest/' + this.categoryNo
        }else{
          //homework를 타고 온 경우
          this.classCheck=true;
          this.homeworkNo = this.$route.params.homeworkNo
          requestURL = '/wordtest/homeworkwordtest/' + this.homeworkNo
        }
        axios.get(requestURL)
        .then(res=>{
          this.wordList = res.data;
          this.totalword = this.wordList.length;
          this.nowshow = this.wordList[0];
          
          for(let word of this.wordList){
            axios.post(`/tts`,{
                language : 'ko-KR',
                text : word.word
            })
            .then(({data}) => {
                word.audioPath = data
            })
          }
        })
        .catch(err =>{
          err
        })
      },goclass() {
      this.$router.push('/class')
      },
      showhint(){
        document.getElementById("hint").style.opacity = '1';
      },
      gowordcategory() {
        this.$router.push('/wordcategory')
      },
      getNoni(){
        axios.post(this.API_ENDPOINT, {
          input_type : 0,
          requests: [{
            ink:  this.shapes,
            language : 'ko',
            writing_guide: {
              "width":this.canvas.width,
              "height":this.canvas.height
            }
          }]
        },
        {
            transformRequest : (data, headers) => {                    
                headers.common['content-type'] = 'application/json'   
                delete headers.common['Authorization']
                return JSON.stringify(data)
            }
        })
        .then(({data}) =>{
          
          this.result=data[1][0][1][0];
          this.correct = this.result == this.nowshow.word;
        })
    },
    
      setEventHandlers(){
        this.pressedAt = Date.now();
        this.canvas.on('mouse:down',this.onDrawingMouseDown)
        this.canvas.on('mouse:move',this.onDrawingMouseMove)
        this.canvas.on('mouse:up',this.onDrawingMouseUp)
        this.canvas.on('touch:start',this.onDrawingMouseDown)
        this.canvas.on('touch:move',this.onDrawingMouseMove)
        this.canvas.on('touch:end',this.onDrawingMouseUp)
      },
      createCanvas(){
        var upper = document.getElementsByClassName('upper-canvas');
        upper
        // upper.style.height = '10vh';
      },
      keyboardInput(){
        var keyboard = document.getElementById('keyboard');
        keyboard.style.display = 'block';
        
        var iconkeyboard = document.getElementById('iconkeyboard');
        iconkeyboard.style.opacity = '1';

        var touch = document.getElementById('penandmouse');
        touch.style.display= 'none';
        
        var icontouch = document.getElementById('iconpenandmouse');
        icontouch.style.opacity= '0.3';
        var iclear = document.getElementById('clear-ans');
        iclear.style.opacity= '0';
        iclear.style.margin='10vw';
        this.inputtype=1;
      },
      touchInput(){
        var keyboard = document.getElementById('keyboard');
        keyboard.style.display = 'none';
        var iconkeyboard = document.getElementById('iconkeyboard');
        iconkeyboard.style.opacity = '0.3';
        
        var touch = document.getElementById('penandmouse');
        touch.style.display= 'block';
        
        var icontouch = document.getElementById('iconpenandmouse');
        icontouch.style.opacity= '1';
        var iclear = document.getElementById('clear-ans');
        iclear.style.opacity= '1';
        iclear.style.margin='1vw';
        
        this.inputtype=0;
      },
      clearCanvas(){
        this.canvas.clear();
        this.result="";
        this.shapes = [];
      },
      
      nextword(){
        this.pressedAt = Date.now();
        this.index = this.index+1;
        var c = document.getElementById('c');
        this.ansimg = c.toDataURL();
        document.getElementById("hint").style.opacity = '0';

      //배포시

        this.submitTest()
        this.nowshow = this.wordList[this.index];
        this.clearCanvas();
        this.correct = false;
        this.anstext = "";
        this.ansimg = null;
        
      },
      finish(){
        this.submitTest()
        this.gowordcategory()
      },
      submitTest(){
        if(!this.correct){
          
          this.correct = this.anstext == this.nowshow.word
        }
        this.ansimg = this.$refs.c.toDataURL();
        var data = {
            'wordTestNo' : this.nowshow.testNo,
            'submitAnswer' : this.anstext,
            'submitImgData' : this.ansimg,
            'correct' : this.correct 
        }

        axios.post('/wordtestsubmit/wordtestsubmit',data)
          .then(
            res =>{
              res
            }
          )
        .catch(err => {
            err
        });
      },
      onDrawingMouseDown(e){
            this.isPressing = true;
            
            if(e.pointer.x < 0){
                e.pointer.x = 0;
            }
            if(e.pointer.y < 0){
                e.pointer.y = 0;
            }
            
            this.clearCurShape() 
            this.curShape[0].push(e.pointer.x)
            this.curShape[1].push(e.pointer.y)
            this.curShape[2].push(Date.now() - this.pressedAt);
        },

        onDrawingMouseMove(e){
            if(!this.isPressing) return;
            if(e.pointer.x < 0){
                e.pointer.x = 0;
            }
            if(e.pointer.y < 0){
                e.pointer.y = 0;
            }
            this.curShape[0].push(e.pointer.x)
            this.curShape[1].push(e.pointer.y)
            this.curShape[2].push(Date.now() - this.pressedat);
            
        },
        onDrawingMouseUp(){
            this.isPressing = false;
            this.shapes.push(this.curShape);
            
            this.getNoni();
        },
        clearCurShape(){
           this.curShape = [
               [],
               [],
               []
           ]
        },
        clickread(){
          this.korAudioLoaded = false
            let timerId = setInterval(() => {
                axios.get(this.$store.getters.BACKEND_URL + this.nowshow.audioPath)
                .then(() => {

                    clearInterval(timerId)
                    this.korAudioLoaded = true
                    new Audio(this.$store.getters.BACKEND_URL + this.nowshow.audioPath).play()
                })
                .catch(() => {
                })
                
            },300) 
        }
      },
    }
</script>


<style scoped>

.contain{
  margin:2vh 2vw;
  font-family: 'Jua', sans-serif;
}
.back_menu {
  display: flex;
  font-family: 'Jua', sans-serif;
  font-size: 250%;
  color:#ffba00;
  margin: 10px;
}
.goallist{
  display: flex;
  justify-content: center;
  margin-left: 5%;
}
.goalimg{
  width: 6vw;
  height: 7vw;
}

.clickbtns.wordtestfooter {
    height: 60px;
    margin: 26px 0;
    display: center;
}
.clickbtn{
    border: 3px solid blue;
    background-color: rgb(119, 197, 17);
    box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 0px 0px;
    margin-right: 16px;
}
.nomobile{
  margin:0;
}
.wordheader{
  display: flex;
  margin: 0 4vw;
  justify-content: space-between;
  font-size: 2vh;
}
.wordheader>div{
  font-size:5vh;
}


.iconkeyboard{
  width: 100px;
  height: 100px;
}
.iconpenandmouse{
  width: 200px;
  height: 100px;
}
.radiusboard{
  
  border:solid 1px;
  border-color:rgb(200, 200, 200);
  border-radius: 5px;
}

.speakimg{
    width: 30px;
    display: inline-block;
}
.cfont{
  min-height: 40px;
  font-size:auto;
  height: 4vh;
}
.cimg{
    width: 35vw;
    height: 50vh;
    margin: auto 10px;
    border-radius: 10%;
  }
.floatleft{
  /* float:left; */
  display:flex;
}


  .inputdiv{
    width: 35vw;
    background-color: skyblue;
    height: 35vw;
  }
  .maincontent{
    margin: 0 auto;
  }
  .rewrite{
    line-height: 12vw;
    font-size: 100px;
  }
  
.ismobile{
  display: flex;
}

#clear-ans{
  position: absolute;
  margin: 1vw;
  display: block;
  z-index: 1;
}

.btn-wrapper{
  display: flex;
  justify-content: space-between;
  margin-top : 10vh;
}

@media (min-width:481px) and (max-width: 763px){
  
.products{
  display: none;
}
  .iconkeyboard{
  width: 50px;
  height: 50px;
}
.iconpenandmouse{
  width: 100px;
  height: 50px;
}
  .cimg{
    width: 30vw;
    height: 50vh;
    margin: auto 10px;
    border-radius: 10%;
    max-height: 230px;
  }
  
.floatleft{
  display:inline-flex;
}
.conmain{
  display: block;
}
.cfont{
  width: 25vw;
    font-size:3vh;
}
.noimg{
  /* display: flex; */
  height: 35vw;
}
.btn-wrapper{
  margin:0;
}
#clear-ans{
    margin: 0 15px;
    left: 32vw;
}
.clickbtns{
  display: flex;
    margin: 0 auto;
}
.btn-wrapper{
  display: flex;
  justify-content: space-between;
  margin-top : 5vh;
}
.inputdiv  {
  margin: 0 auto;
}
}

@media (min-width:764px) and (max-width: 1370px){
  .conmain{
    display: flex;
  justify-content: center;
  }
  .maincontent{
    margin: 0 auto;
  }
.products{
  display: none;
}
  .iconkeyboard{
  width: 50px;
  height: 50px;
}
.iconpenandmouse{
  width: 100px;
  height: 50px;
}
  .cimg{
    width: 35vw;
    height: 35vw;
    margin: auto 10px;
    border-radius: 10%;
  }
}

@media (min-width:1371px) and (max-width: 2360px){
  .conmain{
    display: flex;
  justify-content: center;
  }
  .iconkeyboard{
  width: 50px;
  height: 50px;
}
.iconpenandmouse{
  width: 100px;
  height: 50px;
}
  .cimg{
    width: 35vw;
    height: 35vw;
    margin: auto 10px;
    border-radius: 10%;
  }
}


@media (max-width: 480px) {
  
.floatleft{
  display:inline-flex;
}
  .cimg{
    width: 90vw;
    margin: 5px auto;
    height: 30vh;
    border-radius: 10%;
  }
.conmain{
  display: block;
}
.cfont{
  width: 25vw;
    font-size:3vh;
}
.iconkeyboard{
  width: 15vw;
  height: 10vh;
}
.iconpenandmouse{
  width: 30vw;
  height: 10vh;
}
.products{
  display: none;
}
.inputdiv{
  width:70.1vw;
  margin: 0px auto 5px auto;
  height: 70.1vw;
  
}
.nomobile{
  display: none;
}
.ismobile{
  display: contents;
}
.mobilebtn{
  
    height: 36px;
    min-width: 64px;
    padding: 0 16px;
}
.noimg{
  display: flex;
  height: 90vw;
}
.btn-wrapper{
  margin:0;
}
.clickbtns{
  
  width: 20vw;
  display: block;
}
.btn-wrapper{
  display: flex;
  justify-content: space-between;
}
.rewrite{
  font-size:30px;
}
.clicknextbtn{
  margin-top: 15vh;
}
}

</style>
