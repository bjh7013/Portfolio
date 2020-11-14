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
<title>SSAFY-회원검색</title>
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
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#registerBtn").click(
						function() {

							if ($("#userid").val() == "") {
								alert("아이디 입력!!!");
								return;
							} else {
								$("#memberform").attr("action",
										"${root}/api/user/usersearch.do")
										.submit();
							}
						});

				$('#zipcode').focusin(function() {
					$('#zipModal').modal();
				});
			});
</script>
<%@ include file="/WEB-INF/views/init/stylesheet.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
		<h2 align="left">회원 정보</h2>
		<br>
		<c:if test="${searchs!=null}">
			<table class="table table-condensed"
				style="height: 500px; overflow: scroll;">
				<thead>
					<tr>
						<th>아이디</th>
						<th>닉네임</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>회원등급</th>
					</tr>
				</thead>
				<tr>
					<td><c:out value="${searchs.userid}"></c:out></td>
					<td><c:out value="${searchs.username}"></c:out></td>
					<td><c:out value="${searchs.useraddr}"></c:out></td>
					<td><c:out value="${searchs.userphone}"></c:out></td>
					<td><c:out value="${searchs.usertype}"></c:out></td>
				</tr>
			</table>
			<a href="${root}/api/user/usermenu.do" class="btn btn-secondary">뒤로</a>
		</c:if>
		<c:if test="${searchs==null}">
			<c:if test="${noneuser!=null}">

			</c:if>
			<div class="container" align="center">
				<div class="col-lg-6" align="center">
					<form id="memberform" method="post" action="">
						<input type="hidden" name="act" id="mvjoin" value="">
						<div class="form-group" align="left"
							style="height: 100px; margin-top: 10%;">
							<label for="name">아이디</label> <input type="text"
								class="form-control" id="userid" name="userid" placeholder=""
								onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');"
								maxlength="20">
						</div>
						<div style="color: red; margin-bottom: 20px;]">${noneuser}</div>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-primary" id="registerBtn">찾기</button>
							<a href="${root}/api/user/usermenu.do" class="btn btn-secondary">뒤로</a>
						</div>
					</form>
				</div>
			</div>
		</c:if>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>