<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />

<script type="text/javascript">
	function login() {
		if (document.getElementById("userid").value == "") {
			alert("아이디 입력!!!");
			return;
		} else if (document.getElementById("userpw").value == "") {
			alert("비밀번호 입력!!!");
			return;
		} else {
			document.getElementById("loginform").action = "${root}/api/user/userlogin.do";
			document.getElementById("loginform").submit();
		}
	}

	function moveJoin() {
		document.location.href = "${root}/api/user/usercreate.do";
	}
</script>
<%@ include file="/WEB-INF/views/init/stylesheet.jsp"%>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/views/header.jsp"%>
	</div>
	<section>
		<div class="loginset">
			<div class="container" align="center">
				<div class="col-lg-6" align="center">
					<form id="loginform" method="post" action="">
						<div class="form-group" align="left">
							<label for="">아이디</label> <input type="text" class="form-control"
								id="userid" name="userid" placeholder="" value="${svid}"
								onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');"
								maxlength="20">
						</div>
						<div class="form-group" align="left">
							<label for="">비밀번호</label> <input type="password"
								class="form-control" id="userpw" name="userpw" placeholder=""
								onkeydown="javascript:if(event.keyCode == 13) {login();}">
						</div>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-lg btn-primary btn-block"
								onclick="javascript:login();">로그인</button>
						</div>
						<div style="margin-top: 30px;">
							<c:if test="${msg!=null}">
								<script>
									alert('${msg}');
								</script>
							</c:if>
							<a href="${root}/api/user/usersearchpw.do" class="btn btn-link"
								style="margin-bottom: 10px; color: #bdbdbd;">비밀번호를 잊으셨나요?</a><br>
						</div>
						<div>
							<button type="button" class="btn btn-outline-secondary"
								onclick="javascript:moveJoin();">회원가입</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>