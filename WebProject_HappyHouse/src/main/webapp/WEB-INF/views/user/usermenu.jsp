<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<%@ include file="/WEB-INF/views/init/stylesheet.jsp" %>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
		<div class="container" align="center" >
			<div class="col-lg-6" align="center" style="height:450px;" >
		<c:if test="${userinfo.usertype=='member'}">
			<div style="margin-top:25%;">
				<a href="${root}/api/user/usermodify.do" class="btn btn-lg  btn-block btn-Info">회원 정보 변경</a><br><br>
			</div>
		</c:if>
		<c:if test="${userinfo.usertype=='manager'}">
			
			<a href="${root}/api/user/usersearch.do" class="btn  btn-block  btn-primary">회원 ID검색</a>
			<br>
			<br>
			<a href="${root}/api/user/usersearchall.do" class="btn    btn-block  btn-success">회원 전체검색</a>
			<br>
			<br>
			<a href="${root}/api/user/userdelete.do"class="btn btn-block  btn-danger">회원 삭제</a>
			<br>
			<br>
			<a href="${root}/api/faq/writefaq.do" class="btn  btn-block  btn-Info">자주 묻는 질문 등록</a>
			<br>
			<br>
			<a href="${root}/api/qnam/totallist.do" class="btn btn-block btn-Info">QnA관리</a>
			<br>
			<br>
		</c:if>
		</div></div>
		
	</section>
		<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
