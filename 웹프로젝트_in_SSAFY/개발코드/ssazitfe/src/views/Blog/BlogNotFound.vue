<template>
  <div class='pageBlogNotFound'>
      <div>
        <div style="height:100px;"></div>
        <h2 align="center">존재하지 않는 블로그입니다.</h2>
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
</template>

<script>
export default {
  name: "BlogNotFound",

}
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Nunito:400,700,300);
.pageBlogNotFound {
  margin: 0 auto;
  width: 920px;
}
.contentBlogNotFound {
  width: 33.33%;
  display: inline-block;
  margin: 0 auto;
  position: relative;
  height: 500px;
  max-width: 300px;
}
.circleBlogNotFound_inner__layer {
  width: 600px;
  height: 200px;
  transition: all 0.4s;
  position: absolute;
  top: 0;
  left: -200px;
}
.circleBlogNotFound_inner__layer img {
  width: 100%;
  bottom: 0;
}
.circleBlogNotFound {
  position: absolute;
  left: 0;
  right: 0;
  margin: auto;
  top: 50%;
  width: 200px;
  transform: translateY(-50%);
  transition: all 0.5s;
  cursor: pointer;
}
.circleBlogNotFound:hover .circleBlogNotFound_shine {
  top: 330px;
  left: -200px;
}
.circleBlogNotFound_shine {
  background: white;
  width: 600px;
  transition: 0.3s;
  height: 200px;
  opacity: 0.2;
  top: -10px;
  left: -90px;
  transform: rotate(45deg);
  position: absolute;
  z-index: 2;
}
.circleBlogNotFound:hover h2, .circleBlogNotFound:hover h3 {
  opacity: 1;
  top: -36px;
}
.circleBlogNotFound:hover .contentBlogNotFound_shadow {
  transform: scale(1.1);
  top: -22px;
}
.circleBlogNotFound:hover h3 {
  transition: all 0.2s 0.04s;
}
.circleBlogNotFound:hover h2 {
  transition: all 0.2s;
}
.circleBlogNotFound .circleBlogNotFound_inner__layer:nth-of-type(1) {
  top: 0px;
  left: 0px;
}
.circleBlogNotFound .circleBlogNotFound_inner__layer:nth-of-type(2) {
  top: 0px;
  left: -210px;
}
.circleBlogNotFound .circleBlogNotFound_inner__layer:nth-of-type(3) {
  top: 0px;
  left: -440px;
}
.circleBlogNotFound_title {
  text-align: center;
}
.circleBlogNotFound_title h2, .circleBlogNotFound_title h3 {
  opacity: 0;
  color: #4A7479;
  margin: 0;
  transition: all 0.2s 0.04s;
  position: relative;
  top: -10px;
}
.circleBlogNotFound_title h3 {
  transition: all 0.2s;
  color: #B0D5D6;
  font-size: 15px;
}
.circleBlogNotFound_inner {
  border-radius: 200px;
  background: #B0D5D6;
  overflow: hidden;
  margin: auto;
  width: 200px;
  z-index: 1;
  transition: all 0.3s;
  height: 200px;
  position: relative;
}
.circleBlogNotFound_inner:hover {
  transform: scale(1.1);
}
.circleBlogNotFound_inner:hover .circleBlogNotFound_inner__layer:nth-of-type(1) {
  left: -80px;
  transition: all 4s linear;
}
.circleBlogNotFound_inner:hover .circleBlogNotFound_inner__layer:nth-of-type(2) {
  left: -400px;
  transition: all 4s linear;
}
.circleBlogNotFound_inner:hover .circleBlogNotFound_inner__layer:nth-of-type(3) {
  left: -140px;
  transition: all 4s linear;
}
.contentBlogNotFound_shadow {
  width: 200px;
  box-shadow: 0px 31px 19px -2px #E0E8F9;
  height: 20px;
  border-radius: 70%;
  position: relative;
  top: -44px;
  transition: all 0.3s;
  z-index: 0;
}

</style>