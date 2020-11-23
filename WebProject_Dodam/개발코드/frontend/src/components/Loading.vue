<template>
<div>
    <img id="js-rabbit" class="rabbit" src="../assets/images/rabbit_left.gif" />
    <img id="js-carrot" class="carrot" src="../assets/images/carrot.png" />
    
  <div v-if="loading" style="position:fixed; width: 100vw; height:100vh; top:0; left:0; z-index:10000;background-color:rgb(255, 247, 175);">
    
	<div class="container">
		<div class="dog">
			<div class="dog-head">
				<div class="dog-ears ears-left"></div>
				<div class="dog-ears ears-right"></div>
				<div class="dog-eyes"></div>
				<div class="dog-mouth">
					<div class="dog-nose"></div>
					<div class="dog-tongue"></div>
				</div>
			</div>
			<div class="dog-tail"></div>
			<div class="dog-body">
				<div class="dog-foot"></div>
			</div>
			<div class="ball">
                <div style="position: absolute; width:100px;top:120%; left:-250%">
                로딩중...
                </div>
            </div>
		</div>
	</div>
    
  </div>
    
</div>
</template>
<script>
 import $ from "jquery";
export default {
    
  data(){
    return {
      loading: true,
    timerId:null,
      timeOut:null,
    }
  },
  mounted() {
    $("html,body").stop().animate({scrollTop:0},1);

    //   var carrot = document.getElementById('js-carrot');
    //   var rabbit = document.getElementById('js-rabbit');
    document.addEventListener('click', this.onClick);
      setTimeout(() => {
          this.loading = false
      }, 300)
  },
  methods:{
    onClick(event){
      var carrot = document.getElementById('js-carrot');
      var rabbit = document.getElementById('js-rabbit');
      if(rabbit==null) return;
      var rabbitCur = document.getElementById('js-rabbit').getClientRects()[0];
      if( this.timeOut!=null){
        clearTimeout(this.timeOut);
      }
      if(this.timerId!=null){
        clearInterval(this.timerId);
        this.timerId=null;
      }
      var ratio=50;
    
      carrot.style.left=event.clientX-20+"px";
      carrot.style.top = event.clientY+window.pageYOffset-20+"px";    
      carrot.style.display="block";

      if(event.clientX-20-rabbitCur.left<0){
        rabbit.classList.remove('flip');
      }else{
        rabbit.classList.add('flip');
      }
      var left=(event.clientX-20-rabbitCur.left)/ratio;
      var top=(event.clientY-20-rabbitCur.top)/ratio;
      var cnt=1;       
      this.timerId = setInterval(() => 
      {
        rabbit.style.left=(rabbitCur.left+left*cnt)+"px";
        rabbit.style.top=(rabbitCur.top+window.pageYOffset+top*cnt)+"px";
        cnt+=1;
      }, 20);

      this.timeOut=setTimeout(() => {
         clearInterval(this.timerId); 
         this.timerId=null
         carrot.style.display="none";
         console.log()
       }, ratio*20+10);      
    }
  },
}
</script>
<style scoped>

@font-face {
    font-family: 'TmoneyRoundWindExtraBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#app {
  font-family: 'TmoneyRoundWindExtraBold'; 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#js-rabbit{
    width: 40px;
    height: 40px;
    position: absolute;
    z-index: 1000;
    left:10px;
    top:10px;

}
#js-carrot{
    width: 40px;
    height: 40px;
    position: absolute;
    z-index: 1000;
    left:10px;
    top:10px;
    display: none;
}
.flip{
  -webkit-transform: scaleX(-1);
  -moz-transform: scaleX(-1);
  -o-transform: scaleX(-1);
  transform: scaleX(-1);

  filter: FlipH;
  -ms-filter: "FlipH";
}





* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
body,
html {
	width: 100%;
	height: 100%;
}
.container {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}
.dog {
	width: 160px;
	height: 186px;
	position: relative;
}
@keyframes head {
	0% {
		transform: rotate(0deg);
	}
	30% {
		transform: rotate(0deg);
	}
	40% {
		transform: rotate(-20deg);
	}
	80% {
		transform: rotate(-20deg);
	}
	90% {
		transform: rotate(0deg);
	}
	100% {
		transform: rotate(0deg);
	}
}
.dog-head {
	width: 100px;
  height: 90px;
  position: absolute;
	z-index: 5;
  top: 30px;
  left: calc(50% - 50px);
  background-color: #efc092;
  border-radius: 50%;
	animation: head 2s linear infinite;
}
.dog-head:before,
.dog-head:after {
  content: "";
  width: 79px;
  height: 50px;
  position: absolute;
  z-index: 5;
  top: 44px;
  background-color: #efc092;
  border-radius: 50%;
}
.dog-head:before {
  left: -7px;
  transform: rotate(50deg);
}
.dog-head:after {
  right: -7px;
  transform: rotate(-49deg);
}
.dog-mouth {
	width: 60px;
  height: 35px;
  background-color: #fee2d2;
  position: absolute;
  bottom: -15px;
  left: 20px;
  z-index: 10;
  border-radius: 50%;
}
.dog-mouth:after {
	content: "";
	width: 10px;
  height: 40px;
  background-color: #fee2d2;
  position: absolute;
  bottom: 30px;
  left: 24px;
  z-index: 10;
  border-radius: 20px;
}
.dog-nose {
	width: 16px;
	height: 10px;
	position: absolute;
	top: 8px;
	left: calc(50% - 8px);
	background-color: #000;
	border-radius: 50%;
}
@keyframes tongue {
	0% {
		height: 10px;
	}
	30% {
		height: 10px;
	}
	40% {
		height: 20px;
	}
	60% {
		height: 10px;
	}
	80% {
		height: 20px;
	}
	90% {
		height: 10px;
	}
	100% {
		height: 10px;
	}
}
.dog-tongue {
  width: 20px;
  height: 10px;
  position: absolute;
  top: 24px;
  left: calc(50% - 10px);
  background-color: #f4a4ad;
  border-radius: 0 0 10px 10px;
	animation: tongue 1.3s linear infinite;
}
.dog-eyes {
	width: 10px;
  height: 16px;
  background-color: #000;
  position: absolute;
  top: 52px;
  left: 28px;
  border-radius: 50%;
  z-index: 10;
  box-shadow: 33px 0px 0px #000;
}
.dog-eyes:after {
  content: "";
  width: 92px;
  height: 50px;
  position: absolute;
  top: -50px;
  left: -24px;
  background-color: #efc092;
  border-radius: 50%;
}
.dog-ears {
  width: 80px;
	height: 50px;
	position: absolute;
	z-index: 0;
	top: -2px;
	border-radius: 150px 0 150px 0;
	background-color: #efc092;
}
.dog-ears:after {
	content: "";
  width: 50px;
	height: 30px;
	position: absolute;
	z-index: 0;
	top: 8px;
	left: 20px;
	border-radius: 150px 0 150px 0;
	transform: rotate(-176deg);
	background-color: #fee2d2;
}
.ears-left {
	left: -22px;
	transform: rotate(-105deg);
}
.ears-right {
	right: -22px;
	transform: rotate(-22deg);
}
.dog-body {
  width: 54px;
  height: 60px;
  position: absolute;
  top: 120px;
  left: calc(50% - 35px);
  background-color: #d58b4e;
  border-radius: 49px 0 0 20px;
}
.dog-body:before {
  content: "";
  width: 50px;
  height: 60px;
  position: absolute;
  top: 3px;
  left: calc(50% - 17px);
  background-color: #efbf8e;
  border-radius: 100px 100px 0 0;
}
.dog-body:after {
  content: "";
  width: 15px;
  height: 30px;
  position: absolute;
  top: 32px;
  left: 28px;
  background-color: #d58b4e;
  border-radius: 100px 100px 0 0;
}
.dog-foot {
	width: 20px;
  height: 13px;
  position: absolute;
  z-index: 5;
  bottom: -3px;
  left: 8px;
  background-color: #fce2d3;
  border-radius: 10px 10px 0 0;
	box-shadow: 33px 0 0 #fce2d3;
}
@keyframes tail {
    0% {
    transform: rotate(-47deg);
    }
    100% {
    transform: rotate(-57deg);
    }
}
.dog-tail {
	width: 10px;
  height: 50px;
  position: absolute;
  top: 120px;
  left: calc(50% - 45px);
  background-color: #ecc093;
  transform: rotate(-47deg);
  border-radius: 10px 0 0 0px / 50px 0 0 0px;
	animation: tail .026s infinite alternate;
}
.ball {
	width: 30px;
	height: 30px;
	position: absolute;
	bottom: 0;
	right: 0px;
	border-radius: 50%;
	background-color: #6E64F0;
}

</style>