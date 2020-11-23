<template>
    <div class="wrapper">
        <canvas ref="c" id="c" width="600px" height="600px" style="position:relative; border: 1px solid rgb(170, 170, 170);  touch-action: none; user-select: none;" class="lower-canvas"></canvas>
        <input style="background-color: white" type="text" v-model="letter">
        <button @click="getLetterImg">get letter image</button>
        <br>
        <button @click="curveLetter">to JSON</button>
        <textarea v-model="pathString" rows="5" cols="50"></textarea>
        <br>
        <button @click="pathString = '[\n['">초기화</button>
        <br>
        <button style="background-color: white" @click="next">다음 획</button>
        
        <button style="margin-left: 20px;background-color: white" @click="submit">제출</button>
    </div>
</template>

<script>
import {fabric} from 'fabric';
import Axios from 'axios';
export default {
    data(){
        return {
            letter: '',
            pathString: '[\n[',
            curPathIdx: 0,
            imgWidth:0,
            imgHeight :0,
            imgName: '',
        }
    },
    methods: {
        curveLetter(){
            this.pathString = '[\n['
            
            for(let i = 1; i < window.__canvas._objects.length; i++){
                window.__canvas._objects[i].path.forEach( (p) => {
                    this.pathString += `[${p[1]},${p[2]}],`
                })
                this.pathString = this.pathString.slice(0, -1)
                this.pathString += '],\n['
            }
            this.pathString = this.pathString.slice(0,-4)
            this.pathString += ']\n]'
            Axios.post('/point',{
                path : this.pathString,
                imgWidth: this.imgWidth,
                imgHeight: this.imgHeight,
                fileName : `${this.imgName}.json`,
                ani : true
            })
        },
        getLetterImg(){
            let uri = encodeURI(`letter/${this.letter}`)
            fabric.Image.fromURL(`${this.$store.getters.BACKEND_URL}/${uri}.png`, (img) =>{
                img.set({
                    left:0,
                    top: 0,
                })
                this.canvas.add(img)
                this.imgWidth = img.width
                this.imgHeight = img.height
                this.imgName = this.letter
            })
        },
        init(){
            this.prepareCanvas();
            this.canvas = window.__canvas;
            this.setEventHandlers();
        },
        prepareCanvas() {
            var canvas = window.__canvas = new fabric.Canvas('c', {
                isDrawingMode: true
            });
            fabric.Object.prototype.transparentCorners = false;
            canvas.freeDrawingBrush.width = 4
            /////////////////////
            var canvases = window.__canvases || window.canvases;

            canvas && canvas.calcOffset && canvas.calcOffset();

            if (canvases && canvases.length) {
                for (var i = 0, len = canvases.length; i < len; i++) {
                    canvases[i].calcOffset();
                }
            }
        },
        setEventHandlers(){
            document.addEventListener('keydown', this.undo)
            this.canvas.on('mouse:down', this.setPoint)
            // let canvasContainer = document.getElementsByClassName('canvas-container')[0]
            // ///////////////////////////////////////////////Bubling을 활용해 canvas와 div태그로부터 전파되는 이벤트 감지
            // canvasContainer.addEventListener('mousedown', this.mouseDown)
            // canvasContainer.addEventListener('mouseup', this.mouseUp)
            ////////////////////////////////////////////
            // this.canvas.on('mouse:move',this.onDrawingMouseMove)
        },
        setPoint(e){
            this.pathString += `[${Math.round(e.pointer.x)}, ${Math.round(e.pointer.y)}],`
        },
        next(){
            this.pathString = this.pathString.slice(0,-1)
            this.pathString += '],\n['
        },
        submit(){
            this.pathString = this.pathString.slice(0,-1)
            this.pathString += ']\n]'
            Axios.post('/point',{
                path : this.pathString,
                imgWidth: this.imgWidth,
                imgHeight: this.imgHeight,
                fileName : `${this.imgName}.json`
            })
        },
        undo(e){
            if (e.keyCode == 90 && e.ctrlKey){
                let idx = this.pathString.lastIndexOf('[')
                if(idx > 2){
                    this.pathString = this.pathString.slice(0,idx)
                    this.canvas._objects.pop()
                    this.canvas.renderAll()
                }
            }
        }
    },
    mounted(){
        if(document.readyState == 'complete'){
            this.init()
        }else{
            window.onload = this.init
        }
    }        
}
</script>

<style>
.wrapper{
    position: absolute;
    top:0;
    left:0;
}

</style>