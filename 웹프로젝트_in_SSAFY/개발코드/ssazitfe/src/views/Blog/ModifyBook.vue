<template>
  <div>
    <div class="article-setting" style="height:100%; width:100%;">
      <div align="left" class="title-content">
        <v-btn text dense @click="backList" color="black" class="ml-0 pl-0 mb-5">
          <v-icon>mdi-arrow-left</v-icon>돌아가기
        </v-btn>
      </div>
      <v-row class="pl-3 mb-6" style="justify-content:start; width:100%;">
        <h4 class="text-justify font-weight-bold m-0" style="vertical-align:middle;">
          <div style="height:10px;"></div>
          책수정
        </h4>
      </v-row>

      <div class="mt-5 mb-3 row" style="text-align:left;">
        <b class="ml-3">책 기본 설정</b>
        <v-spacer></v-spacer>
        <v-btn @click="openInfo" class="pr-0" style="background-color:white; box-shadow:none;">책은 어떻게 만드나요  <i class="fa fa-question-circle mr-3"></i></v-btn>
        <v-dialog v-model="infoDialog" max-width="500">    
          <v-card>
            <v-row>
              <v-card-title class="headline pb-0 ml-10 col-3">책 만들기 가이드</v-card-title>
              <v-spacer></v-spacer>
              <v-btn class="mt-3 mr-10 col-1" color="green darken-1" text @click="infoDialog = false">취소<v-icon>mdi-close</v-icon></v-btn>
            </v-row>
            <v-card-text style="padding: 20px 40px;">
              <template v-if="infoDialog">
                <p>1. 책 기본 설정</p>
                <p>- 공개: 책의 공개 범위를 설정할 수 있습니다.</p>
                <p> (전체공개시에만 메인에 등록됩니다.)</p>
                <p>- 카테고리: 글의 유형을 설정할 수 있습니다.</p>
                <p>- 제목: 책의 제목을 입력할 수 있습니다.</p>
                <p>2. 표지 설정</p>
                <p>표지의 이미지를 업로드할 수 있습니다.</p>
                <p>(표지 미리보기를 통해 확인할 수 있습니다.)</p>
                <p>3. 내용 설정</p>
                <p>- 목차: 책의 목차를 설정할 수 있습니다.</p>
                <p>- 내용: 전체글 목록을 가져와 내용을 책으로 합칠 수 있습니다.</p>
                <p>- 출처: 공유한 글인 경우 출처를 남겨주세요.</p>
                <p>4. 태그 편집</p>
                <p>태그를 자유롭게 설정할 수 있습니다.</p>
                <p></p>
              </template>
              <template v-else>
              </template>
            </v-card-text>
            <v-card-actions>
              
            </v-card-actions>
          </v-card>
        </v-dialog>
        <div class="p-0" style="width:100px;"></div>
      </div>
      <div class="article-setting-base" style="width:100%;">
      
        <v-row class="px-3">
          <div class="mr-3 ml-1">
            <div style="height:10px;"></div>
            <span class="">공개</span>
          </div>
          <v-col class="d-flex p-0 mr-3" cols="2">
            <v-select
              v-model="booktagcat.book.openScope"
              :items="openItems"
              item-value="value" 
              item-text="name"
              label="공개"
              outlined
              class="py-1"
              dense
            ></v-select>
          </v-col>

          <div class="mr-3 ml-1">
            <div style="height:10px;"></div>
            <span class="">카테고리설정</span>
          </div>
          <v-col class="d-flex p-0 mr-3" cols="2">
            <v-combobox
              v-model="categorySelected"
              :items="categoryItems"
              item-value="categoryNo"
              item-text="categoryName"
              label="카테고리"
              outlined
              dense
              class="py-1"
            ></v-combobox>
          </v-col>
          
        </v-row>

        <v-row class="px-3 mr-15">
          <v-row class="px-3">
            <div>
              <div style="height:10px;"></div>
              <span class="mr-3 ml-2">제목</span>
            </div>
            <v-col class="py-0 pl-0">
              <v-text-field
                v-model="booktagcat.book.bookTitle"
                outlined
                clearable
                dense
                :rules="nameRules"
                :counter="20"
                required
                placeholder="제목을 입력해주세요"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-row>
        <hr>

        <div :class="{ hideArea : isImg }">
          <p align="left" class="m-0 font-weight-bold">표지 설정</p>
          <!-- <hr class="mt-0"> -->
          <!-- 드래그 업로드할꺼면 필요없는 부분 -->
          <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
          <v-row class="px-5 mr-16">
            <v-col cols="1" align="left" class="p-0">
              <div style="height:13px;"></div>
              <p>앞 표지</p>
            </v-col>
            <v-col class="px-2 pt-0" align="left" style="width:80%; height:44px; border: 1px solid grey; border-radius: 5px;">
              <div style="height:6px;"></div>
              <p class="p-0 m-0" style="color:grey;">
                <input type="file" @change="frontFileChange" ref="front_image_upload"/>
              </p>
            </v-col>
            <div>
              <div style="height:5px;"></div>
              <v-btn icon value="삭제" color="white" class="text-decoration-none" @click="frontFileReset">
                <v-icon color="grey">mdi-close</v-icon>
              </v-btn>
            </div>

          </v-row>
          <v-row class="px-5 mr-16">
            <v-col cols="1" align="left" class="p-0">
              <div style="height:13px;"></div>
              <p>등 표지</p>
            </v-col>
            <v-col class="px-2 pt-0" align="left" style="width:80%; height:44px; border: 1px solid grey; border-radius: 5px;">
              <div style="height:6px;"></div>
              <p class="p-0 m-0" style="color:grey;"><input type="file" @change="middleFileChange" ref="middle_image_upload"/></p>
            </v-col>
            <div>
              <div style="height:5px;"></div>
              <v-btn icon value="삭제" color="white" class="text-decoration-none" @click="middleFileReset">
                <v-icon color="grey">mdi-close</v-icon>
              </v-btn>
            </div>
          </v-row>
          <v-row class="px-5 mr-16">
            <v-col cols="1" align="left" class="p-0">
              <div style="height:13px;"></div>
              <p>뒷 표지</p>
            </v-col>
            <v-col class="px-2 pt-0" align="left" style="width:80%; height:44px; border: 1px solid grey; border-radius: 5px;">
              <div style="height:6px;"></div>
              <p class="p-0 m-0" style="color:grey;"><input type="file" @change="backFileChange" ref="back_image_upload"/></p>
            </v-col>
            <div>
              <div style="height:5px;"></div>
              <v-btn icon value="삭제" color="white" class="text-decoration-none" @click="backFileReset">
                <v-icon color="grey">mdi-close</v-icon>
              </v-btn>
            </div>
          </v-row>
          <br>

          <p align="left" class="m-0 font-weight-bold pb-3">미리보기</p>
          <!-- <hr class="mt-0"> -->
          <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->

          <!-- 400x500? 110x700?-->
          <!-- 미리보기 -->
          <v-row class="px-4 mb-5" >
            <!-- 뒤 -->
            <v-col class="p-0" cols="5" style="border: 1px solid grey; height:545px; overflow:hidden;">
              <v-img :src="coverBack" height="545px" ></v-img>
            </v-col>
            <!-- 중 -->
            <v-col class="p-0" cols="1" style="border: 1px solid grey; height:545px; overflow:hidden;">
              <v-img :src="coverMiddle" height="545px"></v-img>
            </v-col>
            <!-- 앞 -->
            <v-col class="p-0" cols="5" style="border: 1px solid grey; height:545px; overflow:hidden;">
              <v-img :src="coverFront" height="545px"></v-img>
            </v-col>
          </v-row>

        <hr>
        </div>

        <!-- 책 내용 영역 -->
        <div :class="{ hideArea : isContent }">
          <div class="mt-5 mb-3" style="text-align:left;">
            <b>내용 설정</b>
          </div>
          <v-row class="pl-0 mt-0 mr-0 ml-0" justify="start" style="width:91%;">
            <!-- <div> 
              <div style="height:250px;"></div>
              <v-btn-toggle
                borderless
                cols="1"
              >
                <v-btn icon color="white" class="text-decoration-none" @click="toggle">
                  <v-icon x-large>mdi-chevron-left</v-icon>
                </v-btn>
              </v-btn-toggle>
            </div> -->
            <v-col class="blog-tab-wrap px-0">

              <input type="radio" id="blog-tab1" name="blog-tabGroup1" class="blog-tab" checked>
              <label for="blog-tab1">목차</label>

              <input type="radio" id="blog-tab2" name="blog-tabGroup1" class="blog-tab">
              <label for="blog-tab2">내용</label>

              <input type="radio" id="blog-tab3" name="blog-tabGroup1" class="blog-tab">
              <label for="blog-tab3">출처</label>
              <div class="blog-tab__content">
                <ckeditor :config="editorConfig" v-model="booktagcat.book.bookIndex"></ckeditor>
              </div>
              <div class="blog-tab__content">
                <ckeditor :config="editorConfigContent" v-model="booktagcat.book.bookContent"></ckeditor>
              </div>
              <div class="blog-tab__content">
                <ckeditor :config="editorConfig" v-model="booktagcat.book.bookReference"></ckeditor>
              </div>
            </v-col>
            <v-col cols="12" lg="3" class="py-0 pr-0 mt-5">
              <div style="height:460px; overflow:scroll; border: 1px solid grey">
                <div style="border-bottom: 1px solid grey">전체글 목록</div>
                <div class="list-group-item" v-for="articleItem in articleItems" :key="articleItem.article.articleNo">
                  <a style="color:black;" @click="addContent(articleItem.article)">{{articleItem.article.title}}</a>
                </div>
              </div>
            </v-col>
          </v-row>
          <v-btn
              class="col-11 font-weight-black"
              dark
              @click="openPreview"
          >
            내용 미리보기
          </v-btn>
          <v-dialog v-model="dialog" max-width="1000">    
            <v-card>
              <v-row>
                <v-card-title class="headline pb-0 ml-10 col-3">내용 미리보기</v-card-title>
                <v-spacer></v-spacer>
                <v-btn class="mt-3 mr-10 col-1" color="green darken-1" text @click="dialog = false">취소<v-icon>mdi-close</v-icon></v-btn>
              </v-row>
              <v-card-text class="px-auto">
                <template v-if="dialog">
                  <book-preview :editorData="target" :bookTitle="booktagcat.book.bookTitle" :bookIndex="booktagcat.book.bookIndex" :bookReference="booktagcat.book.bookReference"></book-preview>
                </template>
                <template v-else>
                </template>
              </v-card-text>
              
            </v-card>
          </v-dialog>
          <hr>
        </div>

        <!-- 태그 영역 -->
        <div class="mt-5 mb-3" style="text-align:left;">
          <b>태그 편집</b>
        </div>
        <v-col cols="11" class="p-0">
          <v-combobox
            v-model="tagSelected"
            :items="tagItems"
            item-value="tagNo" 
            item-text="tagName"
            label="태그를 선택해주세요. (입력후 enter)"
            multiple
            chips
            class="p-0 m-0"
          ></v-combobox>
        </v-col>

        <v-row v-if="isImg">
          <v-spacer></v-spacer>
            <v-btn-toggle
              borderless
              cols="1"
            >
              <v-btn value="이전" color="white" class="text-decoration-none" @click="toggle">
                <v-icon small>mdi-chevron-left</v-icon>
                <span class="hidden-sm-and-down mr-1">이전</span>
              </v-btn>
            </v-btn-toggle>
            <v-btn-toggle borderless cols="1" >
              <v-btn value="작성" color="white" class="text-decoration-none" @click="modifyBook">
                <span class="hidden-sm-and-down mr-1">작성</span>
                <v-icon small>mdi-pencil</v-icon>
              </v-btn>
            </v-btn-toggle>
          <v-col cols="1"></v-col>
        </v-row>
        <v-row v-else>
          <v-spacer></v-spacer>
            <v-btn-toggle borderless cols="1" >
              <v-btn value="다음" color="white" class="text-decoration-none" @click="toggle">
                <span class="hidden-sm-and-down mr-1">다음</span>
                <v-icon small>mdi-chevron-right</v-icon>
              </v-btn>
            </v-btn-toggle>
          <v-col cols="1"></v-col>
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import BookPreview from './BookPreview.vue'
export default {
  components:{
    BookPreview
  },
  data() {
    return {
      dialog: false,
      infoDialog: false,
      booktagcat:{},
      // book:{
      //   writer: '',
      //   bookTitle: '',
      //   bookIndex: '<p>목차</p>\n\n<p>1. 챕터1</p>\n\n<p>2. 챕터2</p>\n\n<p>3. 챕터3</p>\n\n<p>4. 챕터4</p>\n\n<p>5. 챕터5</p>\n',
      //   bookContent: '',
      //   bookReference: '<p>출처</p>\n\n<p>(번호. 아이디.(게시글작성날짜).게시글명/챕터명)</p>\n\n<p>1. bjh7013.(2020.08.09).영주바보 그건맞지/chapter1</p>\n\n<p>&nbsp;</p>\n',
      //   openScope: '',
      //   version: '',
      //   categoryNo: '',
      // },
      target:'',
      coverFront: '',
      coverMiddle: '',
      coverBack: '',
      front_img_file: '',
      middle_img_file: '',
      back_img_file: '',
      articleItems: [],
      nameRules: [
        v => !!v || '책제목을 입력해주세요.',
        v => v.length <= 20 || '20자 이내로 입력해주세요.',
      ],
      openItems: [
        {
          value:'public',
          name:'전체공개'
        },
        {
          value:'protected',
          name:'맞팔공개'
        },
        {
          value:'private',
          name:'비공개'
        }
      ],
      categoryItems: [],
      categorySelected: {},
      booktag: [],
      tmptag: [],
      tagItems: [],
      tagSelected: [],
      isImg: false,
      isContent: true,
      editorConfig: {
        extraPlugins: 'prism, youtube, image2',
        codeSnippet_theme: 'monokai_sublime',
        filebrowserImageUploadUrl: this.$store.getters.serverURL + 'file/articleimg',
        imageUploadUrl: this.$store.getters.serverURL + "file/articleimg",
      },
      editorConfigContent:{
        height: '800px',
        width : '100%',
        extraPlugins: 'prism, youtube, image2',
        codeSnippet_theme: 'monokai_sublime',
        filebrowserImageUploadUrl: this.$store.getters.serverURL + 'file/articleimg',
        imageUploadUrl: this.$store.getters.serverURL + "file/articleimg",
      },
    }
  },
  computed:{
    userInfo(){
      return this.$store.getters.userInfo;
    },
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  created(){

    // categoryItems
    axios.get(`/article/categorys`)
    .then((Response)=>{
      // console.log(Response.data);
      this.categoryItems = Response.data;
    })
    .catch(()=>{
      alert("카테고리 목록을 불러오는 데 실패했습니다.")
    })

    // articleItems
    axios.get(`/article/articles?userNo=` + encodeURI(this.userInfo.userNo))
    .then((Response)=>{
      // console.log(Response.data);
      this.articleItems = Response.data;
    })
    .catch(()=>{
      alert("게시글 목록을 불러오는 데 실패했습니다.")
    })

    // tagItems
    axios.get(`/tag/tags`)
    .then((Response) => {
      this.tagItems = Response.data;
      // console.dir(this.tagItems);
    })
    .catch(() => {
      alert("태그 목록을 불러오는 데 실패했습니다.")
    })

    // booktagcat
    axios.get(`/book/book/${this.$route.params.bookNo}?userNo=${this.$store.getters.userInfo.userNo}`)
    .then((Response)=>{
      // console.log(Response.data);
      this.booktagcat = Response.data;
      this.coverFront = this.$store.getters.serverURL + this.booktagcat.book.coverFront;
      this.coverMiddle = this.$store.getters.serverURL + this.booktagcat.book.coverMiddle;
      this.coverBack = this.$store.getters.serverURL + this.booktagcat.book.coverBack;
      this.categorySelected = Response.data.book.categoryName;
      for(let tmptag of Response.data.tmptag){
        this.tagSelected.push({tagName : tmptag.tmptagName});
      }
      for(let booktag of Response.data.booktag){
        this.tagSelected.push({tagNo : booktag.tagNo, tagName : booktag.tagName});
      }
      
      this.unParse(this.booktagcat.book.bookContent);
      this.booktagcat.book.bookContent = this.target;

    })
    .catch(()=>{
      alert("책 정보를 불러오는 데 실패했습니다.");
    })
  },
  methods: {
    openPreview(){
      this.parse();
      this.dialog = true;
    },
    openInfo(){
      this.parse();
      this.infoDialog = true;
    },
    unParse(content){
     
      var sax = require('sax');
      let strict = false;
      
      let parser = sax.parser(strict, {lowercase : true});

      let parsed = '';
      let isSelfClosing = false;
      let isPageBreak = 0;
      this.target = '';
      let isPageFooter = false;
      let pageTags = [];

      parser.ontext =  (t) => {
        // got some text.  t is the string of text.
        if(isPageFooter){
          isPageFooter = false;
          return;
        }
        parsed += t;
      };

      parser.onopentag = (node) => {
        // opened a tag.  node has "name" and "attributes"
        // alert('dddddddd')
        let tmp = "<" + node.name;

        if(node.name == 'root')
          return;

        if(node.attributes.class == 'page stf__item' || node.attributes.class == 'page-content' 
        || node.attributes.class == 'page-text' || node.attributes.class == 'page-footer'){


          if(node.attributes.class == 'page-footer'){
            isPageFooter = true;
          } 

          isPageBreak++;

          pageTags.push(true);
        }else{
          for(let attr of Object.keys(node.attributes)){
            tmp += ` ${attr}="${node.attributes[attr]}"`; 
          }
  
          if(node.isSelfClosing){
            tmp += "/>";
            isSelfClosing = true;
          }else{
            tmp += ">";
          }
  
          parsed += tmp;

          pageTags.push(false);
        }
      };

      parser.onclosetag = (node) => {
        if(node != 'root'){
          
          if(pageTags.pop()){ // page break인 div태그가 닫혔을 경우 해당 태그 제거 후 정해진 범위 page로 묶어주기
            isPageBreak--;

            if(isPageBreak == 0){
              parsed = '<div style="page-break-after:always" class="cke_pagebreak"><span style="display:none">&nbsp;</span></div>';
            }
          }else{
            if(!isSelfClosing){
              parsed += `</${node}>`
            }
  
            isSelfClosing = false;

          }

          this.target += parsed;
          parsed = '';
          
        }

      };
      parser.write('<root>' + content + "</root>");
    },
   parse(){
      var sax = require("sax");
      let strict = false; // set to false for html-mode
      let parser = sax.parser(strict, {lowercase : true});
      let parsed = '';
      let isSelfClosing = false;
      let isPageBreak = false;
      let pageNo = 1;
      this.target = '';

      parser.onerror = (e) => {
        // an error happened.
        alert('!!!!!!!!!!!!!!!!!!!!!!!!!')
        // console.log(e);
      };

      parser.ontext =  (t) => {
        // got some text.  t is the string of text.
        parsed += t;
      };

      parser.onopentag = (node) => {
        // opened a tag.  node has "name" and "attributes"
        // alert('dddddddd')
        let tmp = "<" + node.name;
        
        if(node.name == 'root'){
          return;
        }

        if(isPageBreak || node.attributes.style == 'page-break-after:always'){
          // alert('pagebreak')
          isPageBreak = true;
          // console.log(isPageBreak)
        }else{
          for(let attr of Object.keys(node.attributes)){
            tmp += ` ${attr}="${node.attributes[attr]}"`; 
          }
  
          if(node.isSelfClosing){
            tmp += "/>";
            isSelfClosing = true;
          }else{
            tmp += ">";
          }
  
          parsed += tmp;
        }
      };

      parser.onclosetag = (node) => {
        if(node != 'root'){
          
          if(isPageBreak){ // page break인 div태그가 닫혔을 경우 해당 태그 제거 후 정해진 범위 page로 묶어주기
            if(node == 'div'){
             
              parsed = '<div class="page stf__item"><div class="page-content"><div class="page-text">' 
                      + parsed 
                      + '</div> <div class="page-footer">' + pageNo++ + '</div></div></div>'
              this.target += parsed;
              parsed = '';
              isPageBreak = false;
            }
          }else{
            if(!isSelfClosing){
              parsed += `</${node}>`
            }
  
            isSelfClosing = false;
          }
              
        }else{
           parsed = '<div class="page stf__item"><div class="page-content"><div class="page-text">' 
                      + parsed 
                      + '</div> <div class="page-footer">' + pageNo++ + '</div></div></div>'
          this.target += parsed;
          parsed = '';
        }

        
      };
     
      parser.onend = () => {
        // parser stream is done, and ready to have more stuff written to it.
        console.log('end==================================================')
        // this.target += parsed;
      };
      
      parser.write('<root>' + this.booktagcat.book.bookContent + '</root>');
      
    },
    backList(){
      this.$router.go(-1);
    },
    frontFileChange(e){
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) { 
        return;
      } 
      // console.log("filechange!");
      this.front_img_file = files[0];
      // console.log(files[0]);
      this.createFrontImage(files[0]);
    },
    createFrontImage(file) { 
      var reader = new FileReader();
      var vm = this;
      reader.onload = (e) => { 
        vm.coverFront = e.target.result;
      } 
      // console.log("createImage!!");
      reader.readAsDataURL(file);
    },
    middleFileChange(e){
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) { 
        return;
      } 
      // console.log("filechange!");
      this.middle_img_file = files[0];
      // console.log(files[0]);
      this.createMiddleImage(files[0]);
    },
    createMiddleImage(file) { 
      var reader = new FileReader();
      var vm = this;
      reader.onload = (e) => { 
        vm.coverMiddle = e.target.result;
      } 
      reader.readAsDataURL(file);
    },
    backFileChange(e){
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) { 
        return;
      } 
      this.back_img_file = files[0];
      // console.log(files[0]);
      this.createBackImage(files[0]);
    },
    createBackImage(file) { 
      var reader = new FileReader();
      var vm = this;
      reader.onload = (e) => { 
        vm.coverBack = e.target.result;
      } 
      reader.readAsDataURL(file);
    },
    log:function(evt){
      // window.console.log(evt);
    },
    modifyBook(){

      // 데이터 유효성 검사 ~
      if(!this.booktagcat.book.bookTitle || this.booktagcat.book.bookTitle.trim() == ''){
        alert("책 제목을 입력해주세요!");
        return;
      }
      if(!this.booktagcat.book.bookContent || this.booktagcat.book.bookContent.trim() == ''){
        alert("책 내용을 입력해주세요!");
        return;
      }
      // ~ 데이터 유효성 검사

      // 사전작업 ~
      // console.log("-----사전작업-----");
      
      // 책
      // console.log("책");
      // console.log(this.book);
      if(this.booktagcat.book.categoryName != this.categorySelected){
        this.booktagcat.book.categoryName = this.categorySelected.categoryName;
        this.booktagcat.book.categoryNo = this.categorySelected.categoryNo;
      }

      // 태그
      // console.log("태그");
      for(let tag of this.tagSelected){
        if(tag instanceof Object && tag.tagNo){
          this.booktag.push({tagNo : tag.tagNo});
        }else if(tag.tagName){
          this.tmptag.push({tmptagName : tag.tagName});
        }else{
          this.tmptag.push({tmptagName : tag});
        }
      }
      // console.log(this.booktag);
      // console.log(this.tmptag);

      // ~ 사전작업

      // 데이터 전송 ~
      
      // 사진 전송
      var form = new FormData();
      form.append("frontFile",this.front_img_file);
      form.append("middleFile",this.middle_img_file);
      form.append("backFile",this.back_img_file);

      //페이지 브레이크 파싱
      this.parse();
      this.booktagcat.book.bookContent = this.target;

      axios.post(`/file/create_book_cover`, form
      ).then((Response)=>{
        // console.log(Response.data);
        // 책 전송
        axios.put(`/book/CUD`, {
          book: this.booktagcat.book,
          booktag: this.booktag,
          tmptag: this.tmptag,
          fileinfo: Response.data
        }).then(()=>{
          alert("책 수정이 완료되었습니다.");
          this.$router.go(-1);
        }).catch(()=>{
          alert("책 작성을 실패했습니다.");
          this.booktag = [];
          this.tmptag = [];
        })
      }).catch(()=>{
        alert("이미지 업로드에 실패했습니다.");
        this.booktag = [];
        this.tmptag = [];
      })
      
      // ~ 데이터 전송

    },
    toggle() {
      this.isImg = !this.isImg;
      this.isContent = !this.isContent;
    },
    printtest(){
      // console.log(this.book.bookIndex);
    },
    addContent(data){
      // console.log(data.content);
      this.booktagcat.book.bookContent += data.content + '<div style="page-break-after:always"><span style="display:none">&nbsp;</span></div>';
    },
    frontFileReset(){
      this.coverFront = this.$store.getters.serverURL + this.booktagcat.book.coverFront;
      this.front_img_file = '';
      this.$refs.front_image_upload.value=null;
    },
    middleFileReset(){
      this.coverMiddle = this.$store.getters.serverURL + this.booktagcat.book.coverMiddle;
      this.middle_img_file = '';
      this.$refs.middle_image_upload.value=null;
    },
    backFileReset(){
      this.coverBack = this.$store.getters.serverURL + this.booktagcat.book.coverBack;
      this.back_img_file = '';
      this.$refs.back_image_upload.value=null;
    },
  }
}
</script>


