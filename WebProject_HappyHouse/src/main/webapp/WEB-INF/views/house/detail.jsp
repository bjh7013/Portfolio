<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/init/stylesheet.jsp"%>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script>
 
$(document).ready(function(){
	$("#gocart").click(function(){
		$.get("${pageContext.request.contextPath}/api/house/cart.do"
				,{command:"aptno",aptno:$("#aptno").val()}
				, "json"
			);//get
	});
	
});

function cart() {
	document.getElementById("cartform").action = "${root }/api/house/cart.do";
	document.getElementById("cartform").submit();
}
</script>
</head>

<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>

		
		<div style="display: block;">
			<h4>${house.dong }의
				평균 가격보다
				<c:if test='${msg eq "비싸요!"}'>
					<p style="color: red;">${msg }</p>
				</c:if>
				<c:if test='${msg eq "저렴해요!"}'>
					<p style="color: blue;">${msg }</p>
				</c:if>
			</h4>
		</div>
		<div style="margin-top: 50px;">
			<table
				style="border: 0; text-align: center; width: 40%; height: 450px; float: left;">

				<tbody>

					<tr>
						<td>거래번호</td>
						<td id="aptno">${house.no }</td>
					</tr>
					<tr>
						<td>동</td>
						<td>${house.dong }${house.jibun }번지</td>
					</tr>
					<tr>
						<td>아파트 이름</td>
						<td>${house.aptName }${house.floor }층</a></td>
					</tr>
					<tr>
						<td>건축 연도</td>
						<td>${house.buildYear}년</td>
					</tr>
					<tr>
						<td>금액</td>
						<td>${house.dealAmount }원</td>
					</tr>
					<tr>
						<td>면적</td>
						<td>${house.area }평</td>
					</tr>

					<tr>
						<td>거래 구분</td>
						<td><c:choose>
								<c:when test="${house.type eq '1'}">아파트 매매</c:when>
								<c:when test="${house.type eq '2'}">연립 다세대 매매</c:when>
								<c:when test="${house.type eq '3'}">아파트 전월세</c:when>
								<c:when test="${house.type eq '4'}">연립 다세대 전월새</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td>거래 날짜</td>
						<td>${house.dealYear }년${house.dealMonth }월${house.dealDay }일</td>
					</tr>
				</tbody>
			</table>
			<%@include file="chart.jsp"%>
		</div>

		<div style="display: inline-block; margin-top: 40px; width:100%;">
				<form id="cartform" method="get" class="form-inline" action="" style="display: block; margin-top:20px;">
					<input type="hidden" name="cartid" id="cartid" value="${userinfo.userid}"> 
					<input type="hidden" name="aptno" id="aptno" value="${house.no}"> 
					<input type="hidden" name="dong" id="dong" value="${house.dong}"> 
					<button type="button" class="btn btn-primary" id="gocart" onclick="javascript:cart();">찜하기</button>
					<a  class="btn btn-secondary" id="back" href = "${root}/api/house/houselist/search" >뒤로</a>
				</form>
			<%-- <a href="${root }/api/house/cart.do/${userinfo.userid}/${house.no}" class="btn btn-primary" id="gocart">찜하기</a>
			<a href="${root}/api/house/houselist/search" class="btn btn-secondary">뒤로</a> --%>
		</div>
	</section>

	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>