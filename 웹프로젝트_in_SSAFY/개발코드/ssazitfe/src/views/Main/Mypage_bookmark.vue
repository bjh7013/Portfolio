<template>
  <div class="bookmark container col order">
    <div class="row" v-if="userInfo!=null && userInfo.userNo>-1">
      <!-- data가 들어갈 부분 -->
      <div class="col" id="main">
        <v-card :elevation="0" class="col-11" id="bookmark_card">
          <v-container fluid>
            <v-row align="center">
              <!-- <v-col class="d-flex" cols="2" sm="2">
                <v-select
                  :items="items1"
                  label="검색"
                ></v-select>
              </v-col> -->
              <v-col md="4" class="ml-auto">
                <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search"></v-text-field>
              </v-col>
              <v-btn icon ><v-icon>mdi-magnify</v-icon></v-btn>
            </v-row>
          </v-container>

          <!-- 탭 -->
          <b-tabs content-class="mt-3">
            <!-- 게시판 (복붙이라구 수정해야한다구!)--> 
            <b-tab title="게시판" active @click="articles">
              <v-simple-table>
                <template v-slot:default>
                  <colgroup>
                    <col width="3%">
                    <col width="52%">
                    <col width="30%">
                    <col width="15%">
                  </colgroup>
                  <thead>
                    <th></th>
                    <th>게시글 제목</th>
                    <th>블로그</th>
                    <th>닉네임</th>
                  </thead>
                  <tbody>
                     <tr v-for="(articleFav,index) of articleFavList" :key="articleFav.favoriteNo">
                      <td><v-checkbox v-model="checkboard[index]" class="mx-2"></v-checkbox></td>
                      <td class="text-left" @click="toArticle(index)">{{articleFav.articleTitle}}</td>
                      <td class="text-left" @click="toBlogArticle(index)">{{articleFav.blogName}}</td>
                      <td class="text-left">{{articleFav.nickName}}</td>
                    </tr>
                  </tbody>
                  <tfoot>
                    <tr>
                      <td><v-checkbox v-model="checkboardsall" class="mx-2" @change="checkarticleall"></v-checkbox></td>
                      <td class="text-left">전체선택</td>
                      <td></td>
                      <td></td>
                      <td><v-btn outlined @click="deleteboardFav">삭제</v-btn></td>
                    </tr>
                  </tfoot>
                </template>
              </v-simple-table>
              <!-- 페이지네이션 -->
              <v-col cols="12">
                <v-container id="page" class="max-width" @click="clickpageboard">
                  <v-pagination
                    v-model="pageboard"
                    :length="lengthboard"
                    :page="pageboard"
                    :total-visible="totalVisible"
                  ></v-pagination>
                </v-container>
              </v-col>
            </b-tab>
            

            <!-- 작품 -->
            <b-tab title="작품" @click="books">
              <!-- <v-simple-table>
                <template v-slot:default>
                  <colgroup>
                    <col width="3%">
                    <col width="52%">
                    <col width="30%">
                    <col width="15%">
                  </colgroup>
                  <thead>
                    <th></th>
                    <th>게시글 제목</th>
                    <th>블로그</th>
                    <th>닉네임</th>
                  </thead>
                  <tbody>
                    <tr v-for="(bookFav,index) of bookFavList" :key="bookFav.favoriteNo">
                      <td><v-checkbox v-model="checkbook[index]" class="mx-2"></v-checkbox></td>
                      <td class="text-left" @click="toBook(index)">{{bookFav.bookTitle}}</td>
                      <td class="text-left" @click="toBlogBook(index)">{{bookFavUser[index].blogName}}</td>
                      <td class="text-left">{{bookFavUser[index].nickName}}</td>
                    </tr>
                  </tbody>
                  <tfoot>
                    <tr>
                      <td><v-checkbox v-model="checkbooksall" id="check_all_book" class="mx-2" @change="checkbookall"></v-checkbox></td>
                      <td class="text-left">전체선택</td>
                      <td></td>
                      <td></td>
                      <td><v-btn outlined @click="deletebookFav">삭제</v-btn></td>
                    </tr>
                  </tfoot>
                </template>
              </v-simple-table> -->
              <!-- style="border-bottom:10px solid BurlyWood; box-shadow:0px 5px 10px grey;" -->

              <div>
                <div class="container2">
                  <v-row>
                    <v-col cols="12" sm="6" md="6" lg="4" align="center" class="book-contents p-2" style="height:300px;" v-for="(books, index) in bookFavList" :key="books.bookNo">
                      <v-row id="bk-list" class="bk-list clearfix">
                        <li class="col-lg-4 col-md-6 col-sm-12">
                          <div class="bk-book book-1 bk-bookdefault">
                            <div class="bk-front">
                              <div class="bk-cover">
                                <v-hover>
                                  <v-card class="auto" color="grey lighten-4" width="200" height="250">
                                    <v-img style="width:200px; height:250px" :src="serverURL + books.coverFront" @click="toBook(index)">
                                      <v-expand-transition style="vertical-align:bottom; align-items:end">
                                        <div>
                                          <div class="font-weight-light-6 title mb-2" style="width:100%; position:fixed; bottom:0; background-color: rgba( 0, 0, 0, 0.6 );">
                                            <p class="pt-3 mb-0" style="text-align:center; color:white; font-weight:bolder;">{{books.bookTitle}}</p>
                                            <p class="mb-3" style="text-align:center; font-size:14px; color:white;">{{bookFavUser[index].nickName}}</p>
                                          </div>
                                        </div>
                                      </v-expand-transition>
                                    </v-img>
                                  </v-card>
                                </v-hover>
                              </div>
                            </div>
                            <div class="bk-left" style="background-color:#EEEE;">
                              <v-img style="width:200px; height:250px;" :src="serverURL + books.coverMiddle">
                                <h2>
                                  <span>{{books.bookTitle}}</span>
                                  <span>{{bookFavUser[index].nickName}}/{{bookFavUser[index].blogName}}</span>
                                </h2>
                              </v-img>
                            </div>
                          </div>
                        </li>
                      </v-row>
