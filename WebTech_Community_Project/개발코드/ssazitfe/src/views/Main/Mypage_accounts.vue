<template>  
  <div class="accounts container col order">
    <div class="row py-3" v-if="userInfo!=null && userInfo.userNo>-1">
      <!-- data가 들어갈 부분 -->
      <div class="col" id="main">
        <v-col cols="11" id="accounts">
          <div id="left">
            <h4><b>프로필 수정</b></h4>
            <p>SSAZIT 대표 프로필과 별명을 수정하실 수 있습니다.</p>
          </div>

          <v-row id="left">
            <v-col cols="10">
              <v-divider></v-divider>
              <v-card :elevation="0">
                <v-row>
                  <v-col cols="2">
                    <v-card :elevation="0" height="170px">
                      <b>프로필 사진</b>
                    </v-card>
                  </v-col>
                  <v-col>
                    <v-card :elevation="0" height="170px">
                      <div class="profile_photo">
                        <img class="profile-image" :src="userImage" width="150px" height="150px">
                      </div>
                      <div :class="{ active: isActive }"> 
                        <input type="file" round class="change-profile-image" @change="onFileChange" ref="fileupload"> 
                        <button v-on:click="imgUpdateSubmit()">적용</button>&nbsp;
                        <button v-on:click="imgUpdateCancel()">취소</button>
                      </div>
                      <div id="profile_btn" :class="{ active: !isActive }">
                        <v-btn text small v-on:click="toggle"><v-icon small>mdi-pencil</v-icon>수정</v-btn>
                        <v-btn text small><v-icon small>mdi-delete</v-icon>삭제</v-btn>
                      </div>
                    </v-card>
                  </v-col>
                </v-row>
              </v-card>
              <v-divider></v-divider>
              <v-card :elevation="0">
                <v-row>
                  <v-col cols="2">
                    <v-card :elevation="0" height="30px">
                      <b>별명</b>
                    </v-card>
                  </v-col>
                  <v-col>
                    <v-card :elevation="0" height="30px">
                      <div :class="{ active_nickname: !isActive_nickname }">
                        <p>
                          <input type="text" name="nickname" v-model="userInfo.nickName">
                          <v-btn text v-on:click="toggle_nickname"><v-icon small>mdi-pencil</v-icon>수정</v-btn>
                        </p>      
                      </div>
                      <div :class="{ active_nickname: isActive_nickname }">
                        <p>
                          <input type="text" name="nickname" v-model="userid" placeholder="닉네임 입력">
                          <button v-on:click="toggle_nickname_complete">수정완료</button>
                          <button v-on:click="toggle_nickname_cancel">취소</button>
                        </p>      
                      </div>
                    </v-card>
                  </v-col>
                </v-row>
              </v-card>
              <v-divider></v-divider>
              <!-- <v-card :elevation="0">
                <v-row>
                  <v-col cols="2">
                    <v-card :elevation="0" height="30px">
                      <b>랭킹</b>
                    </v-card>
                  </v-col>
                  <v-col>
                    <v-card :elevation="0" height="30px">
                      <div class="container">    
                        <p>랭킹이미지 4ZIT 1위</p>
                        <div class="progress progress-striped">
                          <div class="progress-bar" style="width:80%" >
                          </div>                       
                        </div> 
                        <p>8000/10000</p>
                        <p>{{userInfo.userExp}}/40</p>
                      </div> 
                    </v-card>
                  </v-col>
                </v-row>
              </v-card> -->
            </v-col>
          </v-row>
        </v-col>
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
import axios from "axios"
export default {
  name : "accounts",
  components: {
      
  },
  created() {
    axios.get('/user/mypage').then((Response)=>{
      this.$store.dispatch('Re_userInfo',Response.data)
      // console.log(Response.data);
      // console.log(Response.data.profileImg);
      // console.log("1");
      // console.log(this.userImage);
      // console.log(this.serverURL);
      this.userImage = this.serverURL + Response.data.profileImg;
    })

    // getUserInfo() {
    //   axios.get('/user/mypage').then((Response)=>{
    //     this.$store.dispatch('Re_userInfo',Response.data)
    //     console.log(Response.data);
    //     // this.userImage = 'http://192.168.1.6:9999/' + Response.data.userInfo.profileImg;
    //   }).catch(function(){
    //     console.log('FAILURE!!');
    //   })
    // },
  },
  computed:{
    userInfo(){
      return this.$store.getters.userInfo;
    },
    serverURL(){
      return this.$store.getters.serverURL;
    }
  },
  data(){
    return{
      ssazit : require('../images/ssazit.jpg'),
      file: '',
      userImage: '',
      isActive: true,
      isActive_nickname: true,
      userid : ''
    }
  },
  methods: {
    movelogin(){
      alert("Hi")
      this.$router.push('/login')
    },
    onFileChange(e) { 
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) { 
        return;
      } 
      // console.log("filechange!");
      // console.log(this.userInfo);
      this.file = files[0];
      // console.log(files[0]);
      this.createImage(files[0]);
    }, 
    createImage(file) { 
      var reader = new FileReader();
      var vm = this;
      reader.onload = (e) => { 
        vm.userImage = e.target.result;
      } 
      // console.log("createImage!!");
      reader.readAsDataURL(file);
    },
    removeImage: function (e) {   // eslint-disable-line no-unused-vars
      // console.log("removeImage");
      this.file = '';
      this.userImage = this.serverURL + this.userInfo.profileImg;
      // console.log("removeImage : done");
    }, 

    sendImage() {
      // console.log("sendImage");
      let form = new FormData();
      form.append("file",this.file);
      axios.post(`/user/upload`, form
      )
      .then((Response)=>{
        // console.log("####");
        // console.log(Response.data);
        this.$store.dispatch('Re_userInfo',Response.data);
        // console.log('sendImage : SUCCESS!!');
      })
      .catch(function(){
        // console.log('sendImage :FAILURE!!');
      });
    },
    ckSendImage(){
        // console.log("cksendImage");
        var ck = confirm("Are you sure?");
        if(ck){
          this.sendImage();
          this.toggle();
          // console.log("cksendImage : yes!");
        }
        else{
          // console.log("cksendImage : cancel");
        }
    },
    toggle: function () {
      this.isActive = !this.isActive;
      this.$refs.fileupload.value=null;
    },
    toggle_nickname() {
      this.isActive_nickname = !this.isActive_nickname;
    },
    toggle_nickname_complete(){
      axios.put('/user/nickname',{
          nickName : this.userid,
      }).then(()=>{
        alert("닉네임 변경이 완료되었습니다");
        this.isActive_nickname = !this.isActive_nickname;
        this.$router.go();
      }).catch((error)=>{
        alert(error);
      })
    },
    toggle_nickname_cancel() {
      this.isActive_nickname = !this.isActive_nickname;
      this.userid='';
    },
    imgUpdateSubmit(){
      // console.log("적용");
      this.ckSendImage();
    },
    imgUpdateCancel(){
      // console.log("초기화");
      this.removeImage();
      // console.log(this.userInfo.profileImg);
      this.toggle();
    }

  } 
}
</script>

