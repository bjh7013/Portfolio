<template>
  <v-app>
    <template>
      <v-modal>

      
        <div v-if="!change">
          <div class="haveimg">
            <label class="file-upload" for="photo" id="file-drag" ref="dropbox">
              <img :src="url+classInfo.classImg" class="img_upload" style="width:20vw;"/>
            </label><br>
            <input type="file" id="photo" accept="image/*" @change="onFileChange"/>
          </div>
        </div>
        <div v-else >
            <div class="haveimg" >
              <label class="file-upload" for="photo" id="file-drag" ref="dropbox" >
                <img :src="classimg" class="img_upload"/>
              </label><br>
              <input type="file" id="photo" accept="image/*" @change="onFileChange" />
            </div>
        </div>
        <div style="display:inline-block">
        <v-text-field
          style="width:20vw"
          v-model="classInfo.className"
          :counter="10"
          :rules="nameRules"
          label="교실명"
          required
        ></v-text-field>

        <v-text-field
          style="width:20vw"
          v-model="classInfo.classDesc"
          :rules="emailRules"
          label="교실 소개"
          required
        ></v-text-field>

        <v-select
          style="width:20vw"
          v-model="classInfo.targetAge"
          :items="age"
          :rules="[v => !!v || '선택 필수입니다!']"
          label="연령층"
          required
        ></v-select>

        <v-text-field
          style="width:20vw"
          v-model="classInfo.userLimit"
          :rules="emailRules"
          label="최대 인원수"
          required
        ></v-text-field>

        <v-text-field
          style="width:20vw"
          v-model="classInfo.openType"
          :rules="emailRules"
          label="공개/비공개"
          required
        ></v-text-field>

        <v-text-field
          style="width:20vw"
          v-model="classInfo.startDate"
          :rules="emailRules"
          label="시작일"
          required
        ></v-text-field>

        <v-text-field
          style="width:20vw;"
          v-model="classInfo.endDate"
          :rules="emailRules"
          label="종료일"
          required
        ></v-text-field>
        
        <!-- <v-select
          v-model="select"
          :items="items"
          :rules="[v => !!v || 'Item is required']"
          label="Item"
          required
        ></v-select> -->


        <v-btn
          color="success"
          class="mr-4"
          @click="updateclass"
        >
          수정하기
        </v-btn>

        <v-btn
          color="error"
          class="mr-4"
          @click="reset"
        >
          원래대로
        </v-btn>
        <v-btn
          color="error"
          class="mr-4"
          @click="home"
        >
          홈으로
        </v-btn>
        </div>
      </v-modal>
    </template>
  </v-app>
</template>

<script>
import axios from 'axios'
export default {
    name : 'classmodify',
    data() {
      return {
        classInfo : [],
        classtempInfo : null,
        change : false,
        classimg:"",
        files: "",
        classname :null,
        classdesc : null,
        targetage : null,
        userlimit : null,
        opentype : null,
        startdate : null,
        enddate : null,
        classimgs : null,
        age: [
          {value: 1, text:"유치원생"},
          {value: 2, text:"초등학생(저학년)"},
          {value: 3, text:"초등학생(고학년)"},
          {value: 4, text:"중학생"},
          {value: 5, text:"고학생"},
          {value: 6, text:"대학생"},
          {value: 7, text:"기타"},
        ]
      }
    },
    created() {
      this.get_class()
    },
    methods : {
      home(){
        this.$router.push('/')
      },
      reset(){
        this.classInfo.className = this.classname
        this.classInfo.classDesc = this.classdesc
        this.classInfo.targetAge = this.targetage
        this.classInfo.userLimit = this.userlimit
        this.classInfo.openType = this.opentype
        this.classInfo.startDate = this.startdate
        this.classInfo.endDate = this.enddate
        this.classInfo.classImg = this.classimgs
        this.change = false
      },
      get_class(){
        axios.get(`/class/oneclass/${this.$route.params.classNo}`)
        .then((response)=>{
          this.classInfo = response.data

          this.classname = response.data.className
          this.classdesc = response.data.classDesc
          this.targetage = response.data.targetAge
          this.userlimit = response.data.userLimit
          this.opentype = response.data.openType
          this.startdate = response.data.startDate
          this.enddate = response.data.endDate
          this.classimgs = response.data.classImg
          // this.$store.dispatch('CLASS',classdesc);
        })
        .catch((err) =>{
          err
        })
      },
      onFileChange(e) {
        //이미지 첨부
        var files = e.target.files || e.dataTransfer.files;

        if (!files.length) 
        return;
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
        this.change = true
      },
      updateclass(){
        let data = {
          'classNo' : this.classInfo.classNo,
          'className' : this.classInfo.className,
          'classDesc' : this.classInfo.classDesc,
          'targetAge' : this.classInfo.targetAge,
          'userLimit' : this.classInfo.userLimit,
          'openType' : this.classInfo.openType,
          'startDate' : this.classInfo.startDate,
          'endDate' : this.classInfo.endDate,
          'classImg' : this.classimgs,
          'userNo' : this.classInfo.userNo,
        }
        
        let config ={
          header : {
            'Content-Type' : 'application/json',
            // 'Content-Type' : 'multipart/form-data',
            // 'Authorization' : 'JWT' + localStorage.getItem("token")
          }
        };
        // axios.post('http://k3d206.p.ssafy.io:8000/accounts/',data,config).then(
        axios.put('/class/class',data,config).then( //클래스 수정(이미지제외)
          res =>{
            res
            this.$store.state.createclassval = 1
            this.$store.state.loadingflag = true;
            if(this.change==true){
              let configimg ={
              header : {
                'Content-Type' : 'multipart/form-data',
                // 'Authorization' : 'JWT' + localStorage.getItem("token")
                }
              };
              let form = new FormData();
              form.append("file",this.files)
              form.append("classNo",this.classInfo.classNo)
              axios.put('/class/imgclass',form,configimg) //이미지업데이트
              .then((data) =>{
                data
                this.intervalid = setInterval(()=>{
                this.$store.state.loadingflag = false;
                alert("수정되었습니다.")
                location.reload()
            }, 2000);
              }).catch((error)=>{
                error
              })
            }else{
              alert("수정되었습니다.")
              location.reload()
            }
          })
          .catch( err => {
            err
          })

      }
    },
    computed : {
      url() {
        return this.$store.getters.BACKEND_URL
      }
    }
}
</script>

<style>

</style>