(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1b57ee4e"],{d2f8:function(t,e,n){"use strict";var a=n("fc43"),i=n.n(a);i.a},fb03:function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("div",{staticClass:"canvas-container",staticStyle:{"user-select":"none",position:"relative"}},[n("canvas",{ref:"c",staticClass:"lower-canvas",staticStyle:{position:"relative","touch-action":"none","user-select":"none"},attrs:{id:"c"}})])])},i=[],s=(n("99af"),n("4160"),n("caad"),n("a9e3"),n("ac1f"),n("2532"),n("1276"),n("159b"),n("7a94")),o=n("bc3a"),r=n.n(o),c={props:["letter"],data:function(){return{stopAnimation:!1,canvas:null,scaleX:null,scaleY:null,isPressing:!1,path:[],aniPath:[],readyAniPath:!1,pointSize:30,canvasWidth:800,canvasHeight:800,curPathIdx:0,lastPointIdx:-1,isComplete:!1,isFail:!1,imageLoaded:!1,curveLetters:["ㅇ","ㅎ","ㅈ","ㅊ","ㅉ","가","아","사","자","차","카","하","값","몫","H","F"],faster:["ㅇ","ㅎ","아","하"]}},methods:{validate:function(t,e){e=Number(e),t!=this.curPathIdx||e!=this.lastPointIdx+1&&e!=this.lastPointIdx?this.isFail=!0:(e==this.path[this.curPathIdx].length-1&&(this.isComplete=!0),this.lastPointIdx=e)},mouseUp:function(){var t=this;if(this.isPressing=this.isFail=!1,this.isComplete)if(this.curPathIdx++,this.curPathIdx==this.path.length){new Audio("".concat(this.$store.getters.BACKEND_URL,"/letter/finish.mp3")).play(),this.sendSuccess();var e=location.pathname.split("/")[2];r.a.post("/koreandetail/submit",{koreandetailNo:e,correct:!0,studentNo:this.$store.getters.userInfo.userNo}).then((function(t){})).catch((function(t){}))}else new Audio("".concat(this.$store.getters.BACKEND_URL,"/letter/next_path.mp3")).play(),this.sendNext(),this.setPath();else{var n=null;this.sendFail(),new Audio("".concat(this.$store.getters.BACKEND_URL,"/letter/wrong.mp3")).play(),this.lastPointIdx=-1,n=setInterval((function(){t.canvas._objects.length>1&&(t.undo(),clearInterval(n),t.showAnimation())}),100)}},mouseDown:function(){this.isPressing=!0},setEventHandlers:function(){var t=document.getElementsByClassName("canvas-container")[0];t.addEventListener("mousedown",this.mouseDown),t.addEventListener("touchstart",this.mouseDown),t.addEventListener("mouseup",this.mouseUp),t.addEventListener("touchend",this.mouseUp)},setPath:function(){var t=this,e=function(e){var n,a;e.preventDefault(),a=e.target,"touchmove"==e.type&&(n=e.targetTouches[0],a=document.elementFromPoint(n.clientX,n.clientY));var s=a.id.split("-"),r=s[1];if(r){t.isPressing&&!t.isFail&&t.validate(o,r);var c=null;"mousemove"==e.type&&(c=new MouseEvent(e.type,e)),"touchmove"==e.type&&(c=new TouchEvent(e.type,e)),i.dispatchEvent(c)}},n=function(e){var n,a;e.preventDefault(),a=e.target,"toucdown"==e.type&&(n=e.targetTouches[0],a=document.elementFromPoint(n.clientX,n.clientY));var s=a.id.split("-"),r=s[1];if(r){0==r&&t.validate(o,r);var c=null;"mousedown"==e.type&&(c=new MouseEvent(e.type,e)),"touchstart"==e.type&&(c=new TouchEvent(e.type,e)),i.dispatchEvent(c)}};document.getElementsByClassName("point").forEach((function(t){t.style.pointerEvents="none"})),this.lastPointIdx=-1,this.isComplete=!1;var a=document.getElementsByClassName("canvas-container")[0],i=document.getElementsByClassName("upper-canvas")[0],s=["red","blue","green","black"],o=this.curPathIdx;this.path[this.curPathIdx].forEach((function(i,o){var r=document.createElement("div");r.classList.add("point"),r.id=t.curPathIdx+"-"+o,r.style.position="absolute",r.style.width=t.pointSize+"px",r.style.height=t.pointSize+"px",r.style.cursor="crosshair",r.style.backgroundColor=s[o%4],r.style.left=i[0]*t.scaleX-t.pointSize/2+"px",r.style.top=i[1]*t.scaleY-t.pointSize/2+"px",r.addEventListener("mousemove",e),r.addEventListener("touchmove",e),r.addEventListener("mousedown",n),r.addEventListener("touchstart",n),a.appendChild(r)}));var r=setInterval((function(){t.imageLoaded&&(clearInterval(r),t.showAnimation())}))},initAnimation:function(){for(var t=0,e=["ms","moz","webkit","o"],n=0;n<e.length&&!window.requestAnimationFrame;++n)window.requestAnimationFrame=window[e[n]+"RequestAnimationFrame"],window.cancelAnimationFrame=window[e[n]+"CancelAnimationFrame"]||window[e[n]+"CancelRequestAnimationFrame"];window.requestAnimationFrame||(window.requestAnimationFrame=function(e){var n=(new Date).getTime(),a=Math.max(0,16-(n-t)),i=window.setTimeout((function(){e(n+a)}),a);return t=n+a,i}),window.cancelAnimationFrame||(window.cancelAnimationFrame=function(t){clearTimeout(t)})},undo:function(){this.canvas._objects.pop(),this.canvas.renderAll()},showAnimation:function(){var t=this,e=this.canvas.getContext("2d");e.lineCap="round";var n=null;n=this.curveLetters.includes(this.letter)?this.aniPath:this.path;var a=[];n[this.curPathIdx].forEach((function(e){a.push({x:e[0]*t.scaleX,y:e[1]*t.scaleY})})),e.lineWidth=5,e.strokeStyle="blue";var i=10,s=null;s=this.curveLetters.includes(this.letter)?a:l(a);var o=this.faster.includes(this.letter)?7:3,r=o;function c(t,e,n,a,i){var s=10,o=a-e,r=i-n,c=Math.atan2(r,o);t.moveTo(e,n),t.lineTo(a,i),t.lineTo(a-s*Math.cos(c-Math.PI/4),i-s*Math.sin(c-Math.PI/4)),t.moveTo(a,i),t.lineTo(a-s*Math.cos(c+Math.PI/4),i-s*Math.sin(c+Math.PI/4))}var h=function t(){if(!(o<s.length-1))return e.beginPath(),c(e,s[s.length-2].x,s[s.length-2].y,s[s.length-1].x,s[s.length-1].y),void e.stroke();requestAnimationFrame(t),e.beginPath(),e.moveTo(s[o-r].x,s[o-r].y),e.lineTo(s[o].x,s[o].y),e.stroke(),o+=r};function l(t){for(var e=[],n=1;n<t.length;n++)for(var a=t[n-1],s=t[n],o=s.x-a.x,r=s.y-a.y,c=0;c<i;c++){var h=a.x+o*c/i,l=a.y+r*c/i;e.push({x:h,y:l})}return e}h(s)},setImage:function(){var t=this;this.canvas.clear();var e=this.$route.query.letter||this.letter,n=encodeURI("letter/".concat(e)),a=encodeURI("letter/ani_".concat(e));this.curveLetters.includes(this.letter)&&r.a.get("".concat(this.$store.getters.BACKEND_URL,"/").concat(a,".json")).then((function(e){var n=e.data;t.aniPath=n.path})),r.a.get("".concat(this.$store.getters.BACKEND_URL,"/").concat(n,".json")).then((function(e){var a=e.data;t.scaleX=t.canvasWidth/a.imgWidth,t.scaleY=t.canvasHeight/a.imgHeight,s["fabric"].Image.fromURL("".concat(t.$store.getters.BACKEND_URL,"/").concat(n,".png"),(function(e){e.set({left:0,top:0,scaleX:t.scaleX,scaleY:t.scaleY}),t.canvas.add(e),t.imageLoaded=!0})),t.path=a.path,t.curPathIdx=0})).catch((function(){alert("error")}));var i=null;i=setInterval((function(){t.curveLetters.includes(t.letter)?t.path.length>0&&t.aniPath.length>0&&(clearInterval(i),t.setPath()):t.path.length>0&&(clearInterval(i),t.setPath())}),200)},init:function(){this.prepareCanvas(),this.canvas=window.__canvas;var t=document.getElementsByClassName("canvas-container")[0];t.style.width=this.canvasWidth+"px",t.style.height=this.canvasHeight+"px",this.setEventHandlers(),this.initAnimation(),this.setImage()},prepareCanvas:function(){var t=this.canvasHeight,e=this.canvasWidth,n=window.__canvas=new s["fabric"].Canvas("c",{isDrawingMode:!0,width:e,height:t});n.freeDrawingBrush.width=10,n.freeDrawingBrush.color="green",s["fabric"].Object.prototype.transparentCorners=!1;var a=window.__canvases||window.canvases;if(n&&n.calcOffset&&n.calcOffset(),a&&a.length)for(var i=0,o=a.length;i<o;i++)a[i].calcOffset()},sendSuccess:function(){this.$emit("success",1)},sendNext:function(){this.$emit("success",2)},sendFail:function(){this.$emit("success",3)}},watch:{aniPath:function(){this.readyAniPath=!0}},mounted:function(){this.letter=this.$route.query.letter||this.letter;var t=window.innerHeight,e=window.innerWidth;this.canvasWidth=this.canvasHeight=e<600?.8*e:.58*t,this.pointSize=window.innerHeight/20,"complete"==document.readyState?this.init():window.onload=this.init,this.$emit("speak")}},h=c,l=(n("d2f8"),n("2877")),u=Object(l["a"])(h,a,i,!1,null,null,null);e["default"]=u.exports},fc43:function(t,e,n){}}]);
//# sourceMappingURL=chunk-1b57ee4e.da20c235.js.map