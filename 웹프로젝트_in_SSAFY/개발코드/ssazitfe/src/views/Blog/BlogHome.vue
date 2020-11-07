<template>
  <div id="blogHome">
<!-- 
    <v-row style="width:1050px;">
      <v-col cols="3"></v-col>
      <v-col cols="6" class="px-0">

        <v-text-field
          label="블로그 내 검색"
          clearable
          class="p-0 m-0"
        ></v-text-field>
        
      </v-col>
        <v-btn icon color="grey" class="mx-0 px-0 mt-2">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
    </v-row> 
    
    <br />
    <section class="article-list" style="width:1050px;">
      <router-view />
      <div>

        <div class="article-contents">
          <v-row class="article-post" style="justify-content:space-between">
            <div class="article-content" style="width:80%;">
              <p align="left" class="font-weight-medium caption mb-0">[카테고리]</p>
              <h5 align="left" class="font-weight-black">알고리즘, 매일매일 좋은아침으로 하기 좋은 것</h5>
              <p align="left" class="font-weight-light body-1">
                아래의 깃에서 볼 수 있는 코드는 부분집합, 순열, 중복순열, 조합, 중복조합 Java에서 next_permutation을 지원하지 않기 때문에 가지고 있으면 좋은 것..
              </p>
              <v-row class="article-bottom" style="justify-content:space-between">
                <v-col class="article-info">
                  <p align="left" class="font-weight-light caption mb-0">
                    2020-01-01 · 댓글수 24
                    <v-icon small>mdi-comment-text-outline</v-icon>
                  </p>
                  <p align="left" class="font-weight-medium caption mb-0">
                    #태그 #SSAFY #복붙 #자유게시판 #알고리즘
                  </p>
                </v-col>
                <v-col class="article-icon" cols="2">
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon> mdi-star-outline</v-icon>
                  </v-btn>
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon>mdi-dots-horizontal</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </div>
            <v-col>
              <v-img src="https://picsum.photos/200/200?random=2"></v-img>
            </v-col>
          </v-row>
          <hr />
          <v-row class="article-post" style="justify-content:space-between">
            <div class="article-content" style="width:80%;">
              <p align="left" class="font-weight-medium caption mb-0">[카테고리]</p>
              <h5 align="left" class="font-weight-black">멀캠 올 때마다 ip 설정하기 귀찮으신 여러분을 위해 배치파일을 만..</h5>
              <p align="left" class="font-weight-light body-1">
                zip이 업로드가 안돼서 구글 드라이브 링크로 드려요 다운 받으시면 사용 방법 적혀 있으니까 참고해서 사용 부탁드립니다~!
              </p>
              <v-row class="article-bottom" style="justify-content:space-between">
                <v-col class="article-info">
                  <p align="left" class="font-weight-light caption mb-0">
                    2020-01-01 · 댓글수 24
                    <v-icon small>mdi-comment-text-outline</v-icon>
                  </p>
                  <p align="left" class="font-weight-medium caption mb-0">
                    #태그 #SSAFY #복붙 #열린게시판 #알고리즘
                  </p>
                </v-col>
                
                <v-col class="article-icon" cols="2">
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon> mdi-star-outline</v-icon>
                  </v-btn>
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon>mdi-dots-horizontal</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </div>
            <v-col>
            </v-col>
          </v-row>
          <hr />
          <v-row class="article-post" style="justify-content:space-between">
            <div class="article-content" style="width:80%;">
              <p align="left" class="font-weight-medium caption mb-0">[카테고리]</p>
              <h5 align="left" class="font-weight-black">AWS 공부하기 좋은 블로그 공유해요!</h5>
              <p align="left" class="font-weight-light body-1">
                안녕하세요! 이번에 이러닝 AWS 수강하시는 분들이 많은 것 같아요! 그만큼 AWS에 관심 많은 분들이 많은것 같아서 제가 AWS 공부하면서 참고하고 있는 브런치 주..
              </p>
              <v-row class="article-bottom" style="justify-content:space-between">
                <v-col class="article-info">
                  <p align="left" class="font-weight-light caption mb-0">
                    2020-01-01 · 댓글수 24
                    <v-icon small>mdi-comment-text-outline</v-icon>
                  </p>
                  <p align="left" class="font-weight-medium caption mb-0">
                    #태그 #SSAFY #복붙 #자유게시판 #알고리즘
                  </p>
                </v-col>
                <v-col class="article-icon" cols="2">
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon> mdi-star-outline</v-icon>
                  </v-btn>
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon>mdi-dots-horizontal</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </div>
            <v-col>
              <v-img src="https://picsum.photos/200/200?random=1"></v-img>
            </v-col>
          </v-row>
          <hr />
          <v-row class="article-post" style="justify-content:space-between">
            <div class="article-content" style="width:80%;">
              <p align="left" class="font-weight-medium caption mb-0">[카테고리]</p>
              <h5 align="left" class="font-weight-black">알고리즘, 매일매일 좋은아침으로 하기 좋은 것</h5>
              <p align="left" class="font-weight-light body-1">
                아래의 깃에서 볼 수 있는 코드는 부분집합, 순열, 중복순열, 조합, 중복조합 Java에서 next_permutation을 지원하지 않기 때문에 가지고 있으면 좋은 것..
              </p>
              <v-row class="article-bottom" style="justify-content:space-between">
                <v-col class="article-info">
                  <p align="left" class="font-weight-light caption mb-0">
                    2020-01-01 · 댓글수 24
                    <v-icon small>mdi-comment-text-outline</v-icon>
                  </p>
                  <p align="left" class="font-weight-medium caption mb-0">
                    #태그 #SSAFY #복붙 #자유게시판 #알고리즘
                  </p>
                </v-col>
                <v-col class="article-icon" cols="2">
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon> mdi-star-outline</v-icon>
                  </v-btn>
                  <v-btn icon color="grey" class="mx-0 px-0 mt-2">
                    <v-icon>mdi-dots-horizontal</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </div>
            <v-col>
              <v-img src="https://picsum.photos/200/200?random=3"></v-img>
            </v-col>
          </v-row>

          <v-col cols="12">
            <v-container class="max-width">
              <v-pagination
                v-model="page"
                :length="5"
                :page="page"
              ></v-pagination>
            </v-container>
          </v-col>
          
        </div>
      </div>
    </section> -->
  </div>
</template>

<script>
export default {
  // name: "BlogHome",
  // data() {
  //   return {
  //     page: 1,
  //   }
  // },
  // components: {},
  // computed: {
  //   userInfo() {
  //     return this.$store.getters.userInfo;
  //   }
  // }
};
</script>

<style></style>
