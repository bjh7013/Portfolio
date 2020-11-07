<template>
  <div>
    <div class="page-wrapper chiller-theme navon">
      <a id="show-sidebar" class="btn btn-sm btn-dark" href="#">
        <i class="fas fa-bars"></i>
      </a>
      <nav id="sidebar" class="sidebar-wrapper">
        <div class="sidebar-content">
          <div class="sidebar-brand">
            <a href="#">메뉴</a>
            <div id="close-sidebar">
              <i class="fas fa-times"></i>
            </div>
          </div>
          <div class="sidebar-header">
            <div class="user-pic">
              <img class="img-responsive img-rounded" src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg" alt="User picture">
            </div>
            <div class="user-info">
              <span class="user-name">
                <strong>닉네임</strong>
                (아이디)
              </span>
              <span>
                <i class="fa fa-trophy"></i>
                <span class="user-role">7zit</span>
              </span>
            </div>
          </div>
          <!-- sidebar-header  -->
          <div class="sidebar-search">
            <div>
              <div class="input-group">
                <input type="text" class="form-control search-menu" placeholder="Search...">
                <div class="input-group-append">
                  <span class="input-group-text">
                    <i class="fa fa-search" aria-hidden="true"></i>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <!-- sidebar-search  -->
          <div class="sidebar-menu">
            <ul>
              <li class="header-menu">
                <span>General</span>
              </li>
              <li class="sidebar-dropdown">
                <a href="#">
                  <i class="fa fa-folder"></i>
                  <span>자유게시판</span>
                </a>
                <div class="sidebar-submenu">
                  <ul>
                    <li>
                      <a href="#">하위게시판1</a>
                    </li>
                    <li>
                      <a href="#">하위게시판2</a>
                    </li>
                    <li>
                      <a href="#">하위게시판3</a>
                    </li>
                  </ul>
                </div>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-code"></i>
                  <span>코드게시판</span>
                </a>
              </li>
              <li class="header-menu">
                <span>Extra<br></span>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-book"></i>
                  <span>작품</span>
                </a>
              </li>
            </ul>
          </div>
          <!-- sidebar-menu  -->
        </div>
        <!-- sidebar-content  -->
        <div class="sidebar-footer">
          <!-- 알림 -->
          <a href="#">
            <i class="fa fa-bell"></i>
            <span class="badge badge-pill badge-warning notification">3</span>
          </a>
          <!-- 쪽지 -->
          <a href="#">
            <i class="fa fa-envelope"></i>
            <span class="badge badge-pill badge-success notification">7</span>
          </a>
          <!-- 관리 -->
          <a href="#">
            <i class="fa fa-cog"></i>
            <span class="badge-sonar"></span>
          </a>
          <!-- 로그아웃 -->
          <a href="#">
            <i class="fa fa-power-off"></i>
          </a>
        </div>
      </nav>
      <!-- sidebar-wrapper  -->
      <main class="page-content">
        <div class="container">
          <h2>Pro Sidebar</h2>
          <hr>
          <div class="row">
            <div class="form-group col-md-12">
              여기는 네브
            </div>
          </div>
          <h5>More templates</h5>
          <hr>
          <div class="row">
            여기는 메인
          </div>
          <hr>

          <footer class="text-center">
            <div class="mb-2">
              <small>
                여기는 푸터
              </small>
            </div>

            <div>
              
            </div>
          </footer>

        </div>

      </main>
      <!-- page-content" -->
    </div>
    <!-- page-wrapper -->
  </div>
</template>

<script>
export default {
  mounted(){
      window.$(".sidebar-dropdown > a").click(function() {
      window.$(".sidebar-submenu").slideUp(200);
      if (
        window.$(this)
          .parent()
          .hasClass("active")
      ) {
        window.$(".sidebar-dropdown").removeClass("active");
        window.$(this)
          .parent()
          .removeClass("active");
      } else {
        window.$(".sidebar-dropdown").removeClass("active");
        window.$(this)
          .next(".sidebar-submenu")
          .slideDown(200);
        window.$(this)
          .parent()
          .addClass("active");
      }
    });
    
    window.$("#close-sidebar").click(function() {
      window.$(".page-wrapper").removeClass("navon");
      window.$(".page-wrapper").addClass("navoff");
    });
    window.$("#show-sidebar").click(function() {
      window.$(".page-wrapper").removeClass("navoff");
      window.$(".page-wrapper").addClass("navon");
    });
  }
}
</script>

