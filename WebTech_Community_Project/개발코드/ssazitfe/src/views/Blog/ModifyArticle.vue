<template>
  <div id="article-create">
    <div class="article-setting" style="height:100%;">
      <div align="left" class="title-content">
        <!-- 뒤로가기 버튼 -->
        <v-btn text dense @click="backList" color="black" class="ml-0 pl-0 mb-5">
          <v-icon>mdi-arrow-left</v-icon>돌아가기
        </v-btn>
      </div>
   
      <h4 class="text-justify font-weight-bold m-0" style="vertical-align:middle;">
        <div style="height:10px;"></div>
        글수정
      </h4>
      <br>

      <div class="article-setting-base" style="width:100%;">
        <v-row class="pl-3">
          <v-col class="d-flex p-0 mr-2" cols="2">
            <v-select
              v-model="boardNo"
              :items="boardItems"
              item-value="boardNo"
              item-text="boardName"
              label="게시판"
              outlined
              dense
            ></v-select>
          </v-col>
          <v-col class="d-flex p-0 mr-2" cols="2">
            <v-select
              v-model="articleTagCat.article.openScope"

              :items="openItems"
              item-value="value"
              item-text="name"
              label="공개"
              outlined
              dense
            ></v-select>
          </v-col>

          <span class="pt-1 mx-2">퍼가기 허용</span>
          <v-switch v-model="articleTagCat.article.sharable" class="mt-0"></v-switch>
          <span class="pt-1 mx-2">질문 요청</span>
          <v-switch v-model="articleTagCat.article.reviewRequest" class="mt-0 mr-2"></v-switch>

          <v-col class="pt-0" v-if="articleTagCat.article.articleType == 'N'">
            <v-combobox
              v-model="catSelected"

              :items="categoryItems"
              item-value="categoryNo" 

              item-text="categoryName"
              label="카테고리를 선택해주세요. (입력 후 enter)"
              class="p-0"
            ></v-combobox>
          </v-col>
        </v-row>
          
        <v-row class="px-3" v-if="articleTagCat.article.articleType == 'A'">
          <v-col class="d-flex p-0 mr-2" cols="2">
            <v-select
              v-model="siteSelected"

              :items="siteList"
              label="사이트"
              outlined
              dense
            ></v-select>
          </v-col>

          <v-col v-if="siteSelected != '기타'" class="p-0">
            <v-combobox
              v-model="problemSelected"

              :items="acategorys"
              item-value="acategoryNo"

              item-text="title"
              label="문제를 선택해주세요"
              class="p-0"
            >
              <template slot='selection' slot-scope='{ item }'>
                {{ item.acategoryNumber }}번 - {{ item.title }}
              </template>
              <template slot='item' slot-scope='{ item }'>
                {{ item.acategoryNumber }}번 - {{ item.title }}
              </template>
            </v-combobox>
          </v-col>
          <v-col class="p-0" v-else>
            <v-text-field 
              outlined
              clearable
              dense
              placeholder="문제 사이트"
              
              v-model="articleTagCat.acategory.acategorySource"
            ></v-text-field>
            <v-text-field
              outlined
              clearable
              dense
              placeholder="문제 번호"

              v-model="articleTagCat.acategory.acategoryNumber"
            ></v-text-field>

            <v-text-field
              outlined
              clearable
              dense
              placeholder="문제명을 입력해주세요"

              v-model="articleTagCat.acategory.title"
            ></v-text-field>
            <v-text-field
              outlined
              clearable
              dense
              placeholder="문제링크를 입력해주세요"

              v-model="articleTagCat.acategory.link"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row class="px-3">
          <div>
            <div style="height:10px;"></div>
            <span class="mr-3">제목</span>
          </div>
          <v-col class="p-0">
            <v-text-field
              v-model="articleTagCat.article.title"
              outlined
              clearable
              dense
              placeholder="제목을 입력해주세요"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="pl-3">
          <div class="mb-3">
            <div style="height:3px;"></div>
            <span class="mr-3">태그 설정</span>
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
        </v-row>

        <ckeditor ref="editor" v-model="articleTagCat.article.content" :config="editorConfig"></ckeditor>
        <!-- 글삭제 버튼[수정시에만] -->
        <div align="right" class="my-2 mx-0">
          <v-btn-toggle
            borderless
          >
            <v-btn  @click="modify" value="수정" color="white" class="text-decoration-none">
              <span class="hidden-sm-and-down mr-1">수정</span>
              <v-icon small>mdi-pencil</v-icon>
            </v-btn>
          </v-btn-toggle>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import axios from 'axios';
