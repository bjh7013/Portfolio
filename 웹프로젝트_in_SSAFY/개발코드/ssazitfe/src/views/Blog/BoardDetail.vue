<template>
  <div class="blog-article-list" style="width:100%;">
    <blog-article :boardName="boardName" @changeOrder="setOrder"/>
    <div>
      <!-- for문 반복 -->
      <div class="blog-article-contents" style="width:100%;">
        <template v-if="currentArticles.length > 0" >
          <div v-for="currentArticle in currentArticles" :key="currentArticle.articleNo" class="mb-3">
            <v-row class="blog-article-post mb-7" style="height:155px;">
              <div class="blog-article-content" style="width:100%; border-bottom:1px solid #EEEE;">
                <v-row class="px-5" style="justify-content:space-between">
                  <div style="width:100%">
                    <p align="left" class="font-weight-medium caption mb-0" v-if="currentArticle.article.categoryName">[{{currentArticle.article.categoryName}}]</p>
                    <p align="left" class="font-weight-medium caption mb-0" v-if="currentArticle.article.acategoryNumber != 0">[<b>{{currentArticle.article.acategorySource}}</b> {{currentArticle.article.acategoryNumber}} - {{currentArticle.article.acategoryTitle}}] </p>
                    <h5 align="left" class="font-weight-black">
                      <router-link :to="`/bloghome/${$route.params.userId}/articledetail/${currentArticle.article.articleNo}`" class="text-decoration-none text-dark">
                        {{currentArticle.article.title}}
                      </router-link>
                    </h5>
                    <router-link :to="`/bloghome/${$route.params.userId}/articledetail/${currentArticle.article.articleNo}`" class="text-decoration-none text-dark">
                      <div
                        style="width:100%; 
                          display:-webkit-box;
                          -webkit-line-clamp:3;
                          -webkit-box-orient:vertical;
                          line-height:20px;
                          height:60px;
                          justify-content:space-between; 
                          overflow:hidden; 
                          white-space:normal;
                          text-overflow:ellipsis;
                          word-break:break-all;
                          table-layout:fixed;"
                      >
                        <p align="left" class="blog-article-word" v-html="currentArticle.article.content">
                        </p>
                      </div>
                    </router-link>
                  </div>
                  <div>
                    <router-link :to="`/bloghome/${$route.params.userId}/articledetail/${currentArticle.article.articleNo}`" class="text-decoration-none text-dark">
                      <v-img class="blog-article-thumbnail" :src="$store.getters.serverURL + currentArticle.article.thumbNail"></v-img>
                    </router-link>
                  </div>
                </v-row>
                <v-row class="blog-article-bottom mt-3 px-5" style="justify-content:space-between">
                  <v-col class="blog-article-info px-0">
                    <!-- 좋아요수를 댓글수로 바꾸기 -->
                    <p align="left" class="font-weight-light caption mb-0">
                      {{currentArticle.article.writeDate}} · 좋아요수 {{currentArticle.article.likeCount}}
                      <v-icon small>mdi-heart</v-icon>
                    </p>
                    <p align="left" class="font-weight-medium caption mb-0">
                      <span v-for="tag in currentArticle.arttag" :key="tag.tagNo" @click="$emit('set-search-word', tag.tagName)" class="tags">
                        #{{tag.tagName}}
                      </span>
                      <span v-for="tag in currentArticle.tmpTag" :key="tag.tmptagName" @click="$emit('set-search-word', tag.tmptagName)" class="tags">
                        #{{tag.tmptagName}}
                      </span>
                    </p>
                  </v-col>
                  <v-col class="blog-article-icon px-0" cols="2" align="right">
                    <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                      <v-icon>mdi-star-outline</v-icon>
                    </v-btn>
                    <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                      <v-icon>mdi-dots-horizontal</v-icon>
                    </v-btn>
                  </v-col>
                </v-row>
              </div>
              
            </v-row>
            <br/>
          </div>
          <v-col cols="12">
            <v-pagination
              v-model="currentPage" 
              :length="articlePageLength"
            ></v-pagination>
          </v-col>
        </template>
        <template v-else>
          <h4 align="center">작성된 글이 없습니다.</h4>
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
    </div>
  </div>
</template>

