<template>
  <div class="tag container col order">
    <div class="row">
      <div class="col" id="mainTmpTag">
        <v-card :elevation="0">
          <v-container fluid>
            <v-row>

              <!-- 임시태그 -->
              <v-col cols="6">
                <div>
                  <h3>임시태그</h3>
                  <draggable class="list-group" :list="tmptag" group="tag">
                    <div
                      class="list-group-item"
                      v-for="(tmptag, index) in tmptag"
                      :key="tmptag.tmptagNo"
                    >
                      {{ ckchange(tmptag,index) }} {{ index }}
                    </div>
                  </draggable>
                </div>
              </v-col>

              <!-- 정식태그 -->
              <v-col cols="6">
                <div >
                  <h3>정식태그</h3>
                  <draggable class="list-group" :list="tag" group="tag">
                    <div
                      class="list-group-item"
                      v-for="(tag, index) in tag"
                      :key="tag.tagNo"
                    >
                      {{ realValue(tag) }} {{ index }}
                    </div>
                  </draggable>
                </div>
              </v-col>

              <v-row>
                <v-spacer></v-spacer>
                  <v-btn-toggle
                    borderless
                    cols="1"
                  >
                    <v-btn value="수정" color="white" class="text-decoration-none" @click="clickUpdate">
                      <span class="hidden-sm-and-down mr-1">수정</span>
                      <v-icon small>mdi-pencil</v-icon>
                    </v-btn>
                  </v-btn-toggle>
                <v-col cols="1"></v-col>
              </v-row>
                <hr>

            </v-row>
          </v-container>
        </v-card>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import draggable from "vuedraggable";

export default {
  name : "tmpTag",
  components : {
    draggable,
  },
  data(){
    return {
      tag: [],
      tmptag: [],
      newTags: [],
    }
  },
  methods:{
    realValue(data) {
      return data.tagName ? data.tagName : data.tmptagName + "(임시)";
    },
    ckchange(data,index){
      if(!data.tmptagName){
        // alert(index);
        // alert("정식태그는 임시태그로 수정하지 못합니다!");
        // // Tmptag에서 해당 태그 삭제
        alert(index);
        // console.log(this.tmptag.splice(index,1));
        // // tag 원위치
        // this.tag = this.tag.push(data);
        return;
      }
      return data.tmptagName;
    },
    clickUpdate(){
      for(var index=0; index<this.tag.length; index++){
        if(this.tag[index].tmptagName){
          this.newTags.push({tmptagNo:this.tag[index].tmptagNo, tmptagName:this.tag[index].tmptagName, articleNo:this.tag[index].articleNo, bookNo:this.tag[index].bookNo})
        }
      }
      // console.log(this.newTags);
      // 1. tag에서 for문 돌려서 tmptagName이 있는것 newTags에 push
      // newTags => tmptagName : tmptagName | tmptagNo : tmptagNo
      // axios.post (컨트롤러 List로 여러개 한번에 받는거 추가 연결 -> service에서 for문돌면서 추가,삭제)
      axios.post(`/tag/tmptotag`,
        this.newTags
      ).then(()=>{
        alert("수정 완료!");
        this.newTags = [];
        this.getTags();
        this.getTmptags();
      }).catch(()=>{
        alert("수정 중 문제가 발생하였습니다!");
        this.newTags = [];
        this.getTags();
        this.getTmptags();
      })
    },
    getTags(){
      axios.get(`/tag/tags`)
      .then((Response)=>{
        this.tag = Response.data;
      })
      .catch(()=>{
        alert("정식태그 목록을 불러오는 데 실패했습니다.");
      })
    },
    getTmptags(){
      axios.get(`/tag/tmptags`)
      .then((Response)=>{
        this.tmptag = Response.data;
      })
      .catch(()=>{
        alert("임시태그 목록을 불러오는데 실패했습니다.");
      })
    }
  },
  computed:{
    userInfo(){
      return this.$store.getters.userInfo;
    },
  },
  created(){
    this.getTags();
    this.getTmptags();
  },

}
</script>

<style scoped>

</style>