<!-- 
                      <v-row v-if="index / 3 < 2 && index / 3 >= 1" id="bk-list" class="bk-list clearfix row">
                        <li class="col-lg-4 col-md-6 col-sm-12">
                          <div class="bk-book book-1 bk-bookdefault">
                            <div class="bk-front">
                              <div class="bk-cover">
                                <v-hover>
                                  <v-card class="auto" color="grey lighten-4" width="200" height="250">
                                    <v-img style="width:200px; height:250px" :src="serverURL + books.coverFront" @click="toBook(index)">
                                      <v-expand-transition style="vertical-align:bottom; align-items:end">
                                        <div>
                                          <div class="font-weight-light-6 title mb-2" style="width:100%; position:fixed; bottom:0; background-color: rgba( 0, 0, 0, 0.6 );">
                                            <p class="pt-3 mb-0" style="text-align:center; color:white; font-weight:bolder;">{{books.bookTitle}}</p>
                                            <p class="mb-3" style="text-align:center; font-size:14px; color:white;">{{bookFavUser[index].nickName}}</p>
                                          </div>
                                        </div>
                                      </v-expand-transition>
                                    </v-img>
                                  </v-card>
                                </v-hover>
                              </div>
                            </div>
                            <div class="bk-left" style="background-color:#EEEE;">
                              <v-img style="width:200px; height:250px" :src="serverURL + books.coverMiddle">
                                <h2>
                                  <span>{{books.bookTitle}}</span>
                                  <span>{{bookFavUser[index].nickName}}/{{bookFavUser[index].blogName}}</span>
                                </h2>
                              </v-img>
                            </div>
                          </div>
                        </li>
                      </v-row> -->

                    </v-col>
                  </v-row>

                </div>
              </div>
              

              <!-- 페이지네이션 -->
              <v-row>
                <v-col cols="12">
                  <v-container id="page" class="max-width" @click="clickpagebook">
                    <v-pagination
                      v-model="pagebook"
                      :length="lengthbook"
                      :page="pagebook"
                    ></v-pagination>
                  </v-container>
                </v-col>
              </v-row>
            </b-tab>
          </b-tabs>

        </v-card>
      </div>
    </div>
    <div class='pageBlogNotFound' v-else style="position:relative; left:-200px">
      <div>
        <h2>잘못된 접근입니다.</h2>
      </div>
    <router-link to="/menu">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>회원가입</h2>
          <h3>SSAZIT와 함께 하세요.</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circlBlogNotFounde_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc1.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc3.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc2.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
    <router-link to="/main">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>홈</h2>
          <h3>SSAZIT홈으로 돌아갑니다</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc4.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc5.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc6.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
    <router-link to="/login">
    <div class='contentBlogNotFound'>
      <div class='circleBlogNotFound'>
        <div class='circleBlogNotFound_title'>
          <h2>로그인</h2>
          <h3>로그인 시 이용할 수 있습니다.</h3>
        </div>
        <div class='circleBlogNotFound_inner'>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc7.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc8.png'>
          </div>
          <div class='circleBlogNotFound_inner__layer'>
            <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/pc9.png'>
          </div>
        </div>
        <div class='contentBlogNotFound_shadow'></div>
      </div>
    </div>
    </router-link>
  </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "bookmark",
  components: {},
  data() {
    return {
      items1: ['전체선택', '알고리즘', '웹'],
      articleFavList:[],
      selected : '1',
      page: 1,
      // pagination 이동하게 만들어야함
      rows: 100,
      bookFavList: [],
      bookFavUser: [],
      bookFavList2: [],
      bookFavList3: [],
      bookFavList4: [],
      articleFavUser: [],
      totalVisible: 10,
      lengthbook: 1,
      pagebook: 1,
      lengthboard: 1,
      pageboard: 1,
      checkboard : [],
      checkbook : [],
      checkbooksall : false,
      checkboarsdall : false
    };
  },
  mounted() {
    this.getarticles();
    this.getbooks();
  },
  computed : {
    userInfo() {
      return this.$store.getters.userInfo;
    },
    serverURL(){
      return this.$store.getters.serverURL;
    },
  },
  methods : {
    toArticle(index){
      // console.log(index)
      this.$router.push('/bloghome/' + this.articleFavUser[index].userId + '/articledetail/' + this.articleFavList[index].articleNo)
    },
    toBook(index){
      this.$router.push('/bloghome/' + this.bookFavUser[index].userId + '/bookdetail/' + this.bookFavList[index].bookNo)
    },
    toBlogArticle(index){
      this.$router.push('/bloghome/' + this.articleFavUser[index].userId)
    },
    toBlogBook(index){
      this.$router.push('/bloghome/' + this.bookFavUser[index].userId)
    },
    getarticles(){
      axios.get(`/article/articlefavorites?userNo=` + this.userInfo.userNo + '&limitStart=' + (this.pageboard-1)*10 + '&limitEnd=10')
      .then(({data}) => {
        this.articleFavList = [];
        // console.log('============================================================')
        // console.dir(data)
        for(let i=0;i<data.length;i++){
          this.articleFavList.splice(i,0,data[i]);

          axios.get(`/user/listone/${data[i].userNo}`)
          .then((Restwo)=>{
            this.articleFavUser.splice(i,0,Restwo.data)
          })
        }
      })
      .catch(() => {
        alert("즐겨찾기 목록을 조회하던 중 오류가 발생했습니다")
      })

      axios.get('/article/articlefavoritescount?userNo=' + this.userInfo.userNo)
      .then((Res)=>{
        this.lengthboard = parseInt(Math.ceil(Res.data/10))
      })
    },
    getbooks(){
      axios.get(`/book/bookfavorites?userNo=` + this.userInfo.userNo +'&limitStart=' + (this.pagebook-1)*6 + '&limitEnd=6')
      .then(({data}) => {
        this.bookFavList = []
        this.bookFavUser = []
        for(let i=0;i<data.length;i++){
          axios.get(`/book/book/${data[i].bookNo}`)
          .then((Res)=>{
            this.bookFavList.splice(i,0,Res.data.book);
            axios.get(`/user/listone/${Res.data.book.writer}`)
            .then((Restwo)=>{
              this.bookFavUser.splice(i,0,Restwo.data)
            })
          })
        }
      })
      .catch(() => {
        alert("즐겨찾기 목록을 조회하던 중 오류가 발생했습니다")
      })

      axios.get(`/book/bookfavoritescount?userNo=` + this.userInfo.userNo)
      .then((Res)=>{
        this.lengthbook = parseInt(Math.ceil(Res.data/6))
      })
    },

    // getbooks2(){
    //   this.getFavbooks().then((Response) => {
    //     for(var fav of Response.data){
    //       console.log("##########");
    //       console.log(fav.bookNo);
    //       axios.get(`/book/book/${fav.bookNo}`)
    //       .then((Response2)=>{
    //         console.log(Response2.data);
    //         for(var i=0; i<Response2.data.length; i++){
    //           console.log(Response2[i].data);
    //           axios.get(`/user/listone/${Response2[i].data.book.writer}`)
    //           .then((Response3)=>{
    //             console.log(Response3.data);
    //             this.bookFavList2.push({favInfo : fav, bookInfo : Response2[i].data, userInfo : Response3.data});
    //           })
    //           .catch(()=>{
    //             console.log("user 정보 조회 실패");
    //           })
    //         }
    //       })
    //       .catch(()=>{
    //         console.log("book 정보 조회 실패");
    //       })
    //     }
    //     return "done";
    //   })
    //   .catch(()=>{
    //     console.log("fav 정보 조회 실패");
    //   })

    // },
    // getFavbooks(){
    //   return new Promise((resolve, reject) => {
    //     axios.get(`/book/bookfavorites?userNo=` + this.userInfo.userNo +'&limitStart=' + (this.pagebook-1)*10 + '&limitEnd=10').then((Response) => {
    //       console.log('즐겨찾기 책 가져오기**********');
    //       console.log(Response.data);
    //       resolve(Response);
    //     });
    //   });
    // },
    
    // promistest(){
    //   var start_str = 'main에서 왔습니다.';

    //   console.log('main routine**********');
    //   this.func1(start_str).then((parent_func) => {
    //       console.log('function1 callback**********');
    //       console.log(parent_func);
    //       return parent_func
    //   }).then(this.func2)
    //       .then(this.func3);

    //   console.log("main 종료");
    // },
    // func1(parent_func) {
    //   return new Promise((resolve, reject) => {
    //     axios.get(`/book/bookfavorites?userNo=` + this.userInfo.userNo +'&limitStart=' + (this.pagebook-1)*10 + '&limitEnd=10').then((result) => {
    //       console.log('function1 routine**********');
    //       console.log(parent_func);
    //       resolve('function1에서 왔습니다.');
    //     });
    //   });
    // },
    // func2(parent_func) {
    //   return new Promise((resolve, reject) => {
    //     axios.get(`/book/bookfavorites?userNo=` + this.userInfo.userNo +'&limitStart=' + (this.pagebook-1)*10 + '&limitEnd=10').then((result) => {
    //       console.log('function2 routine**********');
    //       console.log(parent_func);
    //       resolve('function2에서 왔습니다.');
    //     });
    //   });
    // },
    // func3(parent_func) {
    //   return new Promise((resolve, reject) => {
    //     axios.get(`/book/bookfavorites?userNo=` + this.userInfo.userNo +'&limitStart=' + (this.pagebook-1)*10 + '&limitEnd=10').then((result) => {
    //       console.log('function3 routine**********');
    //       console.log(parent_func);
    //       resolve('function3에서 왔습니다.');
    //     });
    //   });
    // },


    articles(){
      this.checkboard = [];
      this.checkboardsall = false;
      this.pageboard=1
      this.getarticles();
    },
    books(){
      this.checkbook = [];
      this.checkbooksall = false;
      this.pagebook=1;
      this.getbooks();
    },
    clickpagebook() {
      this.checkbook = [];
      this.getbooks();
    },
    clickpageboard() {
      this.getarticles();
    },
    deleteboardFav() {
      if(this.userInfo == null){
        alert('로그인 해주세요!')
        return;
      }
      for(let i=0;i<this.checkboard.length;i++){
        if(this.checkboard[i]!=true){
          continue;
        }
        axios.post('/article/articlefavorite/CUD',{
          articleNo : this.articleFavList[i].articleNo
        })
        .then(() => {
          this.checkboard = [];
          this.checkboardsall = false;
          this.getarticles()
        })
        .catch(() => {
          alert('관심글 등록/해제 도중 오류가 발생했습니다.')
        })
      }
    },
    deletebookFav() {
      if(this.userInfo == null){
        alert('로그인 해주세요!')
        return;
      }
      for(let i=0;i<this.checkbook.length;i++){
        if(this.checkbook[i]!=true){
          continue;
        }
        axios.post(`/book/bookfavorite/CUD`,{
          bookNo : this.bookFavList[i].bookNo
        })
        .then(()=>{
          this.checkbook = [];
          this.checkbooksall = false;
          this.getbooks()
        })
        .catch(()=>{
          alert("즐겨찾기 등록/해제 도중 오류가 발생했습니다.");
        })
      }
    },
    checkbookall(){
      if(this.checkbooksall){
        this.checkbook = [];
        for(let i=0;i<this.bookFavList.length;i++){
           this.checkbook.splice(i,0,true);
        }
      }else{
        // this.checkbooksall = true;
        while(this.checkbook.length>0){
          this.checkbook.pop();
        }
      }
    },
    checkarticleall() {
      if(this.checkboardsall){
        this.checkboard = [];
        for(let i=0;i<this.articleFavList.length;i++){
          // this.checkboard.push(true);
          this.checkboard.splice(i,0,true);
        }
      }else{
        // this.checkbooksall = true;
        while(this.checkboard.length>0){
          this.checkboard.pop();
        }
      }
    }
  }
};
</script>

<style scoped>
#bookmark_card {
  padding: 0 30px;
}
#card_detail {
  padding: 0px;
}

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
	overflow: scroll;
	/* overflow-x: hidden; */
}

a {
	color: #555;
	text-decoration: none;
}

.container2 {
  height: 100%;
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
