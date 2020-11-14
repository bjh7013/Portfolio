<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<%@ include file="/WEB-INF/views/init/stylesheet.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
		<a href="${root}/user/userlogin.jsp">로그인</a> <a
			href="${root}/user/usercreate.jsp">회원가입</a> <a
			href="${root}/user/usersearchpw.jsp">비밀번호찾기</a><br>
			<c:if test="msg!=null">
			${msg}
			</c:if>
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
