<template>
  <div>
    <div class="canvas-container">
      <canvas :ref="canvasTmp" :id="canvasTmp" style="border: 1px solid rgb(170, 170, 170); position: absolute; left: 0px; top: 0px; touch-action: none; user-select: none;" class="lower-canvas"></canvas>
    </div>
  </div>
</template>
<script>
import {fabric} from 'fabric';

export default {
  name: 'DictationCanvas',
  props: {
    canvasId: {
      type: String,
      description: "canvas Id",
      default:"canvas1"
    },
    canvasSetting: {
      type:String,
    }
  },
  data() {
    return {
      canvasTmp:this.canvasId,
      canvasSize:this.canvasSetting
    };
  },
  mounted(){
    var canvas = window.__canvas = new fabric.Canvas(this.canvasTmp, {
      isDrawingMode: true
    });
    if(this.canvasTmp == "resultc"){
      canvas.setWidth(this.canvasSize.canvasset.width*10);
      canvas.setHeight(this.canvasSize.canvasset.width*2);
    }
    else{
      canvas.setWidth(this.canvasSize.canvasset.width);
      canvas.setHeight(canvas.getWidth());
    }
    if( window.__canvasList==null){
      window.__canvasList=[];
    }
    window.__canvasList.push(canvas);
    fabric.Object.prototype.transparentCorners = false;
    if (canvas.freeDrawingBrush) {
      canvas.freeDrawingBrush.color = "#000000";
      canvas.freeDrawingBrush.width = 1;
      canvas.freeDrawingBrush.shadow = new fabric.Shadow({
        blur: 0,
        offsetX: 0,
        offsetY: 0,
        affectStroke: true,
        color: "#000000",
      });
    }
  }
}
</script>
