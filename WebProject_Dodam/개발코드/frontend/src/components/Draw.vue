<template>
<div>
    <div class="wrapper">
        <div class="canvas-wrapper" style="user-select: none;">
            <div class="autodraw-results">
            <!-- <template v-for="(suggestion) in suggestions"> 
                <figure class="autodraw-image" v-for="icon in suggestion.icons" :key="icon" @touchstart="pickDrawSuggestion(icon, suggestion.name)" @click="pickDrawSuggestion(icon, suggestion.name)">
                    <img :src="icon" width="50" height="50" :alt="suggestion.name" :title="suggestion.name">
                </figure>  -->
                <template v-for="(suggestion, idx) in suggestions">
                    <figure v-if="suggestion.icons.length > 0" :key="idx" class="autodraw-image" @touchstart="pickDrawSuggestion(suggestion.name)" @click="pickDrawSuggestion(suggestion.name)">
                        <img  class="suggestion-image" :width="suggestionWidth" :height="suggestionHeight" :src="getRandomImg(suggestion)" :alt="suggestion.name" :title="suggestion.name">
                    </figure>
                </template>
            <!-- </template> -->
            </div>
            <img src="../assets/images/sketchbook.png" style="width:100vw; height: 100vh; position: absolute;"/>
            <div class="canvasdiv">
                <div  id="svgWrapper" v-show="svgLoaded" v-html="svgElement"></div>
                <canvas ref="c" id="c" style="border: 1px solid rgb(170, 170, 170); touch-action: none; user-select: none;" class="lower-canvas"></canvas>
            </div>
            <div class='btn-wrapper' v-if="selected">
                <button v-if="korAudioLoaded" id="kr-btn" @touchstart="speak('ko-KR')" @click="speak('ko-KR')"><div class="text">{{translated}}</div> <i id="kr-play" class="fas fa-play-circle fa-2x" style="color:green; margin:10px"></i></button>
                <loading-icon v-else></loading-icon>
                <button v-if="engAudioLoaded" id="eng-btn" @touchstart="speak('en-US')" @click="speak('en-US')"><div class="text">{{src}}</div> <i id="eng-play" class="eng-play fas fa-play-circle fa-2x" style="color:green; margin:10px"></i></button>
                <loading-icon v-else></loading-icon>
            </div>
        </div>
<!-- 
  <transition name="fade">
    <h2 v-if="isActive">Hello World !</h2>
  </transition> -->
    <button class="brushbox" id="colorheader" @click="isActiveBrushColor = !isActiveBrushColor">
        색
    </button>
            
        <transition name="fade">
            <div v-if="isActiveBrushColor">
                <button class="brushbox" style="background-color:red;" @click="changecolor('red')"></button>
                <button class="brushbox" style="background-color:orange;" @click="changecolor('orange')"></button>
                <button class="brushbox" style="background-color:pink;" @click="changecolor('pink')"></button>
                <button class="brushbox" style="background-color:yellow;" @click="changecolor('yellow')"></button>

                <button class="brushbox" style="background-color:greenyellow;" @click="changecolor('greenyellow')"></button>
                <button class="brushbox" style="background-color:green;" @click="changecolor('green')"></button>
                <button class="brushbox" style="background-color:aqua;" @click="changecolor('aqua')"></button>
                <button class="brushbox" style="background-color:blue;" @click="changecolor('blue')"></button>

                <button class="brushbox" style="background-color:navy;" @click="changecolor('navy')"></button>            
                <button class="brushbox" style="background-color:blueviolet;" @click="changecolor('blueviolet')"></button>
                <button class="brushbox" style="background-color:brown;" @click="changecolor('brown')"></button>
                <button class="brushbox" style="background-color:black;" @click="changecolor('black')"></button>
            </div>
        </transition>
        <button class="brushbox" id="colorheader" @click="clickSize()">
            {{brushsize}}
        </button>
        <transition name="fade">
            <div v-show="isActiveBrushSize">
                <div class="slidecontainer">
                    <div id="demo" class="fixst2">
                    </div>
                    <input type="range" min="1" max="20" :value=brushsize class="slider fixst" id="myRange" @change="changesize()">
                    
                </div>
            </div>
        </transition>
        <button class="brushbox" @click="clearCanvas()">
            <i class="fas fa-eraser erasericon"></i>
        </button>
        <button v-show="!isRecord" class="brushbox" id="colorheader" @click="activeSpeech()">
            <i  class="fa fa-microphone"></i>
        </button>
        <button v-show="isRecord" class="brushbox" id="colorheader" @click="stopSpeech()">
            <i class="fa fa-stop" ></i>
        </button>
        <div v-if="!isActiveBrushColor">
        <button class="exitbox" @click="gocontents">
            <img src="../assets/images/exit.png" style="width:4vw;"/>
        </button>
        </div>
    </div>
    
    <div class="ismobile">
    <section>
        <div @click="gocontents" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>돌아가기</div>
    </section>
        <div>모바일은 지원 준비중입니다. 죄송합니다.
        </div>
    </div>
