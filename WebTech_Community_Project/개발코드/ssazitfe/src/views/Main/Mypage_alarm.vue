<template style="background-color:#EEEE;">
  <div id="Mypagealarm">
    <div v-if="userInfo!=null && userInfo.userNo>-1">
      <v-container class="alarm-contents mx-0 py-0" style="height:100%; max-width:100%;">
        <v-row style="height:100%; background-color:#EEEE; border-radius:30px; margin-right:25px;">
          <!-- Sidebar -->
          <!-- 내용 -->
          <v-col class="py-0">
            <div class="v-timeline v-timeline--dense theme--light">
              <!-- 큰 도트 - 날짜 -->
              <v-row>
                <div style="width:15px;"></div>
                <div class="v-timeline-item first-theme--light">
                  <div class="v-timeline-item__divider">
                    <div class="v-timeline-item__dot v-timeline-item__dot">
                      <div class="v-timeline-item__first-inner-dot"></div>
                    </div>
                  </div>
                </div>
                
                <v-spacer></v-spacer>
              </v-row>

              <div v-for="noti in notiList" :key="noti.notiNo" class="v-timeline-item theme--light">
                <!-- 알림창1 -->
                <div class="v-timeline-item__body">
                  <div class="row elevation-2 v-card v-sheet theme--light py-1">
                    <!-- 관련 유저 프사 -->
                    <div class="my-auto mx-5" 
                      style="width:52px; 
                      height:52px; 
                      float: left;
                      align-items: center;
                      justify-content: center;"
                    >
                      <img 
                        cols="1" 
                        :src='serverURL + noti.profileImg'
                        style="object-fit: cover;
                        border-radius:4px;  
                        height: 100%;
                        width: 100%;
                        vertical-align: middle;"
                        >
                    </div>
                    <!-- 알림 내용 -->
                    <!-- <div class="v-card__title headline">Lorem ipsum</div> -->
                    <v-col class="v-card__text px-0" align="left">
                      <div class="mb-1">
                        <!-- 분류 -->
                        <template v-if="noti.notiType == 'like'">
                          <span class="pr-3" style="color:teal;">
                            좋아요
                          </span>
                          <span class="pr-3"><b>{{noti.nickName}}</b>님이 내 글에 좋아요를 눌렀습니다.</span>
                          <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${userInfo.userId}/articledetail/${noti.articleNo}`)">
                            <!-- 알림 관련 페이지 -->
                            <span class="pr-3" style="color:grey;">블로그 게시글</span>
                            <span class="pr-3">|</span>
                            <!-- 관련 게시글 제목 -->
                            <span class="pr-3 link">{{noti.articleTitle}}</span>
                            <span class="pr-3">|</span>
                            <!-- 알림 경과 시간 -->
                            <span>{{noti.notiDate}}</span>
                          </div>
                        </template>

                        <template v-if="noti.notiType == 'comment'">
                          <span class="pr-3" style="color:teal;">
                            댓글
                          </span>
                          <template v-if="noti.articleNo > 0">
                            <span class="pr-3"><b>{{noti.nickName}}</b>님이 내 글에 댓글을 달았습니다.</span>
                            <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${userInfo.userId}/articledetail/${noti.articleNo}`)">
                              <!-- 알림 관련 페이지 -->
                              <span class="pr-3" style="color:grey;">블로그 게시글</span>
                              <span class="pr-3">|</span>
                              <!-- 관련 게시글 제목 -->
                              <span class="pr-3 link">{{noti.articleTitle}}</span>
                              <span class="pr-3">|</span>
                              <!-- 알림 경과 시간 -->
                              <span>{{noti.notiDate}}</span>
                            </div>
                          </template>

                          <template v-if="noti.bookNo > 0">
                            <span class="pr-3"><b>{{noti.nickName}}</b>님이 내 책에 댓글을 달았습니다.</span>
                            <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${userInfo.userId}/bookdetail/${noti.bookNo}`)">
                              <!-- 알림 관련 페이지 -->
                              <span class="pr-3" style="color:grey;">내 책</span>
                              <span class="pr-3">|</span>
                              <!-- 관련 게시글 제목 -->
                              <span class="pr-3 link">{{noti.bookTitle}}</span>
                              <span class="pr-3">|</span>
                              <!-- 알림 경과 시간 -->
                              <span>{{noti.notiDate}}</span>
                            </div>
                          </template>
                        </template>
                        <template v-if="noti.notiType == 'favorite'">
                          <span v-if="noti.notiType == 'favorite'" class="pr-3" style="color:teal;">
                            관심글
                          </span>
                          <template v-if="noti.articleNo > 0">
                            <span class="pr-3"><b>{{noti.nickName}}</b>님이 내 글을 관심글로 등록했습니다.</span>
                            <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${userInfo.userId}/articledetail/${noti.articleNo}`)">
                              <!-- 알림 관련 페이지 -->
                              <span class="pr-3" style="color:grey;">블로그 게시글</span>
                              <span class="pr-3">|</span>
                              <!-- 관련 게시글 제목 -->
                              <span class="pr-3 link">{{noti.articleTitle}}</span>
                              <span class="pr-3">|</span>
                              <!-- 알림 경과 시간 -->
                              <span>{{noti.notiDate}}</span>
                            </div>
                          </template>
                          <template v-if="noti.bookNo > 0">
                            <span class="pr-3"><b>{{noti.nickName}}</b>님이 내 책을 관심책으로 등록했습니다.</span>
                            <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${userInfo.userId}/bookdetail/${noti.bookNo}`)">
                              <!-- 알림 관련 페이지 -->
                              <span class="pr-3" style="color:grey;">내 책</span>
                              <span class="pr-3">|</span>
                              <!-- 관련 게시글 제목 -->
                              <span class="pr-3 link">{{noti.bookTitle}}</span>
                              <span class="pr-3">|</span>
                              <!-- 알림 경과 시간 -->
                              <span>{{noti.notiDate}}</span>
                            </div>
                          </template>
                        </template>
                        <template v-if="noti.notiType == 'share'">
                          <span class="pr-3" style="color:teal;">
                            공유
                          </span>
                          <span class="pr-3"><b>{{noti.nickName}}</b>님이 내 글을 퍼갔습니다.</span>
                          <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${userInfo.userId}/articledetail/${noti.articleNo}`)">
                            <!-- 알림 관련 페이지 -->
                            <span class="pr-3" style="color:grey;">블로그 게시글</span>
                            <span class="pr-3">|</span>
                            <!-- 관련 게시글 제목 -->
                            <span class="pr-3 link">{{noti.articleTitle}}</span>
                            <span class="pr-3">|</span>
                            <!-- 알림 경과 시간 -->
                            <span>{{noti.notiDate}}</span>
                          </div>
                        </template>
                        <template v-if="noti.notiType == 'pick'">
                          <span class="pr-3" style="color:teal;">
                            댓글 채택
                          </span>
                          <span class="pr-3"><b>{{noti.nickName}}</b>님이 내 댓글을 채택했습니다.</span>
                          <template v-if="noti.articleNo > 0">
                            <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${noti.userId}/articledetail/${noti.articleNo}`)">
                              <!-- 알림 관련 페이지 -->
                              <span class="pr-3" style="color:grey;">블로그 게시글</span>
                              <span class="pr-3">|</span>
                              <!-- 관련 게시글 제목 -->
                              <span class="pr-3 link">{{noti.articleTitle}}</span>
                              <span class="pr-3">|</span>
                              <!-- 알림 경과 시간 -->
                              <span>{{noti.notiDate}}</span>
                            </div>
                          </template>
                          <template v-if="noti.bookNo > 0">
                            <div style="color:rgba(187, 187, 187, 0.933);" @click="$router.push(`/bloghome/${noti.userId}/bookdetail/${noti.bookNo}`)">
                              <!-- 알림 관련 페이지 -->
                              <span class="pr-3" style="color:grey;">책</span>
                              <span class="pr-3">|</span>
                              <!-- 관련 게시글 제목 -->
                              <span class="pr-3 link">{{noti.bookTitle}}</span>
                              <span class="pr-3">|</span>
                              <!-- 알림 경과 시간 -->
                              <span>{{noti.notiDate}}</span>
                            </div>
                          </template>
                        </template>
                        <template v-if="noti.notiType == 'follow'">
                          <span class="pr-3" style="color:teal;">
                            팔로우
                          </span>
                          <span class="pr-3"><b>{{noti.nickName}}</b>님이 나를 팔로우했습니다.</span>
                        </template>
                        <template v-if="noti.notiType == 'message'">
                          <span class="pr-3" style="color:teal;">
                            메시지
                          </span>                        
                          <span class="pr-3"><b>{{noti.nickName}}</b>님이 메시지를 보냈습니다.</span>
                        </template>
                        <!-- 알림 내용 -->
                        <!-- 유저 닉네임 -->
                      </div>
                    </v-col>
                    <!-- 알림창 끄기 -->
                    <v-btn cols="1" icon value="삭제" color="white" class="text-decoration-none m-1" @click="deleteNoti(noti.notiNo)">
                      <v-icon color="grey">mdi-close</v-icon>
                    </v-btn>
                  </div>
                </div>
                <div class="v-timeline-item__divider">
                  <div class="v-timeline-item__dot v-timeline-item__dot--small">
                    <div class="v-timeline-item__inner-dot"></div>
                  </div>
                </div>
              </div>

              <!-- 작은 도트 - 끝 -->
              <v-row>
                <div style="width:15px;"></div>
                <div class="v-timeline-item__divider">
                  <div class="v-timeline-item__dot v-timeline-item__dot--small">
                    <div class="v-timeline-item__inner-dot"></div>
                  </div>
                </div>
                <v-spacer></v-spacer>
              </v-row>
              <div style="height:10px;"></div>
            </div>
          </v-col>
        </v-row>

      </v-container>
      
      <!-- Footer -->
      <!-- <v-footer style="position:relative; bottom:-1px; height:50px; width:100%; z-index:10;">
        <div style="color:grey; font-size:12px">기존의 블로그에 당신의 스타일을 한 방울 떨어트려 완성해주세요. SSAZIT</div>
        <v-spacer></v-spacer>
        <div>&copy; {{ new Date().getFullYear() }}.{{ new Date().getMonth() }}</div>
      </v-footer> -->
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
import axios from 'axios';

export default {
  name: "Mypage_alarm",
  components : {
  },
  data() {
    return {
      notiList: '',
    }
  },
  methods:{
    deleteNoti(notiNo){
      axios.delete(`/user/mynoti/${notiNo}`)
      .then(() => {
        this.getMyNotiList();
      })
    },
    getMyNotiList(){
      axios.get("/user/mynoti")
      .then(({data}) => {
        this.notiList = data;
      })
    }
  },
  created(){
    this.getMyNotiList();
  },
  computed: {
    serverURL(){
      return this.$store.getters.serverURL  
    },
    userInfo(){
      return this.$store.getters.userInfo;
    }
  }
}
</script>

<style scoped>
.link{
  cursor: pointer;
}
*, :after, :before {
    background-repeat: no-repeat;
    box-sizing: inherit;
}
.v-sheet.v-app-bar.v-toolbar:not(.v-sheet--outlined) {
  box-shadow: none !important;
}
.v-sheet.v-toolbar:not(.v-sheet--outlined) {
  box-shadow: none !important;
}
.v-application--is-ltr .v-timeline--dense:not(.v-timeline--reverse):before, .v-application--is-rtl .v-timeline--reverse.v-timeline--dense:before {
  right: auto;
  left: 47px;
}
.theme--light.v-timeline:before {
  background: rgba(0,0,0,.12);
}
.v-timeline:before {
  bottom: 0;
  content: "";
  height: 95%;
  position: absolute;
  top: 30px;
  width: 2px;
}
:after, :before {
  text-decoration: inherit;
  vertical-align: inherit;
}
.v-timeline--dense .v-timeline-item {
  flex-direction: row-reverse !important;
}
.v-timeline-item {
  display: flex;
  padding-bottom: 24px;
}
.v-timeline--dense .v-timeline-item__body {
  max-width: calc(100% - 96px);
}
.v-timeline-item__body {
  position: relative;
  height: 100%;
  flex: 1 1 auto;
}
.v-sheet.v-card {
    border-radius: 10px;
}
.theme--light.v-card {
    background-color: #fff;
    color: rgba(0,0,0,.87);
}
.v-application .elevation-2 {
    box-shadow: 0 3px 1px -2px rgba(0,0,0,.2),0 2px 2px 0 rgba(0,0,0,.14),0 1px 5px 0 rgba(0,0,0,.12)!important;
}
.v-application--is-ltr .v-timeline--dense .v-timeline-item .v-timeline-item__body .v-card:after, .v-application--is-ltr .v-timeline--dense .v-timeline-item .v-timeline-item__body>.v-card:before {
    transform: rotate(0);
    left: -10px;
    right: auto;
}
.theme--light.v-timeline .v-timeline-item .v-card:before {
    border-right-color: rgba(0,0,0,.12);
}
.v-timeline-item__body>.v-card:not(.v-card--flat):before {
    top: calc(50% - 8px);
}
.v-timeline-item__body>.v-card:not(.v-card--flat):after, .v-timeline-item__body>.v-card:not(.v-card--flat):before {
    content: "";
    position: absolute;
    border-top: 10px solid transparent;
    border-bottom: 10px solid transparent;
    border-right: 10px solid white !important;
}
.v-card>.v-card__progress+:not(.v-btn):not(.v-chip), .v-card>:first-child:not(.v-btn):not(.v-chip) {
    border-top-left-radius: inherit;
    border-top-right-radius: inherit;
}
.v-application .headline, .v-application .title {
    line-height: 2rem;
    font-family: Roboto,sans-serif!important;
}
.v-application .headline {
    font-size: 1.5rem!important;
    font-weight: 400;
    letter-spacing: normal!important;
}
.v-card__title {
    align-items: center;
    display: flex;
    flex-wrap: wrap;
    word-break: break-all;
}
.theme--light.v-card .v-card__subtitle, .theme--light.v-card>.v-card__text {
    color: rgba(0,0,0,.6);
}
.v-card__title+.v-card__subtitle, .v-card__title+.v-card__text {
    padding-top: 0;
}
.v-card__text {
    width: 100%;
}
.v-card__subtitle, .v-card__text, .v-card__title {
    padding: 16px;
}
.v-card__subtitle, .v-card__text {
    font-size: .875rem;
    font-weight: 400;
    line-height: 1.375rem;
    letter-spacing: .0071428571em;
}
.v-application--is-ltr .v-timeline--dense .v-timeline-item .v-timeline-item__body .v-card:after, .v-application--is-ltr .v-timeline--dense .v-timeline-item .v-timeline-item__body>.v-card:before {
    transform: rotate(0);
    left: -10px;
    right: auto;
}
.v-timeline-item__body>.v-card:not(.v-card--flat):after {
    border-right-color: inherit;
}
.v-timeline-item__body>.v-card:not(.v-card--flat):after, .v-timeline-item__body>.v-card:not(.v-card--flat):before {
    content: "";
    position: absolute;
    border-top: 10px solid transparent;
    border-bottom: 10px solid transparent;
    border-right: 10px solid #000;
    top: calc(50% - 10px);
}
.v-timeline-item__divider {
    position: relative;
    min-width: 96px;
    display: flex;
    align-items: center;
    justify-content: center;
}
.theme--light.v-timeline .v-timeline-item__dot {
    background: lightsteelblue;
}
.v-timeline-item__dot--small {
    height: 16px;
    left: calc(50% - 12px);
    width: 16px;
}
.v-timeline-item__dot {
    z-index: 2;
    border-radius: 50%;
    box-shadow: 0 2px 1px -1px rgba(0,0,0,.2), 0 1px 1px 0 rgba(0,0,0,.14), 0 1px 3px 0 rgba(0,0,0,.12);
}
.v-timeline-item__inner-dot {
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 10px !important;
    margin: 3px;
    width: 10px !important;
    background-color: white;
}
.v-timeline-item__first-inner-dot {
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 30px;
    margin: 4px;
    width: 30px;
    /* background-color: white; */
    border: 1px solid white;
}
.v-sheet.v-card:not(.v-sheet--outlined) {
  box-shadow: 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}
</style>