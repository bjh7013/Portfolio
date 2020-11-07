<template>
  <div id="blogManager">
    <div class="container p-0" style="height:100%;" v-if="searchInfo.userNo==userInfo.userNo">
      <v-row style="justify-content:start;">        
        <h4 class="text-justify font-weight-bold m-0" style="vertical-align:middle;">
          <div style="height:10px;"></div>
          블로그 관리
        </h4>
        <v-btn-toggle
          borderless
        >
          <v-btn value="작성" color="white" class="text-decoration-none" @click="modify_blog">
            <span class="hidden-sm-and-down mr-1">수정</span>
            <v-icon small>mdi-checkbox-marked-outline</v-icon>
          </v-btn>
        </v-btn-toggle>
      </v-row>
      <br>

      <v-row class="pl-3" justify="start" style="width:100%;">
        <v-col cols="5" class="px-0">
          <v-text-field
            v-model="blogname"
            label="블로그명 입력"
            clearable
            :rules="nameRules"
            :counter="20"
            class="p-0 m-0"
            required
            maxlength="20"
          ></v-text-field>
        </v-col>
        <v-col align="left" class="grey--text font-weight-light">한글, 영문, 숫자 혼용가능 (한글 기준20자 이내)</v-col>
      </v-row>
      <br>
      <div align="right" class="my-2">
        <!-- 글삭제 버튼[수정시에만] -->
        <div class="my-2 mx-0">
          <!-- 누르면 경고창!! -->
          <v-dialog v-model="blogDelete" persistent max-width="290">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                color="error"
                text
                v-bind="attrs"
                v-on="on"
              >
                블로그 삭제
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline">블로그를 삭제하시겠습니까?</v-card-title>
              <v-card-text>블로그 내 게시글이 모두 지워집니다.</v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="black darken-1" text @click="blogDelete = false">취소</v-btn>
                <v-btn color="green darken-1" text @click="delete_blog">동의</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </div>
    <hr>
    </div>
    <div v-else>
      남의꺼 고칠 생각 하지마라 절로 가 임마
      <button type="button" @click="home">홈</button>
    </div>
  </div>
</template>
<script>
import axios from "axios"
export default {
  name: "BlogManager",
  data() {
    return {
      blogDelete: false,
      typeselected: "none",
      blogname: '',
      searchInfo:'',
      myInfo:'',
      updateInfo:'',
      boards: '',
      boardsNo: '',
      new_board: '',
      type_value: '',
      select_bd_num : '',
      nameRules: [
        v => !!v || '블로그명을 입력해주세요.',
        v => v.length <= 20 || '20자 이내로 입력해주세요.',
      ],
      sortItems: ['자유', '알고리즘'],
    };
  },
  components: {},
  created() {
    axios.get('/blog/searchbyuserid?userId=' + encodeURI(this.$route.params.userId))
    .then((Response)=>{
      // console.log(Response.data);
      this.blogname = Response.data.blogName;
      this.$store.dispatch('Re_blogInfo',Response.data)
      this.searchInfo = Response.data;
      axios.get('/blog/gndist?blogNo='+ Response.data.blogNo)
      .then((Res)=>{
        this.boardsNo = Res.data;
      })
      axios.get('/blog/boardlist?blogNo=' + Response.data.blogNo)
      .then((Res)=>{
        // console.log(Res);
        this.BoardItems = Res.data;
        this.$store.dispatch('Re_boardInfo',Res.data)
      })
    })
  },
  computed: {
    userInfo() {
      return  this.$store.getters.userInfo;
    },
    boardInfo() {
      return  this.$store.getters.boardInfo;
    }
  },
  methods : {
    home() {
      this.$router.push('/');
    },
    delete_blog() {
      axios.delete(`/blog/CUD?blogNo=`+this.searchInfo.blogNo)
      .then((Response)=>{
        // console.log(Response);
        alert("블로그가 삭제되었습니다.");
        axios.get('/user/mypage').then((Response)=>{
                this.$store.dispatch('Re_userInfo',Response.data)
            })
        this.$router.push("/main");
      })
    },
    modify_blog() {
      axios.put(`/blog/CUD?blogNo=`+this.userInfo.blogNo+`&blogName=`+ encodeURI(this.blogname)+`&userNo=`+this.userInfo.userNo)
      .then((Response)=>{
        alert("블로그가 수정되었습니다.");
        // console.log(Response);
              axios.get('/user/mypage').then((Response)=>{  //이건 유저 정보 갱신
                this.$store.dispatch('Re_userInfo',Response.data)
            }),
            axios.get('/blog/searchbyuserid?userId=' + encodeURI(this.$route.params.userId)) //이건 블로그 정보 갱신
            .then((Response)=>{
              // console.log(Response.data);
              this.$store.dispatch('Re_blogInfo',Response.data)
              this.searchInfo = Response.data;
            })
            this.$router.go();
      }).catch((error)=>{
        alert("에러");
        // console.log(error);
      })
    },
    create_board() {
      if(this.type_value=='자유'){
        // console.log("");
        axios.post('/blog/myboard/CUD?boardName=' + encodeURI(this.new_board) + '&blogNo=' + this.searchInfo.blogNo + '&boardType=N'
        ).then((Response)=>{
          // console.log(Response);
          alert("게시판이 추가되었습니다.");
          this.$router.go();
        }).catch((error)=>{
          alert(error);
        })
      }else if(this.type_value=='알고리즘'){
        // console.log("");
        axios.post('/blog/myboard/CUD?boardName=' + encodeURI(this.new_board) + '&blogNo=' + this.searchInfo.blogNo + '&boardType=A'
        ).then((Response)=>{
          // console.log(Response);
          alert("게시판이 추가되었습니다.");
          this.$router.go();
        }).catch((error)=>{
          alert(error);
        })
      }
    },
    select_board(number) {
      this.select_bd_num = number;
      // console.log(number);
    },
    delete_board() {
      axios.delete('/blog/myboard/CUD?boardNo='+ this.select_bd_num + '&blogNo=' +  this.searchInfo.blogNo)
      .then(()=>{
        alert("삭제되었습니다.");
        this.$router.go();
      })
    }
  }
};
</script>

<style></style>