<style>
li {
  display: inline-block;
  margin: 0 10px;
}
#accounts {
  text-align: left;
}
#level {
  width: 100px;
  height: 80px;
}
#left {
  float: left;
  width: 100%;
}
#right {
  float: right;
  width: 100%;
}
#profile_btn {
  margin: 0 0 0 8px;
}
.container {
  margin: 100px auto;
  width: 500px;
  text-align: center;
}

.progress {
  padding: 6px;
  background: rgba(0, 0, 0, 0.25);
  border-radius: 6px;
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25), 0 1px rgba(255, 255, 255, 0.08);
}

.progress-bar {	
  height: 18px;
	background-color: #ee303c;  
  border-radius: 4px; 
  transition: 0.4s linear;  
  transition-property: width, background-color;    
}

.progress-striped .progress-bar { 	
  background-color: #FCBC51; 
  width: 100%; 
  background-image: linear-gradient(
    45deg, rgb(252,163,17) 25%, 
    transparent 25%, transparent 50%, 
    rgb(252,163,17) 50%, rgb(252,163,17) 75%,
    transparent 75%, transparent); 
  animation: progressAnimationStrike 6s;
}

.active {
  display : none;
}

.active_nickname {
  display : none;
}

@keyframes progressAnimationStrike {
  from { width: 0 }
  to   { width: 100% }
}

.tbody {
  display: table-row-group;
  border-color: inherit;
}
</style>