<script>
import BlogArticle from "./BlogArticle.vue";
import axios from 'axios';
export default {
    name: 'BoardDetail',
    props:['searchWord'],
    data(){
      return {
        boardnum : this.$route.params.boardNo,
        articleTagCatList: [],
        currentPage: 1,
        boardName: '',
        groupNo:'',
        seqNo:'',
        orderKey:'',
        orderValue:'',
      }
    },
    components:{
      BlogArticle
    },
    watch:{
      searchWord : function(){
        this.searchArticle();
      }
    },
    
    methods:{
      setOrder(order){
        if(order == '최신순'){
          this.orderKey = 'writeDate' 
        }else if(order == '인기순'){
          this.orderKey = 'likeCount'
        }else{
          this.orderKey = '';
        }
        this.searchArticle();
      },
      searchArticle(){
        let requestURL;
        if(this.boardnum){
          requestURL = `/article/articles?groupNo=${this.groupNo}&seqNo=${this.seqNo}&searchValue=`
        }else{
          requestURL = `/article/articles?userId=${this.$route.params.userId}&searchValue=`;
        }
        if(this.searchWord){
          requestURL += this.searchWord;
        }

        if(this.orderKey){
          requestURL += `&orderKey=${this.orderKey}` 
        }

        axios.get(requestURL)
        .then(({data}) => {
          // console.log(data);
          this.articleTagCatList = data;
          // console.dir(this.articleTagCatList);
          // console.log(this.articleTagCatList.length);
          
          for(let i = 0; i < this.articleTagCatList.length; i++){
              this.articleTagCatList[i].article.content = this.parse(this.articleTagCatList[i].article.content);
          }
        })
        .catch(() => {
          // alert("게시판 글 목록을 가져오는 데 실패했습니다.");
        });
      },
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
          document.getElementsByTagName('hr')
          let hr = document.getElementsByTagName('hr');
          let len = document.getElementsByTagName('hr').length;
          for(let i = 0; i < len; i++){
            // console.log(i);
            hr[0].parentElement.removeChild(hr[0]);
          } 
          
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

      parse(article){
        var sax = require("sax");
        let strict = false; // set to false for html-mode
        let parser = sax.parser(strict, {lowercase : true});
        let parsed = '';
        let target = '';
        let isPTag = false;
        
        parser.onerror = (e) => {
          // an error happened.
          alert('!!!!!!!!!!!!!!!!!!!!!!!!!')
          // console.log(e);
        };

        parser.ontext =  (t) => {
          // got some text.  t is the string of text.
          // console.log('ontext==========================================');
          // console.log(t);
          if(isPTag){
            parsed += t;
          }
        };

        parser.onopentag = (node) => {
          // console.log('onopentag==========================================');
          // console.dir(node);

          let tmp = "<" + node.name;
          // console.log(tmp);
          // console.log(parsed);
          
          if(node.name == 'root'){
            return;
          }

          if(node.name == 'p'){
            isPTag = true;
            parsed = '<p>'; 
          }
        };

        parser.onclosetag = (node) => {
          // console.log('onclosetag==========================================');

          // console.dir(node);
          if(node == 'p'){
            
            isPTag = false;
            parsed += '</p>';
            target += parsed;  
          }
          
        };
      
        parser.onend = () => {
          // parser stream is done, and ready to have more stuff written to it.
          // console.log('end==================================================')
          // alert('end')
          // this.target += parsed;
        };
        
        parser.write('<root>' + article + '</root>');        
        return target;
      },
    },
    created(){
      if(this.boardnum){
        
        axios.get(`/blog/myboard?boardNo=${this.boardnum}&orderKey=writeDate`)
        .then(({data}) => {
          // console.log(data);
          // console.log(data.groupNo);
          // console.log(data.seqNo);
          this.groupNo = data.groupNo;
          this.seqNo = data.seqNo;
          this.boardName = data.boardName;

          axios.get(`/article/articles?groupNo=${data.groupNo}&seqNo=${data.seqNo}&orderKey=writeDate`)
          .then(({data}) => {
              // console.log(data);
              this.articleTagCatList = data;
              // console.dir(this.articleTagCatList);
              // console.log(this.articleTagCatList.length);

              for(let i = 0; i < this.articleTagCatList.length; i++){
                this.articleTagCatList[i].article.content = this.parse(this.articleTagCatList[i].article.content);
              }
          })
          .catch(() => {
              // alert("게시판 글 목록을 가져오는 데 실패했습니다.");
          }); 
        })
      }else{
        let requestURL = `/article/articles?userId=${this.$route.params.userId}&orderKey=writeDate`
        if(this.$route.query.q){
          requestURL += `&searchValue=${this.$route.query.q}`;
        }

        axios.get(requestURL)
        .then(({data}) => {
            // console.log(data);
            this.articleTagCatList = data;
            // console.dir(this.articleTagCatList);
            // console.log(this.articleTagCatList.length);
            for(let i = 0; i < this.articleTagCatList.length; i++){
                this.articleTagCatList[i].article.content = this.parse(this.articleTagCatList[i].article.content);
              }
        })
        .catch(() => {
            alert("게시판 글 목록을 가져오는 데 실패했습니다.");
        }); 
        
        }
      
    },
   
    computed: {
      userInfo() {
        return this.$store.getters.boardnumInfo;
      },

      // 페이지네이션
      startArticleNo() {
        return ((this.currentPage-1) * 10);
      },
      endArticleNo() {
        return (this.startArticleNo + 10);
      },
      articlePageLength() {
        return Math.ceil(this.articleTagCatList.length / 10);
      },
      currentArticles() {
        return this.articleTagCatList.slice(this.startArticleNo, this.endArticleNo);
      },
    },
    updated() {
      window.Prism.highlightAll();
      this.removeIframe();
      this.removeCode();
    },
}
</script>

