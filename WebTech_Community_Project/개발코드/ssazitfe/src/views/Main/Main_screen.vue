<template>
  <div class="mainboard">
    <!-- 네브바 -->
    <nav-header></nav-header>
    <div id="board_body">

      <!-- select box , 검색-->
      <v-container id="search" fluid>
        <v-row align="center">
          <v-col class="d-flex" cols="3" sm="3">
            <v-select
              :items="items1"
              label="분류"
            ></v-select>
          </v-col>
          <v-col cols="7">
            <v-text-field v-model="search" clearable flat solo-inverted hide-details label="Search"></v-text-field>
          </v-col>
          <v-btn icon><v-icon>mdi-magnify</v-icon></v-btn>
        </v-row>
      </v-container>

        <b-tabs content-class="mt-3">
          <!-- 맞팔로우 -->
          <b-tab title="게시판" @click="tab_board" :active="this.$route.params.select==1"> 
                <div>
        <p style="text-align:left; margin: 0 20px;">이 달의 블로그</p>
        <!-- 이 달의 블로그 -->
        <div>
          <v-row id="reco">
            <!-- 이 달의 블로그 하드코딩1 -->
            <v-hover v-slot:default="{ hover }">
              <v-card class="auto" color="grey lighten-4" max-width="250" max-height="350">
                <v-img max-width="250" max-height="250" src="https://picsum.photos/250/250" img-alt="Image" img-top>
                  <v-expand-transition>
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                      style="height: 100%;"
                    >
                      <div class="font-weight-light title mb-2">아바타 닉네임</div>
                      <div class="font-weight-light title mb-2">
                        내용
                      </div>
                    </div>
                  </v-expand-transition>
                </v-img>
              </v-card>
            </v-hover>
            <!-- 이 달의 블로그 하드코딩2 -->
            <v-hover v-slot:default="{ hover }">
              <v-card class="auto" color="grey lighten-4" max-width="250" max-height="350">
                <v-img max-width="250" max-height="250" src="https://placekitten.com/g/250/250" img-alt="Image" img-top>
                  <v-expand-transition>
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                      style="height: 100%;"
                    >
                      <div class="font-weight-light title mb-2">아바타 닉네임</div>
                      <div class="font-weight-light title mb-2">
                        내용
                      </div>
                    </div>
                  </v-expand-transition>
                </v-img>
              </v-card>
            </v-hover>
            <!-- 이 달의 블로그 하드코딩3 -->
            <v-hover v-slot:default="{ hover }">
              <v-card class="auto" color="grey lighten-4" max-width="250" max-height="350">
                <v-img max-width="250" max-height="250" src="https://placekitten.com/250/250" img-alt="Image" img-top>
                  <v-expand-transition>
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                      style="height: 100%;"
                    >
                      <div class="font-weight-light title mb-2">아바타 닉네임</div>
                      <div class="font-weight-light title mb-2">
                        내용
                      </div>
                    </div>
                  </v-expand-transition>
                </v-img>  
              </v-card>
            </v-hover> 
            <!-- 이 달의 블로그 하드코딩4 -->
            <v-hover v-slot:default="{ hover }">
              <v-card class="auto" color="grey lighten-4" max-width="250" max-height="350">
                <v-img max-width="250" max-height="250" src="https://picsum.photos/250/250?random=1" img-alt="Image" img-top>
                  <v-expand-transition>
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                      style="height: 100%;"
                    >
                      <div class="font-weight-light title mb-2">아바타 닉네임</div>
                      <div class="font-weight-light title mb-2">
                        내용
                      </div>
                    </div>
                  </v-expand-transition>
                </v-img>  
              </v-card>
            </v-hover>   
            <!-- 이 달의 블로그 하드코딩5 -->
            <v-hover v-slot:default="{ hover }">
              <v-card class="auto" color="grey lighten-4" max-width="250" max-height="350">
                <v-img max-width="250" max-height="250" src="https://picsum.photos/250/250?random=2" img-alt="Image" img-top>
                  <v-expand-transition>
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                      style="height: 100%;"
                    >
                      <div class="font-weight-light title mb-2">아바타 닉네임</div>
                      <div class="font-weight-light title mb-2">
                        내용
                      </div>
                    </div>
                  </v-expand-transition>
                </v-img>  
              </v-card>
            </v-hover>      
          </v-row>
        </div>
        <hr>

        <!-- 게시판 하단 메뉴 -->
        <br>
        <div class="left">
          <v-tabs id="tab">
            <v-row id="board_title">       
              <v-col id="left" cols="8">
                <li><v-tab>알고싶어요!!</v-tab></li>
                <li><v-tab>게시글</v-tab></li>
              </v-col>
              <v-col id="right" cols="4" sm="3">
                <v-select
                :items="items2"
                label="분류"
                ></v-select>
              </v-col>
            </v-row>
          </v-tabs>
        </div>

        <!-- 게시판 하단 -->
        <div>
          <b-card img-src="https://placekitten.com/150/150?random=2" img-alt="Card image" img-left class="mb-3">
            <b-card-title>
              고먐미너무 귀며뭐
            </b-card-title>
            <b-card-text>
              정말 고양이 너무 귀여운 것 같아요! 고양이 공유하고 싶어서 글 써욤
            </b-card-text>
            <v-divider></v-divider>
            <b-card-text>
              zerozoo
            </b-card-text>
          </b-card>
          <b-card img-src="https://picsum.photos/150/150?random=2" img-alt="Card image" img-left class="mb-3">
            <b-card-title>
              백준 10034번 python으로 어떻게...
            </b-card-title>
            <b-card-text>
              python으로 백준 10034번 푸는법 알려주세요. 제 코드는...
            </b-card-text>
            <v-divider></v-divider>
            <b-card-text>
              jjiiiiinie
            </b-card-text>
          </b-card>
          <b-card img-src="https://picsum.photos/150/150?random=3" img-alt="Card image" img-left class="mb-3">
            <b-card-title>
              spring boot 꿀팁 알려드림
            </b-card-title>
            <b-card-text>
              저는 backend 짱 dragonwater입니다 ...
            </b-card-text>
            <v-divider></v-divider>
            <b-card-text>
              dragonwater
            </b-card-text>
          </b-card>
          <b-card img-src="https://picsum.photos/150/150?random=4" img-alt="Card image" img-left class="mb-3">
            <b-card-title>
              요즘 맛집 부수기 중
            </b-card-title>
            <b-card-text>
              프로젝트 하면서 매일 점심 맛집 부수기 중인데요 <br> 구미 맛집 소개합니다
            </b-card-text>
            <v-divider></v-divider>
            <b-card-text>
              groot
            </b-card-text>
          </b-card>
        </div>

        <!-- 페이지네이션 -->
        <v-col>
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
        </v-col>
      </div>
          </b-tab>




          <b-tab title="작품" @click="tab_book" :active="this.$route.params.select==2"> 
            <div>
        <!-- 작품 상단 -->
        <div>
          <p style="text-align:left; margin: 0 20px;">이 달의 작품</p>
          <!-- 이 달의 작품 (하드코딩) -->
          <div>
            <v-row id="reco">
              <!-- 이 달의 블로그 하드코딩1 -->
              <v-hover v-slot:default="{ hover }">
                <v-card class="auto" color="grey lighten-4" width="250" height="300">
                  <v-img max-width="250" max-height="250" :src="book5" img-alt="Image" img-top>
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <div class="font-weight-light title mb-2">아바타 닉네임</div>
                        <div class="font-weight-light title mb-2">
                          내용
                        </div>
                      </div>
                    </v-expand-transition>
                  </v-img>
                </v-card>
              </v-hover>
              <!-- 이 달의 블로그 하드코딩2 -->
              <v-hover v-slot:default="{ hover }">
                <v-card class="auto" color="grey lighten-4" width="250" height="300">
                  <v-img max-width="250" max-height="250" :src="book6" img-alt="Image" img-top>
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <div class="font-weight-light title mb-2">아바타 닉네임</div>
                        <div class="font-weight-light title mb-2">
                          내용
                        </div>
                      </div>
                    </v-expand-transition>
                  </v-img>
                </v-card>
              </v-hover>
              <!-- 이 달의 블로그 하드코딩3 -->
              <v-hover v-slot:default="{ hover }">
                <v-card class="auto" color="grey lighten-4" width="250" height="300">
                  <v-img max-width="250" max-height="250" :src="book7" img-alt="Image" img-top>
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <div class="font-weight-light title mb-2">아바타 닉네임</div>
                        <div class="font-weight-light title mb-2">
                          내용
                        </div>
                      </div>
                    </v-expand-transition>
                  </v-img>
                </v-card>
              </v-hover>
              <!-- 이 달의 블로그 하드코딩4 -->
              <v-hover v-slot:default="{ hover }">
                <v-card class="auto" color="grey lighten-4" width="250" height="300">
                  <v-img max-width="250" max-height="250" :src="book8" img-alt="Image" img-top>
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <div class="font-weight-light title mb-2">아바타 닉네임</div>
                        <div class="font-weight-light title mb-2">
                          내용
                        </div>
                      </div>
                    </v-expand-transition>
                  </v-img>
                </v-card>
              </v-hover>
              <!-- 이 달의 블로그 하드코딩5 -->
              <v-hover v-slot:default="{ hover }">
                <v-card class="auto" color="grey lighten-4" width="250" height="300">
                  <v-img max-width="250" max-height="250" :src="book9" img-alt="Image" img-top>
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out blue-grey darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <div class="font-weight-light title mb-2">아바타 닉네임</div>
                        <div class="font-weight-light title mb-2">
                          내용
                        </div>
                      </div>
                    </v-expand-transition>
                  </v-img>
                </v-card>
              </v-hover>
            </v-row>
          </div>
        </div>
        <hr>

        <!-- select box -->
        <v-col id="right" cols="3" sm="3">
          <v-select
          :items="items2"
          label="분류"
          ></v-select>
        </v-col>
        <br><br><br><br>

        <!-- 작품 하단 -->
        <v-row>
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#FFFAF0">
              <v-card-title class="layout justify-center">
                " 꿈을 이루기 위해 당신은 무얼하고 있습니까? "
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book1"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#F0FFFF">
              <v-card-title class="layout justify-center">
                "달님이 잠드는 시간"
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book3"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#FFFAF0">
              <v-card-title class="layout justify-center">
                "옥탑방 고양이"
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book2"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>  
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#F0FFFF">
              <v-card-title class="layout justify-center">
                "어느날 우연히 마주친 그 날"
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book4"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>
        </v-row>
        <v-row>
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#F0FFFF">
              <v-card-title class="layout justify-center">
                " 꿈을 이루기 위해 당신은 무얼하고 있습니까? "
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book1"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#FFFAF0">
              <v-card-title class="layout justify-center">
                "달님이 잠드는 시간"
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book3"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#F0FFFF">
              <v-card-title class="layout justify-center">
                "옥탑방 고양이"
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book2"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>  
          <div class="col-3">
            <v-card class="elevation-6" style="background-color:#FFFAF0">
              <v-card-title class="layout justify-center">
                "어느날 우연히 마주친 그 날"
              </v-card-title>
              <v-card-text>
                <img id="book" :src="book4"/>
              </v-card-text>
              <v-card-footer>
                <v-btn small>좋아요</v-btn>
                <v-btn small>내서재</v-btn>
              </v-card-footer>
            </v-card>
          </div>
        </v-row>


        <!-- 페이지네이션 -->
        <v-col>
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
        </v-col>
      </div>
          </b-tab>
          <b-tab title="블로그" @click="tab_blog" :active="this.$route.params.select==3"> 
          







          </b-tab>
          <b-tab title="유저" @click="tab_user" :active="this.$route.params.select==4"> 
          
          </b-tab>
        </b-tabs>
    </div>
    <!-- 푸터 -->
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
export default {
  name: "MainScreen", 
    data() {
      return{
        items1: ['게시판', '작품', '블로그', '유저'],
        items2: ['전체선택', '알고리즘', '웹'],
        selected1: '1',
        selected2: '1',
        rows: 100,
        page: 1,
        totalVisible: 10,
        board_data: [{
          img_src: "https://placekitten.com/150/150",
          title: "",
          content: "",
          user: "",
        },{
          img_src: "https://placekitten.com/150/150",
          title: "",
          content: "",
          user: "",
        },{
          img_src: "https://placekitten.com/150/150",
          title: "",
          content: "",
          user: "",
        }],
        book1: require("../images/book1.png"),
        book2: require("../images/book2.png"),
        book3: require("../images/book3.png"),
        book4: require("../images/book4.png"),
        book5: require("../images/book5.png"),
        book6: require("../images/book6.png"),
        book7: require("../images/book7.png"),
        book8: require("../images/book8.png"),
        book9: require("../images/book9.png"),
      }
    },
  components : {
    NavHeader,
    NavFooter
  },
  methods : {
      tab_board() {
          this.$router.push("/mainboard");
      },
      tab_book() {
          this.$router.push("/mainbook");
      },
      tab_blog() {
          this.$router.push("/mainscreen/3");
      },
      tab_user() {
          this.$router.push("/mainscreen/4");
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
#card {
  padding: 0 20px;
}
p {
  margin-bottom: 0px;
}
hr {
  margin: 16px 20px;
  padding: 0 20px;
  border: 0;
  height: 2px;
  background: #ccc;
}
#btn_select {
  display: inline-block;
}
#btn {
  float: left;
  margin: 10px;
}
#select {
  float: right;
  padding: 0;
}
.cols {
  padding: 0 0px;
}
#tab {
  display: inline;
}
#left {
  padding: 0 0px;
  width: 50%;
  float: left;
  box-sizing: border-box;
}
#right {
  padding: 0 0px;
  width: 50%;
  float: right;
  box-sizing: border-box;
}
#board_title {
  margin: 0 0px;
}
#page {
  text-align: center;
  margin: 0 auto;
  width: 100%;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}
#reco {
  margin: 0 20px;
}
.v-card__title {
  height: 100px;
  text-align: center;
}
.v-card__text {
  height: 180px;
}
.v-card-footer {
  height: 20px;
}
#book {
  width: 250px;
}
</style>