<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="/WEB-INF/views/init/el.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/init/stylesheet.jsp" %>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
<c:out value="${msg}"></c:out>
<a href="${root}/index.jsp">홈</a>
</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>