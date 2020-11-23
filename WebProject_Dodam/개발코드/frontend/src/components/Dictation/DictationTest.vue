<template>
  <div class="dictationcontainer">
      <div class="dicheader">
        <section style="margin-left:3%">
          <div v-if="!classCheck" @click="gocategory" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>받아쓰기 목록</div>
          <div v-if="classCheck" @click="goclass" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>클래스 목록</div>

        </section>
        <div style="margin:1%;">{{questionsno}}단계</div>
        <!-- <a href="/dictation"><i class="fas fa-arrow-circle-left fa-3x">받아쓰기</i></a> -->
      </div>
      
      
    <div v-if="testing">
      <div class="testbtnlist">
        <div class="testbtns" v-for="(questions, i) in questions" :key="i">
          <v-btn v-bind:id="'test'+(i)" class="testbtn" @click="changeQuestions(i)">{{i + 1}}</v-btn>
        </div>
      </div>

      <div class="speaklist" @click="speak()"> 
        <h2>문제를 듣고 써봅시다.</h2>
        <i class="fas fa-play-circle fa-2x" style="color:#baeb34; margin:5px;"/>
      </div>

      <div id="keyboard" style="display:none;">
        <b-form-input id="k" v-model="showtext" @change="keyDown()" type="text"/>
        <div class="rewrite">
        {{showtext}}
        </div>
      </div>
      <div id="penandmouse">
        <div class= "canvaslist">
          <div v-for="(x) in 10" :key="x">
          <DictationCanvas :canvasId="`canvas0`+(x-1)" :canvasSetting="{canvasset}"></DictationCanvas>
          </div>
        </div>

        <div class= "canvaslist">
          <div v-for="(x) in 10" :key="x">
          <DictationCanvas :canvasId="`canvas1`+(x-1)" :canvasSetting="{canvasset}"></DictationCanvas>
          </div>
        </div>
      </div>

      <div class="movebtns">
        <v-btn v-if="this.idx!=0" @click="changeQuestions('prev')">이전문제</v-btn>
        <v-btn v-if="this.idx==0" style="opacity:0;">안보이지</v-btn>
        <div class="clickbtns"> 
          <v-btn @click="keyboardInput()" id="iconkeyboard" class="clickbtn"><i class="fas fa-keyboard"></i>키보드</v-btn>
          <v-btn @click="touchInput()" id="iconpenandmouse" class="clickbtn"><i class="fas fa-pencil-alt"></i>펜
            /<i class="mdi mdi-mouse"></i>마우스</v-btn>
          <v-btn @click="eraserMode()" id="ieraser" class="clickbtn"><i class="fas fa-eraser"></i>부분지우기</v-btn>
          <v-btn @click="clearAll()"  class="clickbtn"><i class="fas fa-trash-alt"></i>전체지우기</v-btn>
          <v-btn @click="endtest()" class="clickbtn"><i class="fas fa-file-upload"></i>제출하기</v-btn>
        </div>
        <v-btn v-if="this.idx < questions.length - 1" @click="changeQuestions('next')">다음문제</v-btn>
        <v-btn v-else style="opacity:0;">못보지롱</v-btn>
      </div> 


      <!-- ocr 결과 확인 창 -->
      
      <h2 class="speaklist">내가 쓴 답변</h2>
      <v-card dense style="height:8%; width:95%; margin:3%;">
        <v-html>
          <pre id="result00">
          {{this.testwo}}
          <br>

          </pre>
        </v-html>
      </v-card>
      <div style="display:none">
        <DictationCanvas :canvasId="`resultc`" :canvasSetting="{canvasset}"></DictationCanvas>
      </div>
    </div>
    <div v-if="!testing">
      <DictationResult v-bind:ansdata="endresult" :correctcount="endcorrectcount" :questions="endquestions" ></DictationResult>
    </div>
    <v-dialog v-model="OK" fullscreen>
      <v-container>
        <div id="containeral" style="margin:20% 30%">
          <div id="success-box">
            <div class="face">
              <div class="eye"></div>
              <div class="eye right"></div>
              <div class="mouth happy"></div>
            </div>
            <div class="shadow scale"></div>
            <div class="message">
              <h1 class="alert">성공적으로 제출 되었습니다.</h1>
              <p>자동으로 성적표로 구현 중!</p>
            </div>
            <button class="button-box green" style="margin: 5% auto">
              <h1 @click="clear">확인</h1>
            </button>
          </div>
        </div>
      </v-container>
    </v-dialog>
    <v-dialog v-model="NOK" fullscreen>
      <v-container>
        <div id="containeral" style="margin:20% 5%">
          <div id="error-box">
            <div class="face2">
              <div class="eye"></div>
              <div class="eye right"></div>
              <div class="mouth sad"></div>
            </div>
            <div class="shadow scale"></div>
            <div class="message">
              <h1 class="alert">제출에 실패했어요</h1>
              <p>{{need_problem}}번 문제를 풀어주세요!!</p>
            </div>
            <button class="button-box green" style="margin: 3% auto">
              <h1 @click="clear_no">확인</h1>
            </button>
          </div>
        </div>
      </v-container>
    </v-dialog>
  </div>