<style>
.hideArea{
  display: none;
}
.blog-tab-wrap {
  max-width: 100%;
  display: -webkit-box;
  display: flex;
  flex-wrap: wrap;
  position: relative;
  list-style: none;
  background-color: #fff;
  margin: 18px 0;
  padding: 0;
}
.blog-tab {
  display: none;
}
.blog-tab:checked:nth-of-type(1) ~ .blog-tab__content:nth-of-type(1) {
  opacity: 1;
  -webkit-transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease, 0.8s -webkit-transform ease;
  position: relative;
  top: 0;
  z-index: 100;
  -webkit-transform: translateY(0px);
          transform: translateY(0px);
  text-shadow: 0 0 0;
}
.blog-tab:checked:nth-of-type(2) ~ .blog-tab__content:nth-of-type(2) {
  opacity: 1;
  -webkit-transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease, 0.8s -webkit-transform ease;
  position: relative;
  top: 0;
  z-index: 100;
  -webkit-transform: translateY(0px);
          transform: translateY(0px);
  text-shadow: 0 0 0;
}
.blog-tab:checked:nth-of-type(3) ~ .blog-tab__content:nth-of-type(3) {
  opacity: 1;
  -webkit-transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease, 0.8s -webkit-transform ease;
  position: relative;
  top: 0;
  z-index: 100;
  -webkit-transform: translateY(0px);
          transform: translateY(0px);
  text-shadow: 0 0 0;
}
.blog-tab:checked:nth-of-type(4) ~ .blog-tab__content:nth-of-type(4) {
  opacity: 1;
  -webkit-transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease, 0.8s -webkit-transform ease;
  position: relative;
  top: 0;
  z-index: 100;
  -webkit-transform: translateY(0px);
          transform: translateY(0px);
  text-shadow: 0 0 0;
}
.blog-tab:checked:nth-of-type(5) ~ .blog-tab__content:nth-of-type(5) {
  opacity: 1;
  -webkit-transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s -webkit-transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease;
  transition: 0.5s opacity ease-in, 0.8s transform ease, 0.8s -webkit-transform ease;
  position: relative;
  top: 0;
  z-index: 100;
  -webkit-transform: translateY(0px);
          transform: translateY(0px);
  text-shadow: 0 0 0;
}
.blog-tab:first-of-type:not(:last-of-type) + label {
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
.blog-tab:not(:first-of-type):not(:last-of-type) + label {
  border-radius: 0;
}
.blog-tab:last-of-type:not(:first-of-type) + label {
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
}
.blog-tab:checked + label {
  background-color: #fff;
  box-shadow: 0 -1px 0 #fff inset;
  cursor: default;
}
.blog-tab:checked + label:hover {
  box-shadow: 0 -1px 0 #fff inset;
  background-color: #fff;
}
.blog-tab + label {
  box-shadow: 0 -1px 0 #eee inset;
  border-radius: 6px 6px 0 0;
  cursor: pointer;
  display: block;
  text-decoration: none;
  color: #333;
  -webkit-box-flex: 3;
          flex-grow: 3;
  text-align: center;
  background-color: #f2f2f2;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  text-align: center;
  -webkit-transition: 0.3s background-color ease, 0.3s box-shadow ease;
  transition: 0.3s background-color ease, 0.3s box-shadow ease;
  height: 50px;
  box-sizing: border-box;
  padding: 15px;
}
.blog-tab + label:hover {
  background-color: #f9f9f9;
  box-shadow: 0 1px 0 #f4f4f4 inset;
}
.blog-tab__content {
  background-color: transparent;
  position: absolute;
  width: 100%;
  z-index: -1;
  opacity: 0;
  left: 0;
  -webkit-transform: translateY(-3px);
          transform: translateY(-3px);
  border-radius: 6px;
}

</style>