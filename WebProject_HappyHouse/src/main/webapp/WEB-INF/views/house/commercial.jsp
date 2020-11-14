<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/init/stylesheet.jsp"%>
<script type="text/javascript">
	function search() {
		if (document.getElementById("word").value == "") {
			alert("검색어를 입력하지 않아 모든 정보를 조회합니다.");
		}
		document.getElementById("searchform").action = "${root}/house.do?";
		document.getElementById("searchform").submit();

	}
</script>
</head>
<body>
	<div id="wrap">
		<%@include file="/WEB-INF/views/header.jsp"%>
		<section>
		<form id="searchform" method="get" class="form-inline" action="">
			<input type="hidden" name="act" value="commercial">

				<table class="table table-borderless">
					<tr>

						<td align="center"><select class="form-control" name="key"
							id="key">
								<option value="all" selected="selected">전체 조회</option>
								<option value="dong">동</option>
								
						</select> <input type="text" class="form-control" placeholder="검색어를 입력하세요"
							name="word" id="word">
							<button type="button" class="btn btn-primary"
								onclick="javascript:search();">검색</button></td>
					</tr>
				</table>
			</form>
			<table
				style="border: 1px solid black; margin: auto; text-align: center; width: 100%">
				<thead>
					<tr style="border-bottom: 5px double black;">
						<th>번호</th>
						<th>가게명</th>
						<th>종류</th>
						<th>도시</th>
						<th>구</th>
						<th>동</th>
						<th>주소</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="commercial" items="${commercials}">

						<tr style="border-bottom: 1px solid black;">
							<td>${commercial.no }</td>
							<td>${commercial.shopname }</td>
							<td>${commercial.codename }</td>
							<td>${commercial.city }</td>
							<td>${commercial.gu }</td>
							<td>${commercial.dong }</td>
							<td>${commercial.address }</td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${commercials==null }">
					<div style="margin: 100px;">불러올 상권 정보가 없습니다.</div>
				</c:when>
				<c:when test="${commercials.size()==0 }">
					<div style="margin: 100px;">불러올 상권 정보가 없습니다.</div>
				</c:when>

			</c:choose>

			
		</section>
		<%@include file="/WEB-INF/views/footer.jsp"%>
	</div>
</body>
</html>