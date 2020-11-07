<template>
  <!-- 전체적으로 디자인만 해둔 상태니깐 수정 부탁합니당!!!! -->
  <div class="container p-0" style="height:100%;" v-if="searchInfo.userNo==userInfo.userNo">
    <v-row class="pl-3" style="justify-content:start;">        
      <h4 class="text-justify font-weight-bold m-0" style="vertical-align:middle;">
        <div style="height:10px;"></div>
        게시판 관리
      </h4>
      
    </v-row>
    <br>
    <v-row class="pl-0" justify="start" style="width:100%;">
      <v-col cols="12" lg="5">
        <v-row class="text-center mb-3 mx-0">
          <v-btn small tile outlined color="grey" @click="create_board">
            <v-icon left>mdi-plus</v-icon>게시판 추가
          </v-btn>
          <!-- <v-btn class="mr-3" small tile outlined color="grey">
            <v-icon left>mdi-plus</v-icon>구분선 추가
          </v-btn> -->
          <!-- <v-btn class="ml-15" small tile outlined color="red" @click="delete_board">
            <v-icon left>mdi-minus</v-icon>삭제
          </v-btn> -->
          <v-spacer></v-spacer>
          <!-- 글삭제 버튼[수정시에만] -->
          <div>
            <!-- 누르면 경고창!! -->
            <v-dialog v-model="boardDelete" persistent max-width="290">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="error"
                  dense
                  small
                  outlined
                  tile
                  v-bind="attrs"
                  v-on="on"
                  @click="check_sub"
                >
                  <v-icon left>mdi-minus</v-icon>삭제
                </v-btn>
              </template>
              
              <v-card>
                <!-- 상위게시판인 경우 -->
                <div v-if="this.have_sub">
                  <v-card-title class="headline">게시판을 삭제할 수 없습니다.</v-card-title>
                  <v-card-text>하위 게시판을 옮긴 후 삭제해주세요.</v-card-text>
                </div>
                <!-- 하위게시판인 경우 -->
                <div v-if="!this.have_sub">
                  <v-card-title class="headline">게시판을 삭제하시겠습니까?</v-card-title>
                  <v-card-text>게시판 내 게시글이 모두 지워집니다.</v-card-text>
                </div>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="black darken-1" text @click="boardDelete = false">취소</v-btn>
                  <!-- 하위게시판일때만 뜨도록 해주시면 감사하겠습니다^.^ -->
                  <v-btn color="green darken-1" text @click="delete_board" v-if="!this.have_sub">동의</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </v-row>
        <!-- <div class="sortable" style="height:300px; border: 1px solid grey">
          <div class="sortable" style="border: 1px solid grey">SORTABLE</div>
              <div v-for="(board,index) in boards" :key="board.boardNo" style="text-align: left">
                  <p v-if="board.seqNo==0" @click="select_board(board,index)"><v-icon small class="mr-3">{{ articleIcon }}</v-icon><button type="button">{{board.boardName}}</button></p>
                  <p v-if="board.seqNo!=0" @click="select_board(board,index)"><button type="button" @click="select_board(board,index)">-{{board.boardName}}</button></p>
              </div>
        </div>-->
        <table class="sortable" style="height:300px; width:100%; border: 1px solid grey">
          <th class="sortable" style="border: 1px solid grey; height:30px; width:100%">SORTABLE</th>
            <template v-if="boards.length != 0">
              <tr v-for="(board,index) in boards" :key="board.boardNo" style="text-align:left">
                  <p v-if="board.seqNo==0" @click="select_board(board,index)"><v-icon small class="mr-3">{{ articleIcon }}</v-icon><button type="button">{{board.boardName}}</button></p>
                  <p v-if="board.seqNo!=0" @click="select_board(board,index)"><button type="button" @click="select_board(board,index)">-{{board.boardName}}</button></p>
              </tr>
            </template>
        </table>
      </v-col>

      <v-col cols="12" lg="7" class="px-10">
        <div style="height:45px;"></div>
        <v-row>
          <p class="mb-6 mr-3 " style="align-items:center; display:flex;">게시판명</p>
          <template v-if="this.boards.length!=0">
            <v-text-field
              v-model="boards[current_index].boardName"
              outlined
              clearable
              dense
              placeholder="게시판명을 입력해주세요"
            ></v-text-field>
          </template>
          <template v-if="this.boards.length==0">
            <v-text-field
              disabled
              outlined
              clearable
              dense
              placeholder="게시판명을 입력해주세요"
            ></v-text-field>
          </template>
        </v-row>

        <v-row>
          <p class="mb-6 mr-3 " style="align-items:center; display:flex;">게시판타입</p>
            <template v-if="this.boards.length!=0">
              <v-select
                :items="sortItems"
                v-model="boards[current_index].boardType"
                placeholder="게시판 타입을 선택해주세요"
                outlined
                dense
              ></v-select>
            </template>
            <template v-if="this.boards.length==0">
              <v-select
                :items="sortItems"
                disabled
                placeholder="게시판 타입을 선택해주세요"
                outlined
                dense
              ></v-select>
           </template>
        </v-row>

        <v-row>
          <p class="mb-6 mr-3 " style="align-items:center; display:flex;">게시판정렬</p>
          <v-btn class="mr-3" small tile outlined color="grey" @click="up_board">
            <v-icon left>mdi-chevron-up</v-icon>위
          </v-btn>
          <v-btn class="mr-3" small tile outlined color="grey" @click="down_board">
            <v-icon left>mdi-chevron-down</v-icon>아래
          </v-btn>
           <v-btn class="mr-3" small tile outlined color="grey" @click="left_board">
            <v-icon left>mdi-chevron-left</v-icon>메인
          </v-btn>
          <v-btn class="mr-3" small tile outlined color="grey" @click="right_board">
            <v-icon left>mdi-chevron-right</v-icon>서브
          </v-btn>
        </v-row>
      </v-col>
    </v-row>
    <div align="right" style="height:300px;">
      <v-btn-toggle
        borderless
      >
        <v-btn value="작성" color="white" class="text-decoration-none" @click="modify_blog">
          <span class="hidden-sm-and-down mr-1">수정</span>
          <v-icon small>mdi-checkbox-marked-outline</v-icon>
        </v-btn>
      </v-btn-toggle>
    </div>
  </div>