<style scoped>
@keyframes swing {
  0% {
    transform: rotate(0deg);
  }
  10% {
    transform: rotate(10deg);
  }
  30% {
    transform: rotate(0deg);
  }
  40% {
    transform: rotate(-10deg);
  }
  50% {
    transform: rotate(0deg);
  }
  60% {
    transform: rotate(5deg);
  }
  70% {
    transform: rotate(0deg);
  }
  80% {
    transform: rotate(-5deg);
  }
  100% {
    transform: rotate(0deg);
  }
}
@keyframes hiding {
  0% {
    left:0px;
  }
  
  100% {
    left:-260px;
  }
}
@keyframes sliding {
  100% {
    left:0px;
  }
  
  0% {
    left:-260px;
  }

}
@keyframes lefting {
  0% {
    padding-left: 280px;
  }
  
  100% {
    padding-left: 40px;
  }
}
@keyframes righting {
  100% {
    padding-left: 280px;
  }
  
  0% {
    padding-left: 40px;
  }
}
@media screen and (min-width: 768px) {
  .page-wrapper.navon .page-content {
    animation: righting ease-in-out 0.5s 1 alternate;
    padding-left: 280px;
  }
  .page-wrapper.navoff .page-content {
  animation: lefting ease-in-out 0.5s 1 alternate;
  padding-left: 40px;
}
}
.sidebar-wrapper .sidebar-menu ul li a:hover i::before {
  display: inline-block;
  animation: swing ease-in-out 0.5s 1 alternate;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown.active > a:after {
  transform: rotate(90deg);
  right: 17px;
}
html {
  -webkit-tap-highlight-color: transparent;
}
body {
  font-size: 0.9rem;
  display: block;
}
body {
  margin: 0;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-weight: 400;
  line-height: 1.5;
  color: #212529;
  text-align: left;
  background-color: #fff;
}
*, *::before, *::after {
  box-sizing: border-box;
  text-align: left;
}

.page-wrapper {
  height: 100vh;
}
.page-wrapper.navoff #sidebar {
  animation: hiding ease-in-out 0.5s 1 alternate;
  left: -260px;
}
.page-wrapper.navon #sidebar {
  animation: sliding ease-in-out 0.5s 1 alternate;
  left: 0px;
}
#show-sidebar {
  position: fixed;
  top: 10px;
  border-radius: 0 4px 4px 0px;
  width: 35px;
  transition-delay: 0.3s;
}

.btn:not(:disabled):not(.disabled) {
  cursor: pointer;
}
.btn-sm, .btn-group-sm > .btn {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem;
  line-height: 1.5;
}
.btn-dark {
  color: #fff;
  background-color: #343a40;
  border-color: #343a40;
}
.btn {
  display: inline-block;
  font-weight: 400;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  user-select: none;
  border: 1px solid transparent;
}
a {
  text-decoration: none;
}

.fa, .fas {
  font-weight: 900;
}
.fa, .far, .fas {
  font-family: Font Awesome\ 5 Free;
}
.fa, .fab, .fal, .far, .fas {
  -webkit-font-smoothing: antialiased;
  display: inline-block;
  font-style: normal;
  font-variant: normal;
  text-rendering: auto;
  line-height: 1;
}
.fa-bars:before {
  content: "\f0c9";
}

.page-wrapper.navoff .sidebar-wrapper {
  left: 0px;
}
.chiller-theme .sidebar-wrapper {
  background: #31353D;
}
.sidebar-wrapper {
  width: 260px;
  height: 100%;
  max-height: 100%;
  position: fixed;
  top: 0;
  z-index: 999;
}
article, aside, figcaption, figure, footer, header, hgroup, main, nav, section {
  display: block;
}

