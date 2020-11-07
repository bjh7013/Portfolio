<template>
  <div class="mainmain">
    <!-- 네브바 -->
    <nav-header></nav-header>
    <!-- select box , 검색-->
    <v-container id="search" fluid>
      <v-row align="center">
        <v-col class="d-flex" cols="3" sm="3">
          <v-select
            :items="items1"
            label="분류"
            v-model="value"
          ></v-select>
        </v-col>
        <v-col cols="8">
          <!-- 에러 원인 v-model="search" 값이 없음(지우거나 search 선언하거나) -->
          <!-- property or method "search" is not defined on the instance but referenced during render. -->
          <v-text-field v-model.trim="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="searchdata"></v-text-field>
        </v-col>
        <v-btn type="button" icon @click="searchdata"><v-icon>mdi-magnify</v-icon></v-btn>
      </v-row>
    </v-container>
    <!-- 좌측 게시판 -->
    <v-layout row>
      <v-flex xs6>
        <div id="left">
          <!-- 게시판 상단 -->
          <v-layout>
            <v-flex>
              <div class="col-9" id="title">
                <h3 style="text-decoration: none; color:black">게시판</h3>
              </div>
              <br><br><br>
              <div>
                <p style="text-align:left">이 달의 추천글</p>
              </div>
            </v-flex>
          </v-layout>
          <!-- 이 달의 추천글 -->
          <v-layout>
            <div>
              <v-row id="reco1">
                <v-flex xs4 v-for="(board,index) in bestboards" :key="board.boardNo">
                  <v-hover v-slot:default="{ hover }">
                    <v-card class="auto" color="grey lighten-4" max-width="200" max-height="250" @click="toArticle(board)">
                      <v-img max-width="200" height="250" :src="serverURL + board.thumbNail" img-alt="Image" img-top>
                        <v-expand-transition>
                          <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                          >
                            <div class="font-weight-light-6 title mb-2">
                              <v-avatar><img :src="bestuserImage[index]" style="width:50px; height:50px"></v-avatar><br>
                              {{board.nickName}}<br>
                              <div style="font-size:13px">{{board.title | titlesize}}</div>
                            </div>
                          </div>
                        </v-expand-transition>
                      </v-img>
                    </v-card>
                  </v-hover>
                </v-flex>
              </v-row>
            </div>
          </v-layout>
          <hr>

          <!-- 게시판 하단 -->
          <div>
            <!-- 게시판 탭 -->
            <v-tabs background-color="white" color="deep-purple accent-4" left>
              <v-tab @click="yesquestion">게시글
              </v-tab>
              <v-tab @click="noquestion">알고싶어요!
              </v-tab>
            </v-tabs>
            <!-- 게시글 카드 -->
            <div xs4>
              <!-- 게시글 타이틀 -->
              <b-card :img-src="serverURL + board.thumbNail" img-alt="Card image" img-left style="height:153px; margin:0px 0px 17px 0px;" v-for="(board,index) in boards" :key="board.boardNo">
                <b-card-title @click="toArticle(board)">
                  <button>{{board.title | titlesize}}</button>
                </b-card-title>
                <!-- 게시글 내용 -->
                <b-card-text @click="toArticle(board)">
                  <div
                    style="width:450px;
                    display:-webkit-box;
                    -webkit-line-clamp:3;
                    -webkit-box-orient:vertical;
                    line-height:20px;
                    height:45px;
                    justify-content:space-between; 
                    overflow:hidden; 
                    white-space:normal;
                    text-overflow:ellipsis;
                    word-break:break-all;
                    table-layout:fixed;"
                  >
                    <button><v-html v-html=board.content></v-html></button>
                  </div>
                  <!-- 게시글 작성자 -->
                  <div @click="toArticle(board)">
                    <v-row>
                      <v-col cols="9">
                        <div style="text-align:left">
                          <v-avatar style="width:30px; height:30px;"><img :src="userImage[index]" style="width:30px; height:30px;"></v-avatar>
                          <b>{{blogboards[index].blogName}}</b>
                          by.{{board.nickName}}
                        </div>
                      </v-col>
                      <v-col cols="3">
                        <div style="padding: 4px 4px;">
                          <v-icon small color="red">mdi-heart</v-icon><span style="color:grey;">{{board.likeCount}}</span>
                          &nbsp;<v-icon small>mdi-comment-text-outline</v-icon><span style="color:grey;">{{commentlength[index]}}</span>
                        </div>
                      </v-col>
                    </v-row>
                  </div>
                </b-card-text>
                <br>
              </b-card>
            </div>

            
          </div>
          <br>

          <!-- 페이지네이션 -->
          <v-col cols="12">
            <v-container id="page" class="max-width" @click="clickboardpage(status)">
              <v-pagination
                v-model="pageboard"
                :length="boardlength"
                :page="pageboard"
                :total-visible="totalVisible"
              ></v-pagination>
              </v-container>
          </v-col>
        </div>
      </v-flex>

      <v-flex xs6>
        <!-- 작품 상단 -->
        <div id="right">
          <div class="col-9" id="title">
            <h3 style="text-decoration: none; color:black">SSAZIT인들의 작품</h3>
          </div>
          <br><br><br>
          <div>
            <p style="text-align:left">이 달의 작품</p>
          </div>
          <!-- 이 달의 작품 (애니메이션) -->
          <div style="padding-top:30px;">
            <section class="container1" style="height:220px;">
              <div id="carousel">
                <v-hover v-slot:default="{ hover }" v-for="(book,index) in bestbooks" :key="book.bookNo">
                  <div class="book1" @click="toBook(book,bestbooksInfo[index])">
                    <div>
                      <figure class="front"><v-img style="height:190px;width:260px;" :src="bestbookfrontcover[index]" img-alt="Image" img-top>
                        <v-expand-transition>
                          <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                          >
                            <!-- <div class="font-weight-light-6 title mb-2">
                              <v-avatar><img :src="bestbookuserImage[index]" style="width:50px; height:50px"></v-avatar><br>
                              {{bestbooksInfo[index].nickName}}<br>
                              <div style="font-size:13px">{{bestbook.title}}</div>
                            </div> -->
                            <div class="font-weight-light-6 title mb-2">{{bestbooksInfo[index].nickName}}님의 작품</div>
                          </div>
                        </v-expand-transition>
                      </v-img></figure>
                      <figure class="back"><v-img style="height:190px;width:260px;" :src="bestbookendcover[index]" img-alt="Image" img-top>
                        <v-expand-transition>
                          <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                          >
                            <div class="font-weight-light-6 title mb-2">{{bestbooksInfo[index].nickName}}님의 작품</div>
                          </div>
                        </v-expand-transition>
                      </v-img></figure>
                      <figure class="left1"><div class="text">Rank{{index+1}}</div></figure>
                      <figure class="right1"></figure>
                    </div>
                  </div>
                </v-hover>
              </div> 
            </section>
          </div>
          <hr>
          <br>

          <!-- 작품 하단 -->
          <!-- <div id="book_right"> -->
            <!-- <a>버튼</a> -->
            <!-- <div v-for="(book,index) in books" :key="book.bookNo">
              <v-card class="elevation-6" style="background-color:#FFFAF0" v-if="index%2==0 && index%4==2">
                <v-card-title class="layout justify-center" @click="toBook(book,booksInfo[index])">
                  {{book.bookTitle | booktitlesize}}
                </v-card-title>
                <v-card-text @click="toBook(book,booksInfo[index])">
                  <img id="book" style="width:117px;height:160px" :src="bookendcover[index]"/>
                  <img id="book" style="width:16px;height:160px" :src="bookmiddlecover[index]"/>
                  <img id="book" style="width:117px;height:160px" :src="bookfrontcover[index]"/>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar><img :src="bookuserImage[index]" style="width:30px; height:30px"></v-avatar>
                        {{booksInfo[index].nickName}}작가
                      </div>
                    </v-col>
                    <v-col cols="4" style="padding:10px 30px 0px 0px;">
                      <div style="text-align:right">
                        <v-icon color="red">mdi-heart</v-icon><span style="color:grey;">{{book.likeCount}}</span>
                        &nbsp;<v-icon>mdi-comment-text-outline</v-icon><span style="color:grey;">{{commentlengthbook[index]}}</span>
                      </div>
                    </v-col>
                  </v-row>
                </v-card-footer>
              </v-card>
              <v-card class="elevation-6" style="background-color:#F0FFFF" v-if="index%2==0 && index%4==0">
                <v-card-title class="layout justify-center" @click="toBook(book,booksInfo[index])">
                  {{book.bookTitle | booktitlesize}}
                </v-card-title>
                <v-card-text @click="toBook(book,booksInfo[index])">
                  <img id="book" style="width:117px;height:160px" :src="bookendcover[index]"/>
                  <img id="book" style="width:16px;height:160px" :src="bookmiddlecover[index]"/>
                  <img id="book" style="width:117px;height:160px" :src="bookfrontcover[index]"/>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar><img :src="bookuserImage[index]" style="width:30px; height:30px"></v-avatar>
                        {{booksInfo[index].nickName}}작가
                      </div>
                    </v-col>
                    <v-col cols="4" style="padding:10px 30px 0px 0px;">
                      <div style="text-align:right">
                        <v-icon color="red">mdi-heart</v-icon><span style="color:grey;">{{book.likeCount}}</span>
                        &nbsp;<v-icon>mdi-comment-text-outline</v-icon><span style="color:grey;">{{commentlengthbook[index]}}</span>
                      </div>
                    </v-col>
                  </v-row>
                </v-card-footer>
              </v-card>
              <br>
              <div>
                <v-row id="bk-list" class="bk-list clearfix row">
                  <li class="col-lg-4 col-md-6 col-sm-12">
                    <div class="bk-book book-1 bk-bookdefault">
                      <div class="bk-front">
                        <div class="bk-cover">
                          <v-hover>
                            <v-card class="auto" color="grey lighten-4" width="200" height="250">
                              <v-img style="width:200px; height:250px" :src="bookfrontcover[index]" @click="toBook(book,booksInfo[index])">
                                <v-expand-transition style="vertical-align:bottom; align-items:end">
                                  <div>
                                    <div class="font-weight-light-6 title mb-2" style="width:100%; position:fixed; bottom:0; background-color: rgba( 0, 0, 0, 0.6 );">
                                      <p class="pt-3 mb-0" style="text-align:center; color:white; font-weight:bolder;">{{book.bookTitle | booktitlesize}}</p>
                                      <p class="mb-3" style="text-align:center; font-size:14px; color:white;">{{booksInfo[index].nickName}}</p>
                                    </div>
                                  </div>
                                </v-expand-transition>
                              </v-img>
                            </v-card>
                          </v-hover>
                        </div>
                      </div>
                      <div class="bk-left" style="background-color:#EEEE;">
                        <v-img style="width:200px; height:250px" :src="bookmiddlecover[index]">
                          <h2>
                            <span>{{book.bookTitle}}</span>
                            <span>{{book.likeCount}}<v-icon color="red">mdi-heart</v-icon>/{{commentlengthbook[index]}}<v-icon>mdi-comment-text-outline</v-icon></span>
                          </h2>
                        </v-img>
                      </div>
                    </div>
                  </li>
                </v-row>
              </div>
              <br>
            </div>
          </div> -->
          
          <!-- <div id="book_left" >
            <div v-for="(book,index) in books" :key="book.bookNo">
              <v-card class="elevation-6" style="background-color:#FFFAF0" v-if="index%2==1 && index%4==1">
                <v-card-title class="layout justify-center" @click="toBook(book,booksInfo[index])">
                  {{book.bookTitle | booktitlesize}}
                </v-card-title>
                <v-card-text @click="toBook(book,booksInfo[index])">
                  <img id="book" style="width:117px;height:160px" :src="bookendcover[index]"/>
                  <img id="book" style="width:16px;height:160px" :src="bookmiddlecover[index]"/>
                  <img id="book" style="width:117px;height:160px" :src="bookfrontcover[index]"/>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar><img :src="bookuserImage[index]" style="width:30px; height:30px"></v-avatar>
                        {{booksInfo[index].nickName}}작가
                      </div>
                    </v-col>
                    <v-col cols="4" style="padding:10px 30px 0px 0px;">
                      <div style="text-align:right">
                        <v-icon color="red">mdi-heart</v-icon><span style="color:grey;">{{book.likeCount}}</span>
                        &nbsp;<v-icon>mdi-comment-text-outline</v-icon><span style="color:grey;">{{commentlengthbook[index]}}</span>
                      </div>
                    </v-col>
                  </v-row>
                </v-card-footer>
              </v-card>
              <v-card class="elevation-6" style="background-color:#F0FFFF" v-if="index%2==1 && index%4==3">
                <v-card-title class="layout justify-center" @click="toBook(book,booksInfo[index])">
                  {{book.bookTitle | booktitlesize}}
                </v-card-title>
                <v-card-text @click="toBook(book,booksInfo[index])">
                  <img id="book" style="width:117px;height:160px" :src="bookendcover[index]"/>
                  <img id="book" style="width:16px;height:160px" :src="bookmiddlecover[index]"/>
                  <img id="book" style="width:117px;height:160px" :src="bookfrontcover[index]"/>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar><img :src="bookuserImage[index]" style="width:30px; height:30px"></v-avatar>
                        {{booksInfo[index].nickName}}작가
                      </div>
                    </v-col>
                    <v-col cols="4" style="padding:10px 30px 0px 0px;">
                      <div style="text-align:right">
                        <v-icon color="red">mdi-heart</v-icon><span style="color:grey;">{{book.likeCount}}</span>
                        &nbsp;<v-icon>mdi-comment-text-outline</v-icon><span style="color:grey;">{{commentlengthbook[index]}}</span>
                      </div>
                    </v-col>
                  </v-row>
                </v-card-footer>
              </v-card>
              <br>
            </div>

          </div> -->
          <div>
            <v-row class="book-list" style="width:100%;">
              <div v-for="(book,index) in books" :key="book.bookNo" class="m-0 col-6">
              <v-card>
                  <v-row id="bk-list" class="bk-list clearfix row" style="padding-left:65px;">
                    <li>
                      <div class="bk-book book-1 bk-bookdefault">
                        <div class="bk-front">
                          <div class="bk-cover">
                            <v-hover>
                              <v-card class="auto" color="grey lighten-4" width="200" height="250">
                                <v-img style="width:200px; height:250px" :src="bookfrontcover[index]" @click="toBook(book,booksInfo[index])">
                                  <v-expand-transition style="vertical-align:bottom; align-items:end">
                                    <div>
                                      <div class="font-weight-light-6 title mb-2" style="width:100%; position:fixed; bottom:0; background-color: rgba( 0, 0, 0, 0.6 );">
                                        <p class="pt-3 mb-0" style="text-align:center; color:white; font-weight:bolder;">{{book.bookTitle | booktitlesize}}</p>
                                        <p class="mb-3" style="text-align:center; font-size:14px; color:white;">{{booksInfo[index].nickName}}</p>
                                      </div>
                                    </div>
                                  </v-expand-transition>
                                </v-img>
                              </v-card>
                            </v-hover>
                          </div>
                        </div>
                        <div class="bk-left" style="background-color:#EEEE;">
                          <v-img style="width:200px; height:250px" :src="bookmiddlecover[index]">
                            <h2>
                              <span>{{book.bookTitle}}</span>
                              <span>{{booksInfo[index].nickName}}</span>
                            </h2>
                          </v-img>
                        </div>
                      </div>
                    </li>
                  </v-row>
                  <v-card-footer>
                    <v-row>
                      <v-col cols="8" style="padding:0px 0px 0px 20px;">
                        <div style="text-align:left">
                          <v-avatar><img :src="bookuserImage[index]" style="width:30px; height:30px"></v-avatar>
                          {{booksInfo[index].nickName}}작가
                        </div>
                      </v-col>
                      <v-col cols="4" style="padding:10px 30px 0px 0px;">
                        <div style="text-align:right">
                          <v-icon color="red">mdi-heart</v-icon><span style="color:grey;">{{book.likeCount}}</span>
                          &nbsp;<v-icon>mdi-comment-text-outline</v-icon><span style="color:grey;">{{commentlengthbook[index]}}</span>
                        </div>
                      </v-col>
                    </v-row>
                  </v-card-footer>
                </v-card>
              </div>
                <br>
            </v-row>
          </div>

          <!-- 페이지네이션 -->
          <v-container id="pagebook" class="max-width" @click="clickbookpage">
              <v-pagination
                v-model="pagebook"
                :length="booklength"
                :page="pagebook"
                :total-visible="totalVisible"
              ></v-pagination>
          </v-container>
        </div>
      </v-flex>
    </v-layout>

    <!-- 푸터 -->
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
import axios from 'axios'
export default {
  name: "Mainpage", 
    data() {
      return{
        items1: ['게시판', '작품', '블로그', '유저'],
        items2: ['전체선택', '알고리즘', '웹'],
        items3: ['전체선택', '알고리즘', '웹'],
        selected1: '1',
        selected2: '1',
        rows: 100,
        page: 1,
        value : "",
        bestboards : [],
        bestuserImage : [],
        pagelength : [],
        boards : [],
        userImage : [],
        pageboard : 1,
        boardlength : 1,
        booklength : 1,
        totalVisible: 6,
        bestbooks: [],
        bestbookfrontcover: [],
        bestbookmiddlecover: [],
        bestbookendcover: [],
        books: [],
        bookfrontcover: [],
        bookmiddlecover: [],
        bookendcover: [],
        bestbooksInfo: [],
        booksInfo: [],
        pagebook : 1,
        bestbookuserImage: [],
        bookuserImage: [],
        blogboards: [],
        commentlength: [],
        commentlengthbook: [],
        question : true,
        status : 0,
        search: ""

      }
    },
  // created() {
  //     this.getboards()
  //     this.getbooks()
  // },
  components : {
    NavHeader,
    NavFooter
  },
  mounted() {
      this.getboards()
      this.getbooks()
  },
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    },
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  filters : {
    titlesize(value){
      if(value.length>15){
        // alert(value)
        return value.substring(0,15).concat("...");
      }else{
        return value
      }
    },
    booktitlesize(value){
      if(value.length>12){
        // alert(value)
        return value.substring(0,12).concat("...");
      }else{
        return value
      }
    }
  },
  methods : {
    searchdata(){
        if(this.serach == "" || !this.search){
          // alert("검색어 또는 분류를 입력하세요.");
          this.search ="　"
        }
        if(this.value==="게시판"){
          this.$router.push('/mainboard/'+ this.search + '/1/0');
        }else if(this.value==="작품"){
          this.$router.push('/mainbook/'+ this.search + '/1');
        }else if(this.value==="블로그"){
          this.$router.push('/mainblog/'+ this.search + '/1');
        }else if(this.value==="유저"){
          this.$router.push('/mainuser/'+ this.search + '/1');
        }
      },
    toArticle(data){
      this.$router.push('/bloghome/' + data.userId + '/articledetail/' + data.articleNo)
    },
    toBook(data,datauser){
      // alert(data);
      // alert(datauser);
      this.$router.push('/bloghome/' + datauser.userId + '/bookdetail/' + data.bookNo)
    },
    getboards(){
      axios.get('/article/articles?openScope=public&orderKey=likeCount')
        .then((Response)=>{
          this.bestboards = []
          this.bestuserImage = []
          if(Response.data.length>2){
            for(let i = 0; i < 3; i++){
              this.bestboards.splice(i,0,Response.data[i].article);
              this.bestuserImage.splice(i,0,this.serverURL + Response.data[i].article.profileImg);
            }
          }
          else{
            for(let i = 0; i < Response.data.length; i++){
              this.bestboards.splice(i,0,Response.data[i].article);
              this.bestuserImage.splice(i,0,this.serverURL + Response.data[i].article.profileImg);
          }
        }
      }),

      axios.get('/article/articlescount?reviewRequest=0&openScope=public')
        .then((Res)=>{
        this.boardlength = parseInt(Math.ceil(Res.data/4))
      }),

      axios.get('/article/articles?limitStart=' + ((this.pageboard-1)*4) + '&limitEnd=4' + '&reviewRequest=0&openScope=public')
      .then((Response)=>{
        for(let i = 0; i < Response.data.length; i++){
          this.boards[i] = Response.data[i].article;
          this.userImage[i] = Response.data[i].article.profileImg;

          this.boards.splice(i,1,Response.data[i].article);
          this.userImage.splice(i,1,this.serverURL + Response.data[i].article.profileImg);
          axios.get('/blog/blogs?userNo=' + Response.data[i].article.userNo)
          .then((Res)=>{
            this.blogboards[i] = Res.data[0];
            this.blogboards.splice(i,1,Res.data[0]);
          })
          axios.get('/comment/commentscount?articleNo=' + Response.data[i].article.articleNo)
          .then((Res)=>{
            // this.commentlength[i] = Res.data;
            this.commentlength[i] = Res.data;
            this.commentlength.splice(i,1,this.commentlength[i])
          })
        }
        for(let i=Response.data.length; i<4; i++){
          this.boards.pop()
          this.userImage.pop()
          this.blogboards.pop()
          this.commentlength.pop()
        }
      })
    },
    getbooks(){
      axios.get('/book/books?openScope=public&orderKey=likeCount&orderValue=DESC')
      .then((Response)=>{
        // this.bestbooks = []
        // this.bestbookfrontcover = []
        // this.bestbookmiddlecover = []
        // this.bestbookendcover = []
        // this.bestbooksInfo = []
        // this.bestbookuserImage = []
        if(Response.data.length>8){
          // alert(Response.data.length)
            for(let i = 0; i < 9; i++){
              this.bestbooks[i] = Response.data[i].book
              this.bestbookfrontcover[i] = Response.data[i].book.coverFront
              this.bestbookmiddlecover[i] = Response.data[i].book.coverMiddle
              this.bestbookendcover[i] = Response.data[i].book.coverBack

              this.bestbooks.splice(i,1,Response.data[i].book)
              this.bestbookfrontcover.splice(i,1,this.serverURL + Response.data[i].book.coverFront);
              this.bestbookmiddlecover.splice(i,1,this.serverURL + Response.data[i].book.coverMiddle);
              this.bestbookendcover.splice(i,1,this.serverURL + Response.data[i].book.coverBack);
              axios.get(`user/listone/${Response.data[i].book.writer}`)
              .then((Res)=>{
                this.bestbooksInfo[i] = Res.data
                this.bestbookuserImage[i] = Response.data[i].book.profileImg
                this.bestbooksInfo.splice(i,1,Res.data)
                this.bestbookuserImage.splice(i,1,this.serverURL + Response.data[i].book.profileImg);
              })
            }
            for(let i=Response.data.length; i<9; i++){
              this.bestbooks.pop()
              this.bestbookfrontcover.pop()
              this.bestbookmiddlecover.pop()
              this.bestbookendcover.pop()
              this.bestbooksInfo.pop()
              this.bestbookuserImage.pop()
            }
          }else{
            for(let i = 0; i < Response.data.length; i++){
              this.bestbooks[i] = Response.data[i].book
              this.bestbookfrontcover[i] = Response.data[i].book.coverFront
              this.bestbookmiddlecover[i] = Response.data[i].book.coverMiddle
              this.bestbookendcover[i] = Response.data[i].book.coverBack

              this.bestbooks.splice(i,1,Response.data[i].book)
              this.bestbookfrontcover.splice(i,1,this.serverURL + Response.data[i].book.coverFront);
              this.bestbookmiddlecover.splice(i,1,this.serverURL + Response.data[i].book.coverMiddle);
              this.bestbookendcover.splice(i,1,this.serverURL + Response.data[i].book.coverBack);
              axios.get(`user/listone/${Response.data[i].book.writer}`)
              .then((Res)=>{
              this.bestbooksInfo[i] = Res.data
              this.bestbookuserImage[i] = Response.data[i].book.profileImg
              this.bestbooksInfo.splice(i,1,Res.data)
              this.bestbookuserImage.splice(i,1,this.serverURL + Response.data[i].book.profileImg);
              })
            }
            for(let i=Response.data.length; i<9; i++){
              this.bestbooks.pop()
              this.bestbookfrontcover.pop()
              this.bestbookmiddlecover.pop()
              this.bestbookendcover.pop()
              this.bestbooksInfo.pop()
              this.bestbookuserImage.pop()
            }
          }
      })
      axios.get('/book/books?limitStart=' + ((this.pagebook-1)*4) + '&limitEnd=4&openScope=public')
      .then((Response)=>{
        
        for(let i=0;i<Response.data.length;i++){
          this.books[i] = Response.data[i].book
          this.bookfrontcover[i] = Response.data[i].book.coverFront
          this.bookmiddlecover[i] = Response.data[i].book.coverMiddle
          this.bookendcover[i] = Response.data[i].book.coverBack

          this.books.splice(i,1,Response.data[i].book)
          this.bookfrontcover.splice(i,1,this.serverURL + Response.data[i].book.coverFront);
          this.bookmiddlecover.splice(i,1,this.serverURL + Response.data[i].book.coverMiddle);
          this.bookendcover.splice(i,1,this.serverURL + Response.data[i].book.coverBack);
          axios.get(`user/listone/${Response.data[i].book.writer}`)
          .then((Res)=>{
            this.booksInfo[i] = Res.data
            this.bookuserImage[i] = Res.data.profileImg
            
            this.booksInfo.splice(i,1,this.booksInfo[i])
            this.bookuserImage.splice(i,1,this.serverURL + Res.data.profileImg);
          })
          axios.get('/comment/commentscount?bookNo=' + Response.data[i].book.bookNo)
            .then((Res)=>{
              this.commentlengthbook[i] = Res.data
              this.commentlengthbook.splice(i,1,Res.data)
          })
        }

        for(let i=Response.data.length; i<4; i++){
            this.books.pop()
            this.bookfrontcover.pop()
            this.bookmiddlecover.pop()
            this.bookendcover.pop()
            this.booksInfo.pop()
            this.bookuserImage.pop()
            this.commentlengthbook.pop()
        }
      }),
      axios.get('/book/bookscount?openScope=public')
        .then((Res)=>{
        this.booklength = parseInt(Math.ceil(Res.data/4))
      })
    },
    clickboardpage(value) {
      if(value==0){
        this.getboards();
      }else{
        axios.get('/article/articlescount?reviewRequest=1&openScope=public')
        .then((Res)=>{
        this.boardlength = parseInt(Math.ceil(Res.data/4))
      }),

      axios.get('/article/articles?limitStart=' + ((this.pageboard-1)*4) + '&limitEnd=4' + '&reviewRequest=1&openScope=public')
      .then((Response)=>{
        // console.log(Response);
        // console.log("드러옴");
        for(let i = 0; i < Response.data.length; i++){
          this.boards[i] = Response.data[i].article;
          this.userImage[i] = Response.data[i].article.profileImg;

          this.boards.splice(i,1,Response.data[i].article);
          this.userImage.splice(i,1,this.serverURL + Response.data[i].article.profileImg);
          axios.get('/blog/blogs?userNo=' + Response.data[i].article.userNo)
          .then((Res)=>{
            this.blogboards[i] = Res.data[0];
            this.blogboards.splice(i,1,Res.data[0]);
          })
          axios.get('/comment/commentscount?articleNo=' + Response.data[i].article.articleNo)
          .then((Res)=>{
            if(Res.data.length>0){
              this.commentlength[i] = Res.data;
              this.commentlength.splice(i,1,Res.data)
            }
          })
        }

        for(let i=Response.data.length; i<4; i++){
            this.boards.pop()
            this.userImage.pop()
            this.blogboards.pop()
            this.commentlength.pop()
        }
      })
      }
    },
    async clickbookpage(){
        // this.books = [];
        // this.bookfrontcover = [];
        // this.bookmiddlecover = [];
        // this.bookendcover = [];
        // this.booksInfo = [];
        // this.bookuserImage = [];
        await this.getbooks();
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
    },
    yesquestion(){
      this.status = 0;
      // this.boards=[];
      // this.userImage=[];
      // this.blogboards=[];
      this.getboards();
      this.pageboard=1;
      this.question = true;
    },
    noquestion(){
      this.status = 1;
      if(this.boards.length>-1){
      axios.get('/article/articlescount?reviewRequest=1&openScope=public')
        .then((Res)=>{
        this.boardlength = parseInt(Math.ceil(Res.data/4))
      }),

      axios.get('/article/articles?limitStart=0&limitEnd=4&reviewRequest=1&openScope=public')
      .then((Response)=>{
        // console.log(Response);
        // console.log("드러옴");
        for(let i = 0; i < Response.data.length; i++){
          this.boards[i]=Response.data[i].article;
          this.userImage[i]=Response.data[i].article.profileImg;

          this.boards.splice(i,1,Response.data[i].article);
          this.userImage.splice(i,1,this.serverURL + Response.data[i].article.profileImg);
          axios.get('/blog/blogs?userNo=' + Response.data[i].article.userNo)
          .then((Res)=>{
            this.blogboards[i]=Res.data[0];
            this.blogboards.splice(i,1,Res.data[0]);
          })
          axios.get('/comment/commentscount?articleNo=' + Response.data[i].article.articleNo)
          .then((Res)=>{
            // this.commentlength[i] = Res.data;
            this.commentlength[i]=Res.data;
            this.commentlength.splice(i,1,Res.data);
          })
        }
        for(let i=Response.data.length; i<4; i++){
            this.boards.pop()
            this.userImage.pop()
            this.blogboards.pop()
            this.commentlength.pop()
        }
      })
      this.pageboard=1;
      this.question = false;
      }
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
.v-responsive__sizer {
  padding-bottom: 0px !important;
}
@media screen and (min-width: 768px) {
  .page-wrapper.default .page-content {
    padding-left: 260px;
    margin-right: 50px;
  }
  .page-wrapper.navon .page-content {
    animation: righting ease-in-out 0.5s 1 alternate;
    padding-left: 260px;
    margin-right: 50px;
  }
  .page-wrapper.navoff .page-content {
  animation: lefting ease-in-out 0.5s 1 alternate;
  padding-left: 0px;
  margin-right: 0px;
  }
}
li {
  display: inline-block;
  margin: 0 10px;
}
#search {
  margin: 0 auto;
}
#left {
  float: left;
  padding: 12px 40px 12px 100px; 
  box-sizing: border-box;
  border-right: 2px solid gray;
}
#right {
  float: right;
  padding: 12px 100px 12px 40px; 
  box-sizing: border-box;
}
#title {
  padding: 0 10;
  float: left;
  border-left: 4px solid gray;
}
h3 {
  margin: 0 0 0;
  float: left;
}
#title_select {
  padding: 0;
}
hr {
   border: 0;
   height: 2px;
   background: #ccc;
}
#btn {
  text-align: left;
  margin: 10px;
}
#page {
  text-align: center;
  margin: 0;
  width: 100%;
}
#book {
  width: 250px;
}
#book_left {
  float: left;
  top: 20px;
  width: 300px;
}
#book_right {
  float: right;
  padding: 10 0px;
  width: 300px;
}
.v-card__title {
  height: 100px;
  text-align: center;
}
.v-card__text {
  height: 180px;
}
.v-card-footer {
  height: 20px;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .8;
  position: absolute;
  width: 100%;
}
#reco1 {
  margin: 0 15px;
}
#reco2 {
  margin: 0 30px;
}


