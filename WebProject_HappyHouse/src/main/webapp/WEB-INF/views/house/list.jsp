<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
<%@include file="/WEB-INF/views/init/stylesheet.jsp"%>

<script type="text/javascript">
	function search() {
		if (document.getElementById("word").value == "") {
			alert("검색어를 입력하지 않아 모든 정보를 조회합니다.");
		}
		document.getElementById("pg").value = 1;
		document.getElementById("searchform").action = "${root}/api/house/houselist/search";
		document.getElementById("searchform").submit();

	}
	function pageMove(pg) {
		document.getElementById("pg").value = pg;
		document.getElementById("searchform").action = "${root}/api/house/houselist/search";
		document.getElementById("searchform").submit();
	}
</script>

</head>
<body>
	<c:if test="${msg!=null}">
			<script>
				alert('${msg}');
			</script>
		</c:if>
	<div id="wrap">
		<%@include file="/WEB-INF/views/header.jsp"%>
		<section>

			<form id="searchform" method="get" class="form-inline" action="">
				<input type="hidden" name="act" value="list"> <input
					type="hidden" name="pg" id="pg" value="">


				<div id="searchdeal">
					<input type="checkbox" name="type" value="1"
						<c:if test="${info[1]==true}">checked</c:if>> 아파트 매매 <input
						type="checkbox" name="type" value="2"
						<c:if test="${info[2]==true}">checked</c:if>> 연립, 주택 매매<input
						type="checkbox" name="type" value="3"
						<c:if test="${info[3]==true}">checked</c:if>> 아파트 전월세<input
						type="checkbox" name="type" value="4"
						<c:if test="${info[4]==true}">checked</c:if>> 연립, 주택 전월세
				</div>
				<table class="table table-borderless">
					<tr>

						<td align="center"><select class="form-control" name="key"
							id="key">
								<option value="all"
									<c:if test="${keys[0]==true}">selected</c:if>>전체 조회</option>
								<option value="dong"
									<c:if test="${keys[1]==true}">selected</c:if>>동</option>
								<option value="aptName"
									<c:if test="${keys[2]==true}">selected</c:if>>아파트 이름</option>
						</select> <input type="text" class="form-control" placeholder="검색어를 입력하세요"
							name="word" id="word" value="${words}">
							<button type="button" class="btn btn-primary"
								onclick="javascript:search();">검색</button></td>
					</tr>
				</table>
			</form>
			<table
				class="table table-condensed" >
				<thead>
					<tr>
						<th>거래 번호</th>
						<th>동</th>
						<th>아파트 이름</th>
						<th>금 액</th>
						<th>면 적</th>
						<th>거래 구분</th>
						<th>거래 날짜</th>
					</tr>
				</thead>
				<tbody style="font-size:12px;">

					<c:forEach var="house" items="${houses}">

						<tr style="border-bottom: 1px solid black;">
							<td id="no"><a href="${root}/api/house/housedetail/${house.no}" style="font-weight:900;">${house.no }</a></td>
							<td>${house.dong }</td>
							<td>${house.aptName }</td>
							<td>${house.dealAmount }</td>
							<td>${house.area }</td>
							<td><c:choose>
									<c:when test="${house.type eq '1'}">아파트 매매</c:when>
									<c:when test="${house.type eq '2'}">연립 다세대 매매</c:when>
									<c:when test="${house.type eq '3'}">아파트 전월세</c:when>
									<c:when test="${house.type eq '4'}">연립 다세대 전월새</c:when>
								</c:choose></td>
							<td>${house.dealYear }/${house.dealMonth }/${house.dealDay }</td>
						</tr>


					</c:forEach>


				</tbody>
			</table>

			<c:choose>
				<c:when test="${houses==null }">
					<div class="houses_list" style="margin: 100px;">등록된 제품이 없습니다.</div>
				</c:when>
				<c:when test="${houses.size()==0 }">
					<div class="houses_list" style="margin: 100px;">등록된 제품이 없습니다.</div>
				</c:when>

			</c:choose>


			<table id="pagenavi">
				<tr>
					<td>
						<!-- page 출력부분 --> ${navigation.navigator}
					</td>
				</tr>
			</table>

		</section>
		<%@include file="/WEB-INF/views/footer.jsp"%>
	</div>
</body>
</html>