.sidebar-content {
  max-height: calc(100% - 30px);
  height: calc(100% - 30px);
  overflow-y: auto;
  position: relative;
}
.sidebar-wrapper .sidebar-brand {
  padding: 10px 20px;
  display: flex;
  align-items: center;
}
.chiller-theme .sidebar-wrapper .sidebar-header .user-info .user-role, .chiller-theme .sidebar-wrapper .sidebar-header .user-info .user-status, .chiller-theme .sidebar-wrapper .sidebar-search input.search-menu, .chiller-theme .sidebar-wrapper .sidebar-search .input-group-text, .chiller-theme .sidebar-wrapper .sidebar-brand>a, .chiller-theme .sidebar-wrapper .sidebar-menu ul li a, .chiller-theme .sidebar-footer>a {
  color: #818896;
}
.sidebar-wrapper .sidebar-brand > a {
  text-transform: uppercase;
  font-weight: bold;
  flex-grow: 1;
}
.sidebar-wrapper a {
  text-decoration: none;
}

.page-wrapper.chiller-theme.navon #close-sidebar {
  color: #bdbdbd;
}
.sidebar-wrapper .sidebar-brand #close-sidebar {
  cursor: pointer;
  font-size: 20px;
}
.fa-times:before {
  content: "\f00d";
}

.chiller-theme .sidebar-wrapper .sidebar-header, .chiller-theme .sidebar-wrapper .sidebar-search, .chiller-theme .sidebar-wrapper .sidebar-menu {
  border-top: 1px solid #3a3f48;
}
.sidebar-wrapper .sidebar-header {
  padding: 20px;
  overflow: hidden;
}

.sidebar-wrapper .sidebar-header .user-pic {
  float: left;
  width: 60px;
  padding: 2px;
  border-radius: 12px;
  margin-right: 15px;
  overflow: hidden;
}
.sidebar-wrapper .sidebar-header .user-pic img {
  object-fit: cover;
  height: 100%;
  width: 100%;
}
img {
  vertical-align: middle;
  border-style: none;
}

.chiller-theme .sidebar-wrapper .sidebar-menu ul li:hover>a, .chiller-theme .sidebar-wrapper .sidebar-menu .sidebar-dropdown.active>a, .chiller-theme .sidebar-wrapper .sidebar-header .user-info, .chiller-theme .sidebar-wrapper .sidebar-brand>a:hover, .chiller-theme .sidebar-footer>a:hover i {
  color: #b8bfce;
}
.sidebar-wrapper .sidebar-header .user-info {
  float: left;
}
.sidebar-wrapper .sidebar-header .user-info > span {
  display: block;
}
.sidebar-wrapper .sidebar-header .user-info .user-role {
  font-size: 12px;
}
.sidebar-wrapper .sidebar-header .user-info .user-status {
  font-size: 11px;
  margin-top: 4px;
}
.fa-circle:before {
  content: "\f111";
}

