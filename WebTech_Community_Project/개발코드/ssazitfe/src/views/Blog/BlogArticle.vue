<template>
  <div class="board-title" style="height:100%;">
    
    <div class="row px-5 mr-0" align="center" style="justify-content:space-between; height:50px;">
      <v-row class="" style="justify-content:start;">        
        <h4 class="text-justify font-weight-bold">
          <div style="height:10px;"></div>
          <p v-if="boardName">{{ boardName }}</p>
          <p v-else>전체글</p>
        </h4>
        <!-- 글쓰기 -->
        <!-- 현재 게시판 정보를 글쓰기 페이지로 넘겨줘야함,, -->
        <v-btn-toggle
          borderless
          v-if="userInfo && (userInfo.blogNo == searchInfo.blogNo)"
        >
          <v-btn value="글쓰기" :to="'/bloghome/' + $store.getters.userInfo.userId + '/blogeditor' " color="white" class="text-decoration-none">
            <span class="hidden-sm-and-down mr-1">글쓰기</span>
            <v-icon small>mdi-pencil</v-icon>
          </v-btn>
        </v-btn-toggle>
        <v-col style="width:300px;"></v-col>
        <v-col class="mx-0 d-flex p-0" cols="2" style="height:50px; text-align:right;">
          <v-select
            :items="sortItems"
            label="정렬"
            outlined
            dense
            v-model="order"
          ></v-select>
        </v-col>
      </v-row>

      
    </div>

    <!-- 검색창 -->
    <!-- <v-row style="width:1050px;"> -->
      <!-- <v-col cols="3"></v-col>
      <v-col cols="5" class="px-0">
        <v-text-field
          v-model="search"
          label="블로그 내 검색"
          clearable
          class="p-0 m-0"
          @keyup.enter="$emit('call-search', search)"
        ></v-text-field>
        
      </v-col>
      <v-btn icon color="grey" class="mx-0 px-0 mt-2">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-spacer></v-spacer> -->
      <!-- <v-col class="mx-0 d-flex p-0" cols="2" style="height:50px;">
        <v-select
          :items="sortItems"
          label="정렬"
          outlined
          dense
        ></v-select>
      </v-col>
    </v-row> -->
    
    <br>
    <br />
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "BlogArticle",
  props: ['boardName'],
  data() {
    return {
      selected: "none",
      sortItems: ['기본', '최신순', '인기순'],
      searchInfo:{},
      search:'', 
      order:'',
    };
  },
  watch:{
    order: function(){
      this.$emit('changeOrder', this.order);
    }
  },
  created() {
    axios.get('/blog/searchbyuserid?userId=' + encodeURI(this.$route.params.userId))
    .then((Response)=>{
      // console.log(this.$route.params.userId)
      // console.log(Response.data + "어느 사람의 블로그인지");
      this.searchInfo = Response.data;
      this.$store.dispatch('Re_blogInfo',this.searchInfo)
    
      axios.get('/blog/boardlist?blogNo=' + Response.data.blogNo)
      .then((Res)=>{
        // console.log(Res);
        this.BoardItems = Res.data;
        this.$store.dispatch('Re_boardInfo',Res.data)
      })

    })
    axios.post('/user/list',{
      searchKey : "userId",
      searchValue : this.$route.params.userId
    }).then((Response)=>{
      // console.log(Response);
      // console.log("Hi");

      for(let i=0;i<Response.data.length;i++){
        if(Response.data[i].userId==this.$route.params.userId){
          this.blogInfo = Response.data[i];
          break;
        }
      }
      // this.blogInfo = Response.data[0];
      // console.log(this.blogInfo.profileImg);
      this.userImage = this.serverURL + this.blogInfo.profileImg;
    })
    // console.log(this.searchInfo + "어느 사람의 블로그인지");
  },
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    },
  }
};
</script>

<style>

</style>
