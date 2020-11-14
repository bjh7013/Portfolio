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
<title>SSAFY-비밀번호찾기</title>
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
										"${root}/api/user/usersearchpw.do").submit();
								
							}
						});
				$("#registerBtn2").click(
						function() {

							if ($("#sol").val() == "") {
								alert("아이디 입력!!!");
								return;
							} else {
								$("#memberform").attr("action",
										"${root}/api/user/usersearchpwsol.do").submit();
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
	<c:if test="${sols!=null}">
	
		<div class="container" align="center" >
	<div class="col-lg-6" align="center">
		<div id="pwmsg"><c:out value="${sols}"></c:out><br></div>
		<a href="${root}/api/user/userlogin.do"  class="btn btn-primary">로그인</a>
		</div>
		</div>
	</c:if>
	<c:if test="${sols==null}">
		<c:if test="${questions!=null}">
		<div class="container" align="center" >
				<div class="col-lg-6" align="center">
			<form id="memberform" method="post" action="" style="margin-top:10%;">
				<input type="hidden" name="act" id="mvjoin" value="">
				<div class="form-group" align="left">
					<label for="name">찾을 아이디 : </label> <input
						type="text" class="form-control" id="userid" name="userid"
						placeholder="" value="${userid}" readonly>
					<label for="name">비밀번호 확인 질문 : <c:out value="${questions}"></c:out></label> <input
						type="text" class="form-control" id="usersol" name="usersol"
						placeholder="">
					
				</div>
				<div class="form-group" align="center"  style="margin-top:60px;">
					<button type="button" class="btn btn-primary" id="registerBtn2">비밀번호 찾기</button>
					<button type="reset" class="btn btn-warning">초기화</button>
				
				</div>
				
			</form>
			</div></div>
		</c:if>
		<c:if test="${questions==null}">
			<div class="container" align="center">
				<div class="col-lg-6" align="center">
					<form id="memberform" method="post" action="" style="margin-top:10%;">
						<input type="hidden" name="act" id="mvjoin" value="">
						<div class="form-group" align="left">
							<label for="name">찾을 아이디 :</label> <input type="text"
								class="form-control" id="userid" name="userid" placeholder="" onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');" maxlength="20">
						</div>
						<div class="form-group" align="center" style="margin-top:60px;">
							<button type="button" class="btn btn-primary" id="registerBtn" >비밀번호 찾기</button>
							<button type="reset" class="btn btn-warning">초기화</button>
							
						</div>
					</form>
				</div>
			</div>
		</c:if>
	</c:if>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>

</html>