.sidebar-wrapper .sidebar-search > div {
  padding: 10px 20px;
}
.input-group {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  align-items: stretch;
  width: 100%;
}
.chiller-theme .sidebar-wrapper .sidebar-menu ul li a i, .chiller-theme .sidebar-wrapper .sidebar-menu .sidebar-dropdown div, .chiller-theme .sidebar-wrapper .sidebar-search input.search-menu, .chiller-theme .sidebar-wrapper .sidebar-search .input-group-text {
  background: #3a3f48;
}
.chiller-theme .sidebar-wrapper .sidebar-search input.search-menu, .chiller-theme .sidebar-wrapper .sidebar-search .input-group-text {
  border-color: transparent;
  box-shadow: none;
}
.input-group > .form-control:not(:last-child), .input-group > .custom-select:not(:last-child) {
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
.input-group > .form-control, .input-group > .custom-select, .input-group > .custom-file {
  position: relative;
  flex: 1 1 auto;
  width: 1%;
  margin-bottom: 0;
}
.form-control {
  display: block;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
button, input {
  overflow: visible;
}
input, button, select, optgroup, textarea {
  margin: 0;
  font-family: inherit;
}
.input-group-append {
  margin-left: -1px;
}
.input-group-prepend, .input-group-append {
  display: flex;
}
.fa-search:before {
  content: "\f002";
}

.sidebar-wrapper .sidebar-menu {
  padding-bottom: 10px;
}
.sidebar-wrapper ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}
ul {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}
li {
  display: list-item;
  text-align: -webkit-match-parent;
}
.sidebar-wrapper ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}
.chiller-theme .sidebar-wrapper .sidebar-menu .header-menu span {
  color: #6c7b88;
}
.sidebar-wrapper .sidebar-menu .header-menu span {
  font-weight: bold;
  font-size: 14px;
  padding: 15px 20px 5px 20px;
  display: inline-block;
}
.sidebar-wrapper .sidebar-menu ul li a {
  display: inline-block;
  width: 100%;
  text-decoration: none;
  position: relative;
  padding: 8px 30px 8px 20px;
}
a:-webkit-any-link {
  cursor: pointer;
}
.sidebar-wrapper .sidebar-menu ul li a i {
  margin-right: 10px;
  font-size: 12px;
  width: 30px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  border-radius: 4px;
}
.fa-tachometer-alt:before {
  content: "\f3fd";
}

.badge-warning {
  color: #212529;
  background-color: #ffc107;
}
.badge-pill {
  padding-right: 0.6em;
  padding-left: 0.6em;
  border-radius: 10rem;
}
.badge {
  display: inline-block;
  padding: 0.25em 0.4em;
  font-size: 75%;
  font-weight: 700;
  line-height: 1;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown > a:after {
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  content: "\f105";
  display: inline-block;
  font-style: normal;
  font-variant: normal;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  text-align: center;
  background: 0 0;
  position: absolute;
  right: 15px;
  top: 14px;
}
.sidebar-wrapper .sidebar-menu .sidebar-submenu {
  display: none;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu ul {
  padding: 5px 0;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu li {
  padding-left: 25px;
  font-size: 13px;
}
.badge-success {
  color: #fff;
  background-color: #28a745;
}
.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu li a:before {
  content: "\f111";
  font-family: "Font Awesome 5 Free";
  font-weight: 400;
  font-style: normal;
  display: inline-block;
  text-align: center;
  text-decoration: none;
  -webkit-font-smoothing: antialiased;
  margin-right: 10px;
  font-size: 8px;
}
.sidebar-wrapper ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}
.fa-book:before {
  content: "\f02d";
}
.fa-folder:before {
  content: "\f07b";
}

.chiller-theme .sidebar-footer {
  background: #3a3f48;
  box-shadow: 0px -1px 5px #282c33;
  border-top: 1px solid #464a52;
}
.sidebar-footer {
  position: absolute;
  width: 100%;
  bottom: 0;
  display: flex;
}
.chiller-theme .sidebar-footer>a:first-child {
  border-left: none;
}
.sidebar-footer > a {
  flex-grow: 1;
  text-align: center;
  height: 30px;
  line-height: 30px;
  position: relative;
}
.fa-bell:before {
  content: "\f0f3";
}
.sidebar-footer > a .notification {
  position: absolute;
  top: 0;
}
.fa-envelope:before {
  content: "\f0e0";
}
.fa-cog:before {
  content: "\f013";
}
.chiller-theme .sidebar-footer>a:last-child {
  border-right: none;
}
.fa-power-off:before {
  content: "\f011";
}

::-webkit-scrollbar {
  width: 5px;
  height: 7px;
}
::-webkit-scrollbar-button {
  width: 0px;
  height: 0px;
}
::-webkit-scrollbar-corner {
  background: transparent;
}
::-webkit-scrollbar-thumb {
  background: #525965;
  border: 0px none #ffffff;
  border-radius: 0px;
}
::-webkit-scrollbar-track {
  background: transparent;
  border: 0px none #ffffff;
  border-radius: 50px;
}
</style>