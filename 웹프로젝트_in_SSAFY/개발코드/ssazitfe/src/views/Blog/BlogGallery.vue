<template>
  <div class="book-gallery" style="height:100%">
    <!-- 검색창 -->
    <!-- <v-row style="width:1050px;">
      <v-col cols="3"></v-col>
      <v-col cols="6" class="px-0">
        <v-text-field
          label="블로그 내 검색"
          clearable
          class="p-0 m-0"
        ></v-text-field>
      </v-col>
        <v-btn icon color="grey" class="mx-0 px-0 mt-2">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
    </v-row> -->
    
    <div class="row px-5 mr-0" align="center" style="justify-content:space-between; height:50px;">
      <v-row class="" style="justify-content:start;">        
        <h4 class="text-justify font-weight-bold">
          <div style="height:10px;"></div>
          내 작품
        </h4>
        <!-- 책만들기 -->
        <v-btn-toggle
          borderless
          v-if="userInfo && (userInfo.blogNo == searchInfo.blogNo)"
        >
          <v-btn value="책만들기" :to="'/bloghome/' + $store.getters.userInfo.userId + '/writebook'" color="white" class="text-decoration-none">
            <span class="hidden-sm-and-down mr-1">책만들기</span>
            <v-icon small>mdi-pencil</v-icon>
          </v-btn>
        </v-btn-toggle>
      </v-row>

      <!-- 정렬 기준 선택 -->
      <v-col class="mx-0 d-flex p-0" cols="2" style="height:50px;">
        <v-select
          :items="sortItems"
          label="정렬"
          outlined
          dense
        ></v-select>
      </v-col>
    </div>
    <!-- <hr /> -->
    <br>
    <br>
    <template v-if="currentBooks.length > 0">
      <v-row class="book-list" style="width:100%;">
        <!-- 한 줄에 6개 -->
        <!-- <v-col cols="6" sm="4" md="3" lg="2" align="left" class="book-contents p-2" v-for="books in currentBooks" :key="books.book.bookNo">
          <v-img :src="serverURL + books.book.coverFront" class="mb-3" width="159px" height="238.5px"></v-img>
          <router-link style="color:black; text-decoration:none;" :to="`/bloghome/${$route.params.userId}/bookdetail/${books.book.bookNo}`" class="font-weight-black mb-0">{{books.book.bookTitle}}</router-link>
          <p class="font-weight-light caption mb-0">
            댓글수 {{books.commentCount}}
            <v-icon small>mdi-comment-text-outline</v-icon>
          </p>
          <p class="font-weight-light caption mb-2">
            {{books.book.updateDate}}
          </p>
          <p class="font-weight-medium caption mb-0">
            <span v-for="tag in books.booktag" :key="tag.tagNo">
              #{{tag.tagName}}
            </span>
            <span v-for="tag in books.tmptag" :key="tag.tmptagNo">
              #{{tag.tmptagName}}
            </span>
          </p>
        </v-col> -->
        <v-col cols="6" sm="4" md="3" lg="3" align="center" class="book-contents p-2" style="height:300px;" v-for="(books,index) in currentBooks" :key="books.book.bookNo">
          <v-row id="bk-list" class="bk-list clearfix">
            <li class="col-lg-4 col-md-6 col-sm-12">
              <div class="bk-book book-1 bk-bookdefault">
                <div class="bk-front">
                  <div class="bk-cover">
                    <v-hover>
                      <v-card class="auto" color="grey lighten-4" width="200" height="250">
                        <v-img style="width:200px; height:250px" :src="serverURL + books.book.coverFront" @click="toBook(index)">
                          <v-expand-transition style="vertical-align:bottom; align-items:end">
                            <div>
                              <div class="font-weight-light-6 title mb-2" style="width:100%; position:fixed; bottom:0; background-color: rgba( 0, 0, 0, 0.6 );">
                                <p class="pt-3 mb-0" style="text-align:center; color:white; font-weight:bolder;">{{books.book.bookTitle}}</p>
                                <p class="mb-3" style="text-align:center; font-size:14px; color:white;">
                                  <span v-for="tag in books.booktag" :key="tag.tagNo">
                                    #{{tag.tagName}}
                                  </span>
                                  <span v-for="tag in books.tmptag" :key="tag.tmptagNo">
                                    #{{tag.tmptagName}}
                                  </span>
                                </p>
                              </div>
                            </div>
                          </v-expand-transition>
                        </v-img>
                      </v-card>
                    </v-hover>
                  </div>
                </div>
                <div class="bk-left" style="background-color:#EEEE;">
                  <v-img style="width:200px; height:250px;" :src="serverURL + books.book.coverMiddle">
                    <h2 style="text-shadow: -1px 0 #F2F1F6, 0 1px #F2F1F6, 1px 0 #F2F1F6, 0 -1px #F2F1F6;
 -moz-text-shadow: -1px 0 #F2F1F6, 0 1px #F2F1F6, 1px 0 #F2F1F6, 0 -1px #F2F1F6;
 -webkit-text-shadow: -1px 0 #F2F1F6, 0 1px #F2F1F6, 1px 0 #F2F1F6, 0 -1px #F2F1F6;">
                      <span>{{books.book.bookTitle}}</span>
                      <span>
                        댓글수 {{books.commentCount}}
                        <v-icon small>mdi-comment-text-outline</v-icon>
                      </span>
                    </h2>
                  </v-img>
                </div>
              </div>
            </li>
          </v-row>

          
        </v-col>
      </v-row>

      <v-col cols="12">
        <v-pagination
          v-model="currentPage"
          :length="bookPageLength"
        ></v-pagination>
      </v-col>
    </template>
    <template v-else>
      <h4 align="center">작성된 책이 없습니다.</h4>
      <div class="blog-article-scene">
        <div class="blog-article-layer blog-article-layer-1">
          <div class="blog-article-desk"></div>
          <div class="blog-article-plant">
            <div class="blog-article-plant-stalk"></div>
            <div class="blog-article-plant-leaf"></div>
            <div class="blog-article-plant-leaf"></div>
          </div>
          <div class="blog-article-lamp">
            <div class="blog-article-lamp-base"></div>
            <div class="blog-article-lamp-mid">
              <div class="blog-article-lamp-mid-knob"></div>
            </div>
            <div class="blog-article-lamp-top"></div>
          </div>
          <div class="blog-article-lamp-light"></div>
          <div class="blog-article-laptop"></div>
          <div class="blog-article-bookPic-0"></div>
          <div class="blog-article-bookPic-1"></div>
          <div class="blog-article-bookPic-2"></div>
          <div class="blog-article-coffee">
            <div class="blog-article-coffee-handle"></div>
            <div class="blog-article-coffee-brew"></div>
          </div>
          <div class="blog-article-glass"></div>
        </div>
        <div class="blog-article-layer blog-article-layer-2">
        </div>
        <div class="blog-article-layer blog-article-layer-3">
          <div class="blog-article-painting">
            <div class="blog-article-painting-inner"></div>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "BlogGallery",
  data() {
    return {
      selected: "none",
      sortItems: ['최신순', '인기순', '조회순'],
      currentPage: 1,
      searchInfo:{},
      bookInfo:[],
    };
  },
  mounted() {
    axios.get('/blog/searchbyuserid?userId=' + encodeURI(this.$route.params.userId))
    .then((Response)=>{
      // console.log(Response.data);
      // this.$store.dispatch('Re_blogInfo',Response.data)
      this.searchInfo = Response.data;
      this.$store.dispatch('Re_blogInfo',this.searchInfo)

      axios.get('/blog/myboards?blogNo='+ Response.data.blogNo)
      .then((Res)=>{
        // console.log(Res);
        this.BoardItems = Res.data;
        this.$store.dispatch('Re_boardInfo',Res.data)
      }),
      axios.get('/blog/gndist?blogNo='+ Response.data.blogNo)
      .then((Res)=>{
        this.boardsNo = Res.data;
      })
    });
    axios.post('/user/list',{
      searchKey : "userId",
      searchValue : this.$route.params.userId
    }).then((Response)=>{
      // console.log(Response);
      // console.log("Hi");
      this.blogInfo = Response.data[0];
    });

    this.getBookList();
  },
  methods : {
    go_detail() {
      this.$router.push(`/bloghome/${this.userInfo.userId}/bookdetail`);
    },
    getBookList(){
      let requestUrl;
      if(this.userInfo){
        requestUrl = `/book/books?writer=` + encodeURI(this.$store.getters.blogInfo.userNo) + `&loginUserNo=` + encodeURI(this.userInfo.userNo);
      }else{
        requestUrl = `/book/books?writer=` + encodeURI(this.$store.getters.blogInfo.userNo);
      }
      axios.get(requestUrl)
      .then((Response)=>{
        // console.log("here!");
        // console.log(Response);
        this.bookInfo = Response.data;
      })
    },
    toBook(index){
      this.$router.push('/bloghome/' + this.$route.params.userId + '/bookdetail/' + this.currentBooks[index].book.bookNo)
    },
  },
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    },
    blog_Info() {
      return this.$store.getters.blogInfo;
    },
    boardInfo() {
      return  this.$store.getters.boardInfo;
    },
    serverURL(){
      return this.$store.getters.serverURL;
    },

    // 페이지네이션
    startBookNo() {
      return ((this.currentPage-1) * 12);
    },
    endBookNo() {
      return (this.startBookNo + 12);
    },
    bookPageLength() {
      return Math.ceil(this.bookInfo.length / 12);
    },
    currentBooks() {
      return this.bookInfo.slice(this.startBookNo, this.endBookNo);
    },
  },

};
</script>

<style>
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