<style>
.blog-article-word * {
  margin-bottom: 0px !important;
  font-size: 14px !important;
  line-height: 20px;
  /* width: 980px; */
  padding-right: 150px;
}
.blog-article-thumbnail{
  position: absolute;
  right: 0px;
  width: auto; height: auto;
  max-width: 120px;
  max-height: 120px;
}

.blog-article-scene {
  position: relative;
  width: 640px;
  height: 360px;
  margin: auto;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  overflow: hidden;
}

.blog-article-layer {
  position: absolute;
  width: 100%;
  height: 100%;
}
.blog-article-layer-1 {
  z-index: 10;
}
.blog-article-layer-2 {
  z-index: 5;
}
.blog-article-layer-3 {
  z-index: 1;
}
.blog-article-layer div {
  position: absolute;
}

/*
*   .layer-1 styles START
*/
.blog-article-desk {
  width: 100%;
  height: 2%;
  background-color: #957c43;
  bottom: 0;
  z-index: 10;
}

.blog-article-plant {
  width: 12%;
  bottom: 2%;
  left: 1%;
  height: 50%;
  z-index: 5;
}
.blog-article-plant:before {
  content: '';
  display: block;
  width: 100%;
  height: 25%;
  position: absolute;
  bottom: 0;
  z-index: 5;
  background: grey;
  border-bottom-right-radius: 40px;
  border-bottom-left-radius: 40px;
  box-shadow: inset 5px 0px 0px #fff;
  -webkit-animation: flicker-on-shadow 1s forwards;
          animation: flicker-on-shadow 1s forwards;
  -webkit-animation-delay: 1s;
          animation-delay: 1s;
}
.blog-article-plant-stalk {
  width: 8%;
  height: 100%;
  margin: auto;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 1;
  background-color: #21bf21;
  border-radius: 4px;
}
.blog-article-plant-leaf {
  width: 100%;
  height: 20%;
  top: 40%;
}
.blog-article-plant-leaf:before, .blog-article-plant-leaf:after {
  content: '';
  display: block;
  height: 100%;
  width: 48%;
  position: absolute;
  background-color: #21bf21;
}
.blog-article-plant-leaf:before {
  left: 0;
  border-bottom-left-radius: 90%;
  border-top-right-radius: 90%;
  top: 30%;
  -webkit-transform-origin: 100% 100%;
          transform-origin: 100% 100%;
  -webkit-animation: leaf-dance 3s ease-in-out infinite;
          animation: leaf-dance 3s ease-in-out infinite;
}
.blog-article-plant-leaf:after {
  right: 0;
  border-bottom-right-radius: 90%;
  border-top-left-radius: 90%;
  top: -30%;
  -webkit-transform-origin: 0 100%;
          transform-origin: 0 100%;
  -webkit-animation: leaf-dance-reverse 3s ease-in-out infinite;
          animation: leaf-dance-reverse 3s ease-in-out infinite;
}
.blog-article-plant-leaf + .blog-article-plant-leaf {
  top: 10%;
}

