<template>
    <div class="tag container col order">
        <div class="row">
        <div class="col" id="mainTag">
            <v-card :elevation="0">
            <v-container fluid>
                <v-row>

                <!-- 태그그룹 -->
                <v-col cols="6">
                    <div v-for="taggroup in taggroups" :key="taggroup.groupNo">
                        <template v-if="taggroup[0]">
                        <h3>{{taggroup[0].tagName}}({{taggroup[0].origin}})</h3>
                            <draggable class="list-group" :list="taggroup" group="tag">
                                <div
                                class="list-group-item"
                                v-for="tags in taggroup"
                                :key="tags.tagNo"
                                >
                                {{ tags.tagName }} {{ tags.origin }}
                                </div>
                            </draggable>
                        </template>
                    </div>
                </v-col>

                <!-- 태그그룹제거 -->
                <v-col cols="6">
                    <div >
                    <h3>태그그룹제거</h3>
                    <draggable class="list-group" :list="newTags" group="tag">
                        <div
                        class="list-group-item"
                        v-for="tag in newTags"
                        :key="tag.tagNo"
                        >
                        {{ ckalone(tag) }}
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
  name : "tag",
  components : {
    draggable,
  },
  data(){
    return {
      tag: [],
      newTags: [],
      taggroups: [],
      change: [],
    }
  },
  methods:{
    ckalone(data) {
        data.groupNo = data.tagNo;
        return data.tagName + "(" + data.origin + ")";
    },
    clickUpdate(){
        // 1. 태그 그룹 제거된 것 처리
        for(var index=0; index<this.newTags.length; index++){
            this.change.push({tagNo: this.newTags[index].tagNo, 
                              tagName: this.newTags[index].tagName, 
                              groupNo: this.newTags[index].groupNo})
        }
        
        // 2. 태그 그룹 변경된 것 처리
        for(var i=0; i<this.taggroups.length; i++){
            for(var j=0; j<this.taggroups[i].length; j++){
                if(this.taggroups[i][j] && this.taggroups[i][j].groupNo != this.taggroups[i][0].groupNo){
                    this.change.push({tagNo: this.taggroups[i][j].tagNo, 
                                      tagName: this.taggroups[i][j].tagName, 
                                      groupNo: this.taggroups[i][0].groupNo})
                }
            }
        }

        // 3. DB전송
         axios.put(`/tag/updatetaggroup`,
            this.change
        ).then(()=>{
            alert("수정 완료!");
            this.newTags = [];
            this.change = [];
            this.taggroups = [];
            this.getTags();
        }).catch(()=>{
            alert("수정 중 문제가 발생하였습니다!");
            this.newTags = [];
            this.change = [];
            this.taggroups = [];
            this.getTags();
        })

    },
    getTags(){
      axios.get(`/tag/tags`)
      .then((Response)=>{
        this.tag = Response.data;
        this.getTagGroups();
      })
      .catch(()=>{
        alert("정식태그 목록을 불러오는 데 실패했습니다.");
      })
    },
    getTagGroups(){
        var i = 0;
        var j = 0;
        var group;
        var max = 0;
        for(i=0; i<this.tag.length; i++){
            group = 0;
            for(j=0; j<this.taggroups.length; j++){
                if(this.taggroups[j] && this.taggroups[j][0].groupNo == this.tag[i].groupNo){
                    group = j;
                    break;
                }
            }
            if(group != 0){
                this.taggroups[group].push({tagNo:this.tag[i].tagNo, tagName:this.tag[i].tagName, groupNo:this.taggroups[group][0].groupNo, origin: group});
            }
            else{
                this.taggroups.push([{tagNo:this.tag[i].tagNo, tagName:this.tag[i].tagName, groupNo:this.tag[i].tagNo, origin: max}]);
            }
            max++;
        }
    },
  },
  computed:{
    userInfo(){
      return this.$store.getters.userInfo;
    },
  },
  created(){
    this.getTags();
  },
}
</script>

<style>

</style>