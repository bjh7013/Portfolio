<template>
  <div class="mycomments container col order">
    <div class="row" v-if="userInfo!=null && userInfo.userNo>-1">
      <!-- data가 들어갈 부분 -->
      <div class="col" id="main">
        <v-card :elevation="0" class="col-11" id="mycomments_card">
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

          <!-- 게시판 -->
          <b-tabs content-class="mt-3">
            <b-tab title="게시판" active @click="goboard">
              <v-simple-table>
                <template v-slot:default>
                  <colgroup>
                    <col width="20%">
                    <col width="30%">
                    <col width="45%">
                    <col width="5%">
                  </colgroup>
                  <thead>
                    <th>블로그</th>
                    <th>게시글 제목</th>
                    <th>나의 댓글</th>
                    <th>채택</th>
                  </thead>
                  <tbody>
                    <template v-for="comment of commentList">
                      <tr :key="comment.commentNo" v-if="comment.articleNo>0" @click="toArticle(comment)">
                        <td class="text-left">{{comment.blogName}}</td>
                        <td class="text-left">{{comment.articleTitle}}</td>
                        <td class="text-left" v-html="comment.content"></td>
                        <td v-if="comment.isPick == 1"><v-icon color="green lighten-2">mdi-checkbox-marked-circle</v-icon></td>
                        <td v-else>-</td>
                      </tr>
                    </template>
                  </tbody>
                </template>
              </v-simple-table>
            </b-tab>
            <b-tab title="작품" @click="gobook">
              <v-simple-table>
                <template v-slot:default>
                  <colgroup>
                    <col width="20%">
                    <col width="30%">
                    <col width="45%">
                    <col width="5%">
                  </colgroup>
                  <thead>
                    <th>블로그</th>
                    <th>작품명</th>
                    <th>나의 댓글</th>
                    <th>채택</th>
                  </thead>
                  <tbody>
                      <template v-for="comment of bookcommentList">
                        <tr :key="comment.commentNo" v-if="comment.bookNo>0" @click="toBook(comment)">
                          <td class="text-left">{{comment.blogName}}</td>
                          <td class="text-left">{{comment.bookTitle}}</td>
                          <td class="text-left" v-html="comment.content"></td>
                          <td v-if="comment.isPick == 1"><v-icon color="green lighten-2">mdi-checkbox-marked-circle</v-icon></td>
                          <td v-else>-</td>
                        </tr>
                      </template>
                  </tbody>
                </template>
              </v-simple-table>
            </b-tab>
          </b-tabs>

        <!-- 페이지네이션 -->
          <v-col cols="12">
            <v-container id="page" class="max-width">
              <v-pagination
                v-model="page"
                :length="pagelength"
                :page="page"
                total-visible="10"
              ></v-pagination>
            </v-container>
          </v-col>
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
  name: "mycomments",
  data() {
      return {
        items1: ['전체선택', '알고리즘', '웹'],
        commentList:[],
        comment_list: [
          {
            title: [],
            date: [],
            pick: [],
            comments: [],
          },
        ],  
        check : false,
        selected : '1',
        // pagination 이동하게 만들어야함
        rows: 100,
        page: 1,
        bookcommentList : [],
        pagelength : 1
    }   
  },
  computed: {
    userInfo(){
        // console.dir(this.$store.getters.userInfo);
        return this.$store.getters.userInfo;
      }
  },
  methods:{
    removeIframe(){
        setTimeout(() => {
          if(document.getElementsByTagName('iframe')){
            let iframes = document.getElementsByTagName('iframe');
            let len = document.getElementsByTagName('iframe').length;
            for(let i = 0; i < len; i++){
              // console.log(i);
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
              // console.log(i);
              codes[0].parentElement.parentElement.removeChild(codes[0].parentElement);
            } 
          }else{
            alert('dd')
            setTimeout(this.removeCode, 100);
          }
        }, 100)
      },
      search_comment_board() {
      axios.get('/comment/mycomments?li')
      .then(({data}) => {
        this.commentList = data;
      })
      .catch(() => {
        alert('댓글 목록을 가져오던 중 문제가 발생했어용')
      })
    },
    toArticle(data){
      this.$router.push('/bloghome/' + data.userId + '/articledetail/' + data.articleNo)
    },
    toBook(data){
      this.$router.push('/bloghome/' + data.userId + '/bookdetail/' + data.bookNo)
    },
    getbook(){
      axios.get('/comment/mybookcomments?limitStart=' + (this.page-1)*10)
      .then((Response) => {
        this.bookcommentList = Response.data;
        axios.get('/comment/mybookcommentscount')
          .then((Res)=>{
          this.pagelength = parseInt(Math.ceil(Res.data/10))
        })
      })
      .catch(() => {
        alert('작품 댓글 목록을 가져오던 중 문제가 발생했어용')
      })
    },
    getboard() {
      axios.get('/comment/mycomments?limitStart=' + (this.page-1)*10)
    .then(({data}) => {
      this.commentList = data;
      axios.get('/comment/mycommentscount')
        .then((Res)=>{
          this.pagelength = parseInt(Math.ceil(Res.data/10))
      })
    })
    .catch(() => {
      alert('게시글 댓글 목록을 가져오던 중 문제가 발생했어용')
    })
    },
    gobook(){
      this.getbook()
    },
    goboard(){
      this.getboard()
    }
  },
  components: {
    
  },

  created(){
    this.getboard()
  },
  updated(){
    this.removeIframe();
    this.removeCode();
  }
};
</script>

<style scoped>
#mycomments_card {
  padding: 0 30px;
}
</style>