.blog-article-lamp {
  width: 30%;
  height: 80%;
  bottom: 2%;
  right: 0;
  z-index: 5;
}
.blog-article-lamp-base {
  width: 60%;
  height: 4%;
  bottom: 0;
  right: 0;
  background-color: #00a3f6;
  border-radius: 3px;
  z-index: 10;
}
.blog-article-lamp-base:before, .blog-article-lamp-base:after {
  content: '';
  display: block;
  position: absolute;
  margin: auto;
  left: 0;
  right: 0;
  bottom: 100%;
  background-color: #EEEE;
}
.blog-article-lamp-base:before {
  height: 80%;
  width: 75%;
  border-radius: 3px;
}
.blog-article-lamp-base:after {
  height: 180%;
  width: 20%;
  border-top-left-radius: 50%;
  border-top-right-radius: 50%;
}
.blog-article-lamp-mid {
  width: 100%;
  height: 100%;
  z-index: 1;
}
.blog-article-lamp-mid:before, .blog-article-lamp-mid:after {
  content: '';
  display: block;
  position: absolute;
  background-color: #EEEE;
}
.blog-article-lamp-mid:before {
  width: 5%;
  height: 50%;
  bottom: 47%;
  right: 3%;
  -webkit-transform-origin: 50% 100%;
          transform-origin: 50% 100%;
  -webkit-transform: rotate(-48deg);
          transform: rotate(-48deg);
}
.blog-article-lamp-mid:after {
  height: 50%;
  width: 5%;
  bottom: 1%;
  right: 32%;
  -webkit-transform-origin: 50% 100%;
          transform-origin: 50% 100%;
  -webkit-transform: rotate(23deg);
          transform: rotate(23deg);
}
.blog-article-lamp-mid-knob {
  width: 12%;
  height: 8%;
  background-color: #EEEE;
  top: 50%;
  right: 0;
  border-radius: 50%;
}
.blog-article-lamp-top {
  width: 16%;
  height: 10%;
  top: 16%;
  right: 44%;
  background-color: #00a3f6;
  border-top-left-radius: 100%;
  border-top-right-radius: 100%;
  -webkit-transform-origin: 50% 0;
          transform-origin: 50% 0;
  -webkit-transform: rotate(42deg);
          transform: rotate(42deg);
  z-index: 10;
}
.blog-article-lamp-top:before, .blog-article-lamp-top:after {
  content: '';
  display: block;
  position: absolute;
  left: 0;
  right: 0;
}
.blog-article-lamp-top:before {
  width: 300%;
  height: 150%;
  background-color: #00a3f6;
  border-top-left-radius: 50px;
  border-top-right-radius: 50px;
  top: 50%;
  left: -100%;
  right: -100%;
  z-index: 1;
}
.blog-article-lamp-top:after {
  width: 340%;
  height: 30%;
  background-color: #EEEE;
  border-radius: 3px;
  top: 190%;
  left: -120%;
  z-index: 5;
}
.blog-article-lamp-light {
  width: 120%;
  height: 70%;
  bottom: 0;
  background-color: #f8f8a9;
  opacity: 0;
  -webkit-transform: rotate(-15deg);
          transform: rotate(-15deg);
  -webkit-transform-origin: 100% 0;
          transform-origin: 100% 0;
  right: 20%;
  top: 33%;
  z-index: 1;
  -webkit-animation: flicker-on 1s forwards;
          animation: flicker-on 1s forwards;
  -webkit-animation-delay: 1s;
          animation-delay: 1s;
}

