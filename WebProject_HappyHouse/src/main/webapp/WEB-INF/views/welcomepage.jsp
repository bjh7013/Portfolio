<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/init/stylesheet.jsp"%>
<title>welcome</title>
<style>
#weather {
	height:250px;
	font-size:20px;
	position: absolute;
}
</style>
</head>
<body>
	<div id="weather">
		<%@include file="/WEB-INF/resources/w.jsp"%>
	</div>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>

		<img src="img/main_house.png" id="main_house_img">
	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>