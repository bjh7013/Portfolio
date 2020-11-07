<template>
  <div class="blocklist container col order">
    <div class="row" v-if="userInfo!=null && userInfo.userNo>-1">
      <!-- data가 들어갈 부분 -->
      <div class="col" id="main">
        <v-card :elevation="0" class="col-11" id="blocklist_card">
          <v-container fluid>
            <v-row align="center">
              <h4>차단목록</h4>
              <v-col md="4" class="ml-auto">
                <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="select_blocklist"></v-text-field>
              </v-col>
              <b-button @click="select_blocklist">검색</b-button>
            </v-row>
          </v-container>
          <!-- 차단목록 -->
          <b-tabs content-class="mt-3">
            <b-tab title="차단목록" active>
              <v-card :elevation="0">
                  <v-list-item>
                    <v-row>
                      <v-col id="card_detail" v-for="(user,index) in blocklist" :key="user.number" cols="6">
                          <v-col id="card_detail2" v-if="index%2==0">
                            <div>
                              <v-card :elevation="1">
                                <v-list-item-content>         
                                  <v-list-item-title class="my-1"><v-avatar><img :src="userImageblock[index]"></v-avatar>{{user.nickName}}({{user.userId}})</v-list-item-title>
                                </v-list-item-content>
                                <v-btn text @click="block_delete(user.blocking)">차단해제</v-btn>
                              </v-card>
                            </div>
                          </v-col>
                          <v-col id="card_detail2" v-if="index%2==1">
                            <div>
                              <v-card :elevation="1">
                                <v-list-item-content>         
                                  <v-list-item-title class="my-1"><v-avatar><img :src="userImageblock[index]"></v-avatar> {{user.nickName}}({{user.userId}})</v-list-item-title>
                                </v-list-item-content>
                                <v-btn text @click="block_delete(user.blocking)">차단해제</v-btn>
                              </v-card>
                            </div>
                          </v-col>
                      </v-col>
                    </v-row>
                    
                  </v-list-item>
                </v-card>
            </b-tab>
          </b-tabs>


          <!-- 페이지네이션 -->
          <!-- <v-col cols="12">
            <v-container id="page" class="max-width">
              <v-pagination
                v-model="page"
                :circle="circle"
                :disabled="disabled"
                :length="10"
                :next-icon="nextIcon"
                :prev-icon="prevIcon"
                :page="page"
                :total-visible="totalVisible"
              ></v-pagination>
            </v-container>
          </v-col> -->
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
import axios from 'axios'
export default {
  name: "blocklist",
  components: {},
  data() {
    return {
      rows: 100,
      blocklist : [],
      search : '',
      page: 1,
      userImageblock:[],
      blog_block:[]
    };
  },
  created () {
    axios.get('/user/block').then((Response)=>{
      // console.log(Response);
      this.blocklist = Response.data;
      for(let i=0;i<Response.data.length;i++){
        Response.data[i].userId
        // console.log(Response.data[i].userId+"이웃 아이디")
        axios.post('/user/list',{
        searchKey : "userId",
        searchValue : Response.data[i].userId
        }).then((Res)=>{
        // console.log(Res.data);  
        // console.log("Hi위는데이또");

        for(let j=0;j<Res.data.length;j++){
          if(Res.data[j].userId==Response.data[i].userId){
            this.userImageblock.splice(i,0,this.serverURL + Res.data[j].profileImg);
            this.blog_block[i]=Res.data[j].userId;
            break;
          }
        }
        // this.blogInfo = Response.data[0];
        // this.userImagefollowing.push(this.serverURL + this.blogInfo.profileImg);
        // console.log(this.blogInfo.userId+"이거");
        // console.log(this.serverURL+"이거");
        // console.log(this.blogInfo.profileImg+"이거");
    })
      }
    })
  },
  computed : {
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  methods : {
    select_blocklist() {
      axios.get('/user/block?searchValue=' + encodeURI(this.search)).then((Response)=>{
      // console.log(Response);
      this.blocklist = Response.data;
    })
    },
    block_delete(number) {
      axios.delete('/user/block/' + number).then((Response)=>{
        // console.log(Response);
        alert("차단해제되었습니다.");
        this.$router.go();
      })
    }
  }
};
</script>

<style scoped>
#blocklist_card {
  padding: 0 30px;
}
</style>