.blog-article-laptop {
  width: 35%;
  height: 45%;
  bottom: 2%;
  left: 25%;
  background-color: #a4a4a2;
  border-radius: 8px;
  z-index: 10;
}
.blog-article-laptop:before, .blog-article-laptop:after {
  content: '';
  display: block;
  position: absolute;
  margin: auto;
  left: 0;
  right: 0;
}
.blog-article-laptop:before {
  height: 3px;
  width: 25%;
  top: 5%;
  background: -webkit-gradient(linear, left top, right top, from(#52ab73), color-stop(25%, #52ab73), color-stop(25%, #d50f25), color-stop(50%, #d50f25), color-stop(50%, #eeb211), color-stop(75%, #eeb211), color-stop(75%, #3369e8), to(#3369e8));
  background: linear-gradient(90deg, #52ab73 0%, #52ab73 25%, #d50f25 25%, #d50f25 50%, #eeb211 50%, #eeb211 75%, #3369e8 75%, #3369e8 100%);
}

.blog-article-bookPic-0 {
  height: 4%;
  width: 15%;
  background-color: #fff;
  border: 3px solid #f00;
  border-left: none;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  bottom: 2%;
  left: 64%;
  z-index: 5;
}
.blog-article-bookPic-1 {
  height: 1%;
  width: 15%;
  bottom: 6%;
  left: 65%;
  background-color: #00a3f6;
  border-top-right-radius: 2px;
  border-bottom-right-radius: 2px;
  z-index: 5;
}
.blog-article-bookPic-2 {
  height: 4%;
  width: 15%;
  background-color: #fff;
  border: 3px solid #21bf21;
  border-left: none;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  bottom: 7%;
  left: 63%;
  z-index: 5;
}

.blog-article-coffee {
  width: 7%;
  height: 15%;
  bottom: 11%;
  left: 67%;
  background-color: grey;
  border-radius: 8px;
  box-shadow: inset 5px 0px 0px grey;
  z-index: 10;
  -webkit-animation: flicker-on-shadow 1s forwards;
          animation: flicker-on-shadow 1s forwards;
  -webkit-animation-delay: 1s;
          animation-delay: 1s;
}
.blog-article-coffee:before, .blog-article-coffee:after {
  content: '';
  display: block;
  width: 10%;
  height: 25%;
  position: absolute;
  background-color: #fff;
  bottom: 110%;
  border-radius: 3px;
}
.blog-article-coffee:before {
  left: 33%;
  -webkit-animation: steam 2.5s ease infinite;
          animation: steam 2.5s ease infinite;
}
.blog-article-coffee:after {
  left: 66%;
  -webkit-animation: steam 3s ease infinite;
          animation: steam 3s ease infinite;
  bottom: 130%;
}
.blog-article-coffee-handle {
  width: 40%;
  height: 50%;
  left: 85%;
  top: 25%;
  border: 3px solid grey;
  border-radius: 15%;
  z-index: 1;
}
.blog-article-coffee-brew {
  width: 75%;
  height: 8%;
  margin: auto;
  background-color: #471803;
  border-radius: 5px;
  top: 5%;
  left: 0;
  right: 0;
}

.blog-article-glass {
  width: 8%;
  height: 20%;
  bottom: 2%;
  left: 15%;
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 3px 3px 40% 40%;
  z-index: 10;
}
.blog-article-glass:before {
  content: '';
  display: block;
  height: 60%;
  width: 80%;
  position: absolute;
  margin: auto;
  left: 0;
  right: 0;
  bottom: 6%;
  background-color: #7fcbdd;
  border-radius: 0 0 60% 60%;
}

/*
*   .layer-1 styles END
*/
/*
*   .layer-2 styles START
*/
.blog-article-self {
  width: 25%;
  height: 40%;
  left: 30%;
  bottom: 47%;
  background-color: #fff;
}
.blog-article-self:after {
  content: '';
  display: block;
  width: 100%;
  height: 30%;
  background-color: #f00;
  position: absolute;
  bottom: -15%;
  border-top-left-radius: 100%;
  border-top-right-radius: 100%;
}
.blog-article-self-head {
  width: 50%;
  height: 70%;
  margin: auto;
  bottom: 13%;
  left: 0;
  right: 0;
  border-top-left-radius: 25%;
  border-top-right-radius: 25%;
  border-bottom-right-radius: 40% 60%;
  border-bottom-left-radius: 40% 60%;
  background-color: #957c43;
  z-index: 10;
}
.blog-article-self-head:after {
  content: '';
  display: block;
  width: 110%;
  height: 70%;
  margin: auto;
  position: absolute;
  left: -100%;
  right: -100%;
  background-color: #4f4123;
  border-top-left-radius: 35%;
  border-top-right-radius: 35%;
  border-bottom-right-radius: 40% 60%;
  border-bottom-left-radius: 40% 60%;
  z-index: 1;
}

/*
*   .layer-2 styles END
*/
/*
*   .layer-3 styles START
*/
.blog-article-painting {
  width: 13%;
  height: 28%;
  left: 10%;
  top: 5%;
  border: 6px solid #000;
  background-color: #eee;
  border-radius: 10px 0 10px 0;
}
.blog-article-painting-inner {
  width: 50%;
  height: 40%;
  border-radius: 50%;
  background-color: #00a3f6;
  top: 10%;
  left: 10%;
  z-index: 10;
}
.blog-article-painting-inner:before, .blog-article-painting-inner:after {
  content: '';
  display: block;
  position: absolute;
}
.blog-article-painting-inner:before {
  height: 0;
  width: 0;
  border-bottom: 32px solid yellow;
  border-left: 18px solid transparent;
  border-right: 18px solid transparent;
  top: 120%;
  left: 0%;
  z-index: 1;
  -webkit-transform: rotate(45deg);
          transform: rotate(45deg);
  -webkit-transform-origin: 100% 100%;
          transform-origin: 100% 100%;
}
.blog-article-painting-inner:after {
  height: 160%;
  width: 80%;
  background-color: #f00;
  left: 80%;
  z-index: 5;
}

/*
*   .layer-3 styles END
*/
/*
*  animations
*/
@-webkit-keyframes flicker-on {
  0% {
    opacity: 0;
  }
  49% {
    opacity: 0;
  }
  50% {
    opacity: .8;
  }
  51% {
    opacity: 0;
  }
  75% {
    opacity: 0.7;
  }
  77% {
    opacity: 0;
  }
  78% {
    opacity: .8;
  }
  79% {
    opacity: 0;
  }
  80% {
    opacity: .7;
  }
  100% {
    opacity: .7;
  }
}
@keyframes flicker-on {
  0% {
    opacity: 0;
  }
  49% {
    opacity: 0;
  }
  50% {
    opacity: .8;
  }
  51% {
    opacity: 0;
  }
  75% {
    opacity: 0.7;
  }
  77% {
    opacity: 0;
  }
  78% {
    opacity: .8;
  }
  79% {
    opacity: 0;
  }
  80% {
    opacity: .7;
  }
  100% {
    opacity: .7;
  }
}
@-webkit-keyframes flicker-on-shadow {
  0% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  49% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  50% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  51% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  75% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  77% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  78% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  79% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  80% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  100% {
    box-shadow: inset 5px 0px 0px #545454;
  }
}
@keyframes flicker-on-shadow {
  0% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  49% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  50% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  51% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  75% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  77% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  78% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  79% {
    box-shadow: inset 5px 0px 0px #fff;
  }
  80% {
    box-shadow: inset 5px 0px 0px #545454;
  }
  100% {
    box-shadow: inset 5px 0px 0px #545454;
  }
}
@-webkit-keyframes leaf-dance {
  25% {
    -webkit-transform: rotate(-6deg);
            transform: rotate(-6deg);
  }
  75% {
    -webkit-transform: rotate(6deg);
            transform: rotate(6deg);
  }
  100% {
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
}
@keyframes leaf-dance {
  25% {
    -webkit-transform: rotate(-6deg);
            transform: rotate(-6deg);
  }
  75% {
    -webkit-transform: rotate(6deg);
            transform: rotate(6deg);
  }
  100% {
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
}
@-webkit-keyframes leaf-dance-reverse {
  25% {
    -webkit-transform: rotate(6deg);
            transform: rotate(6deg);
  }
  75% {
    -webkit-transform: rotate(-6deg);
            transform: rotate(-6deg);
  }
  100% {
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
}
@keyframes leaf-dance-reverse {
  25% {
    -webkit-transform: rotate(6deg);
            transform: rotate(6deg);
  }
  75% {
    -webkit-transform: rotate(-6deg);
            transform: rotate(-6deg);
  }
  100% {
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
}
@-webkit-keyframes steam {
  0% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    opacity: 1;
  }
  30% {
    -webkit-transform: translateY(-30%);
            transform: translateY(-30%);
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}
@keyframes steam {
  0% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    opacity: 1;
  }
  30% {
    -webkit-transform: translateY(-30%);
            transform: translateY(-30%);
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}
.tags{
  cursor:pointer;
}
</style>