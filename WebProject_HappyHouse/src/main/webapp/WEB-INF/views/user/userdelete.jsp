<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SSAFY-회원삭제</title>
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
										"${root}/api/user/userdelete.do")
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
		<c:if test="${deletes!=null}">
		삭제가 완료되었습니다.
		<a href="${root}/api/base/">홈</a>
		</c:if>
		<c:if test="${deletes==null}">
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
						<div class="form-group" align="center">
							<button type="button" class="btn btn-danger" id="registerBtn">회원
								정보 삭제</button>
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