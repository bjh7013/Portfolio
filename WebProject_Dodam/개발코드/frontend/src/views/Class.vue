<template>
  <v-app>
    <nav-bar></nav-bar> 
    <v-container>
      <div class="">
        <h1>우리반</h1>
      <div class="create-poblem-btns" v-if="$store.getters.userInfo.userType=='teacher'">
        <div class="create-poblem-btn">
          <dictation-make></dictation-make>
        </div>
        <div class="create-poblem-btn">
          <word-make></word-make>
        </div>
      </div>
      </div>
      <v-layout row wrap style="margin:5px 0 100px 0">
        <v-flex xs12 sm6 md3 v-for="(productItem,index) in classlist" :key="index">
          <div class="cards">
            <ProductItems :productItem="productItem"/>
          </div>
        </v-flex>
        <v-flex xs12 sm6 md3 v-if="$store.getters.userInfo && $store.getters.userInfo.userType==='teacher'">      
          <v-card flat class="ma-1 text-xs-center" @click="show">
            <div class="cards align-center" style="padding:33%">
              <i class="far fa-plus-square fa-8x"></i>
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    <div>
      <modal name="modal-classCreate" :height="`700px`" :width="modalwidth+`px`" @before-open="beforeOpen">
        <form id="file-upload-form" class="uploader" enctype="multipart/form-data">
          <div id="classcreatemodal">
            <div class="classcreatemodaldiv1">
              <h2>새 교실 추가</h2>
            </div>
            <div class="classcreatemodaldiv2">
              <div class="no-mobile" v-if="!classimg">
                <div class="noimg">
                  <label for="photo" id="file-drag-not_img" ref="dropbox">
                    <h4>교실 이미지 등록</h4>
                    <input type="file" id="photo" accept="image/*" @change="onFileChange"/>
                  </label>
                </div>
              </div>
              <div class="no-mobile" v-else>
                <div class="haveimg">
                  <label class="file-upload" for="photo" id="file-drag" ref="dropbox">
                    <img :src="classimg" class="img_upload"/>
                  </label>
                  <input type="file" id="photo" accept="image/*" @change="onFileChange"/>
                </div>
              </div>
              <div class="textclasscreate">
                <div class="">
                  <label for="classname" class="floatleft">교실 이름 : </label>
                  <b-form-input v-model="classname" id="classname" type="text" placeholder="교실 이름을 입력해주세요." maxlength="8"/>
                </div>
                <div>
                  <label for="userlimit" class="floatleft">최대 인원 : </label>
                  <b-form-input v-model="userlimit" id="userlimit" type="text" maxlength="2" placeholder="10"/>
                </div>
                <div>
                  <label for="teenager" class="floatleft">수강 연령 : </label>
                  <b-form-select v-model="targetage" id="teenager" :options="age"/>
                </div>
              </div>
            </div>
            <div class="classcreatemodaldiv3">
              <div>
                <label for="classdesc" class="classdescl floatleft">교실 소개 : </label>
              </div>
              <div>
                <b-form-textarea v-model="classdesc" id="classdesc" maxlength="50" placeholder="교실에 대한 간략한 설명을 50자 내로 적어주세요"/>
              </div>
            </div>
            <!-- <br>
            <label for="opentype">공개 여부 : </label>
            <input v-model="classdesc" id="opentype" type="textarea" placeholder="10"/> -->
            <div class="classcreatemodaldiv4">
              <div>
                <label for="starttime" class="floatleft">수업 기간 : </label>
              </div>
              <div>
                <input v-model="startdate" id="starttime" type="date"/>
                ~
                <label for="endtime"></label>
                <input v-model="enddate" id="endtime" type="date"/>
              </div>
            </div>
            <div class="classcreatemodaldiv5">
              <b-button class="btn" @click="createclass" v-if="!create_class_flag">개설</b-button>
            </div>
          </div>
        </form>
      </modal>
    </div>
      <br>
    <foot-bar class="footer"></foot-bar>
  <Loading />
  </v-app>
</template>

<script>
import DictationMake from '@/components/Class/DictationMake.vue'
import WordMake from '@/components/Class/WordMake.vue'
import NavBar from '@/components/NavBar.vue'
import ProductItems from "@/components/ProductItems";
import FootBar from '@/components/FootBar.vue'
import axios from "axios";
import Loading from "@/components/Loading"

