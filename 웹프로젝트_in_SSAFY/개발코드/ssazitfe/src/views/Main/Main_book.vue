<template>
  <div class="mainbook">
    <!-- 네브바 -->
    <nav-header></nav-header>
    <div id="board_body">

      <!-- select box , 검색-->
      <v-container id="search" fluid>
        <v-row align="center">
          <v-col class="d-flex" cols="3" sm="3">
            <v-select
              :items="items1"
              :value="items1[1]"
              v-model="value"
            ></v-select>
          </v-col>
          <v-col cols="7">
            <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="searchdata"></v-text-field>
          </v-col>
          <v-btn icon  @click="searchdata"><v-icon>mdi-magnify</v-icon></v-btn>
        </v-row>
      </v-container>

      <b-tabs content-class="mt-3">
        <!-- 탭 -->
        <b-tab title="게시판" @click="tab_board" > 
    
        </b-tab>
        <b-tab title="작품" @click="tab_book" active> 
          <div>
            <!-- 작품 상단 -->
            <div>
              <h5 style="text-align:left; margin:10px 0px 10px 50px;">이 달의 작품</h5>

              <!-- 이 달의 작품 (애니메이션) -->
              <div class="container1">
                <ul id="bk-list" class="bk-list clearfix">
                  <li v-for="(book,index) in bestbooks" :key="book.bookNo">
                    <div class="bk-book book-1 bk-bookdefault">
                      <div class="bk-front">
                        <!-- <div class="bk-cover-back"></div> -->
                        <div class="bk-cover">
                          <v-hover v-slot:default="{ hover }">
                            <v-card class="auto" color="grey lighten-4" width="200" height="250" @click="go_bestblog(index)"> <!-- 여기하자앎ㄴ아ㅣ런ㅇ마ㅣㄻㄴ어ㅣㅏㅠ-->
                              <v-img style="width:200px;height:250px" :src="bestbookfrontcover[index]">
                                <v-expand-transition>
                                  <div
                                    v-if="hover"
                                    class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                                    style="height: 100%;"
                                  >
                                    <div class="font-weight-light-6 title mb-2">
                                      <!-- <v-avatar><img :src="bestuserImage[index]" style="width:50px; height:50px"></v-avatar> -->
                                      <br>{{bestbooksInfo[index].nickName}}
                                      <br><br><p>{{book.bookTitle | booktitlesize}}</p>
                                      <!-- {{bestbooksInfo[index].nickName}}님의 작품<br>
                                    {{book.bookTitle}} -->
                                    </div>
                                  </div>
                                </v-expand-transition>
                              </v-img>
                            </v-card>
                          </v-hover>
                        </div>
                      </div>
                      <div class="bk-left">
                        <v-img style="width:200px;height:250px" :src="bestbookmiddlecover[index]"></v-img>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          <hr>
          <br>

          <!-- 작품 하단 -->
          <v-row class="book-list" style="width:100%;">
            <div class="col-3" v-for="(book,index) in books" :key="book.bookNo">
              <v-card>
                <v-row id="bk-list" class="bk-list clearfix row"  style="padding-left:65px; height:280px;">
                  <li>
                    <div class="bk-book book-1 bk-bookdefault">
                      <div class="bk-front">
                        <div class="bk-cover">
                          <v-hover>
                            <v-card class="auto" color="grey lighten-4" width="200" height="250">
                              <v-img style="width:200px; height:250px" :src="bookfrontcover[index]" @click="go_blog(index)">
                                <v-expand-transition style="vertical-align:bottom; align-items:end">
                                  <div>
                                    <div class="font-weight-light-6 title mb-2" style="width:100%; position:fixed; bottom:0; background-color: rgba( 0, 0, 0, 0.6 );">
                                      <p class="pt-3 mb-0" style="text-align:center; color:white; font-weight:bolder;">{{book.bookTitle}}</p>
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
                        <v-avatar>
                          <img :src="serverURL+booksInfo[index].profileImg" style="width:30px;height:30px">
                        </v-avatar>
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
              
              <!-- <v-card class="elevation-6" style="background-color:#FFFAF0" v-if="index%2==0 && index%8 < 4">
                <v-card-title class="layout justify-center" @click="go_blog(index)">
                  <v-button>{{book.bookTitle}}</v-button>
                </v-card-title>
                <v-card-text @click="go_blog(index)">
                  <p><img class="books" :src="bookendcover[index]" style="width:117px;height:160px"/>
                  <img class="books" :src="bookmiddlecover[index]" style="width:16px;height:160px"/>
                  <img class="books" :src="bookfrontcover[index]" style="width:117px;height:160px"/></p>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar>
                          <img :src="serverURL+booksInfo[index].profileImg" style="width:30px;height:30px">
                        </v-avatar>
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
              <v-card class="elevation-6" style="background-color:#F0FFFF" v-if="index%2==1 && index%8 < 4">
                <v-card-title class="layout justify-center" @click="go_blog(index)">
                  <v-button>{{book.bookTitle | booktitlesize}}</v-button>
                </v-card-title>
                <v-card-text @click="go_blog(index)">
                  <p><img class="books" :src="bookendcover[index]" style="width:117px;height:160px"/>
                  <img class="books" :src="bookmiddlecover[index]" style="width:16px;height:160px"/>
                  <img class="books" :src="bookfrontcover[index]" style="width:117px;height:160px"/></p>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar>
                          <img :src="serverURL+booksInfo[index].profileImg" style="width:30px;height:30px">
                        </v-avatar>
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
              <v-card class="elevation-6" style="background-color:#FFFAF0" v-if="index%2==1 && index%8 > 3">
                <v-card-title class="layout justify-center" @click="go_blog(index)">
                  <v-button>{{book.bookTitle}}</v-button>
                </v-card-title>
                <v-card-text @click="go_blog(index)">
                  <p><img class="books" :src="bookendcover[index]" style="width:117px;height:160px"/>
                  <img class="books" :src="bookmiddlecover[index]" style="width:16px;height:160px"/>
                  <img class="books" :src="bookfrontcover[index]" style="width:117px;height:160px"/></p>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar>
                          <img :src="serverURL+booksInfo[index].profileImg" style="width:30px;height:30px">
                        </v-avatar>
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
              <v-card class="elevation-6" style="background-color:#F0FFFF" v-if="index%2==0 && index%8 > 3">
                <v-card-title class="layout justify-center" @click="go_blog(index)">
                  <v-button>{{book.bookTitle}}</v-button>
                </v-card-title>
                <v-card-text @click="go_blog(index)">
                  <p><img class="books" :src="bookendcover[index]" style="width:117px;height:160px"/>
                  <img class="books" :src="bookmiddlecover[index]" style="width:16px;height:160px"/>
                  <img class="books" :src="bookfrontcover[index]" style="width:117px;height:160px"/></p>
                </v-card-text>
                <v-card-footer>
                  <v-row>
                    <v-col cols="8" style="padding:0px 0px 0px 20px;">
                      <div style="text-align:left">
                        <v-avatar>
                          <img :src="serverURL+booksInfo[index].profileImg" style="width:30px;height:30px">
                        </v-avatar>
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
              </v-card> -->

            </div>
          </v-row>


          <!-- 페이지네이션 -->
          <v-col>
            <v-container id="page" class="max-width" @click="clickpage">
              <v-pagination
                v-model="page"
                :circle="circle"
                :disabled="disabled"
                :length="pagelength"
                :next-icon="nextIcon"
                :prev-icon="prevIcon"
                :page="page"
                :total-visible="totalVisible"
              ></v-pagination>
            </v-container>
          </v-col>
        </div>
      </b-tab>
      <b-tab title="블로그" @click="tab_blog" :active="this.$route.params.select==3"> 
      </b-tab>
      <b-tab title="유저" @click="tab_user" :active="this.$route.params.select==4"> 
      
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
        value : "작품",
        search:this.$route.params.searchValue,
        page: this.$route.params.page,
        bestbooks: [],
        bestbooksInfo: [],
        books: [],
        booksInfo: [],
        bestbookfrontcover:[],
        bestbookmiddlecover:[],
        bestbookendcover:[],
        bookfrontcover:[],
        bookmiddlecover:[],
        bookendcover:[],
        pagelength:[],
        commentlengthbook: [],
      }
    },
  created(){
    this.get_book()
    this.search = "";
  },
  computed: { 
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  filters : {
    booktitlesize(value){
      if(value.length>12){
        // alert(value)
        return value.substring(0,12).concat("...");
      }else{
        return value
      }
    }
  },
  components : {
    NavHeader,
    NavFooter
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
      get_book(){
        axios.get('/book/books?openScope=public&orderKey=likeCount&orderValue=DESC')
        .then((Response)=>{
          if(Response.data.length>4){
            // alert(Response.data.length)
                for(let i = 0; i < 5; i++){
                  this.bestbooks.splice(i,1,Response.data[i].book)
                  this.bestbookfrontcover.splice(i,0,this.serverURL + Response.data[i].book.coverFront);
                  this.bestbookmiddlecover.splice(i,0,this.serverURL + Response.data[i].book.coverMiddle);
                  this.bestbookendcover.splice(i,0,this.serverURL + Response.data[i].book.coverBack);
                  axios.get(`user/listone/${Response.data[i].book.writer}`)
                  .then((Res)=>{
                      this.bestbooksInfo.splice(i,0,Res.data)
                  })
                }
            }else{
              for(let i = 0; i < Response.data.length; i++){
                      this.bestbooks.splice(i,0,Response.data[i].book)
                      this.bestbookfrontcover.splice(i,0,this.serverURL + Response.data[i].book.coverFront);
                      this.bestbookmiddlecover.splice(i,0,this.serverURL + Response.data[i].book.coverMiddle);
                      this.bestbookendcover.splice(i,0,this.serverURL + Response.data[i].book.coverBack);
                      axios.get(`user/listone/${Response.data[i].book.writer}`)
                      .then((Res)=>{
                      this.bestbooksInfo.splice(i,0,Res.data)
                      })
                }
            }
        })
        if(this.search=="　"){
          axios.get('/book/books?limitStart=' + ((this.$route.params.page-1)*12) + '&limitEnd=12' +'&openScope=public')
        .then((Response)=>{
          for(let i=0;i<Response.data.length;i++){
            this.books.splice(i,0,Response.data[i].book)
            this.bookfrontcover.splice(i,0,this.serverURL + Response.data[i].book.coverFront);
            this.bookmiddlecover.splice(i,0,this.serverURL + Response.data[i].book.coverMiddle);
            this.bookendcover.splice(i,0,this.serverURL + Response.data[i].book.coverBack);
            axios.get(`user/listone/${Response.data[i].book.writer}`)
            .then((Res)=>{
            this.booksInfo.splice(i,0,Res.data)
            })
            axios.get('/comment/commentscount?bookNo=' + Response.data[i].book.bookNo)
            .then((Res)=>{
                  this.commentlengthbook[i] = Res.data;
            })
          }
        })
        axios.get('/book/bookscount?openScope=public')
        .then((Res)=>{
            this.pagelength = parseInt(Math.ceil(Res.data/12));
        })
        }else{
          axios.get('/book/books?searchValue=' + this.$route.params.searchValue + '&limitStart=' + ((this.$route.params.page-1)*12) + '&limitEnd=12' +'&openScope=public')
          .then((Response)=>{
            for(let i=0;i<Response.data.length;i++){
              this.books.splice(i,0,Response.data[i].book)
              this.bookfrontcover.splice(i,0,this.serverURL + Response.data[i].book.coverFront);
              this.bookmiddlecover.splice(i,0,this.serverURL + Response.data[i].book.coverMiddle);
              this.bookendcover.splice(i,0,this.serverURL + Response.data[i].book.coverBack);
              axios.get(`user/listone/${Response.data[i].book.writer}`)
              .then((Res)=>{
              this.booksInfo.splice(i,0,Res.data)
              })
              axios.get('/comment/commentscount?bookNo=' + Response.data[i].book.bookNo)
              .then((Res)=>{
                    this.commentlengthbook[i] = Res.data;
              })
            }
          })
          axios.get('/book/bookscount?searchValue='+ this.$route.params.searchValue +'&openScope=public')
          .then((Res)=>{
              this.pagelength = parseInt(Math.ceil(Res.data/12));
          })

        }
      },
      go_bestblog(index) {
        this.$router.push('/bloghome/' + this.bestbooksInfo[index].userId + '/bookdetail/' + this.bestbooks[index].bookNo)
      },
      go_blog(index) {
        this.$router.push('/bloghome/' + this.booksInfo[index].userId + '/bookdetail/' + this.books[index].bookNo)
      },
      clickpage() {
        this.$router.push({ name: 'Mainbook', params: { searchValue: this.$route.params.searchValue, page:this.page ,key: this.$route.params.searchValue+this.page }});
      }
  }
  
  
};

