<template>
  <div class="article-setting" style="height:100%;">
    <div align="left" class="title-content">
      <!-- 뒤로가기 버튼 -->
      <v-btn text dense @click="backList" color="black" class="ml-0 pl-0 mb-5">
        <v-icon>mdi-arrow-left</v-icon>목록
      </v-btn>
    </div>
    <h4 class="text-justify font-weight-bold m-0" style="vertical-align:middle; width:100%;">
      <div style="height:10px;"></div>
      글쓰기
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
            v-model="article.openScope"

            :items="openItems"
            item-value="value" 
            item-text="name"
            label="공개"
            outlined
            dense
          ></v-select>
        </v-col>

        <span class="pt-1 mx-2">퍼가기 허용</span>
        <v-switch v-model="shareSwitch" class="mt-0"></v-switch>
        <span class="pt-1 mx-2">질문 요청</span>
        <v-switch v-model="questionSwitch" class="mt-0 mr-2"></v-switch>

        <v-col class="pt-0" cols="3" v-if="boardType == 'N'">
          <v-combobox
            v-model="catSelected"

            :items="categoryItems"
            item-value="categoryNo" 
            item-text="categoryName"
            label="카테고리를 선택해주세요."
            class="p-0"
          ></v-combobox>
        </v-col>
      </v-row>

      <v-row class="px-3" v-if="boardType == 'A'">
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

            :items="acategoryList"
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
            
            v-model="acategory.acategorySource"
          ></v-text-field>
          <v-text-field
            outlined
            clearable
            dense
            placeholder="문제 번호"

            v-model="acategory.acategoryNumber"
          ></v-text-field>

          <v-text-field
            outlined
            clearable
            dense
            placeholder="문제명을 입력해주세요"

            v-model="acategory.title"
          ></v-text-field>
          <v-text-field
            outlined
            clearable
            dense
            placeholder="문제링크를 입력해주세요"

            v-model="acategory.link"
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
            v-model="article.title"
            outlined
            clearable
            dense
            placeholder="제목을 입력해주세요"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row class="pl-3">
        <div>
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

      <ckeditor v-model="article.content" :config="editorConfig"></ckeditor>
      <div align="right" class="my-2 mx-0">
        <v-btn-toggle
          borderless
        >
          <v-btn  @click="writeArticle" value="작성" color="white" class="text-decoration-none">
            <span class="hidden-sm-and-down mr-1">작성</span>
            <v-icon small>mdi-pencil</v-icon>
          </v-btn>
        </v-btn-toggle>
      </div>
    </div>
  </div>

</template>

