<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="/WEB-INF/views/init/el.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SSAFY-회원가입</title>
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
			alert("이름을 입력 해주세요");
			return;
		} else if($("#userid").val() == "") {
			alert("아이디를 입력 해주세요");
			return;
		} else if($("#userpw").val() == "") {
			alert("비밀번호를 입력 해주세요]");
			return;
		} else if($("#userpw").val() != $("#userpwcheck").val()) {
			alert("비밀번호를 다시 확인해주세요");
			return;
		} else if($("#doubleid").val() == "") {
			alert("아이디 중복확인하세요.");
			$("#userid").focus();
			return;
		} else if($("#doubleid").val() == "no") {
			alert("아이디 중복입니다.");
			$("#userid").focus();
			return;
		}else {
			$("#memberform").attr("action", "${root}/api/user/usercreate.do").submit();
		}
	});
	$("#idCheckBtn").click(function() {
		if($("#userid").val() == "") {
			alert("아이디를 입력 해주세요");
			return;
		}
		$.ajax({
			type:'GET',
			url:'/happyhouse/api/user/usersearch.do/'+$("#userid").val(),
			dataType:'text',
			success:function(data){
				if(data=="yes"){
					alert("사용 가능한 아이디입니다.");
				}else if(data=="no"){
					alert("아이디가 중복입니다.");					
				}
				$("#doubleid").val(data);
			},
	        error: function(errorThrown) {
	            alert(errorThrown.statusText);
	        }
        });
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
<c:if test="${enrolls!=null}">
성공했습니다
<a href="${root}/api/base/">홈</a>
</c:if>
<c:if test="${enrolls==null}">
<div class="container" align="center">
<div class="loginset">
	<div class="col-lg-5" align="center">
		<form id="memberform" method="post" action="">
		<input type="hidden" name="act" id="mvjoin" value="">
		<input type="hidden" class="form-control" id="usertype" name="usertype" value="member">
			<div class="form-group" align="left">
				<label for="name">아이디</label>
				<input type="text" class="form-control" id="userid" name="userid" placeholder="" maxlength="20" onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');">
				<input type="hidden" name="doubleid" id="doubleid" value="">
				<button type="button" class="btn btn-outline-primary" id="idCheckBtn">ID중복체크</button>
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="userpw" name="userpw" placeholder="" maxlength="20">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호확인</label>
				<input type="password" class="form-control" id="userpwcheck" name="userpwcheck" placeholder="" maxlength="20">
			</div>
			<div class="form-group" align="left">
				<label for="">이름</label>
				<input type="text" class="form-control" id="username" name="username" placeholder="" maxlength="20">
			</div>
			<div class="form-group" align="left">
				<label for="">이메일</label><br>
				<input type="text" class="form-control" id="useraddr" name="useraddr" placeholder="" size="25">
			</div>
			<div class="form-group" align="left">
				<label for="">연락처</label><br>
				<input type="text" class="form-control" id="userphone" name="userphone" placeholder="" size="25">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호 찾기 질문</label><br>
				<input type="text" class="form-control" id="userquestion" name="userquestion" placeholder="" size="25">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호 찾기 정답</label><br>
				<input type="text" class="form-control" id="usersol" name="usersol" placeholder="" size="25">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn">회원 가입</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			
			</div>
		</form>
	</div>
	</div>
</div>
</c:if>
</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>