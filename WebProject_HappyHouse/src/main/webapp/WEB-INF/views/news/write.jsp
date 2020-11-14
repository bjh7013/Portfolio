<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/init/el.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>공지사항 작성</title>
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
	function writeNews() {
		if (document.getElementById("newstitle").value == "") {
			alert("제목 입력!");
			return;
		} else if (document.getElementById("newscontent").value == "") {
			alert("기사 주소 입력!");
			return;
		} else if (document.getElementById("newscontent").value == "") {
			alert("출처 입력!");
			return;
		}else {
			document.getElementById("writeform").action = "${root}/api/news/writenews.do";
			document.getElementById("writeform").submit();
		}
	}
</script>
<%@include file="/WEB-INF/views/init/stylesheet.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	<section>
	<c:if test="${userinfo!=null}">
	<div class="container" align="center">
	<div class="col-lg-8" align="center">
			<h2 align="left">뉴스 작성</h2>
			<form id="writeform" method="post" action="">
				<input type="hidden" name="act" value="write">
				<div class="form-group" align="left">
					<label for="newstitle">제목 : </label> <input type="text"
						class="form-control" id="newstitle" name="newstitle">
				</div>
				<div class="form-group" align="left">
					<label for="newscontent">기사URL : </label>
					<textarea class="form-control" rows="15" id="newscontent"
						name="newscontent"></textarea>
				</div>
				<div class="form-group" align="left">
					<label for="newspublish">출처 : </label>
					<input type="text" class="form-control" id="newspublish" name="newspublish">
				</div>
				<button type="button" class="btn btn-primary"
					onclick="javascript:writeNews();">작성</button>
				<button type="reset" class="btn btn-warning">초기화</button>
				<a href="${root}/api/news/list.do" class="btn btn-secondary">뒤로</a>
			</form>
		</div>
	</div>
</c:if>
	</section>
</body>
</html>