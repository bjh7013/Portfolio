<template>
    <div class="recorder">
        <!-- <canvas class="canvas"></canvas> -->
        <!-- <div class="copy"> -->
        <div ref="record" @click="voiceMode" class="fire"><i  class="fa fa-microphone" aria-hidden="true"></i></div>
        <!-- </div> -->
        <div ref="replay" v-if="replay" class="replay" @click="replayVoice"><i class="fa fa-volume-up" aria-hidden="true"></i></div>     
    </div>
</template>

<script>
export default {
    props: {
        idx: Number,
        setAudio: Object,
    },
    data(){
        return {
            audio: null,
            audioUrl:null,
            mediaRecorder : null,
            audioChunks: [],
            isRecording: false,
            replay:false,
            base64data:null
        }
    },
    methods:{
        voiceMode(){
            this.$emit('voice-mode')
        },
        hasGetUserMedia() {
            return !!(navigator.getUserMedia || navigator.webkitGetUserMedia ||
                        navigator.mozGetUserMedia || navigator.msGetUserMedia);
        },
        replayVoice(){
            this.audio.play()
        }
    },
    watch:{
        base64data: function(){
            this.$emit('audio-change',this.idx, this.base64data)
        },
        audio: function(){
            this.replay = true;
        },
        setAudio: function(){
            this.audio = this.setAudio
            this.replay = true
        }
    },
    mounted(){
        if(this.setAudio){
            this.replay = true
            this.audio = this.setAudio
        }
        if (this.hasGetUserMedia()) {
            // Good to go!
        } else {
            alert('getUserMedia() is not supported in your browser');
        }

        // Not showing vendor prefixes.
        navigator.mediaDevices.getUserMedia({audio: true}) 
        .then(stream => {
            this.mediaRecorder = new MediaRecorder(stream);
            
            this.mediaRecorder.addEventListener("dataavailable", event => {
                this.audioChunks.push(event.data);
            });

            this.mediaRecorder.addEventListener("stop", () => {
                let audioBlob = new Blob(this.audioChunks, {type:'audio/mp3'});
                
                this.audioUrl = URL.createObjectURL(audioBlob);
                this.audio = new Audio(this.audioUrl)
                    
                
                var reader = new FileReader();
                reader.readAsDataURL(audioBlob); 
               
                
                reader.onloadend = () => {
                    this.base64data = reader.result;
                }
            });
        })
        var vText = this.$refs.record
        var vClix = vText

        const vRespondClick = () => {
            if(!this.isRecording){
                this.audioChunks = []
                vText.innerHTML = '<i class="fa fa-stop" aria-hidden="true"></i>';
                // vSpeed = 420;
                // vPhase = 35;
                
                this.isRecording = true;
                this.mediaRecorder.start();
            }else{
                vText.innerHTML = '<i  class="fa fa-microphone" aria-hidden="true"></i>';              
                // vSpeed = 11000;
                // vPhase = 1;
                
                this.isRecording = false;
                this.mediaRecorder.stop();
            }

        }

        
        vClix.addEventListener('click',vRespondClick);

        // for (var idx = 0; idx <= gradients.length - 1; idx++) {
        
        //     let swingpoints = [];
        //     let radian = 0;

        //     for (var i = 0; i < points; i++){
        //         radian = pi * 2 / points * i;
        //         var ptX = center.x + radius * Math.cos(radian);
        //         var ptY = center.y + radius * Math.sin(radian);
        //         swingpoints.push({ 
        //             x: ptX,
        //             y: ptY,
        //             radian: radian,
        //             range: random(rangeMin, rangeMax),
        //             phase: 0 
        //         });
        //     }    
        //     circles.push(swingpoints);

        // }

        // function swingCircle() {
        //     ctx.clearRect(0, 0, w * dpr, h * dpr);
            
        //     ctx.globalAlpha = 1;
        //     // ctx.globalCompositeOperation = 'source-over';
        //     ctx.globalCompositeOperation = 'screen';
            
        //     for (let k = 0; k < circles.length; k++) {
        //         let swingpoints = circles[k];
            
        //         for (var i = 0; i < swingpoints.length; i++){
        //             swingpoints[i].phase += random(1, vPhase) * -0.01;
                    
        //             let phase = 4 * Math.sin(tick / 65);
                    
        //             if (mouseY !== 0) {
        //                 phase = mouseY / 200 + 1;
        //             }
                    
        //             var r = radius + (swingpoints[i].range * phase * Math.sin(swingpoints[i].phase)) - rangeMax;
                    
        //             swingpoints[i].radian += pi / vSpeed;
                    
        //             var ptX = center.x + r * Math.cos(swingpoints[i].radian);
        //             var ptY = center.y + r * Math.sin(swingpoints[i].radian);

                    
        //             if (showPoints === true) {
        //                 ctx.strokeStyle = '#96fbc4';

        //                 ctx.beginPath();
        //                 ctx.arc(ptX, ptY, 2 * dpr, 0, pi * 2, true);
        //                 ctx.closePath();
        //                 ctx.stroke();
        //             }
                        
        //             swingpoints[i] = {
        //                 x: ptX,
        //                 y: ptY,
        //                 radian: swingpoints[i].radian,
        //                 range: swingpoints[i].range,
        //                 phase: swingpoints[i].phase,
        //             };
        //         }

        //         const fill = gradients[k];
        //         drawCurve(swingpoints, fill);
            
        //     }
            
        //     tick++;
            
        //     requestAnimationFrame(swingCircle);
        // }

        // requestAnimationFrame(swingCircle);


        // function drawCurve(pts, fillStyle) {
        //     ctx.fillStyle = fillStyle;
        //     ctx.beginPath();
        //     ctx.moveTo(
        //         (pts[cycle( - 1, points)].x + pts[0].x) / 2,
        //         (pts[cycle( - 1, points)].y + pts[0].y) / 2);
        //     for (var i = 0; i < pts.length; i++){
            
        //         ctx.quadraticCurveTo(
        //             pts[i].x,
        //             pts[i].y,
        //             (pts[i].x + pts[cycle(i + 1, points)].x) / 2,
        //             (pts[i].y + pts[cycle(i + 1, points)].y) / 2);
        //     }
        
        //     ctx.closePath();
        //     ctx.fill();

        // }

        // function cycle( num1, num2 ) {
        //     return ( num1 % num2 + num2 ) % num2;
            
        // }

        // function random (num1, num2) {
        //     var max = Math.max(num1, num2);
        //     var min = Math.min(num1, num2);
        //     return Math.floor(Math.random() * (max - min + 1)) + min;
        // }
    }
}
</script>
<style scoped>

