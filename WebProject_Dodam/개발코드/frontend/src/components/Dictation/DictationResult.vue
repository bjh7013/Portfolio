<template>    
  <div>
    <div id=mant>
      <div>
        <h1>나의 점수는? {{correctcount/questions.length*100}}점</h1>
      </div>
    </div>
    <div v-if="correctcount != questions.length">
      <div class="sub-mant">
        <div>
          오답 노트를 볼까요?
        </div>
      </div>
      <div>
        <div v-for="(ansitem,x) in ansdata" :key="x">
          <div class="display-flex_width-100 item-one" v-if="!ansitem.correct">
            <div class="submit-item-left">
              {{x+1}}번
            </div>
            <div class="display-flex_width-90">
              <div class="submit-item-mid">
                <div class="display-flex_width-100" style="background-color:#F7AA97">
                  <div class="display-flex_width-10">정답</div>
                  <div class="display-flex_width-90 padding-4">
                    {{questions[x].dictationSentence}}
                  </div>
                </div>
                <div class="display-flex_width-100" style="background-color:peachpuff">
                  <div class="display-flex_width-15">내가 쓴 답</div>
                  <div class="display-flex_width-85 padding-4" v-if='ansitem.submitOCR==""'>
                    {{ansitem.submitAnswer}}
                  </div>
                  <div class="display-flex_width-85 padding-4" style="justify-content: space-between;" v-if='ansitem.submitOCR!=""'>
                    <div>
                    {{ansitem.submitOCR}} 
                    </div>
                  </div>
                </div>
                <div v-if='ansitem.submitOCR!=""'  style="background-color:peachpuff;">
                  <div :id="`ocr`+(x)" class="ocrimg">
                    <img class="ocrimgs" :src="ansitem.submitImgData"/>
                  </div>
                </div>
              </div>
              <div class="submit-item-right" v-if='ansitem.submitOCR!=""'>
                <div class="btn-showresultimg" @click="ckresults(x)">
                  내가 쓴 글씨 보기 <i class="fas fa-angle-down"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>   
</template>

<script>
  export default {
    name: 'dictationresult',
    props: ["ansdata","questions","correctcount"],
    data() {
      return {
      };
    },
    created(){
    },
    mounted(){
      this.getdisplay();
    },
    methods:{
      getdisplay(){
        for(var i =0;i<this.questions.length;i++){
          if(!this.ansdata[i].correct && this.ansdata[i].submitOCR!=""){
            document.getElementById("ocr"+i).style.display = "none";
          }
        }
      },
      ckresults(i){
        if(document.getElementById("ocr"+i).style.display == "none"){
          document.getElementById("ocr"+i).style.display= "block";
          return;
        }
        document.getElementById("ocr"+i).style.display = "none";
      },
    }
  };
</script>

<style>
.sub-mant{
  float: left;
  padding: 1% 4%;
  font-size: 20px;
}
.padding-4{
  padding: 0 4%;
}
.display-flex_width-100{
  display:flex;
  width:100%;
}
.display-flex_width-10{
  display:flex;
  width:10%;
}
.display-flex_width-90{
  display:flex;
  width:90%;
}
.submit-item-right{
  width:20%
}
.submit-item-mid{
  width:75%;
  background-color:#FDD692; 
}
.submit-item-left{
  width:5%
}
.item-one{
  margin: 2px;
}
.btn-showresultimg{
  width:100%;
  height:100%;
  background-color: peachpuff; 
}
.ocrimg{
  display:none;
}
.ocrimgs{
  width: 100%;
}
</style>