<template>
  <div v-if="classInfo" style="background-color:#CBFFFF;">

    <!-- 커리큘럼 도안 1 -->
          <!-- <img src="../../assets/images/curi.png" class="mimg"/> -->


    <div class="curriculums">
      
    <!-- 커리큘럼 도안 2 -->
    <img src="../../assets/images/back3.png" class="mimg"/>
        <div class="curriculum" >
          
            <p style="margin-top:10px">수업 기간: {{classInfo.startDate}} ~ {{classInfo.endDate}}</p>
            <p>수강 인원: {{classInfo.studentCnt}} /{{classInfo.userLimit}}</p>
            <p>클래스 설명: {{classInfo.classDesc}}</p>
            <div style="padding: 0 0 20px;min-height: 200px;height: auto;border: none; border-radius: 50px;background-color: rgb(255, 255, 255);"> 
                <template v-for="(session,index) in sessionInfo">
                  <b-col cols="12" :key="index">
                    <a  v-show="!vshow[index]">{{session.sessionName}} - {{session.sessionDesc}}</a>
                     <input style="border: 1px solid gray;" v-model="modifySessionName" v-show="vshow[index]" type="text"/>
                      <input style="border: 1px solid gray;margin-left:5px" v-model="modifySessionDesc" v-show="vshow[index]" type="text"/>
                    <v-icon  @click="delSession(session.sessionNo)" v-if="$store.getters.userInfo && $store.getters.userInfo.userType=='teacher' ">
                      mdi-delete
                    </v-icon>
                       <v-icon  v-show="!vshow[index]" @click="sessionModify(index)" v-if="$store.getters.userInfo && $store.getters.userInfo.userType=='teacher' ">
                      mdi-pencil
                    </v-icon>
                      <v-icon   v-show="vshow[index]" @click="sessionUpdate(session,index)" v-if="$store.getters.userInfo && $store.getters.userInfo.userType=='teacher' ">
                      mdi-check
                    </v-icon>
                  </b-col>
                </template>

              <div style="display:flex; justify-content:center;width:100%">
                    <template>
                        <div>
                          <v-dialog
                            v-model="dialog"
                            persistent
                            max-width="600px"
                          >
                            <template v-slot:activator="{ on, attrs }">
                              <div v-if="owner_check">
                                <v-btn
                                  color="primary"
                                  dark
                                  v-bind="attrs"
                                  v-on="on"
                                  >
                                  커리큘럼추가
                                </v-btn>
                              </div>
                              <div v-else>
                              
                              </div>
                            </template>
                            <v-card>
                              <v-card-title>
                                <span class="headline">커리큘럼 만들기</span>
                              </v-card-title>
                              <v-card-text>
                                <v-container>
                                  <v-row>
                                    <v-col
                                      cols="12"
                                      sm="6"
                                      md="4"
                                    >
                                      <v-text-field
                                        label="커리큘럼이름"
                                        required
                                        v-model="sessionName"
                                      ></v-text-field>
                                    </v-col>
                                    <v-col
                                      cols="12"
                                      sm="6"
                                      md="4"
                                    >
                                      <v-text-field
                                        label="커리큘럼내용"
                                        v-model="sessionDesc"
                                      ></v-text-field>
                                    </v-col>
                                  </v-row>
                                </v-container>
                              </v-card-text>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                  color="blue darken-1"
                                  text
                                  @click="dialog = false"
                                >
                                  닫기
                                </v-btn>
                                <v-btn
                                  color="blue darken-1"
                                  text
                                  @click="addSession"
                                >
                                  추가
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </div>
                  </template>
              </div>
             
            </div>
        </div>
        <div class="curi-img">
					<v-img v-if="classInfo!=null&&classInfo.classImg!=null" :src="$store.getters.BACKEND_URL+classInfo.classImg" aspect-ratio="1" style="margin-right:10px" ></v-img>

        </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return {
      classInfo : [],
      check_owner : null,
      sessionInfo : [],
      dialog: false,
      vshow:[],
      modifySessionName:"",
      modifySessionDesc:""
    }
  },
  created() {
    this.get_classInfo();
    this.get_classsession();
  },
  methods : {
    get_classInfo(){
      axios.get(`class/oneclass/`+this.$route.params.classno)
        .then((Response)=>{
            this.classInfo = Response.data
            this.check_owner = Response.data.checkOwner
        })
        .catch((error)=>{
            error
        })
    },
    get_classsession(){
      axios.get(`classsession/selectclasssession/`+this.$route.params.classno)
      .then((Response)=>{
        this.sessionInfo = Response.data

      })
      .catch((error)=>{
        error
      })
    },
    addSession(){
      this.dialog = false
      let data = {
        sessionName : this.sessionName,
        sessionDesc : this.sessionDesc,
        classNo : this.$route.params.classno
      }
      axios.post('classsession/classsession',data)
      .then((Response)=>{
        Response
        alert('세션 추가 완료')
        this.$router.go()
      })
      .catch((error)=>{
        error
      })
    },
    delSession(no){
      axios.delete('classsession/oneclasssession/'+no)
      .then((Response)=>{
        Response
        alert('세션 삭제 완료')
        this.$router.go()
      })
      .catch((error)=>{
        error
      })
    },sessionModify(index){
      this.vshow[index] = !this.vshow[index];
      this.vshow = [...this.vshow];
      this.modifySessionName=this.sessionInfo[index].sessionName;
      this.modifySessionDesc=this.sessionInfo[index].sessionDesc;
    },sessionUpdate(session,index){
      session.sessionName=this.modifySessionName;
      session.sessionDesc=this.modifySessionDesc;
      axios.put("/classsession/classsession",session).then((response)=>{
        response
        this.sessionInfo[index].sessionName=this.modifySessionName;
        this.sessionInfo[index].sessionDesc=this.modifySessionDesc;
        this.modifySessionName="";
        this.modifySessionDesc="";
        this.vshow[index] = !this.vshow[index];
        this.vshow = [...this.vshow];
      }).catch((err)=>{
        err
      })
    }
  },
  computed : {
    owner_check() {
      return this.check_owner
    }
  }
}
</script>

<style>
/* 도안 1 */
/* .mimg{
  width: auto;
  height: 60vh;    
  position: absolute;
  max-height: 100vh;
  bottom: 0;
  right: 0;
  z-index: 0;
} */
/* 도안 2 */
.mimg{
  width: 100%;
  position: absolute;
  bottom: 0;
  right: 0;
  z-index: 0;
} 
.curi-img{
  width:300px;
  margin:auto;
}
.curriculums{
    margin: 0 0 0 2%;
    width: 98%;
  display: flex;
}
.curriculum{
  
  flex: 0 0 66.666667%;
  max-width: 66.666667%;
}
.curriculum>*{
  position: relative;
  z-index: 20;
}

@media (min-width:481px) and (max-width: 763px){
  .curriculum{
      
    flex: 0 0 50%;
    max-width: 50%;
  }
}

@media (max-width: 480px) {
  .curriculums{  
    margin: 0 2%;
    width: 96%;
    flex-wrap: wrap-reverse;
  }
  .curriculum{
  
    flex: 0 0 100%;
    max-width: 90%;
    margin: 0 auto;
  }
  .curi-img{
    margin: 10px auto;
  }

}

</style>