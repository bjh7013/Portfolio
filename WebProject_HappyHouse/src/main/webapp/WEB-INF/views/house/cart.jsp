<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/init/stylesheet.jsp"%>
<script type="text/javascript">
	function del() {

		document.getElementById("cartbox").action = "${root}/api/house/cart/del";
		document.getElementById("cartbox").submit();

	}
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
		<c:if test="${msg!=null}">
			<script>
				alert('${msg}');
			</script>
		</c:if>
		<div style="min-height: 500px;">
			<div style="height: 300px;">
				<h4>찜한 아파트</h4>
				<form method="get" id="cartbox">
					<input type="hidden" name="cartid" id="cartid"
						value="${userinfo.userid}">

					<table class="table table-condensed" style="margin-top: 30px;">
						<thead>
							<tr>
								<th>거래 번호</th>
								<th>동</th>
								<th>아파트 명</th>
								<th>선택</th>
							</tr>
						</thead>
						<tbody style="height: 300px; overflow: scroll; font-size: 12px;">

							<c:forEach var="cartapt" items="${cartapts}">

								<tr style="border-bottom: 1px solid black;">
									<td><a href="${root}/api/house/housedetail/${cartapt.no}"
										style="font-weight: 900;">${cartapt.no }</a></td>
									<td>${cartapt.dong }</td>
									<td>${cartapt.aptName }${cartapt.floor }층</td>
									<td><input type="checkbox" name="del"
										value="${cartapt.no }"></td>
								</tr>
							</c:forEach>


						</tbody>
					</table>
				</form>
				<div style="margin-top: 5%; min-height: 40px;">
					<c:choose>
						<c:when test="${cartapts==null }">
							<a class="btn btn-link" id="back"
								href="${root}/api/house/houselist/search">원하는 아파트를 찾아보세요!</a>
						</c:when>
						<c:when test="${cartapts.size()==0 }">
							<div c style="">등록된 제품이 없습니다.</div>
						</c:when>

					</c:choose>
				</div>

				<button class="btn btn-secondary" id="delcart"
					onclick="javascript:del();">삭제</button>

			</div>
			<div style="margin-top: 100px;">
				<h4>매물 근처 공인중개사를 추천드려요!</h4>
				<table class="table table-condensed" style="margin-top: 30px;">
					<thead>
						<tr>

							<th>동</th>
							<th>공인중개사</th>
							<th>주소</th>
							<th>전화번호</th>
						</tr>
					</thead>
					<tbody style="height: 500px; overflow: scroll; font-size: 12px;">

						<c:forEach var="rsinfo" items="${rsinfos}">

							<tr style="border-bottom: 1px solid black;">
								<td>${rsinfo.dong }</td>
								<td>${rsinfo.rsshopname }</td>
								<td>${rsinfo.address }</td>
								<td>${rsinfo.tel }</td>

							</tr>
						</c:forEach>


					</tbody>
				</table>
				<div style="margin-top: 10%;">
					<c:choose>
						<c:when test="${rsinfos==null }">
							<div>근처 공인중개사가 없습니다.</div>
						</c:when>
						<c:when test="${rsinfos.size()==0 }">
							<div>근처 공인중개사가 없습니다.</div>
						</c:when>

					</c:choose>
				</div>
			</div>


		</div>

	</section>
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>