/* 책 돌아가는 애니메이션 */
.container1 {
  margin-top: 50px;
  left : 260px;
  width: 100px;
  height: 216px;
  position: relative;
  perspective: 1000px;
}
.book1 {
  width: 100%;
  height: 100%;
  position: absolute;
  transform-style: preserve-3d;
  -webkit-transform-style: preserve-3d;
}
.book1 figure {
  width: 140px;
  height: 190px;
  display: block;
  position: absolute;
  padding: 0px;
}
.text {
  -webkit-transform: rotate(90deg);
  -ms-transform: rotate(90deg);
  transform: rotate(90deg);
  margin-top: 20px;
  color: #FFFF99;
  width: 100%;
  font-size: 10px;
}
.book1 .front {
  -webkit-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(54px);
  -moz-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(54px);
  -o-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(54px);
  -ms-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(54px);
  transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(54px);
  background-color: #dddddd;
  background-size: 100%;
  box-shadow: inset 4px 0 10px rgba(0, 0, 0, 0.1);
  z-index: 2;
}
.book1 .front:after {
  content: '';
  position: absolute;
  top: 0;
  left: 10px;
  bottom: 0;
  background: rgba(0, 0, 0, 0.06);
  box-shadow: 1px 0 3px rgba(255, 255, 255, 0.1);
}
.book1 .back {
  -webkit-transform: rotateX(0) rotateY(-180deg) translateX(0) translateY(0) translateZ(-32px);
  -moz-transform: rotateX(0) rotateY(-180deg) translateX(0) translateY(0) translateZ(-32px);
  -o-transform: rotateX(0) rotateY(-180deg) translateX(0) translateY(0) translateZ(-32px);
  -ms-transform: rotateX(0) rotateY(-180deg) translateX(0) translateY(0) translateZ(-32px);
  transform: rotateX(0) rotateY(-180deg) translateX(0) translateY(0) translateZ(-32px);
  background-color: #dddddd;
  background-size: 100%;
}
.book1 .right1 {
  -webkit-transform: rotateX(0) rotateY(90deg) translateX(-43px) translateY(0) translateZ(130px);
  -moz-transform: rotateX(0) rotateY(90deg) translateX(-43px) translateY(0) translateZ(130px);
  -o-transform: rotateX(0) rotateY(90deg) translateX(-43px) translateY(0) translateZ(130px);
  -ms-transform: rotateX(0) rotateY(90deg) translateX(-43px) translateY(0) translateZ(130px);
  transform: rotateX(0) rotateY(90deg) translateX(-43px) translateY(0) translateZ(130px);
  background-color: #fff;
  width: 20px;
}
.book1 .left1 {
  -webkit-transform: rotateX(0) rotateY(-93deg) translateX(45px) translateY(0) translateZ(8px);
  -moz-transform: rotateX(0) rotateY(-93deg) translateX(45px) translateY(0) translateZ(8px);
  -o-transform: rotateX(0) rotateY(-93deg) translateX(45px) translateY(0) translateZ(8px);
  -ms-transform: rotateX(0) rotateY(-93deg) translateX(45px) translateY(0) translateZ(8px);
  transform: rotateX(0) rotateY(-93deg) translateX(45px) translateY(0) translateZ(8px);
  background-color: black;
  width: 20px;
}
.book1 {
  -webkit-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(0px);
  -moz-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(50px);
  -o-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(50px);
  -ms-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(50px);
  transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(50px);
}