</div>
</template>

<script>
import Axios from 'axios';
import {fabric} from 'fabric';
import LoadingIcon from '@/components/LoadingIcon.vue'
import anime from 'animejs/lib/anime.es.js'
export default {
    components:{
        LoadingIcon : LoadingIcon
    },
    data(){
        return{
            API_ENDPOINT : 'https://inputtools.google.com/request?ime=handwriting&app=autodraw&dbg=1&cs=1&oe=UTF-8',
            stencils: [],
            suggestions:[],
            canvas: null, 
            pencil_effect: null,
            svgElement: null,
            svgLoaded:false,
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
            srcText:{},
            translated: '',
            src:'',
            engAudio:'',
            korAudio:'',
            audioSrc:'',
            canvasWidth: null,
            canvasHeight:null,
            randomImg:{},
            engAudioLoaded:false,
            korAudioLoaded:false,
            selected:false,
            suggestionHeight:null,
            suggestionWidth:null,
            brushcolor:'black',
            isActiveBrushColor: false,
            brushsize:10,
            isActiveBrushSize:true,
            upperCanvas: null,
            recognition:null,
            sttResult:"11",
            isRecord:false,
        }
    },created(){
        this.recognition=new window.webkitSpeechRecognition;
    },
    methods:{
        gocontents() {
            this.$router.push('/contentsmain')
        },
        storeRandomImg(name, icon){
            this.randomImg[name] = icon
        },
        getRandomImg(suggestion){
            let randNum = Math.floor(Math.random() * suggestion.icons.length);
            if(suggestion.icons.length <= randNum){
                alert('ffff')
            }
            let icon = suggestion.icons[randNum]
            this.storeRandomImg(suggestion.name, icon)
            
            return icon
        },
        loadStencils(){
            this.stencils = require('@/assets/stencils.json')
            
        },
        speak(lang){
            this.activeAnimation();
            event.preventDefault();
           if(lang == 'ko-KR'){
               this.audioSrc = this.korAudio
           }else{
               this.audioSrc = this.engAudio
           }
        //    alert(this.audioSrc)
           new Audio(this.$store.getters.BACKEND_URL + this.audioSrc).play()
        },

        closeTool(){
            this.isActiveBrushColor= false;
            this.isActiveBrushSize= false;

        },
        onDrawingMouseDown(e){
            this.closeTool();

            this.isPressing = true;

            if(e.pointer.x < 0){
                e.pointer.x = 0;
            }
            if(e.pointer.y < 0){
                e.pointer.y = 0;
            }
            
            this.clearCurShape();
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
            this.curShape[2].push(Date.now() - this.pressedAt);
            (e.pointer.x > this.maxX) && (this.maxX = e.pointer.x);
            (e.pointer.x < this.minX) && (this.minX = e.pointer.x);
            (e.pointer.y > this.maxY) && (this.maxY = e.pointer.y);
            (e.pointer.y < this.minY) && (this.minY = e.pointer.y);
        },
        onDrawingMouseUp(){
            this.isPressing = false;
            this.shapes.push(this.curShape);
            
            this.getDrawSuggetions()
            this.updateObjectBox()
        },
        getDrawSuggetions(){
            Axios.post(this.API_ENDPOINT, {
                input_type : 0,
                requests: [{
                    ink: this.shapes,
                    language : 'autodraw',
                    writing_guide: {
                        "width": this.canvas.width,
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
                let results = JSON.parse(data[1][0][3].debug_info.match(/SCORESINKS: (.*) Service_Recognize:/)[1])
                    .map(result => {
                        return {
                            name: result[0],
                            icons: (this.stencils[result[0]] || []).map(collection => collection.src)
                        }
                    });
                this.suggestions = results.slice(0,20);
                
            })
        },
        checkKorAudioAvailable(){
            let timerId = setInterval(() => {
                Axios.get(this.$store.getters.BACKEND_URL + this.korAudio)
                .then(() => {
                    clearInterval(timerId)
                    this.korAudioLoaded = true
                })
                .catch(() => {
                })
                
            },300)
        },
        checkEngAudioAvailable(){
            let timerId = setInterval(() => {
                Axios.get(this.$store.getters.BACKEND_URL + this.engAudio)
                .then(() => {
                    clearInterval(timerId)
                    this.engAudioLoaded = true
                })
                .catch(() => {
                })      
                
            },300)
            
        },
        async pickDrawSuggestion(name){
            event.preventDefault();
            this.selected = true
            this.clearCanvas();
            this.src = name;
            if(this.srcText[name]){
                this.translated = this.srcText[name].translated
                this.korAudio = this.srcText[name].korAudio
                this.engAudio = this.srcText[name].engAudio
            }else{
                Axios.get(`/translate?text=${name}`)
                .then(({data}) => {
                    this.srcText[name] = {}
                    this.srcText[name]['translated'] = this.translated = data;
                    
                    // document.getElementById('kr-btn').disabled = true
                    // document.getElementById('kr-play').style.color = 'grey'
                    // document.getElementById('eng-btn').disabled = true
                    // document.getElementById('eng-play').style.color = 'grey'
                    this.engAudioLoaded = false
                    this.korAudioLoaded = false
                    
                    Axios.post(`/tts`,{
                        language : 'ko-KR',
                        text : this.translated
                    })
                    .then(({data}) => {
                        this.srcText[name]['korAudio'] =  this.korAudio = data
                        
                       
                        // this.korAudioLoaded = true;
                        this.checkKorAudioAvailable()
                    })

                    Axios.post(`/tts`,{
                        language : 'en-US',
                        text : this.src
                    })
                    .then(({data}) => {
                        this.srcText[name]['engAudio'] = this.engAudio = data
                        
                        this.checkEngAudioAvailable()
                        // this.engAudioLoaded = true;
                    })
                })
                .catch(() => {
                    
                })
            }

            await this.eraseSvg()
            this.svgElement = null;
            

            Axios.get(this.randomImg[name],
            {
                transformRequest : (data, headers) => {                    
                    delete headers.common['Authorization']
                    return JSON.stringify(data)
                }
            })
            .then(({data}) => {
                this.svgLoaded = false
                this.svgElement = data
            })
            .catch((error) => {
                error
            })
            // let image = new Image();
            // let icon = this.randomImg[name]
            // let imageWidth,imageHeight;
            // imageHeight = imageWidth = window.innerHeight * 0.6;
            
            // image.src = icon;
            // image.onload = () => this.canvas.getContext('2d').drawImage(image, 
            //             this.canvas.width / 2 - imageWidth / 2, this.canvas.height / 2 - imageHeight / 2, imageWidth, imageHeight);
            
        },
        clearCanvas(){
            this.canvas.clear();
            this.shapes = [];
            this.clearCurShape();
        },
        updateObjectBox(){
           
        },
        clearObjectBox(){

        },
        clearCurShape(){
           this.curShape = [
               [],
               [],
               []
           ]
        },
        setEventHandlers(){
            this.pressedAt = Date.now();
            this.canvas.on('mouse:down',this.onDrawingMouseDown)
            this.canvas.on('mouse:move',this.onDrawingMouseMove)
            this.canvas.on('mouse:up',this.onDrawingMouseUp)
        },
        changecolor(color){
            this.brushcolor = color;
            this.closeTool();
            document.getElementById("colorheader").style.backgroundColor = this.brushcolor;
            
            var canvas = window.__canvas;
            this.setBrush(canvas);    
        },
        setBrush(canvas){
            var brush = canvas.freeDrawingBrush;
            brush.color = this.brushcolor;

            if (brush.getPatternSrc) {
                brush.source = brush.getPatternSrc.call(brush);
            }
            brush.width = this.brushsize;
            
        },
        clickSize(){
            this.isActiveBrushSize = !this.isActiveBrushSize;
        },
        changesize(){
            var slider = document.getElementById("myRange");
            var output = document.getElementById("demo");
            output.innerHTML = slider.value; // Display the default slider value
        
            this.brushsize =slider.value;
        // Update the current slider value (each time you drag the slider handle)
            // output.innerHTML = slider.value;

            slider.oninput = function() {
                output.innerHTML = this.value;
                this.brushsize =this.value;
            }
            var canvas = window.__canvas;
            this.setBrush(canvas);

        },
        prepareCanvas() {
            // var $ = function(id){return document.getElementById(id)};
            let canvasHeight = this.canvasHeight
            let canvasWidth = this.canvasWidth
            
            let container = document.getElementsByClassName('canvas-wrapper')[0]
            container.style.width = window.innerWidth + 'px'
            container.style.height = window.innerHeight + 'px'

            var canvas = window.__canvas = new fabric.Canvas('c', {
                isDrawingMode: true,
                width : canvasWidth,
                height : canvasHeight
            });
            
            fabric.Object.prototype.transparentCorners = false;

            var texturePatternBrush = new fabric.PatternBrush(canvas);
            // texturePatternBrush.source = img;
            
            canvas.freeDrawingBrush = texturePatternBrush
            this.setBrush(canvas);

            

            /////////////////////
            var canvases = window.__canvases || window.canvases;

            canvas && canvas.calcOffset && canvas.calcOffset();

            if (canvases && canvases.length) {
                for (var i = 0, len = canvases.length; i < len; i++) {
                    canvases[i].calcOffset();
                }
            }

            document.getElementsByTagName('canvas').forEach( (canvas) => {
                canvas.style.border = 'none'
            })
        },
        setSvgSize(){
            let svgWrapper = document.getElementById('svgWrapper')
            let width, height;
            
            
            width = height =  this.canvas.height * 0.7
            svgWrapper.style.width = svgWrapper.style.height = width + 'px'
            svgWrapper.style.top = this.canvas.height / 2 - height / 2 + 'px'
            svgWrapper.style.left = this.canvas.width / 2 - width / 2 + 'px'
        },
        init(){
            this.prepareCanvas();
            this.canvas = window.__canvas;
            this.canvas.setWidth(window.innerWidth*0.9);
            this.setEventHandlers();
            this.changesize();
            this.setSvgSize();
            // let svgWrapper = document.getElementById('svgWrapper')
            // svgWrapper.style.top = this.canvas.height / 2 - this.svgWrapper.style.height / 2
            // svgWrapper.style.left = this.canvas.width / 2 - this.svgWrapper.style.width / 2
        },
        async eraseSvg(){
            let svgPath;
            svgPath = document.querySelectorAll('path');
            const eraseAnimation =  anime({
                targets: svgPath,
                strokeDashoffset: [0, anime.setDashoffset],
                easing: 'easeInOutSine',
                duration: 200,
                delay: (el, i) => { return i * 10 }
            }).finished;

            await Promise.all([eraseAnimation]);
        },
        drawSvg(){
            let svgPath;
            let color = ['#9696FF','#66F8F0','#FFDCFF','#FFFF96','#FAC8C8','#FF9DFF','#FF3232']
            
           
            this.pencil_effect.pause()
            this.pencil_effect.currentTime = 0
            this.pencil_effect.play()
            setTimeout(() => {
                this.pencil_effect.pause()
                this.pencil_effect.currentTime = 0
            }, 4000)

            let timerId = setInterval(() => {
                
                svgPath = document.querySelectorAll('g path');
                
                if(svgPath){
                    
                    this.svgLoaded = true;
                    clearInterval(timerId)
                    let tl = anime.timeline();

                    tl.add({
                        targets: svgPath,
                        strokeDashoffset: [anime.setDashoffset, 0],
                        easing: 'easeInOutSine',
                        add: '#f96',
                        duration: 200,
                        delay: (el, i) => { return i * 200 }
                    });
                    // for (let path of svgPath) {
                    //     tl.add({
                    //         targets: path,
                    //         strokeDashoffset: [anime.setDashoffset, 0],
                    //         easing: 'easeInOutSine',
                    //         add: '#f96',
                    //         duration: 200,
                    //         // delay: (el, i) => { return i * 1000 }
                    //     })
                    // }
                    for(let path of svgPath){
                        tl.add({
                            targets: path,
                            easing: 'easeInOutSine',
                            duration: 100,
                            fill: ['#FFF',color[Math.floor(Math.random() * color.length)]],
                        })
                    }

                }
            }, 100)
        },
        activeAnimation(){
            var pathEls = document.querySelectorAll('path');
            for (let i = 0; i < pathEls.length; i++) {
                var pathEl = pathEls[i];
         
                let offset = anime.setDashoffset(pathEl);
                pathEl.setAttribute('stroke-dashoffset', offset);
                anime({
                    targets: pathEl,
                    strokeDashoffset: [0,offset / 2],
                    duration: anime.random(1000, 3000),
                    delay: anime.random(0, 0),
                    loop: true,
                    direction: 'alternate',
                    easing: 'easeInOutSine',
                    autoplay: true
                });
            }
        },stopSpeech(){
            this.recognition.stop();
            this.isRecord=false;
        },activeSpeech(){
            this.recognition.lang='ko';
            this.recognition.start();
            var obj=this;
            this.isRecord=true;
            this.recognition.onresult = function(event) {
                    console.log('You said: ', event.results[0][0].transcript);
                    this.sttResult=event.results[0][0].transcript;
                    console.log(this.sttResult);
                    obj.DrawVoice(this.sttResult);
                    obj.isRecord=false;
                };
        },async DrawVoice(name){
            event.preventDefault();
            this.selected = true

            this.clearCanvas();
            this.src = name;
          
            Axios.get(`/translatetokor?text=${name}`)
            .then(({data}) => {
                this.srcText[name] = {}
                this.srcText[name]['translated'] = this.translated = this.src;
                this.src=data;
                console.dir(data);
                // document.getElementById('kr-btn').disabled = true
                // document.getElementById('kr-play').style.color = 'grey'
                // document.getElementById('eng-btn').disabled = true
                // document.getElementById('eng-play').style.color = 'grey'
                this.engAudioLoaded = false
                this.korAudioLoaded = false
                
                Axios.post(`/tts`,{
                    language : 'ko-KR',
                    text : this.translated
                })
                .then(({data}) => {
                    this.srcText[name]['korAudio'] =  this.korAudio = data
                    // this.korAudioLoaded = true;
                    this.checkKorAudioAvailable()
                })

                Axios.post(`/tts`,{
                    language : 'en-US',
                    text : this.src
                })
                .then(({data}) => {
                    this.srcText[name]['engAudio'] = this.engAudio = data
                    this.checkEngAudioAvailable()
                    // this.engAudioLoaded = true;
                })
            })
            .catch(() => {
                alert('error')
            })
        

            await this.eraseSvg()
            this.svgElement = null;
          
            try{
                this.stencils[this.src][0]
            }catch(error){
                error
                alert(this.src+"는 제공되는 그림이미지가 없습니다.");
                return;
            }
            Axios.get(this.stencils[this.src][0].src,
            {
                transformRequest : (data, headers) => {                    
                    delete headers.common['Authorization']
                    return JSON.stringify(data)
                }
            })
            .then(({data}) => {
                this.svgLoaded = false
                this.svgElement = data
            })
            .catch((error) => {
                error
            })
            // let image = new Image();
            // let icon = this.randomImg[name]
            // let imageWidth,imageHeight;
            // imageHeight = imageWidth = window.innerHeight * 0.6;
            
            // image.src = icon;
            // image.onload = () => this.canvas.getContext('2d').drawImage(image, 
            //             this.canvas.width / 2 - imageWidth / 2, this.canvas.height / 2 - imageHeight / 2, imageWidth, imageHeight);
            
        },
    },
    mounted(){
        this.loadStencils();
        
        let container = document.getElementsByClassName('canvas-wrapper')[0]
        this.pencil_effect = new Audio(`${this.$store.getters.BACKEND_URL}/audio/pencil_effect.mp3`)
      
        this.canvasWidth = window.innerWidth;
        this.canvasHeight = window.innerHeight * 0.8;
        let canvases = document.getElementsByTagName('canvas')
        canvases.forEach( (canvas) => {
            canvas.style.width = this.canvasWidth
            canvas.style.height = this.canvasHeight
        })
        this.suggestionWidth = this.suggestionHeight = window.innerWidth * 0.05
        // let suggestionImg = document.getElementsByClassName('suggestion-image')
        
        // suggestionImg.forEach((img) => {
            //     img.width = 40
        //     img.height = 40
        // })

        if(document.readyState == 'complete'){
            this.init()

        }else{
            window.onload = this.init
        }
        window.addEventListener('resize',() => {            
            
            let canvas = this.canvas || window.__canvas
            this.suggestionWidth = this.suggestionHeight = window.innerHeight * 0.1

            canvas.setWidth(window.innerWidth*0.9)
            canvas.setHeight(this.canvasHeight)
            canvas.calcOffset()
            canvases.forEach( (canvas) => {
                canvas.style.width = this.canvasWidth
                canvas.style.height = this.canvasHeight
            })
            
            this.canvasHeight = canvas.height
            this.canvasWidth = canvas.width
            
            //////////////////////
            let defaultContainer = document.getElementsByClassName('canvas-container')[0]
            defaultContainer.style.width =  container.style.width = canvas.width + 'px'
            defaultContainer.style.height = container.style.height = canvas.height + 'px'
            ////////////////////////////
            this.setSvgSize()
        })
        document.getElementById("colorheader").style.backgroundColor = this.brushcolor;
        
        this.isActiveBrushSize=false;
  },
  watch:{
      svgElement : function(){
          
          this.drawSvg()
      }
  }
}
</script>

<style scoped>

section {
  margin: auto;
  width: 80%;
  font-size: 100px;
  text-align: center;
}

.back_menu {
  display: flex;
  font-family: 'Jua', sans-serif;
  font-size: 40%;
  color:#ffba00;
}

.fixst{
    top: 20vh;
    position: fixed;
    width: 10vw;
    left: 80vw;
}
.fixst2{
    top: 20vh;
    position: fixed;
    width: 10vw;
    left: 73vw;
}
.exitbox{
    position: relative;
    left: 95vw;
    width: 4vw;
    margin: 0;
    height: 4vw;
    top: 75vh;
    border-radius: 50%;
}
.erasericon{
    position: absolute;
    background: aqua;
    width: 4vw;
    height: 4vw;
    max-width: 5vh;
    max-height: 5vh;
    border-radius: 50%;
    padding: 25%;
    /* line-height: 5vh; */
}
.slidecontainer {
    position: absolute;
    top: 80vh;
  width: 10%; /* Width of the outside container */
}

/* The slider itself */
.slider {
  -webkit-appearance: none;  /* Override default CSS styles */
  appearance: none;
  width: 10vw; /* Full-width */
  height: 25px; /* Specified height */
  background: #d3d3d3; /* Grey background */
  outline: none; /* Remove outline */
  opacity: 0.7; /* Set transparency (for mouse-over effects on hover) */
  -webkit-transition: .2s; /* 0.2 seconds transition on hover */
  transition: opacity .2s;
}

/* Mouse-over effects */
.slider:hover {
  opacity: 1; /* Fully shown on mouse-over */
}

/* The slider handle (use -webkit- (Chrome, Opera, Safari, Edge) and -moz- (Firefox) to override default look) */
.slider::-webkit-slider-thumb {
  -webkit-appearance: none; /* Override default look */
  appearance: none;
  width: 25px; /* Set a specific slider handle width */
  height: 25px; /* Slider handle height */
  background: #4CAF50; /* Green background */
  cursor: pointer; /* Cursor on hover */
}

.slider::-moz-range-thumb {
  width: 25px; /* Set a specific slider handle width */
  height: 25px; /* Slider handle height */
  background: #4CAF50; /* Green background */
  cursor: pointer; /* Cursor on hover */
}


.brushbox{
    position: relative;
    left: 95vw;
    width: 4vw;
    margin: 0;
    height: 4vw;
    max-width: 5vh;
    max-height: 5vh;
    z-index: 11;
    top: 10vw;
    border-radius: 50%;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}


.range-slider {
  width: 10vw;
}


.canvasdiv{
    position:absolute;
    top: 12vh;
    left: 2vw;
}

.canvas-wrapper{
    margin: auto;
    padding: auto;
    display: flex;
    position: absolute;
    background-size: 100% 100%;
}

.autodraw-results{
    height: 10vh;
    width: 100%;
    position: absolute;
    z-index: 10;
    top:0;
    display: flex;
    flex-wrap: wrap;
}

#drawing-mode {
  margin-bottom: 10px;
  vertical-align: top;
}
#drawing-mode-options {
  display: inline-block;
  vertical-align: top;
  margin-bottom: 10px;
  margin-top: 10px;
  background: #f5f2f0;
  padding: 10px;
}
label {
  display: inline-block; width: 130px;
}
.info {
  display: inline-block;
  width: 25px;
  background: #ffc;
}
#bd-wrapper {
  min-width: 1500px;
}

.wrapper{
    position: relative;
}

.btn-wrapper{
    position: absolute;
    bottom:5vh;
    width: 90%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: auto;
    height: 10vh;
    z-index: 2;
}
button{
    margin-left : 20px;
    margin-right : 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
}
.btn-wrapper * {
    margin : 0 10px;
    
}
.text{
    width: 80%;
    margin:0;
}
#svgWrapper {
    /* width:50%; */
    pointer-events: none; 
    position: absolute;

}

@media only screen and (max-width: 600px) {
    .wrapper{
        display: none;
    }
}

</style>