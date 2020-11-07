<template>
  <div id="NavFooter">
    <footer class="page-footer font-small blue-grey lighten-5">

  <div style="background-color: #D7CCC8; height:110px; position:relative;">
    <div class="container" style="padding-top:0px;">

      <!-- Grid row-->
      <div class="row py-4 d-flex align-items-center">

        <!-- Grid column -->
        <div class="col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0">
          <h6 class="mb-0">기존의 블로그에 당신의 스타일을</h6>
          <br>
          <h6 class="mb-0">한 방울 떨어트려 완성해주세요. SSAZIT</h6>
        </div>
        <!-- Grid column -->

        
        <!-- Grid column -->

      </div>
      <!-- Grid row-->

    </div>
  </div>

  <!-- Footer Links -->
  <div class="container text-center text-md-left" style="padding-top:0px; padding-bottom:0px;">

    <!-- Grid row -->
    <div class="row mt-3 dark-grey-text">

      <!-- Grid column -->
      <div class="col-md-3 col-lg-4 col-xl-3 mb-4">

        <!-- Content -->
        <h6 class="text-uppercase font-weight-bold">Team name</h6>
        <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
        <p>Team_SSAZIT : SSAFY 구미 3기<img id="logo" :src="logo"></p>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4 infos">

        <!-- Links -->
        <h6 class="text-uppercase font-weight-bold">알고리즘</h6>
        <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
        <p>
          <a class="dark-grey-text" href="https://programmers.co.kr/" >프로그래머스</a>
        </p>
        <p>
          <a class="dark-grey-text" href="https://www.acmicpc.net/">백준</a>
        </p>
        <p>
          <a class="dark-grey-text" href="https://swexpertacademy.com/main/main.do">SWEA</a>
        </p>
        <p>
          <a class="dark-grey-text" href="http://www.jungol.co.kr/">JungOl</a>
        </p>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4 infos">

        <!-- Links -->
        <h6 class="text-uppercase font-weight-bold">프로그래밍언어</h6>
        <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
        <p>
          <a class="dark-grey-text" href="https://www.oracle.com/kr/java/">Java</a>
        </p>
        <p>
          <a class="dark-grey-text" href="https://www.python.org/">Python</a>
        </p>
        <p>
          <a class="dark-grey-text" href="https://visualstudio.microsoft.com/ko/vs/features/cplusplus/">C</a>
        </p>
        <p>
          <a class="dark-grey-text" href="https://visualstudio.microsoft.com/ko/vs/features/cplusplus/">C++</a>
        </p>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4 infos">

        <!-- Links -->
        <h6 class="text-uppercase font-weight-bold">문의</h6>
        <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
        <p>
          <i class="fas fa-home mr-3"></i> Gumi, SSAFY Class, KOR</p>
        <p>
          <i class="fas fa-envelope mr-3"></i> bjh7013@gmail.com</p>
        <p>
          <i class="fas fa-print mr-3"></i> + vntmxmqkd@naver.com</p>
        <p>
          <i class="fas fa-phone mr-3"></i> + 82 010 9934 5504</p>

      </div>
      <!-- Grid column -->

    </div>
    <!-- Grid row -->

  </div>
  <!-- Footer Links -->

  <!-- Copyright -->
  <div class="footer-copyright text-center text-black-50" >© 2020 Copyright:
    <a class="dark-grey-text" href="http://i3d104.p.ssafy.io/"> SSAZIT.com</a>
  </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->
  </div>
</template>

<script>
export default {
  name: "NavFooter",
  data() {
    return {
      logo: require("../images/logo4.png"),
    }
  }
};
</script>

<style scoped>
#NavFooter {
  clear: both;
}
#logo {
  position: relative;
  height: 100px;
  top: 20px;
  bottom: 0px;
  left: 5px;
}
.infos > p{
  padding:5px 0px 0px 0px; margin:0px 0px 0px 0px;
}
</style>
