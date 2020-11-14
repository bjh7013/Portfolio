<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <title>공지사항 작성 성공</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>
<body>

    <div class="container" align="center">
    <div class="col-lg-6">
	<div class="jumbotron">
	<h1>공지사항 작성 성공</h1>      
	</div>  
	<p><a href="${root}/notice.do?act=noticelist">공지사항 목록</a></p>
	</div>

	<!-- "/notice/list.jsp" -->
</div>
</body>
</html>