</template>

<script>
import DictationCanvas from "./DictationCanvas";
import DictationResult from "./DictationResult";
import axios from 'axios';

export default {
  name: 'dictationtest',
  components:{
    DictationCanvas,
    DictationResult
  },
  props:{
    questionsno:{
      type:String,
      default:""
    }
  },
  data() {
    return { 
      idx : null, //now question index
            // questionno : 'a',
      questions:[ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],
      API_ENDPOINT : 'https://inputtools.google.com/request?ime=handwriting&app=autodraw&dbg=1&cs=1&oe=UTF-8',
      pressedat: 0,
      ispressing: false,
      maxX: 100000,
      minX: 0,
      maxY: 100000,
      minY: 0,
      shapes: [],
      posti:"",
      curShape:[],
      canvas:[],
      canvasset: {
        width:0,
      },
      showtext:"",
      iseraser: false,
      inputtype: 0,
      anscount:0,
      ansdata:[],
      testwo:"                    ",
      defaultocr:"                    ",
      showocr:"",
      
      // 테스트
      word:[
          [],[],[],[],[],
          [],[],[],[],[],
          [],[],[],[],[],
          [],[],[],[],[]
      ],
      // ======== 배포시 주석 ==========
      imgsave:[],
      korea: /[^ㄱ-ㅎ|ㅏ-ㅣ|가-힝|` `|0-9]/g,
      testing:true,
      
      endresult:[],
      endquestions:[],
      endcorrectcount:0,
      classCheck:false,
      OK:false,
      NOK:false,
      need_problem:1
    }
  }, 
  created(){
    
    this.canvasset.width =window.innerWidth/11;
    this.getDictations();
  },
  mounted(){
    this.makeword();
  },
  methods:{
    clear(){
      this.saveImage();
      this.OK=false
    },
    clear_no(){
      this.NOK=false
    },
    gocategory() {
      this.$router.push('/dictation')
    },
    goclass() {
      this.$router.push('/class')
    },
    getDictations() {
      let requestURL

      if(this.$route.params.homeworkno){
        this.classCheck=true;
        requestURL = `/dictation/dictationhomework/${this.$route.params.homeworkno}`
      }else if(this.$route.params.questionsno){
        requestURL = `/dictation/${this.$route.params.questionsno}`
      }

      axios.get(requestURL)
      .then((Response)=>{
        this.questions = Response.data;
        this.remakequestions();
        this.makeSave();
        this.clearImgs();
        this.setCanvas();
        this.touchInput();
        this.changeQuestions(0);
      })
      .catch((err) =>{
        err
      })
    },
    remakequestions(){
      for(var i=0; i<this.questions.length;i++){
        var tmp = this.questions[i].dictationSentence.replace(this.korea,"").trim();
        this.questions[i].dictationSentence = tmp;
      }
    },
    makeword(){
      for(var x = 0; x<20; x++){
        this.setword(x);
      }
      
    },
    setword(x){
      var wordform = {
        load : false
      };
      this.word[x]= wordform;
    },


    endtest(){
      this.updateAns();
      var unans=[];
      for(var k = 0; k < this.questions.length;k++){
        if(!this.ansdata[k].wasans){
          unans.push(k+1);
        }
      }
      if(this.anscount == this.questions.length){
        this.OK = true
      }
      else {
        this.NOK = true
        this.need_problem = unans
      }
    },

    makeSave(){
      for(var i=0; i<this.questions.length;i++){
        this.setSave(i);
      }
    },
    setSave(index){
      var ansform = {
        ansidx: this.questions[index].dictationNo,
        anstext : "",
        ansshape: null,
        ansimg :null,
        ocrresult : this.defaultocr,
        wasans: false,
        poste: null,
        anstype : this.inputtype,
      };
      this.ansdata.push(ansform);
    },

    setCanvas(){
      this.clearCurShape();
      for(var j=0;j<2;j++){
        for(var i=0;i<10;i++){
          this.canvas.push(document.getElementById(`canvas`+j+i));
          var arr=[];
          this.shapes.push(arr);
          this.setEventHandlers(j*10+i);
        }
      }
      this.canvas.push(document.getElementById(`resultc`));
    },
    changeQuestions(btnnum){
      if(btnnum == this.idx){
        alert("현재 풀고 있는 문제예요~~!")
      }
      else{
        this.updateAns();
        if(this.idx != null){
          document.getElementById('test'+this.idx).style.opacity = '0.3';
        }
        if(btnnum=="next"){
          this.idx++;
        }
        else if(btnnum=="prev"){
          this.idx--;
        }
        else{
          this.idx = btnnum;
        }

        // alert(this.idx)
        this.clearAll();
        // alert('here')
        this.loadAns();
        document.getElementById('test'+this.idx).style
          .opacity = '1';
        }
    },

    keyboardInput(){
      this.inputtype=1;
      this.changeBtnCss();
    },

    touchInput(){
      this.inputtype=0;
      this.iseraser=false;
      this.changeBtnCss();
    },
    
    changeBtnCss(){
      document.getElementById('keyboard').style
        .display = (this.inputtype==0) ?'none':'block';
      document.getElementById('iconkeyboard').style
        .opacity = (this.inputtype==0) ?'0.3':'1';
      
      document.getElementById('penandmouse').style
        .display= (this.inputtype==0)?'block':'none';
      
      document.getElementById('iconpenandmouse').style
        .opacity= (this.inputtype==0)?'1':'0.3';

      document.getElementById('ieraser').style
        .opacity = (this.inputtype==0)?'0.3':'0';
    },

    eraserMode(){
      if(this.inputtype==1)return;
      document.getElementById('ieraser').style.opacity = this.iseraser?'0.3':'1';
      this.iseraser = this.iseraser?false:true;
    },
    
    clearOne(index){
      window.__canvasList[index].clear();
      this.shapes[index]=[];
      this.testwoc(index," ");
      this.setword(index);
      
      this.refreshimg(index);

      window.__canvasList[20].clear();
      this.makepostimg();
    },
    clearAll(){
      this.showtext="";
      for(var i=0;i<20;i++){
        window.__canvasList[i].clear();
        this.shapes[i]=[];
        this.testwoc(i," ");
        this.setword(i);
        
        this.refreshimg(i);
      }
      window.__canvasList[20].clear();
      this.makepostimg();
      this.clearImgs();
    },
    clearImgs(){
      var tmp = [];
      for(var i = 0;i<21;i++){
        tmp.push([]);
      }
      this.imgsave=tmp;
    },

    updateAns(){
      var ansform;
      for(var i=0;i<20;i++){
        
        this.refreshimg(i);

      }
      var tmp = document.getElementById('resultc').toDataURL();
      this.imgsave[20] = tmp;
      if(this.idx != null){
        if(!this.ansdata[this.idx].wasans){
          this.anscount++;
        }
        ansform = {
          ansidx:this.questions[this.idx].dictationNo,
          anstext : this.showtext,
          ansshape: this.shapes,
          ansimg :{...this.imgsave},
          ocrresult : this.testwo,
          wasans: true,
          poste: tmp,
          anstype : this.inputtype,
        };
        
        this.ansdata[this.idx] = ansform;
      }
      
    },
    loadAns(){
      if(!this.ansdata[this.idx].wasans){
        return;
      }
      if(this.ansdata[this.idx].anstype == 1 && this.ansdata[this.idx].anstext !=""){
        this.showtext = this.ansdata[this.idx].anstext;
        return;
      }
      this.testwo = this.ansdata[this.idx].ocrresult;
      this.shapes = this.ansdata[this.idx].ansshape;
      this.showtext = "";
      for(var i = 0;i<20;i++){
        this.word[i].load=true;
        this.loadImg(i,1,1);
      }
      this.loadImg(20,10,2);
      this.imgsave = this.ansdata[this.idx].ansimg;
      this.posti = this.ansdata[this.idx].poste;

    },

    loadImg(index,w,h){
      var width =this.canvasset.width;
      var myCanvas = window.__canvasList[index];
      var ctx = myCanvas.getContext('2d');
      var img =new Image;
      img.onload = function(){
        ctx.drawImage(img,0,0,width*w,width*h);
      };
      img.src = this.ansdata[this.idx].ansimg[index];
    },


    mimg(ctx, i, j){
        var width =this.canvasset.width;
        var img =new Image;
      if(ctx==0){
        var myCanvas = window.__canvasList[20];
        var ctxi = myCanvas.getContext('2d');
        img.onload = function(){
          ctxi.drawImage(img,i*width,j*width,width,width);
        };
        img.src = this.imgsave[j*10+i];
      }
      else{
        img.onload = function(){
          ctx.drawImage(img,i*width,j*width,width,width);
        };
        img.src = this.imgsave[j*10+i];
      }
    },
    makepostimg(){
      var myCanvas = window.__canvasList[20];
      var ctx = myCanvas.getContext('2d');
      for(var j=0;j<2;j++){
        for(var i=0;i<10;i++){
          this.mimg(ctx, i,j);
        }
      }
    },

    clearCurShape(){
      this.curShape=[];
      
      for(var i=0;i<20;i++){
        this.setCurShape();
      }
    },

    setCurShape(){
      var arr=[
        [],
        [],
        []
      ]
    
      this.curShape.push(arr);
    },

    onDrawingMouseDown(index,e){
      // update more...
      if(this.word[index].load){
        this.clearOne(index);
        this.word[index].load=false;
      }
      //======================
      if(this.iseraser) {
        this.clearOne(index);
        return;
      }
      this.ispressing = true;
      this.pressedat = Date.now();
      if(e.pointer.x < 0){
              e.pointer.x = 0;
      }
      if(e.pointer.y < 0){
              e.pointer.y = 0;
      }	
      this.clearCurShape(); 
      this.curShape[index][0].push(e.pointer.x)
      this.curShape[index][1].push(e.pointer.y)
      this.curShape[index][2].push(Date.now() - this.pressedat);

    },

    onDrawingMouseMove(index,e){
      if(!this.ispressing) return;
      if(e.pointer.x < 0){
              e.pointer.x = 0;
      }
      if(e.pointer.y < 0){
              e.pointer.y = 0;
      }
      this.curShape[index][0].push(e.pointer.x)
      this.curShape[index][1].push(e.pointer.y)
      this.curShape[index][2].push(Date.now() - this.pressedat);

      (e.pointer.x > this.maxX) && (this.maxX = e.pointer.x);
      (e.pointer.x < this.minX) && (this.minX = e.pointer.x);
      (e.pointer.y > this.maxY) && (this.maxY = e.pointer.y);
      (e.pointer.y < this.minY) && (this.minY = e.pointer.y);

      this.showtext = "";
    },
    onDrawingMouseUp(index){
      if(this.iseraser){
        this.clearOne(index);
        return;
      }
      this.ispressing = false;
      this.shapes[index].push(this.curShape[index]);
      this.getNoni(index);
     
    }, 
    setEventHandlers(index){
      window.__canvasList[index]
        .on('mouse:down',this.onDrawingMouseDown.bind(null,index))
        .on('mouse:move',this.onDrawingMouseMove.bind(null,index))
        .on('mouse:up',this.onDrawingMouseUp.bind(null,index))
        .on('touch:start',this.onDrawingMouseDown.bind(null,index))
        .on('touch:move',this.onDrawingMouseMove.bind(null,index))
        .on('touch:end',this.onDrawingMouseUp.bind(null,index));
    }, 
    
    getNoni(index){
      axios.post(this.API_ENDPOINT, {
      input_type : 0,
      requests: [{
        ink:  this.shapes[index],
        language : 'ko',
        writing_guide: {
          "width":this.canvas[index].width,
          "height":this.canvas[index].height
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
        var result=data[1][0][1][0];
        this.word[index].load = false;
        this.testwoc(index,result[0]);

        this.refreshimg(index);
        this.mimg(0,index%10,parseInt(index/10));
      })
    },

    refreshimg(index){
        var tmpimg;
        if(index<10){
          tmpimg = document.getElementById(`canvas0`+index).toDataURL();
        }else{
          tmpimg = document.getElementById(`canvas`+index).toDataURL();
        }
        this.imgsave[index] = tmpimg;
      
    },

    testwoc(index,result){
      var conshow=this.testwo.substring(0,index)+result;
      if(index!=19){
        conshow=conshow+this.testwo.substring(index+1,20);
      }
      this.testwo = conshow;
    },


    async saveImage(){
      var makedata = [];
      for(var i=0;i<this.questions.length;i++){
        var studentansOCR = this.ansdata[i].ocrresult.replace(this.korea,"").trim();
        var studentansText = this.ansdata[i].anstext.replace(this.korea,"").trim();
        
        var dataform ={
          'dictationNo': this.questions[i].dictationNo,
          'submitAnswer': studentansText,
          'submitImgData' : this.ansdata[i].ansimg[20],
          'submitOCR' : studentansOCR,
          'correct' : this.correct(studentansOCR,studentansText,i),
        }
        makedata.push(dataform);
      }
      this.endresult = makedata;
      this.testing = false;
      await axios.post('/dictationsubmit/dictationsubmit', makedata)
    },

    correct(studentansOCR,studentansText,i){
      var questionform={
        'dictationNo' : i,
        'dictationSentence' : this.questions[i].dictationSentence,
      };
      this.endquestions.push(questionform);
      if(studentansOCR == this.questions[i].dictationSentence){
        this.endcorrectcount = this.endcorrectcount+1;
        return true;
      } else if(studentansText == this.questions[i].dictationSentence){
        this.endcorrectcount = this.endcorrectcount+1;
        return true;
      }
      return false;
    },

    speak() {
      new Audio(this.$store.getters.BACKEND_URL + this.questions[this.idx].audioPath).play()
      
      // if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
      //   alert("이 브라우저는 음성 합성을 지원하지 않습니다.");
      //   return;
      // }
      // window.speechSynthesis.cancel() // 현재 읽고있다면 초기화
      // const speechmsg = new SpeechSynthesisUtterance()
      // speechmsg.rate = 1    
      // speechmsg.pitch = 1.2
      // speechmsg.lang = "ko-KR"
      // speechmsg.text = this.questions[this.idx].dictationSentence;
      // // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행
      // window.speechSynthesis.speak(speechmsg)
    },
  } 
}
</script>

<style scoped>
* {
  -webkit-touch-callout: none;
  /* iOS Safari */
  -webkit-user-select: none;
  /* Safari */
  -khtml-user-select: none;
  /* Konqueror HTML */
  -moz-user-select: none;
  /* Firefox */
  -ms-user-select: none;
  /* Internet Explorer/Edge */
  user-select: none;
  /* Non-prefixed version, currently supported by Chrome and Opera */
}

.dictationcontainer{
  margin:2vh 2vw;
}

.dicheader{
  display: flex;
  margin: 0 4vw;
  justify-content: space-between;
  font-size: 2vh;
}
.dicheader>div{
  font-size:4vh;
}
.back_menu {
  display: flex;
  font-family: 'Jua', sans-serif;
  font-size: 250%;
  color:#ffba00;
  margin: 10px;
}
h3 {
  font-family: 'Jua', sans-serif;
  margin: 0;
  font-size: 250%;
}
.testbtnlist{
  display: flex;
  justify-content: space-around;
  margin: 2vh 2vw;
  height: 4vh;
}
.testbtn {
  opacity: 0.3;
  border: 3px solid rgb(105, 170, 4);
  background-color: rgb(119, 197, 17);
  box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 0px 0px;
  width: 7vw;
  padding: 0;
}

.speaklist{
  display:flex;
  margin-left: 3vw;
  height: 4vh;
}

.movebtns{
  display: flex;
  justify-content: space-between;
  margin: 0 2vw;
}

.canvaslist{
  display: flex;
  width: 90.8vw;
  margin: 2vh 3vw;
  background-color: snow;
}

.rewrite{
  font-size: 5vw;
}

#keyboard{
  height: 19vw;
  margin:2vw 3vw;
}

#ieraser{
  opacity: 0.3;
}

.clickbtns {
  height: 60px;
  margin: 0% 0;
  display: center;
}
.clickbtn {
  border: 3px solid blue;
  background-color: rgb(119, 197, 17);
  box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 0px 0px;
  margin-right: 16px;
}

.testimg{
  width: 9vw;
  height: 9vw;
}
.oneimg{
  width: 90vw;
  height: 18vw;
}

#result00{
  font-size:5vh;
  margin:0 auto;
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

#containeral {
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