</template>

<script>
// blogmanager.vue 도 정리해주세요
import { 
  mdiCommentMultipleOutline, 
  } from '@mdi/js';
import axios from "axios"
export default {
  name: "BlogManager",
  data() {
    return {
      articleIcon: mdiCommentMultipleOutline,
      boardDelete: false,
      typeselected: "none",
      blogname: '',
      searchInfo:'',
      myInfo:'',
      updateInfo:'',
      boards: [],
      boardsNo: '',
      new_board: '',
      type_value: '',
      select_bd_num : '',
      beforeboard:{depth : -1},
      nowboard:{depth : -1},
      afterboard:{depth : -1},
      beforeboard_index:'',
      nowboard_index:'',
      afterboard_index:'',
      heads:{},
      temp_change_before : {},
      temp_change_now : {},
      temp_change_after : {},
      current_index : 0,
      temp_data: {},
      now_board_count: '',
      temp_boards:[],
      have_sub :false,
      nameRules: [
        v => !!v || '블로그명을 입력해주세요.',
        v => v.length <= 20 || '20자 이내로 입력해주세요.',
      ],
      sortItems: ['자유','알고리즘'],
    };
  },
  components: {},
  created() {
    axios.get('/blog/searchbyuserid?userId=' + encodeURI(this.$route.params.userId))
    .then((Response)=>{
      this.blogname = Response.data.blogName;
      this.$store.dispatch('Re_blogInfo',Response.data)
      this.searchInfo = Response.data;
      // axios.get('/blog/myboards?blogNo='+ Response.data.blogNo)
      // .then((Res)=>{
      //   console.log(Res);
      //   this.boards = Res.data;
      //   this.$store.dispatch('Re_boardInfo',Response.data)
      // }),
      axios.get('blog/gndist?blogNo=' + this.searchInfo.blogNo).then((num)=>{
        this.heads = num.data;

        axios.get('/blog/boardlist?blogNo=' + Response.data.blogNo)
        .then((Res)=>{
          // for(var i=0;i<Res.data.length;i++){
            
            // }
          this.boards = Res.data;
          this.$store.dispatch('Re_boardInfo',Res.data)
        })
      })
    })
  },
  computed: {
    userInfo() {
      return  this.$store.getters.userInfo;
    },
    boardInfo() {
      // console.log(this.$store.getters.boardInfo);
      return  this.$store.getters.boardInfo;
    },
  },
  methods : {
    check_sub(){
      this.temp_now_count = 0;
        for(let i=0;i<this.boards.length;i++){
          if(this.boards[i].groupNo==this.boards[this.current_index].groupNo){
            this.temp_now_count++;
          }
        }
      if(this.boards[this.current_index].seqNo == 0 && this.temp_now_count > 1){ // 메인이고 하위게시판이 있을 때
        this.boardDelete = true;
        this.have_sub = true;
        return;
      }else{
        this.have_sub = false;
      }
    },
    home() {
      this.$router.push('/main');
    },
    modify_blog() {
      for(let i=0;i<this.boards.length;i++){
        if(this.boards[i].boardType==="자유"){
          this.boards[i].boardType="N"
        }if(this.boards[i].boardType==="알고리즘"){
          this.boards[i].boardType="A"
        }
          if(this.boards[i].boardType==="N"){
            axios.put('/blog/myboard/CUD?blogNo=' + this.searchInfo.blogNo +"&boardName=" + this.boards[i].boardName + "&boardNo=" + this.boards[i].boardNo + "&boardType=N&depth=" + this.boards[i].depth + "&groupNo=" + this.boards[i].groupNo + "&seqNo=" + this.boards[i].seqNo)
            .then(()=>{
            }).catch(()=>{
            })
          }
          else if(this.boards[i].boardType==="A"){
            axios.put('/blog/myboard/CUD?blogNo=' + this.searchInfo.blogNo +"&boardName=" + this.boards[i].boardName + "&boardNo=" + this.boards[i].boardNo + "&boardType=A&depth=" + this.boards[i].depth + "&groupNo=" + this.boards[i].groupNo + "&seqNo=" + this.boards[i].seqNo)
            .then(()=>{
            }).catch(()=>{
            })
          }

      }
      alert("수정이 완료되었습니다.");
      this.$router.push(`/bloghome/${this.userInfo.userId}`);
    },
    create_board() {
      let temp_depth = 1;
      if(this.boards.length==0 || this.boards[0].boardsNo==null){
        temp_depth = 1;
      }else{
        temp_depth = this.boards[this.boards.length-1].depth + 1;
      }

      axios.post('/blog/myboard/CUD?boardName=' + encodeURI("게시판") + '&blogNo=' + this.searchInfo.blogNo + '&boardType=N&depth=' + temp_depth
        ).then(()=>{
          alert("게시판이 추가되었습니다.");
          axios.get('/blog/boardlist?blogNo=' + this.searchInfo.blogNo)
        .then((Res)=>{
          // console.log(Res.data+"3");
          this.boards = [];
          // console.log(this.boards +"4");
          for(let i=0;i<Res.data.length;i++){
            this.boards.splice(i,0,Res.data[i]); // boards에 데이터 갈아끼우기
          }
        })
        }).catch((error)=>{
          alert(error);
        })


      // if(this.type_value=='자유'){
      //   console.log("");
      //   axios.post('/blog/myboard/CUD?boardName=' + encodeURI(this.new_board) + '&blogNo=' + this.searchInfo.blogNo + '&boardType=N&depth=' + temp_depth
      //   ).then(()=>{
      //     alert("게시판이 추가되었습니다.");
      //     axios.get('/blog/boardlist?blogNo=' + this.searchInfo.blogNo)
      //   .then((Res)=>{
      //     console.log(Res.data+"3");
      //     this.boards = [];
      //     console.log(this.boards +"4");
      //     for(let i=0;i<Res.data.length;i++){
      //       this.boards.splice(i,0,Res.data[i]); // boards에 데이터 갈아끼우기
      //     }
      //   })
      //   }).catch((error)=>{
      //     alert(error);
      //   })
      // }else if(this.type_value=='알고리즘'){
      //   console.log("");
      //   axios.post('/blog/myboard/CUD?boardName=' + encodeURI(this.new_board) + '&blogNo=' + this.searchInfo.blogNo + '&boardType=A&depth=' + temp_depth
      //   ).then(()=>{
      //     // console.log(Response);
      //     alert("게시판이 추가되었습니다.2");
      //     axios.get('/blog/boardlist?blogNo=' + this.searchInfo.blogNo)
      //   .then((Res)=>{
      //     console.log(Res.data+"3");
      //     this.boards = [];
      //     console.log(this.boards +"4");
      //     for(let i=0;i<Res.data.length;i++){
      //       this.boards.splice(i,0,Res.data[i]); // boards에 데이터 갈아끼우기
      //     }
      //   })
      //   }).catch((error)=>{
      //     alert(error);
      //   })
      // }
      this.new_board = '';
      this.type_value = '';
    },
    select_board(data,index) {
      this.select_bd_num = data.boardNo;
      this.nowboard = data;
      this.current_index = index;
      if(index!=0){
        this.beforeboard = this.boards[index-1];
      }
      if(index==0){
        this.beforeboard = {depth : -1};
      }
      if(index!=(this.boards.length-1)){
        this.afterboard = this.boards[index+1];
      }
      if(index==(this.boards.length-1)){
        this.afterboard = {depth : -1};
      }
      // console.log("이전 : " + this.beforeboard.boardName);
      // console.log("현재 : " + this.nowboard.boardName);
      // console.log("다음 : " + this.afterboard.boardName);
      // console.log(this.current_index);
      this.new_board = this.boards[this.current_index].boardName
      this.type_value = this.boards[this.current_index].type_value
      // console.log("인덱스 : " + index);
      // if(index!=0){
      // console.log("전값 : " + this.boards[index-1].boardName);
      // }
      // console.log("현값 : " + this.boards[index].boardName);
      // if(index!=(this.boards.length-1)){
      // console.log("후값 : " + this.boards[index+1].boardName);
      // }
    },
    delete_board() {
        this.temp_now_count = 0;
        for(let i=0;i<this.boards.length;i++){
          if(this.boards[i].groupNo==this.boards[this.current_index].groupNo){
            this.temp_now_count++;
          }
        }
      if(this.boards[this.current_index].seqNo == 0 && this.temp_now_count > 1){ // 메인이고 하위게시판이 있을 때
        this.boardDelete = true;
        return;
      }

      axios.delete('/blog/myboard/CUD?boardNo='+ this.nowboard.boardNo + '&blogNo=' +  this.searchInfo.blogNo)
      .then(()=>{
        alert("삭제되었습니다.");
        axios.get('/blog/boardlist?blogNo=' + this.searchInfo.blogNo)
        .then((Res)=>{
          // console.log(Res.data+"3");
          this.boards = [];
          // console.log(this.boards +"4");
          for(let i=0;i<Res.data.length;i++){
            this.boards.splice(i,0,Res.data[i]); // boards에 데이터 갈아끼우기
          }
        })
      })
      this.boardDelete = false;
    },
    up_board() {
      if(this.nowboard.depth==-1){
        alert("선택된 게시판이 없습니다.");
        return;
      }
      if(this.current_index == 0){
        alert("위로 이동 할 수 없습니다.");
        return;
      }
      if(this.current_index == 1 && this.boards[this.current_index].seqNo!=0){
        alert("위로 이동 할 수 없습니다.");
        return;
      }
      if(this.boards[this.current_index-1].seqNo!=0 && this.boards[this.current_index].seqNo!=0){ 
        let tempseqNo = this.boards[this.current_index].seqNo

        let tempboardnow = this.boards[this.current_index];
        tempboardnow.seqNo = this.boards[this.current_index-1].seqNo;

        let tempboardbefore = this.boards[this.current_index-1];
        tempboardbefore.seqNo = tempseqNo;
        this.boards.splice(this.current_index-1,2,tempboardnow,tempboardbefore);
        this.current_index = this.current_index - 1;

        // console.log("졸병 2번이상")
      }else if(this.boards[this.current_index].seqNo==0){
        //이전 데이터의 그룹,졸병여부
        // let tempdepth = this.boards[this.current_index].depth

          let beforecount = 0;
          let nowcount = 0;
          let temparray = [];
              // alert("이후"+tempboardnow.depth);
          const before_depth = this.boards[this.current_index-1].depth;
          const now_depth = this.boards[this.current_index].depth;
          for(let i=0;i<this.boards.length;i++){ //현재보드값갯수
            if(this.boards[i].groupNo == this.boards[this.current_index].groupNo){
              nowcount++;
              let temp = this.boards[i]
              temp.depth = before_depth;
              temparray.push(temp);
            }
          }

          for(let i=0;i<this.boards.length;i++){ //현재보드값갯수
            if(this.boards[i].groupNo == this.boards[this.current_index-1].groupNo){
              beforecount++;
              let tempt = this.boards[i]
              tempt.depth = now_depth;
              temparray.push(tempt);
            }
          }


          for(let i= this.current_index-beforecount;i<this.current_index+nowcount;i++){
            this.boards.splice(i,1,temparray[i-(this.current_index-beforecount)]);
          }

          // this.current_index = this.current_index - 1;
        this.current_index = this.current_index-beforecount;

        // console.log("대빵");
      }else { 
        const before = this.boards[this.current_index-1];


        const now = {
          boardNo: this.boards[this.current_index].boardNo,
          blogNo: this.boards[this.current_index].blogNo,
          groupNo: this.boards[this.current_index-2].groupNo,
          seqNo: this.boards[this.current_index-2].seqNo + 1,
          depth: this.boards[this.current_index-2].depth,
          boardName: this.boards[this.current_index].boardName,
          boardType: this.boards[this.current_index].boardType,
          searchKey: this.boards[this.current_index].searchKey,
          searchValue: this.boards[this.current_index].searchValue,
          orderKey: this.boards[this.current_index].orderKey,
          orderValue: this.boards[this.current_index].orderValue,
          limitStart: this.boards[this.current_index].limitStart,
          limitEnd: this.boards[this.current_index].limitEnd,
          articleCount: this.boards[this.current_index].articleCount
        }
        
        
        this.boards.splice(this.current_index,1,before);
        this.boards.splice(this.current_index-1,1,now);
        this.current_index-=1;
        // console.log("쫄병 1번");
      }
      // this.$router.go();
    },
    down_board() {
      // console.log("들어옴" + this.heads.length);
      if(this.nowboard.depth==-1){
          alert("선택된 게시판이 없습니다.");
          return;
      }
      if(this.current_index == this.boards.length - 1){
        alert("아래로 이동 할 수 없습니다.");
        return;
      }
      let tempcnt = 0
      for(let i=0;i<this.boards.length;i++){ //현재보드값갯수
            if(this.boards[i].groupNo == this.boards[this.current_index].groupNo){
              tempcnt++;
            }
      }
      if(this.boards.length == this.current_index+tempcnt && this.boards[this.current_index].seqNo==0){
        alert("아래로 이동 할 수 없습니다.");
        return;
      }
      if(this.boards[this.current_index].seqNo!=0 && this.boards[this.current_index].depth!=this.boards[this.current_index+1].depth){//미완성
        const after = this.boards[this.current_index+1];
        if(this.boards.length!=this.current_index+2)
          if(this.boards[this.current_index+2].seqNo==1){
            for(let i = 0;i < this.boards.length;i++){
              if(this.boards[i].groupNo == after.groupNo && this.boards[i].seqNo>0){
                this.boards[i].seqNo++;
              }
            }
          }
        // console.log("쫄병마지막")
        const now = {
          boardNo: this.boards[this.current_index].boardNo,
          blogNo: this.boards[this.current_index].blogNo,
          groupNo: this.boards[this.current_index+1].groupNo,
          seqNo: 1,
          depth: this.boards[this.current_index+1].depth,
          boardName: this.boards[this.current_index].boardName,
          boardType: this.boards[this.current_index].boardType,
          searchKey: this.boards[this.current_index].searchKey,
          searchValue: this.boards[this.current_index].searchValue,
          orderKey: this.boards[this.current_index].orderKey,
          orderValue: this.boards[this.current_index].orderValue,
          limitStart: this.boards[this.current_index].limitStart,
          limitEnd: this.boards[this.current_index].limitEnd,
          articleCount: this.boards[this.current_index].articleCount
        }
        this.boards.splice(this.current_index,1,after);
        this.boards.splice(this.current_index+1,1,now);
        this.current_index+=1;
      }else if(this.boards[this.current_index].depth==this.boards[this.current_index+1].depth && this.boards[this.current_index].seqNo!=0){ 
        // console.log("쫄병마지막x");
        let tempseqNo = this.boards[this.current_index].seqNo

        let tempboardnow = this.boards[this.current_index];
        tempboardnow.seqNo = this.boards[this.current_index+1].seqNo;

        let tempboardafter = this.boards[this.current_index+1];
        tempboardafter.seqNo = tempseqNo;
        this.boards.splice(this.current_index,2,tempboardafter,tempboardnow);
        this.current_index = this.current_index + 1;
      }else {
          // console.log("대빵");
          let aftercount = 0;
          let nowcount = 0;
          let temparray = [];

          for(let i=0;i<this.boards.length;i++){ //현재보드값갯수
            if(this.boards[i].groupNo == this.boards[this.current_index].groupNo){
              nowcount++;
            }
          }

          const after_depth = this.boards[this.current_index+nowcount].depth;
          const now_depth = this.boards[this.current_index].depth;

          for(let i=0;i<this.boards.length;i++){ //이후보드값개수
              if(this.boards[i].groupNo == this.boards[this.current_index+nowcount].groupNo){
                aftercount++;
                let tempt = this.boards[i]
                tempt.depth = now_depth;
                temparray.push(tempt);
              }
          }
              
          for(let i=0;i<this.boards.length;i++){ //현재보드값갯수
            if(this.boards[i].groupNo == this.boards[this.current_index].groupNo){
              let temp = this.boards[i]
              temp.depth = after_depth;
              temparray.push(temp);
            }
          }

          for(let i= this.current_index;i<this.current_index+nowcount+aftercount;i++){
            this.boards.splice(i,1,temparray[i-(this.current_index)]);
          }

          // this.current_index = this.current_index - 1;
        this.current_index = this.current_index+aftercount;
      }
      
    },
    left_board() {
      if(this.boards[this.current_index].seqNo==0){
        alert("이동 할 수 없습니다.헤드");
        return ;
      }
      this.afterboard = this.boards[this.boards.length-1];
      let leftboard = this.nowboard;
      leftboard.depth = this.boards[this.boards.length-1].depth+1;
      leftboard.seqNo = 0;
      leftboard.groupNo = leftboard.boardNo;

      for(let i= this.current_index;i<this.boards.length;i++){
        this.boards.splice(i,1,this.boards[i+1]);
      }
      this.boards[this.boards.length-1] = leftboard;
      this.current_index = this.boards.length-1;
    },
    right_board() {
      if(this.boards[this.current_index].seqNo>0){
        alert("이동 할 수 없습니다.");
        return ;
      }
      if(this.current_index!=this.boards.length-1)
        if(this.boards[this.current_index].seqNo==0 && this.boards[this.current_index].groupNo==this.boards[this.current_index+1].groupNo){
          alert("서브게시판 존재 이동 불가");
          return ;
        }
      this.afterboard = this.boards[this.boards.length-1];
      let rightboard = this.boards[this.current_index];
      rightboard.depth = this.boards[this.current_index - 1].depth;
      rightboard.seqNo = this.boards[this.current_index - 1].seqNo+1;
      rightboard.groupNo = this.boards[this.current_index - 1].groupNo;
      this.boards.splice(this.current_index,1,rightboard);
    },
  }
};
</script>

<style>

</style>