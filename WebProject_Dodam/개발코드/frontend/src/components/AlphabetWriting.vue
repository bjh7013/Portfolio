<template>
  <div>
    <div class="canvas-container" style="user-select: none; position:relative;">
      <canvas ref="c" id="c" style="position:relative; touch-action: none; user-select: none;" class="lower-canvas"></canvas>
    </div>
  </div>
</template>

<script>
// import Axios from 'axios';
import {fabric} from 'fabric';
import Axios from 'axios';

export default {
    props:['letter'],
    data(){
			return{
				stopAnimation:false,
				canvas: null,
				scaleX: null,
				scaleY: null,
				isPressing: false,
				path:[],
				aniPath:[],
				readyAniPath : false,
				pointSize: 30,
				canvasWidth : 800,
				canvasHeight : 800,
				curPathIdx : 0,
				lastPointIdx : -1,
				isComplete : false,
				isFail : false,
				imageLoaded : false,
				curveLetters: ['ㅇ','ㅎ', 'ㅈ', 'ㅊ', 'ㅉ','가','아','사','자','차','카','하','값','몫','H','F'],
				faster:['ㅇ','ㅎ','아','하']
			}
    },
    methods:{
			validate(pathIdx, pointIdx){
				pointIdx = Number(pointIdx)
				if(pathIdx != this.curPathIdx || (pointIdx != this.lastPointIdx + 1 && pointIdx != this.lastPointIdx)){
					this.isFail = true
					return
				}
				if(pointIdx == this.path[this.curPathIdx].length - 1){//해당 획의 핀포인트들을 다 방문한 경우
                    this.isComplete = true    

				}
				this.lastPointIdx = pointIdx
				return
			},
			mouseUp(){
				this.isPressing = this.isFail = false
				if(this.isComplete){
					this.curPathIdx++

					if(this.curPathIdx == this.path.length){
						new Audio(`${this.$store.getters.BACKEND_URL}/letter/finish.mp3`).play()
						this.sendSuccess()
                            var url= location.pathname.split("/")[2];
                            Axios.post("/koreandetail/submit",{
								"koreandetailNo":url,
                                "correct":true,
                                "studentNo":this.$store.getters.userInfo.userNo,
                            }).then((response)=>{
								response
                            }).catch((err)=>{
								err
                            })
					}else{
						new Audio(`${this.$store.getters.BACKEND_URL}/letter/next_path.mp3`).play()
						this.sendNext()
						this.setPath()
					}
				}else{
					let timerId = null
					this.sendFail()
					new Audio(`${this.$store.getters.BACKEND_URL}/letter/wrong.mp3`).play()
					this.lastPointIdx = -1
					timerId = setInterval(()=>{
						if(this.canvas._objects.length > 1){
							this.undo()
							clearInterval(timerId)
							this.showAnimation()
						}
					},100)
				}
			},
			mouseDown(){
				this.isPressing = true
			},
			setEventHandlers(){
				let canvasContainer = document.getElementsByClassName('canvas-container')[0]
				///////////////////////////////////////////////Bubling을 활용해 canvas와 div태그로부터 전파되는 이벤트 감지
				canvasContainer.addEventListener('mousedown', this.mouseDown)
				canvasContainer.addEventListener('touchstart', this.mouseDown)
				canvasContainer.addEventListener('mouseup', this.mouseUp)
				canvasContainer.addEventListener('touchend', this.mouseUp)
			},
			setPath(){
				let move = (e) => {
					e.preventDefault();
					let loc, target;
					target = e.target
					if(e.type == 'touchmove'){
						loc = e.targetTouches[0];
						target = document.elementFromPoint(loc.clientX, loc.clientY)
					}
					let id = target.id.split('-');
					let i = id[1]
					if(!i){
						return
					}
					if(this.isPressing && !this.isFail){
						this.validate(pathIdx, i)
					}
					let nEvent = null
					if(e.type == 'mousemove'){
						nEvent = new MouseEvent(e.type, e)
					}
					if(e.type == 'touchmove'){
						nEvent = new TouchEvent(e.type, e)
					}
					upperCanvas.dispatchEvent(nEvent)
				};

				let down  = (e) => {
					e.preventDefault();
					let loc, target;
					target = e.target
					if(e.type == 'toucdown'){
						loc = e.targetTouches[0];
						target = document.elementFromPoint(loc.clientX, loc.clientY)
					}
					let id = target.id.split('-')
					let i = id[1]
					if(!i){
						return
					}
					if(i == 0){
						this.validate(pathIdx, i)
					}
					let nEvent = null
					if(e.type == 'mousedown'){
						nEvent = new MouseEvent(e.type, e)
					}
					if(e.type == 'touchstart'){
						nEvent = new TouchEvent(e.type, e)
					}
					upperCanvas.dispatchEvent(nEvent)
				};
				document.getElementsByClassName('point').forEach( (div) => {
					div.style.pointerEvents = 'none'
				})
				this.lastPointIdx = -1
				this.isComplete = false
				let canvasContainer = document.getElementsByClassName('canvas-container')[0]
				let upperCanvas = document.getElementsByClassName('upper-canvas')[0]
				let color = ['red', 'blue', 'green', 'black']
				let pathIdx = this.curPathIdx;	
				this.path[this.curPathIdx].forEach( (point, i) => {
					let div = document.createElement('div')
					div.classList.add('point')
					div.id = this.curPathIdx + '-' + i
					div.style.position = 'absolute';
					div.style.width = this.pointSize + 'px'
					div.style.height = this.pointSize + 'px'
					div.style.cursor = 'crosshair'
					//////////////////////////////////////
					div.style.backgroundColor = color[i % 4]
					///////////////////////////////////////
					div.style.left =  point[0] * this.scaleX - (this.pointSize / 2) + 'px'
					div.style.top =  point[1] * this.scaleY - (this.pointSize / 2) + 'px'		
					div.addEventListener('mousemove', move)
					div.addEventListener('touchmove', move)
					div.addEventListener('mousedown', down)
					div.addEventListener('touchstart', down)
					canvasContainer.appendChild(div)
				} )
				let timerId = setInterval(() => {
					if(this.imageLoaded){
						clearInterval(timerId)
						this.showAnimation()
					}
				})
			},
			initAnimation(){
				var lastTime = 0;
				var vendors = ['ms', 'moz', 'webkit', 'o'];
				for (var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
					window.requestAnimationFrame = window[vendors[x] + 'RequestAnimationFrame'];
					window.cancelAnimationFrame = window[vendors[x] + 'CancelAnimationFrame'] || window[vendors[x] + 'CancelRequestAnimationFrame'];
				}
				if (!window.requestAnimationFrame) window.requestAnimationFrame = function (callback) {
					var currTime = new Date().getTime();
					var timeToCall = Math.max(0, 16 - (currTime - lastTime));
					var id = window.setTimeout(function () {
							callback(currTime + timeToCall);
					},
					timeToCall);
					lastTime = currTime + timeToCall;
					return id;
				};
				if (!window.cancelAnimationFrame) window.cancelAnimationFrame = function (id) {
					clearTimeout(id);
				};
			},
			undo(){
				this.canvas._objects.pop()
				this.canvas.renderAll()
			},
			showAnimation(){
				var ctx = this.canvas.getContext("2d");
				ctx.lineCap = "round";	
				let pathForAnimation = null
				if(this.curveLetters.includes(this.letter)){
						pathForAnimation = this.aniPath
				}else{
						pathForAnimation = this.path
				}
				// define the path to plot
				var vertices = [];
				pathForAnimation[this.curPathIdx].forEach( (point) => {
					vertices.push({
						x : point[0] * this.scaleX,
						y : point[1] * this.scaleY
					})
				})
				// set some style
				ctx.lineWidth = 5;
				ctx.strokeStyle = "blue";
				// calculate incremental points along the path
				var frame = 10
				var points = null
				if(this.curveLetters.includes(this.letter)){
					points = vertices
				}else{
					points = calcWaypoints(vertices);
				}
				// variable to hold how many frames have elapsed in the animation
				var t = this.faster.includes(this.letter) ? 7 : 3;
				let step = t;
				
			
				
				function canvas_arrow(context, fromx, fromy, tox, toy) {
					var headlen = 10; // length of head in pixels
					var dx = tox - fromx;
					var dy = toy - fromy;

					var angle = Math.atan2(dy, dx);
					
					context.moveTo(fromx, fromy);
					context.lineTo(tox, toy);
					context.lineTo(tox - headlen * Math.cos(angle - Math.PI / 4), toy - headlen * Math.sin(angle - Math.PI / 4));
					context.moveTo(tox, toy);
					context.lineTo(tox - headlen * Math.cos(angle + Math.PI / 4), toy - headlen * Math.sin(angle + Math.PI / 4));
				}
				
				// extend the line from start to finish with animation
				let animate = () => {
					if (t < points.length - 1) {
						requestAnimationFrame(animate);
					}else{
						ctx.beginPath();
						canvas_arrow(ctx,points[points.length - 2].x,
										points[points.length - 2].y,
										points[points.length - 1].x, 
										points[points.length - 1].y);
						
						ctx.stroke();
						return
					}
					// draw a line segment from the last waypoint
					// to the current waypoint
					ctx.beginPath();
					ctx.moveTo(points[t - step].x, points[t - step].y);
					ctx.lineTo(points[t].x, points[t].y);
					ctx.stroke();
					// increment "t" to get the next waypoint
					t += step;
				}
				animate(points);

				
				

				// calc waypoints traveling along vertices
				function calcWaypoints(vertices) {
					// var waypoints = [];
					var waypoints = [];
					for (var i = 1; i < vertices.length; i++) {
						var pt0 = vertices[i - 1];
						var pt1 = vertices[i];
						var dx = pt1.x - pt0.x;
						var dy = pt1.y - pt0.y;
						for (var j = 0; j < frame; j++) {
							var x = pt0.x + dx * j / frame;
							var y = pt0.y + dy * j / frame;
							waypoints.push({
								x: x,
								y: y
							});
						}
					}
					return (waypoints);
				}	
			},
			setImage(){
				this.canvas.clear()
				let letter = this.$route.query.letter || this.letter
				let uri = encodeURI(`letter/${letter}`)
				let aniUri = encodeURI(`letter/ani_${letter}`)
				if(this.curveLetters.includes(this.letter)){
					Axios.get(`${this.$store.getters.BACKEND_URL}/${aniUri}.json`)
					.then(({data}) => {
						this.aniPath = data.path
					})
				}
				Axios.get(`${this.$store.getters.BACKEND_URL}/${uri}.json`)
				.then(({data})=>{	
					this.scaleX = this.canvasWidth / data.imgWidth
					this.scaleY = this.canvasHeight / data.imgHeight
					fabric.Image.fromURL(`${this.$store.getters.BACKEND_URL}/${uri}.png`, (img) =>{
						img.set({
							left:0,
							top: 0,
							scaleX: this.scaleX,
							scaleY: this.scaleY
						})
						this.canvas.add(img)
						this.imageLoaded = true
					})
					
					this.path = data.path
					this.curPathIdx = 0;
				})
				.catch(() => {
					alert('error')
				})
				let timerId = null
				timerId = setInterval(()=>{
					if(this.curveLetters.includes(this.letter)){
						if(this.path.length > 0 && this.aniPath.length > 0){
							clearInterval(timerId)
							this.setPath()
							}
					}else{
						if(this.path.length > 0){          
							clearInterval(timerId)
							this.setPath()
						}
					}
				},200)
			},
			init(){
				this.prepareCanvas();
				this.canvas = window.__canvas;
				let container = document.getElementsByClassName('canvas-container')[0]
				container.style.width = this.canvasWidth + 'px'
				container.style.height = this.canvasHeight + 'px'
				this.setEventHandlers();
				this.initAnimation();
				this.setImage()
			},
			prepareCanvas() {
				let canvasHeight = this.canvasHeight
				let canvasWidth = this.canvasWidth
				var canvas = window.__canvas = new fabric.Canvas('c', {
					isDrawingMode: true,
					width: canvasWidth,
					height: canvasHeight
				});
				canvas.freeDrawingBrush.width = 10;
				canvas.freeDrawingBrush.color = 'green'
				fabric.Object.prototype.transparentCorners = false;
				var canvases = window.__canvases || window.canvases;
				canvas && canvas.calcOffset && canvas.calcOffset();
				if (canvases && canvases.length) {
					for (var i = 0, len = canvases.length; i < len; i++) {
						canvases[i].calcOffset();
					}
				}
		},
		sendSuccess(){
			this.$emit('success',1);
		},
		sendNext(){
			this.$emit('success',2);
		},
		sendFail(){
			this.$emit('success',3);
		}    
  },
  watch:{
		aniPath: function(){
			this.readyAniPath = true
		}
  },
  mounted(){
	this.letter = this.$route.query.letter || this.letter
	var winh = window.innerHeight;
	var winw = window.innerWidth;
	if(winw < 600){
		this.canvasWidth = this.canvasHeight = winw * 0.80;
	}
	// else if(winh>winw){
	// 		this.canvasWidth = this.canvasHeight = winh * 0.58;
	// } 
	else {
		this.canvasWidth = this.canvasHeight = winh * 0.58;
	}
		
    this.pointSize = window.innerHeight / 20;
    if(document.readyState == 'complete'){
      this.init()
    }else{
      window.onload = this.init
	}
	this.$emit('speak')
  },
//   watch:{
//       letter: function(){
//           this.setImage();
//       }
//   }
}
</script>

<style>
.canvas-container{
  margin: auto;
  padding: auto;
  display: flex;
}
.point{
  opacity: 0;
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

</style>