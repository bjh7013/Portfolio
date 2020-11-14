<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/init/el.jsp"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SSAFY-회원정보수정</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#registerBtn").click(function() {
		
		if($("#username").val() == "") {
			alert("이름 입력!!!");
			return;
		} else if($("#userpw").val() == "") {
			alert("비밀번호 입력!!!");
			return;
		} else {
			$("#memberform").attr("action", "${root}/api/user/usermodify.do").submit();
		}
	});
	 
	$('#zipcode').focusin(function() {
		$('#zipModal').modal();
	});
});
</script>
<%@ include file="/WEB-INF/views/init/stylesheet.jsp" %>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
<c:if test="${modifys!=null}">
회원정보 수정에 성공했습니다<br>
<a href="${root}/api/base/" class="btn btn-primary">홈</a>
</c:if>

<c:if test="${modifys==null}">
<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<form id="memberform" method="post" action="">
		<input type="hidden" name="act" id="mvjoin" value="">
			<div class="form-group" align="left">
				<label for="name">아이디</label>
				<input type="text" value="${userinfo.userid}" class="form-control" id="userid" name="userid" placeholder="" readonly>
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="userpw" name="userpw" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">이름</label>
				<input type="text" value="${userinfo.username}" class="form-control" id="username" name="username" placeholder="">
			</div>
			<input type="hidden" value="${userinfo.usertype}" class="form-control" id="usertype" name="usertype" placeholder="">
			<div class="form-group" align="left">
				<label for="">이메일</label><br>
				<input type="text" value="${userinfo.useraddr}" class="form-control" id="useraddr" name="useraddr" placeholder="" size="25">
			</div>
			<div class="form-group" align="left">
				<label for="">연락처</label><br>
				<input type="text" value="${userinfo.userphone}" class="form-control" id="userphone" name="userphone" placeholder="" size="25">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호 찾기 질문</label><br>
				<input type="text" value="${userinfo.userquestion}" class="form-control" id="userquestion" name="userquestion" placeholder="" size="25">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호 찾기 정답</label><br>
				<input type="text" value="${userinfo.usersol}" class="form-control" id="usersol" name="usersol" placeholder="" size="25">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn">수정</button>
				<button type="reset" class="btn btn-warning">초기화</button>
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