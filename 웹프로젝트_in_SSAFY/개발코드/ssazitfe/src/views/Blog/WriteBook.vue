<template>
  <div>
    <div class="article-setting" style="height:100%; width:100%;">
      <div align="left" class="title-content">
        <!-- 뒤로가기 버튼 -->
        <v-btn text dense @click="backList" color="black" class="ml-0 pl-0 mb-5">
          <v-icon>mdi-arrow-left</v-icon>목록
        </v-btn>
      </div>
      <v-row class="pl-3 mb-6" style="justify-content:start; width:100%;">        
        <h4 class="text-justify font-weight-bold m-0" style="vertical-align:middle;">
          책작성
        </h4>
      </v-row>

      <div class="article-setting-base" style="width:100%;">
        <!-- 책 기본 설정 -->
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
                <template v-if="infoDialog" style="overflow-y:hidden;">
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
        <v-row class="px-3">
          <!-- 공개범위 -->
          <div class="mr-3 ml-1">
            <div style="height:10px;"></div>
            <span class="">공개</span>
          </div>
          <v-col class="d-flex p-0 mr-3" cols="2">
            <v-select
              v-model="book.openScope"
              :items="openItems"
              item-value="value" 
              item-text="name"
              label="공개"
              outlined
              class="py-1"
              dense
            ></v-select>
          </v-col>
          <!-- 카테고리 -->
          <div class="mr-3 ml-1">
            <div style="height:10px;"></div>
            <span class="">카테고리</span>
          </div>
          <v-col class="d-flex p-0 mr-3" cols="2">
            <v-combobox
              v-model="catSelected"
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

        <!-- 제목 -->
        <v-row class="px-3 mr-15">
          <v-row class="px-3">
            <div>
              <div style="height:10px;"></div>
              <span class="mr-3 ml-2">제목</span>
            </div>
            <v-col class="py-0 pl-0">
              <v-text-field
                v-model="book.bookTitle"
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

        <!-- 표지 설정 -->
        <div :class="{ hideArea : isImg }">
          <p align="left" class="m-0 font-weight-bold">표지 설정</p>
          <!-- 앞 표지 -->
          <v-row class="px-5 mr-16">
            <!-- 표지 이름 -->
            <v-col cols="1" align="left" class="p-0">
              <div style="height:13px;"></div>
              <p>앞 표지</p>
            </v-col>
            <!-- 이미지 업로드 -->
            <v-col class="px-2 pt-0" align="left" style="width:80%; height:44px; border: 1px solid grey; border-radius: 5px;">
              <div style="height:6px;"></div>
              <p class="p-0 m-0" style="color:grey;">
                <input type="file" @change="frontFileChange" ref="front_image_upload"/>
              </p>
            </v-col>
            <!-- 업로드 이미지 제거 -->
            <div>
              <div style="height:5px;"></div>
              <v-btn icon value="삭제" color="white" class="text-decoration-none" @click="frontFileReset">
                <v-icon color="grey">mdi-close</v-icon>
              </v-btn>
            </div>
          </v-row>
          <!-- 등 표지 -->
          <v-row class="px-5 mr-16">
            <!-- 표지 이름 -->
            <v-col cols="1" align="left" class="p-0">
              <div style="height:13px;"></div>
              <p>등 표지</p>
            </v-col>
            <!-- 이미지 업로드 -->
            <v-col class="px-2 pt-0" align="left" style="width:80%; height:44px; border: 1px solid grey; border-radius: 5px;">
              <div style="height:6px;"></div>
              <p class="p-0 m-0" style="color:grey;"><input type="file" @change="middleFileChange" ref="middle_image_upload"/></p>
            </v-col>
            <!-- 업로드 이미지 제거 -->
            <div>
              <div style="height:5px;"></div>
              <v-btn icon value="삭제" color="white" class="text-decoration-none" @click="middleFileReset">
                <v-icon color="grey">mdi-close</v-icon>
              </v-btn>
            </div>
          </v-row>
          <!-- 뒷 표지 -->
          <v-row class="px-5 mr-16">
            <!-- 표지 이름 -->
            <v-col cols="1" align="left" class="p-0">
              <div style="height:13px;"></div>
              <p>뒷 표지</p>
            </v-col>
            <!-- 이미지 업로드 -->
            <v-col class="px-2 pt-0" align="left" style="width:80%; height:44px; border: 1px solid grey; border-radius: 5px;">
              <div style="height:6px;"></div>
              <p class="p-0 m-0" style="color:grey;"><input type="file" @change="backFileChange" ref="back_image_upload"/></p>
            </v-col>
            <!-- 업로드 이미지 제거 -->
            <div>
              <div style="height:5px;"></div>
              <v-btn icon value="삭제" color="white" class="text-decoration-none" @click="backFileReset">
                <v-icon color="grey">mdi-close</v-icon>
              </v-btn>
            </div>
          </v-row>
          <br>

          <!-- 미리보기 -->
          <p align="left" class="m-0 font-weight-bold pb-3">표지 미리보기</p>
          <v-row class="px-4 mb-5" >
            <!-- 뒤 -->
            <v-col class="p-0" cols="5" style="border: 1px solid grey; height:545px; overflow:hidden;">
              <v-img :src="coverBack" height="545px" ></v-img>
            </v-col>
            <!-- 등 -->
            <v-col class="p-0" cols="1" style="border: 1px solid grey; height:545px; overflow:hidden;">
              <v-img :src="coverMiddle" height="545px"></v-img>
            </v-col>
            <!-- 앞 -->
            <v-col class="p-0" cols="5" style="border: 1px solid grey; height:545px; overflow:hidden;">
              <v-img :src="coverFront" height="545px"></v-img>
            </v-col>
            <!-- 표지설정 <=> 내용설정 토글 버튼 -->
            <!-- <div> 
              <div style="height:250px;"></div>
              <v-btn-toggle borderless cols="1">
                <v-btn icon color="white" class="text-decoration-none" @click="toggle">
                  <v-icon x-large>mdi-chevron-right</v-icon>
                </v-btn>
              </v-btn-toggle>
            </div> -->
          </v-row>
        <hr>
        </div>

        <!-- 책 내용 영역 -->
        <div :class="{ hideArea : isContent }">
          <div class="mt-5 mb-3" style="text-align:left;">
            <b>내용 설정</b>
          </div>
          <v-row class="pl-0 mt-0 mr-0 ml-0" justify="start" style="width:91%;">
            <!-- 표지설정 <=> 내용설정 토글 버튼 -->
            <!-- <div> 
              <div style="height:250px;"></div>
              <v-btn-toggle borderless cols="1" >
                <v-btn icon color="white" class="text-decoration-none" @click="toggle">
                  <v-icon x-large>mdi-chevron-left</v-icon>
                </v-btn>
              </v-btn-toggle>
            </div> -->
            <!-- 내용 설정 -->
            <v-col class="blog-tab-wrap px-0">
              <!-- 목차 / 내용 / 출처 탭 -->
              <input type="radio" id="blog-tab1" name="blog-tabGroup1" class="blog-tab" checked>
              <label for="blog-tab1">목차</label>
              <input type="radio" id="blog-tab2" name="blog-tabGroup1" class="blog-tab">
              <label for="blog-tab2">내용</label>
              <input type="radio" id="blog-tab3" name="blog-tabGroup1" class="blog-tab">
              <label for="blog-tab3">출처</label>
              <!-- 목차 / 내용 / 출처 에디터 -->
              <div class="blog-tab__content">
                <ckeditor :config="editorConfig" v-model="book.bookIndex"></ckeditor>
              </div>
              <div class="blog-tab__content">
                <ckeditor :config="editorConfigContent" v-model="book.bookContent"></ckeditor>
              </div>
              <div class="blog-tab__content">
                <ckeditor :config="editorConfig" v-model="book.bookReference"></ckeditor>
              </div>
            </v-col>
            <!-- 게시글 내용 가져오기 -->
            <v-col cols="12" lg="3" class="py-0 pr-0 mt-5">
              <div style="height:460px; overflow:scroll; border: 1px solid grey">
                <div style="border-bottom: 1px solid grey">전체글 목록</div>
                <div class="list-group-item" v-for="articleItem in articleItems" :key="articleItem.article.articleNo">
                  <a style="color:black;" @click="addContent(articleItem.article)">{{articleItem.article.title}}</a>
                </div>
              </div>
            </v-col>
            
          </v-row>
          <hr>
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
                  <book-preview :editorData="target" :bookTitle="book.bookTitle" :bookIndex="book.bookIndex" :bookReference="book.bookReference"></book-preview>
                </template>
                <template v-else>
                </template>
              </v-card-text>
             
            </v-card>
          </v-dialog>
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
        <!-- 작성 버튼 -->
        <v-row v-if="isImg">
          <v-spacer></v-spacer>
            <v-btn-toggle borderless cols="1" >
              <v-btn value="이전" color="white" class="text-decoration-none" @click="toggle">
                <v-icon small>mdi-chevron-left</v-icon>
                <span class="hidden-sm-and-down mr-1">이전</span>
              </v-btn>
            </v-btn-toggle>
            <v-btn-toggle borderless cols="1" >
              <v-btn value="작성" color="white" class="text-decoration-none" @click="writeBook">
                <span class="hidden-sm-and-down mr-1">작성</span>
                <v-icon small>mdi-pencil</v-icon>
              </v-btn>
            </v-btn-toggle>
          <v-col cols="1"></v-col>
        </v-row>
        <!-- ~작성 버튼 -->
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
      destroy:false,
      book:{
        writer: '',
        bookTitle: '',
        bookIndex: '<h2><strong>목차</strong></h2><ol><li><h3>Chapter1</h3></li></ol><p>&nbsp;</p>',
        bookContent: '',
        bookReference: '<p><span style="font-size:16px"><strong>출처</strong></span></p><ul><li>(번호. 아이디, 게시글작성날짜, 게시글명/챕터명)</li></ul><p>&nbsp;</p>',
        openScope: '',
        version: '',
        categoryNo: '',
      },
      target:'',
      dialog: false,
      infoDialog: false,
      coverFront: this.$store.getters.serverURL + 'resource/book_img/default_front.JPEG',
      coverMiddle: this.$store.getters.serverURL + 'resource/book_img/default_middle.JPG',
      coverBack: this.$store.getters.serverURL + 'resource/book_img/default_back.JPG',
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
      catSelected: '',
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
      }
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
  mounted(){
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
    
    this.loadArticles();

    axios.get(`/tag/tags`)
    .then((Response) => {
      this.tagItems = Response.data;
      // console.dir(this.tagItems);
    })
    .catch(() => {
      alert("태그 목록을 불러오는 데 실패했습니다." )
    })
  },
  methods: {
    loadArticles(){
      setTimeout(() => {
        if(this.userInfo){
          axios.get(`/article/articles?userNo=` + encodeURI(this.userInfo.userNo))
          .then((Response)=>{
            // console.log(Response.data);
            this.articleItems = Response.data;
          })
          .catch(()=>{
            alert("게시글 목록을 불러오는 데 실패했습니다.")
          })
        }else{
          this.loadArticles();
        }
      }, 200);
    },
    openPreview(){
      this.parse();
      this.dialog = true;
    },
    openInfo(){
      this.parse();
      this.infoDialog = true;
    },
    backList(){
      this.$router.go(-1);
    },
    parse(){
      this.target = '';
      var sax = require("sax");
      let strict = false; // set to false for html-mode
      let parser = sax.parser(strict, {lowercase : true});
      let parsed = '';
      let isSelfClosing = false;
      let isPageBreak = false;

      parser.onerror = (e) => {
        // an error happened.
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
                      + '</div></div></div>'
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
                      + '</div></div></div>'
          this.target += parsed;
          parsed = '';
        }

        
      };
      
      parser.write('<root>' + this.book.bookContent + '</root>');      
    },
    frontFileChange(e){
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) { 
        return;
      } 
      this.front_img_file = files[0];
      this.createFrontImage(files[0]);
    },
    createFrontImage(file) { 
      var reader = new FileReader();
      var vm = this;
      reader.onload = (e) => { 
        vm.coverFront = e.target.result;
      } 
      reader.readAsDataURL(file);
    },
    middleFileChange(e){
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) { 
        return;
      } 
      this.middle_img_file = files[0];
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
    writeBook(){

      // 데이터 유효성 검사 ~
      if(this.book.openScope.trim() == ''){
        alert("공개범위를 선택해주세요!");
        return;
      }
      if(this.catSelected == ''){
        alert("카테고리를 선택해주세요!");
        return;
      }
      if(this.book.bookTitle.trim() == ''){
        alert("책 제목을 입력해주세요!");
        return;
      }
      if(this.book.bookTitle.length > 20){
        alert("책 제목은 20자를 넘을 수 없습니다!");
        return;
      }
      if(this.book.bookContent.trim() == ''){
        alert("책 내용을 입력해주세요!");
        return;
      }
      // ~ 데이터 유효성 검사

      // 사전작업 ~
      // console.log("-----사전작업-----");
        // 책
      // console.log("책");
      this.book.categoryNo = this.catSelected.categoryNo;
      // console.log(this.book);

        // 태그
      // console.log("태그");
      for(let tag of this.tagSelected){
        if(tag instanceof Object && tag.tagNo){
          this.booktag.push({tagNo : tag.tagNo});
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

      this.parse();
      this.book.bookContent = this.target;
      
      axios.post(`/file/create_book_cover`, form
      ).then((Response)=>{
        // console.log(Response.data);
        // 책 전송
        axios.post(`/book/CUD`, {
          book: this.book,
          booktag: this.booktag,
          tmptag: this.tmptag,
          fileinfo: Response.data
        }).then(()=>{
          alert("책 작성이 완료되었습니다.");
          this.$router.go(-1);
        }).catch(()=>{
          alert("책 작성을 실패했습니다.");
          this.BookTagCat = [];
          this.booktag = [];
          this.tmptag = [];
        })
      }).catch(()=>{
        alert("이미지 업로드에 실패했습니다.");
        this.BookTagCat = [];
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
      this.book.bookContent += data.content + '<div style="page-break-after:always" class="cke_pagebreak"><span style="display:none">&nbsp;</span></div>';
    },
    frontFileReset(){
      this.coverFront = this.$store.getters.serverURL + 'resource/book_img/default_front.JPEG';
      this.front_img_file = '';
      this.$refs.front_image_upload.value=null;
    },
    middleFileReset(){
      this.coverMiddle = this.$store.getters.serverURL + 'resource/book_img/default_middle.JPG';
      this.middle_img_file = '';
      this.$refs.middle_image_upload.value=null;
    },
    backFileReset(){
      this.coverBack = this.$store.getters.serverURL + 'resource/book_img/default_back.JPG';
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
</style>