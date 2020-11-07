<template>
  <div class="friends container col order">
    <div class="row" v-if="userInfo!=null && userInfo.userNo>-1">
      <!-- data가 들어갈 부분 -->
      <div class="col" id="main">
        <v-card :elevation="0" class="col-11" id="friends_card">
          <v-container fluid>
            <v-row align="center">
              <v-col md="4" class="ml-auto">
                <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="search_friends"></v-text-field>
              </v-col>
              <v-btn icon @click="search_friends"><v-icon>mdi-magnify</v-icon></v-btn>
            </v-row>
          </v-container>

          <!-- 탭 -->
          <b-tabs content-class="mt-3">
            <!-- 맞팔로우 -->
            <b-tab title="맞팔로우" active @click="tab_one"> 
              <v-card :elevation="0">
                <v-list-item>
                  <v-row>
                    <v-col id="card_detail" v-for="(user,index) in follow" :key="user.number" cols="6">
                        <v-col id="card_detail2" v-if="index%2==0">
                          <div>
                            <v-card :elevation="3">
                              <v-list-item-content>         
                                <v-list-item-title class="my-1"><v-avatar><img :src="userImagefollow[index]"></v-avatar>{{user.nickName}}({{user.userId}})</v-list-item-title>
                                <v-list-item-subtitle @click="goblog_follow(index)">Blog. {{user.blogName}}</v-list-item-subtitle>
                              </v-list-item-content>
                              <v-btn text @click="follow_delete(user.following)"> 끊기</v-btn>
                              <v-btn text @click="block_follow(user.following)">차단</v-btn>
                            </v-card>
                          </div>
                        </v-col>
                        <v-col id="card_detail2" v-if="index%2==1">
                          <div>
                            <v-card :elevation="3">
                              <v-list-item-content>         
                                <v-list-item-title class="my-1"><v-avatar><img :src="userImagefollow[index]"></v-avatar> {{user.nickName}}({{user.userId}})</v-list-item-title>
                                <v-list-item-subtitle @click="goblog_follow(index)">Blog. {{user.blogName}}</v-list-item-subtitle>
                              </v-list-item-content>
                              <v-btn text @click="follow_delete(user.following)"> 끊기</v-btn>
                              <v-btn text @click="block_follow(user.following)">차단</v-btn>
                            </v-card>
                          </div>
                        </v-col>
                    </v-col>
                  </v-row> 
                </v-list-item>
              </v-card>
            </b-tab>      
            <!-- 팔로잉 -->
            <b-tab title="팔로잉" @click="tab_two">
              <v-card :elevation="0">
                <v-list-item>
                  <v-row>
                    <v-col id="card_detail" v-for="(user,index) in following" :key="user.number" cols="6">
                        <v-col id="card_detail2" v-if="index%2==0">
                          <div>
                            <v-card :elevation="3">
                              <v-list-item-content>         
                                <v-list-item-title class="my-1"><v-avatar><img :src="userImagefollowing[index]"></v-avatar>{{user.nickName}}({{user.userId}})</v-list-item-title>
                                <v-list-item-subtitle @click="goblog_following(index)">Blog. {{user.blogName}}</v-list-item-subtitle>
                              </v-list-item-content>
                              <v-btn text @click="follow_delete(user.following)"> 끊기</v-btn>
                              <v-btn text @click="block_follow(user.following)">차단</v-btn>
                            </v-card>
                          </div>
                        </v-col>
                        <v-col id="card_detail2" v-if="index%2==1">
                          <div>
                            <v-card :elevation="3">
                              <v-list-item-content>         
                                <v-list-item-title class="my-1"><v-avatar><img :src="userImagefollowing[index]"></v-avatar> {{user.nickName}}({{user.userId}})</v-list-item-title>
                                <v-list-item-subtitle @click="goblog_following(index)">Blog. {{user.blogName}}</v-list-item-subtitle>
                              </v-list-item-content>
                              <v-btn text @click="follow_delete(user.following)"> 끊기</v-btn>
                              <v-btn text @click="block_follow(user.following)">차단</v-btn>
                            </v-card>
                          </div>
                        </v-col>
                    </v-col>
                  </v-row>
                </v-list-item>
              </v-card>
            </b-tab>
            <!-- 팔로워 -->
            <b-tab title="팔로워" @click="tab_three">
              <v-card :elevation="0">
                <v-list-item>
                  <v-row>
                    <v-col id="card_detail" v-for="(user,index) in follower" :key="user.number" cols="6">
                        <v-col id="card_detail2" v-if="index%2==0">
                          <div>
                            <v-card :elevation="3">
                              <v-list-item-content>         
                                <v-list-item-title class="my-1"><v-avatar><img :src="userImagefollower[index]"></v-avatar>{{user.nickName}}({{user.userId}})</v-list-item-title>
                                <v-list-item-subtitle @click="goblog_follower(index)">Blog. {{user.blogName}}</v-list-item-subtitle>
                              </v-list-item-content>
                              <v-btn text @click="follow_you(user.follower)"> 팔로우</v-btn>
                              <v-btn text @click="block_follow(user.follower)">차단</v-btn>
                            </v-card>
                          </div>
                        </v-col>
                        <v-col id="card_detail2" v-if="index%2==1">
                          <div>
                            <v-card :elevation="3">
                              <v-list-item-content>         
                                <v-list-item-title class="my-1"><v-avatar><img :src="userImagefollower[index]"></v-avatar> {{user.nickName}}({{user.userId}})</v-list-item-title>
                                <v-list-item-subtitle @click="goblog_follower(index)">Blog. {{user.blogName}}</v-list-item-subtitle>
                              </v-list-item-content>
                              <v-btn text @click="follow_you(user.follower)"> 팔로우</v-btn>
                              <v-btn text @click="block_follow(user.follower)">차단</v-btn>
                            </v-card>
                          </div>
                        </v-col>
                    </v-col>
                  </v-row> 
                </v-list-item>
              </v-card>
            </b-tab>
            <!-- 차단목록 -->
            <b-tab title="차단목록" @click="tab_four">
              <v-card :elevation="0">
                <v-list-item>
                  <v-row>
                    <v-col id="card_detail" v-for="(user,index) in blocklist" :key="user.number" cols="6">
                      <v-col id="card_detail2" v-if="index%2==0">
                        <div>
                          <v-card :elevation="3">
                            <v-list-item-content>         
                              <v-list-item-title class="my-1"><v-avatar><img :src="userImageblock[index]"></v-avatar>{{user.nickName}}({{user.userId}})</v-list-item-title>
                            </v-list-item-content>
                            <v-btn text @click="block_delete(user.blocking)">차단해제</v-btn>
                          </v-card>
                        </div>
                      </v-col>
                      <v-col id="card_detail2" v-if="index%2==1">
                        <div>
                          <v-card :elevation="3">
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
                :length="msglength"
                :next-icon="nextIcon"
                :prev-icon="prevIcon"
                :page="page"
                total-visible="10"
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
  name: "friends",
  components: {},
  data() {
    return {
      rows: 100,
      follower : [],
      following : [],
      follow : [],
      blocklist : [],
      search : '',
      select : 1,
      userImagefollow : [],
      userImagefollower : [],
      userImagefollowing : [],
      userImageblock:[],
      blog_follow : [],
      blog_follower : [],
      blog_following : [],
      blog_block:[],
      // page: 1,
    };
  },
  created(){
    axios.get('/user/following')
    .then((Response)=>{
      // console.log(Response);
      this.following = Response.data;
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
            this.blogInfo = Res.data[j]; // 해당 블로그 정보
            this.userImagefollowing.splice(i,0,this.serverURL + Res.data[j].profileImg);
            this.blog_following[i]=Res.data[j].userId;
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
    }),
    axios.get('/user/follower')
    .then((Response)=>{
      // console.log(Response);
      this.follower = Response.data;
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
            this.blogInfo = Res.data[j]; // 해당 블로그 정보
            this.userImagefollower.splice(i,0,this.serverURL + Res.data[j].profileImg);
            this.blog_follower[i]=Res.data[j].userId;
            break;
          }
        }
        // this.blogInfo = Response.data[0];
        // this.userImagefollower.push(this.serverURL + this.blogInfo.profileImg);
        // console.log(this.blogInfo.userId+"이거");
        // console.log(this.serverURL+"이거");
        // console.log(this.blogInfo.profileImg+"이거");
    })
      }
    }),
    axios.get('/user/f4f')
    .then((Response)=>{
      // console.log(Response);
      this.follow = Response.data;
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
            this.blogInfo = Res.data[j]; // 해당 블로그 정보
            this.userImagefollow.splice(i,0,this.serverURL + Res.data[j].profileImg);
            this.blog_follow[i]=Res.data[j].userId;
            break;
          }
        }
        // this.blogInfo = Response.data[0];
        // this.userImagefollow.push(this.serverURL + this.blogInfo.profileImg);
        // console.log(this.blogInfo.userId+"이거");
        // console.log(this.serverURL+"이거");
        // console.log(this.blogInfo.profileImg+"이거");
        // console.log(i+"다")
    })
      }
    }),
    axios.get('/user/block')
    .then((Response)=>{
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
        // console.log(this.blogInfo.userId+"이거");
        // console.log(this.serverURL+"이거");
        // console.log(this.blogInfo.profileImg+"이거");
    })
      }
    })
  },
  computed: {
    serverURL(){
      return this.$store.getters.serverURL;
    },
    userInfo(){
        // console.dir(this.$store.getters.userInfo);
        return this.$store.getters.userInfo;
      }
  },
  methods: {
    tab_one() {
      this.select = 1;
      axios.get('/user/f4f')
    .then((Response)=>{
      // console.log(Response);
      this.follow = Response.data;
    })
    },
    tab_two() {
      this.select = 2;
      axios.get('/user/following')
    .then((Response)=>{
      // console.log(Response);
      this.following = Response.data;
    })
    },
    tab_three() {
      this.select = 3;
      axios.get('/user/follower')
    .then((Response)=>{
      // console.log(Response);
      this.follower = Response.data;
    })
    },
    tab_four() {
      this.select = 4;
      axios.get('/user/block')
    .then((Response)=>{
      // console.log(Response);
      this.blocklist = Response.data;
    })
    },
    search_friends() {
      if(this.select==1){
        axios.get('/user/f4f?searchValue='+encodeURI(this.search))
        .then((Response)=>{
        // console.log(Response);
        this.follow = Response.data;
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
            this.blogInfo = Res.data[j]; // 해당 블로그 정보
            this.userImagefollow.splice(i,0,this.serverURL + Res.data[j].profileImg);
            this.blog_follow[i]=Res.data[j].userId;
            break;
          }
        }
        // this.blogInfo = Response.data[0];
        // this.userImagefollow.push(this.serverURL + this.blogInfo.profileImg);
        // console.log(this.blogInfo.userId+"이거");
        // console.log(this.serverURL+"이거");
        // console.log(this.blogInfo.profileImg+"이거");
        // console.log(i+"다")
    })
      }
        })
      }else if(this.select==2){
          axios.get('/user/following?searchValue='+encodeURI(this.search))
          .then((Response)=>{
          // console.log(Response);
          this.following = Response.data;
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
            this.blogInfo = Res.data[j]; // 해당 블로그 정보
            this.userImagefollowing.splice(i,0,this.serverURL + Res.data[j].profileImg);
            this.blog_following[i]=Res.data[j].userId;
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
      }else if(this.select==3){
        axios.get('/user/follower?searchValue='+encodeURI(this.search))
        .then((Response)=>{
        // console.log(Response);
        this.follower = Response.data;
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
            this.blogInfo = Res.data[j]; // 해당 블로그 정보
            this.userImagefollower.splice(i,0,this.serverURL + Res.data[j].profileImg);
            this.blog_follower[i]=Res.data[j].userId;
            break;
          }
        }
        // this.blogInfo = Response.data[0];
        // this.userImagefollower.push(this.serverURL + this.blogInfo.profileImg);
        // console.log(this.blogInfo.userId+"이거");
        // console.log(this.serverURL+"이거");
        // console.log(this.blogInfo.profileImg+"이거");
    })
      }
        })
      }else{
        axios.get('/user/block?searchValue='+encodeURI(this.search))
        .then((Response)=>{
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
        // console.log(this.blogInfo.userId+"이거");
        // console.log(this.serverURL+"이거");
        // console.log(this.blogInfo.profileImg+"이거");
    })
      }
        })   
      }
    },
    follow_delete(number) {
      // console.log(number)
      axios.delete(`/user/follow/`+ number).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 삭제되었습니다.");
        this.$router.go();
      })
    },
    follow_you(number){
      axios.post(`/user/follow/`+ number).then((Response)=>{
        // console.log(Response);
        alert("팔로우가 되었습니다.");
        this.$router.go();
      })
    },
    block_follow(number){
      axios.post(`/user/block/` + number).then((Res)=>{
          // console.log(Res);
          alert("차단 되었습니다.");
          this.$router.go();
        })
    },
    block_delete(number) {
      axios.delete('/user/block/' + number).then((Response)=>{
        // console.log(Response);
        alert("차단해제되었습니다.");
        this.$router.go();
      })
    },
    goblog_follower(index){
      this.$router.push("/bloghome/"+this.blog_follower[index]);
    },
    goblog_follow(index){
      this.$router.push("/bloghome/"+this.blog_follow[index]);
    },
    goblog_following(index){
      this.$router.push("/bloghome/"+this.blog_following[index]);
    },
  }
};
</script>

<style scoped>
#friends_card {
  padding: 0 30px;
}
#card_detail {
  padding: 0px;
}
#card_detail2 {
  padding: 5px;
}
</style>