#carousel .book1:nth-child(1) {
  -webkit-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(0) translateX(0) translateY(0) translateZ(200px);
  z-index: 8;
}
#carousel .book1:nth-child(2) {
  -webkit-transform: rotateX(0) rotateY(40deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(40deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(40deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(40deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(40deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 7;
}
#carousel .book1:nth-child(3) {
  -webkit-transform: rotateX(0) rotateY(80deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(80deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(80deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(80deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(80deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 6;
}
#carousel .book1:nth-child(4) {
  -webkit-transform: rotateX(0) rotateY(120deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(120deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(120deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(120deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(120deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 5;
}
#carousel .book1:nth-child(5) {
  -webkit-transform: rotateX(0) rotateY(160deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(160deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(160deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(160deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(160deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 5;
}
#carousel .book1:nth-child(6) {
  -webkit-transform: rotateX(0) rotateY(200deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(200deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(200deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(200deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(200deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 5;
}
#carousel .book1:nth-child(7) {
  -webkit-transform: rotateX(0) rotateY(240deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(240deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(240deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(240deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(240deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 5;
}
#carousel .book1:nth-child(8) {
  -webkit-transform: rotateX(0) rotateY(280deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(280deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(280deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(280deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(280deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 5;
}
#carousel .book1:nth-child(9) {
  -webkit-transform: rotateX(0) rotateY(320deg) translateX(0) translateY(0) translateZ(200px);
  -moz-transform: rotateX(0) rotateY(320deg) translateX(0) translateY(0) translateZ(200px);
  -o-transform: rotateX(0) rotateY(320deg) translateX(0) translateY(0) translateZ(200px);
  -ms-transform: rotateX(0) rotateY(320deg) translateX(0) translateY(0) translateZ(200px);
  transform: rotateX(0) rotateY(320deg) translateX(0) translateY(0) translateZ(200px);
  z-index: 5;
}
@-webkit-keyframes spincube {
  100% {
    -webkit-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    -moz-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    -o-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    -ms-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
  }
}
@keyframes spincube {
  100% {
    -webkit-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    -moz-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    -o-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    -ms-transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
    transform: rotateX(0) rotateY(-360deg) translateX(0) translateY(0) translateZ(0);
  }
}
#carousel {
  -webkit-animation-name: spincube;
  animation-name: spincube;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
  -webkit-animation-iteration-count: infinite;
  animation-iteration-count: infinite;
  -webkit-animation-duration: 30s;
  animation-duration: 30s;
  transform-style: preserve-3d;
  -webkit-transform-style: preserve-3d;
  transform-origin: 60px 60px 0;
  -webkit-transform-origin: 60px 60px 0;
}
.card-img-left {
  height: 150px;
  width: 150px; 
}
.mainmain {
  width: 1540px;
  overflow:auto;
}





.clearfix:before,
.clearfix:after {
	content: " ";
	display: table;
}

.clearfix:after {
	clear: both;
}

.clearfix {
	*zoom: 1;
}

body {
	background: #f6f6f6;
	font-weight: 400;
	font-size: 15px;
	color: #333;
	/* overflow: hidden; */
	/* overflow-x: hidden; */
}

a {
	color: #555;
	text-decoration: none;
}

.container1 {
  height: 300px;
	max-width: 100%;
  margin: 0 0 0 25px;
  padding: 0px;
}

.bk-list li {
	position: relative;
	width: 240px;
	/* float: left; */
	/* z-index: 1; */
	margin: 10px 0px 10px 0;
}

.bk-list li .bk-book {
	/* position: absolute; */
	width: 100%;
	height: 250px;
	-webkit-transform-style: preserve-3d;
	transform-style: preserve-3d;
	-webkit-transition: -webkit-transform .5s;
	transition: transform .5s;
}

.bk-list li .bk-book.bk-bookdefault:hover {
	-webkit-transform: rotate3d(0, 1, 0, 35deg);
	transform: rotate3d(0, 1, 0, 35deg);
}

.bk-list li .bk-book > div,
.bk-list li .bk-front > div {
	display: block;
	position: absolute;
}

.bk-list li .bk-front {
	-webkit-transform-style: preserve-3d;
	transform-style: preserve-3d;
	-webkit-transform-origin: 0% 50%;
	transform-origin: 0% 50%;
	-webkit-transition: -webkit-transform .5s;
	transition: transform .5s;
	-webkit-transform: translate3d(0, 0, 20px);
	transform: translate3d(0, 0, 20px);
	z-index: 10;
  box-shadow: 10px 10px 30px rgba(0,0,0,0.3);
}

.bk-list li .bk-front > div {
	z-index: 1;
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
	-webkit-transform-style: preserve-3d;
	transform-style: preserve-3d;
}


.bk-list li .bk-front,
.bk-list li .bk-back,
.bk-list li .bk-front > div {
	width: 200px;
	height: 250px;
}

.bk-list li .bk-left,
.bk-list li .bk-right {
	width: 40px;
	left: -20px;
}

.bk-list li .bk-back {
	-webkit-transform: rotate3d(0, 1, 0, -180deg) translate3d(0, 0, 20px);
	transform: rotate3d(0, 1, 0, -180deg) translate3d(0, 0, 20px);
	box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.3);
	border-radius: 3px 0 0 3px;
}

.bk-list li .bk-left {
	height: 250px;
	-webkit-transform: rotate3d(0, 1, 0, -90deg);
	transform: rotate3d(0, 1, 0, -90deg);
}

.bk-list li .bk-front:after {
	content: '';
	/* position: absolute; */
	top: 1px;
	bottom: 1px;
	left: -1px;
	width: 1px;
}

.bk-list li .bk-cover:after,
.bk-list li .bk-back:after {
	content: '';
	/* position: absolute; */
	top: 0;
	left: 10px;
	bottom: 0;
	width: 3px;
	background: rgba(0, 0, 0, 0.06);
	box-shadow: 1px 0 3px rgba(255, 255, 255, 0.1);
}

.bk-list li .bk-back:after {
	left: auto;
	right: 10px;
}

.book-1 .bk-front > div,
.book-1 .bk-back,
.book-1 .bk-left,
.book-1 .bk-front:after {
	background-color: #CFD8DC;
}

.book-1 .bk-cover {
	
	background-repeat: no-repeat;
	background-position: 10px 40px;
  
}

.book-1 .bk-cover h2 span:first-child,
.book-1 .bk-left h2 span:first-child {
	text-transform: uppercase;
	font-weight: 250;
	font-size: 13px;
	padding-right: 20px;
}

.book-1 .bk-cover h2 span:first-child {
	display: block;
}

.book-1 .bk-left h2 {
    font-size: 10px;
    line-height: 40px;
    padding-right: 10px;
    text-align: right;
}

.bk-left h2 {
    width: 250px;
    height: 40px;
    transform-origin: 0 0;
    transform: rotate(90deg) translateY(-40px);
}
</style>