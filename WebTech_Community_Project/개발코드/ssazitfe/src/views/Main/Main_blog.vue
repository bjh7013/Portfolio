<template>
  <div class="mainblog">
    <!-- 네브바 -->
    <nav-header></nav-header>
    <div id="board_body">

      <!-- select box , 검색-->
      <v-container id="search" fluid>
        <v-row align="center">
          <v-col class="d-flex" cols="3" sm="3">
            <v-select
              :items="items1"
              :value="items1[2]"
              v-model="value"
            ></v-select>
          </v-col>
          <v-col cols="7">
            <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search" @keyup.enter="searchdata"></v-text-field>
          </v-col>
          <v-btn icon @click="searchdata"><v-icon>mdi-magnify</v-icon></v-btn>
        </v-row>
      </v-container>

      <b-tabs content-class="mt-3">
        <b-tab title="게시판" @click="tab_board" > 
        </b-tab>
        <b-tab title="작품" @click="tab_book"> 
        </b-tab>

        <b-tab title="블로그" @click="tab_blog" active> 
          <div class="container list-article">
            <v-col lg="12" xs="4">
              <div class="btn-group pull-right">
                <button class="btn btn-dark" @click="switchviewone">
                  <span class="icon-th-large"></span>
                </button>
                <button class="btn btn-dark" @click="switchviewtwo">
                  <span class="icon-th-list"></span>
                </button>
              </div>
              <br><br>
              <div class="clearfix"></div>
              <div class="row article-wrapper bloc col-xs-12 col-xs-4" v-if="isActiveone">
                <template v-for="(user,index) in users">
                  <div class="col-xs-4" :key="user.userId" v-if="user.blogName!=null" style="width:250px;">
                    <article>
                      <a @click="goblog(index)" class="more">more</a>
                      <div class="img-wrapper"><img :src="userImage[index]"></div>
                      <br>
                      <h1>{{user.blogName}}</h1>
                      <p>{{user.nickName}}</p>
                    </article>
                  </div>
                </template>
              </div>
              <div class="row article-wrapper" v-if="isActivetwo">
                <template v-for="(user,index) in users">
                  <div class="col-xs-12"  :key="user.userId" v-if="user.blogName!=null">
                    <article style="width:1050px;">
                      <a @click="goblog(index)" class="more">more</a>
                      <div class="img-wrapper"><img :src="userImage[index]"></div>
                      <h1>{{user.blogName}}</h1>
                      <p>{{user.nickName}}</p>
                    </article>
                  </div>
                </template>
              </div>
            </v-col>
          </div>
          <!-- 페이지네이션 -->
                  <v-col cols="12">
                    <v-container id="page" class="max-width" @click="clickpage">
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
                  </v-col>
        </b-tab>
        
        <b-tab title="유저" @click="tab_user"> 
        </b-tab>
      </b-tabs>
    </div>
    <!-- 푸터 -->
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import axios from 'axios'
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
export default {
  name: "Mainuser", 
    data() {
      return{
        items1: ['게시판', '작품', '블로그', '유저'],
        selected1: '1',
        rows: 100,
        isActiveone: false,
        isActivetwo: true,
        users : [],
        page: this.$route.params.page,
        search : this.$route.params.searchValue,
        userImage : [],
        go_blog : [],
        msglength: 1,
        value : "블로그",
      }
    },
  created() {
    this.get_user()
    this.blogcount()
    this.search = "";
  },
  components : {
    NavHeader,
    NavFooter
  },
  computed: { 
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  methods : {
    tab_board() {
        this.$router.push({ name: 'Mainboard', params: { searchValue: this.$route.params.searchValue, page:1, type:0 ,key: this.search+this.page }});
      },
    tab_book() {
        this.$router.push({ name: 'Mainbook', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
    },
    tab_blog() {
     this.$router.push({ name: 'Mainblog', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
    },
    tab_user() {
      this.$router.push({ name: 'Mainuser', params: { searchValue: this.$route.params.searchValue, page:1 ,key: this.search+this.page }});
    },
    switchviewone() {
      this.isActiveone=true;
      this.isActivetwo=false;
    },
    switchviewtwo() {
      this.isActiveone=false;
      this.isActivetwo=true;
    },
    get_user() {
      if(this.search == "　"){
        axios.get('/blog/blogs?limitStart='+(this.$route.params.page-1)*9)
        .then((Response)=>{
          this.users = []
          this.userImage = []
          for(let i=0;i<Response.data.length;i++){
            this.users.splice(i,0,Response.data[i]);
            this.userImage.splice(i,0,this.serverURL + Response.data[i].profileImg);
          }
        })
      }else{
        axios.get('/blog/blogs?blogName='+encodeURI(this.$route.params.searchValue)+'&limitStart='+(this.$route.params.page-1)*9)
        .then((Response)=>{
          this.users = []
          this.userImage = []
          for(let i=0;i<Response.data.length;i++){
            this.users.splice(i,0,Response.data[i]);
            this.userImage.splice(i,0,this.serverURL + Response.data[i].profileImg);
          }
        })

      }
    },
    blogcount() {
      if(this.search == "　"){
        axios.get('/blog/blogscount')
        .then((Response)=>{
          this.msglength = parseInt(Math.ceil(Response.data/9))
        })
      }else{
        axios.get('/blog/blogscount?blogName='+ encodeURI(this.$route.params.searchValue))
        .then((Response)=>{
          this.msglength = parseInt(Math.ceil(Response.data/9))
        })
      }
    },
    goblog(index){
      this.$router.push("/bloghome/"+this.users[index].userId);
    },
    searchdata(){
        if(this.serach == "" || !this.search){
          this.search ="　"
        }
        if(this.value==="게시판"){
          this.$router.push({ name: 'Mainboard', params: { searchValue: this.search, page:1, type:0 ,key: this.search+this.page }});
        }else if(this.value==="작품"){
          this.$router.push({ name: 'Mainbook', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }else if(this.value==="블로그"){
          this.$router.push({ name: 'Mainblog', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }else if(this.value==="유저"){
          this.$router.push({ name: 'Mainuser', params: { searchValue: this.search, page:1 ,key: this.search+this.page }});
        }
        this.search =""
      },
      clickpage() {
        this.$router.push({ name: 'Mainblog', params: { searchValue: this.$route.params.searchValue, page:this.page ,key: this.$route.params.searchValue+this.page }});
      }
  }
};

</script>

<style scoped>
#board_body {
  width: 85%;
  margin: 0 100px;
}
#search {
  margin: 0 auto;
}

@import url(https://fonts.googleapis.com/css?family=Pacifico);

.article-wrapper {
  -moz-transition: width 0.4s;
  -o-transition: width 0.4s;
  -webkit-transition: width 0.4s;
  transition: width 0.4s;
}

article {
  position: relative;
  padding-left: 180px;
  padding-right: 75px;
  height: 150px;
  margin-bottom: 30px;
  background-color: #ECEFF1;
  -moz-border-radius: 150px;
  -webkit-border-radius: 150px;
  border-radius: 150px;
  cursor: pointer;
}

article .img-wrapper {
  position: absolute;
  top: -10px;
  left: -10px;
  -moz-border-radius: 100px;
  -webkit-border-radius: 100px;
  border-radius: 100px;
  background-color: #C5CAE9;
  padding: 10px;
  z-index: 1000;
  -moz-transition: all 0.2s;
  -o-transition: all 0.2s;
  -webkit-transition: all 0.2s;
  transition: all 0.2s;
}
article .img-wrapper img {
  -moz-border-radius: 100px;
  -webkit-border-radius: 100px;
  border-radius: 100px;
  -moz-transition: all 0.2s;
  -o-transition: all 0.2s;
  -webkit-transition: all 0.2s;
  transition: all 0.2s;
  width: 150px;
  height: 150px;
}
article h1 {
  height: 75px;
  line-height: 75px;
  font-size: 24px;
  border-bottom: 2px solid #cde;
  color: #000000;
  -moz-transition: all 0.4s;
  -o-transition: all 0.4s;
  -webkit-transition: all 0.4s;
  transition: all 0.4s;
}
article p {
  color: #616161;
}
article a {
  position: absolute;
  display: block;
  top: 0;
  left: 0;
  height: 100%;
  line-height: 150px;
  text-align: center;
  width: 100%;
  background-color: #D1C4E9;
  font-size: 48px;
  font-style: italic;
  opacity: 0;
  color: #FF9E80 !important; 
  -moz-transition: all 0.4s;
  -o-transition: all 0.4s;
  -webkit-transition: all 0.4s;
  transition: all 0.4s;
  text-decoration: none !important;
  -moz-border-radius: 150px;
  -webkit-border-radius: 150px;
  border-radius: 150px;
  font-family: "Pacifico";
  text-shadow: 0 1px 1px #aaa;
}
article:hover .img-wrapper {
  padding: 5px;
  -moz-transform: rotate(5deg);
  -ms-transform: rotate(5deg);
  -webkit-transform: rotate(5deg);
  transform: rotate(5deg);
}
article:hover .img-wrapper img {
  width: 150px;
}
article:hover a {
  opacity: 1;
}

.bloc article {
  padding: 10px;
  height: 225px;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  margin-top: 75px;
}
.bloc article .img-wrapper {
  position: relative;
  top: -85px;
  width: 170px;
  left: inherit;
  margin: auto;
  margin-bottom: -100px;
}
.bloc article h1 {
  font-size: 14px;
  height: 37.5px;
  line-height: 37.5px;
}
.bloc article p {
  font-size: 12px;
}
.bloc article a {
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  line-height: 255px;
}
</style>