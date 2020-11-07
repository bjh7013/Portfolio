<template>
  <div id="article-detail" style="height:100%;">
    <v-row class="article-title px-3 mt-0"> 
      <div align="left" class="title-content">
        <!-- 뒤로가기 버튼 -->
        <v-btn text dense @click="backList" color="black" class="ml-0 pl-0 mb-6">
          <v-icon>mdi-arrow-left</v-icon>목록
        </v-btn>
      </div>
      <v-row style="justify-content:space-between; width:100%;">        
        <v-col class="pl-5">
          <!-- 카테고리 -->
          <h6 v-if="articleTagCat.article.categoryNo != 0" @click="clickArtCategory">|{{articleTagCat.article.categoryName}}</h6>
          <h6 v-if="articleTagCat.article.acategoryNo != 0" @click="clickAcategory"><a href="#">|<b>{{articleTagCat.article.acategorySource}}</b> {{articleTagCat.article.acategoryNumber}} - {{articleTagCat.article.acategoryTitle}}</a></h6>
          <!-- 글 제목 -->
          <h3 class="mb-3" v-html="articleTagCat.article.title"></h3>
        </v-col>
        <!-- 글삭제 버튼[수정시에만] -->
        <div class="my-2 mx-0" v-if="$store.getters.userInfo && $store.getters.userInfo.userNo == articleTagCat.article.userNo">
          <!-- 누르면 경고창!! -->
          <v-dialog v-model="artDelete" persistent max-width="290">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                color="error"
                text
                v-bind="attrs"
                v-on="on"
              >
                글 삭제
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline">해당 게시글을 삭제하시겠습니까?</v-card-title>
              <v-card-text>삭제된 게시글은 복구할 수 없습니다.</v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="black darken-1" text @click="artDelete = false">취소</v-btn>
                <v-btn color="green darken-1" text @click="deleteArticle">동의</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-row>
      
    </v-row>
    <v-row class="mx-1" style="justify-content:space-between;">
      <v-row class="px-6">
        <!-- 레벨에 따라 프사 테두리색 다르게(미구현) -->
        <v-avatar color="white" class="mr-2">
          <img
            :src="serverURL + articleTagCat.article.profileImg"
            alt="John"
          >
        </v-avatar>
        <div class="title-user">
          <!-- 닉네임 클릭시 해당 유저의 블로그로 이동 -->
          <p class="m-0 pt-1 caption" align="left">
            <router-link :to="`/bloghome/${articleTagCat.article.userId}`" class="text-decoration-none text-dark">
                  {{articleTagCat.article.nickName}}
            </router-link>
            <!-- 팔로우 버튼 [글작성자가 아닌 경우에만] -->
            <v-btn v-if="$store.getters.userInfo && (articleTagCat.article.userNo != $store.getters.userInfo.userNo) && !this.check" class="ml-1" x-small tile dense outlined color="indigo" style="border-radius: 5px;" @click="follow">
              <v-icon x-small>mdi-account-plus</v-icon> Follow
            </v-btn>
            <!-- 팔로우삭제 버튼 [글작성자가 아닌 경우에만] -->
            <v-btn v-else-if="$store.getters.userInfo && (articleTagCat.article.userNo != $store.getters.userInfo.userNo) && this.check" class="ml-1" x-small tile dense outlined color="red" style="border-radius: 5px;" @click="unfollow">
              <v-icon x-small>mdi-account-plus</v-icon> UnFollow 
            </v-btn>
          </p>
          <p align="left" class="m-0 caption">{{articleTagCat.article.writeDate}}</p>
        </div>
      </v-row>
      <div>
        <div style="height:12px;"></div>
        <v-row>
          <v-btn icon :class="'mx-0 px-0 ' + (articleTagCat.article.favoriteDate? 'yellow--text' : '')" @click="clickFav">
            <v-icon>mdi-star</v-icon>
          </v-btn>
          <!-- 게스글 공유? 스크랩? -->
          <v-btn icon color="grey" class="mx-0 px-0" @click="articleShare">
            <v-icon>mdi-bookmark-outline</v-icon>
          </v-btn>
          <!-- 글수정버튼 [글작성자인 경우에만] -->
          <!-- 글삭제는 수정 페이지 맨 아래에 추가 -->
          <v-btn icon color="grey" class="mx-0 px-0" v-if="$store.getters.userInfo && $store.getters.userInfo.userNo == articleTagCat.article.userNo">
            <v-icon @click="modifyArticle">mdi-pencil</v-icon>
          </v-btn>
        </v-row>
      </div>
    </v-row>
    <hr>

    <!--        글 내용 부분          -->
    
    <div class="pl-3 article-content" 
      align="center" 
      v-html="articleTagCat.article.content"
      style="width:100%; 
        line-height:20px;
        overflow:hidden; 
        white-space:normal;
        text-overflow:ellipsis;
        word-break:break-all;
        table-layout:fixed;
        text-align:left;"
    >
    </div>

    <div align="left">
      <!-- 태그입니돠 -->
      <div class="mb-3 ml-2">
        <v-chip
          v-for="tag in articleTagCat.arttag"
          :key="tag.tagNo"
          
          class="mr-2"
          label
          color="#EEEEEE"
          @click="clickTag(tag)"
        >
          #{{tag.tagName}}
        </v-chip>
        <v-chip
          v-for="tag in articleTagCat.tmpTag"
          :key="tag.tmptagNo"
          
          class="mr-2"
          label
          color="#EEEEEE"
          @click="clickTag(tag)"
        >
          #{{tag.tmptagName}}
        </v-chip>
      </div>

      <v-row class="pl-3">
        <!-- 글 좋아요 버튼 (누르면 빨간하트로 변신!!!!!!!) -->
        <v-btn icon :class="'mx-0 ' + (articleTagCat.article.likeDate? 'red--text' : '')"
                    @click="hitArticleLike">
          <v-icon>mdi-heart</v-icon>
        </v-btn>
        <div class="mr-3">
          <div style="height:5px;"></div>
          <span style="color:grey;">{{articleTagCat.article.likeCount}}명이 좋아합니다</span>
        </div>

        <!-- 댓글 버튼 (누르면 댓글창 펼쳐지기) -->
        <v-btn icon color="grey" class="mx-0" @click="commentToggle = !commentToggle">
          <v-icon>mdi-comment-text-outline</v-icon>
        </v-btn>
        <div>
          <div style="height:5px;"></div>
          <span style="color:grey;">{{commentCount}}개 댓글이 있습니다</span>
        </div>
        <v-spacer></v-spacer>
        <div v-if="reference">
          <div style="height:5px;"></div>
          <a :href='`/bloghome/${reference.userId}/articledetail/${reference.articleNo}`'>이 글을 참조했어요!</a>
        </div>
      </v-row>
      
      
    </div>
    <hr>
    <v-alert
      :value="commentToggle"
      transition="scale-transition"
    >
      <form>
        <v-row>
          <!-- <span class="px-3 ml-3 font-weight-black">
            <div style="height:6px;"></div>
            댓글
          </span> -->
          
          <v-btn
              class="mx-4 font-weight-black"
              dark
              v-if="userInfo"
              @click="editorData = '';dialog = true;mode='create'"
          >
            댓글작성
          </v-btn>
          <v-dialog v-model="dialog" persistent max-width="1000">    
            <v-card>
              <v-card-title class="headline">댓글</v-card-title>
              <v-card-text>
                <ckeditor v-model="editorData" :config="editorConfig"></ckeditor>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="writeComment">확인</v-btn>
                <v-btn color="green darken-1" text @click="dialog = false">취소</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </form>
    
      <br>
      <!--
        v-for="comment in commentList" :key="comment.commentNo"
        [채택된] 댓글 -->
      <div class="pt-5 px-3" v-for="comment in commentList" :key="comment.commentNo">
        <div class="py-5 px-5 comment" :style="comment.isPick==1?'background-color:#EEEE':''">
        <v-row class="pb-5" style='justify-content:space-between;'>
          <v-row class="comment-profile">
            <div style="width:30px;"></div>
            <v-avatar>
              <v-img :src="serverURL + comment.profileImg"></v-img>
            </v-avatar>
            <div class="px-2">
              <div style="height:10px;"></div>
              <span class="font-weight-bold">{{comment.nickName}}</span>
            </div>
            <div v-if="comment.isPick == 1">
              <div style="height:10px;"></div>
              <span class="pr-2">·</span>
              <span class="pr-2 font-weight-bold">채택된 답변</span>
              <v-icon color="green lighten-2">mdi-checkbox-marked-circle</v-icon>
            </div>
          </v-row>
          <v-row v-if="userInfo && userInfo.userNo == comment.userNo" class="mr-6" style="justify-content:flex-end;">
            <!-- 댓글수정 버튼[댓글 작성자만] -->
            <div align="right" class="my-1 mx-0" @click="editorData = comment.content;dialog = true;mode='modify';commentNo = comment.commentNo">
              <v-btn text dense>수정</v-btn>
            </div>
            <!-- 댓글삭제 버튼[댓글 작성자만] -->
            <div align="right" class="my-1 mx-0" @click="deleteComment(comment.commentNo)">
              <v-btn text dense color="error">삭제</v-btn>
            </div>
          </v-row>
        </v-row>
        <!-- 댓글 내용 -->
        <div align="left">
          <p v-html="comment.content"></p>
        </div>
        <v-row class="px-3 mt-5 mb-3">
          <v-col cols="9" class="p-0">
            <div style="height:10px;"></div>
            <p class="font-weight-light" style="text-align:left;">{{comment.writeDate}}</p>
          </v-col>
          <v-spacer></v-spacer>
          <div>
            <v-row>
              <!-- 채택 버튼 [글작성자가 댓글을 채택하지 않았을 때만] -->
              <div class="my-2" v-if="userInfo && (articleTagCat.article.userNo == userInfo.userNo)" @click="pickComment(comment)">
                <v-btn text outlined small>채택</v-btn>
              </div>
              <!-- 댓글 좋아요 버튼 (누르면 빨간하트로 변신!!!!!!!) -->
              <div class="mx-6">
                <div style="height:3px;"></div>
                <v-row>
                  <v-btn icon :class="'mx-0 ' + (comment.likeDate? 'red--text' : '')"
                      @click="hitCommentLike(comment)">
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                  <div class="mr-3">
                    <div style="height:5px;"></div>
                    <span style="color:grey;">{{comment.likeCount}}</span>
                  </div>
                </v-row>
              </div>
            </v-row>
          </div>
        </v-row>
        </div>
      </div>
    </v-alert>
    <!-- 이전글/다음글 슬라이드 -->
    <v-slide-group
      class="pa-4"
      center-active
      show-arrows
    >
      <v-slide-item
        v-for="article in blogarticles"
        :key="article.articleNo"
        v-slot:default="{active}"
      > 
        <!-- <v-row v-if="article.articleNo == this.$route.params.articleNo">
        </v-row> -->
        <v-card
          :color="active ? 'grey' : 'grey lighten-1'"
          class="mx-4 mt-0 mb-5"
          height="100"
          width="150"
          @click="go_article(article)"
          :img="serverURL + article.thumbNail"
          style="text-align:center; color:grey; font-weight:bold; font-size:12px;"
        >
          <v-row
            class="fill-height"
            align="center"
            justify="center"
          >
          </v-row>
          <!-- <p>{{article.title}}</p> -->
          <p v-if="article.articleNo!=$route.params.articleNo">{{article.title}}</p>
          <p v-if="article.articleNo==$route.params.articleNo" style="background-color:#EEEE; color:black;">{{article.title}}</p>
        </v-card>
      </v-slide-item>
    </v-slide-group>
    <div style="height:200px;"></div>
  </div>
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import axios from 'axios'