export default {
  name: "BlogEditor",
  data() {
    return {
      articleTagCat:{
        article: {}
      },
      editorData:null,
      boardNo:'',
      boardType:'',
      shareSwitch: true,
      questionSwitch: false,
      
      // 이전 페이지의 게시판으로 초기값 설정
      boardItems: [
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
      siteList: [],
      siteSelected: "none",
      problemSelected: '',
      catSelected: '',
      tagSelected: [],

      acategory:{
	      acategorySource: '',			/*NN 알고리즘 문제 사이트*/
        acategoryNumber: '',			/*NN 알고리즘 문제 번호*/
	      title: '',						/*NN 알고리즘 문제명*/
	    	link: ''						/*사이트 링크*/
      },

      tagItems: [],
      categoryItems: [ ],
      acategorys: [],

      editorConfig: {
        // The configuration of the editor.
        extraPlugins: 'lineutils, widget, codesnippet, prism, image2, youtube',
        codeSnippet_theme: 'monokai_sublime',
          filebrowserImageUploadUrl: this.$store.getters.serverURL + 'file/articleimg',
          imageUploadUrl: this.$store.getters.serverURL + "file/articleimg",
      },
     
    };
  },
  watch:{
    siteSelected: function(){
      axios.get(`/article/acategorys?acategorySource=${this.siteSelected}`)
      .then( ({data}) => {
          this.acategorys = data;
          for(let acategory of this.acategorys){
            if(this.articleTagCat.article.acategoryNo == acategory.acategoryNo){
              this.problemSelected = acategory;
            }
          }
      })
      .catch( () => {
          })
    },
    boardNo: function(){
      this.articleTagCat.article.boardNo = this.boardNo;

      for(let board of this.boardItems){
        if(board.boardNo == this.boardNo){
          this.boardType = board.boardType;
          break;
        }
      }
    }
  },
  methods:{
    backList(){
      this.$router.go(-1);
    },
    modify(){
      if(this.boardType == 'A'){
        this.articleTagCat.article.categoryNo = 0;

        if(this.siteSelected == '기타'){
          this.problemSelected = null;
          this.articleTagCat.article.acategoryNo = 0;
        }else{
          if(!(this.problemSelected instanceof Object) || !this.problemSelected.acategoryNo ){
            alert("알고리즘 문제가 선택되지 않았습니다!");
            return;
          }
          this.articleTagCat.article.acategoryNo = this.problemSelected.acategoryNo;
        }
      }else if(this.boardType == 'N'){
        this.articleTagCat.article.acategoryNo = 0;
        this.articleTagCat.acategory = null;
        
        if(this.articleTagCat.article.categoryNo == 0){
          alert("카테고리가 선택되지 않았습니다!");
          return;
        }
        this.articleTagCat.article.categoryNo = this.catSelected.categoryNo
      }  


      //태그 설정
      console.dir(this.tagSelected);
      this.articleTagCat.arttag = [];
      this.articleTagCat.tmpTag = [];
      for(let tag of this.tagSelected){
        if(tag instanceof Object && tag.tagNo){
          this.articleTagCat.arttag.push({tagNo : tag.tagNo}); //기정의된 정식태그
        } else if(tag instanceof Object && tag.tmptagNo){
          this.articleTagCat.tmpTag.push({tmptagNo : tag.tmptagNo}); //기정의된 임시태그
        } else{
          this.articleTagCat.tmpTag.push({tmptagName : tag}); //사용자 생성 태그
        }
      }

      
      console.log("==============")
      this.parse();

      axios.put("/article/CUD",{
        article : this.articleTagCat.article,
        arttag : this.articleTagCat.arttag,
        acategory: this.articleTagCat.acategory,
        tmpTag : this.articleTagCat.tmpTag
      })
      .then(()=>{
        alert('수정 성공');
        this.$router.go(-1);
      })
      .catch(() => {
        
        alert('수정 실패')
      })
     /*
      */
     },

    // loadIframe(){
    //   setTimeout(() =>{
    //     if(document.getElementsByClassName('cke_wysiwyg_frame')[0]){
          
    //       document.getElementsByClassName('cke_wysiwyg_frame')[0].contentDocument.body.innerHTML = this.articleTagCat.article.content;
    //     }else{
    //       alert('not yet');
    //       setTimeout(this.loadIframe, 300);
    //     }
    //   }, 300);
    // },
     parse(){
      var sax = require("sax");
      let strict = false; // set to false for html-mode
      let parser = sax.parser(strict, {lowercase : true});
      let thumbNailSelected = false;

      parser.onerror = (e) => {
        alert('!!!!!!!!!!!!!!!!!!!!!!!!!')
        console.log(e);
      };

      parser.onopentag = (node) => {
        console.log('onopentag==========================================');
        console.dir(node);
        if(thumbNailSelected){
          return;
        }

        if(node.name == 'img'){
          this.articleTagCat.article.thumbNail = node.attributes['src'].substring(node.attributes['src'].indexOf('img/'));

          thumbNailSelected = true;
        }
      };
      
      parser.write('<root>' + this.articleTagCat.article.content + '</root>');
      
      // console.log('11111111111111111111111111111111111111111111111111111111111111111')
     }
  }
  ,
  mounted(){
    axios.get(`/blog/myboards?blogNo=${this.$store.getters.blogInfo.blogNo}`)
    .then(({data}) => {
        this.boardItems = data;
    })
    .catch(() => {
        alert("게시판 목록을 불러오는 데 실패했습니다.")
    })

    axios.get("/article/categorys")
    .then(({data}) => {
        this.categoryItems = data;
      // console.dir(this.categoryItems)
    })
    .catch(() => {
        alert("카테고리 목록을 불러오는 데 실패했습니다.")
    })

    axios.get("/tag/tags")
    .then(({data}) => {
        this.tagItems = data;
      // console.dir(this.tagItems)
    })
    .catch(() => {
        alert("태그 목록을 불러오는 데 실패했습니다.")
    })

    axios.get("/article/acategorys/site")
    .then(({data}) => {
      this.siteList = data;
      this.siteList.push('기타');
    })
    .catch(() =>{
      alert("사이트 목록을 가져오는 데 실패했습니다.")
    })
    

     axios.get(`/article/article/${this.$route.params.articleNo}`)
    .then( ({data}) => {
                
        this.articleTagCat = data;
        // console.log("=================================")
        // console.dir(this.articleTagCat);
        if(!this.$store.getters.userInfo || this.$store.getters.userInfo.userNo != this.articleTagCat.article.userNo){
            alert('접근 권한이 없습니다.');
            this.$router.go(-1);
        }
        
        this.tagSelected = this.articleTagCat.arttag;
        
        // console.dir(this.articleTagCat.tmpTag);
        for(let tmpTag of this.articleTagCat.tmpTag){
          this.tagSelected.push({
            tmptagNo : tmpTag.tmptagNo,
            articleNo : tmpTag.articleNo,
            bookNo : tmpTag.bookNo,
            tagName : tmpTag.tmptagName
          });
        }
        
        this.siteSelected = this.articleTagCat.article.acategorySource;
        
      
        this.boardNo = this.articleTagCat.article.boardNo;
        this.boardType = this.articleTagCat.article.articleType;
        
        this.catSelected = {categoryNo : this.articleTagCat.article.categoryNo, categoryName : this.articleTagCat.article.categoryName};
        // console.log('87sdf9a0hasdkfhasdjfashdl')
      
        // console.log(document.getElementsByClassName('cke_wysiwyg_frame')[0].contentDocument.body.innerHTML);
     
        // console.dir(window.CKEDITOR.dom.document)
        // let test = window.CKEDITOR.dom.document("document");
        // this.loadIframe();
        

    })
    .catch((err) => {
        // console.dir(err);
        alert("글 상세 정보를 불러오는 데 실패했습니다.") 
    })
  },
  components: {}
};
</script>
<style></style>