export default {
  name: 'class',
  components: {
    NavBar,
    ProductItems,
    FootBar,
		DictationMake,
    WordMake,
    Loading,
  },
  data() {
    return {
      modalwidth:700,
      classname:"",
      classdesc:"",
      targetage:null,
      userlimit:"",
      opentype:"public",
      startdate:"",
      enddate:"",
      classimg:"",
      userInfo : this.$store.getters.userInfo,
      fileName: "/img/class/default.jpg",
      files: null,
      userno:"1",
      classlist: [],
      age: [
        {value: null, text: "연령대"},
        {value: 0, text:"유치원생"},
        {value: 1, text:"초등학생(저학년)"},
        {value: 2, text:"초등학생(고학년)"},
        {value: 3, text:"중학생"},
        {value: 4, text:"고학생"},
        {value: 5, text:"대학생"},
        {value: 6, text:"기타"},
      ],
      create_class_flag : false
    };
  },
  computed: {
  },
  methods:{
    show(){
      this.$modal.show("modal-classCreate");
    },
    createclass(){
      if(this.classname==""){
        alert("교실 이름이 없습니다.");
        return;
      }
      if(this.userlimit==""){
        alert("인원수를 설정해 주세요.");
        return;
      }
      if(this.startdate==""){
        alert("시작일을 정해주세요.");
        return;
      }
      if(this.enddate==""){
        alert("종료일을 정해주세요.");
        return;
      }
      var date = new Date();
      var customdate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
      if(customdate>this.startdate){
        alert("시작일은 오늘 전이 될 수 없습니다.");
        return;
      }
      if(this.targetage==null){
        alert("연령대를 설정해주세요.");
        return;
      }


      this.create_class_flag = true;
      let data = {
      'className' : this.classname,
      'classDesc' : (this.classdesc=="")?"자세한 설명이 없습니다.":this.classdesc,
      'targetAge' : this.targetage,
      'userLimit' : this.userlimit,
      'openType' : this.opentype,
      'startDate' : this.startdate,
      'endDate' : this.enddate,
      'classImg' : this.fileName,
      'userNo' : this.userno,
      }   
      let config ={
        header : {
          'Content-Type' : 'application/json',
        }
      };
      axios.post('class/class',data,config).then( //클래스 생성
        res =>{
          res
          this.$store.state.createclassval = 1
          // this.hide()
          this.$store.state.loadingflag = true;

          let configimg ={
          header : {
            'Content-Type' : 'multipart/form-data',
            }
          };
          if(this.files==null){
            this.$router.go()
            return
          }
          let form = new FormData();
          form.append("file",this.files)
          form.append("userNo",this.userno)
          form.append("classNo",0)
          axios.put('class/imgclass',form,configimg) //이미지업데이트
          .then((data) =>{
            data
        //     this.intervalid = setInterval(()=>{
        //     this.$router.go()
        // }, 1000);
            this.$router.go()
          }).catch((error)=>{
            error
          })
        })
        .catch( err => {
          err
        })
    },
    beforeOpen(){
      this.classimg = "";
    },
    onFileChange(e) {
      let files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.createImage(files[0]);
      this.files = e.target.files[0];
    },
    createImage(file) {
      this.fileName = file.name;
      var reader = new FileReader();
      var vm = this;
      reader.onload = (e) => {
        vm.classimg = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function() {
      this.classimg = "";
      this.fileName = "";
    },
    userFail: function(text) {
      if (text[0] == "0") {
        text = text.substring(1, 5);
        this.userlimit = text;
      }
    },
    getClass(){
      axios.get('class/userclass')
      .then( res => {
          this.classlist = res.data;
      })
      .catch( err => {
          err
      })
    },
  },
  created(){
    this.getClass();
    if(this.$session.get('jwt')==null){
      this.$router.push('/');
    }
      var windowwidth= window.innerWidth;
      if(windowwidth<740){
        this.modalwidth = 300;
      }
  },
  watch: {
    userlimit: function() {
    this.userFail(this.userlimit);
    },
  },
}
</script>


<style scoped>
.create-poblem-btns{
  display:flex; 
  justify-content: flex-end;
}
.create-poblem-btn{
  margin: 0% 4px;
}
#classcreatemodal *{
  margin: 0;
}
#classcreatemodal{
  margin: 0 10px;
}
#classcreatemodal>div{
  padding: 6px 0;
}
#teenager,#userlimit,#classname{
  width:190px;
  margin: 6px 0;
  display: inline-block;
}
.floatleft{
  display: inline;
  padding: 0 0 0 10px;
  line-height: 50px;
}
.classcreatemodaldiv2,
.classcreatemodaldiv4{
  display: flex;
  line-height: 50px;
}
#starttime,#endtime{
  border:solid 1px;
  border-color:rgb(200, 200, 200);
  border-radius: 5px;
}
#classname{
  width: 270px;
}
#classdesc{
  resize: none;
  height: 90px;
}

#photo{
  display: none;
}
.haveimg,
.img_upload,
.noimg,
.photo{
  height: 200px;
  width: 200px;
  margin:0px auto;
  line-height: 200px;
}
.noimg{
  border-style:dashed;
  background:violet;
}
.cards{
  padding: 1% 1%;
  border-radius: 50%;
}

.cimg{
    border-radius: 10%;
}
.cfont{
    font-size:1vh;
}

.cti{
    border-radius: 15%;
    background-color: rgb(196, 189, 189);
    height: 5vh;
}
.textclasscreate{
  width:60vw;
    display: flex;
    flex-wrap: wrap;
    margin: 0 40px;
}
.footer{
  position: absolute;
  bottom:0;
  width:100%;
}
.create-poblem-btn{
  margin: 0 3%;
}
.uploader{
  margin-top:40px;
}

@media (max-width: 740px){
  .no-mobile{
    display: none;
  }
  .uploader{
    margin: 1px auto;
  }
  .floatleft{
    display: none;
  }
}

</style>