export default {
  name: "ArticleDetail",
  validations: {
    name: { required },
  },
  data(){
    return {
      articleTagCat:{
        article: {},
      },
      commentToggle: false,
      reference: '',
      following:{},
      check:false,
      dialog: false,
      artDelete: false,
      editorData: '',
      editorConfig: {
          extraPlugins: 'prism, youtube, image2',
          codeSnippet_theme: 'monokai_sublime',
          filebrowserImageUploadUrl: 'http://192.168.1.7:9999/file/articleimg',
          imageUploadUrl: "http://192.168.1.7:9999/file/articleimg"
      },
      commentList: [],
      commentCount: 0,
      mode:'create',
      commentNo: 0,
      limitStart : 0,
      limitEnd : 5,
      eventStop : false,
      blogarticles : []
    }
  },
  computed : {
    serverURL(){
      return this.$store.getters.serverURL;
    },
    userInfo(){
      return this.$store.getters.userInfo;
    }
  },
  created() {
    axios.get('/user/following')
    .then((Response)=>{
      // console.log(Response);
      this.following = Response.data;
      for(let i=0;i<Response.data.length;i++){
        if(this.$route.params.userId == Response.data[i].userId){
          this.check = true;
          break;
        }
      }
    })
    this.defaultarticle()
  },
  methods:{
    loginpage(){
      this.$router.push(`/login`);
    },
    deleteArticle(){
      axios.delete(`/article/CUD?articleNo=${this.articleTagCat.article.articleNo}`)
      .then(() => {
        alert('삭제 완료');
        this.$router.go(-1);
      })
      .catch(() =>{
        alert('삭제 실패')
      })
    },
    articleShare(){
      if(this.userInfo == null){
        alert('로그인 해주세요!');
        this.loginpage();
        return;
      }
      this.$router.push({name : 'BlogEditor', params:{userId: this.userInfo.userId, editorData: this.articleTagCat.article.content, reference: this.articleTagCat.article.articleNo}})
    },
    clickFav(){
      if(this.userInfo == null){
        alert('로그인 해주세요!');
        this.loginpage();
        return;
      }
      axios.post('/article/articlefavorite/CUD',{
        articleNo : this.articleTagCat.article.articleNo
      })
      .then(({data}) => {
        if(data == 'insert'){
          alert('관심글 등록')
          this.articleTagCat.article.favoriteDate = true;
        }else if(data == 'delete'){
          alert('관심글 해제')
          this.articleTagCat.article.favoriteDate = false;
        }
      })
      .catch(() => {
        alert('관심글 등록/해제 도중 오류가 발생했습니다.')
      })
    },
    pickComment(comment){
      this.resetComment(comment).then((comment)=>{
        this.clickPickComment(comment);
      });
    },
    clickPickComment(comment){
      axios.put('/comment/pick',{
        commentNo : comment.commentNo
      })
      .then(()=>{
        this.limitStart = 0;
        this.getCommentList();
      })
      .catch(() => {
        alert('댓글 채택 도중 오류가 발생했습니다')
      })
    },
    backList(){
      this.$router.go(-1);
    },
    clickTag(tag){
      //클릭된 태그로 검색하는 기능(정식태그 or 임시태그)
      let word;
      
      // console.dir(tag);

      word = tag.tagName || tag.tmptagName;

      this.$router.push(`/bloghome/${this.$route.params.userId}?q=${word}`);
    },
    clickArtCategory(){
    },
    clickAcategory(){
      //클릭된 알고리즘 카테고리로 검색하는 기능
      window.open(this.articleTagCat.article.link)
    },
    hitArticleLike(){
      if(this.userInfo == null){
        alert('로그인 해주세요!');
        this.loginpage();
        return;
      }
      // console.dir()
      axios.post(`/article/articlelike/CUD`,{
        articleNo : this.articleTagCat.article.articleNo
      })
      .then(()=>{
        //빨간색 변신
        if (this.articleTagCat.article.likeDate){
          this.articleTagCat.article.likeDate = false;
          this.articleTagCat.article.likeCount--;
        }else{
          this.articleTagCat.article.likeDate = true;
          this.articleTagCat.article.likeCount++;
        }
      })
      .catch(() =>{
        alert('좋아요 등록 중 오류 발생')
      })
    },
    hitCommentLike(comment){
      if(this.userInfo == null){
        alert('로그인 해주세요!');
        this.loginpage();
        return;
      }

      axios.post(`/comment/commentlike/CUD`,{
        commentNo : comment.commentNo
      })
      .then(()=>{
        //빨간색 변신

        if (comment.likeDate){
          comment.likeDate = false;
          comment.likeCount--;
        }else{
          comment.likeDate = true;
          comment.likeCount++;
        }
      })
      .catch(() =>{
        alert('좋아요 등록 중 오류 발생')
      })
    },
    deleteComment(commentNo){
      this.resetComment(commentNo).then((comment)=>{
        this.clickDeleteComment(commentNo);
      });
    },
    resetComment(data){
      return new Promise((resolve, reject) => {
        this.commentList = [];
        resolve(data);
      })
    },
    clickDeleteComment(commentNo){
      axios.delete(`/comment/CUD?commentNo=${commentNo}`)
      .then( () => {
        this.limitStart = 0;
        this.getCommentList();
        alert('삭제 완료');
      })
      .catch( () => {
        alert('댓글을 삭제하던 중 오류가 발생했습니다')
      })
    },
    writeComment(){
      this.resetComment("").then(()=>{
        this.clickWriteComment();
      });
    },
    clickWriteComment(){
      if(this.editorData.trim() == ''){
        alert('내용을 입력해주세요')
        return;
      }

      if(this.mode == 'create'){   
        axios.post('/comment/CUD',{
          articleNo : this.articleTagCat.article.articleNo,
          content : this.editorData
        })
        .then( () => {
          this.limitStart = 0;
          this.getCommentList();
          alert('등록완료');
          this.dialog = false;
        })
        .catch( () => {
          alert('댓글 등록에 실패했습니다!')
        })
      }else if(this.mode == 'modify'){
        axios.put('/comment/CUD',{
          commentNo : this.commentNo,
          content : this.editorData
        })
        .then( () => {
          this.limitStart = 0;
          this.getCommentList();
          alert('수정완료');
          this.dialog = false;
        })
        .catch( () => {
          alert('댓글 수정에 실패했습니다!')
        })
      }else{
        alert('유효하지 않은 요청입니다')
      }

    },
    modifyArticle(){
      this.$router.push(`../modifyarticle/${this.$route.params.articleNo}`)
    },
    follow() {
      axios.post(`/user/follow/`+ this.articleTagCat.article.userNo).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 되었습니다.");
        this.$router.go();
      }).catch(()=>{
        alert("에러");
      })
    },
    unfollow(){
      axios.delete(`/user/follow/`+ this.articleTagCat.article.userNo).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 삭제되었습니다.");
        this.$router.go();
      })
    },
    getCommentList(){
      let requestUrl = `/comment/comments?articleNo=${this.$route.params.articleNo}&limitStart=${this.limitStart}&limitEnd=${this.limitEnd}`;
      if(this.userInfo){
        requestUrl += `&loginUserNo=${this.userInfo.userNo}`;
      }
      axios.get(requestUrl)
      .then( ({data}) => {
        if(data.length){
          for(let comment of data){
            this.commentList.push(comment);
          }
          this.eventStop = false;
        }else{
          this.eventStop = true;
        }
        this.getCommentCount();
        // this.commentList = data;
        // console.log('-------------------------------------------------------------------------------------')
        // console.dir(this.commentList);
        this.limitStart += this.limitEnd;
      })
      .catch(() => {
        // alert('댓글 정보를 불러오던 중 오류가 발생했습니다')
      })
    },
    getCommentCount(){
      axios.get(`/comment/commentscount?articleNo=${this.$route.params.articleNo}`)
      .then((Response)=>{
        this.commentCount = Response.data;
      })
    },
    defaultarticle(){
      axios.get(`/article/articles?userId=${this.$route.params.userId}`)
      .then(({data}) => {
          for(let i=0;i<data.length;i++){
            this.blogarticles.splice(i,1,data[i].article)
          }
      })
      .catch(() => {
          alert("게시판 글 목록을 가져오는 데 실패했습니다.");
      }); 
      },
      go_article(data){
        this.$router.push('/bloghome/' + this.$route.params.userId + '/articledetail/' + data.articleNo)
      }
  },
  mounted(){
    let requestUrl;
    if(this.userInfo){
      requestUrl = `/article/article/${this.$route.params.articleNo}?userNo=${this.userInfo.userNo}`;
    }else{
      requestUrl = `/article/article/${this.$route.params.articleNo}`;
    }

    axios.get(requestUrl)
    .then( ({data}) => {
      this.articleTagCat = data;
      // console.log('123123123123123123123123123123123')
      // console.dir(this.articleTagCat);

      if(this.articleTagCat.article.reference > 0){

        axios.get(`/article/reference?articleNo=${this.articleTagCat.article.reference}`)
        .then( ({data}) => {
          this.reference = data;
        })
        .catch( () => {
          alert('참조글 정보를 가져오던 중 오류가 발생했습니다')
        })
      }
    })
    .catch(() => {
      alert("글 상세 정보를 불러오는 데 실패했습니다.")
    })

    this.resetComment().then(()=>{
      this.getCommentList();
    });
    window.Prism.highlightAll();
   
   window.onscroll = () => {
    if (!this.eventStop && (window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        this.getCommentList();
    }
};
  },
  updated(){
    window.Prism.highlightAll();
  }
}
</script>

<style scoped>
.pick{
  background-color:grey;
}
.comment{
  border-radius:15px;
}
</style>
