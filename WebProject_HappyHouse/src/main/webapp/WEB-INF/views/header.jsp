<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<head>

<style>

#navheader {
	list-style-type: none;
	height: 30px;
	margin: 0;
	padding: 5px 1px;
	z-index: 4;
}

#navheader li {
	float: left;
	position: relative;
	margin: 0px 10px;
	padding: 0;
}

#navheader li a {
	display: block;
	font-family: "굴림";
	font-weight: bold;
	font-size: 18px;
	padding: 7px 8px;
	margin: 0;
	margin: 0;
	text-decoration: none;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.3);
}

#navheader li:hover>a {
	color: white;
}

#navheader ul {
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	left: 0;
	top: 40px;
	width: 105px;
	background: #7fa4ea;
	text-align: center;
	opacity: 0;
}

#navheader ul li {
	float: none;
	margin: 0;
	padding: 0;
	font-size: 10px;
}

#navheader ul li:hover a {
	background: #5189f3;
	font-weight: bold;
}

#navheader li:hover ul {
	opacity: 1;
}

#navheader li:ohver ul li {
	height: 20px;
	overflow: visible;
	padding: 0;
}
#happyimg {
	height:200px;
	border-style : none;

}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<header>
	
	<div id="header" class="navbar">
		<a href="${root}/"><img id = "happyimg" src="${root}/static/happyhouse.png"></img></a>

		<div id="menu_bar">
			<ul id="navheader">
				<li><a href="${root}/api/news/list.do">News</a>&emsp;
				<li><a href="${root }/api/map/mapshow">지도 검색</a>&emsp;
				<li><a href="${root}/api/house/houselist/search">아파트 매매 정보</a>&emsp;
					<c:if test="${userinfo!=null}">
						&emsp;
						<li><a href="${root}/api/house/gocart/${userinfo.userid}">찜한 매물</a>
					</c:if>
				<li><a href="#">게시판</a>&emsp;
					<ul>
						<li><a href="${root}/api/board/notice.do">공지사항</a></li>
						<li><a href="${root}/static/index.html">QnA</a></li>
						<!-- <li><a href="http://localhost:9999">QnA</a></li> -->
					</ul></li>
			</ul>
			<div id="header_menu"></div>
		</div>
		<div id="menu_login">
			<c:if test="${userinfo==null}">
				<a href="${root}/api/user/userlogin.do">로그인 </a>
			</c:if>
			<c:if test="${userinfo!=null}">

				<div style="font-size: 13px;">
					<a href="${root}/api/user/usermenu.do"><strong>${userinfo.username}(${userinfo.userid})</strong></a>님
					환영합니다.<br> <a id="menu_logout"
						href="${root}/api/user/userlogout.do" style="font-size: 13px;">로그아웃</a><br>
				</div>
			</c:if>
		</div>
	</div>

</header>