.recorder{
    position: relative;
    display: flex; 
    align-items: center;
    justify-content: center;
    /* // background-image: linear-gradient(-20deg, #2b5876 0%, #4e4376 100%);
    // background-image: linear-gradient(to top, #d299c2 0%, #fef9d7 100%);
    // background-image: linear-gradient(to top, #9795f0 0%, #fbc8d4 100%);
    // background-image: linear-gradient(-20deg, #ddd6f3 0%, #faaca8 100%, #faaca8 100%); */
    /* background-image: linear-gradient(to right, #ffc3a0 0%, #ffafbd 100%); */
    cursor: pointer;
    
}
.recorder div {
    margin : 10px;
}

canvas {
    height: 50px !important;
    width: 50px !important;
}

.copy {
    position: absolute;
    top: 25%;
    left: 30%;
    width: 40%;
    height: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    pointer-events: none;
    z-index: 100;
}
    
h1 {
    word-wrap: break-word;
    margin: 1;
    color: #6f86d6;
    letter-spacing: 1px;
    font-size: 10px;
    font-weight: 100;
    font-family: 'Futura', 'Helvetica Neue', Helvetica;
    background: linear-gradient(to top, #48c6ef 0%, #6f86d6 100%);
    background: -moz-linear-gradient(transparent, transparent);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;

}
</style>