<script>
import axios from 'axios';
// import sax from 'sax';
// import { mdiStarCircle } from '@mdi/js';
export default {
  name: "BlogEditor",
  data() {
    return {
      article:{
        boardNo: '',
        title: '',
        content: '',
        openScope: '',
        thumbNail: '',
        articleType: '',
        sharable:'',
        reviewRequest: '',
        acategoryNo:'',
        categoryNo:'',
        reference: 0
      },
      boardNo : '',//watch용 데이터
      boardType:'',
      shareSwitch: true,
      questionSwitch: false,
      editorData: '',
      editorConfig: {
          extraPlugins: 'prism, youtube, image2',
          codeSnippet_theme: 'monokai_sublime',
          filebrowserImageUploadUrl: this.$store.getters.serverURL + 'file/articleimg',
          imageUploadUrl: this.$store.getters.serverURL + "file/articleimg",
      },
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

      catSelected: '',
      tagSelected: [],
      problemSelected: '',
      
      acategory:{ //사용자 정의 카테고리 생성 용
	      acategorySource: '',			/*NN 알고리즘 문제 사이트*/
        acategoryNumber: '',			/*NN 알고리즘 문제 번호*/
	      title: '',						/*NN 알고리즘 문제명*/
	    	link: ''						/*사이트 링크*/
      },

      arttag: [],
      tmptag:[],

      tagItems: [],
      categoryItems: [ ],

      acategoryList: [],
    };
  },
  watch:{
    siteSelected: function(){
      axios.get(`/article/acategorys?acategorySource=${this.siteSelected}`)
      .then( ({data}) => {
          this.acategoryList = data;
      })
      .catch( () => {

      })
    },
    boardNo: function(){
      this.article.boardNo = this.boardNo;

      for(let board of this.boardItems){
        if(board.boardNo == this.boardNo){
          this.boardType = board.boardType;
          break;
        }
      }
    }
  },
  methods:{
    parse(){
      var sax = require("sax");
      let strict = false; // set to false for html-mode
      let parser = sax.parser(strict, {lowercase : true});
      let thumbNailSelected = false;

      parser.onerror = (e) => {
        alert('!!!!!!!!!!!!!!!!!!!!!!!!!')
        // console.log(e);
      };

      parser.onopentag = (node) => {
        // console.log('onopentag==========================================');
        // console.dir(node);
        if(thumbNailSelected){
          return;
        }

        if(node.name == 'img'){
          this.article.thumbNail = node.attributes['src'].substring(node.attributes['src'].indexOf('img/'));

          thumbNailSelected = true;
        }
      };
      
      parser.write('<root>' + this.article.content + '</root>');
      
      console.log('11111111111111111111111111111111111111111111111111111111111111111')
    },
    backList(){
      this.$router.go(-1);
    },
    displayTitle(){
      alert("sssss");
    },
    writeArticle(){
      // this.article.articleType = "N"; 서버의 서비스단에서 보드타입에 맞는 articleType삽입
      this.article.sharable = this.shareSwitch? 1 : 0;
      this.article.reviewRequest = this.questionSwitch? 1 : 0;
      
      if(this.boardType == 'A'){
        if(this.siteSelected == '기타'){
          this.problemSelected = null;
          this.article.acategoryNo = 0;
        }else{
          if(!(this.problemSelected instanceof Object) || !this.problemSelected.acategoryNo ){
            alert("알고리즘 문제가 선택되지 않았습니다!");
            return;
          }
          this.article.acategoryNo = this.problemSelected.acategoryNo;
        }
      }else if(this.boardType == 'N'){
        if(!(this.catSelected instanceof Object) || !this.catSelected.categoryNo ){
          alert("카테고리가 선택되지 않았습니다!");
          return;
        }
        this.article.categoryNo = this.catSelected.categoryNo;
      } 

      //태그 설정
      this.arttag = [];
      this.tmptag = [];

      for(let tag of this.tagSelected){
        if(tag instanceof Object && tag.tagNo){
          this.arttag.push({tagNo : tag.tagNo});
        }else{
          this.tmptag.push({tmptagName : tag});
        }
      }

      console.log("==============")
      console.dir(this.arttag);
      console.dir(this.tmptag);
      this.parse();
      axios.post("/article/CUD",{
        article: this.article,
        arttag: this.arttag,
        tmpTag: this.tmptag,
        acategory: this.acategory
      }).then(()=>{
        alert("글작성이 완료되었습니다.");
        this.$router.push(`/bloghome/${this.userInfo.userId}/board/${this.boardNo}`);
      }).catch(()=>{
        alert("에러");
      })
    }
  },
  created(){
    if(!this.$store.getters.userInfo){
      alert('접근 권한이 없습니다');
      return;
    }

    axios.get(`/blog/myboards?blogNo=${this.$store.getters.userInfo.blogNo}`)
    .then(({data}) => {
      this.boardItems = data;
    })
    .catch(() => {
      alert("게시판 목록을 불러오는 데 실패했습니다.")
    })

    axios.get("/article/categorys")
    .then(({data}) => {
      this.categoryItems = data;
      console.dir(this.categoryItems)

      if(this.fromBoardNo){
        this.boardNo = parseInt(this.fromBoardNo);
        this.article.boardNo = this.boardNo;

        for(let board of this.boardItems){
          if(board.boardNo == this.boardNo){
            this.boardType = board.boardType;
            break;
          }
        }

      }
      this.article.openScope = 'public';
      })
    .catch(() => {
      alert("카테고리 목록을 불러오는 데 실패했습니다.")
    })

    axios.get("/tag/tags")
    .then(({data}) => {
      this.tagItems = data;
      console.dir(this.tagItems)
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
    if(this.$route.params.reference){
      this.article.reference = this.$route.params.reference;
      alert('공유글')
    }
    this.article.content = this.$route.params.editorData;

/////////////////////////////////////////////////////////////////////////////////////
    
  },
  components: {},
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    },
    fromBoardNo(){
      return this.$store.getters.fromBoardNo;
    }
  },
};
</script>

<style></style>
