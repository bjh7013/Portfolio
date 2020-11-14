<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/views/init/el.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SSAFY-회원리스트</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<%@ include file="/WEB-INF/views/init/stylesheet.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
	<h2 align="left">회원 정보</h2><br>
	<c:if test="${searchsdata!=null}">
<table class="table table-condensed" style="height:500px; overflow:scroll;">
	<thead><tr><th>아이디</th><th>닉네임</th><th>이메일</th><th>전화번호</th><th>회원등급</th></tr></thead>
		<c:forEach var="user" items="${searchsdata}">
	<tr">
			<td><c:out value="${user.userid}"></c:out></td>
			<td><c:out value="${user.username}"></c:out></td>
			<td><c:out value="${user.useraddr}"></c:out></td>
			<td><c:out value="${user.userphone}"></c:out></td>
			<td><c:out value="${user.usertype}"></c:out></td>
	</tr>
		</c:forEach>
	</table>
		<a href="${root}/api/user/usermenu.do" class="btn btn-secondary">뒤로</a>
	</c:if>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>