// 작품 애니메이션 스크립트 코드
// https://codepen.io/Samanta_Zdravkovska/pen/mOrago?editors=0110

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
#page {
  text-align: center;
  margin: 0 auto;
  width: 100%;
}
#book {
  width: 250px;
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
#reco {
  margin: 0 20px;
}


/* 이 달의 작품 */
:after,
:before {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 0;
	margin: 0;
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
	overflow: auto;
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

/* .bk-info {
	position: relative;
	margin-top: 440px;
} */

/* .bk-info h3 {
	padding: 25px 0 10px;
	margin-bottom: 20px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.3);
	font-size: 26px;
} */

/* .bk-info h3 span:first-child {
	font-size: 14px;
	font-weight: 400;
	text-transform: uppercase;
	padding-bottom: 5px;
	display: block;
	color: #777;
} */

/* .bk-info p {
	line-height: 24px;
	color: #444;
	min-height: 160px;
} */

/* .bk-info button {
	background: #FC756F;
	border: none;
	color: #fff;
	display: inline-block;
	padding: 3px 15px;
	font-weight: 700;
	font-size: 14px;
	text-transform: uppercase;
	cursor: pointer;
	margin-right: 4px;
	box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.15);
}

.bk-info button.bk-active,
.bk-info button:active {
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8), inset 0 -1px 1px rgba(0, 0, 0, 0.2);
}

.no-touch .bk-info button:hover,
.bk-info button.bk-active {
	background: #d0544e;
} */

.bk-list li .bk-book {
	/* position: absolute; */
	width: 100%;
	height: 400px;
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
  /* 여기 */
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

/* .bk-list li .bk-top,
.bk-list li .bk-bottom {
	width: 200px;
	height: 40px;
	top: -15px;
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
} */

.bk-list li .bk-back {
	-webkit-transform: rotate3d(0, 1, 0, -180deg) translate3d(0, 0, 20px);
	transform: rotate3d(0, 1, 0, -180deg) translate3d(0, 0, 20px);
	box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.3);
	border-radius: 3px 0 0 3px;
}

/* 
.bk-list li .bk-cover-back {
	background-color: #000;
	-webkit-transform: rotate3d(0, 1, 0, -179deg);
	transform: rotate3d(0, 1, 0, -179deg);
} */

/* .bk-list li .bk-right {
	height: 390px;
	top: 5px;
	-webkit-transform: rotate3d(0, 1, 0, 90deg) translate3d(0, 0, 295px);
	-moz-transform: rotate3d(0, 1, 0, 90deg) translate3d(0, 0, 295px);
	transform: rotate3d(0, 1, 0, 90deg) translate3d(0, 0, 295px);
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
} */

.bk-list li .bk-left {
	height: 250px;
	-webkit-transform: rotate3d(0, 1, 0, -90deg);
	transform: rotate3d(0, 1, 0, -90deg);
}

/* .bk-list li .bk-top {
	-webkit-transform: rotate3d(1, 0, 0, 90deg);
	transform: rotate3d(1, 0, 0, 90deg);
} */

/* .bk-list li .bk-bottom {
	-webkit-transform: rotate3d(1, 0, 0, -90deg) translate3d(0, 0, 390px);
	transform: rotate3d(1, 0, 0, -90deg) translate3d(0, 0, 390px);
} */


/* Transform classes */

/* .bk-list li .bk-book.bk-viewback {
	-webkit-transform: translate3d(0, 0, 0px) rotate3d(0, 1, 0, 180deg);
	transform: translate3d(0, 0, 0px) rotate3d(0, 1, 0, 180deg);
} */

/* .bk-list li .bk-front > div {
	border-radius: 0 3px 3px 0;
	box-shadow: inset 4px 0 10px rgba(0, 0, 0, 0.1);
} */

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

/* .bk-left h2 {
	width: 300px;
	height: 40px;
	-webkit-transform-origin: 0 0;
	-moz-transform-origin: 0 0;
	transform-origin: 0 0;
	-webkit-transform: rotate(90deg) translateY(-40px);
	transform: rotate(90deg) translateY(-40px);
} */


/* Book 1 */

.book-1 .bk-front > div,
.book-1 .bk-back,
.book-1 .bk-left,
.book-1 .bk-front:after {
	background-color: #CFD8DC;
}

/*background-image: ;*/
.book-1 .bk-cover {
	
	background-repeat: no-repeat;
	background-position: 10px 40px;
  
}

/* .book-1 .bk-cover h2 {
	position: absolute;
	bottom: 0;
	right: 0;
	left: 0;
	padding: 15px;
	background: #000;
	color: #fff;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.1);
  opacity: .5;
} */

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

.mainbook{
  width: 1560px;
}

/* .book-1 .bk-cover h2 span:last-child,
.book-1 .bk-left h2 span:last-child {
	font-family: "Big Caslon", "Book Antiqua", "Palatino Linotype", Georgia, serif;
} */

/* .book-1 .bk-content p {
	font-family: Georgia, Times, "Times New Roman", serif;
} */

/* .book-1 .bk-left h2 {
	color: #fff;
	font-size: 15px;
	line-height: 40px;
	padding-right: 10px;
	text-align: right;
} */

/* .book-1 .bk-back p {
	color: #fff;
	font-size: 13px;
	padding: 40px;
	text-align: center;
	font-weight: 700;
} */


/* Fallbacks */

/* .no-csstransforms3d .bk-list li .bk-book > div,
.no-csstransforms3d .bk-list li .bk-book .bk-cover-back {
	display: inline-block;
} */

/* .no-csstransforms3d .bk-list li .bk-book > div.bk-front {
	display: block;
} */
/* 
.no-csstransforms3d .bk-info button,
.no-js .bk-info button {
	display: inline-block;
} */
</style>