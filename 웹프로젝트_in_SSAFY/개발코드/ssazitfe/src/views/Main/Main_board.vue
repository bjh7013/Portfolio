<template>
  <div class="mainboard">
    <!-- 네브바 -->
    <nav-header></nav-header>
    <div id="board_body">

      <!-- select box , 검색-->
      <v-container id="search" fluid>
        <v-row align="center">
          <v-col class="d-flex" cols="3" sm="3">
            <v-select
              :items="items1"
              :value="items1[0]"
              v-model="value"
            ></v-select>
          </v-col>
          <v-col cols="7">
            <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="searchdata"></v-text-field>
          </v-col>
          <v-btn icon @click="searchdata"><v-icon>mdi-magnify</v-icon></v-btn>
        </v-row>
      </v-container>

      <b-tabs content-class="mt-3">
        <!-- 게시판 탭 -->
        <b-tab title="게시판" @click="tab_board" active> 
          <div>
            <h5 style="text-align:left; margin:10px 0px 10px 20px;">이 달의 게시글</h5>
            <!-- 이 달의 블로그 -->
            <div>
              <v-row id="reco">
                <v-hover v-slot:default="{ hover }" v-for="(board,index) in bestboards" :key="board.articleNo">
                  <v-card class="auto" color="grey lighten-4" max-width="250" max-height="350" @click="tobestArticle(board)">
                    <v-img max-width="300" height="250" :src="serverURL + board.thumbNail" img-alt="Image" img-top>
                      <v-expand-transition>
                        <div
                          v-if="hover"
                          class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                          style="height: 100%;"
                        >
                          <div class="font-weight-light-6 title mb-2">
                            <v-avatar><img :src="bestuserImage[index]" style="width:50px; height:50px"></v-avatar>
                            <br>{{board.nickName}}
                            <br><br><p>{{board.title | titlesize}}</p>
                          </div>
                        </div>
                      </v-expand-transition>
                    </v-img>
                  </v-card>
                </v-hover>
              </v-row>
            </div>
            <hr>

            <!-- 게시판 하단 메뉴 -->
            <b-tabs>
              <b-tab title="게시글" @click="tab_boardlist">
              </b-tab>
              <b-tab title="알고싶어요!" @click="tab_qna">
              </b-tab>
            </b-tabs>
            <br>

            <!-- 게시판 하단 -->
            <div>
              <!-- 게시글 타이틀 -->
              <b-card :img-src="serverURL + board.article.thumbNail" img-left style="height:203px; margin:0px 0px 17px 0px;" v-for="(board,index) in boards" :key="board.articleNo">
                <b-card-title @click="toArticle(board)">
                  <button>{{board.article.title | titlesize}}</button>
                </b-card-title>
                <!-- 게시글 내용 -->
                <b-card-text @click="toArticle(board)">
                  <div
                    style="width:1050px; 
                      display:-webkit-box;
                      -webkit-line-clamp:3;
                      -webkit-box-orient:vertical;
                      line-height:20px;
                      height:57px;
                      justify-content:space-between; 
                      overflow:hidden; 
                      white-space:normal;
                      text-overflow:ellipsis;
                      word-break:break-all;
                      table-layout:fixed;"
                  >
                    <button><v-html v-html=board.article.content></v-html></button>
                  </div>
                  <!-- 태그 -->
                  <div style="text-align:left">
                    <span class="badge badge-pill badge-dark" style="margin:5px;" v-for="tag in board.tmpTag" :key="tag">{{tag.tmptagName}}</span>
                  </div>
                  <!-- 게시글 작성자 -->
                  <div>
                    <v-row>
                      <v-col cols="9">
                        <div style="text-align:left">
                          <v-avatar style="width:30px; height:30px;"><img :src="userImage[index]" style="width:30px; height:30px;"></v-avatar>
                          <b>{{boards[index].blogName}}</b>
                          by.{{board.article.nickName}}
                        </div>
                      </v-col>
                      <v-col cols="3">
                        <div style="text-align:right; padding:4px 4px;">
                          <v-icon small color="red">mdi-heart</v-icon><span style="color:grey;">{{board.article.likeCount}}</span>
                          &nbsp;<v-icon small>mdi-comment-text-outline</v-icon><span style="color:grey;">{{commentlength[index]}}</span>
                        </div>
                      </v-col>
                    </v-row>
                  </div>
                </b-card-text>
                
                <!-- <v-divider></v-divider> -->
                <!-- <v-avatar style="width:30px; height:30px;"><img :src="userImage[index]" style="width:30px; height:30px;"></v-avatar>{{board.nickName}} -->
                <!-- <b>{{blogboards[index].blogName}}</b>
                by.{{board.nickName}} -->
              </b-card>
            </div>

            <!-- 페이지네이션 -->
            <v-col>
              <v-container id="page" class="max-width" @click="clickpage">
                <v-pagination
                  v-model="page"
                  :length="pagelength"
                  :page="page"
                  :total-visible="totalVisible"
                ></v-pagination>
              </v-container>
            </v-col>
          </div>
        </b-tab>

        <!-- 나머지 탭 -->
        <b-tab title="작품" @click="tab_book"> 
        </b-tab>
        <b-tab title="블로그" @click="tab_blog"> 
        </b-tab>
        <b-tab title="유저" @click="tab_user">
        </b-tab>
      </b-tabs>
    </div>
    
    <!-- 푸터 -->
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from 'axios';
export default {
  name: "Main", 
    data() {
      return{
        items1: ['게시판', '작품', '블로그', '유저'],
        items2: ['전체선택', '알고리즘', '웹'],
        selected1: '1',
        selected2: '1',
        rows: 100,
        totalVisible: 10,
        value : "게시판",
        boards : [],
        bestboards : [],
        page: this.$route.params.page,
        search : this.$route.params.searchValue,
        pagelength : 1,
        bestuserImage : [],
        userImage : [],
        type : this.$route.params.type,
        blogboards : [],
        commentlength : []
        // search:this.$route.params.searchValue,
        // page: this.$route.params.page,
      }
    },
  components : {
    NavHeader,
    NavFooter
  },
  computed: { 
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  filters : {
    titlesize(value){
      if(value.length>18){
        // alert(value)
        return value.substring(0,18).concat("...");
      }else{
        return value
      }
    }
  },
  created() {
    this.getboards()
    this.search = "";
  },
  methods : {
      tab_board() {
        this.$router.push({ name: 'Mainboard', params: { searchValue: this.$route.params.searchValue, page:1, type:0 ,key: this.search+this.page }});
      },
      tab_book() {
        this.$router.push({ name: 'Mainbook', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
      },
      tab_blog() {
        this.$router.push({ name: 'Mainblog', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
      },
      tab_user() {
        this.$router.push({ name: 'Mainuser', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
      },
      toArticle(data){
        this.$router.push('/bloghome/' + data.article.userId + '/articledetail/' + data.article.articleNo)
      },
      tobestArticle(data) {
        this.$router.push('/bloghome/' + data.userId + '/articledetail/' + data.articleNo)
      },
      searchdata(){
        if(this.serach == "" || !this.search){
          this.search ="　"
        }
        if(this.value==="게시판"){
          this.$router.push({ name: 'Mainboard', params: { searchValue: this.search, page:1, type:0 ,key: this.search+this.page }});
        }else if(this.value==="작품"){
          this.$router.push({ name: 'Mainbook', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }else if(this.value==="블로그"){
          this.$router.push({ name: 'Mainblog', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }else if(this.value==="유저"){
          this.$router.push({ name: 'Mainuser', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }
        this.search =""
      },
      getboards(){
        axios.get('/article/articles?openScope=public&orderKey=likeCount&orderValue=DESC')
            .then((Response)=>{
              this.bestboards = []
              this.bestuserImage = []
              // console.log(Response);
              // console.log("드러옴");
              if(Response.data.length>4){
                for(let i = 0; i < 5; i++){
                  this.bestboards.splice(i,0,Response.data[i].article);
                  this.bestuserImage.splice(i,0,this.serverURL + Response.data[i].article.profileImg);
                }
              }else{
                for(let i = 0; i < Response.data.length; i++){
                  this.bestboards.splice(i,0,Response.data[i].article);
                  this.bestuserImage.splice(i,0,this.serverURL + Response.data[i].article.profileImg);
                }
              }
          })
        if(this.search=="　"){
          axios.get('/article/articlescount?openScope=public' +'&reviewRequest=' + this.type)
          .then((Res)=>{
          this.pagelength = parseInt(Math.ceil(Res.data/5));
        }),
        
        axios.get('/article/articles?limitStart=' + ((this.page-1)*5) + '&limitEnd=5&openScope=public&reviewRequest=' + this.type)
        .then((Response)=>{
          for(let i = 0; i < Response.data.length; i++){
          this.boards.splice(i,0,Response.data[i]);
          // if(Response.data[i].tmpTag.length>0)
          //   alert(Response.data[i].tmpTag[0].tmptagName)
          this.userImage.splice(i,0,this.serverURL + Response.data[i].article.profileImg);
          axios.get('/blog/blogs?userNo=' + Response.data[i].article.userNo)
          .then((Res)=>{
            this.blogboards.splice(i,0,Res.data[0]);
          })
          axios.get('/comment/commentscount?articleNo=' + Response.data[i].article.articleNo)
          .then((Res)=>{
            // this.commentlength[i] = Res.data;
            this.commentlength.splice(i,0,parseInt(Res.data))
          })
        }
        })
        }else{
          axios.get('/article/articlescount?searchValue='+ this.search +'&openScope=public' +'&reviewRequest=' + this.type)
            .then((Res)=>{
            this.pagelength = parseInt(Math.ceil(Res.data/5));
          }),
          
          axios.get('/article/articles?searchValue='+this.search + '&limitStart=' + ((this.page-1)*5) + '&limitEnd=5&openScope=public&reviewRequest=' + this.type)
          .then((Response)=>{
            for(let i = 0; i < Response.data.length; i++){
            this.boards.splice(i,0,Response.data[i]);
            // if(Response.data[i].tmpTag.length>0)
            //   alert(Response.data[i].tmpTag[0].tmptagName)
            this.userImage.splice(i,0,this.serverURL + Response.data[i].article.profileImg);
            axios.get('/blog/blogs?userNo=' + Response.data[i].article.userNo)
            .then((Res)=>{
              this.blogboards.splice(i,0,Res.data[0]);
            })
            axios.get('/comment/commentscount?articleNo=' + Response.data[i].article.articleNo)
            .then((Res)=>{
              // this.commentlength[i] = Res.data;
              this.commentlength.splice(i,0,Res.data)
            })
          }
          })
        }
      },
      tab_qna(){
        this.type=1;
        this.page = 1;
        this.boards = [];
        this.userImage = [];
        this.blogboards = [];
        this.commentlength = [];

        this.getboards();
      },
      tab_boardlist(){
        this.type=0;
        this.page = 1;
        this.boards = [];
        this.userImage = [];
        this.blogboards = [];
        this.commentlength = [];

        this.getboards();
      },
      clickpage() {
        // this.$router.push({ name: 'Mainboard', params: { searchValue: this.$route.params.searchValue, page:this.page, type:this.type, key: this.$route.params.searchValue+this.page }});
        this.boards = [];
        this.userImage = [];
        this.blogboards = [];
        this.commentlength = [];
        this.getboards();
      },
      removeIframe(){
        setTimeout(() => {
          if(document.getElementsByTagName('iframe')){
            let iframes = document.getElementsByTagName('iframe');
            let len = document.getElementsByTagName('iframe').length;
            for(let i = 0; i < len; i++){
              iframes[0].parentElement.removeChild(iframes[0]);
            } 
          }else{
            alert('dd')
            setTimeout(this.removeIframe, 100);
          }
        }, 100)
      },
      removeCode(){
        setTimeout(() => {
          if(document.getElementsByTagName('code')){
            let codes = document.getElementsByTagName('code');
            let len = document.getElementsByTagName('code').length;
            for(let i = 0; i < len; i++){
              codes[0].parentElement.parentElement.removeChild(codes[0].parentElement);
            } 
          }else{
            alert('dd')
            setTimeout(this.removeCode, 100);
          }
        }, 100)
      }
  },
  updated(){
      window.Prism.highlightAll();
      this.removeIframe();
      this.removeCode();
    }

};
</script>

<style scoped>
#board_body {
  width: 85%;
  margin: 0 100px;
}
#search {
  margin: 0 auto;
}
#card {
  padding: 0 20px;
}
p {
  margin-bottom: 0px;
}
hr {
  margin: 16px 20px;
  padding: 0 20px;
  border: 0;
  height: 2px;
  background: #ccc;
}
#btn_select {
  display: inline-block;
}
#btn {
  float: left;
  margin: 10px;
}
#select {
  float: right;
  padding: 0;
}
.cols {
  padding: 0 0px;
}
#tab {
  display: inline;
}
#left {
  padding: 0 0px;
  width: 50%;
  float: left;
  box-sizing: border-box;
}
#right {
  padding: 0 0px;
  width: 50%;
  float: right;
  box-sizing: border-box;
}
#board_title {
  margin: 0 0px;
}
#page {
  text-align: center;
  margin: 0 auto;
  width: 100%;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .8;
  position: absolute;
  width: 100%;
}
#reco {
  margin: 0 20px;
}
.card-img-left {
  height: 200px;
  width: 200px;
}
#card_detail {
  height: 150px;
}
#card_content {
  width:1050px; 
  display:-webkit-box;
  -webkit-line-clamp:3;
  -webkit-box-orient:vertical;
  line-height:20px;
  height:37px;
  justify-content:space-between; 
  overflow:hidden; 
  white-space:normal;
  text-overflow:ellipsis;
  word-break:break-all;
  table-layout:fixed;
}
.mainboard{
  width: 1560px;
}
</style>